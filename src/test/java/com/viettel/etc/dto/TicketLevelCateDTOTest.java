package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketLevelCateDTOTest {

    private TicketLevelCateDTO ticketLevelCateDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketLevelCateDTOUnderTest = new TicketLevelCateDTO();
        ticketLevelCateDTOUnderTest.lstLevelCateId = Arrays.asList(0L);
        ticketLevelCateDTOUnderTest.search = 0L;
        ticketLevelCateDTOUnderTest.ticketLevelCateId = 0L;
        ticketLevelCateDTOUnderTest.ticketLevelCateName = "ticketLevelCateName";
        ticketLevelCateDTOUnderTest.ticketLevelCateCode = "ticketLevelCateCode";
        ticketLevelCateDTOUnderTest.status = 0L;
        ticketLevelCateDTOUnderTest.description = "description";
        ticketLevelCateDTOUnderTest.isActive = 0L;
        ticketLevelCateDTOUnderTest.createUser = "createUser";
        ticketLevelCateDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketLevelCateDTOUnderTest.updateUser = "updateUser";
        ticketLevelCateDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketLevelCateDTOUnderTest.type = 0L;
        ticketLevelCateDTOUnderTest.startRecord = 0;
        ticketLevelCateDTOUnderTest.pageSize = 0;
        ticketLevelCateDTOUnderTest.fromDate = "fromDate";
        ticketLevelCateDTOUnderTest.toDate = "toDate";
        ticketLevelCateDTOUnderTest.lstIdsActive = Arrays.asList(0L);
        ticketLevelCateDTOUnderTest.lstIdsInactive = Arrays.asList(0L);
        ticketLevelCateDTOUnderTest.ticketSlaId = 0L;
        ticketLevelCateDTOUnderTest.ticketTimeFull = 0L;
        ticketLevelCateDTOUnderTest.ticketTimeLv1 = 0L;
        ticketLevelCateDTOUnderTest.ticketTimeLv2 = 0L;
        ticketLevelCateDTOUnderTest.ticketRetime = 0L;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketLevelCateDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketLevelCateDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketLevelCateDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketLevelCateDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
