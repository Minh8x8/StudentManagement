package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Course implements Serializable {
    private String id;
    private String name;

    private  ArrayList<Course> courses;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(courses, course.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, courses);
    }
}

