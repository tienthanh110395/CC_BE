package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketExpireCauseDTOTest {

    private TicketExpireCauseDTO ticketExpireCauseDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExpireCauseDTOUnderTest = new TicketExpireCauseDTO();
        ticketExpireCauseDTOUnderTest.ticketExpireCauseId = 0L;
        ticketExpireCauseDTOUnderTest.name = "name";
        ticketExpireCauseDTOUnderTest.code = "code";
        ticketExpireCauseDTOUnderTest.description = "description";
        ticketExpireCauseDTOUnderTest.parentId = 0L;
        ticketExpireCauseDTOUnderTest.status = 0L;
        ticketExpireCauseDTOUnderTest.createUser = "createUser";
        ticketExpireCauseDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExpireCauseDTOUnderTest.updateUser = "updateUser";
        ticketExpireCauseDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExpireCauseDTOUnderTest.startrecord = 0;
        ticketExpireCauseDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketExpireCauseDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketExpireCauseDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketExpireCauseDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketExpireCauseDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
