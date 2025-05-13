package com.jithendra.tracker.studentperformance.util;

import com.jithendra.tracker.studentperformance.model.Student;
import com.jithendra.tracker.studentperformance.config.ConfigLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelExporter {


    private static final Logger log = LogManager.getLogger(ExcelExporter.class);

    // Export a list of students into a single Excel sheet
    public static void exportToExcel(List<Student> students) {
        String outputPath = ConfigLoader.get("output.excel.path");
        log.info("Starting Excel export for all students. Output path: {}", outputPath);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Student Performance");
            writeHeader(sheet);
            writeData(sheet, students);

            try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
                workbook.write(outputStream);
            }

            System.out.println("Excel file generated successfully at: " + outputPath);
            log.info("Excel file generated successfully at: {}", outputPath);
        } catch (IOException e) {
            log.error("Error while exporting to Excel", e);
        }
    }

    // Optional: export grouped by course into multiple sheets
    public void exportGroupedByCourse(Map<String, List<Student>> courseMap) {
        String outputPath = ConfigLoader.get("output.excel.path");
        log.info("Starting grouped Excel export by course. Output path: {}", outputPath);
        try (Workbook workbook = new XSSFWorkbook()) {
            for (Map.Entry<String, List<Student>> entry : courseMap.entrySet()) {
                Sheet sheet = workbook.createSheet(entry.getKey());
                writeHeader(sheet);
                writeData(sheet, entry.getValue());
            }

            try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
                workbook.write(outputStream);
            }

            System.out.println("Grouped Excel file generated at: " + outputPath);
            log.info("Grouped Excel file generated successfully at: {}", outputPath);
        } catch (IOException e) {
            log.error("Error while exporting grouped data to Excel", e);
        }
    }

    // Helper method to write header
    private static void writeHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] headers = { "Student ID", "Name", "Course", "Marks" };
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle style = sheet.getWorkbook().createCellStyle();
            Font font = sheet.getWorkbook().createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }
        log.debug("Header written to Excel sheet.");
    }

    // Helper method to write student data
    private static void writeData(Sheet sheet, List<Student> students) {
        int rowNum = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getStudentId());
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getCourseName());
            row.createCell(3).setCellValue(student.getMarks());
        }

        // Auto-size columns
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        log.debug("Data written to Excel sheet for {} students.", students.size());
    }
}
