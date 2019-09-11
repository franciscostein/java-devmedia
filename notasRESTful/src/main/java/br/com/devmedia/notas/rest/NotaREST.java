package br.com.devmedia.notas.rest;

import br.com.devmedia.notas.model.Nota;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

import java.util.List;

public class NotaREST {

    private Client client;
    private WebResource webResource;

    public NotaREST() {
        ClientConfig clientConfig = new DefaultClientConfig(GensonProvider.class);
        client = Client.create(clientConfig);
        client.addFilter(new LoggingFilter(System.out));
        webResource = client.resource("http://devmedianotesapi.azurewebsites.net/api/");
    }

    public List<Nota> listar() {
        return webResource.path("Notes").get(new GenericType<List<Nota>>(){});
    }

    public Nota obter(Integer id) {
        return webResource.path("Notes").path(id.toString()).get(new GenericType<Nota>(){});
    }

    public void atualizar(Nota nota) {
        webResource.path("Notes").path(nota.getId().toString()).put(ClientResponse.class, nota);
    }                                                               //ClientResponse tras as informações de retorno da atualização

    public void remover(Integer id) {
        webResource.path("Notes").path(id.toString()).delete();
    }

    public void inserir(Nota nota) {
        webResource.path("Notes").post(new GenericType<Nota>(){}, nota);
    }

    public static void main(String[] args) {

        NotaREST notaREST = new NotaREST();
        List<Nota> notas = notaREST.listar();

        for (Nota nota : notas) {

            System.out.println(nota);
        }
    }
}
