package com.i2i.sms.service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;

import java.util.Optional;

public interface AddressService {
    Optional<Address> getAddressById(int addressId) throws StudentManagementException;
}
