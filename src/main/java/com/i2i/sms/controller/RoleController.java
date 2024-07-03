package com.i2i.sms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;
import com.i2i.sms.service.RoleService;

@RestController
@RequestMapping("sms/api/v1.0/roles")
public class RoleController {
//    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    public RoleService roleService;

    /**
     * <p>
     * Retrieves the role with associated students by the given Id.
     * </p>
     */
    @GetMapping("find/{id}")
    public void getRoleById(@PathVariable("id") int roleId) {
        try {
            Optional<Role> fetchedRole = roleService.getRoleById(roleId);
            if (fetchedRole.isPresent()) {
                Role role = fetchedRole.get();
                System.out.println(role);
                System.out.println(role.getStudents());
            } else {
                System.out.println("No Role Exists");
//                logger.warn("No Role Exists");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
//            logger.error(e.getMessage(), e);
        }
    }
}