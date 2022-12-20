package view;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.io.*;
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
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentManagementView extends JFrame {

    private JPanel contentPane;
    private JMenu menuFile;
    private JTextField textField_search;
    private ButtonGroup btng_gender;
    public static JTable table;
    private static DefaultTableModel model_table;
    public static TableRowSorter<DefaultTableModel> sorter;
    public static StudentManagementModel model;
    public FormInputView formInputView;

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
        try {
            String theme = "com.formdev.flatlaf.FlatIntelliJLaf";
            UIManager.setLookAndFeel(theme);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 634);

        Action action = new StudentManagementController(this);
        model = new StudentManagementModel();
        formInputView = new FormInputView();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem menuOpen = new JMenuItem("Open");
        menuOpen.addActionListener(action);
        menuFile.add(menuOpen);

        JMenuItem menuSave = new JMenuItem("Save");
        menuSave.addActionListener(action);
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
        textField_search.setBounds(606, 30, 150, 35);
        contentPane.add(textField_search);
        textField_search.setColumns(10);

        JButton btn_search = new JButton("Search");
        btn_search.addActionListener(action);
        btn_search.setBounds(776, 30, 100, 35);
        contentPane.add(btn_search);

        JButton btn_add = new JButton("Add");
        btn_add.addActionListener(action);
        btn_add.setBounds(56, 109, 134, 35);
        contentPane.add(btn_add);

        JButton btn_update = new JButton("Update");
        btn_update.addActionListener(action);
        btn_update.setBounds(56, 182, 134, 35);
        contentPane.add(btn_update);

        JButton btn_delete = new JButton("Delete");
        btn_delete.addActionListener(action);
        btn_delete.setBounds(56, 253, 134, 35);
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

        JButton btn_clearSearch = new JButton("Clear");
        btn_clearSearch.addActionListener(action);
        btn_clearSearch.setBounds(886, 30, 90, 35);
        contentPane.add(btn_clearSearch);

        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);

    }

    public Student getStudentFromTable() {
        model_table = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        if (sorter!=null) {
            int rowInModel = sorter.convertRowIndexToModel(row);
            row = rowInModel;
        }
        if (row == -1) return new Student();
        String name, gender, id, dobString, courseString, total, status = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd - MM - yyyy");
        name = model_table.getValueAt(row, 0).toString();
        gender = model_table.getValueAt(row, 2).toString();
        id = model_table.getValueAt(row, 1).toString();
        dobString = model_table.getValueAt(row, 3).toString();
        Date dob = null;
        if (!dobString.equals("")) {
            try {
                dob = simpleDateFormat.parse(dobString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        courseString = model_table.getValueAt(row, 4).toString();
        Course course = Course.getCourseById(courseString);
        total = model_table.getValueAt(row, 5).toString();
        status = model_table.getValueAt(row, 6).toString();
        return new Student(name, gender, id, dob, course, Float.parseFloat(total), status);
    }

    public static void addStudentToTable(Student s) {
        DefaultTableModel model_table = (DefaultTableModel) table.getModel();
        try {
            model_table.addRow(new Object[]{
                    s.getName(), s.getId(), s.getGender(), s.getDob(), s.getCourse() == null ? "" : s.getCourse().getId(), s.getTotal(), s.getStatus()
            });

        }
        catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Can not add to table", "ERROR", 0);
            e.printStackTrace();
        }
    }
    public static void addStudentToModel(Student s) {
        model.addStudent(s);
        System.out.println(model);
    }

    public static void saveUpdate(Student s) {
        model_table = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        if (sorter!=null) {
            int rowInModel = sorter.convertRowIndexToModel(row);
            row = rowInModel;
        }
        if (row < 0) return;
        model_table.setValueAt(s.getName(), row, 0);
        model_table.setValueAt(s.getId(), row, 1);
        model_table.setValueAt(s.getGender(), row, 2);
        model_table.setValueAt(s.getDob(), row, 3);
        model_table.setValueAt(s.getCourse() == null ? "" : s.getCourse().getId(), row, 4);
        model_table.setValueAt(s.getTotal(), row, 5);
        model_table.setValueAt(s.getStatus(), row, 6);
        model.updateStudent(s, row);
    }

    public void deleteStudentFromTable() {
        model_table = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        if (sorter!=null) {
            int rowInModel = sorter.convertRowIndexToModel(row);
            row = rowInModel;
        }
        if (row<0) return;
        int isDelete = JOptionPane.showConfirmDialog(this, "Delete confirm");
        if (isDelete == JOptionPane.YES_OPTION) {
            model_table.removeRow(row);
            this.model.deleteStudent(row);
        }
    }

    public void search() {
        String search = textField_search.getText();
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(search, 0);
        model_table = (DefaultTableModel) table.getModel();
        sorter = new TableRowSorter<>(model_table);
        sorter.setRowFilter(rf);
        table.setRowSorter(sorter);
    }
    public void clearSearch() {
        String search = "";
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(search, 0);
        model_table = (DefaultTableModel) table.getModel();
        sorter = new TableRowSorter<>(model_table);
        sorter.setRowFilter(rf);
        table.setRowSorter(sorter);
    }

    public void saveFile(String path) {
        try {
            this.model.setFileName(path);
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Student student : this.model.getStudentList()) {
                oos.writeObject(student);
            }
            oos.close();
            JOptionPane.showMessageDialog(null, "Save successful", "Save!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveFile(){
        if(this.model.getFileName().length()>0) {
            saveFile(this.model.getFileName());
        }else {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                saveFile(file.getAbsolutePath());
            }
        }
    }
    public void openFile() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            openFile(file);
            reloadTable();
        }
    }
    public void openFile(File file) {
        ArrayList<Student> ds = new ArrayList<Student>();
        try {
            this.model.setFileName(file.getAbsolutePath());
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student st = null;
            while((st = (Student) ois.readObject())!=null) {
                ds.add(st);
            }
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.model.setStudentList(ds);
    }
    public void reloadTable() {
        while (true) {
            DefaultTableModel model_table = (DefaultTableModel) table.getModel();
            int rowNum = model_table.getRowCount();
            if(rowNum==0)
                break;
            else
                try {
                    model_table.removeRow(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        for (Student st : this.model.getStudentList()) {
            this.addStudentToTable(st);
        }
    }
}
