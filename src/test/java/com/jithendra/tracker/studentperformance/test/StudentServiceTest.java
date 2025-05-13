package com.jithendra.tracker.studentperformance.test;

import com.jithendra.tracker.studentperformance.dao.StudentDAO;
import com.jithendra.tracker.studentperformance.model.Student;
import com.jithendra.tracker.studentperformance.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentDAO mockStudentDAO;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        mockStudentDAO = mock(StudentDAO.class);
        studentService = new StudentService(mockStudentDAO); // Constructor injection
    }

    @Test
    void testGetStudentById_success() throws SQLException, ClassNotFoundException {
        Student student = new Student(1, "Alice", "Math", 85.0);
        when(mockStudentDAO.getStudent(1)).thenReturn(student);

        Student result = studentService.getStudentById(1);
        assertNotNull(result);
        assertEquals("Alice", result.getStudentName());
        verify(mockStudentDAO, times(1)).getStudent(1);
    }

    @Test
    void testUpdateStudent_success() throws SQLException, ClassNotFoundException {
        Student updated = new Student(2, "Bob", "Science", 90.0);
        when(mockStudentDAO.updateStudent(updated)).thenReturn(updated);

        Student result = studentService.updateStudent(updated);
        assertNotNull(result);
        assertEquals(90.0, result.getMarks());
        verify(mockStudentDAO).updateStudent(updated);
    }

    @Test
    void testDeleteStudent_success() throws SQLException, ClassNotFoundException {
        Student toDelete = new Student(3, "Carol", "History", 72.5);
        when(mockStudentDAO.deleteStudent(3)).thenReturn(toDelete);

        assertDoesNotThrow(() -> studentService.deleteStudent(3));
        verify(mockStudentDAO).deleteStudent(3);
    }

    @Test
    void testDeleteStudent_notFound() throws SQLException, ClassNotFoundException {
        when(mockStudentDAO.deleteStudent(99)).thenReturn(null);

        assertDoesNotThrow(() -> studentService.deleteStudent(99));
        verify(mockStudentDAO).deleteStudent(99);
    }
}
