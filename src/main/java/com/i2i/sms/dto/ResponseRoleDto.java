package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class ResponseRoleDto {
    private int id;
    private String role;
    private Set<StudentDto> students;
}
