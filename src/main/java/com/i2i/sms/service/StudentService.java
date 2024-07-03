package com.i2i.sms.service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService {
    Student addStudent(String name, LocalDate dob, int std,
                       String section, Address address,
                       Set<Role> roles) throws StudentManagementException;

    Optional<Student> getStudentById(int studentId) throws StudentManagementException;

    List<Student> getAllStudents() throws StudentManagementException;

    boolean deleteStudentById(int studentId) throws StudentManagementException;
}
