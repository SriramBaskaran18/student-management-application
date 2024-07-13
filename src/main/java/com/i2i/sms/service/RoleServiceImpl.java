package com.i2i.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.dto.RoleDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.mapper.RoleMapper;
import com.i2i.sms.mapper.StudentMapper;
import com.i2i.sms.model.Role;
import com.i2i.sms.repository.RoleRepository;
import org.springframework.util.ObjectUtils;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StudentMapper studentMapper;

    public List<RoleDto> getAllRoles() {
        try {
            List<RoleDto> roles = new ArrayList<>();
            roleRepository.findAll().forEach(role -> roles.add(roleMapper.entityToRolesDto(role)));
            return roles;
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred while fetching all roles", e);
        }
    }

    public Role getRoleIfRoleExists(String roleName) {
        try {
            Role role = roleRepository.findByName(roleName);
            if (ObjectUtils.isEmpty(role)) {
                role = new Role();
                role.setName(roleName);
                role = roleRepository.save(role);
            }
            return role;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while checking" +
                    " role exists or not with role name :" + roleName, e);
        }
    }

    public ResponseRoleDto getRoleById(UUID roleId) {
        try {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                List<StudentDto> students = new ArrayList<>();
                role.get().getStudents().forEach(student ->
                        students.add(studentMapper.entityToStudentDto(student)));
                return roleMapper.entityToResponseDto(role.get(), students);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while" +
                    "fetching role by its id: " + roleId, e);
        }
    }

    public List<StudentDto> getStudentsByRole(UUID roleId) {
        try {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                List<StudentDto> students = new ArrayList<>();
                role.get().getStudents().forEach(student ->
                        students.add(studentMapper.entityToStudentDto(student)));
                return students;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error Occurred while fetching" +
                    " corresponding students of the role with its id: " + roleId, e);
        }
    }
}
