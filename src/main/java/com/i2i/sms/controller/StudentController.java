package com.i2i.sms.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.RequestUpdateStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.service.StudentService;
import com.i2i.sms.utils.DateUtils;
import com.i2i.sms.utils.StringValidationUtil;

/**
 * <p>
 * Methods within this class handle user inputs, validate data,
 * and delegate tasks to respective services.
 * </p>
 */
@RestController
@RequestMapping("v1/students")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentService studentService;

    /**
     * <p>
     * get information from the user as dto object and creates a student.
     * </p>
     * @param requestStudentDto {@link RequestStudentDto}
     * @return {@link ResponseStudentDto}
     */
    @PostMapping
    public ResponseEntity<?> createStudent(@Valid
            @RequestBody RequestStudentDto requestStudentDto) {
        try {
            return new ResponseEntity<>(studentService.addStudent(requestStudentDto),
                    HttpStatus.CREATED);
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * updates student object with the given information.
     * </p>
     * @param studentId id of the student to update for.
     * @param requestUpdateStudentDto {@link RequestUpdateStudentDto}
     * @return {@link ResponseStudentDto}
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") UUID studentId,
            @RequestBody RequestUpdateStudentDto requestUpdateStudentDto) {
        try {
            if (!StringValidationUtil.isValidString(requestUpdateStudentDto.getName())) {
                return new ResponseEntity<>("Student name should be in a-z or" +
                        " A-Z format", HttpStatus.BAD_REQUEST);
            }
            if (!DateUtils.isValidDate(requestUpdateStudentDto.getDob())) {
                return new ResponseEntity<>("Could not process the given date" +
                        " is future date", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(studentService.updateStudent(studentId,
                    requestUpdateStudentDto), HttpStatus.OK);
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * Retrieves All students details.
     * </p>
     * @return list of studentDto{@link StudentDto}.
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * Retrieves the corresponding student details by their id.
     * </p>
     * @param studentId id of the student to search for.
     * @return {@link ResponseStudentDto}.
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") UUID studentId) {
        try {
            ResponseStudentDto student = studentService.getStudentById(studentId);
            if (!ObjectUtils.isEmpty(student)) {
                return ResponseEntity.ok(student);
            } else {
                return new ResponseEntity<>("No data Found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * deletes the corresponding student with their id.
     * </p>
     * @param studentId id of the student to delete for.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") UUID studentId) {
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}