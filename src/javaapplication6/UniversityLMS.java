package javaapplication6;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class UniversityLMS extends Frame implements ActionListener {

    String role;
    TextArea output = new TextArea();

    Button addStudentBtn, createCourseBtn, createAssignmentBtn, evaluateBtn, viewReportsBtn;

    Button registerBtn, enrollBtn, viewCoursesBtn, viewAssignmentsBtn, viewResultBtn;
    Button logoutBtn;

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Assignment> assignments = new ArrayList<>();
    static Teacher teacher = new Teacher("Prof Demo", 101, "prof@uni.edu");

    Student loggedStudent;

    UniversityLMS(String r) {
        role = r;
        setTitle(role + " Dashboard");
        setSize(920,580);
        setLayout(new BorderLayout());
        setBackground(new Color(34,36,40));

        add(menu(), BorderLayout.WEST);

        output.setFont(new Font("Monospaced", Font.PLAIN, 13));
        output.setBackground(new Color(22,24,28));
        output.setForeground(new Color(230,230,230));
        add(output, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter(){ public void windowClosing(WindowEvent e){ System.exit(0);} });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    Panel menu() {
        Panel p = new Panel(new GridLayout(12,1,8,8));
        p.setPreferredSize(new Dimension(240,0));
        p.setBackground(new Color(40,42,48));

        if (role.equals("TEACHER")) {
            addStudentBtn = new Button("Add Student");
            createCourseBtn = new Button("Create Course");
            createAssignmentBtn = new Button("Create Assignment");
            evaluateBtn = new Button("Evaluate Student");
            viewReportsBtn = new Button("View Reports");

            for (Button b : new Button[]{addStudentBtn, createCourseBtn, createAssignmentBtn, evaluateBtn, viewReportsBtn}) {
                styleMenuButton(b);
                b.addActionListener(this);
                p.add(b);
            }
        } else {
            registerBtn = new Button("Enter My Details");
            enrollBtn = new Button("Enroll New Course");
            viewCoursesBtn = new Button("My Courses");
            viewAssignmentsBtn = new Button("View Assignments");
            viewResultBtn = new Button("View Result");

            for (Button b : new Button[]{registerBtn, enrollBtn, viewCoursesBtn, viewAssignmentsBtn, viewResultBtn}) {
                styleMenuButton(b);
                b.addActionListener(this);
                p.add(b);
            }
        }

        logoutBtn = new Button("Logout");
        styleMenuButton(logoutBtn);
        logoutBtn.setBackground(new Color(210,75,75));
        logoutBtn.addActionListener(this);
        p.add(logoutBtn);

        return p;
    }

    void styleMenuButton(Button b) {
        b.setBackground(new Color(70,130,180));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
    }

    void styleDialogButton(Button b) {
        b.setBackground(new Color(88,160,255));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == addStudentBtn) openAddStudentDialog();
        if (src == createCourseBtn) openCreateCourseDialog();
        if (src == createAssignmentBtn) openCreateAssignmentDialog();
        if (src == evaluateBtn) openEvaluateDialog();
        if (src == viewReportsBtn) output.setText(FileHandling.readReport());

        if (src == registerBtn) openStudentRegister();
        if (src == enrollBtn) openEnrollDialog();
        if (src == viewCoursesBtn) showStudentCourses();
        if (src == viewAssignmentsBtn) showStudentAssignments();
        if (src == viewResultBtn) showStudentResults();

      
        if (src == logoutBtn) {
            dispose();
            new LoginFrame();
        }
    }

    void openAddStudentDialog() {
        Dialog d = new Dialog(this, "Add Student", true);
        d.setLayout(new GridLayout(4,2,8,8));
        d.setBackground(new Color(40,42,46));

        TextField name = new TextField(), id = new TextField(), email = new TextField();
        Button save = new Button("Save");

        Label l1 = new Label("Name"); l1.setForeground(new Color(220,220,220));
        Label l2 = new Label("ID"); l2.setForeground(new Color(220,220,220));
        Label l3 = new Label("Email"); l3.setForeground(new Color(220,220,220));

        d.add(l1); d.add(name);
        d.add(l2); d.add(id);
        d.add(l3); d.add(email);
        d.add(new Label(""));
        styleDialogButton(save);
        d.add(save);

        save.addActionListener(ae -> {
            try {
                Student s = new Student(name.getText().trim(),
                                        Integer.parseInt(id.getText().trim()),
                                        email.getText().trim());
                students.add(s);
                output.setText("Student added: " + s.getname() + " (ID:" + s.getid() + ")");
                d.dispose();
            } catch (Exception ex) {
                output.setText("Invalid input for ID.");
            }
        });

        d.setSize(360,220); d.setLocationRelativeTo(this); d.setVisible(true);
    }

    void openCreateCourseDialog() {
        if (teacher == null) return;

        Dialog d = new Dialog(this, "Create Course", true);
        d.setLayout(new GridLayout(3,2,8,8));
        d.setBackground(new Color(40,42,46));

        TextField cname = new TextField(), ccode = new TextField();
        Button create = new Button("Create");

        Label l1 = new Label("Course Name"); l1.setForeground(new Color(220,220,220));
        Label l2 = new Label("Course Code"); l2.setForeground(new Color(220,220,220));

        d.add(l1); d.add(cname);
        d.add(l2); d.add(ccode);
        d.add(new Label(""));
        styleDialogButton(create);
        d.add(create);

        create.addActionListener(ae -> {
            String cn = cname.getText().trim();
            String cc = ccode.getText().trim();
            if (cn.isEmpty() || cc.isEmpty()) { output.setText("Fill course name & code."); return; }
            Course c = teacher.createCourse(cn, cc);
            courses.add(c);
            output.setText("Course created: " + cn + " ("+cc+")");
            d.dispose();
        });

        d.setSize(380,200); d.setLocationRelativeTo(this); d.setVisible(true);
    }

    void openCreateAssignmentDialog() {
        if (courses.isEmpty()) { output.setText("No courses exist."); return; }

        Dialog d = new Dialog(this, "Create Assignment", true);
        d.setLayout(new GridLayout(4,2,8,8));
        d.setBackground(new Color(40,42,46));

        Choice courseChoice = new Choice();
        for (Course c : courses) courseChoice.add(c.courseName()+" ("+c.courseCode()+")");

        TextField title = new TextField(), desc = new TextField();
        Button create = new Button("Create");

        Label l1 = new Label("Select Course"); l1.setForeground(new Color(220,220,220));
        Label l2 = new Label("Title"); l2.setForeground(new Color(220,220,220));
        Label l3 = new Label("Description"); l3.setForeground(new Color(220,220,220));

        d.add(l1); d.add(courseChoice);
        d.add(l2); d.add(title);
        d.add(l3); d.add(desc);
        d.add(new Label(""));
        styleDialogButton(create);
        d.add(create);

        create.addActionListener(ae -> {
            int idx = courseChoice.getSelectedIndex();
            Course selected = courses.get(idx);
            Assignment a = teacher.createAssignment(title.getText().trim(), desc.getText().trim(), selected);
            assignments.add(a);
            output.setText("Assignment '" + a.title() + "' created for course " + selected.courseName());
            d.dispose();
        });

        d.setSize(420,240); d.setLocationRelativeTo(this); d.setVisible(true);
    }

    void openEvaluateDialog() {
        if (students.isEmpty() || assignments.isEmpty()) { output.setText("No students or assignments available."); return; }

        Dialog d = new Dialog(this, "Evaluate Student", true);
        d.setLayout(new GridLayout(4,2,8,8));
        d.setBackground(new Color(40,42,46));

        Choice studentChoice = new Choice();
        for (Student s : students) studentChoice.add(s.getname()+" (ID:"+s.getid()+")");

        Choice assignChoice = new Choice();
        for (Assignment a : assignments) assignChoice.add(a.title()+" - "+(a.course()!=null ? a.course().courseName() : ""));

        TextField marksFld = new TextField();
        Button eval = new Button("Evaluate");

        Label l1 = new Label("Select Student"); l1.setForeground(new Color(220,220,220));
        Label l2 = new Label("Select Assignment"); l2.setForeground(new Color(220,220,220));
        Label l3 = new Label("Marks"); l3.setForeground(new Color(220,220,220));

        d.add(l1); d.add(studentChoice);
        d.add(l2); d.add(assignChoice);
        d.add(l3); d.add(marksFld);
        d.add(new Label(""));
        styleDialogButton(eval);
        d.add(eval);

        eval.addActionListener(ae -> {
            try {
                Student s = students.get(studentChoice.getSelectedIndex());
                Assignment a = assignments.get(assignChoice.getSelectedIndex());
                double marks = Double.parseDouble(marksFld.getText().trim());
                teacher.evaluate(s, a, marks);
                output.setText("Evaluated " + s.getname() + " | " + a.title() + " = " + marks);
                d.dispose();
            } catch (Exception ex) {
                output.setText("Invalid input for marks.");
            }
        });

        d.setSize(480,260); d.setLocationRelativeTo(this); d.setVisible(true);
    }

    void openStudentRegister() {
        Dialog d = new Dialog(this, "Student Registration", true);
        d.setLayout(new GridLayout(4,2,8,8));
        d.setBackground(new Color(40,42,46));

        TextField name = new TextField(), id = new TextField(), email = new TextField();
        Button save = new Button("Save");

        Label l1 = new Label("Name"); l1.setForeground(new Color(220,220,220));
        Label l2 = new Label("ID"); l2.setForeground(new Color(220,220,220));
        Label l3 = new Label("Email"); l3.setForeground(new Color(220,220,220));

        d.add(l1); d.add(name);
        d.add(l2); d.add(id);
        d.add(l3); d.add(email);
        d.add(new Label(""));
        styleDialogButton(save);
        d.add(save);

        save.addActionListener(ae -> {
            try {
                int sid = Integer.parseInt(id.getText().trim());
                String sname = name.getText().trim();
                String semail = email.getText().trim();

                Student s = null;
                for (Student st : students) {
                    if (st.getid() == sid) {
                        s = st;
                        break;
                    }
                }
                if (s == null) {
                    s = new Student(sname, sid, semail);
                    students.add(s);
                }

                loggedStudent = s;
                output.setText("Welcome, " + s.getname() + " (ID:" + s.getid() + ")");
                d.dispose();
            } catch (Exception ex) {
                output.setText("Invalid ID. Please enter numeric ID.");
            }
        });

        d.setSize(380,220); d.setLocationRelativeTo(this); d.setVisible(true);
    }

    void openEnrollDialog() {
        if (loggedStudent == null) { output.setText("Register first."); return; }
        if (courses.isEmpty()) { output.setText("No courses available to enroll."); return; }

        Dialog d = new Dialog(this, "Enroll Course", true);
        d.setLayout(new GridLayout(2,2,8,8));
        d.setBackground(new Color(40,42,46));

        Choice courseChoice = new Choice();
        for (Course c : courses) courseChoice.add(c.courseName()+" ("+c.courseCode()+")");

        Button enroll = new Button("Enroll");
        Label l1 = new Label("Select Course"); l1.setForeground(new Color(220,220,220));

        d.add(l1); d.add(courseChoice);
        d.add(new Label(""));
        styleDialogButton(enroll);
        d.add(enroll);

        enroll.addActionListener(ae -> {
            Course c = courses.get(courseChoice.getSelectedIndex());
            loggedStudent.enrollCourse(c);
            output.setText("Enrolled in: " + c.courseName());
            d.dispose();
        });

        d.setSize(380,160); d.setLocationRelativeTo(this); d.setVisible(true);
    }

    void showStudentCourses() {
        if (loggedStudent == null) { output.setText("Register first."); return; }
        StringBuilder sb = new StringBuilder();
        sb.append("My Courses:\n");
        for (Course c : loggedStudent.getCourses()) sb.append(" - ").append(c.courseName()).append("\n");
        output.setText(sb.toString());
    }

    void showStudentAssignments() {
        if (loggedStudent == null) { output.setText("Register first."); return; }
        StringBuilder sb = new StringBuilder();
        sb.append("Assignments for your courses:\n");
        for (Course c : loggedStudent.getCourses()) {
            for (Assignment a : c.getAssignments()) {
                sb.append(" - ").append(a.title()).append(" (Course: ").append(c.courseName()).append(")\n");
            }
        }
        output.setText(sb.toString());
    }

    void showStudentResults() {
        if (loggedStudent == null) { output.setText("Register first."); return; }
        StringBuilder sb = new StringBuilder();
        sb.append("My Results:\n");
        if (loggedStudent.getGrades().isEmpty()) {
            sb.append(" No grades yet.\n");
        } else {
            for (Grade g : loggedStudent.getGrades()) {
                sb.append(" - Course: ").append(g.course().courseName())
                  .append(" | Assignment: ").append(g.assignment()!=null ? g.assignment().title() : "[unknown]")
                  .append(" | Marks: ").append(g.marks())
                  .append(" | Grade: ").append(g.gradeLetter()!=null ? g.gradeLetter().name() : "N/A")
                  .append("\n");
            }
        }
        output.setText(sb.toString());
    }

    public static void main(String[] args) {

        new LoginFrame(); 
    }
}
