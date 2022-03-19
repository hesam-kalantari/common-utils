package com.khesam.common.persiandate;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersianDateTools {

    private PersianDateTools() {
        throw new AssertionError("Instantiating utility class.");
    }

    public static DateModel nowInHijri() {
        Calendar today = new GregorianCalendar();
        return gregorianToSolarHijri(
                new DateModel(
                        today.get(Calendar.YEAR),
                        today.get(Calendar.MONTH) + 1,
                        today.get(Calendar.DAY_OF_MONTH),
                        today.get(Calendar.HOUR_OF_DAY),
                        today.get(Calendar.MINUTE),
                        today.get(Calendar.SECOND)
                )
        );
    }

    public static DateModel yesterday() {
        return nextDay(-1);
    }

    public static DateModel yesterdayOf(final DateModel input) {
        return nextDayOf(input, -1);
    }

    public static DateModel tomorrow() {
        return nextDay(1);
    }

    public static DateModel tomorrowOf(final DateModel input) {
        return nextDayOf(input, 1);
    }

    public static DateModel priorDay(final int count) {
        return DateHelper.moveInDate(nowInHijri(), -Math.abs(count));
    }

    public static DateModel priorDayOf(final DateModel input, int count) {
        return DateHelper.moveInDate(input, -Math.abs(count));
    }

    public static DateModel nextDay(final int count) {
        return DateHelper.moveInDate(nowInHijri(), count);
    }

    public static DateModel nextDayOf(final DateModel input, int count) {
        return DateHelper.moveInDate(input, count);
    }

    public static int dayOfYear(final DateModel input) {
        return DateHelper.dayOfYear(DateHelper.DateSystem.SOLAR_HIJRI, input);
    }

    public static DateModel.WeekDay dayOfWeek(final DateModel input) {
        return DateModel.WeekDay.getByIndex(DateHelper.dayOfWeek(DateHelper.solarHijriToGregorian(input)));
    }

    public static int dateDifference(final DateModel first, DateModel second) {
        return DateHelper.daysBetweenDates(first, second);
    }

    public static DateModel gregorianToSolarHijri(final DateModel input) {
        return DateHelper.gregorianToSolarHijri(input);
    }

    public static DateModel solarHijriToGregorian(final DateModel input) {
        return DateHelper.solarHijriToGregorian(input);
    }

    public static boolean isLeap(final int year) {
        return DateHelper.isLeap(year);
    }

    public static boolean isEqual(final DateModel first, final DateModel second, final String pattern) {
        if (pattern.contains("SS") && (first.second != second.second)) return false;
        if (pattern.contains("MM") && (first.minute != second.minute)) return false;
        if (pattern.contains("HH") && (first.hour != second.hour)) return false;
        if (pattern.contains("dd") && (first.day != second.day)) return false;
        if (pattern.contains("mm") && (first.month != second.month)) return false;
        return (!pattern.contains("yy") && !pattern.contains("yyyy")) || (first.year == second.year);
    }
}
