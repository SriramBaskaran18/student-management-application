package com.i2i.sms.dao;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Role;

@Repository
public class RoleDao {
    Logger logger = LoggerFactory.getLogger(RoleDao.class);

    /**
     * <p>
     * Retrieves a Role record from the database if it exists, based on the provided role name.
     * </p>
     *
     * @param roleName The name of the role to search for in the database.
     * @return A Role object if the role exists, or null if it does not.
     * @throws StudentManagementException If an error occurs while retrieving role from the database.
     */
    public Role getRoleIfRoleExists(String roleName) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started to fetch the role: {} if exists", roleName);
            Query<Role> query = session.createQuery("FROM Role WHERE name = :roleName"
                    , Role.class);
            query.setParameter("roleName", roleName);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred While checking if Role exists"
                    + " or not with role name :" + roleName, e);
        }
    }

    /**
     * <p>
     * Adds a new Role to the database.
     * </p>
     *
     * @param role the Role object to be added
     * @return the added Role object
     * @throws StudentManagementException if an error occurs during the insertion process
     */
    public Role addRole(Role role) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Process started to add role: {} in the database", role.getName());
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            logger.info("Role Added successfully with Role: {}", role);
            return role;
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error occurred while inserting Role" + role, e);
        }
    }

    /**
     * <p>
     * Fetch role record by its id and also fetch the
     * corresponding students of that role from the student table by using role Id.
     * </p>
     *
     * @param roleId id of the role to search for.
     * @return Role containing data from the database or null.
     * @throws StudentManagementException if a database access error occurs while retrieving
     *                                    the role by its id.
     */
    public Role getRoleById(int roleId) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input roleId: {} to search the role in the database", roleId);
            Role role = session.get(Role.class, roleId);
            if (role != null) {
                Hibernate.initialize(role.getStudents());
                logger.info("Role fetched successfully with Role id: {}", roleId);
            }
            return role;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while getting role by its Id :"
                    + roleId, e);
        }
    }
}