package com.i2i.sms.service;

import java.util.List;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Admin;
import com.i2i.sms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

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
        try {
            Admin admin = new Admin(adminName, adminPassword);
            return adminRepository.save(admin);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while adding admin with name: " + adminName, e);
        }
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
        try {
            boolean isAvailable = adminRepository.existsById(adminId);
            if (isAvailable) {
                adminRepository.deleteById(adminId);
            }
            return isAvailable;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while delete student by its id: " + adminId, e);
        }
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
        try {
            return adminRepository.findAll();
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching all admins", e);
        }
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
        try {
            return adminRepository.findAdminByAdminNameAndAdminPassword(adminName, adminPass);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while checking isAdmin exists", e);
        }
    }
}
