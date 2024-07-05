package com.i2i.sms.service;

import com.i2i.sms.dto.AddressDto;
import com.i2i.sms.exception.StudentManagementException;

public interface AddressService {

    /**
     * <p>
     * Retrieve Address record by its id and also fetch the
     * corresponding student of that Address from the student table by using Address Id.
     * </p>
     *
     * @param addressId id of the address to search for.
     * @return Address containing data from the database or null.
     * @throws StudentManagementException if an error occurs while retrieving the address by its id.
     */
    AddressDto getAddressById(int addressId) throws StudentManagementException;
}