package com.i2i.sms.controller;

import java.util.Scanner;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
import com.i2i.sms.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressController {
    public static Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(AddressController.class);
    public AddressService addressService = new AddressService();

    /**
     * <p>
     * Retrieves the Address with associated student by the given Id.
     * </p>
     */
    public void getAddressById() {
        try {
            System.out.println("Enter AddressId to Search Specific Address : ");
            int addressId = scanner.nextInt();
            Address address = addressService.getAddressById(addressId);
            if (null != address) {
                System.out.println(address + "\n" + "Student Id : " + address.getStudent().getId() +
                        " Student Name:" + address.getStudent().getName());
            } else {
                System.out.println("Address not Exists");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }
}