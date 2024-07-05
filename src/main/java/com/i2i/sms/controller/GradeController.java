package com.i2i.sms.controller;

import java.util.List;

import com.i2i.sms.dto.GradeDto;
import com.i2i.sms.dto.GradeStudentsResponseDto;
import com.i2i.sms.dto.ResponseGradeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.service.GradeService;

@RestController
@RequestMapping("sms/api/v1/grades")
public class GradeController {
    private final Logger logger = LoggerFactory.getLogger(GradeController.class);
    @Autowired
    private GradeService gradeService;

    @GetMapping
    public ResponseEntity<?> getAllGrades() {
        try {
            List<ResponseGradeDto> grades = gradeService.getAllGrades();
            if (!grades.isEmpty()) {
                return ResponseEntity.ok(grades);
            } else {
                return new ResponseEntity<>("No student found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * retrieves the specific grade with associated students.
     * </p>
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getGradeById(@PathVariable("id") int gradeId) {
        try {
            GradeDto grade = gradeService.getGradeById(gradeId);
            if (null != grade) {
                return ResponseEntity.ok(grade);
            } else {
                return new ResponseEntity<>("no data found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * deletes the corresponding grade with its id.
     * </p>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGradeById(@PathVariable("id") int gradeId) {
        try {
            boolean isGradeDelete = gradeService.deleteGradeById(gradeId);
            if (isGradeDelete) {
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            } else {
                logger.warn("Grade with this Id: {} not found to delete", gradeId);
                return new ResponseEntity<>("no data found to delete", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * Fetches a list of students associated with a specified grade.
     * </p>
     */
    @GetMapping("{id}/students")
    public ResponseEntity<?> getStudentsByGrade(@PathVariable("id") int gradeId) {
        try {
            GradeStudentsResponseDto students = gradeService.getStudentsByGrade(gradeId);
            if (null != students) {
                return new ResponseEntity<>(students, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No students found with the gradeId:" + gradeId, HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
