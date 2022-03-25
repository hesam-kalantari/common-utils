package com.khesam.common.persiandate;

class DateHelper {

    private static final int[] SUM_DAYS_OF_GREGORIAN_MONTHS = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };

    private DateHelper() {
        throw new AssertionError("cannot instantiating \"DateHelper\" class.");
    }

//    static DateModel gregorianToSolarHijri(DateModel gregorianDate) {
//
//        int gy2 = (gregorianDate.month.index > 2) ? (gregorianDate.year + 1) : gregorianDate.year;
//        int days = 355666 + (365 * gregorianDate.year) + ((int) ((gy2 + 3) / 4)) - ((int) ((gy2 + 99) / 100)) + ((int) ((gy2 + 399) / 400)) + gregorianDate.day + SUM_DAYS_OF_GREGORIAN_MONTHS[g - 1];
//        int jy = -1595 + (33 * ((int) (days / 12053)));
//        days %= 12053;
//        jy += 4 * ((int) (days / 1461));
//        days %= 1461;
//        if (days > 365) {
//            jy += (int) ((days - 1) / 365);
//            days = (days - 1) % 365;
//        }
//        int jm = (days < 186) ? 1 + (int) (days / 31) : 7 + (int) ((days - 186) / 30);
//        int jd = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));
//        int[] jalali = { jy, jm, jd };
//        return jalali;
//    }
//
//    static DateModel solarHijriToGregorian(DateModel solarHijriDate) {
//
//    }
}
