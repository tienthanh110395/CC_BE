package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketHistoryDTOTest {

    private TicketHistoryDTO ticketHistoryDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketHistoryDTOUnderTest = new TicketHistoryDTO();
        ticketHistoryDTOUnderTest.ticketAssignId = "ticketAssignId";
        ticketHistoryDTOUnderTest.ticketId = "ticketId";
        ticketHistoryDTOUnderTest.processContent = "processContent";
        ticketHistoryDTOUnderTest.processTime = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketHistoryDTOUnderTest.processResult = "processResult";
        ticketHistoryDTOUnderTest.siteId = "siteId";
        ticketHistoryDTOUnderTest.staffCode = "staffCode";
        ticketHistoryDTOUnderTest.staffName = "staffName";
        ticketHistoryDTOUnderTest.actTypeId = "actTypeId";
        ticketHistoryDTOUnderTest.siteName = "siteName";
        ticketHistoryDTOUnderTest.status = "status";
        ticketHistoryDTOUnderTest.assignStatus = 0L;
        ticketHistoryDTOUnderTest.ticketStatus = 0L;
        ticketHistoryDTOUnderTest.startrecord = 0;
        ticketHistoryDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketHistoryDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketHistoryDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketHistoryDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketHistoryDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
