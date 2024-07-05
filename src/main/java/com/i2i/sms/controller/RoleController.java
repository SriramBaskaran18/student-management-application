package com.i2i.sms.controller;

import com.i2i.sms.dto.ResponseRoleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.service.RoleService;

@RestController
@RequestMapping("sms/api/v1/roles")
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    public RoleService roleService;

    /**
     * <p>
     * Retrieves the role with associated students by the given Id.
     * </p>
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getRoleById(@PathVariable("id") int roleId) {
        try {
            ResponseRoleDto role = roleService.getRoleById(roleId);
            if (null != role) {
                return ResponseEntity.ok(role);
            } else {
                logger.warn("No Role Exists");
                return new ResponseEntity<>("no role found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}