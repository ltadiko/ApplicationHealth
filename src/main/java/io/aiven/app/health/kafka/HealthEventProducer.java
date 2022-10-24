package io.aiven.app.health.kafka;

import io.aiven.app.health.models.HealthStatus;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;
import static io.aiven.app.health.configuration.KafkaProperties.getKafkaProperties;

public class HealthEventProducer {

    private static KafkaProducer<Integer, String> createProducer() {
        Properties properties = getKafkaProperties();
        properties.put("key.serializer", IntegerSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        return new KafkaProducer<>(properties);
    }


    public void sendToTopic(final int websiteId, final HealthStatus healthStatus) {
        KafkaProducer<Integer, String> producer = createProducer();
        producer.send(new ProducerRecord<>(getProperty("kafka.health.audit.topic"), websiteId, healthStatus.toString()));
        producer.flush();
        producer.close();
    }


}
