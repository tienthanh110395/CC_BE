package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WorkingDaysEntityTest {

    private WorkingDaysEntity workingDaysEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        workingDaysEntityUnderTest = new WorkingDaysEntity();
        workingDaysEntityUnderTest.yyyy = 0L;
        workingDaysEntityUnderTest.dd = Date.valueOf(LocalDate.of(2020, 1, 1));
        workingDaysEntityUnderTest.type = "type";
        workingDaysEntityUnderTest.isLeave = 0L;
        workingDaysEntityUnderTest.leaveType = 0L;
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = workingDaysEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = workingDaysEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = workingDaysEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = workingDaysEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
