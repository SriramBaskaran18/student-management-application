package com.i2i.sms.service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;
import com.i2i.sms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * <p>
     * Retrieves a Role record from the database if it exists,based on the provided role name.
     * </p>
     *
     * @param roleName The name of the role to search for in the database.
     * @return A role object if the role exists, or null if it does not.
     * @throws StudentManagementException if an error occurs while adding the role.
     */
    public Role getRoleIfRoleExists(String roleName) throws StudentManagementException {
        try {
            Role role = roleRepository.findByName(roleName);
            if (null == role) {
                role = new Role();
                role.setName(roleName);
                role = roleRepository.save(role);
            }
            return role;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while checking role exists or not with role name :" + roleName, e);
        }
    }

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
    public Optional<Role> getRoleById(int roleId) throws StudentManagementException {
        try {
            return roleRepository.findById(roleId);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching role by its id: " + roleId, e);
        }
    }
}
