package io.aiven.app.health;

import io.aiven.app.health.factory.ConsumerBeanFactory;
import io.aiven.app.health.factory.ProducerBeanFactory;
import io.aiven.app.health.services.consumer.HealthAuditLogConsumer;
import io.aiven.app.health.services.producer.PublishAuditLogScheduler;

/**
 * @author : Lakshmaiah Tatikonda
 * Main application starts the application by starting a scheduler to check health and publish
 * Also, it starts the kafka consumer to listen messages from Kafka and to store in DB
 */

public class MainApplication {

    public static void main(String[] args) {

        ProducerBeanFactory producerBeanFactory = new ProducerBeanFactory();
        PublishAuditLogScheduler publishAuditLogScheduler = producerBeanFactory.getPublishAuditLogScheduler();
        publishAuditLogScheduler.schedule();

        ConsumerBeanFactory consumerBeanFactory = new ConsumerBeanFactory();
        HealthAuditLogConsumer healthAuditLogConsumer = consumerBeanFactory.getHealthAuditLogConsumer();
        healthAuditLogConsumer.startKafkaConsumer();
    }

}
