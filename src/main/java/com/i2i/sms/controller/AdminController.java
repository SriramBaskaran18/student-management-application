package com.i2i.sms.controller;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Admin;
import com.i2i.sms.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AdminController {
    public static Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(AddressController.class);
    public AdminService adminService = new AdminService();

    /**
     * <p>
     * Gather information from the Admin like their name and password.
     * </p>
     */
    public void addAdmin() {
        try {
            System.out.println("Enter Admin Name :");
            String adminName = scanner.nextLine();
            System.out.println("Enter Admin Password :");
            String adminPassword = scanner.nextLine();
            System.out.println(adminService.addAdmin(adminName, adminPassword));
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Prompts User to enter the id of the admin to be removed,
     * it will display a success message if the specified admin removed or
     * else it will display a warning message with the corresponding admin id.
     * </p>
     */
    public void deleteAdminById() {
        try {
            System.out.println("Enter Admin id to delete :");
            int adminId = scanner.nextInt();
            if (adminService.deleteAdminById(adminId)) {
                System.out.println("Deleted Successfully");
            } else {
                logger.warn("Admin Not found With id: {} to delete",adminId);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Retrieves All the admin details and displays the admin information to the user.
     * </p>
     */
    public void getAllAdmins() {
        try {
            for (Admin admin : adminService.getAllAdmins()) {
                System.out.println(admin);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Retrieves a Admin object from the database if it exists based on the given username and password.
     * </p>
     * @param adminName
     *         the adminName to be searched for.
     * @param adminPass
     *         the adminPass of the admin to be searched for.
     * @return
     *         true if the admin exists, otherwise false.
     */
    public boolean isAdminExists(String adminName, String adminPass) {
        try {
            return adminService.isAdminExists(adminName, adminPass);
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}
