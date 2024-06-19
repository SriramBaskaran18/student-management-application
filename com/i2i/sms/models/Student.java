package com.i2i.sms.models;

import java.util.Set;

import com.i2i.sms.utils.DateUtils;

public class Student {
    private int id;    
    private String name;    
    private String dob;    
    private Grade grade;
    private Address address;
    private Set<Role> roles;

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

    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
 
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nStudentId : ").append(id)
            .append(", Student Name : ").append(name)
            .append(", Student D.O.B : ").append(dob)
            .append(", Student Age : ").append(DateUtils.calculateDateDifference(dob));
        return stringBuilder.toString();
    }
}
