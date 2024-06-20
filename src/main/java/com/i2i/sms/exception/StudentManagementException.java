package com.i2i.sms.exception;

/**
 * <p>
 * The StudentManagementException class represents an exception specific to database operations.
 * It extends the standard Java Exception class.
 * </p>
 */
public class StudentManagementException extends Exception {

    /**
     * <p>
     * Constructs a new StudentManagementException with the specified error message.
     * </p>
     * @param errorMessage 
     *        A string containing the error message.
     */
    public StudentManagementException (String errorMessage) {
        super(errorMessage);
    }

    /**
     * <p>
     * Constructs a new StudentManagementException with the specified error message and a throwable cause.
     * </p>
     * @param errorMessage 
     *        A string containing the error message.
     * @param e 
     *        The throwable cause of the exception.
     */
    public StudentManagementException (String errorMessage, Throwable e) {
        super(errorMessage, e);
    }    
}