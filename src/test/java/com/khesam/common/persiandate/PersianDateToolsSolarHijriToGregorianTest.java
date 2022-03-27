package com.khesam.common.persiandate;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersianDateToolsSolarHijriToGregorianTest {

    @Test
    void testSolarHijriToGregorian() {
        PersianCalendar date = PersianCalendar.getInstance(1369, 2, 3);
        assertEquals(
                new GregorianCalendar(1990, 4, 23, 0, 0, 0),
                PersianDateTools.solarHijriToGregorian(date)
        );
    }

    @Test
    void testSolarHijriLeapYearToGregorian() {
        PersianCalendar date = PersianCalendar.getInstance(1399, 4, 21);
        assertEquals(
                new GregorianCalendar(2020, 7, 11, 0, 0, 0),
                PersianDateTools.solarHijriToGregorian(date)
        );
    }

    @Test
    void testFirstDayOfSolarHijriLeapYearToGregorian() {
        PersianCalendar date = PersianCalendar.getInstance(1354, 1, 1);
        assertEquals(
                new GregorianCalendar(1975, 3, 21, 0, 0, 0),
                PersianDateTools.solarHijriToGregorian(date)
        );
    }

    @Test
    void testLadDayOfSolarHijriLeapYearToGregorian() {
        PersianCalendar date = PersianCalendar.getInstance(1354, 12, 30);
        assertEquals(
                new GregorianCalendar(1976, 3, 20, 0, 0, 0),
                PersianDateTools.solarHijriToGregorian(date)
        );
    }

    @Test
    void testYearBeforeSolarHijriLeapYearToGregorian() {
        PersianCalendar date = PersianCalendar.getInstance(1398, 11, 8);
        assertEquals(
                new GregorianCalendar(2020, 1, 28, 0, 0, 0),
                PersianDateTools.solarHijriToGregorian(date)
        );
    }

    @Test
    void testYearAfterSolarHijriLeapYearToGregorian() {
        PersianCalendar date = PersianCalendar.getInstance(1400, 6, 25);
        assertEquals(
                new GregorianCalendar(2021, 9, 16, 0, 0, 0),
                PersianDateTools.solarHijriToGregorian(date)
        );
    }
}
