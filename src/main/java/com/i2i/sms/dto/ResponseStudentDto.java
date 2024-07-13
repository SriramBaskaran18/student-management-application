package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * This class is responsible for managing the response student details that contains
 * student's name, date of birth, age, response address{@link ResponseAddressDto},
 * response grade {@link ResponseGradeDto}, response roles{@link ResponseRoleDto}.
 * </p>
 */
@Getter
@Setter
@Builder
public class ResponseStudentDto {
    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
    private ResponseAddressDto address;
    private ResponseGradeDto grade;
    private List<RequestRoleDto> roles;
}
