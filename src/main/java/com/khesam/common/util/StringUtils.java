package com.khesam.common.util;

public class StringUtils {

    public static final String EmptyString = "";

    private StringUtils() {
        throw new AssertionError("Instantiating utility class.");
    }

    public static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }

    public static boolean justDigit(String input) {
        return StringUtils.isEmpty(input) || input.matches("[0-9]+");
    }

    public static String addLeadingZero(int input, int length) {
        if (input < 0) throw new IllegalArgumentException("Input should be positive");

        StringBuilder result = new StringBuilder(String.valueOf(input));
        while (result.length() < length)
            result.insert(0, "0");
        return result.toString();
    }

    public static String removeLeadingChar(String input, char removalChar) {
        if (StringUtils.isEmpty(input))
            return EmptyString;
        while (input.length() > 1 && input.charAt(0) == removalChar)
            input = input.substring(1);
        return input;
    }

    public static String removeLeadingZero(String input) {
        return StringUtils.removeLeadingChar(input, '0');
    }

    public static String removeLeadingWhitespace(String input) {
        return StringUtils.removeLeadingChar(input, ' ');
    }

    public static String addSingleQuote(String input) {
        return (!input.startsWith("'"))? String.format("'%s'", input): input;
    }

    public static String addDoubleQuotes(String input) {
        return (!input.startsWith("\""))? String.format("\"%s\"", input): input;
    }
}
