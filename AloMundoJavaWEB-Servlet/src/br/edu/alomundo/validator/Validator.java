package br.edu.alomundo.validator;

import br.edu.alomundo.exception.ValidationException;

import java.util.Map;

public interface Validator {

    boolean validar(Map<String, Object> valores) throws ValidationException;
}
