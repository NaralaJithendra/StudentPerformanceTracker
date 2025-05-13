package com.jithendra.tracker.studentperformance.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jithendra.tracker.studentperformance.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentFilterTest {
    private List<Student> getSampleStudentList() {
        return Arrays.asList(
                new Student(1, "Alice", "Math", 85.5),
                new Student(2, "Bob", "Science", 78.0),
                new Student(3, "Charlie", "Math", 92.0)
        );
    }
    @Test
    public void testFilterStudentsByMarks() {
        // Assuming we have a list of students to filter
        List<Student> studentList = getSampleStudentList();
        List<Student> filteredStudents = studentList.stream()
            .filter(student -> student.getMarks() > 75)
            .collect(Collectors.toList());
        assertEquals(3, filteredStudents.size(), "There should be 3 students with marks above 75");
    }
}
