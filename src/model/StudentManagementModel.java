package model;

import java.util.ArrayList;

public class StudentManagementModel {
    private ArrayList<Student> studentList = new ArrayList<Student>();

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(Student student) {this.studentList.add(student);}

    public StudentManagementModel() {}

    public StudentManagementModel(ArrayList<Student> stList) {
        this.studentList = stList;
    }

    public void insertStudent(Student student) {
        this.studentList.add(student);
    }
    public void deleteStudent(int row) {
        studentList.remove(row);
    }
    public void updateStudent(Student student, int i) {
        studentList.set(i, student);
    }
}
