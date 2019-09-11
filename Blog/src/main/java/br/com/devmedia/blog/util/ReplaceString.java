package br.com.devmedia.blog.util;

import java.text.Normalizer;

public class ReplaceString {

    public static String formatarPermalink(String value) {

        // Persistência com JPA! -> persistencia_com_jpa

        String link = value.trim();

        link = link.toLowerCase();

        //A normalizer tira os acentos mas deixando as letras
        link = Normalizer.normalize(link, Normalizer.Form.NFD);

        //       \\s é o espaço
        link = link.replaceAll("\\s", "_");

        //      \\W tira tds caracteres não alfanumericos
        link = link.replaceAll("\\W", "");

        // garante que terá apenas 1 underline
        link = link.replaceAll("\\_+", "_");

        return link;
    }
}
