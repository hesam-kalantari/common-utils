package com.khesam.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    void justDigit() {
        assertTrue(StringUtils.justDigit("123"));
    }

    @Test
    void justDigit_falseIfContainChar() {
        assertFalse(StringUtils.justDigit("12s3"));
    }

    @Test
    void justDigit_falseIfContainSpace() {
        assertFalse(StringUtils.justDigit("12 3"));
    }

    @Test
    void justDigit_falseIfContainNegativeSign() {
        assertFalse(StringUtils.justDigit("-123"));
    }

    @Test
    @DisplayName("Ensure leading zero correctly added to the input")
    void addLeadingZero() {
        assertEquals(
                "02",
                StringUtils.addLeadingZero(2, 2)
        );
    }

    @Test
    @DisplayName("Ensure leading zero not added as its size equal to length")
    void addLeadingZero_nothingAddedAsInputSizeEqualToLength() {
        assertEquals(
                "1369",
                StringUtils.addLeadingZero(1369, 4)
        );
    }

    @Test
    @DisplayName("Ensure leading zero not added as its size greater than length")
    void addLeadingZero_nothingAddedAsInputSizeGreaterThanLength() {
        assertEquals(
                "1369",
                StringUtils.addLeadingZero(1369, 3)
        );
    }

    @Test
    @DisplayName("Ensure leading zero failed for negative input")
    void addLeadingZero_throwExceptionIfInputIsNegativeNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> StringUtils.addLeadingZero(-2, 2)
        );
    }

    @Test
    void removeLeadingChar() {
        assertEquals(
                "originalText",
                StringUtils.removeLeadingChar("&&&&&&&&&originalText", '&')
        );
    }

    @Test
    void removeLeadingChar_nothingRemoveIfRemovalIsNotLeading() {
        assertEquals(
                "original&&&&&Text",
                StringUtils.removeLeadingChar("original&&&&&Text", '&')
        );
    }

    @Test
    void removeLeadingZero() {
        assertEquals(
                "12345",
                StringUtils.removeLeadingZero("0012345")
        );
    }

    @Test
    void addSingleQuote() {
        assertEquals(
                "\'salam\'",
                StringUtils.addSingleQuote("salam")
        );
    }

    @Test
    void addDoubleQuotes() {
        assertEquals(
                "\"salam\"",
                StringUtils.addDoubleQuotes("salam")
        );
    }

}
