package com.i2i.sms.mapper;

import com.i2i.sms.dto.CreateRoleDto;
import com.i2i.sms.dto.RequestStudentDto;
import com.i2i.sms.dto.ResponseStudentDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;
import com.i2i.sms.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class StudentMapper {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * <p>
     * Maps a Student entity to a ResponseStudentDto object.
     * This method takes a Student entity as input and returns a ResponseStudentDto object
     * with the corresponding data. It uses custom mappers for addresses and grades to
     * transform the data into the desired format.
     * </p>
     *
     * @param student the Student entity to be mapped
     * @return a ResponseStudentDto object with the mapped data
     */
    public ResponseStudentDto entityToResponseDto(Student student) {
        Set<CreateRoleDto> roles = new HashSet<>();
        for (Role role : student.getRoles()) {
            roles.add(roleMapper.entityToRoleDto(role));
        }
        return ResponseStudentDto.builder().id(student.getId()).name(student.getName()).
                dob(student.getDob()).age(DateUtils.calculateDateDifference(student.getDob())).
                address(addressMapper.entityToResponseDto(student.getAddress())).
                grade(gradeMapper.entityToResponseDto(student.getGrade())).roles(roles).
                build();
    }

    /**
     * <p>
     * Maps a requestStudentDto entity to a Student object.
     * This method takes a requestStudentDto entity as input and returns a Student object
     * with the corresponding data.
     * </p>
     *
     * @param requestStudentDto the requestStudentDto to be mapped
     * @return a Student object with the mapped data
     */
    public Student requestDtoToEntity(RequestStudentDto requestStudentDto) {
        return Student.builder().name(requestStudentDto.getName()).
                dob(requestStudentDto.getDob()).build();
    }

    /**
     * <p>
     * Maps a Student entity to a StudentDto object.
     * This method takes a Student entity as input and returns a StudentDto object
     * with the corresponding data.
     * </p>
     *
     * @param student the Student to be mapped
     * @return a StudentDto object with the mapped data
     */
    public StudentDto entityToStudentDto(Student student) {
        return StudentDto.builder().id(student.getId()).name(student.getName()).
                dob(student.getDob()).age(DateUtils.calculateDateDifference(student.getDob())).
                build();
    }
}
