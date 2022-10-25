package io.aiven.app.health;

import io.aiven.app.health.factory.ConsumerBeanFactory;
import io.aiven.app.health.factory.ProducerBeanFactory;
import io.aiven.app.health.services.consumer.HealthAuditLogTrigger;
import io.aiven.app.health.services.producer.PublishAuditLogScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author : Lakshmaiah Tatikonda
 * Main application starts the application by starting a scheduler to check health and publish
 * Also, it starts the kafka consumer to listen messages from Kafka and to store in DB
 */

public class MainApplication {
    private static final Logger logger = LogManager.getLogger(MainApplication.class);

    public static void main(String[] args) {
        ProducerBeanFactory producerBeanFactory = new ProducerBeanFactory();
        PublishAuditLogScheduler publishAuditLogScheduler = producerBeanFactory.getPublishAuditLogScheduler();
        publishAuditLogScheduler.schedule();
        logger.info("Publish audit log Scheduler started");

        ConsumerBeanFactory consumerBeanFactory = new ConsumerBeanFactory();
        HealthAuditLogTrigger healthAuditLogTrigger = consumerBeanFactory.getHealthAuditLogConsumer();
        healthAuditLogTrigger.startKafkaConsumer();
        logger.info("Audit log kafka consumer started");

    }

}
