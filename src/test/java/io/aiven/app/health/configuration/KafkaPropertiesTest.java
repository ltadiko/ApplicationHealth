package io.aiven.app.health.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class KafkaPropertiesTest {
    @Test
    @DisplayName("Should return all mandatory and common kafka properties")
    void testConfigurations() {
        Properties properties = KafkaProperties.getKafkaProperties();
        assertNotNull(properties.getProperty("bootstrap.servers"));
        assertNotNull(properties.getProperty("security.protocol"));
        assertNotNull(properties.getProperty("ssl.truststore.location"));
        assertNotNull(properties.getProperty("ssl.truststore.password"));
        assertNotNull(properties.getProperty("ssl.keystore.type"));
        assertNotNull(properties.getProperty("ssl.keystore.location"));
        assertNotNull(properties.getProperty("ssl.keystore.password"));
        assertNotNull(properties.getProperty("ssl.key.password"));
    }
}
