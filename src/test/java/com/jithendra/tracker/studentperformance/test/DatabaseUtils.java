package com.jithendra.tracker.studentperformance.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    // Method to return a Connection object
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Optional: Method to create mock table + data for testing
    public static void initializeTestDatabase(Connection conn) throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS students (" +
                "studentid INT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "course VARCHAR(100), " +
                "marks DOUBLE)";

        String insertData = "INSERT INTO students (studentid, name, course, marks) VALUES " +
                "(1, 'Alice', 'Math', 85.5), " +
                "(2, 'Bob', 'Science', 78.0), " +
                "(3, 'Charlie', 'Math', 92.0)";

        conn.createStatement().execute(createTable);
        conn.createStatement().execute(insertData);
    }
}
