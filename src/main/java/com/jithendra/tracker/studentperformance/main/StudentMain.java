package com.jithendra.tracker.studentperformance.main;

import com.jithendra.tracker.studentperformance.config.ConfigLoader;
import com.jithendra.tracker.studentperformance.model.Student;
import com.jithendra.tracker.studentperformance.service.StudentService;
import com.jithendra.tracker.studentperformance.util.ExcelExporter;
import com.jithendra.tracker.studentperformance.util.ExcelReader;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
/**
 * @author Narala Jithendra
 */
public class StudentMain {
    static Supplier<StudentService> studentServiceSupplier = StudentService::new;
    static Supplier<Scanner>  scannerSupplier = () -> new Scanner(System.in);
    static Supplier<ExcelExporter> excelExporterSupplier = ExcelExporter::new;
    static Supplier<List<Student>> studentList = () -> {
        return studentServiceSupplier.get().getAllStudents();
    };

    public static void studentPrint(){
        StudentService service = studentServiceSupplier.get();

        System.out.println("=== Student Performance Tracker ===");

        List<Student> allStudents = studentList.get();
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

    }
    public void exportToExcelStudentprint() {
        ExcelExporter exporter = excelExporterSupplier.get();
        // 5. Export to Excel (all students in one sheet)]
        List<Student> allStudents = studentList.get();
        exporter.exportToExcel(allStudents);
        System.out.println("\n=== Done ===");
    }

    public void exportToExcelCoursePrint() throws SQLException, ClassNotFoundException {
        ExcelExporter exporter = excelExporterSupplier.get();
        Scanner scanner = scannerSupplier.get();
        List<Student> allStudents = studentList.get();
        exporter.exportToExcel(allStudents);

        System.out.println("\nDo you want to export each course to a separate Excel sheet? (yes/no): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            Map<String, List<Student>> grouped = studentList.get().stream()
                    .collect(Collectors.groupingBy(Student::getCourseName));
            exporter.exportGroupedByCourse(grouped);
        }

        System.out.println("\n=== Done ===");
    }


    public void exportCombinedToExcelPrint() throws SQLException, ClassNotFoundException {
        ExcelExporter exporter = excelExporterSupplier.get();
        Scanner scanner = scannerSupplier.get();
        System.out.println("\nDo you want to export each course to a separate Excel sheet? (yes/no): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            Map<String, List<Student>> grouped = studentList.get().stream()
                    .collect(Collectors.groupingBy(Student::getCourseName));
            exporter.exportGroupedByCourse(grouped);
            exporter.exportCombined(studentList.get(), grouped);
        }

        System.out.println("\n=== Done ===");
    }

    public void readStudentsFromExcel() throws SQLException, ClassNotFoundException {
        ExcelReader reader = new ExcelReader();
        String path = ConfigLoader.get("output.excel.path.student");
        List<Student> students = reader.readStudentsFromExcel(path, "Student Performance");
        students.forEach(System.out::println);
    }
}
