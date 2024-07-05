package com.i2i.sms.service;

import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.mapper.RoleMapper;
import com.i2i.sms.mapper.StudentMapper;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;
import com.i2i.sms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public ResponseRoleDto getRoleById(int roleId) throws StudentManagementException {
        try {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                Set<StudentDto> students = new HashSet<>();
                for (Student student : role.get().getStudents()) {
                    students.add(studentMapper.entityToStudentDto(student));
                }
                return roleMapper.entityToResponseDto(role.get(), students);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching role by its id: " + roleId, e);
        }
    }
}
