package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSlaSearchDTOTest {

    private TicketSlaSearchDTO ticketSlaSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSlaSearchDTOUnderTest = new TicketSlaSearchDTO();
        ticketSlaSearchDTOUnderTest.formDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketSlaSearchDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketSlaSearchDTOUnderTest.createUser = "createUser";
        ticketSlaSearchDTOUnderTest.lstTicketGroup = Arrays.asList(0L);
        ticketSlaSearchDTOUnderTest.lstTicketGenre = Arrays.asList(0L);
        ticketSlaSearchDTOUnderTest.lstTicketType = Arrays.asList(0L);
        ticketSlaSearchDTOUnderTest.lstPriority = Arrays.asList(0L);
        ticketSlaSearchDTOUnderTest.startRecord = 0;
        ticketSlaSearchDTOUnderTest.pageSize = 0;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketSlaSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketSlaSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSlaSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketSlaSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
