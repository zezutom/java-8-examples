package org.zezutom.java8.examples.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows how easy it is to get to a relative point in time
 */
public class PaydayCalculator {

    private LocalDateTimeFormatter formatter = new LocalDateTimeFormatter();

    /**
     * The pay happens every Friday in a given month
     *
     * @param year      current year
     * @param month     current month
     * @return
     */
    public String weeklyPayInIreland(int year, int month) {
        LocalDate baseDate = nowInIreland().withYear(year).withMonth(month);
        LocalDate currentDate = baseDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
        LocalDate endDate = baseDate.with(TemporalAdjusters.lastDayOfMonth());

        StringBuilder sb = new StringBuilder();

        while (!currentDate.isAfter(endDate)) {
            // add the found day to the list
            sb.append(currentDate.format(DateTimeFormatter.ofPattern("d"))).append(" ");

            // jump to the next Friday
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return sb.toString().trim();
    }

    public String monthlyPayInIreland(int year, int month) {
        return formatter.format(nowInIreland()
                .withYear(year)
                .withMonth(month)
                .with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
    }
    /**
     * Every 25th or the nearest preceding business day,
     * if the 25th happens to fall on a weekend (or bank holiday: ignored in this example)
     *
     * @param year      current year
     * @param month     current month
     * @return
     */
    public String paydayInSweden(int year, int month) {
        return formatter.format(calculatePaydayInSweden(year, month));
    }

    private LocalDate calculatePaydayInSweden(int year, int month) {
        LocalDate payday = nowInSweden().withYear(year).withMonth(month).withDayOfMonth(25);

        switch(payday.getDayOfWeek()) {
            case SATURDAY:
                payday = payday.minusDays(1);
                break;
            case SUNDAY:
                payday = payday.minusDays(2);
                break;
        }
        return payday;
    }

    /**
     * Lists all dates (yyyy-mm-dd) where the payday falls on a Friday in a given year
     *
     * @param year  current year
     * @return a space separated list of formatted dates
     */
    public String happyFridaysInSweden(int year) {

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 12; i++) {
            LocalDate payday = calculatePaydayInSweden(year, i);

            if (payday.getDayOfWeek() == DayOfWeek.FRIDAY) {
                sb.append(payday.format(DateTimeFormatter.ofPattern("d/M"))).append(" ");
            }
        }

        return sb.toString().trim();
    }

    private LocalDate nowInSweden() {
        return LocalDate.now(ZoneId.of("Europe/Stockholm"));
    }

    private LocalDate nowInIreland() {
        return LocalDate.now(ZoneId.of("Europe/Dublin"));
    }

}
