package io.aiven.app.health.repository;

import io.aiven.app.health.connection.DatabaseConnection;
import io.aiven.app.health.models.Website;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProducerApplicationRepository {
    private static final String ALL_WEBSITES_SQL = "SELECT * FROM WEBSITES";
    private DatabaseConnection databaseConnection;

    public ProducerApplicationRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    public List<Website> getWebsites() throws SQLException, MalformedURLException {
        List<Website> websiteList = new ArrayList<>();
        try (Connection connect = databaseConnection.getConnection();
             Statement stmt = connect.createStatement(); ResultSet rs = stmt.executeQuery(ALL_WEBSITES_SQL)) {
            while (rs.next()) {
                websiteList.add(transform(rs));
            }
        }
        return websiteList;
    }

    private Website transform(ResultSet rs) throws SQLException, MalformedURLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        URL url = new URL(rs.getString("url"));// rs.getURL("name");
        return new Website(id, url, name);
    }
}
