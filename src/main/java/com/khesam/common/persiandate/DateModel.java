package com.khesam.common.persiandate;

import com.khesam.common.util.StringUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;

public class DateModel {

    public int year;
    public Month month;
    public int day;
    public int hour;
    public int minute;
    public int second;

    private DateModel(int year, int month, int day, int hour, int minute, int second) {
        if (year < 1 || (month <  1 || month > 12) || (day < 1 || day > 31) ||
                (hour < 0 || hour > 23) || (minute < 0 || minute > 59) || (second < 0 || second > 59)) {
            throw new IllegalArgumentException("Invalid date value");
        }
        this.year = year;
        this.month = Month.getByIndex(month);
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static DateModel getInstance(List<Integer> fields) {
        int year = 1, month = 1, day = 1, minute = 0, hour = 0, second = 0;

        if (fields.size() > 0) year   = fields.get(0);
        if (fields.size() > 1) month  = fields.get(1);
        if (fields.size() > 2) day    = fields.get(2);
        if (fields.size() > 3) hour   = fields.get(3);
        if (fields.size() > 4) minute = fields.get(4);
        if (fields.size() > 5) second = fields.get(5);

        return new DateModel(year, month, day, hour, minute, second);
    }

    public static DateModel getInstance(Integer... fields) {
        return getInstance(Arrays.asList(fields));
    }

    public static DateModel getInstance(Calendar calendar) {
        return new DateModel(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        );
    }

    public static DateModel getInstance(DateModel dateModel) {
        return new DateModel(
                dateModel.year,
                dateModel.month.ordinal() + 1,
                dateModel.day,
                dateModel.hour,
                dateModel.minute,
                dateModel.second
        );
    }

    //yyyy/mm/dd HH:MM:SS
    public String show(final String pattern) {
        String result = pattern;
        if (pattern.contains("yyyy"))
            result = result.replace("yyyy", String.valueOf(this.year));
        else if (pattern.contains("yy"))
            result = result.replace("yy", String.valueOf(this.year % 100));

        result = result.replace("mm", StringUtils.addLeadingZero(this.month.ordinal() + 1, 2));
        result = result.replace("dd", StringUtils.addLeadingZero(this.day, 2));

        result = result.replace("HH", StringUtils.addLeadingZero(this.hour, 2));
        result = result.replace("MM", StringUtils.addLeadingZero(this.minute, 2));
        result = result.replace("SS", StringUtils.addLeadingZero(this.second, 2));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateModel dateModel = (DateModel) o;

        if (year != dateModel.year) return false;
        if (day != dateModel.day) return false;
        if (hour != dateModel.hour) return false;
        if (minute != dateModel.minute) return false;
        if (second != dateModel.second) return false;
        return month == dateModel.month;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month.hashCode();
        result = 31 * result + day;
        result = 31 * result + hour;
        result = 31 * result + minute;
        result = 31 * result + second;
        return result;
    }

    @Override
    public String toString() {
        return show("yyyymmdd-HH:MM:SS");
    }

    enum Month {
        FIRST    (1,  "January",   "فروردین"),
        SECOND   (2,  "February",  "اردیبهشت"),
        THIRD    (3,  "March",     "خرداد"),
        FORTH    (4,  "April",     "تیر"),
        FIFTH    (5,  "May",       "مرداد"),
        SIXTH    (6,  "June",      "شهریور"),
        SEVENTH  (7,  "July",      "مهر"),
        EIGHTH   (8,  "August",    "آبان"),
        NINTH    (9,  "September", "آذر"),
        TENTH    (10, "October",   "دی"),
        ELEVENTH (11, "November",  "بهمن"),
        TWELFTH  (12, "December",  "اسفند");

        final int index;
        final String englishName;
        final String persianName;

        Month(int index, String englishName, String persianName) {
            this.index = index;
            this.englishName = englishName;
            this.persianName = persianName;
        }

        public static Month getByIndex(final int month) {
            return EnumSet.allOf(Month.class).stream()
                    .filter(e -> e.ordinal() == (month - 1))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    enum WeekDay {
        SATURDAY  ("Saturday",  "شنبه"),
        SUNDAY    ("Sunday",    "یک شنبه"),
        MONDAY    ("Monday",    "دوشنبه"),
        TUESDAY   ("Tuesday",   "سه شنبه"),
        WEDNESDAY ("Wednesday", "چهارشنبه"),
        THURSDAY  ("Thursday",  "پنج شنبه"),
        FRIDAY    ("Friday",    "جمعه");

        final String englishName;
        final String persianName;

        WeekDay(final String englishName, final String persianName) {
            this.englishName = englishName;
            this.persianName = persianName;
        }

        public static WeekDay getByIndex(final int index) {
            return EnumSet.allOf(WeekDay.class).stream()
                    .filter(e -> e.ordinal() == (index - 1))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
