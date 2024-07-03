package com.i2i.sms.controller;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
import com.i2i.sms.service.AddressService;

@RestController
@RequestMapping("sms/api/v1.0/addresses")
public class AddressController {
    public static Scanner scanner = new Scanner(System.in);
//    private final Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    public AddressService addressService;

    /**
     * <p>
     * Retrieves the Address with associated student by the given Id.
     * </p>
     */
    @GetMapping("find/{id}")
    public void getAddressById(@PathVariable("id") int addressId) {
        try {
            Optional<Address> address = addressService.getAddressById(addressId);
            if (address.isPresent()) {
                Address address1 = address.get();
                System.out.println(address + "\n" + "Student Id : " + address1.getStudent().getId() +
                        " Student Name:" + address1.getStudent().getName());
            } else {
                System.out.println("Address not Exists");
//                logger.warn("Address not Exists");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
//            logger.error(e.getMessage(), e);
        }
    }
}