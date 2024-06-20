package com.i2i.sms.helper ;

import org.hibernate.cfg.Configuration ;
import org.hibernate.HibernateException ;
import org.hibernate.SessionFactory ;
import org.hibernate.Transaction;

import com.i2i.sms.exception.StudentManagementException ;

public class HibernateManagement {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public static void rollBackTransaction(Transaction transaction) {
        if (null != transaction) {
            transaction.rollback();
        }
    }
}