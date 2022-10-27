package io.aiven.app.health.services.consumer;

import io.aiven.app.health.repository.ConsumerApplicationRepository;
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


class WebsiteHealthLogsServiceAvroSchemaTest {
    private ConsumerApplicationRepository consumerApplicationRepository = mock(ConsumerApplicationRepository.class);
    private WebsiteHealthLogsService underTest = new WebsiteHealthLogsService(consumerApplicationRepository);

    @Test
    @DisplayName("SHOULD return list of websites WHEN repository returns websites")
    void testAddWebsiteHealthStatusSuccess() throws SQLException {
        // Execute the service that uses the mocked repository
        underTest.addWebsiteHealthStatus(1, HEALTHY);

        // Validate the response
        verify(consumerApplicationRepository).addWebsiteHealthStatus(1, HEALTHY);
        verifyNoMoreInteractions(consumerApplicationRepository);
    }

    @Test
    @DisplayName("SHOULD throw SQLException WHEN repository throws SQLException")
    void testGetWebSitesSQLException() throws SQLException {
        doThrow(new SQLException("Connection Exception"))
                .when(consumerApplicationRepository).addWebsiteHealthStatus(1, HEALTHY);


        // Execute the service that uses the mocked repository
        assertThrows(SQLException.class, () -> underTest.addWebsiteHealthStatus(1, HEALTHY));

    }
}
