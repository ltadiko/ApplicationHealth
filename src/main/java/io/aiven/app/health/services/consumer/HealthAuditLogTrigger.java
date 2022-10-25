package io.aiven.app.health.services.consumer;

import io.aiven.app.health.exception.DatabaseGenericException;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthAuditLogTrigger {
    private HealthEventKafkaConsumer healthEventKafkaConsumer;

    public HealthAuditLogTrigger(HealthEventKafkaConsumer healthEventKafkaConsumer) {
        this.healthEventKafkaConsumer = healthEventKafkaConsumer;
    }

    public void startKafkaConsumer() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(() -> {
            try {
                healthEventKafkaConsumer.consumeAndStore();
            } catch (SQLException e) {
                throw new DatabaseGenericException("SQL queries failed while running due to " + e);
            }
        });
    }
}
