package com.i2i.sms.helper;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateManagement {
    private static final SessionFactory sessionFactory;

    static {
        Dotenv dotenv = Dotenv.load();

        Configuration configuration = new Configuration().configure();
        configuration.setProperty("hibernate.connection.url", dotenv.get("DATABASE_URL"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("DATABASE_USER_NAME"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("DATABASE_PASSWORD"));

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void rollBackTransaction(Transaction transaction) {
        if (null != transaction) {
            transaction.rollback();
        }
    }
}