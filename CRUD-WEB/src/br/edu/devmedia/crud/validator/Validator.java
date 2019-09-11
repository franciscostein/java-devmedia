package br.edu.devmedia.crud.validator;

import br.edu.devmedia.crud.exception.ValidationException;

import java.util.Map;

public interface Validator {

    boolean validar(Map<String, Object> valores) throws ValidationException;
}
