package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class OtpEntityTest {

    private OtpEntity otpEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        otpEntityUnderTest = new OtpEntity();
        otpEntityUnderTest.phone = "phone";
        otpEntityUnderTest.confirmType = 0;
        otpEntityUnderTest.otp = "otp";
        otpEntityUnderTest.signDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        otpEntityUnderTest.duration = 0;
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = otpEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = otpEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = otpEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = otpEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
