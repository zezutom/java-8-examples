package org.zezutom.java8.examples.datetime;

import org.junit.Before;
import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests the fluency of the Date-Time API
 *
 * @see org.zezutom.java8.examples.datetime.DateTimeWizard
 */
public class DateTimeWizardTest {

    private DateTimeWizard dateTimeWizard;

    @Before
    public void setUp() {
        dateTimeWizard = new DateTimeWizard();
    }

    @Test
    public void lastDayOfWeekInMonth() {
        // Last Wednesday in February
        assertThat(format(dateTimeWizard.lastDayOfWeekInMonth(2015, 2, DayOfWeek.WEDNESDAY)), is("2015-02-25"));

        // Last Monday in June
        assertThat(format(dateTimeWizard.lastDayOfWeekInMonth(2015, 6, DayOfWeek.MONDAY)), is("2015-06-29"));

        // Leap year - last day in February 2016 falls on a Monday
        assertThat(format(dateTimeWizard.lastDayOfWeekInMonth(2016, 2, DayOfWeek.MONDAY)), is("2016-02-29"));
    }

    @Test
    public void schedule() {
        assertThat(format(dateTimeWizard.schedule(2015, 4, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY)),
                is("2 7 9 14 16 21 23 28 30"));

        assertThat(format(dateTimeWizard.schedule(2015, 10, DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY)),
                is("5 7 12 14 19 21 26 28"));
    }

    @Test
    public void oddEvenWeekSchedule() {
        assertThat(format(dateTimeWizard.oddEvenWeekSchedule(2015, 2,
                new DayOfWeek[] {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY},
                new DayOfWeek[] {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY})),
                is("2 4 10 12 16 18 24 26"));
    }

    @Test
    public void workDays() {
        assertThat(format(dateTimeWizard.workDays(2015, 1)),
                is("1 2 5 6 7 8 9 12 13 14 15 16 19 20 21 22 23 26 27 28 29 30"));
        assertThat(format(dateTimeWizard.workDays(2015, 7)),
                is("1 2 3 6 7 8 9 10 13 14 15 16 17 20 21 22 23 24 27 28 29 30 31"));
    }

    private String format(LocalDate date) {
        return format(date, null);
    }

    private String format(LocalDate date, String pattern) {
        DateTimeFormatter formatter = (pattern == null || pattern.isEmpty()) ?
                DateTimeFormatter.ISO_LOCAL_DATE : DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    private String format(List<LocalDate> dates) {
        String pattern = "d";
        StringBuilder sb = new StringBuilder();
        for (LocalDate date : dates) {
            sb.append(format(date, pattern)).append(" ");
        }
        return sb.toString().trim();
    }

}
