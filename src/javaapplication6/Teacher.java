package javaapplication6;

public class Teacher extends Person implements Evaluation {

    Teacher(String name, int id, String email) {

        super(name, id, email);
    }

    Course createCourse(String name, String courseCode) {
        return new Course(name, courseCode, this);
    }

    Assignment createAssignment(String t, String d, Course c) {
        Assignment a = new Assignment(t, d);
        a.setCourse(c);
        c.addAssignment(a);
        return a;
    }

    @Override
    public void evaluate(Student s, Assignment a, double m) {
        Grade g = new Grade(s, a, a.course(), m);
        g.calculateGrade();
        s.addGrade(g);
        FileHandling.writeReport(
                "Student: " + s.getname()
                + " | ID: " + s.getid()
                + " | Course: " + a.course().courseName()
                + " | Assignment: " + a.title()
                + " | Marks: " + m
                + " | Grade: " + g.gradeLetter()
        );
    }

    @Override
    void displayDetails() {
        System.out.println("Name is "+getname());
        System.out.println("id is "+getid());
        System.out.println("email is "+getemail());
    }
}
