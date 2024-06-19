package com.i2i.sms.controller;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.sms.helper.HibernateManagement;
import com.i2i.sms.model.Student;

/**
 * <p>
 * This class is responsible for add a student into the database.
 * </p>
 */ 
public class StudentController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Name :");
        String name = scanner.next();
        System.out.println("Enter Your D.O.B in the format(yyyy-mm-dd) :");
        String dob = scanner.next();
        System.out.println("Enter Your City :");
        String city = scanner.next();
        Student student = new Student(name, dob, city);
        System.out.println("Student record saved successfully: " + addStudent(student));
    }

    /**
     * <p>
     * Adds student record to the database.
     * </p>
     * @param student 
     *        The Student object to be inserted.
     * @return The added Student object.
     */
    public static Student addStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateManagement.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            HibernateManagement.rollBackTransaction(transaction);
            e.printStackTrace();
        }
        return student;
    }
}
