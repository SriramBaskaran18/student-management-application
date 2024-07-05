package com.i2i.sms.service;

import java.util.*;

import com.i2i.sms.dto.*;
import com.i2i.sms.mapper.GradeMapper;
import com.i2i.sms.mapper.StudentMapper;
import com.i2i.sms.model.Student;
import com.i2i.sms.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * {@inheritDoc}
     */
    public Grade getGradeIfGradeExists(CreateGradeDto gradeDto) throws StudentManagementException {
        try {
            return gradeRepository.findByStandardAndSection(gradeDto.getStandard(), gradeDto.getSection());
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while checking isGrade exists", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Grade addGrade(CreateGradeDto gradeDto) throws StudentManagementException {
        try {
            Grade grade = gradeMapper.requestDtoToEntity(gradeDto);
            return gradeRepository.save(grade);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while " +
                    "inserting grade with standard: " + gradeDto.getStandard() +
                    "and section: " + gradeDto.getSection(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public GradeDto getGradeById(int gradeId) throws StudentManagementException {
        try {
            Optional<Grade> grade = gradeRepository.findById(gradeId);
            if (grade.isPresent()) {
                Set<StudentDto> students = new HashSet<>();
                for (Student student : grade.get().getStudents()) {
                    students.add(studentMapper.entityToStudentDto(student));
                }
                return gradeMapper.entityToGradeDto(grade.get(), students);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching grade by its id: " + gradeId, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ResponseGradeDto> getAllGrades() throws StudentManagementException {
        try {
            List<ResponseGradeDto> grades = new ArrayList<>();
            for (Grade grade : gradeRepository.findAll()) {
                grades.add(gradeMapper.entityToResponseDto(grade));
            }
            return grades;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching all students", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteGradeById(int gradeId) throws StudentManagementException {
        try {
            boolean isAvailable = gradeRepository.existsById(gradeId);
            if (isAvailable) {
                gradeRepository.deleteById(gradeId);
            }
            return isAvailable;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while deleting grade by its id: " + gradeId, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public GradeStudentsResponseDto getStudentsByGrade(int gradeId) throws StudentManagementException {
        try {
            Optional<Grade> grade = gradeRepository.findById(gradeId);
            if (grade.isPresent()) {
                Set<StudentDto> students = new HashSet<>();
                for (Student student : grade.get().getStudents()) {
                    students.add(studentMapper.entityToStudentDto(student));
                }
                return gradeMapper.entityToGradeStudentsResponseDto(students);
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching students with grade id:" + gradeId, e);
        }
    }
}