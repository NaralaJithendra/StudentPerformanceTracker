package com.jithendra.tracker.studentperformance.service;

import com.jithendra.tracker.studentperformance.dao.StudentDAO;
import com.jithendra.tracker.studentperformance.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentService {
    private static final Logger log = LogManager.getLogger(StudentService.class);
    private final StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    // Method to get all students
    public List<Student> getAllStudents() {
        log.info("Fetching all students...");
        List<Student> students = studentDAO.getAllStudents();
        log.info("Total students fetched: {}", students.size());
        return students;
    }

    // Method to get students by course
    public List<Student> getStudentsByCourse(String course) throws SQLException {
        log.info("Fetching students for course: {}", course);
        List<Student> students = studentDAO.getStudentsByCourse(course);
        log.info("Students fetched for course '{}': {}", course, students.size());
        return students;
    }

    // Method to filter students based on marks above a certain threshold
    public List<Student> getStudentsAboveMarks(double thresholdMarks) {
        log.info("Filtering students with marks above {}", thresholdMarks);
        List<Student> filtered = studentDAO.getAllStudents()
                .stream()
                .filter(student -> student.getMarks() > thresholdMarks)
                .collect(Collectors.toList());
        log.info("Students found above threshold {}: {}", thresholdMarks, filtered.size());
        return filtered;
    }

    // Method to calculate average marks per course
    public Map<String, Double> calculateAverageMarksByCourse() {
        log.info("Calculating average marks by course...");
        Map<String, Double> averages = studentDAO.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(Student::getCourseName,
                        Collectors.averagingDouble(Student::getMarks)));
        log.info("Average marks by course calculated.");
        return averages;
    }

    public HashMap<String, Double> calculateAverageMarksByStudent() {
        log.info("Calculating average marks by course (manual method)...");
        HashMap<String, List<Double>> marksMap = new HashMap<>();

        // Group marks by course
        studentDAO.getAllStudents().forEach(student -> {
            marksMap.computeIfAbsent(student.getCourseName(), k -> new ArrayList<>())
                    .add(student.getMarks());
        });

        // Compute average
        HashMap<String, Double> averageMarksByCourse = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : marksMap.entrySet()) {
            double avg = entry.getValue().stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);
            averageMarksByCourse.put(entry.getKey(), avg);
            log.debug("Course: {}, Average Marks: {}", entry.getKey(), avg);
        }

        log.info("Manual average marks by course calculation completed.");
        return averageMarksByCourse;
    }

    // Method to sort students by marks in descending order
    public List<Student> getStudentsSortedByMarks() {
        log.info("Sorting students by marks in descending order...");

        List<Student> sortedStudents = studentDAO.getAllStudents()
                .stream()
                .sorted((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()))
                .collect(Collectors.toList());

        log.info("Sorted {} students by marks.", sortedStudents.size());
        log.debug("Top student: {}", sortedStudents.isEmpty() ? "None" : sortedStudents.get(0));
        return sortedStudents;
    }

    public Student getStudentById(int studentId) throws SQLException, ClassNotFoundException {
        log.info("Fetching student with ID: {}", studentId);
        return studentDAO.getStudent(studentId);
    }

    public Student updateStudent(Student student) throws SQLException, ClassNotFoundException {
        log.info("Updating student with ID: {}", student.getStudentId());
        return studentDAO.updateStudent(student);
    }

    public void deleteStudent(int studentId) throws SQLException, ClassNotFoundException {
        log.info("Deleting student with ID: {}", studentId);
        Student deletedStudent = studentDAO.deleteStudent(studentId);

        if (deletedStudent != null) {
            log.info("Deleted student: {}", deletedStudent);
        } else {
            log.warn("No student found with ID: {}", studentId);
        }
    }
}
