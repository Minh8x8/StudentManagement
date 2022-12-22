package test;

import model.Course;
import model.CourseList;
import model.Student;
import model.StudentManagementModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagementModelTest {
    StudentManagementModel model = new StudentManagementModel();
    @BeforeEach
    void init() {
        model.setFileName("File.txt");
        Course course = CourseList.getCourseById("1618");
        Student studentExpected1 = new Student("Minh", "Male","GCD1001",
                new Date(2003, Calendar.JANUARY, 26), course, 10, "Pass");
        Student studentExpected2 = new Student("Chien", "Male","GCD1002",
                new Date(2003, Calendar.JANUARY, 22), course, 10, "Pass");
        Student studentExpected3 = new Student("Kiet", "Male","GCD1003",
                new Date(2003, Calendar.JANUARY, 22), course, 10, "Pass");
        model.addStudent(studentExpected1);
        model.addStudent(studentExpected2);
        model.addStudent(studentExpected3);
    }

    @Test
    void getStudentList() {
        ArrayList<Student> stListExpected = new ArrayList<Student>();
        Course course = CourseList.getCourseById("1618");
        Student studentExpected1 = new Student("Minh", "Male","GCD1001",
                new Date(2003, Calendar.JANUARY, 26), course, 10, "Pass");
        Student studentExpected2 = new Student("Chien", "Male","GCD1002",
                new Date(2003, Calendar.JANUARY, 22), course, 10, "Pass");
        Student studentExpected3 = new Student("Kiet", "Male","GCD1003",
                new Date(2003, Calendar.JANUARY, 22), course, 10, "Pass");
        stListExpected.add(studentExpected1);
        stListExpected.add(studentExpected2);
        stListExpected.add(studentExpected3);
        ArrayList<Student> stListActual = model.getStudentList();
        for (int i = 0; i < stListActual.size(); i++) {
            assertTrue(stListExpected.get(i).equals(stListActual.get(i)));
        }
    }

    @Test
    void addStudent() {
        Course course = CourseList.getCourseById("1618");
        Student studentExpected4 = new Student("Hieu", "Male","GCD1004",
                new Date(2003, Calendar.JANUARY, 26), course, 10, "Pass");
        model.addStudent(studentExpected4);
        ArrayList<Student> stListActual = model.getStudentList();
        Student studentActual = stListActual.get(stListActual.size() - 1);
        assertTrue(studentActual.equals(studentExpected4));
    }

    @Test
    void setFileName() {
        model.setFileName("FileRename.txt");
        assertEquals("FileRename.txt", model.getFileName());
    }

    @Test
    void getFileName() {
        assertEquals("File.txt", model.getFileName());
    }

    @Test
    void deleteStudent() {
        ArrayList<Student> stListActual = model.getStudentList();
        assertEquals(3, stListActual.size());
        model.deleteStudent(2);
        stListActual = model.getStudentList();
        assertEquals(2, stListActual.size());
    }

    @Test
    void updateStudent() {
        Course course = CourseList.getCourseById("1618");
        Student studentExpected4 = new Student("Hieu", "Male","GCD1004",
                new Date(2003, Calendar.JANUARY, 26), course, 10, "Pass");
        model.updateStudent(studentExpected4, 2);
        ArrayList<Student> stList = model.getStudentList();
        assertTrue(stList.get(2).equals(studentExpected4));
    }
}