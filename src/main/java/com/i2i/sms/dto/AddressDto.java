package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class AddressDto {
    private UUID id;
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private String mobileNumber;
    private StudentDto student;
}
