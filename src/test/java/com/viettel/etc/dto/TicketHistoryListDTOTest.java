package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketHistoryListDTOTest {

    private TicketHistoryListDTO ticketHistoryListDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketHistoryListDTOUnderTest = new TicketHistoryListDTO();
        ticketHistoryListDTOUnderTest.ticketId = "ticketId";
        ticketHistoryListDTOUnderTest.ticketAssignId = "ticketAssignId";
        ticketHistoryListDTOUnderTest.l1TicketTypeId = "l1TicketTypeId";
        ticketHistoryListDTOUnderTest.groupPA = "groupPA";
        ticketHistoryListDTOUnderTest.l2TicketTypeId = "l2TicketTypeId";
        ticketHistoryListDTOUnderTest.subgroupPA = "subgroupPA";
        ticketHistoryListDTOUnderTest.l3TicketTypeId = "l3TicketTypeId";
        ticketHistoryListDTOUnderTest.detailPA = "detailPA";
        ticketHistoryListDTOUnderTest.contentReceive = "contentReceive";
        ticketHistoryListDTOUnderTest.ticketStatus = "ticketStatus";
        ticketHistoryListDTOUnderTest.receiveDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketHistoryListDTOUnderTest.concludeDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketHistoryListDTOUnderTest.startDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketHistoryListDTOUnderTest.endDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketHistoryListDTOUnderTest.receiveUser = "receiveUser";
        ticketHistoryListDTOUnderTest.concludeUserCode = "concludeUserCode";
        ticketHistoryListDTOUnderTest.concludeUserName = "concludeUserName";
        ticketHistoryListDTOUnderTest.phoneNumber = "phoneNumber";
        ticketHistoryListDTOUnderTest.plateTypeCode = "plateTypeCode";
        ticketHistoryListDTOUnderTest.plateNumber = "plateNumber";
        ticketHistoryListDTOUnderTest.contractNo = "contractNo";
        ticketHistoryListDTOUnderTest.phoneContact = "phoneContact";
        ticketHistoryListDTOUnderTest.ticketTimes = 0L;
        ticketHistoryListDTOUnderTest.stageId = 0L;
        ticketHistoryListDTOUnderTest.stageName = "stageName";
        ticketHistoryListDTOUnderTest.stationId = 0L;
        ticketHistoryListDTOUnderTest.stationName = "stationName";
        ticketHistoryListDTOUnderTest.startrecord = 0;
        ticketHistoryListDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketHistoryListDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketHistoryListDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketHistoryListDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketHistoryListDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
