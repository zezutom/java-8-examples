package org.zezutom.java8.examples.datetime;

import org.junit.Before;
import org.junit.Test;
import java.time.Month;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests reference to points in local time (date, time, date and time)
 *
 * @see org.zezutom.java8.examples.datetime.PointInLocalTime
 */
public class PointInLocalTimeTest {

    private PointInLocalTime pointInLocalTime;

    @Before
    public void setUp() {
        pointInLocalTime = new PointInLocalTime();
    }

    @Test
    public void getDate() {
        assertThat(pointInLocalTime.getDate(2015, 1, 11), is("2015-01-11"));
    }

    @Test
    public void getDateUsingMonthDataType() {
        assertThat(pointInLocalTime.getDate(2015, Month.JANUARY, 11), is("2015-01-11"));
    }

    @Test
    public void getTime() {
        assertThat(pointInLocalTime.getTime(10, 30), is("10:30:00"));
    }

    @Test
    public void getTimeUsingSecond() {
        assertThat(pointInLocalTime.getTime(10, 30, 45), is("10:30:45"));
    }

    @Test
    public void getDateTime() {
        assertThat(pointInLocalTime.getDateTime(2015, 1, 11, 10, 30, 45), is("2015-01-11T10:30:45"));
    }

    @Test
    public void getDateTimeUsingMonthDataType() {
        assertThat(pointInLocalTime.getDateTime(2015, Month.JANUARY, 11, 10, 30, 45), is("2015-01-11T10:30:45"));
    }
}
