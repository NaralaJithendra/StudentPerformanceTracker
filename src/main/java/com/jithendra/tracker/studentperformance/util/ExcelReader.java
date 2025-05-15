package com.jithendra.tracker.studentperformance.util;

import com.jithendra.tracker.studentperformance.model.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Narala Jithendra
 */
public class ExcelReader {

    public List<Student> readStudentsFromExcel(String filePath, String sheetName) {
        List<Student> studentList = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return studentList;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Student student = new Student();
                student.setStudentId((int) row.getCell(0).getNumericCellValue());
                student.setStudentName(row.getCell(1).getStringCellValue());
                student.setCourseName(row.getCell(2).getStringCellValue());
                student.setMarks((int) row.getCell(3).getNumericCellValue());

                studentList.add(student);
            }

        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e);
        }

        return studentList;
    }
}