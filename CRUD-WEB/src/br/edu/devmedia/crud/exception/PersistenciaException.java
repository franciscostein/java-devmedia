package br.edu.devmedia.crud.exception;

public class PersistenciaException extends Exception {

    public PersistenciaException(String erro) {
        super(erro);
    }

    public PersistenciaException(String erro, Exception e) {
        super(erro, e);
    }

    public PersistenciaException(Exception e) {
        super(e.getMessage());
    }
}
