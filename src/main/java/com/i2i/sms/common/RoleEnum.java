package com.i2i.sms.common;

public enum RoleEnum {
    CLASS_REPRESENTATIVE ("Class Representative"),
    BOARD_INCHARGE ("Board Incharge"),
    CABINET_INCHARGE ("Cabinet Incharge");
    private String role;
    
    RoleEnum(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}