package io.aiven.app.health.services;

import io.aiven.app.health.repository.ApplicationRepository;
import io.aiven.app.health.services.consumer.WebsiteHealthLogsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static io.aiven.app.health.models.HealthStatus.HEALTHY;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


class WebsiteHealthLogsServiceTest {
    private ApplicationRepository applicationRepository = mock(ApplicationRepository.class);
    private WebsiteHealthLogsService underTest = new WebsiteHealthLogsService(applicationRepository);

    @Test
    @DisplayName("SHOULD return list of websites WHEN repository returns websites")
    void testAddWebsiteHealthStatusSuccess() throws SQLException {
        // Execute the service that uses the mocked repository
        underTest.addWebsiteHealthStatus(1, HEALTHY);

        // Validate the response
        verify(applicationRepository).addWebsiteHealthStatus(1, HEALTHY);
        verifyNoMoreInteractions(applicationRepository);
    }

    @Test
    @DisplayName("SHOULD throw SQLException WHEN repository throws SQLException")
    void testGetWebSitesSQLxception() throws SQLException {
        doThrow(new SQLException("Connection Exception"))
                .when(applicationRepository).addWebsiteHealthStatus(1, HEALTHY);


        // Execute the service that uses the mocked repository
        assertThrows(SQLException.class, () -> underTest.addWebsiteHealthStatus(1, HEALTHY));

    }
}
