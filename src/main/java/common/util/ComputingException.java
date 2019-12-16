package common.util;

public class ComputingException extends RuntimeException {

    public ComputingException(String message) {
        super(message);
    }

    public ComputingException(String message, Throwable cause) {
        super(message, cause);
    }
}
