package com.i2i.sms.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;
import com.i2i.sms.service.RoleService;

@Controller
public class RoleController {
    public static Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    public RoleService roleService;

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
                logger.warn("No Role Exists");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }
}