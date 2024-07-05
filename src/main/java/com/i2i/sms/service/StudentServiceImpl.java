package com.i2i.sms.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.i2i.sms.dto.CreateRoleDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
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
     * <p>
     * Add student information to the database.
     * </p>
     *
     * @param requestStudentDto requested dto object from the user to add.
     * @return The Added student object or null if any exception occurs.
     * @throws StudentManagementException if an error occurs during student addition.
     */
    public ResponseStudentDto addStudent(RequestStudentDto requestStudentDto) throws StudentManagementException {
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
            Set<Role> roles = new HashSet<>();
            for (CreateRoleDto role : requestStudentDto.getRoles()) {
                roles.add(roleService.getRoleIfRoleExists(role.getRole()));
            }
            student.setRoles(roles);
            Student insertedStudent = studentRepository.save(student);
            return studentMapper.entityToResponseDto(insertedStudent);
        } catch (Exception e) {
            throw new StudentManagementException("Error occur while inserting student with name: " + requestStudentDto.getName(), e);
        }
    }

    /**
     * <p>
     * Searches for a student in the database by their Id.
     * </p>
     *
     * @param studentId ID of the student to search for.
     * @return The Student object corresponding to the given id, or null if not found.
     * @throws StudentManagementException if an error occurs while fetching the student.
     */
    public ResponseStudentDto getStudentById(int studentId) throws StudentManagementException {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if(student.isPresent()) {
                return studentMapper.entityToResponseDto(student.get());
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while get Student by id: " + studentId, e);
        }
    }

    /**
     * <p>
     * Retrieves a list of all students from the database.
     * </p>
     *
     * @return a list of all students in the database.
     * @throws StudentManagementException if an error occurs while fetching the students.
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
     * <p>
     * Deletes a student from the database by their ID.
     * </p>
     *
     * @param studentId the ID of the student to be deleted.
     * @return true if the student was found and deleted, false if the student was not found.
     * @throws StudentManagementException if an error occurs while deleting the student.
     */
    public boolean deleteStudentById(int studentId) throws StudentManagementException {
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