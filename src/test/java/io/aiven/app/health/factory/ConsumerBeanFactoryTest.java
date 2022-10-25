package io.aiven.app.health.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsumerBeanFactoryTest {
    private ConsumerBeanFactory underTest = new ConsumerBeanFactory();

    @Test
    @DisplayName("Should return all beans from consumer bean factory")
    void testAllBeans() {
        assertNotNull(underTest.getDatabaseConnection());
        assertNotNull(underTest.getConsumerApplicationRepository());
        assertNotNull(underTest.getWebsiteHealthLogsService());
        assertNotNull(underTest.getHealthAuditLogConsumer());
    }

}
