package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketErrorCauseNewDTOTest {

    private TicketErrorCauseNewDTO ticketErrorCauseNewDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketErrorCauseNewDTOUnderTest = new TicketErrorCauseNewDTO();
        ticketErrorCauseNewDTOUnderTest.name = "name";
        ticketErrorCauseNewDTOUnderTest.code = "code";
        ticketErrorCauseNewDTOUnderTest.ticketErrorCauseId = 0L;
        ticketErrorCauseNewDTOUnderTest.ticketErrorCauseId1 = 0L;
        ticketErrorCauseNewDTOUnderTest.ticketErrorCauseId2 = 0L;
        ticketErrorCauseNewDTOUnderTest.ticketErrorCauseId3 = 0L;
        ticketErrorCauseNewDTOUnderTest.ticketErrorCauseName = "ticketErrorCauseName";
        ticketErrorCauseNewDTOUnderTest.ticketErrorCauseCode = "ticketErrorCauseCode";
        ticketErrorCauseNewDTOUnderTest.errorCauseNameParent = "errorCauseNameParent";
        ticketErrorCauseNewDTOUnderTest.status = 0L;
        ticketErrorCauseNewDTOUnderTest.createUser = "createUser";
        ticketErrorCauseNewDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketErrorCauseNewDTOUnderTest.updateUser = "updateUser";
        ticketErrorCauseNewDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketErrorCauseNewDTOUnderTest.startRecord = 0;
        ticketErrorCauseNewDTOUnderTest.pageSize = 0;
        ticketErrorCauseNewDTOUnderTest.formDate = "formDate";
        ticketErrorCauseNewDTOUnderTest.toDate = "toDate";
        ticketErrorCauseNewDTOUnderTest.lstIdsActive = Arrays.asList(0L);
        ticketErrorCauseNewDTOUnderTest.lstIdsInactive = Arrays.asList(0L);
        ticketErrorCauseNewDTOUnderTest.description = "description";
        ticketErrorCauseNewDTOUnderTest.parentId = 0L;
        ticketErrorCauseNewDTOUnderTest.parentIdLv1 = 0L;
        ticketErrorCauseNewDTOUnderTest.parentIdLv2 = 0L;
        ticketErrorCauseNewDTOUnderTest.parentIdLv3 = 0L;
        ticketErrorCauseNewDTOUnderTest.lstParentId = Arrays.asList(0L);
        ticketErrorCauseNewDTOUnderTest.levelError = 0L;
        ticketErrorCauseNewDTOUnderTest.ticketErrorLevelOne = 0L;
        ticketErrorCauseNewDTOUnderTest.ticketErrorLevelTwo = 0L;
        ticketErrorCauseNewDTOUnderTest.statusType = Arrays.asList("value");
        ticketErrorCauseNewDTOUnderTest.search = 0L;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketErrorCauseNewDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketErrorCauseNewDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketErrorCauseNewDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketErrorCauseNewDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
