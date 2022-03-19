package com.khesam.common.util;

public class StringUtils {

    private StringUtils() {
        throw new AssertionError("Instantiating utility class.");
    }

    public static String addLeadingZero(final int input, final int length) {
        if (input < 0) throw new IllegalArgumentException("Input should be positive");

        StringBuilder result = new StringBuilder(String.valueOf(input));
        while (result.length() < length)
            result.insert(0, "0");
        return result.toString();
    }
}
