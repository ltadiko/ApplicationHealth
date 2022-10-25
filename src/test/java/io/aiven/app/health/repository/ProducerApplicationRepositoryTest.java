package io.aiven.app.health.repository;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.models.Website;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class ProducerApplicationRepositoryTest {

    private DatabaseConnection databaseConnection = mock(DatabaseConnection.class);

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    ResultSet resultSet = mock(ResultSet.class);
    ProducerApplicationRepository underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ProducerApplicationRepository(databaseConnection);


    }

    @Test
    @DisplayName("should return websites from database when connection and statements are correct")
    void testGetWebsite() throws SQLException, MalformedURLException {
        //given
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery("SELECT * FROM WEBSITES")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true)
                .thenReturn(false);
        when(resultSet.getString("url")).thenReturn("https://google.com");

        //when
        List<Website> websiteList = underTest.getWebsites();

        //verify
        verify(databaseConnection).getConnection();
        verify(connection).createStatement();
        verify(preparedStatement).executeQuery("SELECT * FROM WEBSITES");
        verify(resultSet, times(2)).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getString("url");
        verify(resultSet).getString("name");
        verify(connection).close();
        verify(preparedStatement).close();
        verifyNoMoreInteractions(databaseConnection, connection, preparedStatement);
    }

    @Test
    @DisplayName("should throw MalformedURLException when url is invalid")
    void testGetWebsiteInvalidURL() throws SQLException, MalformedURLException {
        //given
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery("SELECT * FROM WEBSITES")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true)
                .thenReturn(false);
        when(resultSet.getString("url")).thenReturn("test.com");

        //when
        assertThrows(MalformedURLException.class, () -> underTest.getWebsites());

        //verify
        verify(databaseConnection).getConnection();
        verify(connection).createStatement();
        verify(preparedStatement).executeQuery("SELECT * FROM WEBSITES");
        verify(resultSet).next();
        verify(resultSet).getInt("id");
        verify(resultSet).getString("url");
        verify(resultSet).getString("name");
        verify(connection).close();
        verify(preparedStatement).close();
        verifyNoMoreInteractions(databaseConnection, connection, preparedStatement);
    }
}
