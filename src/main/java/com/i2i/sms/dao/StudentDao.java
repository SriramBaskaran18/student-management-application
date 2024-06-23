package com.i2i.sms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Address;
import com.i2i.sms.model.Student;

public class StudentDao {

    /**
     * <p>
     * Adds a student to the database. 
     * </p>
     * @param student 
     *         the student object to be added to the database.
     * @throws StudentManagementException 
     *         if an error occurs while adding the student.
     */
     public Student addStudent(Student student) throws StudentManagementException {
        Transaction transaction = null; 
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error Occurred While Adding student: " + student, e);
        }
    }


    /**
     * <p>
     * Retrieves a student from the database by their ID.
     * </p>
     * @param studentId 
     *         the ID of the student to be retrieved.
     * @return the student object with the specified ID, or null if no student is found.
     * @throws StudentManagementException 
     *         if an error occurs while fetching the student.
     */
    public Student getStudentById(int studentId) throws StudentManagementException {
        Student student = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            student = session.get(Student.class, studentId);
            if (student != null) {
                Hibernate.initialize(student.getGrade());
                Hibernate.initialize(student.getAddress());
                Hibernate.initialize(student.getRoles());
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error fetching student with ID: " + studentId, e);
        }
        return student;
    }

    /**
     * <p>
     * Retrieves a list of all students from the database.
     * </p>
     * @return a list of all students in the database.
     * @throws StudentManagementException 
     *         if an error occurs while fetching the students.
     */
    public List<Student> getAllStudents() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error occured while fetching all students", e);
        }
    } 

    /**
     * <p>
     * Deletes a student from the database by their ID.
     * </p>
     * @param studentId 
     *         the ID of the student to be deleted.
     * @return true if the student was found and deleted, false if the student was not found.
     * @throws StudentManagementException 
     *         if an error occurs while deleting the student.
     */
    public boolean deleteStudentById(int studentId) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                student.getGrade().getStudents().remove(student);
                student.getRoles().forEach(role -> role.getStudents().remove(student));
                student.setRoles(null);
                Address address = session.createQuery("from Address where student.id = :studentId", Address.class)
                        .setParameter("studentId", studentId)
                        .uniqueResult();
                if (address != null) {
                    address.setStudent(null);
                    session.update(address);
                }
                session.delete(student);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error occured while deleting student by its id :" + studentId, e);
        }
        return false;
    }
}