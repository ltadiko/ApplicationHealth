package io.aiven.app.health.repository;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.models.HealthStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConsumerApplicationRepository {
    private static final String INSERT_WEBSITE_HEALTH_LOG = "INSERT INTO WEBSITE_HEALTH_LOGS(id,website_id,status,created_on)  VALUES(DEFAULT,?,?,?)";
    private DatabaseConnection databaseConnection;

    public ConsumerApplicationRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void addWebsiteHealthStatus(final int websiteId, final HealthStatus healthStatus) throws SQLException {
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_WEBSITE_HEALTH_LOG)) {
            preparedStatement.setInt(1, websiteId);
            preparedStatement.setString(2, healthStatus.toString());
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        }
    }

}
