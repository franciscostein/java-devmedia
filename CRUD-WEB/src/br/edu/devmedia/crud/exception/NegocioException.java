package br.edu.devmedia.crud.exception;

public class NegocioException extends Exception {

    public NegocioException(Exception e) {
        super(e.getMessage());
    }

    public NegocioException(String message) {
        super(message);
    }
}
