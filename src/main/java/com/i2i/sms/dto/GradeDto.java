package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * This class is responsible for managing the grade details that contains
 * grade id, standard, section, list of students{@link StudentDto}
 */
@Builder
@Setter
@Getter
public class GradeDto {
    private UUID id;
    private int standard;
    private String section;
    private List<StudentDto> students;
}
