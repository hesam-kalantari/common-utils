package com.khesam.common.persiandate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersianDateToolsTest {

    @Test
    @DisplayName("Ensure yesterdayOf works correctly")
    void testYesterdayOf() {
        DateModel day = new DateModel(1369, 2, 3);
        DateModel expected = new DateModel(1369, 2, 2);
        DateModel actual = PersianDateTools.yesterdayOf(day);
        assertEquals(
                expected, actual
        );
    }

    @Test
    @DisplayName("Ensure tomorrowOf works correctly")
    void testTomorrowOf() {
        DateModel day = new DateModel(1369, 2, 3);
        DateModel expected = new DateModel(1369, 2, 4);
        DateModel actual = PersianDateTools.tomorrowOf(day);
        assertEquals(
                expected, actual
        );
    }

    @Test
    @DisplayName("Ensure priorDayOf works correctly")
    void testPriorDayOf() {
        DateModel day = new DateModel(1369, 2, 3);
        DateModel expected = new DateModel(1369, 1, 27);
        DateModel actual = PersianDateTools.priorDayOf(day, 7);
        assertEquals(
                expected, actual
        );
    }

    @Test
    @DisplayName("Ensure nextDayOf works correctly")
    void testNextDayOf() {
        DateModel day = new DateModel(1369, 2, 3);
        DateModel expected = new DateModel(1369, 2, 10);
        DateModel actual = PersianDateTools.nextDayOf(day, 7);
        assertEquals(
                expected, actual
        );
    }

    @Test
    @DisplayName("Ensure dayOfWeek works correctly")
    void testDayOfWeek() {
        DateModel day = new DateModel(1369, 2, 3);
        assertEquals(
                DateModel.WeekDay.MONDAY,
                PersianDateTools.dayOfWeek(day)
        );
    }

    @Test
    @DisplayName("Ensure dayOfWeek works correctly")
    void test() {
        DateModel day = new DateModel(1369, 2, 3);
        assertEquals(
                DateModel.WeekDay.MONDAY,
                PersianDateTools.dayOfWeek(day)
        );
    }
}
