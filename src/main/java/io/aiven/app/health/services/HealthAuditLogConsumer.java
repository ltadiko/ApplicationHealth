package io.aiven.app.health.services;

import io.aiven.app.health.exception.DatabaseGenericException;
import io.aiven.app.health.kafka.HealthEventConsumer;
import io.aiven.app.health.repository.ApplicationRepository;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthAuditLogConsumer {
    public void consumeAndStore() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            HealthEventConsumer consumer = new HealthEventConsumer();
            ApplicationRepository applicationRepository = new ApplicationRepository();
            try {
                consumer.consumer(applicationRepository);
            } catch (SQLException e) {
                throw new DatabaseGenericException("SQL queries failed while running due to " + e);
            }
        });
    }
}
