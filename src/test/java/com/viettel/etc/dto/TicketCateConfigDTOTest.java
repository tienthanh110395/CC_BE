package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketCateConfigDTOTest {

    private TicketCateConfigDTO ticketCateConfigDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketCateConfigDTOUnderTest = new TicketCateConfigDTO();
        ticketCateConfigDTOUnderTest.ticketTypeId = 0L;
        ticketCateConfigDTOUnderTest.ticketTypeName = "ticketTypeName";
        ticketCateConfigDTOUnderTest.ticketTypeCode = "ticketTypeCode";
        ticketCateConfigDTOUnderTest.description = "description";
        ticketCateConfigDTOUnderTest.parentId = 0L;
        ticketCateConfigDTOUnderTest.lstParentId = Arrays.asList(0L);
        ticketCateConfigDTOUnderTest.status = 0L;
        ticketCateConfigDTOUnderTest.createUser = "createUser";
        ticketCateConfigDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketCateConfigDTOUnderTest.updateUser = "updateUser";
        ticketCateConfigDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketCateConfigDTOUnderTest.isCpt = 0L;
        ticketCateConfigDTOUnderTest.type = 0L;
        ticketCateConfigDTOUnderTest.ticketTemplate = "ticketTemplate";
        ticketCateConfigDTOUnderTest.deadTimeType = 0L;
        ticketCateConfigDTOUnderTest.startRecord = 0;
        ticketCateConfigDTOUnderTest.pageSize = 0;
        ticketCateConfigDTOUnderTest.ticketGenre = "ticketGenre";
        ticketCateConfigDTOUnderTest.ticketGroup = "ticketGroup";
        ticketCateConfigDTOUnderTest.levelTt = 0L;
        ticketCateConfigDTOUnderTest.formDate = "formDate";
        ticketCateConfigDTOUnderTest.toDate = "toDate";
        ticketCateConfigDTOUnderTest.ticketTypeGroupId = 0L;
        ticketCateConfigDTOUnderTest.lstIdsActive = Arrays.asList(0L);
        ticketCateConfigDTOUnderTest.lstIdsInactive = Arrays.asList(0L);
        ticketCateConfigDTOUnderTest.lstIds = Arrays.asList(0L);
        ticketCateConfigDTOUnderTest.search = 0L;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketCateConfigDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketCateConfigDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketCateConfigDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketCateConfigDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
