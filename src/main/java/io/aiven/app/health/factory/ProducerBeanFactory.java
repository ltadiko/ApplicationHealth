package io.aiven.app.health.factory;

import io.aiven.app.health.avro.AppHealth;
import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.repository.ProducerApplicationRepository;
import io.aiven.app.health.services.producer.HealthCheckPublisher;
import io.aiven.app.health.services.producer.HealthEventKafkaProducer;
import io.aiven.app.health.services.producer.PublishAuditLogScheduler;
import io.aiven.app.health.services.producer.WebsitesService;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.IntegerSerializer;

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

        return new HealthEventKafkaProducer(getKafkaProducer());
    }

    private KafkaProducer<Integer, AppHealth> getKafkaProducer() {
        Properties properties = getKafkaProperties();
        properties.put("key.serializer", IntegerSerializer.class.getName());
        properties.put("value.serializer", KafkaAvroSerializer.class.getName());
        properties.put("basic.auth.credentials.source", "USER_INFO");
        properties.put(KafkaAvroSerializerConfig.USER_INFO_CONFIG, "avnadmin" + ":" + "AVNS_jz09pBrRV6_FUhglAWK");
        properties.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, false);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "https://kafka-11984e8e-tlaxman88-efbc.aivencloud.com:20163");
        return new KafkaProducer<>(properties);
    }

    public WebsitesService getWebsitesService() {
        return new WebsitesService(getProducerApplicationRepository());
    }

    public HealthCheckPublisher getHealthCheck() {
        return new HealthCheckPublisher(getWebsitesService(), getHealthEventProducer());
    }

    public PublishAuditLogScheduler getPublishAuditLogScheduler() {
        return new PublishAuditLogScheduler(getHealthCheck());
    }
}
