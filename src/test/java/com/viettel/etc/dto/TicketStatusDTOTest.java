package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketStatusDTOTest {

    private TicketStatusDTO ticketStatusDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketStatusDTOUnderTest = new TicketStatusDTO();
        ticketStatusDTOUnderTest.ticketStatusId = 0L;
        ticketStatusDTOUnderTest.ticketId = 0L;
        ticketStatusDTOUnderTest.siteId = 0L;
        ticketStatusDTOUnderTest.ticketStatus = 0L;
        ticketStatusDTOUnderTest.processTime = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketStatusDTOUnderTest.note = "note";
        ticketStatusDTOUnderTest.createUser = "createUser";
        ticketStatusDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketStatusDTOUnderTest.updateUser = "updateUser";
        ticketStatusDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketStatusDTOUnderTest.processContent = "processContent";
        ticketStatusDTOUnderTest.siteName = "siteName";
        ticketStatusDTOUnderTest.startrecord = 0;
        ticketStatusDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketStatusDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketStatusDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketStatusDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketStatusDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
