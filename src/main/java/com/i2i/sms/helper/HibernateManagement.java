package com.i2i.sms.helper;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
* <p>
* This class is responsible for creating and managing the Hibernate SessionFactory.
* </p>
*/
public class HibernateManagement {
    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    /**
     * <p>
     * This method provides the singleton SessionFactory instance.
     * </p>
     * @return The Hibernate SessionFactory object.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * <p>
     * Rolls back the given transaction if it is not null.
     * </p>
     * @param transaction 
     *        The Hibernate Transaction object to be rolled back.
     */
    public static void rollBackTransaction(Transaction transaction) {
        try {
            if (null != transaction) {
                transaction.rollback();
            }
        } catch(Exception e) {
            System.err.println("\n Error occured while rollbacking the transaction");
        }
    }
}