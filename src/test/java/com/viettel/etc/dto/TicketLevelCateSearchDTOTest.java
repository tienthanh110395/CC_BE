package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketLevelCateSearchDTOTest {

    private TicketLevelCateSearchDTO ticketLevelCateSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketLevelCateSearchDTOUnderTest = new TicketLevelCateSearchDTO();
        ticketLevelCateSearchDTOUnderTest.ticketLevelCateCode = "ticketLevelCateCode";
        ticketLevelCateSearchDTOUnderTest.fromDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketLevelCateSearchDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketLevelCateSearchDTOUnderTest.status = Arrays.asList(0L);
        ticketLevelCateSearchDTOUnderTest.inEffect = false;
        ticketLevelCateSearchDTOUnderTest.createUser = "createUser";
        ticketLevelCateSearchDTOUnderTest.expire = false;
        ticketLevelCateSearchDTOUnderTest.startRecord = 0;
        ticketLevelCateSearchDTOUnderTest.pageSize = 0;
        ticketLevelCateSearchDTOUnderTest.lstLevelCateId = Arrays.asList(0L);
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketLevelCateSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketLevelCateSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketLevelCateSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketLevelCateSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
