package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course() {}

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Course getCourseById(String id) {
        ArrayList<Course> courses = Course.getCourseList();
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public static ArrayList<Course> getCourseList() {
        ArrayList<Course> courses = new ArrayList<Course>();
        String[][] subject = new String[][]{
                {"1618", "Programming"},
                {"1619", "Networking"},
                {"PROG102", "Procedural Programming"},
                {"1633", "Website Design & Development"},
                {"1620", "Professional Practice"},
                {"1622", "Database Design & Development"},
                {"1623", "Security"},
                {"1631", "Software Development Life Cycles"},
                {"WEBG301", "Project Web"},
                {"PROG191", "Java Programming"},
                {"1649", "Data Structures & Algorithms"},
                {"1690", "Internet of Things"}
        };
        for (int i = 0; i < subject.length; i++) {
            Course course = new Course(subject[i][0], subject[i][1]);
            courses.add(course);
        }
        return courses;
    }
}

