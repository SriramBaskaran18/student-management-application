package com.i2i.sms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * This class is responsible for managing the requested student details that contains
 * student's name, date of birth, requested grade {@link RequestGradeDto},
 * requested address{@link RequestAddressDto},list of requested roles{@link RequestRoleDto}.
 * </p>
 */
@Getter
@Setter
@Builder
public class RequestStudentDto {
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "invalid format, name must between in a-z/A-z")
    private String name;
    @NotNull(message = "Date of birth is mandatory")
    private LocalDate dob;
    @Valid
    private RequestGradeDto grade;
    @Valid
    private RequestAddressDto address;
    @Valid
    private List<RequestRoleDto> roles;
}
