package com.khesam.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {

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
}
