/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

import java.util.ArrayList;

/**
 *
 * @author touqe
 */
class Course {
    private String courseName;
    private String courseCode;
    private Teacher teacher;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Assignment> assignments = new ArrayList<>(); 
    Course(String n, String c, Teacher t) {
        this.courseName = n;
        this.courseCode = c;
        this.teacher = t;
    }
    void addStudent(Student s) { if (!students.contains(s)) students.add(s); }
    void addAssignment(Assignment a) { if (!assignments.contains(a)) assignments.add(a); }
    String courseName() { return courseName; }
    String courseCode() { return courseCode; }
    Teacher teacher() { return teacher; }
    ArrayList<Student> getStudents() { return students; }
    ArrayList<Assignment> getAssignments() { return assignments; }
}
