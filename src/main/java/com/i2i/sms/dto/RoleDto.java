package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * This class is responsible for managing the role details that contains
 * role id, role name.
 */
@Builder
@Getter
@Setter
public class RoleDto {
    private UUID id;
    private String roleName;
}
