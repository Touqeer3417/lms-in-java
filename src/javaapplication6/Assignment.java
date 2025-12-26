/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author touqe
 */
public class Assignment {
    private String title;
    private String description;
    private Course course;
    
    Assignment(String title, String description) {
        this.title = title;
        this.description = description;
    }
    void setCourse(Course c) { course = c; }
    Course course() { return course; }
    String title() { return title; }
    String description() { return description; }
}