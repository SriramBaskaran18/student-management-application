package com.i2i.sms.service;

import java.util.List;
import java.util.Optional;

import com.i2i.sms.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    /**
     * <p>
     * Checks if a grade exists for a given standard and section.
     * </p>
     *
     * @param standard The standard of grade to search for.
     * @param section  The section within the grade to search for.
     * @return the grade if the grade found (or) null object if no grade found.
     * @throws StudentManagementException if a database access error occurs while check the grade
     *                                    exists or not.
     */
    public Grade getGradeIfGradeExists(int standard, String section) throws StudentManagementException {
        try {
            return gradeRepository.findByStandardAndSection(standard, section);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while checking isGrade exists", e);
        }
    }

    /**
     * <p>
     * Adds a new Grade object to the database.
     * </p>
     *
     * @param std     standard for the grade to be inserted.
     * @param section section for the grade to be inserted.
     * @return The inserted grade record or null.
     * @throws StudentManagementException if an error occurs while inserting the grade.
     */
    public Grade addGrade(int std, String section) throws StudentManagementException {
        try {
            Grade grade = new Grade(std, section);
            return gradeRepository.save(grade);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while " +
                    "inserting grade with standard: " + std + "and section: " + section, e);
        }
    }

    /**
     * <p>
     * Retrieves a Grade object from the database based on the provided grade ID.
     * </p>
     *
     * @param gradeId the ID of the Grade to be retrieved.
     * @return the Grade object with the specified ID.
     * @throws StudentManagementException if an error occurs while fetching the grade by its id.
     */
    public Optional<Grade> getGradeById(int gradeId) throws StudentManagementException {
        try {
            return gradeRepository.findById(gradeId);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching grade by its id: " + gradeId, e);
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
    public List<Grade> getAllGrades() throws StudentManagementException {
        try {
            return gradeRepository.findAll();
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching all students", e);
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
}