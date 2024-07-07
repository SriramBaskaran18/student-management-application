package com.i2i.sms.service;

import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    /**
     * <p>
     * Retrieves a Role record from the database if it exists,based on the provided role name.
     * </p>
     *
     * @param roleName The name of the role to search for in the database.
     * @return A role object if the role exists, or null if it does not.
     * @throws StudentManagementException if an error occurs while adding the role.
     */
    Role getRoleIfRoleExists(String roleName) throws StudentManagementException;

    /**
     * <p>
     * Retrieve role record by its id and also fetch the corresponding students
     * of that role from the student table by using role Id.
     * </p>
     *
     * @param roleId id of the role to search for.
     * @return Role containing data from the database or null.
     * @throws StudentManagementException if an error occurs while retrieving the role by its id.
     */
    ResponseRoleDto getRoleById(UUID roleId) throws StudentManagementException;

    /**
     * <p>
     * Fetches a list of students associated with a specified role.
     * </p>
     * @param roleId The ID of the role for which students need to be retrieved.
     * @return set of studentDto containing the list of students for the given grade.
     * @throws StudentManagementException If an error occurs while fetching the students.
     */
    List<StudentDto> getStudentsByRole(UUID roleId) throws StudentManagementException;
}
