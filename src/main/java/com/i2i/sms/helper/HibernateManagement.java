package com.i2i.sms.helper;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateManagement {
    private static final SessionFactory sessionFactory;

    static {
        Dotenv dotenv = Dotenv.load();
        String dbUrl = dotenv.get("URL");
        String dbUser = dotenv.get("USER_NAME");
        String dbPassword = dotenv.get("PASSWORD");

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUser);
        configuration.setProperty("hibernate.connection.password", dbPassword);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void rollBackTransaction(Transaction transaction) {
        if (null != transaction || transaction.isActive()) {
            transaction.rollback();
        }
    }
}