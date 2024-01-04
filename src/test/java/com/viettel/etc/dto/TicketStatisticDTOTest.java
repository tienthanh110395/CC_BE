package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketStatisticDTOTest {

    private TicketStatisticDTO ticketStatisticDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketStatisticDTOUnderTest = new TicketStatisticDTO();
        ticketStatisticDTOUnderTest.statisticId = 0L;
        ticketStatisticDTOUnderTest.contractNoUserName = "contractNoUserName";
        ticketStatisticDTOUnderTest.plateNumber = "plateNumber";
        ticketStatisticDTOUnderTest.systemPhoneNumber = "systemPhoneNumber";
        ticketStatisticDTOUnderTest.callPhoneNumber = "callPhoneNumber";
        ticketStatisticDTOUnderTest.sourceId = 0L;
        ticketStatisticDTOUnderTest.l1StatisticTypeId = 0L;
        ticketStatisticDTOUnderTest.l2StatisticTypeId = 0L;
        ticketStatisticDTOUnderTest.l3StatisticTypeId = 0L;
        ticketStatisticDTOUnderTest.l4StatisticTypeId = 0L;
        ticketStatisticDTOUnderTest.l5StatisticTypeId = 0L;
        ticketStatisticDTOUnderTest.statisticContent = "statisticContent";
        ticketStatisticDTOUnderTest.custReaction = "custReaction";
        ticketStatisticDTOUnderTest.createUser = "createUser";
        ticketStatisticDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketStatisticDTOUnderTest.fromDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketStatisticDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketStatisticDTOUnderTest.updateUser = "updateUser";
        ticketStatisticDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketStatisticDTOUnderTest.l1StatisticTypeName = "l1StatisticTypeName";
        ticketStatisticDTOUnderTest.l2StatisticTypeName = "l2StatisticTypeName";
        ticketStatisticDTOUnderTest.l3StatisticTypeName = "l3StatisticTypeName";
        ticketStatisticDTOUnderTest.l4StatisticTypeName = "l4StatisticTypeName";
        ticketStatisticDTOUnderTest.l5StatisticTypeName = "l5StatisticTypeName";
        ticketStatisticDTOUnderTest.sourceName = "sourceName";
        ticketStatisticDTOUnderTest.contractNoUserNames = "contractNoUserNames";
        ticketStatisticDTOUnderTest.plateNumbers = "plateNumbers";
        ticketStatisticDTOUnderTest.systemPhoneNumbers = "systemPhoneNumbers";
        ticketStatisticDTOUnderTest.callPhoneNumbers = "callPhoneNumbers";
        ticketStatisticDTOUnderTest.reactionCustomerType = Arrays.asList("value");
        ticketStatisticDTOUnderTest.resultSqlEx = false;
        ticketStatisticDTOUnderTest.startrecord = 0;
        ticketStatisticDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketStatisticDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketStatisticDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketStatisticDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketStatisticDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
