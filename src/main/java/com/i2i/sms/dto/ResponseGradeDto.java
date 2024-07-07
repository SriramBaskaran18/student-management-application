package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ResponseGradeDto {
    private UUID id;
    private int standard;
    private String section;
}
