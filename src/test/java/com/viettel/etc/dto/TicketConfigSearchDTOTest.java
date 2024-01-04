package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketConfigSearchDTOTest {

    private TicketConfigSearchDTO ticketConfigSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketConfigSearchDTOUnderTest = new TicketConfigSearchDTO();
        ticketConfigSearchDTOUnderTest.ticketTypeName = "ticketTypeName";
        ticketConfigSearchDTOUnderTest.ticketTypeCode = "ticketTypeCode";
        ticketConfigSearchDTOUnderTest.inEffect = false;
        ticketConfigSearchDTOUnderTest.expire = false;
        ticketConfigSearchDTOUnderTest.formDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketConfigSearchDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketConfigSearchDTOUnderTest.createUser = "createUser";
        ticketConfigSearchDTOUnderTest.lstTicketTypeGroupId = Arrays.asList(0L);
        ticketConfigSearchDTOUnderTest.lstTicketTypeGenreId = Arrays.asList(0L);
        ticketConfigSearchDTOUnderTest.lstTicketCategoryId = Arrays.asList(0L);
        ticketConfigSearchDTOUnderTest.startRecord = 0;
        ticketConfigSearchDTOUnderTest.pageSize = 0;
        ticketConfigSearchDTOUnderTest.ticketTypeLevel = 0L;
        ticketConfigSearchDTOUnderTest.status = Arrays.asList(0L);
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketConfigSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketConfigSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketConfigSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketConfigSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
