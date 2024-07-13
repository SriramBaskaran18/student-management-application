package com.i2i.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.dto.RequestRoleDto;
import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.RequestUpdateStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.mapper.AddressMapper;
import com.i2i.sms.mapper.StudentMapper;
import com.i2i.sms.model.Address;
import com.i2i.sms.model.Grade;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;
import com.i2i.sms.repository.StudentRepository;
import org.springframework.util.ObjectUtils;

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

    public ResponseStudentDto addStudent(RequestStudentDto requestStudentDto) {
        try {
            Grade grade = gradeService.getGradeIfGradeExists(requestStudentDto.getGrade());
            if (ObjectUtils.isEmpty(grade)) {
                grade = gradeService.addGrade(requestStudentDto.getGrade());
            }
            Student student = studentMapper.requestDtoToEntity(requestStudentDto);
            Address address = addressMapper.requestDtoToEntity(requestStudentDto.getAddress());
            student.setAddress(address);
            address.setStudent(student);
            student.setGrade(grade);
            List<Role> roles = new ArrayList<>();
            for (RequestRoleDto role : requestStudentDto.getRoles()) {
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

    public ResponseStudentDto updateStudent(
            UUID studentId, RequestUpdateStudentDto requestUpdateStudentDto) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                student.get().setName(requestUpdateStudentDto.getName());
                student.get().setDob(requestUpdateStudentDto.getDob());
                Address address = addressMapper.requestDtoToEntity(
                        requestUpdateStudentDto.getAddress());
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

    public ResponseStudentDto getStudentById(UUID studentId) {
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

    public List<StudentDto> getAllStudents() {
        List<StudentDto> students = new ArrayList<>();
        try {
            List<Student> allStudents = studentRepository.findAll();
            if (!allStudents.isEmpty()) {
                allStudents.forEach(student ->
                        students.add(studentMapper.entityToStudentDto(student)));
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while get all students", e);
        }
        return students;
    }

    public boolean deleteStudentById(UUID studentId) {
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
            throw new StudentManagementException("Error occurred while" +
                    " deleting student by id: " + studentId, e);
        }
    }
}