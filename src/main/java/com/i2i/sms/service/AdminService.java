package com.i2i.sms.service;

import java.util.List;

import com.i2i.sms.dao.AdminDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    /**
     * <p>
     * Add student information to the database.
     * </p>
     *
     * @param adminName     Name of the admin.
     * @param adminPassword password of the admin.
     * @return The Added admin object or null if any exception occurs.
     * @throws StudentManagementException if an error occurs during admin addition.
     */
    public Admin addAdmin(String adminName, String adminPassword) throws StudentManagementException {
        Admin admin = new Admin(adminName, adminPassword);
        return adminDao.addAdmin(admin);
    }

    /**
     * <p>
     * Deletes a admin from the database by their ID.
     * </p>
     *
     * @param adminId the ID of the admin to be deleted.
     * @return true if the admin was found and deleted, false if the admin was not found.
     * @throws StudentManagementException if an error occurs while deleting the admin.
     */
    public boolean deleteAdminById(int adminId) throws StudentManagementException {
        return adminDao.deleteAdminById(adminId);
    }

    /**
     * <p>
     * Retrieves a list of all admins from the database.
     * </p>
     *
     * @return a list of all admins in the database.
     * @throws StudentManagementException if an error occurs while fetching the admins.
     */
    public List<Admin> getAllAdmins() throws StudentManagementException {
        return adminDao.getAllAdmins();
    }

    /**
     * <p>
     * Retrieves a Admin object from the database if it exists based on the
     * given username and password.
     * </p>
     *
     * @param adminName the adminName to be searched for.
     * @param adminPass the adminPass of the admin to be searched for.
     * @return true if the admin exists, otherwise false.
     * @throws StudentManagementException if an error occurs while checking if the admin exists.
     */
    public boolean isAdminExists(String adminName, String adminPass) throws StudentManagementException {
        return adminDao.isAdminExists(adminName, adminPass);
    }
}
