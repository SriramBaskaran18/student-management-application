package com.i2i.sms.service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;

import java.util.Optional;


public interface RoleService {
    Role getRoleIfRoleExists(String roleName) throws StudentManagementException;

    Optional<Role> getRoleById(int roleId) throws StudentManagementException;
}
