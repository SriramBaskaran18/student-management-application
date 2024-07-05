package com.i2i.sms.service;

import com.i2i.sms.dto.AddressDto;
import com.i2i.sms.mapper.AddressMapper;
import com.i2i.sms.mapper.StudentMapper;
import com.i2i.sms.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.repository.AddressRepository;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private StudentMapper studentMapper;

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
    public AddressDto getAddressById(int addressId) throws StudentManagementException {
        try {
            Optional<Address> address = addressRepository.findById(addressId);
            if(address.isPresent()) {
                return addressMapper.entityToAddressDto(address.get(), studentMapper.entityToStudentDto(address.get().getStudent()));
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching address by its id: " + addressId, e);
        }
    }
}