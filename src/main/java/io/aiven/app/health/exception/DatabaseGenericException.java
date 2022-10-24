package io.aiven.app.health.exception;

/**
 * Exception is thrown while database connection or query related exceptions
 */
public class DatabaseGenericException extends RuntimeException {
    public DatabaseGenericException(String message) {
        super(message);
    }
}
