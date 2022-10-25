package io.aiven.app.health.services.producer;

import io.aiven.app.health.models.HealthStatus;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

public class HealthEventKafkaProducer {
    private KafkaProducer<Integer, String> kafkaProducer;

    public HealthEventKafkaProducer(KafkaProducer<Integer, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * Method publishes status of website to kafka
     *
     * @param websiteId    : Unique id of the website
     * @param healthStatus : Health Status of the website
     */
    public void sendToTopic(final int websiteId, final HealthStatus healthStatus) {
        String topicName = getProperty("kafka.health.audit.topic");
        kafkaProducer.send(new ProducerRecord<>(topicName, websiteId, healthStatus.toString()));
        kafkaProducer.flush();
        kafkaProducer.close();
    }


}
