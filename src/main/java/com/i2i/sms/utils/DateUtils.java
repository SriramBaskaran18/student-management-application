package com.i2i.sms.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public final class DateUtils {
    private DateUtils() {}

      /**
       * <p>
       *  To calculate the difference between given date and current date.
       * </p>
       * @param date 
       * Date for which difference need to be calculated and it is in "yyyy-MM-dd" format.
       * @return The Difference of dates in years,if given date is future date it will return the difference in negative.
       **/
    public static int calculateDateDifference(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(date, currentDate).getYears();
    }
 
    /**
     * <p>
     * Checks if a given date is a valid date.
     * </p>
     * @param date 
     *          The date to validate.
     * @return True if the date is valid, and it is in yyyy-MM-dd format, false otherwise.
     */
    public static boolean isValidDate(LocalDate date) {
        try {
            return LocalDate.now().isAfter(date);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}