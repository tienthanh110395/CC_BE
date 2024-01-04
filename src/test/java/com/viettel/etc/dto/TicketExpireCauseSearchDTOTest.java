package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketExpireCauseSearchDTOTest {

    private TicketExpireCauseSearchDTO ticketExpireCauseSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExpireCauseSearchDTOUnderTest = new TicketExpireCauseSearchDTO();
        ticketExpireCauseSearchDTOUnderTest.inEffect = false;
        ticketExpireCauseSearchDTOUnderTest.expire = false;
        ticketExpireCauseSearchDTOUnderTest.formDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExpireCauseSearchDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExpireCauseSearchDTOUnderTest.lstExpireCauseOne = Arrays.asList(0L);
        ticketExpireCauseSearchDTOUnderTest.lstExpireCauseTwo = Arrays.asList(0L);
        ticketExpireCauseSearchDTOUnderTest.lstExpireCauseThree = Arrays.asList(0L);
        ticketExpireCauseSearchDTOUnderTest.lstLevelExpire = Arrays.asList(0L);
        ticketExpireCauseSearchDTOUnderTest.startRecord = 0;
        ticketExpireCauseSearchDTOUnderTest.pageSize = 0;
        ticketExpireCauseSearchDTOUnderTest.ticketTypeLevel = 0L;
        ticketExpireCauseSearchDTOUnderTest.status = Arrays.asList(0L);
        ticketExpireCauseSearchDTOUnderTest.createUser = "createUser";
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketExpireCauseSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketExpireCauseSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketExpireCauseSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketExpireCauseSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
