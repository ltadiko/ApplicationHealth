package io.aiven.app.health.configuration;

import java.util.Properties;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

/**
 * KafkaProperties adds Kafka server and ssl details
 *
 * @author : Lakshmaiah Tatikonda
 */
public final class KafkaProperties {
    private KafkaProperties() {
    }

    /**
     * @return : List of properties of Kafka Server and SSL connection details
     */
    public static Properties getKafkaProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", getProperty("kafka.bootstrapServers"));
        properties.put("security.protocol", "SSL");
        properties.put("ssl.truststore.location", getProperty("kafka.ssl.truststore.location"));
        properties.put("ssl.truststore.password", getProperty("kafka.ssl.truststore.password"));
        properties.put("ssl.keystore.type", getProperty("kafka.ssl.keystore.type"));
        properties.put("ssl.keystore.location", getProperty("kafka.ssl.keystore.location"));
        properties.put("ssl.keystore.password", getProperty("kafka.ssl.keystore.password"));
        properties.put("ssl.key.password", getProperty("kafka.ssl.key.password"));

        return properties;
    }
}
