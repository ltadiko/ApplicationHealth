package io.aiven.app.health.services.producer;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class PublishAuditLogSchedulerAvroSchemaTest {
    private HealthCheckPublisher healthCheckPublisher = mock(HealthCheckPublisher.class);
    PublishAuditLogScheduler underTest = new PublishAuditLogScheduler(healthCheckPublisher);

    @Test
    void testSchedule() {
        underTest.schedule();

        //verify
        //TODO : check how to verify async job
        // verify(healthCheck, timeout(10000)).checkWebsitesAndPublishStatus();
        verifyNoMoreInteractions(healthCheckPublisher);
    }
}
