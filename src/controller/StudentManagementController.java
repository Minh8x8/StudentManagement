package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JOptionPane;

import model.*;
import view.*;

public class StudentManagementController implements Action{
    StudentManagementView view;
    public StudentManagementController(StudentManagementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add":
                Student s = this.view.getStudentFromForm();
                this.view.addStudentToTable(s);
                this.view.deleteForm();
                this.view.addStudentToModel(s);
                break;
            case "Update":
                this.view.setFormFromTable();
                break;
            case "Save Update":
                this.view.saveUpdate();
                break;
            case "Delete":
                this.view.deleteStudentFromTable();
            case "Clear":
                this.view.deleteForm();
                break;
            case "Search":
                this.view.search();
                break;
            case "Save":
                this.view.saveFile();
                break;
            case "Open":
                this.view.openFile();
        }
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

