package view;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.toedter.calendar.JDateChooser;

import controller.StudentManagementController;
import model.Course;
import model.Student;
import model.StudentManagementModel;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentManagementView extends JFrame {

    private JPanel contentPane;
    private JMenu menuFile;
    private JTextField textField_search;
    private JTextField textField_name;
    private JRadioButton rdbtn_male;
    private JRadioButton rdbtn_female;
    private ButtonGroup btng_gender;
    private JTextField textField_id;
    private JDateChooser dateChooser;
    private JComboBox comboBox_course;
    private JTextField textField_total;
    private JTextField textField_status;
    private JTable table;
    private StudentManagementModel model;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentManagementView frame = new StudentManagementView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StudentManagementView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 634);

        Action action = new StudentManagementController(this);
        model = new StudentManagementModel();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem menuOpen = new JMenuItem("Open");
        menuFile.add(menuOpen);

        JMenuItem menuSave = new JMenuItem("Save");
        menuFile.add(menuSave);

        JSeparator separator = new JSeparator();
        menuFile.add(separator);

        JMenuItem menuExit = new JMenuItem("Exit");
        menuFile.add(menuExit);

        JMenu menuAbout = new JMenu("About");
        menuBar.add(menuAbout);

        JMenuItem menuAboutMe = new JMenuItem("About me");
        menuAbout.add(menuAboutMe);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField_search = new JTextField();
        textField_search.setBounds(571, 11, 150, 35);
        contentPane.add(textField_search);
        textField_search.setColumns(10);

        JButton btn_search = new JButton("Search");
        btn_search.addActionListener(action);
        btn_search.setBounds(748, 11, 100, 35);
        contentPane.add(btn_search);

        JLabel lb_name = new JLabel("Name");
        lb_name.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_name.setBounds(10, 100, 70, 35);
        contentPane.add(lb_name);

        JLabel lb_gender = new JLabel("Gender");
        lb_gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_gender.setBounds(10, 150, 70, 35);
        contentPane.add(lb_gender);

        JLabel lb_id = new JLabel("ID");
        lb_id.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_id.setBounds(10, 200, 70, 35);
        contentPane.add(lb_id);

        JLabel lb_dob = new JLabel("DoB");
        lb_dob.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_dob.setBounds(10, 250, 70, 35);
        contentPane.add(lb_dob);

        JLabel lb_course = new JLabel("Course");
        lb_course.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_course.setBounds(10, 300, 70, 35);
        contentPane.add(lb_course);

        JLabel lb_total = new JLabel("Total");
        lb_total.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_total.setBounds(10, 350, 70, 35);
        contentPane.add(lb_total);

        JLabel lb_status = new JLabel("Status");
        lb_status.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lb_status.setBounds(10, 400, 70, 35);
        contentPane.add(lb_status);

        textField_name = new JTextField();
        textField_name.setBounds(77, 101, 150, 35);
        contentPane.add(textField_name);
        textField_name.setColumns(10);

        textField_id = new JTextField();
        textField_id.setColumns(10);
        textField_id.setBounds(77, 201, 150, 35);
        contentPane.add(textField_id);

        textField_total = new JTextField();
        textField_total.setColumns(10);
        textField_total.setBounds(77, 351, 150, 35);
        contentPane.add(textField_total);

        textField_status = new JTextField();
        textField_status.setColumns(10);
        textField_status.setBounds(77, 401, 150, 35);
        contentPane.add(textField_status);

        rdbtn_male = new JRadioButton("Male");
        rdbtn_male.setBounds(78, 157, 70, 23);
        contentPane.add(rdbtn_male);

        rdbtn_female = new JRadioButton("Female");
        rdbtn_female.setBounds(150, 157, 70, 23);
        contentPane.add(rdbtn_female);

        btng_gender = new ButtonGroup();
        btng_gender.add(rdbtn_male);
        btng_gender.add(rdbtn_female);

        dateChooser = new JDateChooser();
        dateChooser.setBackground(new Color(255, 255, 255));
        dateChooser.setDateFormatString("dd - MM - yyyy");
        dateChooser.setBounds(77, 250, 150, 35);
        contentPane.add(dateChooser);

        comboBox_course = new JComboBox();
        comboBox_course.setBounds(77, 301, 150, 35);
        ArrayList<Course> courseList = Course.getCourseList();
        for (Course c : courseList) {
            comboBox_course.addItem(c.getId());
        }
        comboBox_course.setSelectedIndex(-1);
        contentPane.add(comboBox_course);

        JButton btn_add = new JButton("Add");
        btn_add.addActionListener(action);
        btn_add.setBounds(10, 457, 108, 35);
        contentPane.add(btn_add);

        JButton btn_update = new JButton("Update");
        btn_update.addActionListener(action);
        btn_update.setBounds(10, 500, 108, 35);
        contentPane.add(btn_update);

        JButton btn_delete = new JButton("Delete");
        btn_delete.addActionListener(action);
        btn_delete.setBounds(142, 457, 108, 35);
        contentPane.add(btn_delete);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(260, 100, 716, 392);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Name", "ID", "Gender", "Date of birth", "Course", "Mark", "Status"
                }
        ));

        JButton btn_saveUpdate = new JButton("Save Update");
        btn_saveUpdate.addActionListener(action);
        btn_saveUpdate.setBounds(142, 500, 108, 35);
        contentPane.add(btn_saveUpdate);

        JButton btn_clear = new JButton("Clear");
        btn_clear.addActionListener(action);
        btn_clear.setBounds(74, 540, 108, 35);
        contentPane.add(btn_clear);

        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);

    }
    public void deleteForm() {
        textField_name.setText("");
        btng_gender.clearSelection();
        textField_id.setText("");
        comboBox_course.setSelectedIndex(-1);
        textField_total.setText("");
        textField_status.setText("");
    }

    public Student getStudentFromForm() {
        String name = textField_name.getText();
        String gender = "";
        if (rdbtn_male.isSelected()) gender = "Male";
        else if (rdbtn_female.isSelected()) gender = "Female";
        else JOptionPane.showMessageDialog(null, "Gender is not selected", "FORM ERROR", JOptionPane.ERROR_MESSAGE);
        String id = textField_id.getText();
        Date dob = dateChooser.getDate();
        String courseName = "";
        try {
            courseName = comboBox_course.getSelectedItem().toString();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Course is null", "Course Error", JOptionPane.ERROR_MESSAGE);
        }
        Course course = Course.getCourseById(courseName);
        float total = 0;
        try {
            total = Float.parseFloat(textField_total.getText());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Number Error", JOptionPane.ERROR_MESSAGE);
        }
        String status = textField_status.getText();
        return new Student(name, gender, id, dob, course, total, status);
    }
    public void setFormFromTable() {
        Student s = getStudentFromTable();
        textField_name.setText(s.getName());
        if (s.getGender().equals("Male")) rdbtn_male.setSelected(true);
        else if (s.getGender().equals("Female")) rdbtn_female.setSelected(true);
        textField_id.setText(s.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
        Date date = null;
        try {
            date = sdf.parse(s.getDob());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        dateChooser.setDate(date);
        comboBox_course.setSelectedItem(s.getCourse().getId());
        textField_total.setText(String.valueOf(s.getTotal()));
        textField_status.setText(s.getStatus());
    }

    public Student getStudentFromTable() {
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        String name, gender, id, dobString, courseString, total, status = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd - MM - yyyy");
        name = model_table.getValueAt(row, 0).toString();
        gender = model_table.getValueAt(row, 1).toString();
        id = model_table.getValueAt(row, 2).toString();
        dobString = model_table.getValueAt(row, 3).toString();
        Date dob;
        try {
            dob = simpleDateFormat.parse(dobString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        courseString = model_table.getValueAt(row, 4).toString();
        Course course = Course.getCourseById(courseString);
        total = model_table.getValueAt(row, 5).toString();
        status = model_table.getValueAt(row, 6).toString();
        return new Student(name, gender, id, dob, course, Float.parseFloat(total), status);
    }

    public void addStudentToTable(Student s) {
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        try {
            model_table.addRow(new Object[]{
                    s.getName(), s.getGender(), s.getId(), s.getDob(), s.getCourse().getId(), s.getTotal(), s.getStatus()
            });
            this.model.addStudent(s);

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Can not add to table", "ERROR", 0);
        }
    }

    public void saveUpdate() {
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        if (row < 0) return;
        Student s = getStudentFromForm();
        model_table.setValueAt(s.getName(), row, 0);
        model_table.setValueAt(s.getGender(), row, 1);
        model_table.setValueAt(s.getId(), row, 2);
        model_table.setValueAt(s.getDob(), row, 3);
        model_table.setValueAt(s.getCourse() == null ? "" : s.getCourse().getId(), row, 4);
        model_table.setValueAt(s.getTotal(), row, 5);
        model_table.setValueAt(s.getStatus(), row, 6);
        model.updateStudent(s, row);
    }

    public void deleteStudentFromTable() {
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        if (row<0) return;
        int isDelete = JOptionPane.showConfirmDialog(this, "Delete confirm");
        if (isDelete == JOptionPane.YES_OPTION) {
            model_table.removeRow(row);
            this.model.deleteStudent(row);
        }
    }

    public void search() {
        String search = textField_search.getText();
    }
}