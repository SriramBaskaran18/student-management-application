package com.i2i.sms.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.dto.RoleDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.service.RoleService;

@RestController
@RequestMapping("v1/roles")
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    public RoleService roleService;

    /**
     * <p>
     * Retrieves a list of roles.
     * </p>
     * @return list of roles{@link RoleDto}
     */
    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        try {
            List<RoleDto> roles = roleService.getAllRoles();
            if (roles.isEmpty()) {
                return new ResponseEntity<>("no role found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(roles, HttpStatus.OK);

            }

        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * Retrieves the role with associated students by the given Id.
     * </p>
     * @param roleId id of the role search for.
     * @return {@link ResponseRoleDto}
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getRoleById(@PathVariable("id") UUID roleId) {
        try {
            ResponseRoleDto role = roleService.getRoleById(roleId);
            if (ObjectUtils.isEmpty(role)) {
                return ResponseEntity.ok(role);
            } else {
                logger.warn("No Role Exists");
                return new ResponseEntity<>("no role found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * <p>
     * Fetches a list of students associated with a specified role.
     * </p>
     * @param roleId id of the role to search associated students.
     * @return list of {@link com.i2i.sms.dto.StudentDto}
     */
    @GetMapping("{id}/students")
    public ResponseEntity<?> getStudentsByRole(@PathVariable("id") UUID roleId) {
        try {
            return new ResponseEntity<>(roleService.getStudentsByRole(roleId), HttpStatus.OK);
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}