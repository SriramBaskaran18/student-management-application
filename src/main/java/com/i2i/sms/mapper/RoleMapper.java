package com.i2i.sms.mapper;

import com.i2i.sms.dto.CreateRoleDto;
import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public CreateRoleDto entityToRoleDto(Role role) {
        return CreateRoleDto.builder().role(role.getName()).build();
    }
}
