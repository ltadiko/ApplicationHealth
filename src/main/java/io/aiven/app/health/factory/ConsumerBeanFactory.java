package io.aiven.app.health.factory;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.services.consumer.HealthEventKafkaConsumer;
import io.aiven.app.health.repository.ConsumerApplicationRepository;
import io.aiven.app.health.services.consumer.HealthAuditLogTrigger;
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

    public HealthEventKafkaConsumer getHealthEventConsumer() {
        return new HealthEventKafkaConsumer(getWebsiteHealthLogsService());
    }

    public HealthAuditLogTrigger getHealthAuditLogConsumer() {
        return new HealthAuditLogTrigger(getHealthEventConsumer());
    }

}
