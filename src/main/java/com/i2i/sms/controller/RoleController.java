package com.i2i.sms.controller;

import java.util.Scanner;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;
import com.i2i.sms.service.RoleService;

public class RoleController {
    public static Scanner scanner = new Scanner(System.in);
    public RoleService roleService = new RoleService();

    /**
     * <p>
     * Retrieves the role with associated students by the given Id.
     * </p>
     */
    public void getRoleById() {
        try {
            System.out.println("Enter Role Id to Search Role with Associated Students :");
            int roleId = scanner.nextInt();
            Role role = roleService.getRoleById(roleId);
            if (null != role) {
                System.out.println(role);
                System.out.println(role.getStudents());
            } else {
                System.out.println("No Role Exists");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}