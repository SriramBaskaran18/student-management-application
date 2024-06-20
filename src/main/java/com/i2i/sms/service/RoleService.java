package com.i2i.sms.service;

import com.i2i.sms.dao.RoleDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Role;

public class RoleService{
    
    private RoleDao roleDao = new RoleDao();

    /**
     * <p>
     * Retrieves a Role record from the database if it exists, based on the provided role name.
     * </p>
     * @param role 
     *         The name of the role to search for in the database.
     * @return A role object if the role exists, or null if it does not.
     * @throws StudentManagementException
     *         if an error occurs while adding the role.
     */
    public Role getRoleIfRoleExists(String roleName) throws StudentManagementException{
        Role role = roleDao.getRoleIfRoleExists(roleName);
        if (null == role) {
            role = new Role();
            role.setName(roleName);
            role = roleDao.addRole(role);
        }
        return role;
    }

    /**
     * <p>
     * Retireve role record by its id and also fetch the corresponding students of that role from the student table by using role Id.
     * </p>
     * @param roleId
     *         Id of the role to search for.  
     * @return Role containing data from the database or null.
     * @throws StudentManagementException
     *         if an error occurs while retrieving the role by its id.
     */  
    public Role getRoleById(int roleId) throws StudentManagementException {
        return roleDao.getRoleById(roleId);
    } 
}
