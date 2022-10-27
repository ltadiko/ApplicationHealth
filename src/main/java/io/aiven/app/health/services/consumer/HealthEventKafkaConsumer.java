package io.aiven.app.health.services.consumer;

import io.aiven.app.health.models.HealthStatus;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Collections;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

/**
 * Kafka Consumer
 */
public class HealthEventKafkaConsumer {

    private static final Logger logger = LogManager.getLogger(HealthEventKafkaConsumer.class);

    private WebsiteHealthLogsService websiteHealthLogsService;
    private KafkaConsumer<Integer, String> kafkaConsumer;

    public HealthEventKafkaConsumer(WebsiteHealthLogsService websiteHealthLogsService,
                                    KafkaConsumer<Integer, String> kafkaConsumer) {
        this.websiteHealthLogsService = websiteHealthLogsService;
        this.kafkaConsumer = kafkaConsumer;
    }


    /**
     * @throws SQLException
     */
    public void consumeAndStore() throws SQLException {
        kafkaConsumer.subscribe(Collections.singletonList(getProperty("kafka.health.audit.topic")));
        while (true) {
            ConsumerRecords<Integer, String> records = kafkaConsumer.poll(Duration.ofMillis(Long.parseLong(getProperty("KafkaConsumer.pollIntervalInMilliSeconds"))));
            for (ConsumerRecord<Integer, String> consumerRecord : records) {
                logger.debug("Health data for website {} received successfully", consumerRecord.key());
                websiteHealthLogsService.addWebsiteHealthStatus(consumerRecord.key(), HealthStatus.valueOf(consumerRecord.value()));
                logger.debug("Health data for website {} stored successfully", consumerRecord.key());
            }
        }
    }

}
