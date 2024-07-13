package com.i2i.sms.service;

import java.util.List;
import java.util.UUID;

import com.i2i.sms.dto.RequestGradeDto;
import com.i2i.sms.dto.GradeDto;
import com.i2i.sms.dto.ResponseGradeDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;


public interface GradeService {

    /**
     * <p>
     * Checks if a grade exists for a given standard and section.
     * </p>
     *
     * @param gradeDto {@link GradeDto}
     * @return the grade if the grade found (or) null object if no grade found.
     * @throws StudentManagementException if a database access error occurs while check the grade
     *                                    exists or not.
     */
    Grade getGradeIfGradeExists(RequestGradeDto gradeDto);

    /**
     * <p>
     * Adds a new Grade object to the database.
     * </p>
     *
     * @param  gradeDto {@link GradeDto}
     * @return The inserted grade record or null.
     * @throws StudentManagementException if an error occurs while inserting the grade.
     */
    Grade addGrade(RequestGradeDto gradeDto);

    /**
     * <p>
     * Retrieves a Grade object from the database based on the provided grade ID.
     * </p>
     *
     * @param gradeId the ID of the Grade to be retrieved.
     * @return the Grade object with the specified ID if it presents otherwise null.
     * @throws StudentManagementException if an error occurs while fetching the grade by its id.
     */
    GradeDto getGradeById(UUID gradeId);

    /**
     * <p>
     * Deletes a Grade from the database based on its ID.
     * </p>
     *
     * @param gradeId the ID of the Grade to be deleted.
     * @return true if the Grade was successfully deleted,false otherwise (if the Grade not found).
     * @throws StudentManagementException if an error occurs while deleting the Grade by its id.
     */
    boolean deleteGradeById(UUID gradeId) throws StudentManagementException;

    /**
     * <p>
     * Retrieves all grade data from the database.
     * </p>
     *
     * @return List of Grade objects containing data from the database.
     * @throws StudentManagementException if an error occurs while fetching all grades.
     */
    List<ResponseGradeDto> getAllGrades();

    /**
     * <p>
     * Fetches a list of students associated with a specified grade.
     * </p>
     * @param gradeId The ID of the grade for which students need to be retrieved.
     * @return List of StudentDto containing the list of students for the given grade.
     * @throws StudentManagementException If an error occurs while fetching the students.
     */
    List<StudentDto> getStudentsByGrade(UUID gradeId);
}
