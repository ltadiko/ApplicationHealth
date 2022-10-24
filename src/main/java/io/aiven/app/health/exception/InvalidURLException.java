package io.aiven.app.health.exception;

/**
 * Exception is thrown when URL is Malformed
 */
public class InvalidURLException extends RuntimeException {
    public InvalidURLException(String message) {
        super(message);
    }
}
