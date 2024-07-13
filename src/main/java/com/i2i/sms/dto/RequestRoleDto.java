package com.i2i.sms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is responsible for managing the requested Role details
 * that contain the role selected by the student.
 */
@Builder
@Getter
@Setter
public class RequestRoleDto {
    @NotBlank(message = "role name is mandatory")
    private String role;

    public RequestRoleDto() {
    }

    public RequestRoleDto(String role) {
        this.role = role;
    }
}
