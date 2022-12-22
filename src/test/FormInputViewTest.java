package test;

import model.Course;
import model.CourseList;
import model.Student;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import view.FormInputView;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FormInputViewTest {
    private FormInputView formInputView = new FormInputView();
    @org.junit.jupiter.api.BeforeEach
    public void init() {
        formInputView.textField_name.setText("Minh");
        formInputView.textField_id.setText("GCD210237");
        formInputView.rdbtn_male.setSelected(true);
        formInputView.dateChooser.setDate(new Date(2003, Calendar.FEBRUARY, 26));
        formInputView.comboBox_course.setSelectedIndex(0);
        formInputView.textField_total.setText("10");
        formInputView.textField_status.setText("Pass");
        formInputView.student();
    }

    @org.junit.jupiter.api.Test
    void returnStudent() {
        formInputView.student();
        System.out.println(formInputView.textField_name.getText());
        Date date = new Date(2003, 1, 26);
        Course course = CourseList.getCourseById("1618");
        formInputView.student.setCourse(course);
        Student studentExpected = new Student("Minh", "Male","GCD210237",
                date, course, 10, "Pass");
        assertEquals(studentExpected, formInputView.student);
    }

    @org.junit.jupiter.api.Test
    void student() {
        assertTrue(formInputView.student());
    }

    @org.junit.jupiter.api.Test
    void setFormFromTable() {
        Date date = new Date(2003, 1, 26);
        Course course = CourseList.getCourseById(formInputView.comboBox_course.getSelectedItem().toString());
        formInputView.student.setCourse(course);
        Student studentExpected = new Student("Minh", "Male","GCD210237",
                date, course, 10, "Pass");
        formInputView.setFormFromTable(studentExpected);
        assertEquals("Minh", formInputView.textField_name.getText());
        assertEquals("Male", formInputView.student.getGender());
        assertEquals(date, formInputView.dateChooser.getDate());
        assertEquals(studentExpected.getId(), formInputView.textField_id.getText());
        assertEquals(studentExpected.getCourse(), course);
        assertEquals(studentExpected.getTotal(), Float.parseFloat(formInputView.textField_total.getText()));
        assertEquals(studentExpected.getStatus(), formInputView.textField_status.getText());
    }
}