package io.aiven.app.health.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

/**
 * Represents a database connection
 */
public class DatabaseConnection {
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USERNAME = "database.username";
    private static final String DATABASE_PASSWORD = "database.password";

    /**
     * Create a new JDBC connection based on database url.
     *
     * @return : Database Connection
     * @throws SQLException : throws exception when connection creating fails
     */
    public Connection getConnection() throws SQLException {
        try {
            System.out.println("get connection");
            return DriverManager.getConnection(getProperty(DATABASE_URL),
                    getProperty(DATABASE_USERNAME),
                    getProperty(DATABASE_PASSWORD));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
