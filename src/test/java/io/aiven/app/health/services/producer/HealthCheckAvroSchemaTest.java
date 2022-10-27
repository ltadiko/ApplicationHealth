package io.aiven.app.health.services.producer;

import io.aiven.app.health.exception.DatabaseGenericException;
import io.aiven.app.health.exception.InvalidURLException;
import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.models.Website;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HealthCheckAvroSchemaTest {
    WebsitesService websitesService = mock(WebsitesService.class);
    private HealthEventKafkaProducer healthEventKafkaProducer = mock(HealthEventKafkaProducer.class);
    private HealthCheckPublisher healthCheckPublisher = new HealthCheckPublisher(websitesService, healthEventKafkaProducer);

    @Test
    @DisplayName("SHOULD publish to kafka WHEN websites are configured")
    void checkWebsitesAndPublishStatus() throws MalformedURLException, SQLException {
        //given
        when(websitesService.getWebsites()).thenReturn((Arrays.asList(
                new Website(1, new URL("https://unhealthy.website.neverwork.com"), "google"),
                new Website(2, new URL("https://unhealthy.website.neverwork2.com"), "yahoo"))));
        //when
        healthCheckPublisher.checkWebsitesAndPublishStatus();

        //then
        verify(websitesService).getWebsites();
        verify(healthEventKafkaProducer).sendToTopic(1, HealthStatus.UNHEALTHY);
        verify(healthEventKafkaProducer).sendToTopic(2, HealthStatus.UNHEALTHY);
        verifyNoMoreInteractions(websitesService, healthEventKafkaProducer);


    }

    @Test
    @DisplayName("SHOULD throw InvalidURLException WHEN URL conversion fails")
    void checkInvalidURLException() throws MalformedURLException, SQLException {
        //given
        doThrow(MalformedURLException.class).when(websitesService).getWebsites();
        //when
        assertThrows(InvalidURLException.class, () -> healthCheckPublisher.checkWebsitesAndPublishStatus());

        //then
        verify(websitesService).getWebsites();
        verifyNoMoreInteractions(websitesService, healthEventKafkaProducer);
    }


    @Test
    @DisplayName("SHOULD throw DatabaseGenericException WHEN SQL fails")
    void checkSQLException() throws MalformedURLException, SQLException {
        //given
        doThrow(SQLException.class).when(websitesService).getWebsites();
        //when
        assertThrows(DatabaseGenericException.class, () -> healthCheckPublisher.checkWebsitesAndPublishStatus());

        //then
        verify(websitesService).getWebsites();
        verifyNoMoreInteractions(websitesService, healthEventKafkaProducer);
    }
}
