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
     * @throws ClassNotFoundException : throws when postgresql driver class is not found
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getProperty(DATABASE_URL),
                getProperty(DATABASE_USERNAME),
                getProperty(DATABASE_PASSWORD));
    }
}
