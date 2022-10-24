package io.aiven.app.health.configuration;

import io.aiven.app.health.exception.ConfigurationFileIOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigurationProperties loads all Configurations and provide get key value functionality
 *
 * @author : Lakshmaiah Tatikonda
 */
public final class ConfigurationProperties {
    private ConfigurationProperties() {
    }

    private static final Properties properties = new Properties();

    /**
     * loads all configurations from application.properties file and adds to properties
     */
    static {
        try (InputStream fis = ConfigurationProperties.class.getResourceAsStream("/application.properties")) {
            properties.load(fis);
        } catch (IOException ioe) {
            throw new ConfigurationFileIOException("Exception while reading configuration file" + ioe);
        }
    }

    /**
     * @param key : Input key
     * @return returns configured value of the property key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
