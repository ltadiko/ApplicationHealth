package io.aiven.app.health.factory;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.repository.ConsumerApplicationRepository;
import io.aiven.app.health.services.consumer.HealthAuditLogTrigger;
import io.aiven.app.health.services.consumer.HealthEventKafkaConsumer;
import io.aiven.app.health.services.consumer.WebsiteHealthLogsService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

import static io.aiven.app.health.configuration.KafkaProperties.getKafkaProperties;

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
        return new HealthEventKafkaConsumer(getWebsiteHealthLogsService(), getHealthAuditLogKafkaConsumer());
    }

    public static KafkaConsumer<Integer, String> getHealthAuditLogKafkaConsumer() {
        Properties properties = getKafkaProperties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "AppHealthProducer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "AppHealthProducerGrp");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        return new KafkaConsumer<>(properties);
    }


    public HealthAuditLogTrigger getHealthAuditLogConsumer() {
        return new HealthAuditLogTrigger(getHealthEventConsumer());
    }

}
