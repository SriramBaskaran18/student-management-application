package com.i2i.sms.dao;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdminDao {
    /**
     * <p>
     * Adds a admin to the database.
     * </p>
     * @param admin
     *         the admin object to be added to the database.
     * @throws StudentManagementException
     *         if an error occurs while adding the admin.
     */
    public Admin addAdmin(Admin admin) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
            return admin;
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error Occurred while adding admin", e);
        }
    }

    /**
     * <p>
     * Deletes a admin from the database by their ID.
     * </p>
     * @param adminId
     *         the ID of the admin to be deleted.
     * @return true if the admin was found and deleted, false if the admin was not found.
     * @throws StudentManagementException
     *         if an error occurs while deleting the admin.
     */
    public boolean deleteAdminById(int adminId) throws StudentManagementException{
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, adminId);
            if (null != admin) {
                session.delete(admin);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error occurred while deleting Admin with its id:" + adminId, e);
        }
        return false;
    }

    /**
     * <p>
     * Retrieves a list of all admins from the database.
     * </p>
     * @return a list of all admins in the database.
     * @throws StudentManagementException
     *         if an error occurs while fetching the admins.
     */
    public List<Admin> getAllAdmins() throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            return session.createQuery("FROM Admin", Admin.class).list();
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching all admins", e);
        }
    }
}
