package com.i2i.sms.service;

import java.util.*;

import com.i2i.sms.dto.CreateGradeDto;
import com.i2i.sms.dto.GradeDto;
import com.i2i.sms.dto.ResponseGradeDto;
import com.i2i.sms.dto.StudentDto;
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
     * <p>
     * Checks if a grade exists for a given standard and section.
     * </p>
     *
     * @param gradeDto grade object with standard and section to search for.
     * @return the grade if the grade found (or) null object if no grade found.
     * @throws StudentManagementException if a database access error occurs while check the grade
     *                                    exists or not.
     */
    public Grade getGradeIfGradeExists(CreateGradeDto gradeDto) throws StudentManagementException {
        try {
            return gradeRepository.findByStandardAndSection(gradeDto.getStandard(), gradeDto.getSection());
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while checking isGrade exists", e);
        }
    }

    /**
     * <p>
     * Adds a new Grade object to the database.
     * </p>
     *
     * @param  gradeDto grade to be inserted.
     * @return The inserted grade record or null.
     * @throws StudentManagementException if an error occurs while inserting the grade.
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
     * <p>
     * Retrieves a Grade object from the database based on the provided grade ID.
     * </p>
     *
     * @param gradeId the ID of the Grade to be retrieved.
     * @return the Grade object with the specified ID if it presents otherwise null.
     * @throws StudentManagementException if an error occurs while fetching the grade by its id.
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
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching grade by its id: " + gradeId, e);
        }
    }

    /**
     * <p>
     * Deletes a Grade from the database based on its ID.
     * </p>
     *
     * @param gradeId the ID of the Grade to be deleted.
     * @return true if the Grade was successfully deleted,false otherwise (if the Grade not found).
     * @throws StudentManagementException if an error occurs while deleting the Grade by its id.
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
     * <p>
     * Retrieves all grade data from the database.
     * </p>
     *
     * @return List of Grade objects containing data from the database.
     * @throws StudentManagementException if an error occurs while fetching all grades.
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
}