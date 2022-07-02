package co.com.jorge.quotes.services;

public class ServiceJdbcException extends RuntimeException {

    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
