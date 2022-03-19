package com.khesam.common.persiandate;

import com.khesam.common.util.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class DateModel {

    public int year = 1;
    public Month month = Month.getByIndex(1);
    public int day = 1;
    public int hour = 0;
    public int minute = 0;
    public int second = 0;

    private DateModel(@NotNull List<Integer> fields) {
        if (fields.size() > 0) this.year = fields.get(0);

        if (fields.size() > 1) this.month = Month.getByIndex(fields.get(1));

        if (fields.size() > 2) this.day = fields.get(2);

        if (fields.size() > 3) this.hour = fields.get(3);

        if (fields.size() > 4) this.minute = fields.get(4);

        if (fields.size() > 5) this.second = fields.get(5);
    }

    public DateModel(final Integer... fields) {
        this(Arrays.asList(fields));
    }

    public DateModel(final String... fields) {
        this(Arrays.stream(fields)
                .map(Integer::valueOf)
                .collect(Collectors.toList()));
    }

    public DateModel(final @NotNull Calendar calendar) {
        this(Arrays.asList(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND)
                )
        );
    }

    //yyyy/mm/dd HH:MM:SS
    public String show(final @NotNull String pattern) {
        String result = pattern;
        if (pattern.contains("yyyy"))
            result = result.replace("yyyy", String.valueOf(this.year));
        else if (pattern.contains("yy"))
            result = result.replace("yy", String.valueOf(this.year % 100));

        result = result.replace("mm", StringUtils.addLeadingZero(this.month.index, 2));
        result = result.replace("dd", StringUtils.addLeadingZero(this.day, 2));

        result = result.replace("HH", StringUtils.addLeadingZero(this.hour, 2));
        result = result.replace("MM", StringUtils.addLeadingZero(this.minute, 2));
        result = result.replace("SS", StringUtils.addLeadingZero(this.second, 2));
        return result;
    }

    @Override
    public String toString() {
        return show("yyyymmdd-HH:MM:SS");
    }

    public enum Month {
        FIRST(1, "January", "فروردین"),
        SECOND(2, "February", "اردیبهشت"),
        THIRD(3, "March", "خرداد"),
        FORTH(4, "April", "تیر"),
        FIFTH(5, "May", "مرداد"),
        SIXTH(6, "June", "شهریور"),
        SEVENTH(7, "July", "مهر"),
        EIGHTH(8, "August", "آبان"),
        NINTH(9, "September", "آذر"),
        TENTH(10, "October", "دی"),
        ELEVENTH(11, "November", "بهمن"),
        TWELFTH(12, "December", "اسفند");

        final int index;
        final String englishName;
        final String persianName;

        Month(final int index, final String englishName, final String persianName) {
            this.index = index;
            this.englishName = englishName;
            this.persianName = persianName;
        }

        public static Month getByIndex(final int index) {
            return EnumSet.allOf(Month.class).stream()
                    .filter(e -> e.index == index)
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    public enum WeekDay {
        SATURDAY(0, "Saturday", "شنبه"),
        SUNDAY(1, "Sunday", "یک شنبه"),
        MONDAY(2, "Monday", "دوشنبه"),
        TUESDAY(3, "Tuesday", "سه شنبه"),
        WEDNESDAY(4, "Wednesday", "چهارشنبه"),
        THURSDAY(5, "Thursday", "پنج شنبه"),
        FRIDAY(6, "Friday", "جمعه");

        final int index;
        final String englishName;
        final String persianName;

        WeekDay(final int index, final String englishName, final String persianName) {
            this.index = index;
            this.englishName = englishName;
            this.persianName = persianName;
        }

        public static WeekDay getByIndex(final int index) {
            return EnumSet.allOf(WeekDay.class).stream()
                    .filter(e -> e.index == index)
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
