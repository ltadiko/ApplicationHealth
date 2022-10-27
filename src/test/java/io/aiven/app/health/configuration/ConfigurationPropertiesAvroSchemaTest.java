package io.aiven.app.health.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConfigurationPropertiesAvroSchemaTest {
    @Test
    @DisplayName("Should return configured value from application.properties WHEN the key is configured")
    void testConfigurations() {
        assertNotNull(getProperty("kafka.bootstrapServers"));
        assertNull(getProperty("invalid"));
    }

    @Test
    @DisplayName("Should return null from application.properties WHEN the key is not configured")
    void testConfigurationsWhenKeyIsNotPresent() {
        assertNull(getProperty("invalid"));
    }
}
