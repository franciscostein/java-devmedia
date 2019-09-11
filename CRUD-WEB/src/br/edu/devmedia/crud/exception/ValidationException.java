package br.edu.devmedia.crud.exception;

public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Exception exception) {
        super(message, exception);
    }
}
