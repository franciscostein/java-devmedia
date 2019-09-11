package br.com.devmedia.webservice.resources;

import br.com.devmedia.webservice.model.domain.Empregado;
import br.com.devmedia.webservice.service.RelationshipService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ProjetoEmpregadoResource {

    private RelationshipService service = new RelationshipService();

    @POST
    @Path("{empregadoId}")
    public Response save(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.saveRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }

    @GET
    public List<Empregado> getEmpregados(@PathParam("projetoId") long projetoId) {
        return service.getEmpregados(projetoId);
    }

    @DELETE
    @Path("{empregadoId}")
    public Response delete(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.deleteRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }
}
