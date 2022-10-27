package io.aiven.app.health.services.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

class HealthAuditLogTriggerAvroSchemaTest {
    HealthEventKafkaConsumer healthEventKafkaConsumer = mock(HealthEventKafkaConsumer.class);
    HealthAuditLogTrigger underTest = new HealthAuditLogTrigger(healthEventKafkaConsumer);

    @Test
    @DisplayName("SHOULD complete successful list of websites WHEN healthEventKafkaConsumer ")
    void testSuccessfulKafkaConsumer() throws SQLException {
        //given
        underTest.startKafkaConsumer();
        //then
        verify(healthEventKafkaConsumer, timeout(5000).times(1)).consumeAndStore();
    }

    @Test
    @DisplayName("SHOULD throw SQL exception when kafka consumer throw SQL exception")
    void testKafkaConsumerSQLException() throws SQLException {
        //given
        doThrow(new SQLException("Connection Exception")).when(healthEventKafkaConsumer).consumeAndStore();
        //when
        underTest.startKafkaConsumer();
        //then
        verify(healthEventKafkaConsumer).consumeAndStore();
    }
}
