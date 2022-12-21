package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.*;
import view.*;

public class StudentManagementController {
    public StudentManagementView view;

    public StudentManagementController(StudentManagementView view) {
        this.view = view;
    }
    public void addButton() {
        this.view.formInputView.setCommand("Add");
        this.view.formInputView.deleteForm();
        this.view.formInputView.setVisible(true);
        this.view.formInputView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void updateButton() {
        int row = view.table.getSelectedRow();
        if (row == -1) return;
        this.view.formInputView.setCommand("Update");
        Student student = getStudentFromTable();
        this.view.formInputView.setFormFromTable(student);
        this.view.formInputView.setVisible(true);
        this.view.formInputView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public Student getStudentFromTable() {
        view.model_table = (DefaultTableModel) view.table.getModel();
        int row = view.table.getSelectedRow();
        if (view.sorter!=null) {
            int rowInModel = view.sorter.convertRowIndexToModel(row);
            row = rowInModel;
        }
        if (row == -1) return new Student();
        String name, gender, id, dobString, courseString, total, status = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd - MM - yyyy");
        name = view.model_table.getValueAt(row, 0).toString();
        gender = view.model_table.getValueAt(row, 2).toString();
        id = view.model_table.getValueAt(row, 1).toString();
        dobString = view.model_table.getValueAt(row, 3).toString();
        Date dob = null;
        if (!dobString.equals("")) {
            try {
                dob = simpleDateFormat.parse(dobString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        courseString = view.model_table.getValueAt(row, 4).toString();
        Course course = Course.getCourseById(courseString);
        total = view.model_table.getValueAt(row, 5).toString();
        status = view.model_table.getValueAt(row, 6).toString();
        return new Student(name, gender, id, dob, course, Float.parseFloat(total), status);
    }

    public void deleteButton() {
        view.model_table = (DefaultTableModel) view.table.getModel();
        int row = view.table.getSelectedRow();
        if (view.sorter==null) return;
        if (view.sorter!=null && view.sorter.getModelRowCount()>0) {
            int rowInModel =-1;
            try {
                rowInModel = view.sorter.convertRowIndexToModel(row);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            row = rowInModel;
        }
        if (row<0||row==-1) return;

        int isDelete = JOptionPane.showConfirmDialog(view, "Delete confirm");
        if (isDelete == JOptionPane.YES_OPTION) {
            view.model_table.removeRow(row);
            view.model.deleteStudent(row);
        }
    }
    public void searchButton() {
        String search = view.textField_search.getText();
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(search, 0);
        view.model_table = (DefaultTableModel) view.table.getModel();
        view.sorter = new TableRowSorter<>(view.model_table);
        view.sorter.setRowFilter(rf);
        view.table.setRowSorter(view.sorter);
    }
    public void clearSearchButton() {
        String search = "";
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(search, 0);
        view.model_table = (DefaultTableModel) view.table.getModel();
        view.sorter = new TableRowSorter<>(view.model_table);
        view.sorter.setRowFilter(rf);
        view.table.setRowSorter(view.sorter);
    }
    public void setTheme(int index) {
        ThemeList themeList = new ThemeList();
        String classPathTheme = themeList.themeList[index][1];
        try {
            UIManager.setLookAndFeel(classPathTheme);
            UIManager.getLookAndFeel().provideErrorFeedback(this.view);
            SwingUtilities.updateComponentTreeUI(this.view);
            UIManager.getLookAndFeel().provideErrorFeedback(this.view.formInputView);
            SwingUtilities.updateComponentTreeUI(this.view.formInputView);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void menupen() {
        openFile();
    }
    public void openFile() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(view);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            openFile(file);
            reloadTable();
        }
    }
    public void openFile(File file) {
        ArrayList<Student> ds = new ArrayList<Student>();
        try {
            view.model.setFileName(file.getAbsolutePath());
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
        view.model.setStudentList(ds);
    }
    public void reloadTable() {
        while (true) {
            DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
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
        for (Student st : view.model.getStudentList()) {
            view.addStudentToTable(st);
        }
    }
    public void menuSave() {
        saveFile();
    }
    public void saveFile(){
        if(view.model.getFileName().length()>0) {
            saveFile(view.model.getFileName());
        }else {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                saveFile(file.getAbsolutePath());
            }
        }
    }
    public void saveFile(String path) {
        try {
            view.model.setFileName(path);
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Student student : view.model.getStudentList()) {
                oos.writeObject(student);
            }
            oos.close();
            JOptionPane.showMessageDialog(null, "Save successful", "Save!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void test() {
        Set<Object> values = new HashSet<>();
        for (int row = 0; row < this.view.table.getRowCount(); row++) {
            Object value = this.view.table.getValueAt(row, 1); // column is the index of the column that you want to check for duplicates
            if (values.contains(value)) {
                // value is a duplicate
            }
            values.add(value);
        }
        System.out.println(values);
    }
}

