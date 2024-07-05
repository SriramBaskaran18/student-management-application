package com.i2i.sms.service;

import com.i2i.sms.dto.AddressDto;
import com.i2i.sms.exception.StudentManagementException;

public interface AddressService {
    AddressDto getAddressById(int addressId) throws StudentManagementException;
}