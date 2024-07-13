package com.i2i.sms.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.i2i.sms.dto.RequestRoleDto;
import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.dto.RoleDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.model.Role;

@Component
public class RoleMapper {

    /**
     * <p>
     * Maps a Role entity to a ResponseRoleDto object.
     * This method takes a Role entity as input and returns a ResponseRoleDto object
     * with the corresponding data. It uses custom mappers for students to
     * transform the data into the desired format.
     * </p>
     *
     * @param role the role entity to be mapped
     * @return a ResponseRoleDto object with the mapped data
     */
    public ResponseRoleDto entityToResponseDto(Role role, List<StudentDto> students) {
        return ResponseRoleDto.builder().id(role.getId()).
                role(role.getName()).
                students(students).build();
    }

    /**
     * <p>
     * Maps a Role entity to CreateRoleDto object.
     * This method takes a Role entity as input and returns a CreateRoleDto object
     * with the corresponding data. It uses custom mappers for students to
     * transform the data into the desired format.
     * </p>
     *
     * @param role the role entity to be mapped.
     * @return a CreateRoleDto object with the mapped data.
     */
    public RequestRoleDto entityToRoleDto(Role role) {
        return RequestRoleDto.builder().role(role.getName()).build();
    }
    /**
     * <p>
     * Maps a Role entity to RoleDto object.
     * This method takes a Role entity as input and returns a RoleDto object
     * with the corresponding data. It uses custom mappers for students to
     * transform the data into the desired format.
     * </p>
     *
     * @param role the role entity to be mapped.
     * @return a RoleDto object with the mapped data.
     */
    public RoleDto entityToRolesDto(Role role){
        return RoleDto.builder().id(role.getId()).roleName(role.getName()).build();
    }
}
