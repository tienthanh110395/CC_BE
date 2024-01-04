package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketProcessShareDetailDTOTest {

    private TicketProcessShareDetailDTO ticketProcessShareDetailDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareDetailDTOUnderTest = new TicketProcessShareDetailDTO();
        ticketProcessShareDetailDTOUnderTest.ticketProcessShareDetailId = 0L;
        ticketProcessShareDetailDTOUnderTest.ticketProcessShareId = 0L;
        ticketProcessShareDetailDTOUnderTest.assignTime = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketProcessShareDetailDTOUnderTest.ticketId = 0L;
        ticketProcessShareDetailDTOUnderTest.processUser = "processUser";
        ticketProcessShareDetailDTOUnderTest.startrecord = 0;
        ticketProcessShareDetailDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketProcessShareDetailDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketProcessShareDetailDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketProcessShareDetailDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketProcessShareDetailDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
