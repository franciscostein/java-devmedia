package br.edu.alomundo.converter;

public interface Converter {

    String converter(Object object);

    Object converter(String valor);
}
