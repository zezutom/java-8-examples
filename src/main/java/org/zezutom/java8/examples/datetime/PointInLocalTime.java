package org.zezutom.java8.examples.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Shows how to get local date / time. Results are presented using the basic ISO format.
 */
public class PointInLocalTime {

    private LocalDateTimeFormatter formatter = new LocalDateTimeFormatter();

    public String getDate(int year, int month, int day) {
        return formatter.format(LocalDate.of(year, month, day));
    }

    public String getDate(int year, Month month, int day) {
        return formatter.format(LocalDate.of(year, month, day));
    }

    public String getTime(int hour, int minute) {
        return formatter.format(LocalTime.of(hour, minute));
    }

    public String getTime(int hour, int minute, int second) {
        return formatter.format(LocalTime.of(hour, minute, second));
    }

    public String getDateTime(int year, int month, int day, int hour, int minute, int second) {
        return formatter.format(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    public String getDateTime(int year, Month month, int day, int hour, int minute, int second) {
        return formatter.format(LocalDateTime.of(year, month, day, hour, minute, second));
    }
}
