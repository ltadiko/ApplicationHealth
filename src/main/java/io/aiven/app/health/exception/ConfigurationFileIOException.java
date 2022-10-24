package io.aiven.app.health.exception;

/**
 * Exception is thrown when IO input operation fails while reading application configuration file
 */
public class ConfigurationFileIOException extends RuntimeException {
    public ConfigurationFileIOException(String message) {
        super(message);
    }
}
