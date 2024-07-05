package com.i2i.sms.mapper;

import com.i2i.sms.dto.ResponseRoleDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleMapper {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * <p>
     * Maps a Role entity to a ResponseRoleDto object.
     * This method takes a Role entity as input and returns a ResponseRoleDto object
     * with the corresponding data. It uses custom mappers for students to
     * transform the data into the desired format.
     * </p>
     * @param role the role entity to be mapped
     * @return a ResponseRoleDto object with the mapped data
     */
    public ResponseRoleDto entityToResponseDto(Role role){
        Set<StudentDto> students = new HashSet<>();
        for(Student student : role.getStudents()){
            students.add(studentMapper.entityToStudentDto(student));
        }
        return ResponseRoleDto.builder().id(role.getId()).
                role(role.getName()).
                students(students).build();
    }
}
