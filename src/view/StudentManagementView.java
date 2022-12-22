package view;

import java.awt.*;
import javax.swing.*;
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
import model.ThemeList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentManagementView extends JFrame {

    private JPanel Pane;
    private JPanel homePane;
    private JFrame jframe;
    private JMenu menuFile;
    public JTextField textField_search;
    private ButtonGroup btng_gender;
    public static JTable table;
    public static DefaultTableModel model_table;
    public static TableRowSorter<DefaultTableModel> sorter;
    public static StudentManagementModel model;
    public static StudentManagementController controller;
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
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 634);

        // Set location to the center
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setVisible(true);

        controller = new StudentManagementController(this);

        model = new StudentManagementModel();
        formInputView = new FormInputView();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem menuOpen = new JMenuItem("Open");
        menuOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action event
                controller.menuOpen();
            }
        });
        menuFile.add(menuOpen);

        JMenuItem menuSave = new JMenuItem("Save");
        menuSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action event
                controller.menuSave();
            }
        });
        menuFile.add(menuSave);

        JSeparator separator = new JSeparator();
        menuFile.add(separator);

        JMenuItem menuExit = new JMenuItem("Exit");
        menuFile.add(menuExit);

        JMenu menuAbout = new JMenu("About");
        menuBar.add(menuAbout);

        JMenuItem menuAboutMe = new JMenuItem("About me");
        menuAbout.add(menuAboutMe);
        Pane = new JPanel();
        Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(Pane);
        Pane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 986, 575);
        Pane.add(tabbedPane);

        JPanel homePane = new JPanel();
        homePane.setLayout(null);
        homePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabbedPane.addTab("Home", null, homePane, null);

        textField_search = new JTextField();
        textField_search.setColumns(10);
        textField_search.setBounds(606, 30, 150, 35);
        homePane.add(textField_search);

        JButton btn_search = new JButton("Search");
        btn_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action event
                controller.searchButton();
            }
        });
        btn_search.setBounds(776, 30, 100, 35);
        homePane.add(btn_search);

        JButton btn_add = new JButton("Add");
        btn_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action event
                controller.addButton();
            }
        });
        btn_add.setBounds(56, 109, 134, 35);
        homePane.add(btn_add);

        JButton btn_update = new JButton("Update");
        btn_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action event
                controller.updateButton();
            }
        });
        btn_update.setBounds(56, 182, 134, 35);
        homePane.add(btn_update);

        JButton btn_delete = new JButton("Delete");
        btn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action event
                controller.deleteButton();
            }
        });
        btn_delete.setBounds(56, 253, 134, 35);
        homePane.add(btn_delete);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(260, 100, 716, 392);
        homePane.add(scrollPane);

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
        btn_clearSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the action event
                controller.clearSearchButton();
            }
        });
        btn_clearSearch.setBounds(886, 30, 90, 35);
        homePane.add(btn_clearSearch);

        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);

        // Setting panel
        JPanel settingPane = new JPanel();
        tabbedPane.addTab("Setting", null, settingPane, null);
        settingPane.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(41, 99, 184, 423);
        settingPane.add(scrollPane_1);

        ThemeList themeList = new ThemeList();
        String[] theme = themeList.getThemeListName();
        JList list = new JList(theme);
        list.setCellRenderer(new MyListCellRenderer(new int[] {0, 5}));
        list.setSelectionModel(new MyListSelectionModel(new int[] {0, 5}));
        list.setSelectedIndex(1);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int index = list.locationToIndex(evt.getPoint());
                controller.setTheme(index);

            }
        });
        scrollPane_1.setViewportView(list);

        JLabel lblNewLabel_theme = new JLabel("Themes:");
        lblNewLabel_theme.setEnabled(false);
        lblNewLabel_theme.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_theme.setBounds(41, 62, 184, 26);
        settingPane.add(lblNewLabel_theme);
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
}
