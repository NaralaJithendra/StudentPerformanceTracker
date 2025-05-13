package com.jithendra.tracker.studentperformance.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jithendra.tracker.studentperformance.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageMarksTest {

    private List<Student> getSampleStudentList() {
        return Arrays.asList(
                new Student(1, "Alice", "Math", 85.5),
                new Student(2, "Bob", "Science", 78.0),
                new Student(3, "Charlie", "Math", 92.0)
        );
    }
    @Test
    public void testCalculateAverageMarksPerCourse() {
        List<Student> studentList = getSampleStudentList();
        Map<String, Double> averageMarksPerCourse = studentList.stream()
            .collect(Collectors.groupingBy(Student::getCourseName,
                Collectors.averagingDouble(Student::getMarks)));

        assertEquals(88.75, averageMarksPerCourse.get("Math"), "Average marks for Math should be 80.75");
    }
}
