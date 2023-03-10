package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student implements Serializable {
    private String name;
    private String id;
    private String gender;
    private Date dob;
    private Course course;
    private float total;
    private String status;
    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        String pattern = "dd - MM - yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (dob==null) return "";
        return simpleDateFormat.format(dob);
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student(String name, String gender, String id, Date dob, Course course, float total, String status) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.dob = dob;
        this.course = course;
        this.total = total;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", course='" + course + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }
    public boolean isTheSameStudent(Student student) {
        if (this.name == student.name
        && this.gender == student.gender
        && this.id == student.id
        && this.dob == student.dob
        ) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(student.total, total) == 0 && Objects.equals(name, student.name) && Objects.equals(id, student.id) && Objects.equals(gender, student.gender) && Objects.equals(dob, student.dob) && Objects.equals(course, student.course) && Objects.equals(status, student.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, gender, dob, course, total, status);
    }
}

