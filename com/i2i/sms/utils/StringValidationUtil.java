package com.i2i.sms.utils;

import java.util.regex.Matcher;

public final class StringValidationUtil {

    private StringValidationUtil() {}

    /**
     * <p>
     * Checks whether a given string contains only alphabetic characters or not.
     * </p>
     * @param str 
     *         The string to be validated.
     *         whether it may contain alphabets or numnbers. 
     * @return True if the string contains only alphabetic characters, false otherwise.
     */
    public static boolean isValidString(String str) {
        return str.matches("^[a-zA-Z]*$");
    }
}