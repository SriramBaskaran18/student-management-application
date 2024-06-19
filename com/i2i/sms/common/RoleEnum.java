package com.i2i.sms.common;

public enum RoleEnum {
    CLASS_REPRESENTATIVE ("Class_Representative"),
    BOARD_INCHARGE ("Board_Incharge"),
    CABINET_INCHARGE ("Cabinet_Incharge");
    private String role;
    
    RoleEnum(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}