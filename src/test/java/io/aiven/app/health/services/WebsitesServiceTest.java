package io.aiven.app.health.services;

import io.aiven.app.health.models.Website;
import io.aiven.app.health.repository.ProducerApplicationRepository;
import io.aiven.app.health.services.producer.WebsitesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


class WebsitesServiceTest {
    private ProducerApplicationRepository producerApplicationRepository = mock(ProducerApplicationRepository.class);
    private WebsitesService underTest = new WebsitesService(producerApplicationRepository);

    @Test
    @DisplayName("SHOULD return list of websites WHEN repository returns websites")
    void testGetWebSitesSuccess() throws MalformedURLException, SQLException {
        // Setup mock scenario
        when(producerApplicationRepository.getWebsites()).thenReturn(Arrays.asList(new Website(1, new URL("https://google.com"), "google"), new Website(1, new URL("https://yahoo.com"), "yahoo")));


        // Execute the service that uses the mocked repository
        List<Website> websiteList = underTest.getWebsites();

        // Validate the response
        assertNotNull(websiteList);
        assertEquals(2, websiteList.size());
        verify(producerApplicationRepository).getWebsites();
        verifyNoMoreInteractions(producerApplicationRepository);
    }

    @Test
    @DisplayName("SHOULD throw SQLException WHEN repository throws SQLException")
    void testGetWebSitesSQLxception() throws MalformedURLException, SQLException {
        // Setup mock scenario
        when(producerApplicationRepository.getWebsites()).thenThrow(new SQLException("Connection Exception"));


        // Execute the service that uses the mocked repository
        assertThrows(SQLException.class, () -> underTest.getWebsites());

        // Validate the response
        verify(producerApplicationRepository).getWebsites();
        verifyNoMoreInteractions(producerApplicationRepository);
    }

    @Test
    @DisplayName("SHOULD throw MalformedURLException WHEN repository throws MalformedURLException")
    void testGetWebSitesMalformedURLException() throws MalformedURLException, SQLException {
        // Setup mock scenario
        when(producerApplicationRepository.getWebsites()).thenThrow(new MalformedURLException("Malformed URL Exception"));


        // Execute the service that uses the mocked repository
        assertThrows(MalformedURLException.class, () -> underTest.getWebsites());

        // Validate the response
        verify(producerApplicationRepository).getWebsites();
        verifyNoMoreInteractions(producerApplicationRepository);
    }
}
