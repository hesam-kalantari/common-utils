package com.khesam.common.persiandate;

import java.util.Calendar;
import java.util.GregorianCalendar;

class DateHelper {

    private static final int[] SUM_DAYS_OF_GREGORIAN_MONTHS = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    private DateHelper() {
        throw new AssertionError("cannot instantiating \"DateHelper\" class.");
    }

    //https://jdf.scr.ir/jdf
    static PersianCalendar gregorianToSolarHijri(GregorianCalendar gregorianDate) {
        int gy = gregorianDate.get(Calendar.YEAR);
        int gm = gregorianDate.get(Calendar.MONTH) + 1;
        int gd = gregorianDate.get(Calendar.DAY_OF_MONTH);

        int gy2 = (gm > 2) ? (gy + 1) : gy;
        int days = 355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100)
                + ((gy2 + 399) / 400) + gd + SUM_DAYS_OF_GREGORIAN_MONTHS[gm - 1];
        int jy = -1595 + (33 * (days / 12053));
        days %= 12053;
        jy += 4 * (days / 1461);
        days %= 1461;
        if (days > 365) {
            jy += (days - 1) / 365;
            days = (days - 1) % 365;
        }
        int jm = (days < 186) ? 1 + (days / 31) : 7 + ((days - 186) / 30);
        int jd = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));
        return PersianCalendar.getInstance(
                jy, jm, jd,
                gregorianDate.get(Calendar.HOUR),
                gregorianDate.get(Calendar.MINUTE),
                gregorianDate.get(Calendar.SECOND)
        );
    }

    //https://jdf.scr.ir/jdf
    static GregorianCalendar solarHijriToGregorian(PersianCalendar persianCalendar) {
        int jy = persianCalendar.year;
        int jm = persianCalendar.month;
        int jd = persianCalendar.day;

        jy += 1595;
        int days = -355668 + (365 * jy) + ((jy / 33) * 8) + (((jy % 33) + 3) / 4) + jd + ((jm < 7) ? (jm - 1) * 31 : ((jm - 7) * 30) + 186);
        int gy = 400 * (days / 146097);
        days %= 146097;
        if (days > 36524) {
            gy += 100 * (--days / 36524);
            days %= 36524;
            if (days >= 365)
                days++;
        }
        gy += 4 * (days / 1461);
        days %= 1461;
        if (days > 365) {
            gy += (days - 1) / 365;
            days = (days - 1) % 365;
        }
        int gd = days + 1;
        int[] sal_a = {0, 31, ((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0)) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int gm;
        for (gm = 0; gm < 13 && gd > sal_a[gm]; gm++) gd -= sal_a[gm];

        return new GregorianCalendar(
                gy, gm, gd, persianCalendar.hour, persianCalendar.minute, persianCalendar.second
        );
    }
}
