package com.jithendra.tracker.studentperformance.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jithendra.tracker.studentperformance.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentGroupingTest {
    private List<Student> getSampleStudentList() {
        return Arrays.asList(
                new Student(1, "Alice", "Math", 85.5),
                new Student(2, "Bob", "Science", 78.0),
                new Student(3, "Charlie", "Math", 92.0)
        );
    }
    @Test
    public void testGroupStudentsByCourse() {
        List<Student> studentList = getSampleStudentList();
        Map<String, List<Student>> studentsByCourse = studentList.stream()
            .collect(Collectors.groupingBy(Student::getCourseName));

        // Verify the grouping
        assertEquals(2, studentsByCourse.size(), "There should be 2 courses (e.g., Math and Science)");
    }
}
