package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketAssignLogDTOTest {

    private TicketAssignLogDTO ticketAssignLogDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignLogDTOUnderTest = new TicketAssignLogDTO();
        ticketAssignLogDTOUnderTest.ticketAssignLogId = 0L;
        ticketAssignLogDTOUnderTest.ticketAssignId = 0L;
        ticketAssignLogDTOUnderTest.ticketId = 0L;
        ticketAssignLogDTOUnderTest.logContent = "logContent";
        ticketAssignLogDTOUnderTest.siteId = 0L;
        ticketAssignLogDTOUnderTest.createUser = "createUser";
        ticketAssignLogDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignLogDTOUnderTest.updateUser = "updateUser";
        ticketAssignLogDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignLogDTOUnderTest.logType = 0L;
        ticketAssignLogDTOUnderTest.siteName = "siteName";
        ticketAssignLogDTOUnderTest.startrecord = 0;
        ticketAssignLogDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketAssignLogDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketAssignLogDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketAssignLogDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketAssignLogDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
