package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * This class is responsible for managing the student details that contains
 * student id, student name, student dob, student age.
 */
@Builder
@Setter
@Getter
public class StudentDto {
    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
}
