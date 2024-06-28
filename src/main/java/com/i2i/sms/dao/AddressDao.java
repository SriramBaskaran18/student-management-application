package com.i2i.sms.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Address;

@Repository
public class AddressDao {
    Logger logger = LoggerFactory.getLogger(AddressDao.class);

    /**
     * <p>
     * Retrieve Address record by its id and also fetch the
     * corresponding student of that Address from the student table by using Address Id.
     * </p>
     *
     * @param addressId id of the address to search for.
     * @return Address containing data from the database or null.
     * @throws StudentManagementException if a database access error occurs while
     *                                    retrieving the address by its id.
     */
    public Address getAddressById(int addressId) throws StudentManagementException {
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            logger.debug("Received input addressId: {} to search the address in the database",
                    addressId);
            Address address = session.get(Address.class, addressId);
            if (address != null) {
                Hibernate.initialize(address.getStudent());
                logger.info("Address fetched successfully with Address id: {}", addressId);
            }
            return address;
        } catch (Exception e) {
            throw new StudentManagementException("Error occurred while fetching address by its id :"
                    + addressId, e);
        }
    }
}