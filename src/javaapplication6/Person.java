/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author touqe
 */
public abstract class Person {
    private String name;
    private int id;
    private String email;
    
    Person(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }
    
    abstract void displayDetails();
    String getname() { return name; }
    int getid() { return id; }
    String getemail() { return email; }
}