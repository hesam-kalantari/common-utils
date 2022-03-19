package com.khesam.common.persiandate;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

class DateHelper {

    private static final int GREGORIAN_SOLAR_HIJRI_YEAR_DIFFERENCE  =   622;
    private static final int FIRST_GREGORIAN_MONTH_IN_SOLAR_HIJRI   =   10;
    private static final int FIRST_GREGORIAN_DAY_IN_SOLAR_HIJRI     =   11;

    private static final int SOLAR_HIJRI_GREGORIAN_YEAR_DIFFERENCE  =   621;
    private static final int FIRST_SOLAR_HIJRI_MONTH_IN_GREGORIAN   =   3;
    private static final int FIRST_SOLAR_HIJRI_DAY_IN_GREGORIAN     =   21;

    @Contract(pure = true)
    private static int getMonthDays(
            final @NotNull DateSystem dateSystem,
            final int year,
            final int month
    ) {
        if (year % 4 == dateSystem.leafYearReference)
            return dateSystem.leapYearMonthDays[month];
        return dateSystem.normalYearMonthDays[month];
    }

    private static @NotNull Fraction resolveDate(
            final DateSystem dateSystem,
            int yearValue,
            int monthValue,
            int dayValue
    ) {
        Fraction result = new Fraction();
        int day = 0;
        while (!((dayValue - day) < getMonthDays(dateSystem, yearValue, monthValue) && dayValue - day >= 0)) {
            if (dayValue >= 0) {
                day += getMonthDays(dateSystem, yearValue, monthValue);
                result.quotient++;
                monthValue++;
                if (monthValue >= 12) {
                    monthValue = 0;
                    yearValue++;
                }
            } else {
                result.quotient--;
                monthValue--;
                if (monthValue < 0) {
                    monthValue = 11;
                    yearValue--;
                }
                day -= getMonthDays(dateSystem, yearValue, monthValue);
            }
        }
        result.reminder = dayValue - day;
        return result;
    }

    @Contract("_ -> new")
    private static @NotNull DateModel clone(
            final @NotNull DateModel input
    ) {
        return new DateModel(input.year, input.month.index, input.day,
                input.hour, input.minute, input.second);
    }

    private static void addDaysToDate(
            final DateSystem dateSystem,
            final @NotNull DateModel source,
            final int additionDays
    ) {
        int tmpMonth = source.month.index - 1;
        Fraction fraction;
        source.day--;

        source.day += additionDays;
        fraction = resolveDate(dateSystem, source.year, tmpMonth, source.day);

        source.day = fraction.reminder;

        tmpMonth += fraction.quotient;
        fraction.dividend = tmpMonth;
        fraction.divisor = 12;
        fraction.divide();
        tmpMonth = fraction.reminder;

        source.year += fraction.quotient;

        source.day++;
        source.month = DateModel.Month.getByIndex(tmpMonth + 1);
    }

    public static boolean isLeap(final int year) {
        return year % 4 == 3;
    }

    @Contract(pure = true)
    public static int dayOfYear(
            final DateSystem dateSystem,
            final @NotNull DateModel source
    ) {
        int result = 0;
        for (int i = 0; i < source.month.index - 1; i++)
            result += getMonthDays(dateSystem, source.year, i);
        result += source.day - 1;
        return result;
    }

    @Contract(pure = true)
    public static int dayOfWeek(final @NotNull DateModel source) {
        int tmpMonth = source.month.index;
        if (source.month.index == 1)
            tmpMonth = 13;
        else if (source.month.index == 2)
            tmpMonth = 14;

        int century = source.year / 100;
        int yearsOfCentury = source.year % 100;

        double result = (
                source.day + ((26d * (tmpMonth + 1)) / 10) + yearsOfCentury + (yearsOfCentury / 4d) + (century / 4d) + (5 * century)) % 7;
        return (int) result;
    }

    public static int daysBetweenDates(
            final @NotNull DateModel first,
            final @NotNull DateModel second
    ) {
        // Cumulative Days By Month
        int[] cumDays = {0, 31, 62, 93, 124, 155, 186, 216, 246, 276, 306, 336};
        int diffDays = 0;

        if (first.year == second.year)
            return (cumDays[second.month.index - 1] + second.day) - (cumDays[first.month.index - 1] + first.day);

        if (isLeap(first.year))
            diffDays += 366 - (cumDays[first.month.index - 1] + first.day);
        else
            diffDays += 365 - (cumDays[first.month.index - 1] + first.day);

        int year = first.year + 1;

        while (year < second.year) {
            if (isLeap(year))
                diffDays += 366;
            else
                diffDays += 365;
            year += 1;
        }

        diffDays += cumDays[second.month.index - 1] + second.day;
        return diffDays;
    }

    public static @NotNull DateModel gregorianToSolarHijri(final DateModel gregorianDate) {
        DateModel solarHijri = clone(gregorianDate);
        int additionDays;

        solarHijri.year -= GREGORIAN_SOLAR_HIJRI_YEAR_DIFFERENCE;
        solarHijri.month = DateModel.Month.getByIndex(FIRST_GREGORIAN_MONTH_IN_SOLAR_HIJRI);
        solarHijri.day = FIRST_GREGORIAN_DAY_IN_SOLAR_HIJRI;

        if (gregorianDate.year % 4 == 1)
            solarHijri.day++;

        additionDays = dayOfYear(DateSystem.GREGORIAN, gregorianDate);

        addDaysToDate(DateSystem.SOLAR_HIJRI, solarHijri, additionDays);

        return solarHijri;
    }

    public static @NotNull DateModel solarHijriToGregorian(final DateModel solarHijriDate) {
        DateModel gregorian = clone(solarHijriDate);
        int additionDays;

        gregorian.year += SOLAR_HIJRI_GREGORIAN_YEAR_DIFFERENCE;
        gregorian.month = DateModel.Month.getByIndex(FIRST_SOLAR_HIJRI_MONTH_IN_GREGORIAN);
        gregorian.day = FIRST_SOLAR_HIJRI_DAY_IN_GREGORIAN;

        if (solarHijriDate.year % 4 == 3)
            gregorian.day--;
        additionDays = dayOfYear(DateSystem.SOLAR_HIJRI, solarHijriDate);

        addDaysToDate(DateSystem.GREGORIAN, gregorian, additionDays);

        return gregorian;
    }

    public static @NotNull DateModel moveInDate(final DateModel input, final int hop) {
        DateModel solarHijri = clone(input);
        addDaysToDate(DateSystem.SOLAR_HIJRI, solarHijri, hop);

        return solarHijri;
    }

    private static class Fraction {
        public int dividend   =   0;
        public int divisor    =   0;
        public int quotient   =   0;
        public int reminder   =   0;

        public void divide() {
            if (this.divisor == 0)
                return;
            this.reminder = this.dividend % this.divisor;
            if (this.reminder < 0)
                this.reminder += this.divisor;
            this.quotient = (this.dividend - this.reminder) / this.divisor;
        }
    }

    public enum DateSystem {
        GREGORIAN(0, new int[] {
                31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        }, new int[] {
                31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
        ),

        SOLAR_HIJRI(3, new int[] {
                31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29
        }, new int[] {
                31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30}
        );

        final int leafYearReference;
        final int[] normalYearMonthDays;
        final int[] leapYearMonthDays;

        DateSystem(final int leafYearReference, final int[] normalYearMonthDays, final int[] leapYearMonthDays) {
            this.leafYearReference = leafYearReference;
            this.normalYearMonthDays = normalYearMonthDays;
            this.leapYearMonthDays = leapYearMonthDays;
        }
    }
}
