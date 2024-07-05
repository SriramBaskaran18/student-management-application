package com.i2i.sms.service;

import com.i2i.sms.dto.CreateAddressDto;
import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService {

    ResponseStudentDto addStudent(RequestStudentDto requestStudentDto) throws StudentManagementException;

    ResponseStudentDto getStudentById(int studentId) throws StudentManagementException;

    List<StudentDto> getAllStudents() throws StudentManagementException;

    boolean deleteStudentById(int studentId) throws StudentManagementException;

}
