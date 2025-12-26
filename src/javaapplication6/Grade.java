/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author touqe
 */
enum GradeLetter { A, B, C, F }
public class Grade {
    private Student student;
    private Assignment assignment;
    private Course course;
    private double marks;
    private GradeLetter gradeLetter;
    Grade(Student s, Assignment a, Course c, double marks) {
        this.student = s;
        this.assignment = a;
        this.course = c;
        this.marks = marks;
    }
    void calculateGrade() {
        if (marks >= 85) gradeLetter = GradeLetter.A;
        else if (marks >= 70) gradeLetter = GradeLetter.B;
        else if (marks >= 50) gradeLetter = GradeLetter.C;
        else gradeLetter = GradeLetter.F;
    }
    Course course() { return course; }
    Assignment assignment() { return assignment; }
    double marks() { return marks; }
    GradeLetter gradeLetter() { return gradeLetter; }
}
