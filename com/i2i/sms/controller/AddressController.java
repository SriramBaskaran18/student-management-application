package com.i2i.sms.controller;

import java.util.Scanner;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Address;
import com.i2i.sms.service.AddressService;

public class AddressController {
    public static Scanner scanner = new Scanner(System.in);
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
            System.out.println("" + address + "\n" +"Student Id : "+ address.getStudent().getId() + " Student Name:" + address.getStudent().getName());
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}