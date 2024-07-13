package com.i2i.sms.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class is responsible for managing the requested grade details that contain the standard and section.
 * </p>
 */
@Builder
@Setter
@Getter
public class RequestGradeDto {
    @NotNull(message = "standard is mandatory")
    @Min(value = 1, message = "standard must be 1-12")
    @Max(value = 12, message = "standard must be 1-12")
    private int standard;
    @Size(min = 1, max = 1, message = "section must contain only one Alphabet")
    private String section;
}
