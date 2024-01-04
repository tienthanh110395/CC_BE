package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class WorkingDaysDTOTest {

    private WorkingDaysDTO workingDaysDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        workingDaysDTOUnderTest = new WorkingDaysDTO();
        workingDaysDTOUnderTest.yyyy = 0L;
        workingDaysDTOUnderTest.dd = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        workingDaysDTOUnderTest.type = "type";
        workingDaysDTOUnderTest.isLeave = 0L;
        workingDaysDTOUnderTest.leaveType = 0L;
        workingDaysDTOUnderTest.startrecord = 0;
        workingDaysDTOUnderTest.pagesize = 0;
        workingDaysDTOUnderTest.resultSqlEx = false;
    }

    @Test
    void testEquals() {
        final boolean result = workingDaysDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = workingDaysDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = workingDaysDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = workingDaysDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
