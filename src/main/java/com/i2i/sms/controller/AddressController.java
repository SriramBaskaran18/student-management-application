package com.i2i.sms.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sms.dto.AddressDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.service.AddressService;


@RestController
@RequestMapping("v1/addresses")
public class AddressController {
    private final Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    public AddressService addressService;

    /**
     * <p>
     * Retrieves the Address with associated student by the given Id.
     * </p>
     * @param addressId id of the address to search for.
     * @return {@link AddressDto}
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getAddressById(@PathVariable("id") UUID addressId) {
        try {
            AddressDto address = addressService.getAddressById(addressId);
            if (ObjectUtils.isEmpty(address)) {
                return ResponseEntity.ok(address);
            } else {
                logger.warn("Address not Exists");
                return new ResponseEntity<>("No address found", HttpStatus.NOT_FOUND);
            }
        } catch (StudentManagementException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}