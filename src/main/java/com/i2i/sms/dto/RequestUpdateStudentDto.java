package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
public class RequestUpdateStudentDto {
    private String name;
    private LocalDate dob;
    private CreateAddressDto address;
}
