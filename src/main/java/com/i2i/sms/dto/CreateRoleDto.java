package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateRoleDto {
    private String role;

    public CreateRoleDto() {
    }

    public CreateRoleDto(String role) {
        this.role = role;
    }
}
