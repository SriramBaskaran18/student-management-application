package com.i2i.sms.service;

import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;

public interface RoleService {
    Role getRoleIfRoleExists(String roleName) throws StudentManagementException;

    ResponseRoleDto getRoleById(int roleId) throws StudentManagementException;
}
