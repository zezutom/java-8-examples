package org.zezutom.java8.examples.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates fluency of the Java 8 Date-Time API
 */
public class DateTimeWizard {

    public LocalDate lastDayOfWeekInMonth(int year, int month, DayOfWeek dayOfWeek) {
        return LocalDate.now()
                .withYear(year)
                .withMonth(month)
                .with(TemporalAdjusters.lastInMonth(dayOfWeek));
    }

    public List<LocalDate> schedule(int year, int month, DayOfWeek... daysOfWeek) {
        LocalDate baseDate = LocalDate.now().withYear(year).withMonth(month);
        LocalDate startDate = baseDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = baseDate.with(TemporalAdjusters.lastDayOfMonth());

        WeeklySchedule schedule = new WeeklySchedule(daysOfWeek);
        List<LocalDate> scheduledDays = new ArrayList<>();

        while (!startDate.isAfter(endDate)) {
            if (startDate.query(schedule)) scheduledDays.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return scheduledDays;
    }

    public List<LocalDate> oddEvenWeekSchedule(int year, int month, DayOfWeek[] daysOfOddWeek, DayOfWeek[] daysOfEvenWeek) {
        LocalDate baseDate = LocalDate.now().withYear(year).withMonth(month);
        LocalDate startDate = baseDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = baseDate.with(TemporalAdjusters.lastDayOfMonth());

        WeeklySchedule oddWeekSchedule = new WeeklySchedule(daysOfOddWeek);
        WeeklySchedule evenWeekSchedule = new WeeklySchedule(daysOfEvenWeek);
        List<LocalDate> scheduledDays = new ArrayList<>();

        while (!startDate.isAfter(endDate)) {
            boolean isEvenWeek = startDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH) % 2 == 0;
            WeeklySchedule schedule = (isEvenWeek) ? evenWeekSchedule : oddWeekSchedule;
            if (startDate.query(schedule)) scheduledDays.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return scheduledDays;

    }

    public List<LocalDate> workDays(int year, int month) {
        LocalDate baseDate = LocalDate.now().withYear(year).withMonth(month);
        LocalDate startDate = baseDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = baseDate.with(TemporalAdjusters.lastDayOfMonth());

        List<LocalDate> workDays = new ArrayList<>();

        while (!startDate.isAfter(endDate)) {
            if (startDate.query(WorkDays::isWorkDay)) workDays.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return workDays;
    }

    private class WeeklySchedule implements TemporalQuery<Boolean> {

        private DayOfWeek[] daysOfWeek;

        WeeklySchedule(DayOfWeek[] daysOfWeek) {
            this.daysOfWeek = daysOfWeek;
        }

        @Override
        public Boolean queryFrom(TemporalAccessor date) {
            boolean scheduled = false;
            int day = date.get(ChronoField.DAY_OF_WEEK);

            for (DayOfWeek dayOfWeek : daysOfWeek) {
                if (dayOfWeek.get(ChronoField.DAY_OF_WEEK) == day) {
                    scheduled = true;
                    break;
                }
            }
            return scheduled;
        }
    }

    static class WorkDays {

        static boolean isWorkDay(TemporalAccessor date) {
            int day = date.get(ChronoField.DAY_OF_WEEK);
            return day >= 1 && day <= 5;
        }
    }
}
