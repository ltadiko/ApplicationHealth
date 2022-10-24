package io.aiven.app.health.services;

import org.junit.jupiter.api.BeforeEach;

class HealthAuditLogPublisherTest {
    PublishAuditLogScheduler underTest;

    @BeforeEach
    void setup() {
        underTest = new PublishAuditLogScheduler();
    }


}
