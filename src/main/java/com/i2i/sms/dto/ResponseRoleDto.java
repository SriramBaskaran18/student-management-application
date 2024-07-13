package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * This class is responsible for managing the response student details that contains
 * role id, role, list of students {@link StudentDto}.
 */
@Builder
@Getter
@Setter
public class ResponseRoleDto {
    private UUID id;
    private String role;
    private List<StudentDto> students;
}
