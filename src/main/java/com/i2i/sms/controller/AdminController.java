package com.i2i.sms.controller;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Admin;
import com.i2i.sms.service.AdminService;

import java.util.Scanner;

public class AdminController {
    public static Scanner scanner = new Scanner(System.in);
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
            System.out.println(e.getMessage());
            e.printStackTrace();
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
                System.out.println("Admin Not found to delete");
            }
        } catch (StudentManagementException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
