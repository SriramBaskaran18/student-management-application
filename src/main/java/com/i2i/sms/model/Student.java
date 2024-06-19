package com.i2i.sms.model;

/**
 * <p>
 * This class represents a student entity with attributes
 * such as id, name, date of birth (dob) and city.
 * </p>
 */
public class Student {
    private int id;
    private String name;
    private String dob;
    private String city;

    public Student() {}

    public Student(String name, String dob, String city) {
        this.name = name;
        this.dob = dob;
        this.city = city;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Student Id :").append(id)
                     .append(", Student Name :").append(name)
                     .append(", Student Dob :").append(dob)
                     .append(", Students City :").append(city);
        return stringBuilder.toString();
    }
}
