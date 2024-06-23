package com.i2i.sms.service;

import java.util.List;
import java.util.Set;

import com.i2i.sms.dao.StudentDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
import com.i2i.sms.model.Grade;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;

/**
 * <p>
 * It interacts with the database like adding, fetching, searching, and deleting student records.
 * </p>
 */
public class StudentService {

    private GradeService gradeService = new GradeService();
    private StudentDao studentDao = new StudentDao(); 

    /**
     * <p>
     * Add student information to the database.
     * </p>
     * @param name
     *         Name of the student.
     * @param dob
     *         D.O.B of the student.
     * @param std
     *         standard of the student to check the grade exists or not.
     * @param section 
     *         section of the student to check the grade exists or not.
     * @param address 
     *         address of the student.
     * @param roles 
     *         set of roles picked by the student.
     * @return The Added student object or null if any exception occurs.
     * @throws StudentManagementException 
     *         if an error occurs during student addition.
     */
    public Student addStudent(String name, String dob, int std, String section, Address address, Set<Role> roles) throws StudentManagementException {
        Grade grade = gradeService.getGradeIfGradeExists(std, section);
        if (null == grade) {
            grade = gradeService.addGrade(std, section);
        } 
        Student student = new Student();
        student.setName(name);
        student.setDob(dob);
        student.setAddress(address);
        address.setStudent(student);
        student.setGrade(grade);
        student.setRoles(roles);
        return studentDao.addStudent(student);        
    }  

    /**
     * <p>
     * Searches for a student in the database by their Id.
     * </p>
     * @param searchId
     *         ID of the student to search for.
     * @return The Student object corresponding to the given Id, or null if not found.
     * @throws StudentManagementException 
     *         if an error occurs while fetching the student.
     */
    public Student getStudentById(int studentId) throws StudentManagementException {
        return studentDao.getStudentById(studentId);
    }

    /**
     * <p>
     * Retrieves a list of all students from the database.
     * </p>
     * @return a list of all students in the database.
     * @throws StudentManagementException 
     *         if an error occurs while fetching the students.
     */
    public List<Student> getAllStudents() throws StudentManagementException {
        return studentDao.getAllStudents();
    }
    
    /**
     * <p>
     * Deletes a student from the database by their ID.
     * </p>
     * @param studentId 
     *         the ID of the student to be deleted.
     * @return true if the student was found and deleted, false if the student was not found.
     * @throws StudentManagementException 
     *         if an error occurs while deleting the student.
     */
    public boolean deleteStudentById(int studentId) throws StudentManagementException {
        return studentDao.deleteStudentById(studentId);
    }
}