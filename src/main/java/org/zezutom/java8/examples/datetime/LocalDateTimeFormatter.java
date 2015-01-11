package org.zezutom.java8.examples.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Formats the given date / time, assumes local time
 */
public class LocalDateTimeFormatter {

    /**
     *
     * @param date
     * @return yyyy-mm-dd, no offset
     */
    public String format(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     *
     * @param time
     * @return hh:mm:ss, no offset
     */
    public String format(LocalTime time) {
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     *
     * @param dateTime
     * @return yyyy-mm-dd hh:mm:ss, no offset
     */
    public String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
