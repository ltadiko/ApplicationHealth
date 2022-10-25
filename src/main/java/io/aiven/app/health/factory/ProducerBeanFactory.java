package io.aiven.app.health.factory;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.repository.ProducerApplicationRepository;
import io.aiven.app.health.services.producer.HealthCheck;
import io.aiven.app.health.services.producer.HealthEventKafkaProducer;
import io.aiven.app.health.services.producer.PublishAuditLogScheduler;
import io.aiven.app.health.services.producer.WebsitesService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static io.aiven.app.health.configuration.KafkaProperties.getKafkaProperties;

public class ProducerBeanFactory {
    public DatabaseConnection getDatabaseConnection() {
        return new DatabaseConnection();
    }

    public ProducerApplicationRepository getProducerApplicationRepository() {
        return new ProducerApplicationRepository(getDatabaseConnection());
    }

    public HealthEventKafkaProducer getHealthEventProducer() {
        Properties properties = getKafkaProperties();
        properties.put("key.serializer", IntegerSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        return new HealthEventKafkaProducer(new KafkaProducer<>(properties));
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
