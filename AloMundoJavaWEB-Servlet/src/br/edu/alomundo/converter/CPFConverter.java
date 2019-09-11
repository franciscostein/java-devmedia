package br.edu.alomundo.converter;

import br.edu.alomundo.util.Util;

public class CPFConverter implements Converter {

    public String converter(Object object) {

        String cpf = (String) object;

        return Util.imprimeCPF(cpf);
    }

    public Object converter(String valor) {

        valor.replaceAll("\\.", "").replaceAll(".", "");

        return Long.parseLong(valor);
    }
}
