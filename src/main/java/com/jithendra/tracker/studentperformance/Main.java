package com.jithendra.tracker.studentperformance;

import com.jithendra.tracker.studentperformance.model.Student;
import com.jithendra.tracker.studentperformance.service.StudentService;
import com.jithendra.tracker.studentperformance.util.ExcelExporter;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        ExcelExporter exporter = new ExcelExporter();

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Student Performance Tracker ===");

        List<Student> allStudents = service.getAllStudents();
        System.out.println("\nTotal Students: " + allStudents.size());

        // 1. Display all students
        System.out.println("\nAll Students:");
        allStudents.forEach(System.out::println);

        // 2. Students with marks > 75
        System.out.println("\nStudents with marks > 75:");
        List<Student> topPerformers = service.getStudentsAboveMarks(75);
        topPerformers.forEach(System.out::println);

        // 3. Sorted by marks
        System.out.println("\nStudents sorted by marks:");
        List<Student> sorted = service.getStudentsSortedByMarks();
        sorted.forEach(System.out::println);

        // 4. Average marks per course
        System.out.println("\nAverage Marks by Course:");
        Map<String, Double> avgByCourse = service.calculateAverageMarksByCourse();
        avgByCourse.forEach((course, avg) -> System.out.println(course + ": " + avg));

        // 5. Export to Excel (all students in one sheet)
        exporter.exportToExcel(allStudents);

        // 6. Optional: Export grouped by course
        System.out.println("\nDo you want to export each course to a separate Excel sheet? (yes/no): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            Map<String, List<Student>> grouped = allStudents.stream()
                    .collect(Collectors.groupingBy(Student::getCourseName));
            exporter.exportGroupedByCourse(grouped);
        }

        System.out.println("\n=== Done ===");
    }
}
