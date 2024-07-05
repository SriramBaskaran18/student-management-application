package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Setter
@Getter
public class GradeDto {
    private int id;
    private int standard;
    private String section;
    private Set<StudentDto> students;
}
