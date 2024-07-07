package com.i2i.sms.service;


import java.util.*;

import com.i2i.sms.dto.*;
import com.i2i.sms.mapper.AddressMapper;
import com.i2i.sms.mapper.StudentMapper;
import com.i2i.sms.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;
import com.i2i.sms.repository.StudentRepository;

/**
 * <p>
 * It interacts with the database like adding, fetching, searching, and deleting student records.
 * </p>
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * {@inheritDoc}
     */
    public ResponseStudentDto addStudent(RequestStudentDto requestStudentDto)
            throws StudentManagementException {
        try {
            Grade grade = gradeService.getGradeIfGradeExists(requestStudentDto.getGrade());
            if (null == grade) {
                grade = gradeService.addGrade(requestStudentDto.getGrade());
            }
            Student student = studentMapper.requestDtoToEntity(requestStudentDto);
            Address address = addressMapper.requestDtoToEntity(requestStudentDto.getAddress());
            student.setAddress(address);
            address.setStudent(student);
            student.setGrade(grade);
            List<Role> roles =new ArrayList<>();
            for (CreateRoleDto role : requestStudentDto.getRoles()) {
                roles.add(roleService.getRoleIfRoleExists(role.getRole()));
            }
            student.setRoles(roles);
            Student insertedStudent = studentRepository.save(student);
            return studentMapper.entityToResponseDto(insertedStudent);
        } catch (Exception e) {
            throw new StudentManagementException("Error occur while inserting student" +
                    " with name: " + requestStudentDto.getName(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ResponseStudentDto updateStudent(UUID studentId, RequestUpdateStudentDto requestUpdateStudentDto)
            throws StudentManagementException {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                student.get().setName(requestUpdateStudentDto.getName());
                student.get().setDob(requestUpdateStudentDto.getDob());
                Address address = addressMapper.requestDtoToEntity(requestUpdateStudentDto.getAddress());
                address.setId(student.get().getId());
                student.get().setAddress(address);
                address.setStudent(student.get());
                Student studentEntity = studentRepository.save(student.get());
                return studentMapper.entityToResponseDto(studentEntity);
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While " +
                    "updating student with id:" + studentId, e);
        }

        return null;
    }


    /**
     * {@inheritDoc}
     */
    public ResponseStudentDto getStudentById(UUID studentId) throws StudentManagementException {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                return studentMapper.entityToResponseDto(student.get());
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while get Student by id: " +
                    studentId, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<StudentDto> getAllStudents() throws StudentManagementException {
        try {
            List<StudentDto> students = new ArrayList<>();
            for (Student student : studentRepository.findAll()) {
                students.add(studentMapper.entityToStudentDto(student));
            }
            return students;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while get all students", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteStudentById(UUID studentId) throws StudentManagementException {
        try {
            Optional<Student> fetchedStudent = studentRepository.findById(studentId);
            if (fetchedStudent.isPresent()) {
                Student student = fetchedStudent.get();
                student.getGrade().getStudents().remove(student);
                student.getRoles().forEach(role -> role.getStudents().remove(student));
                student.setRoles(null);
                studentRepository.delete(student);
            }
            return fetchedStudent.isPresent();
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while deleting student by id: " + studentId, e);
        }
    }
}