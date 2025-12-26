package javaapplication6;

import java.awt.*;
import java.awt.event.*;

class LoginFrame extends Frame implements ActionListener {
    Button teacherBtn, studentBtn;
    LoginFrame() {
        setTitle(" LMS Login");
        setSize(360,240);
        setLayout(new GridLayout(4,1,10,10));

        setBackground(new Color(28,30,34));

        Label title = new Label(" LMS", Label.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(235,235,235));
        add(title);

        teacherBtn = new Button("Login as Teacher");
        studentBtn = new Button("Login as Student");

        styleButton(teacherBtn);
        styleButton(studentBtn);

        add(teacherBtn); 
        add(studentBtn);
        teacherBtn.addActionListener(this);
        studentBtn.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){ 
                System.exit(0);} });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void styleButton(Button b) {
        b.setBackground(new Color(72,133,237));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == teacherBtn) new UniversityLMS("TEACHER");
        else new UniversityLMS("STUDENT");
        dispose();
    }
}
