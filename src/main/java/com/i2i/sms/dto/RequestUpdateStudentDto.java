package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <p>
 * This class is responsible for managing the requested student details that contains
 * student's name, date of birth, requested address{@link RequestAddressDto}.
 * </p>
 */
@Getter
@Setter
@Builder
public class RequestUpdateStudentDto {
    private String name;
    private LocalDate dob;
    private RequestAddressDto address;
}
