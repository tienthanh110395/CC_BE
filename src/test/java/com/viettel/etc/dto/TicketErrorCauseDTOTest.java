package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketErrorCauseDTOTest {

    private TicketErrorCauseDTO ticketErrorCauseDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketErrorCauseDTOUnderTest = new TicketErrorCauseDTO();
        ticketErrorCauseDTOUnderTest.ticketErrorCauseId = 0L;
        ticketErrorCauseDTOUnderTest.name = "name";
        ticketErrorCauseDTOUnderTest.code = "code";
        ticketErrorCauseDTOUnderTest.description = "description";
        ticketErrorCauseDTOUnderTest.parentId = 0L;
        ticketErrorCauseDTOUnderTest.status = 0L;
        ticketErrorCauseDTOUnderTest.createUser = "createUser";
        ticketErrorCauseDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketErrorCauseDTOUnderTest.updateUser = "updateUser";
        ticketErrorCauseDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketErrorCauseDTOUnderTest.startrecord = 0;
        ticketErrorCauseDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketErrorCauseDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketErrorCauseDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketErrorCauseDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketErrorCauseDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
