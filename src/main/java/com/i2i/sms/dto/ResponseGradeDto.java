package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * This class is responsible for managing the response grade details that contains
 * grade id, standard, section.
 */
@Getter
@Setter
@Builder
public class ResponseGradeDto {
    private UUID id;
    private int standard;
    private String section;
}
