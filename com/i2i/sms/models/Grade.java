package com.i2i.sms.models;

import java.util.Set;

public class Grade {
    public Grade() {}

    private int id;
    private int std;
    private String section;
    private Set<Student> students;

    Grade(int std, String section) {
        this.std = std;
        this.section = section;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStd() {
        return std;
    }
    public void setStd(int std) {
        this.std = std;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }

    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Grade Unique Id : ").append(id)
                .append("\nStudent's Standard : ").append(std)
                .append("\nStudent's Section : ").append(section);
        return stringBuilder.toString();
    }
}
