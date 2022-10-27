package io.aiven.app.health;

import io.aiven.app.health.avro.AppHealth;
import io.aiven.app.health.models.HealthStatus;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Future;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;
import static io.aiven.app.health.configuration.KafkaProperties.getKafkaProperties;

public class AvroSchemaTest {
    public static void main(String args[]) {
        try {
            Properties properties = getKafkaProperties();
            properties.put("key.serializer", IntegerSerializer.class.getName());
            properties.put("value.serializer", KafkaAvroSerializer.class.getName());
            properties.put("basic.auth.credentials.source", "USER_INFO");
            properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,120000);
            properties.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,120000);
            properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);



            properties.put(KafkaAvroSerializerConfig.USER_INFO_CONFIG, "avnadmin" + ":" + "AVNS_jz09pBrRV6_FUhglAWK");
            properties.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, false);
            properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "https://kafka-11984e8e-tlaxman88-efbc.aivencloud.com:20163");
            KafkaProducer<Integer, AppHealth> kafkaProducer = new KafkaProducer<>(properties);
            String topicName = getProperty("kafka.health.audit.topic");
            System.out.println("Before Producer");

            Future<RecordMetadata> record = kafkaProducer.send(new ProducerRecord<>(topicName, new AppHealth(1, HealthStatus.HEALTHY.toString())));
            System.out.println("after Producer");
            System.out.println(record.get().toString());
            System.out.println("partition" + record.get().partition());

            kafkaProducer.flush();
            kafkaProducer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // testConsumer();

    }

    public static void testConsumer() {
        Properties properties = getKafkaProperties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "AppHealthConsumer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "AppHealthConsumerGrp");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put("basic.auth.credentials.source", "USER_INFO");
        properties.put(KafkaAvroSerializerConfig.USER_INFO_CONFIG, "avnadmin" + ":" + "AVNS_jz09pBrRV6_FUhglAWK");
        properties.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, false);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "https://kafka-11984e8e-tlaxman88-efbc.aivencloud.com:20163");
        KafkaConsumer<Integer, AppHealth> kafkaConsumer = new KafkaConsumer(properties);
        String topicName = getProperty("kafka.health.audit.topic");
        kafkaConsumer.subscribe(Collections.singletonList(topicName));
        System.out.println("consumer starter");
        while (true) {
            ConsumerRecords<Integer, AppHealth> records = kafkaConsumer.poll(Duration.ofMillis(Long.parseLong(getProperty("KafkaConsumer.pollIntervalInMilliSeconds"))));
            System.out.println("consumer 11111111111111111");
            for (ConsumerRecord<Integer, AppHealth> consumerRecord : records) {
                System.out.println("consumer starter");
                System.out.println("============" + consumerRecord.value().toString());
            }
        }

    }
}
