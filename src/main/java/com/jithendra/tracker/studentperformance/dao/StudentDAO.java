package com.jithendra.tracker.studentperformance.dao;


import com.jithendra.tracker.studentperformance.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
public class StudentDAO {


    private static final Logger log = LogManager.getLogger(StudentDAO.class);

    private static Connection getConnection() throws SQLException {
        log.info("Connecting to the database...");
        return ConnectionFactory.getConnection();
    }

    // Method to fetch all students from the database
    public List<Student> getAllStudents() {
        log.info("Fetching all students...");
        List<Student> students = new ArrayList<>();
        String query = "SELECT studentId, name, course, marks FROM students";

        try (Connection connection = getConnection();
             Statement statement = (connection).createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String name = resultSet.getString("name");
                String course = resultSet.getString("course");
                double marks = resultSet.getDouble("marks");

                Student student = new Student(studentId, name, course, marks);
                students.add(student);
            }
            log.info("Total students fetched: {}", students.size());
        } catch (SQLException e) {
            log.error("Error fetching students: {}", e.getMessage(), e);
            e.printStackTrace();
        }
        return students;
    }

    // Method to fetch students by course
    public List<Student> getStudentsByCourse(String course) throws SQLException {
        log.info("Fetching students enrolled in course: {}", course);
        List<Student> students = new ArrayList<>();
        String query = "SELECT studentId, name, course, marks FROM students WHERE course = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, course);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("studentId");
                    String name = resultSet.getString("name");
                    String courseName = resultSet.getString("course");
                    double marks = resultSet.getDouble("marks");

                    Student student = new Student(studentId, name, courseName, marks);
                    students.add(student);
                }
            }
            log.info("Students fetched for course '{}': {}", course, students.size());

        } catch (SQLException e) {
            log.error("Error fetching students by course '{}': {}", course, e.getMessage(), e);
            throw new SQLException("SQL Exception while fetching students by course", e);
        }

        return students;
    }

    // Method to insert a new student (optional)
    public void addStudent(Student student) throws SQLException {
        log.info("Adding new student: {}", student);
        String sql = "INSERT INTO students (studentId, name, course, marks) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setString(3, student.getCourseName());
            preparedStatement.setDouble(4, student.getMarks());

            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Insert successful. Rows affected: {}", rowsAffected);
        }  catch (SQLException e) {
            log.error("Error inserting student: {}", e.getMessage(), e);
            throw new SQLException("SQL Exception while inserting student", e);
        }
    }
}

