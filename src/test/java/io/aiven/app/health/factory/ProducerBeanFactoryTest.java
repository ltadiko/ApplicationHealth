package io.aiven.app.health.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProducerBeanFactoryTest {
    private ProducerBeanFactory underTest = new ProducerBeanFactory();

    @Test
    @DisplayName("Should return all beans from producer bean factory")
    void testAllBeans() {
        assertNotNull(underTest.getDatabaseConnection());
        assertNotNull(underTest.getHealthEventProducer());
        assertNotNull(underTest.getHealthEventProducer());
        assertNotNull(underTest.getWebsitesService());
        assertNotNull(underTest.getHealthCheck());
        assertNotNull(underTest.getPublishAuditLogScheduler());
    }

}
