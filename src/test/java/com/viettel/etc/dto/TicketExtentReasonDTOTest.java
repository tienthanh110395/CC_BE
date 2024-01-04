package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketExtentReasonDTOTest {

    private TicketExtentReasonDTO ticketExtentReasonDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExtentReasonDTOUnderTest = new TicketExtentReasonDTO();
        ticketExtentReasonDTOUnderTest.ticketExtentReasonId = 0L;
        ticketExtentReasonDTOUnderTest.name = "name";
        ticketExtentReasonDTOUnderTest.description = "description";
        ticketExtentReasonDTOUnderTest.status = 0L;
        ticketExtentReasonDTOUnderTest.createUser = "createUser";
        ticketExtentReasonDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExtentReasonDTOUnderTest.updateUser = "updateUser";
        ticketExtentReasonDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExtentReasonDTOUnderTest.startrecord = 0;
        ticketExtentReasonDTOUnderTest.pagesize = 0;
        ticketExtentReasonDTOUnderTest.resultSqlEx = false;
    }

    @Test
    void testEquals() {
        final boolean result = ticketExtentReasonDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketExtentReasonDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketExtentReasonDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketExtentReasonDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
