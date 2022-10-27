package io.aiven.app.health.services.producer;

import io.aiven.app.health.avro.AppHealth;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;
import static io.aiven.app.health.models.HealthStatus.UNHEALTHY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HealthEventKafkaProducerAvroSchemaTest {
    KafkaProducer<Integer, AppHealth> kafkaProducer = mock(KafkaProducer.class);
    HealthEventKafkaProducer underTest = new HealthEventKafkaProducer(kafkaProducer);

    @Test
    @DisplayName("Should invoke Kafka producer")
    void testSendToTopic() {
        underTest.sendToTopic(1, UNHEALTHY);
        //verify
        verify(kafkaProducer).send(new ProducerRecord<>(getProperty("kafka.health.audit.topic"), new AppHealth(1, UNHEALTHY.toString())));
        verify(kafkaProducer).flush();
        verify(kafkaProducer).close();
    }
}
