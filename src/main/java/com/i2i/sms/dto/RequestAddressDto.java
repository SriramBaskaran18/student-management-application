package com.i2i.sms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This class is responsible for managing the requested address details
 * that contain the doorNumber, street, city, state, zipcode, mobileNumber.
 */
@Getter
@Setter
@Builder
public class RequestAddressDto {

    @NotBlank(message = "Door number is mandatory")
    private String doorNumber;
    @NotBlank(message = "street is mandatory")
    private String street;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "state is mandatory")
    private String state;
//    @NotNull(message = "zipcode is mandatory")
//    @Pattern(regexp = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$")
    private int zipcode;
    @NotBlank(message = "mobile number is mandatory")
    @Pattern(regexp = "(0|91)?[6-9][0-9]{9}")
    private String mobileNumber;
}
