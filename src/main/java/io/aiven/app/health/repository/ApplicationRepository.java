package io.aiven.app.health.repository;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.models.Website;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ApplicationRepository {
    private static final String ALL_WEBSITES_SQL = "SELECT * FROM WEBSITES";
    private static final String INSERT_WEBSITE_HEALTH_LOG = "INSERT INTO WEBSITE_HEALTH_LOGS(id,website_id,status,created_on) " + "VALUES(DEFAULT,?,?,?)";
    private DatabaseConnection databaseConnection;

    public ApplicationRepository() {
        this.databaseConnection = new DatabaseConnection();
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

    public List<Website> getWebsites() throws SQLException, MalformedURLException {
        List<Website> websiteList = new ArrayList<>();
        try (Connection connect = databaseConnection.getConnection(); Statement stmt = connect.createStatement(); ResultSet rs = stmt.executeQuery(ALL_WEBSITES_SQL)) {
            while (rs.next()) {
                websiteList.add(transform(rs));
            }
        }
        return websiteList;
    }

    private Website transform(ResultSet rs) throws SQLException, MalformedURLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        URL url = new URL(rs.getString("url"));
        return new Website(id, url, name);
    }
}
