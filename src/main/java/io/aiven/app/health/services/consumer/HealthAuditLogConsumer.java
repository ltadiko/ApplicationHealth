package io.aiven.app.health.services.consumer;

import io.aiven.app.health.exception.DatabaseGenericException;
import io.aiven.app.health.kafka.HealthEventConsumer;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthAuditLogConsumer {
    public static void startKafkaConsume() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            HealthEventConsumer consumer = new HealthEventConsumer();
            try {
                consumer.consumeAndStore();
            } catch (SQLException e) {
                throw new DatabaseGenericException("SQL queries failed while running due to " + e);
            }
        });
    }
}
