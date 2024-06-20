package com.i2i.sms.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Role;

public class RoleDao {

    /**
     * <p>
     * Retrieves a Role record from the database if it exists, based on the provided role name.
     * </p>
     * @param roleName 
     *         The name of the role to search for in the database.
     * @return A Role object if the role exists, or null if it does not.
     * @throws StudentManagementException 
     *         If an error occurs while retreiving role from the database.
     */
    public Role getRoleIfRoleExists(String roleName) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            Query<Role> query = session.createQuery("FROM Role WHERE name = :roleName", Role.class);
            query.setParameter("roleName", roleName);
            Role role = query.uniqueResult();
            return role;
        } catch (Exception e) {
            throw new StudentManagementException("Error Ocurred While checking if Role exists or not with role name :"+ roleName, e);
        }
    }

    public Role addRole(Role role) throws StudentManagementException {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(role);
            return role;
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            throw new StudentManagementException("Error Occured While Inserting Role"+ role, e);
        }
    }

    /**
     * <p>
     * Fetch role record by its id and also fetch the corresponding students of that role from the student table by using role Id.
     * </p>
     * @param roleId
     *         Id of the role to search for.  
     * @return Role containing data from the database or null.
     * @throws StudentManagementException
     *         if a database access error occurs while retrieving the role by its id.
     */ 
    public Role getRoleById(int roleId) throws StudentManagementException {
        Role role = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            role = session.get(Role.class, roleId);
        } catch (Exception e) {
            throw new StudentManagementException("Error Ocurred while get role by its Id :"+ roleId, e);
        }
        return role;
    }
}