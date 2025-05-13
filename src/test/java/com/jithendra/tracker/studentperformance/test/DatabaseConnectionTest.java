package com.jithendra.tracker.studentperformance.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    @Test
    public void testDatabaseConnection() throws SQLException {
        // Assuming we have a method to get a connection
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull(connection, "Database connection should be established");
    }
}
