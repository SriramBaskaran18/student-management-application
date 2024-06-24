package com.i2i.sms.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.sms.dao.GradeDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;

public class GradeService {
 
    private GradeDao gradeDao = new GradeDao();

    /**
     * <p>
     * Checks if a grade exists for a given standard and section.
     * </p>
     * @param std
     *         The standard of grade to search for.
     * @param section
     *         The section within the grade to search for.
     * @return the grade if the grade found (or) null object if no grade found.
     * @throws StudentManagementException
     *         if a database access error occurs while check the grade exists or not.      
     */
    public Grade getGradeIfGradeExists(int std, String section) throws StudentManagementException {
        return gradeDao.getGradeIfGradeExists(std, section);
    }

    /**
     * <p>
     * Adds a new Grade object to the database.
     * </p>
     * @param std
     *         standard for the grade to be inserted.
     * @param section
     *         section for the grade to be inserted.
     * @return The inserted grade record or null.
     * @throws StudentManagementException
     *         if an error occurs while inserting the grade.
     */
    public Grade addGrade(int std, String section) throws StudentManagementException {
        Grade grade = new Grade(std, section);
        return gradeDao.addGrade(grade);
    }

    /**
     * <p>
     * Retrieves a Grade object from the database based on the provided grade ID.
     * </p>
     * @param gradeId 
     *         the ID of the Grade to be retrieved.
     * @return the Grade object with the specified ID.
     * @throws StudentManagementException 
     *         if an error occurs while fetching the grade by its Id.
     */
    public Grade getGradeById(int gradeId) throws StudentManagementException {
        return gradeDao.getGradeById(gradeId);
    }
 
    /**
     * <p>
     * Retrieves all grade data from the database.
     * </p>
     * @return List of Grade objects containing data from the database.
     * @throws StudentManagementException
     *         if an error occurs while fetching all grades.
     */
    public List<Grade> getAllGrades() throws StudentManagementException {
        return gradeDao.getAllGrades();
    } 

    /**
     * <p>
     * Deletes a Grade from the database based on its ID.
     * </p>
     * @param gradeId 
     *         the ID of the Grade to be deleted.
     * @return true if the Grade was successfully deleted, false otherwise (if the Grade not found).
     * @throws StudentManagementException 
     *         if an error occurs while deleting the Grade by its Id.
     */
    public boolean deleteGradeById(int gradeId) throws StudentManagementException {
        return gradeDao.deleteGradeById(gradeId);
    } 
}