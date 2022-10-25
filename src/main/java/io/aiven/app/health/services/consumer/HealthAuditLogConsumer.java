package io.aiven.app.health.services.consumer;

import io.aiven.app.health.exception.DatabaseGenericException;
import io.aiven.app.health.kafka.HealthEventConsumer;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthAuditLogConsumer {
    private HealthEventConsumer healthEventConsumer;

    public HealthAuditLogConsumer(HealthEventConsumer healthEventConsumer) {
        this.healthEventConsumer = healthEventConsumer;
    }

    public void startKafkaConsumer() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            try {
                healthEventConsumer.consumeAndStore();
            } catch (SQLException e) {
                throw new DatabaseGenericException("SQL queries failed while running due to " + e);
            }
        });
    }
}
