package pl.edziennik.client.exception;

public class ViewException extends RuntimeException{

    public ViewException(String message) {
        super(message);
    }

    public ViewException(String message, Throwable cause) {
        super(message, cause);
    }
}
