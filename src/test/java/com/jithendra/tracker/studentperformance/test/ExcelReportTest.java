package com.jithendra.tracker.studentperformance.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jithendra.tracker.studentperformance.model.Student;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.jithendra.tracker.studentperformance.util.ExcelExporter;
public class ExcelReportTest {

    private List<Student> getSampleStudentList() {
        return Arrays.asList(
                new Student(1, "Alice", "Math", 85.5),
                new Student(2, "Bob", "Science", 78.0),
                new Student(3, "Charlie", "Math", 92.0)
        );
    }

    @Test
    public void testGenerateExcelReport() {
        // Assuming ExcelExporter is the class to export reports
        ExcelExporter.exportToExcel(getSampleStudentList());
        
        // Verify if the file is created
        File file = new File("StudentPerformanceReport.xlsx");
        assertTrue(file.exists(), "Excel file should be created");
    }
}
