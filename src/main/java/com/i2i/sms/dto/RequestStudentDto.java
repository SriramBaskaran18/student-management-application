package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class RequestStudentDto {
    private String name;
    private LocalDate dob;
    private CreateGradeDto grade;
    private CreateAddressDto address;
    private Set<CreateRoleDto> roles;
}
