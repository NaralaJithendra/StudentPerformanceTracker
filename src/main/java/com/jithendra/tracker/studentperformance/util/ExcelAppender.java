package com.jithendra.tracker.studentperformance.util;

import com.jithendra.tracker.studentperformance.config.ConfigLoader;
import com.jithendra.tracker.studentperformance.model.Student;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.List;
/**
 * @author Narala Jithendra
 */
public class ExcelAppender {

    public void appendSheet(String sheetName, List<Student> students) {
        String outputPath = ConfigLoader.get("output.excel.path");

        try (
            FileInputStream fis = new FileInputStream(outputPath);
            Workbook workbook = new XSSFWorkbook(fis)
        ) {
            // Create new sheet and write data
            Sheet sheet = workbook.createSheet(sheetName);
            writeHeader(sheet);
            writeData(sheet, students);

            // Write back to the same file
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
            }

            System.out.println("Sheet '" + sheetName + "' appended to Excel file: " + outputPath);
        } catch (IOException e) {
            System.out.println("Error appending sheet: " + e);
        }
    }

    private void writeHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Student ID", "Name", "Course Name", "Marks"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle style = sheet.getWorkbook().createCellStyle();
            Font font = sheet.getWorkbook().createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }
    }

    private void writeData(Sheet sheet, List<Student> students) {
        int rowNum = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getStudentId());
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getCourseName());
            row.createCell(3).setCellValue(student.getMarks());
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
