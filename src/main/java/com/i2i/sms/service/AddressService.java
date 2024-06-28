package com.i2i.sms.service;

import java.util.List;

import com.i2i.sms.dao.AddressDao;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
import com.i2i.sms.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

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
    public Address getAddressById(int addressId) throws StudentManagementException {
        return addressDao.getAddressById(addressId);
    }
}