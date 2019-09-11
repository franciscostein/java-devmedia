package main.br.edu.devmedia.rest;

import main.br.edu.devmedia.dao.NotaDAO;
import main.br.edu.devmedia.entidade.Nota;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/notas")
public class NotaService {

    private static final String CHARSET_UTF8 = ";charset=utf-8";

    private NotaDAO notaDAO;

    @PostConstruct  //Usamos para não usar um construtor, já que instanciar a classe é função do Jersey
    private void init() {
        notaDAO = new NotaDAO();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Nota> listarNotas() {
        List<Nota> lista = null;
        try {
            lista = notaDAO.listarNotas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @POST   //Verbo HTTP de criação
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)    //UTF8 pra corrigir problemas com acentos e outros caracteres
    @Produces(MediaType.TEXT_PLAIN)
    public String addNota(Nota nota) {
        String msg = "";

        System.out.println(nota.getTitulo());

        try {
            int idGerado = notaDAO.addNota(nota);
            msg = String.valueOf(idGerado);
            //msg = "Nota adicionada com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao adicionar nota";
            e.printStackTrace();
        }

        return msg;
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Nota buscarPorId(@PathParam("id") int idNota) {
        Nota nota = null;
        try {
            nota = notaDAO.buscarNotaPorId(idNota);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nota;
    }

    @PUT    //Verbo HTTP para edição
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
    @Produces(MediaType.TEXT_PLAIN)         //O Jersey faz a conversão, se sucesso cria o objeto Nota
    public String editarNota(Nota nota, @PathParam("id") int idNota) {
        String msg = "";

        System.out.println(nota.getTitulo());

        try {
            notaDAO.editarNota(nota, idNota);

            msg = "Nota alterada com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao editar nota";
            e.printStackTrace();
        }

        return msg;
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String editarNota(@PathParam("id") int idNota) {
        String msg = "";

        try {
            notaDAO.removerNota(idNota);

            msg = "Nota removida com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao remover a nota";
            e.printStackTrace();
        }

        return msg;
    }
}
