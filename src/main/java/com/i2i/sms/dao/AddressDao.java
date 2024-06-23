package com.i2i.sms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Address;

public class AddressDao {

    /**
     * <p>
     * Retrieve Address record by its id and also fetch the
     * corresponding student of that Address from the student table by using Address Id.
     * </p>
     *
     * @param addressId Id of the address to search for.
     * @return Address containing data from the database or null.
     * @throws StudentManagementException if a database access error occurs while retrieving the address by its id.
     */
    public Address getAddressById(int addressId) throws StudentManagementException {
        Address address = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            address = session.get(Address.class, addressId);
            if (address != null) {
                Hibernate.initialize(address.getStudent());
            }
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching address by its id :" + addressId, e);
        }
        return address;
    }
}