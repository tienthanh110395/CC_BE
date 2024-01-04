package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OtpIdentifyTest {

    private OtpIdentify otpIdentifyUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        otpIdentifyUnderTest = new OtpIdentify("phone", 0);
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = otpIdentifyUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = otpIdentifyUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = otpIdentifyUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = otpIdentifyUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
