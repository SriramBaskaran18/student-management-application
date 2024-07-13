package com.i2i.sms.mapper;

import org.springframework.stereotype.Component;

import com.i2i.sms.dto.AddressDto;
import com.i2i.sms.dto.RequestAddressDto;
import com.i2i.sms.dto.ResponseAddressDto;
import com.i2i.sms.dto.StudentDto;
import com.i2i.sms.model.Address;

@Component
public class AddressMapper {

    /**
     * <p>
     * Maps a Address entity to a ResponseAddressDto object.
     * This method takes a Address entity as input and returns a ResponseAddressDto object
     * with the corresponding data.
     * </p>
     * @param address the address entity to be mapped
     * @return a ResponseAddressDto object with the mapped data
     */
    public ResponseAddressDto entityToResponseDto(Address address) {
        return ResponseAddressDto.builder().id(address.getId()).
                doorNumber(address.getDoorNumber()).street(address.getStreet()).
                city(address.getCity()).state(address.getState()).
                zipcode(address.getZipcode()).mobileNumber(address.getMobileNumber()).build();
    }

    /**
     * <p>
     * Maps a CreateAddressDto entity to a Address object.
     * This method takes a CreateAddressDto entity as input and returns a Address object
     * with the corresponding data.
     * </p>
     * @param requestAddressDto the createAddressDto entity to be mapped
     * @return an Address object with the mapped data
     */
    public Address requestDtoToEntity(RequestAddressDto requestAddressDto) {
        return Address.builder().doorNumber(requestAddressDto.getDoorNumber()).
                street(requestAddressDto.getStreet()).city(requestAddressDto.getCity()).
                state(requestAddressDto.getState()).zipcode(requestAddressDto.getZipcode()).
                mobileNumber(requestAddressDto.getMobileNumber()).build();
    }

    /**
     * <p>
     * Maps a Address entity with its corresponding student to a AddressDto object.
     * This method takes a Address entity with its corresponding student as input and returns a AddressDto object
     * with the corresponding data.
     * </p>
     * @param address the address entity to be mapped
     * @param student the corresponding student of the address to be mapped.
     * @return an AddressDto object with the mapped data
     */
    public AddressDto entityToAddressDto(Address address, StudentDto student) {
        return AddressDto.builder().id(address.getId()).
                doorNumber(address.getDoorNumber()).street(address.getStreet()).
                city(address.getCity()).state(address.getState()).
                zipcode(address.getZipcode()).
                mobileNumber(address.getMobileNumber()).student(student).build();
    }
}
