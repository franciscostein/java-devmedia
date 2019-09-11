package br.com.devmedia.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
                                //Usa GlassFish 4.1
@WebService     //http://leandro-pc:8080/WebServiceSOAP_war_exploded/MyFirstSOAPWebServiceService?Tester
public class MyFirstSOAPWebService {

    @WebMethod  //Todos os métodos de uma classe @WebService são @WebMethod por padrão, só usar essa anotação para modificar alguma coisa
    public String ping(@WebParam(name = "nome") String nome) {
        return "Pong! " + nome;
    }

    public Integer getAge() {
        return new Random().nextInt();
    }

    public List<String> getMinhaPrimeiraLista() {
        List<String> toReturn = new LinkedList<>();
        toReturn.add("Item 1");
        toReturn.add("Item 2");
        toReturn.add("Item 3");
        toReturn.add("Item 4");
        toReturn.add("Item 5");
        return toReturn;
    }

    public List<Carro> getMeusCarros() {
        List<Carro> toReturn = new LinkedList<>();
        Carro carro = new Carro();
        carro.setNome("Focus");
        carro.setMarca("Ford");
        carro.setCor(Cor.AZUL);
        toReturn.add(carro);
        Carro carro1 = new Carro();
        carro1.setNome("Civic");
        carro1.setMarca("Honda");
        carro1.setCor(Cor.PRETO);
        toReturn.add(carro1);
        return toReturn;
    }
}
