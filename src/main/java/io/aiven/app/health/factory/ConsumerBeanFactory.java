package io.aiven.app.health.factory;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.kafka.HealthEventConsumer;
import io.aiven.app.health.repository.ConsumerApplicationRepository;
import io.aiven.app.health.services.consumer.HealthAuditLogConsumer;
import io.aiven.app.health.services.consumer.WebsiteHealthLogsService;

public class ConsumerBeanFactory {
    public DatabaseConnection getDatabaseConnection() {
        return new DatabaseConnection();
    }

    public ConsumerApplicationRepository getConsumerApplicationRepository() {
        return new ConsumerApplicationRepository(getDatabaseConnection());
    }

    public WebsiteHealthLogsService getWebsiteHealthLogsService() {
        return new WebsiteHealthLogsService(getConsumerApplicationRepository());
    }

    public HealthEventConsumer getHealthEventConsumer() {
        return new HealthEventConsumer(getWebsiteHealthLogsService());
    }

    public HealthAuditLogConsumer getHealthAuditLogConsumer() {
        return new HealthAuditLogConsumer(getHealthEventConsumer());
    }

}
