package io.aiven.app.health.services.producer;

import io.aiven.app.health.avro.AppHealth;
import io.aiven.app.health.models.HealthStatus;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

public class HealthEventKafkaProducer {
    private static final Logger logger = LogManager.getLogger(HealthEventKafkaProducer.class);

    private KafkaProducer<Integer, AppHealth> kafkaProducer;

    public HealthEventKafkaProducer(KafkaProducer<Integer, AppHealth> kafkaProducer) {
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
        AppHealth appHealth = new AppHealth(websiteId, healthStatus.toString());
        Future<RecordMetadata> recordMetadataFuture = kafkaProducer.send(new ProducerRecord<>(topicName, appHealth));
        try {
            logger.debug("Message sent to partition {} and offset {}", recordMetadataFuture.get().partition(), recordMetadataFuture.get().offset());
        } catch (InterruptedException e) {
            logger.error("InterruptedException while sending to topic",e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            logger.error("ExecutionException while sending to topic",e);

        }
        kafkaProducer.flush();
    }


}
