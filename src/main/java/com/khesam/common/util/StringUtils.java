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
        return isEmpty(input) || input.matches("[0-9]+");
    }

    public static boolean justLetter(String input) {
        if (isEmpty(input))
            return true;
        char[] chars = input.toCharArray();
        for (char ch : chars)
            if (!Character.isLetter(ch))
                return false;
        return true;
    }

    public static boolean isPersian(String input) {
        if (isEmpty(input))
            return true;
        for (int i = 0; i < Character.codePointCount(input, 0, input.length()); i++) {
            int c = input.codePointAt(i);
            if ((c < 0x600 || c > 0x6FF) && c != 0xFB8A && c != 0x200C && c != 0x200F && c != 0x0020) {
                return false;
            }
        }
        return true;
    }

    public static String addLeadingZero(int input, int length) {
        if (input < 0) throw new IllegalArgumentException("Input should be positive");

        StringBuilder result = new StringBuilder(String.valueOf(input));
        while (result.length() < length)
            result.insert(0, "0");
        return result.toString();
    }

    public static String removeLeadingChar(String input, char removalChar) {
        if (isEmpty(input))
            return EmptyString;
        while (input.length() > 1 && input.charAt(0) == removalChar)
            input = input.substring(1);
        return input;
    }

    public static String removeLeadingZero(String input) {
        return StringUtils.removeLeadingChar(input, '0');
    }

    public static String removeLeadingWhitespace(String input) {
        if (isEmpty(input))
            return EmptyString;
        while (input.length() > 1 && Character.isWhitespace(input.charAt(0)))
            input = input.substring(1);
        return input;
    }

    public static String addSingleQuote(String input) {
        return (!input.startsWith("'"))? String.format("'%s'", input): input;
    }

    public static String addDoubleQuotes(String input) {
        return (!input.startsWith("\""))? String.format("\"%s\"", input): input;
    }
}
