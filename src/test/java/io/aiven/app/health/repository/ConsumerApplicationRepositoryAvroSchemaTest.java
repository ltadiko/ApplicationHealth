package io.aiven.app.health.repository;

import io.aiven.app.health.connection.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static io.aiven.app.health.models.HealthStatus.UNHEALTHY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ConsumerApplicationRepositoryAvroSchemaTest {

    private DatabaseConnection databaseConnection = mock(DatabaseConnection.class);

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    ConsumerApplicationRepository consumerApplicationRepository;

    @BeforeEach
    public void setUp() {
        consumerApplicationRepository = new ConsumerApplicationRepository(databaseConnection);


    }

    @Test
    @DisplayName("Should be insert query update successful when connection and statement are fine")
    void testInsertTable() throws SQLException {
        //given
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement("INSERT INTO WEBSITE_HEALTH_LOGS(id,website_id,status,created_on)  VALUES(DEFAULT,?,?,?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        //when
        consumerApplicationRepository.addWebsiteHealthStatus(1, UNHEALTHY);

        //verify
        verify(databaseConnection).getConnection();
        verify(connection).prepareStatement("INSERT INTO WEBSITE_HEALTH_LOGS(id,website_id,status,created_on)  VALUES(DEFAULT,?,?,?)");
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).setString(2, UNHEALTHY.toString());
        verify(preparedStatement).setTimestamp(anyInt(), any());
        verify(preparedStatement).executeUpdate();
        verify(connection).close();
        verify(preparedStatement).close();
        verifyNoMoreInteractions(databaseConnection, connection, preparedStatement);


    }
}
