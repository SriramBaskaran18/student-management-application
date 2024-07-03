package com.i2i.sms.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * <p>
 * The Grade class represents a grade entity with attributes such as id, standard, section, and students.
 * It includes getter and setter methods for accessing and modifying these attributes.
 * </p>
 */
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "standard")
    private int standard;

    @Column(name = "section")
    private String section;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students;

    public Grade() {
    }

    public Grade(int standard, String section) {
        this.standard = standard;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStd() {
        return standard;
    }

    public void setStd(int standard) {
        this.standard = standard;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Grade Unique Id : ").append(id)
                .append("\nStudent's Standard : ").append(standard)
                .append("\nStudent's Section : ").append(section);
        return stringBuilder.toString();
    }
}