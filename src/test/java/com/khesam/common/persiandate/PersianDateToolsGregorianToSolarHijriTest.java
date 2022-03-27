package com.khesam.common.persiandate;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersianDateToolsGregorianToSolarHijriTest {


    @Test
    void testGregorianToSolarHijri() {
        GregorianCalendar gregorian = new GregorianCalendar(
                1992, Calendar.JUNE, 25, 0, 0, 0
        );

        assertEquals(
                PersianCalendar.getInstance(1371,4,4),
                PersianDateTools.gregorianToSolarHijri(gregorian)
        );
    }

    @Test
    void testGregorianLeapYearToSolarHijri() {
        GregorianCalendar gregorian = new GregorianCalendar(
                2000, Calendar.MARCH, 25, 0, 0, 0
        );

        assertEquals(
                PersianCalendar.getInstance(1379,1,6),
                PersianDateTools.gregorianToSolarHijri(gregorian)
        );
    }
}
