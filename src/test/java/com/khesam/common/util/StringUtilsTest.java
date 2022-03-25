package com.khesam.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    void test_justDigit() {
        assertTrue(StringUtils.justDigit("123"));
    }

    @Test
    void test_justDigit_failedIfContainChar() {
        assertFalse(StringUtils.justDigit("12s3"));
    }

    @Test
    void test_justDigit_failedIfContainSpace() {
        assertFalse(StringUtils.justDigit("12 3"));
    }

    @Test
    void test_justDigit_failedIfNegativeSign() {
        assertFalse(StringUtils.justDigit("-123"));
    }

    @Test
    @DisplayName("Ensure leading zero correctly added to the input")
    void test_addLeadingZero() {
        assertEquals(
                "02",
                StringUtils.addLeadingZero(2, 2)
        );
    }

    @Test
    @DisplayName("Ensure leading zero not added as its size equal to length")
    void test_addLeadingZero_nothingAddedAsInputSizeEqualToLength() {
        assertEquals(
                "1369",
                StringUtils.addLeadingZero(1369, 4)
        );
    }

    @Test
    @DisplayName("Ensure leading zero not added as its size greater than length")
    void test_addLeadingZero_nothingAddedAsInputSizeGreaterThanLength() {
        assertEquals(
                "1369",
                StringUtils.addLeadingZero(1369, 3)
        );
    }

    @Test
    @DisplayName("Ensure leading zero failed for negative input")
    void test_addLeadingZero_negativeNumber_failed() {
        assertThrows(
                IllegalArgumentException.class,
                () -> StringUtils.addLeadingZero(-2, 2)
        );
    }

    @Test
    void test_addSingleQuote() {
        assertEquals(
                "\'salam\'",
                StringUtils.addSingleQuote("salam")
        );
    }

    @Test
    void test_addDoubleQuotes() {
        assertEquals(
                "\"salam\"",
                StringUtils.addDoubleQuotes("salam")
        );
    }

}
