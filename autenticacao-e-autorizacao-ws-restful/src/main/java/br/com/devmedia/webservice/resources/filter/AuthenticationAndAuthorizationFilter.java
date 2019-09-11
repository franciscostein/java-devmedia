package br.com.devmedia.webservice.resources.filter;

import br.com.devmedia.webservice.domain.ErrorMessage;
import br.com.devmedia.webservice.domain.Tipo;
import br.com.devmedia.webservice.domain.Usuario;
import br.com.devmedia.webservice.service.UsuarioService;
import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Provider
@AcessoRestrito
public class AuthenticationAndAuthorizationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_AUTHORIZATION_PREFIX = "Basic ";

    private UsuarioService usuarioService = new UsuarioService();

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> headersAutorizacao = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
        if((headersAutorizacao != null) && (headersAutorizacao.size() > 0)) {
            String dadosAutorizacao = headersAutorizacao.get(0);
            Usuario usuarioDoHeader = obeterUsuarioDoHeader(dadosAutorizacao);
            Usuario usuarioAutenticado = usuarioService.autenticarUsuario(usuarioDoHeader);
            if (usuarioAutenticado != null) {
                autorizarUsuario(requestContext, usuarioAutenticado);
                return;
            }
        }
        Response naoAutorizado = Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage("Usuário não autorizado", Response.Status.UNAUTHORIZED.getStatusCode()))
                .build();
        requestContext.abortWith(naoAutorizado);
    }

    private void autorizarUsuario(ContainerRequestContext requestContext, Usuario usuarioAutenticado) {
        Class<?> classeDoRecurso = resourceInfo.getResourceClass();
        List<Tipo> permissoesDaClasse = recuperarPermissoes(classeDoRecurso);

        Method metodoDoRecurso = resourceInfo.getResourceMethod();
        List<Tipo> permissoesDoMetodo = recuperarPermissoes(metodoDoRecurso);

        try {
            if (permissoesDoMetodo.isEmpty()) {
                verificarPermissoes(permissoesDaClasse, requestContext, usuarioAutenticado);
            } else {
                verificarPermissoes(permissoesDoMetodo, requestContext, usuarioAutenticado);
            }

        } catch (Exception ex) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                            .entity(new ErrorMessage("Usuário não tem permissão para executar essa função.",
                                    Response.Status.FORBIDDEN.getStatusCode()))     //403
                            .build());
        }
    }

    private void verificarPermissoes(List<Tipo> permissoes, ContainerRequestContext requestContext, Usuario usuario) throws Exception {
        if (permissoes.contains(usuario.getTipo())) {
            long idUsuarioAcessado = recuperarIdDaURL(requestContext);

            if ((Tipo.CLIENTE.equals(usuario.getTipo())) && (usuario.getId() == idUsuarioAcessado)) {
                return;
            } else if (!Tipo.CLIENTE.equals(usuario.getTipo())) {
                return;
            }
        }
        throw new Exception();
    }

    private long recuperarIdDaURL(ContainerRequestContext requestContext) {
        String idObtidoDaURL = requestContext.getUriInfo().getPathParameters().getFirst("usuarioId");
        if (idObtidoDaURL == null) {
            return 0;
        } else {
            return Long.parseLong(idObtidoDaURL);
        }
    }

    private List<Tipo> recuperarPermissoes(AnnotatedElement elementoAnotado) {
        AcessoRestrito acessoRetrito = elementoAnotado.getAnnotation(AcessoRestrito.class);
        if (acessoRetrito == null) {
            return new ArrayList<Tipo>();
        } else {
            Tipo[] permissoes = acessoRetrito.value();
            return Arrays.asList(permissoes);
        }
    }

    private Usuario obeterUsuarioDoHeader(String dadosAutorizacao) {
        dadosAutorizacao = dadosAutorizacao.replaceFirst(BASIC_AUTHORIZATION_PREFIX, "");
        String dadosDecodificados = Base64.decodeAsString(dadosAutorizacao);
        StringTokenizer dadosTokenizer = new StringTokenizer(dadosDecodificados,":");   //Separa o nomeUsuario:senha pelo :
        Usuario usuario = new Usuario();
        usuario.setUsername(dadosTokenizer.nextToken());
        usuario.setPassword(dadosTokenizer.nextToken());
        return usuario;
    }
}
