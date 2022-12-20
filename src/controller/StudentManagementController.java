package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import model.*;
import view.*;

public class StudentManagementController implements Action{
    public StudentManagementView view;

    public StudentManagementController(StudentManagementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add":
                //Student s = this.view.getStudentFromForm("Add");
                    this.view.formInputView.setCommand("Add");
                    this.view.formInputView.deleteForm();
                    this.view.formInputView.setVisible(true);
                    this.view.formInputView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case "Update":
                //this.view.setFormFromTable();
                this.view.formInputView.setCommand("Update");
                Student student = this.view.getStudentFromTable();
                this.view.formInputView.setFormFromTable(student);
                this.view.formInputView.setVisible(true);
                this.view.formInputView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                break;
            case "Delete":
                this.view.deleteStudentFromTable();
            case "Search":
                this.view.search();
                break;
            case "Clear":
                this.view.clearSearch();
                break;
            case "Save":
                this.view.saveFile();
                break;
            case "Open":
                this.view.openFile();
                break;
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

    @Override
    public Object getValue(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putValue(String key, Object value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEnabled(boolean b) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub

    }
}

