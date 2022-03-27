package com.khesam.common.persiandate;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersianDateTools {

    public static PersianCalendar gregorianToSolarHijri(GregorianCalendar input) {
        return DateHelper.gregorianToSolarHijri(input);
    }

    public static PersianCalendar nowInSolarHijri() {
        return DateHelper.gregorianToSolarHijri(new GregorianCalendar());
    }

    public static PersianCalendar yesterday() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public static PersianCalendar yesterdayOf(PersianCalendar input) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public static PersianCalendar tomorrow() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public static PersianCalendar tomorrowOf(PersianCalendar input) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public static Calendar solarHijriToGregorian(PersianCalendar input) {
        return DateHelper.solarHijriToGregorian(input);
    }
}
