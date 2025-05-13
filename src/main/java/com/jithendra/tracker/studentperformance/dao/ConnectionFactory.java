package com.jithendra.tracker.studentperformance.dao;

import com.jithendra.tracker.studentperformance.config.ConfigLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Narala Jithendra
 */
public class ConnectionFactory {

    private static final Logger log = LogManager.getLogger(ConnectionFactory.class);
    // Static method to get a database connection
    public static Connection getConnection() throws SQLException {
        try {
            // Load H2 JDBC driver
            Class.forName("org.h2.Driver");
            log.info("Connecting to database...");
            log.info("H2 JDBC Driver loaded successfully.");
            // Read connection properties from ConfigLoader
            String dbUrl = ConfigLoader.get("db.url");
            String username = ConfigLoader.get("db.username");
            String password = ConfigLoader.get("db.password");
            log.info("Database connection properties loaded.");
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            log.info("Database connection established successfully.");
            // Return the connection
            return connection;
        } catch (ClassNotFoundException e) {
            log.error("H2 JDBC Driver not found.", e);
            throw new SQLException("H2 JDBC Driver not found.", e);
        } catch (SQLException e) {
            log.error("Error while establishing database connection.", e);
            throw new SQLException("Error while establishing database connection.", e);
        }
    }
}
