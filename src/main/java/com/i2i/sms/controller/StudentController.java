package com.i2i.sms.controller;

import java.util.List;

import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.utils.DateUtils;
import com.i2i.sms.utils.StringValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.service.StudentService;

/**
 * <p>
 * Methods within this class handle user inputs, validate data,
 * and delegate tasks to respective services.
 * It also contains methods for displaying student data.
 * </p>
 */
@RestController
@RequestMapping("sms/api/v1/students")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentService studentService;

    /**
     * <p>
     * get information from the user as dto object and creates a student.
     * </p>
     */
    @PostMapping
    public ResponseEntity<?> createStudent(
            @RequestBody RequestStudentDto requestStudentDto) {
        try {
            if (!StringValidationUtil.isValidString(requestStudentDto.getName())) {
                return new ResponseEntity<>("Student name should be in a-z or" +
                        " A-Z format", HttpStatus.BAD_REQUEST);
            }
            if (!DateUtils.isValidDate(requestStudentDto.getDob())) {
                return new ResponseEntity<>("Could not process the given date" +
                        " is future date", HttpStatus.BAD_REQUEST);
            }
            if (!StringValidationUtil.isValidString(requestStudentDto.getGrade().getSection())
                    && requestStudentDto.getGrade().getSection().length() != 1) {
                return new ResponseEntity<>("Could not process the given section is either" +
                        " not a string or more than one character", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(studentService.addStudent(requestStudentDto),
                    HttpStatus.CREATED);
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int studentId, @RequestBody RequestStudentDto requestStudentDto) {
        try {
            return new ResponseEntity<>(studentService.updateStudent(studentId, requestStudentDto), HttpStatus.OK);
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * Retrieves All the student details and displays the student information to the user.
     * </p>
     */
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        try {
            List<StudentDto> students = studentService.getAllStudents();
            if (!students.isEmpty()) {
                return ResponseEntity.ok(students);
            } else {
                return new ResponseEntity<>("no student found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * Retrieves the corresponding student details by their id.
     * </p>
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int studentId) {
        try {
            ResponseStudentDto student = studentService.getStudentById(studentId);
            if (null != student) {
                return ResponseEntity.ok(student);
            } else {
                return new ResponseEntity<>("No data Found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * deletes the corresponding student with their id.
     * </p>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") int studentId) {
        try {
            boolean isStudentDelete = studentService.deleteStudentById(studentId);
            if (isStudentDelete) {
                return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("Student Id: {} not found to delete", studentId);
                return new ResponseEntity<>("No student found to delete", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}