package view;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.toedter.calendar.JDateChooser;
import controller.StudentManagementController;
import model.Course;
import model.Student;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static javax.swing.JOptionPane.YES_OPTION;

public class FormInputView extends JFrame {

    private JPanel formInputPane;
    private JTextField textField_name;
    private ButtonGroup btng_gender;
    private JRadioButton rdbtn_male;
    private JRadioButton rdbtn_female;
    private JTextField textField_id;
    private JDateChooser dateChooser;
    private JComboBox comboBox_course;
    private JTextField textField_total;
    private JTextField textField_status;

    public String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Create the frame.
     */
    public FormInputView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 355, 517);
        formInputPane = new JPanel();
        formInputPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(formInputPane);
        formInputPane.setLayout(null);

        JLabel lb_title = new JLabel("Student Form");
        lb_title.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_title.setBounds(10, 0, 113, 35);
        formInputPane.add(lb_title);

        JLabel lb_name = new JLabel("Name");
        lb_name.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_name.setBounds(10, 71, 70, 35);
        formInputPane.add(lb_name);

        JLabel lb_gender = new JLabel("Gender");
        lb_gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_gender.setBounds(10, 121, 70, 35);
        formInputPane.add(lb_gender);

        JLabel lb_id = new JLabel("ID");
        lb_id.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_id.setBounds(10, 171, 70, 35);
        formInputPane.add(lb_id);

        JLabel lb_dob = new JLabel("DoB");
        lb_dob.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_dob.setBounds(10, 221, 70, 35);
        formInputPane.add(lb_dob);

        JLabel lb_course = new JLabel("Course");
        lb_course.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_course.setBounds(10, 271, 70, 35);
        formInputPane.add(lb_course);

        JLabel lb_total = new JLabel("Total");
        lb_total.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_total.setBounds(10, 321, 70, 35);
        formInputPane.add(lb_total);

        JLabel lb_status = new JLabel("Status");
        lb_status.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_status.setBounds(10, 371, 70, 35);
        formInputPane.add(lb_status);

        textField_name = new JTextField();
        textField_name.setColumns(10);
        textField_name.setBounds(77, 72, 250, 35);
        formInputPane.add(textField_name);

        rdbtn_male = new JRadioButton("Male");
        rdbtn_male.setBounds(78, 128, 70, 23);
        formInputPane.add(rdbtn_male);

        rdbtn_female = new JRadioButton("Female");
        rdbtn_female.setBounds(150, 128, 70, 23);
        formInputPane.add(rdbtn_female);

        btng_gender = new ButtonGroup();
        btng_gender.add(rdbtn_male);
        btng_gender.add(rdbtn_female);

        textField_id = new JTextField();
        textField_id.setColumns(10);
        textField_id.setBounds(77, 172, 250, 35);
        formInputPane.add(textField_id);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd - MM - yyyy");
        dateChooser.setBackground(Color.WHITE);
        dateChooser.setBounds(77, 221, 250, 35);
        formInputPane.add(dateChooser);

        comboBox_course = new JComboBox();
        comboBox_course.setBounds(77, 272, 250, 35);
        ArrayList<Course> courseList = Course.getCourseList();
        for (Course c : courseList) {
            comboBox_course.addItem(c.getId());
        }
        comboBox_course.setSelectedIndex(-1);
        formInputPane.add(comboBox_course);

        textField_total = new JTextField();
        textField_total.setColumns(10);
        textField_total.setBounds(77, 322, 250, 35);
        formInputPane.add(textField_total);

        textField_status = new JTextField();
        textField_status.setColumns(10);
        textField_status.setBounds(77, 372, 250, 35);
        formInputPane.add(textField_status);

        JSeparator separator = new JSeparator();
        separator.setBounds(91, 25, 285, 2);
        formInputPane.add(separator);

        JButton btn_submit = new JButton("Submit");
        btn_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (student()) {
                    switch (command) {
                        case "Add":
                            StudentManagementView.addStudentToTable(student);
                            StudentManagementView.addStudentToModel(student);
                            System.out.println(2);
                            break;
                        case "Update":
                            StudentManagementView.saveUpdate(student);
                            break;
                    }
                    dispose();
                }
            }
        });
        btn_submit.setBounds(36, 434, 113, 35);
        formInputPane.add(btn_submit);

        JButton btn_clear = new JButton("Clear");
        btn_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteForm();
            }
        });
        btn_clear.setBounds(184, 434, 113, 35);
        formInputPane.add(btn_clear);
    }
    public Student student;
    public Student returnStudent() {
        return this.student;
    }
    public boolean student() {
        String errorEmpty = "";
        String errorInput = "";
        String name = textField_name.getText();
        if (name.length() == 0) {
            errorEmpty+="\nName is empty";
        }
        String gender = "";
        if (rdbtn_male.isSelected()) gender = "Male";
        else if (rdbtn_female.isSelected()) gender = "Female";
        else {
            errorEmpty+="\nGender is not selected";
        }
        String id = textField_id.getText();
        if (id.length() == 0) {
            errorEmpty+="\nID is empty";
        }
        Date dob = dateChooser.getDate();
        if (dob == null) {
            errorEmpty+="\nDob is empty";
        }
        String courseName = "";
        try {
            courseName = comboBox_course.getSelectedItem().toString();
        }
        catch (Exception e) {
            errorEmpty+="\nCourse is null";
        }
        Course course = Course.getCourseById(courseName);
        float total = 0;
        try {
            String floatString = textField_total.getText();
            if (floatString.length() == 0) {
                errorEmpty+="\nTotal is emty";
            }
            else total = Float.parseFloat(floatString);
        }
        catch (Exception e) {
            errorInput= errorInput + "\n" + e.getMessage();
        }
        if (total <0 || total >10) errorInput+="\nWrong score range";
        String status = textField_status.getText();
        if (status.length() == 0) {
            errorEmpty+="\nStatus is empty";
        }
        int jOption = JOptionPane.NO_OPTION;
        student = new Student(name, gender, id, dob, course, total, status);
        if (errorInput.length() > 0) {
            JOptionPane.showMessageDialog(null, errorInput, "ERROR INPUT", JOptionPane.ERROR_MESSAGE);
        }
        else if (errorEmpty.length() > 0) {
            jOption = JOptionPane.showConfirmDialog(null, errorEmpty +
                    "\n\nDo you still want to submit?", "INCOMPLETE FORM", JOptionPane.INFORMATION_MESSAGE);
        }
        if (jOption == JOptionPane.YES_OPTION || errorInput.length()==0 && errorEmpty.length()==0)
            return true;
        return false;
    }
    public void setFormFromTable(Student student) {
        textField_name.setText(student.getName());
        if (student.getGender() == null) {

        }
        else if (student.getGender().equals("Male")) rdbtn_male.setSelected(true);
        else if (student.getGender().equals("Female")) rdbtn_female.setSelected(true);
        textField_id.setText(student.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
        Date date = null;
        if (student.getDob().equals("")) {
            dateChooser.setDate(null);
        }
        else {
            try {
                date = sdf.parse(student.getDob());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            dateChooser.setDate(date);
        }
        if (student.getCourse() != null) {
            comboBox_course.setSelectedItem(student.getCourse().getId());
        }
        textField_total.setText(String.valueOf(student.getTotal()));
        textField_status.setText(student.getStatus());
    }
    public void deleteForm() {
        textField_name.setText("");
        btng_gender.clearSelection();
        textField_id.setText("");
        dateChooser.setDate(null);
        comboBox_course.setSelectedIndex(-1);
        textField_total.setText("");
        textField_status.setText("");
    }
}
