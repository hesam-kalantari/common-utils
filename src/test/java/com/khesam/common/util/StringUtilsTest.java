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
    void justLetter() {
        assertTrue(StringUtils.justLetter("sAlaM"));
    }

    @Test
    void justLetter_falseIfContainDigit() {
        assertFalse(StringUtils.justLetter("james007"));
    }

    @Test
    void justLetter_trueIfContainWhiteSpace() {
        assertFalse(StringUtils.justLetter("james\tbond"));
    }

    @Test
    void justLetter_trueIfContainSymbol() {
        assertFalse(StringUtils.justLetter("james@bond"));
    }

    @Test
    void justLetter_trueIfContainPersianChars() {
        assertFalse(StringUtils.justLetter("سلام James"));
    }

    @Test
    void isPersian() {
        assertTrue(StringUtils.isPersian("سلام"));
    }

    @Test
    void isPersian_trueIfContainZhe() {
        assertTrue(StringUtils.isPersian("ژاله"));
    }

    @Test
    void isPersian_trueIfContainPe() {
        assertTrue(StringUtils.isPersian("پشم"));
    }

    @Test
    void isPersian_trueIfContainGe() {
        assertTrue(StringUtils.isPersian("بگا"));
    }

    @Test
    void isPersian_falseIfContainLatinChar() {
        assertFalse(StringUtils.isPersian("سلام James"));
    }

    @Test
    void isPersian_falseIfContainDigit() {
        assertFalse(StringUtils.isPersian("سلام 007"));
    }

    @Test
    void isPersian_falseIfContainSymbol() {
        assertFalse(StringUtils.isPersian("سلام جیمز@باند"));
    }

    @Test
    void isPersian_trueIfContainWhiteSpace() {
        assertTrue(StringUtils.isPersian("سلام جیمز"));;
    }

    @Test
    void isPersian_falseIfContainTabChar() {
        assertFalse(StringUtils.isPersian("\t سلام"));
    }

    @Test
    void isPersian_falseIfContainCarriageReturnChar() {
        assertFalse(StringUtils.isPersian("\n سلام"));
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
    void removeLeadingWhitespace() {
        assertEquals(
                "salam salam",
                StringUtils.removeLeadingWhitespace("    salam salam")
        );
    }

    @Test
    void removeLeadingWhitespace_removeLeadingTabChar() {
        assertEquals(
                "salam salam",
                StringUtils.removeLeadingWhitespace("\tsalam salam")
        );
    }

    @Test
    void removeLeadingWhitespace_removeCarriageReturnChar() {
        assertEquals(
                "salam salam",
                StringUtils.removeLeadingWhitespace("\rsalam salam")
        );
    }

    @Test
    void removeLeadingWhitespace_removeLineFeedChar() {
        assertEquals(
                "salam salam",
                StringUtils.removeLeadingWhitespace("\nsalam salam")
        );
    }

    @Test
    void removeLeadingWhitespace_removeFormFeedChar() {
        assertEquals(
                "salam salam",
                StringUtils.removeLeadingWhitespace("\fsalam salam")
        );
    }

    @Test
    void removeLeadingWhitespace_removeDifferentWhitespaceChars() {
        assertEquals(
                "salam salam",
                StringUtils.removeLeadingWhitespace(" \f\t \n   \rsalam salam")
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
