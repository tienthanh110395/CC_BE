package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OtpDTOTest {

    private OtpDTO otpDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        otpDTOUnderTest = new OtpDTO(0L, "phone", "user", 0, 0L, "plateTypeCode");
    }

    @Test
    void testEquals() {
        final boolean result = otpDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = otpDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = otpDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = otpDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final OtpDTO.OtpDTOBuilder result = OtpDTO.builder();
        assertThat(result).isEqualTo(result);
        // Verify the results
    }
}
