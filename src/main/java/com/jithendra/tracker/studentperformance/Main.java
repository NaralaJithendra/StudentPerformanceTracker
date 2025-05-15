package com.jithendra.tracker.studentperformance;

import com.jithendra.tracker.studentperformance.main.StudentMain;

import java.sql.SQLException;
/**
 * @author Narala Jithendra
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        StudentMain studentMain = new StudentMain();
        studentMain.studentPrint();
        studentMain.exportToExcelStudentprint();
        studentMain.exportToExcelCoursePrint();
        studentMain.exportCombinedToExcelPrint();
        studentMain.readStudentsFromExcel();
    }
}
