package io.aiven.app.health.factory;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.repository.ProducerApplicationRepository;
import io.aiven.app.health.services.producer.HealthCheck;
import io.aiven.app.health.services.producer.HealthEventProducer;
import io.aiven.app.health.services.producer.PublishAuditLogScheduler;
import io.aiven.app.health.services.producer.WebsitesService;

public class ProducerBeanFactory {
    public DatabaseConnection getDatabaseConnection() {
        return new DatabaseConnection();
    }

    public ProducerApplicationRepository getProducerApplicationRepository() {
        return new ProducerApplicationRepository(getDatabaseConnection());
    }

    public HealthEventProducer getHealthEventProducer() {
        return new HealthEventProducer();
    }

    public WebsitesService getWebsitesService() {
        return new WebsitesService(getProducerApplicationRepository());
    }

    public HealthCheck getHealthCheck() {
        return new HealthCheck(getWebsitesService(), getHealthEventProducer());
    }

    public PublishAuditLogScheduler getPublishAuditLogScheduler() {
        return new PublishAuditLogScheduler(getHealthCheck());
    }
}
