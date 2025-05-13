package com.jithendra.tracker.studentperformance.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Student {

    private static final Logger log = LogManager.getLogger(Student.class);
    private int studentId;
    private String studentName;
    private String courseName;
    private double marks;

    public Student() {
        log.debug("Created empty Student object.");
    }

    public Student(int studentId, String studentName, String courseName, double marks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.marks = marks;
        log.debug("Created Student: {}", this);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        log.debug("Setting studentId: {} -> {}", this.studentId, studentId);
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        log.debug("Setting studentName: {} -> {}", this.studentName, studentName);
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        log.debug("Setting courseName: {} -> {}", this.courseName, courseName);
        this.courseName = courseName;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        log.debug("Setting marks for studentId {}: {} -> {}", studentId, this.marks, marks);
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", marks=" + marks +
                '}';
    }
}
