package br.com.devmedia.webservice.resources;

import br.com.devmedia.webservice.model.domain.Produto;
import br.com.devmedia.webservice.resources.Beans.ProdutoFilterBean;
import br.com.devmedia.webservice.service.ProdutoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

//      http://localhost:8080/webservice/webapi/produtos[/id]
@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")    //Colocado aqui pra não ficar código repetido sobre os métodos, o Jersey sabe qual metodo consome ou produz
public class ProdutoResource {

    //      Verbos HTTP
    /*
    * GET    - Recuperar
    * POST   - Criar
    * PUT    - Atualizar
    * DELETE - Remover
    * */
    private ProdutoService service = new ProdutoService();

    @GET
    public List<Produto> geProdutos(@BeanParam ProdutoFilterBean produtoFilter) {
        if (produtoFilter.getOffset() >= 0 && produtoFilter.getLimit() > 0) {
            return service.getProdutosByPagination(produtoFilter.getOffset(), produtoFilter.getLimit());
        }
        if (produtoFilter.getName() != null) {
            return service.getProdutoByName(produtoFilter.getName());
        }
        return service.getProdutos();
    }

    @GET
    @Path("{produtoId}")
    public Produto getProduto(@PathParam("produtoId") long id) {
        return service.getProduto(id);
    }

    @POST
    public Response save(Produto produto) {
        produto = service.saveProduto(produto);
        return Response.status(Status.CREATED)
                .entity(produto)                //Dessa forma retorna o status HTTP 201 Created
                .build();
    }

    @PUT
    @Path("{produtoId}")
    public void update(@PathParam("produtoId") long id, Produto produto) {
        produto.setId(id);
        service.updateProduto(produto);
    }

    @DELETE
    @Path("{produtoId}")
    public void delete(@PathParam("produtoId") long id) {
        service.deleteProduto(id);
    }
}
