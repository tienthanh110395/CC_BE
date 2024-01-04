package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketExpireCauseNewDTOTest {

    private TicketExpireCauseNewDTO ticketExpireCauseNewDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExpireCauseNewDTOUnderTest = new TicketExpireCauseNewDTO();
        ticketExpireCauseNewDTOUnderTest.ticketExpireCauseId = 0L;
        ticketExpireCauseNewDTOUnderTest.ticketExpireCauseOneId = 0L;
        ticketExpireCauseNewDTOUnderTest.ticketExpireCauseTwoId = 0L;
        ticketExpireCauseNewDTOUnderTest.ticketExpireCauseThreeId = 0L;
        ticketExpireCauseNewDTOUnderTest.expireCauseName = "expireCauseName";
        ticketExpireCauseNewDTOUnderTest.expireCauseNameParent = "expireCauseNameParent";
        ticketExpireCauseNewDTOUnderTest.expireCauseCode = "expireCauseCode";
        ticketExpireCauseNewDTOUnderTest.parentId = 0L;
        ticketExpireCauseNewDTOUnderTest.parentOneId = 0L;
        ticketExpireCauseNewDTOUnderTest.parentTwoId = 0L;
        ticketExpireCauseNewDTOUnderTest.parentThreeId = 0L;
        ticketExpireCauseNewDTOUnderTest.description = "description";
        ticketExpireCauseNewDTOUnderTest.status = 0L;
        ticketExpireCauseNewDTOUnderTest.createUser = "createUser";
        ticketExpireCauseNewDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExpireCauseNewDTOUnderTest.updateUser = "updateUser";
        ticketExpireCauseNewDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketExpireCauseNewDTOUnderTest.statusType = Arrays.asList("value");
        ticketExpireCauseNewDTOUnderTest.levelExpire = 0L;
        ticketExpireCauseNewDTOUnderTest.ticketExpireLevelOne = 0L;
        ticketExpireCauseNewDTOUnderTest.ticketExpireLevelTwo = 0L;
        ticketExpireCauseNewDTOUnderTest.lstIdsActive = Arrays.asList(0L);
        ticketExpireCauseNewDTOUnderTest.lstIdsInactive = Arrays.asList(0L);
        ticketExpireCauseNewDTOUnderTest.actTypeId = 0L;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketExpireCauseNewDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketExpireCauseNewDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketExpireCauseNewDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketExpireCauseNewDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
