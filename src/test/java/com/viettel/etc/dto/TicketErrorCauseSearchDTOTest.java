package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketErrorCauseSearchDTOTest {

    private TicketErrorCauseSearchDTO ticketErrorCauseSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketErrorCauseSearchDTOUnderTest = new TicketErrorCauseSearchDTO();
        ticketErrorCauseSearchDTOUnderTest.ticketErrorCauseId = 0L;
        ticketErrorCauseSearchDTOUnderTest.formDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketErrorCauseSearchDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketErrorCauseSearchDTOUnderTest.inEffect = false;
        ticketErrorCauseSearchDTOUnderTest.expire = false;
        ticketErrorCauseSearchDTOUnderTest.status = Arrays.asList(0L);
        ticketErrorCauseSearchDTOUnderTest.createUser = "createUser";
        ticketErrorCauseSearchDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketErrorCauseSearchDTOUnderTest.updateUser = "updateUser";
        ticketErrorCauseSearchDTOUnderTest.startRecord = 0;
        ticketErrorCauseSearchDTOUnderTest.pageSize = 0;
        ticketErrorCauseSearchDTOUnderTest.lstTicketErrorCauseNameLv1Id = Arrays.asList(0L);
        ticketErrorCauseSearchDTOUnderTest.lstTicketErrorCauseNameLv2Id = Arrays.asList(0L);
        ticketErrorCauseSearchDTOUnderTest.lstErrorCauseLv1 = Arrays.asList(0L);
        ticketErrorCauseSearchDTOUnderTest.lstErrorCauseLv2 = Arrays.asList(0L);
        ticketErrorCauseSearchDTOUnderTest.lstErrorCauseLv3 = Arrays.asList(0L);
        ticketErrorCauseSearchDTOUnderTest.lstLevelError = Arrays.asList(0L);
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketErrorCauseSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketErrorCauseSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketErrorCauseSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketErrorCauseSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
