package com.jithendra.tracker.studentperformance.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jithendra.tracker.studentperformance.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StudentSortingTest {
    private List<Student> getSampleStudentList() {
        return Arrays.asList(
                new Student(1, "Alice", "Math", 85.5),
                new Student(2, "Bob", "Science", 78.0),
                new Student(3, "Charlie", "Math", 92.0)
        );
    }
    @Test
    public void testSortStudentsByMarks() {
        List<Student> studentList = getSampleStudentList();
        List<Student> sortedStudents = studentList.stream()
            .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
            .collect(Collectors.toList());

        // Verify that the list is sorted
        assertTrue(sortedStudents.get(0).getMarks() >= sortedStudents.get(1).getMarks(), "List should be sorted by marks");
    }
}
