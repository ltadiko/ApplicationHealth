package io.aiven.app.health;

import io.aiven.app.health.services.HealthAuditLogConsumer;
import io.aiven.app.health.services.PublishAuditLogScheduler;


public class MainApplication {
    public static void main(String[] args) {
        checkWebSiteStatusAndPublishToKafka();
        consumeFromKafkaAndStoreToDB();
    }

    private static void checkWebSiteStatusAndPublishToKafka() {
        PublishAuditLogScheduler publishAuditLogScheduler = new PublishAuditLogScheduler();
        publishAuditLogScheduler.schedule();
    }

    private static void consumeFromKafkaAndStoreToDB() {
        HealthAuditLogConsumer healthAuditLogConsumer = new HealthAuditLogConsumer();
        healthAuditLogConsumer.consumeAndStore();
    }
}
