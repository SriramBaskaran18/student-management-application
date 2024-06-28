package com.i2i.sms.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Grade;

@Repository
public class GradeDao {
    Logger logger = LoggerFactory.getLogger(GradeDao.class);

    /**
     * <p>
     * Retrieves a Grade object from the database if it exists based on the given
     * standard and section.
     * </p>
     *
     * @param standard the standard to be searched for.
     * @param section  the section of the grade to be searched for.
     * @return the Grade object if it exists, otherwise null object.
     * @throws StudentManagementException if an error occurs while checking if the grade exists.
     */
    public Grade getGradeIfGradeExists(int standard, String section) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started to fetch the grade of standard: {} and"
                    + " section: {} if exists", standard, section);
            Query<Grade> query = session.createQuery("FROM Grade WHERE standard = :standard and"
                    + " section = :section", Grade.class);
            query.setParameter("standard", standard);
            query.setParameter("section", section);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new StudentManagementException("error occurred while checking grade exists"
                    + " or not with standard :" + standard + " and section :" + section, e);
        }
    }

    /**
     * <p>
     * Adds a new Grade object to the database.
     * This method initiates a transaction, saves the Grade object,
     * commits the transaction, and handles any exceptions that may occur.
     * </p>
     *
     * @param grade the Grade object with standard and section to be added to the database.
     * @return The inserted grade record or null.
     * @throws StudentManagementException if an error occurs while inserting the grade.
     */
    public Grade addGrade(Grade grade) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started to add standard: {} and"
                    + " section: {} in the database", grade.getStd(), grade.getSection());
            transaction = session.beginTransaction();
            session.save(grade);
            transaction.commit();
            logger.info("Grade Added successfully with Grade: {}", grade);
            return grade;
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error occurred while inserting grade", e);
        }
    }

    /**
     * <p>
     * Retrieves a Grade object from the database based on the provided grade ID.
     * </p>
     *
     * @param gradeId the ID of the Grade to be retrieved.
     * @return the Grade object with the specified ID.
     * @throws StudentManagementException if an error occurs while fetching the grade.
     */
    public Grade getGradeById(int gradeId) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input gradeId: {} to search the grade in the database",
                    gradeId);
            return session.get(Grade.class, gradeId);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching grade with id :"
                    + gradeId, e);
        }
    }

    /**
     * <p>
     * Retrieves all Grade objects from the database.
     * </p>
     *
     * @return A List containing all Grade objects retrieved from the database.
     * @throws StudentManagementException if an error occurs while fetching all grades.
     */
    public List<Grade> getAllGrades() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started to fetch all the grades from the database");
            return session.createQuery("FROM Grade", Grade.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While Fetching All Grades", e);
        }
    }

    /**
     * <p>
     * Deletes a Grade from the database based on its ID.
     * </p>
     *
     * @param gradeId the ID of the Grade to be deleted.
     * @return true if the Grade was successfully deleted,
     * false otherwise (if the Grade not found).
     * @throws StudentManagementException if an error occurs while deleting the Grade.
     */
    public boolean deleteGradeById(int gradeId) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input gradeId: {} to remove the grade in the database"
                    , gradeId);
            transaction = session.beginTransaction();
            Grade grade = session.get(Grade.class, gradeId);
            if (grade != null) {
                session.delete(grade);
                transaction.commit();
                logger.info("Grade Deleted successfully with grade Id: {}", gradeId);
                return true;
            }
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error occurred while deleting grade by its id :"
                    + gradeId, e);
        }
        return false;
    }
}

