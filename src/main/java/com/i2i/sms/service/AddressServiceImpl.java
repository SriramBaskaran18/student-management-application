package com.i2i.sms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
import com.i2i.sms.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

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
    public Optional<Address> getAddressById(int addressId) throws StudentManagementException {
        try {
            return addressRepository.findById(addressId);
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching address by its id: " + addressId, e);
        }
    }
}