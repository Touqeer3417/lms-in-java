# 🎓 University Learning Management System (LMS)

A **Java-based Desktop Learning Management System** built using **Core Java, OOP principles, AWT GUI, and File Handling**.
This project simulates a simple **University LMS** where **teachers manage courses & assignments** and **students enroll, submit, and view results**.

---

## ✨ Features

### 🔐 Login System

* Login as **Teacher**
* Login as **Student**

### 👨‍🏫 Teacher Panel

* ➕ Add Students
* 📚 Create Courses
* 📝 Create Assignments
* 🧮 Evaluate Students (Marks → Grades)
* 📄 View Saved Report Cards

### 👨‍🎓 Student Panel

* 🧾 Register Student Details
* 📥 Enroll in Courses
* 📖 View Enrolled Courses
* 📝 View Assignments
* 📊 View Results & Grades

---

## 🧠 Concepts Used

This project is designed to **demonstrate core Java and OOP concepts clearly**:

* ✅ Object-Oriented Programming (OOP)

  * Abstraction
  * Inheritance
  * Encapsulation
  * Polymorphism
* ✅ Interfaces (`Evaluation`)
* ✅ Abstract Classes (`Person`)
* ✅ Enums (`GradeLetter`)
* ✅ Java Collections (`ArrayList`)
* ✅ File Handling (`FileReader`, `FileWriter`)
* ✅ AWT GUI (Frame, Button, Dialog, TextField, Choice)
* ✅ Event Handling

---

## 🏗️ Project Structure

```
javaapplication6/
│
├── Assignment.java
├── Course.java
├── Evaluation.java
├── FileHandling.java
├── Grade.java
├── LoginFrame.java
├── Person.java
├── Student.java
├── Teacher.java
├── UniversityLMS.java
```

---

## 📐 UML-Based Design

This project follows a **clean UML-style structure**:

* `Person` (Abstract Class)

  * `Student`
  * `Teacher`
* `Course`
* `Assignment`
* `Grade`
* `Evaluation` (Interface)



---

## 🖥️ User Interface

* Clean **AWT-based GUI**
* Separate dashboards for **Teacher & Student**
* Dialog-based forms
* Dark-themed interface for better readability

---

## 💾 File Handling

* Student evaluation reports are saved in:

  ```
  ReportCard.txt
  ```
* Reports can be viewed anytime from the **Teacher Dashboard**

---

## 🚀 How to Run the Project

1. Open **NetBeans**
2. Create a **Java Application**
3. Copy all files into the `javaapplication6` package
4. Run:

   ```java
   UniversityLMS.java
   ```
5. Login as **Teacher** or **Student**

---



## 🧑‍💻 Author

**Touqeer Ali**
🌱 Learning OOP, File Handling, and GUI Development

---

## ⭐ Future Improvements

* Database integration (MySQL)
* Login authentication
* Assignment submission
* Swing / JavaFX UI
* Role-based access control

---

## 📜 License

This project is for **learning and educational purposes**.


