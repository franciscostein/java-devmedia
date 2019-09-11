package br.com.devmedia;

import br.com.devmedia.jwt.TokenJwt;
import br.com.devmedia.utils.Utils;

import java.util.Date;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        TokenJwt token = new TokenJwt(Utils.gerarChave());

        System.out.println("*** Gerando o JSON Web Token ***");
        Date dataDeExpiracao = Utils.definirDataDeExpiracao(60L);
        String jwt = token.gerarToken("Leandro", dataDeExpiracao);

        System.out.println("JWT: " + jwt);
        System.out.println("*** ====================== ***");

        System.out.println("*** Aguardando 05 segundos para testar validade do token ***");
        Thread.sleep(5000L);

        if (token.tokenValido()) {
            System.out.println("--> Token válido!");
            System.out.println("Nome do usuário presente no token: " + token.recuperarSubjectDoToken());
            System.out.println("Emissor do token: " + token.recuperarIssuerDoToken());
        } else {
            System.out.println("--> Token inválido!");
        }
    }

}
