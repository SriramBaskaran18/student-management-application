package com.i2i.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.dto.RequestGradeDto;
import com.i2i.sms.dto.GradeDto;
import com.i2i.sms.dto.ResponseGradeDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.mapper.GradeMapper;
import com.i2i.sms.mapper.StudentMapper;
import com.i2i.sms.model.Grade;
import com.i2i.sms.model.Student;
import com.i2i.sms.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private StudentMapper studentMapper;

    public Grade getGradeIfGradeExists(RequestGradeDto gradeDto) {
        try {
            return gradeRepository.findByStandardAndSection(gradeDto.getStandard(),
                    gradeDto.getSection());
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while checking isGrade exists", e);
        }
    }

    public Grade addGrade(RequestGradeDto gradeDto) {
        try {
            return gradeRepository.save(gradeMapper.requestDtoToEntity(gradeDto));
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while " +
                    "inserting grade with standard: " + gradeDto.getStandard() +
                    "and section: " + gradeDto.getSection(), e);
        }
    }

    public GradeDto getGradeById(UUID gradeId) {
        try {
            Optional<Grade> grade = gradeRepository.findById(gradeId);
            if (grade.isPresent()) {
                List<StudentDto> students = new ArrayList<>();
                grade.get().getStudents().forEach(student ->
                        students.add(studentMapper.entityToStudentDto(student)));
                return gradeMapper.entityToGradeDto(grade.get(), students);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching" +
                    " grade by its id: " + gradeId, e);
        }
    }

    public List<ResponseGradeDto> getAllGrades() {
        try {
            List<ResponseGradeDto> grades = new ArrayList<>();
            gradeRepository.findAll().forEach(grade ->
                    grades.add(gradeMapper.entityToResponseDto(grade)));
            return grades;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching all students", e);
        }
    }

    public boolean deleteGradeById(UUID gradeId) {
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

    public List<StudentDto> getStudentsByGrade(UUID gradeId) {
        try {
            Optional<Grade> grade = gradeRepository.findById(gradeId);
            if (grade.isPresent()) {
                List<StudentDto> students = new ArrayList<>();
                for (Student student : grade.get().getStudents()) {
                    students.add(studentMapper.entityToStudentDto(student));
                }
                return students;
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching" +
                    " students with grade id:" + gradeId, e);
        }
    }
}