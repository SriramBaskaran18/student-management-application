package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class StudentDto {
    private int id;
    private String name;
    private LocalDate dob;
    private int age;
}
