package com.jithendra.tracker.studentperformance.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jithendra.tracker.studentperformance.dao.StudentDAO;
import com.jithendra.tracker.studentperformance.model.Student;
import org.junit.jupiter.api.Test;
import java.util.List;

public class StudentDataRetrievalTest {

    @Test
    public void testGetAllStudents() {
        // Mocking the DAO layer
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAllStudents();
        assertFalse(students.isEmpty(), "Student list should not be empty");
        assertTrue(students.size() > 0, "Student list should contain data");
    }
}
