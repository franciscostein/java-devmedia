package jdbc.exception;

public class ValidacaoException extends Exception {

    public ValidacaoException(String msg, Exception exception) {
        super(msg, exception);
    }

    public ValidacaoException(String msg) {
        super(msg);
    }
}
