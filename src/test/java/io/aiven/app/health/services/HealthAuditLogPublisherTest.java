package io.aiven.app.health.services;

import io.aiven.app.health.services.producer.PublishAuditLogScheduler;
import org.junit.jupiter.api.BeforeEach;

class HealthAuditLogPublisherTest {
    PublishAuditLogScheduler underTest;

    @BeforeEach
    void setup() {
        underTest = new PublishAuditLogScheduler();
    }


}
