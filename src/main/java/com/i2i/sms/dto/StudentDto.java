package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Setter
@Getter
public class StudentDto {
    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
}
