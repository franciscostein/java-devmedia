package br.com.devmedia.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

public class TokenJwt {

    private Key chave;

    private String jwt;

    public TokenJwt(Key chave) {
        this.chave = chave;
    }

    public String gerarToken(String nomeUsuario, Date dataExpiracao) {
        jwt = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(nomeUsuario)
                .setIssuer("DevMedia")
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, chave)
                .compact();
        return jwt;
    }

    public boolean tokenValido() {
        try {
            Jwts.parser().setSigningKey(chave).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String recuperarSubjectDoToken() {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(jwt);
        return claimsJws.getBody().getSubject();
    }

    public String recuperarIssuerDoToken() {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(jwt);
        return claimsJws.getBody().getIssuer();
    }

}
