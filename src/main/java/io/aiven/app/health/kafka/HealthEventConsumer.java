package io.aiven.app.health.kafka;

import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.repository.ApplicationRepository;
import io.aiven.app.health.services.consumer.WebsiteHealthLogsService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;
import static io.aiven.app.health.configuration.KafkaProperties.getKafkaProperties;

/**
 * Kafka Consumer
 */
public class HealthEventConsumer {

    private static KafkaConsumer<Integer, String> createConsumer() {
        Properties properties = getKafkaProperties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "AppHealthProducer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "AppHealthProducerGrp");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        return new KafkaConsumer<>(properties);
    }

    /**
     *
     * @param applicationRepository
     * @throws SQLException
     */
    public void consumeAndStore() throws SQLException {
        Consumer<Integer, String> consumer = createConsumer();
        consumer.subscribe(Collections.singletonList(getProperty("kafka.health.audit.topic")));
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(Long.parseLong(getProperty("KafkaConsumer.pollIntervalInMilliSeconds"))));
            for (ConsumerRecord<Integer, String> consumerRecord : records) {
                WebsiteHealthLogsService websiteHealthLogsService = new WebsiteHealthLogsService();
                websiteHealthLogsService.addWebsiteHealthStatus(consumerRecord.key(), HealthStatus.valueOf(consumerRecord.value()));
                System.out.println("Sent to DB " + HealthStatus.valueOf(consumerRecord.value()));

            }
        }
    }

}
