package com.i2i.sms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
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
    private GradeServiceImpl gradeServiceImpl;
    @Autowired
    private StudentRepository studentRepository;

    /**
     * <p>
     * Add student information to the database.
     * </p>
     *
     * @param name    Name of the student.
     * @param dob     D.O.B of the student.
     * @param std     standard of the student to check the grade exists or not.
     * @param section section of the student to check the grade exists or not.
     * @param address address of the student.
     * @param roles   set of roles picked by the student.
     * @return The Added student object or null if any exception occurs.
     * @throws StudentManagementException if an error occurs during student addition.
     */
    public Student addStudent(String name, LocalDate dob, int std,
                              String section, Address address,
                              Set<Role> roles) throws StudentManagementException {
        try {
            Grade grade = gradeServiceImpl.getGradeIfGradeExists(std, section);
            if (null == grade) {
                grade = gradeServiceImpl.addGrade(std, section);
            }
            Student student = new Student();
            student.setName(name);
            student.setDob(dob);
            student.setAddress(address);
            address.setStudent(student);
            student.setGrade(grade);
            student.setRoles(roles);
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new StudentManagementException("Error occur while inserting student with name: " + name, e);
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
    public Optional<Student> getStudentById(int studentId) throws StudentManagementException {
        try {
            return studentRepository.findById(studentId);
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
    public List<Student> getAllStudents() throws StudentManagementException {
        try {
            return studentRepository.findAll();
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