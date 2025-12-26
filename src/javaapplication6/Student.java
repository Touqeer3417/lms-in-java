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
public class Student extends Person {
    
    private ArrayList<Course> enrolledCourses = new ArrayList<>();
    private ArrayList<Grade> grades = new ArrayList<>();
    
    Student(String name, int id, String email) {
        super(name, id, email);
    }
    void enrollCourse(Course c) {
        if (!enrolledCourses.contains(c)) {
            enrolledCourses.add(c);
            c.addStudent(this);
        }
    }
    ArrayList<Course> getCourses() { return enrolledCourses; }
    ArrayList<Grade> getGrades() { return grades; }
    void addGrade(Grade g) { grades.add(g); }
    @Override
    void displayDetails() {}
}
