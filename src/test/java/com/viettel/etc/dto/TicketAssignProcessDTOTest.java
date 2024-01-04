package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TicketAssignProcessDTOTest {

    private TicketAssignProcessDTO ticketAssignProcessDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignProcessDTOUnderTest = new TicketAssignProcessDTO();
        ticketAssignProcessDTOUnderTest.ticketAssignProcessId = 0L;
        ticketAssignProcessDTOUnderTest.ticketAssignId = 0L;
        ticketAssignProcessDTOUnderTest.ticketId = 0L;
        ticketAssignProcessDTOUnderTest.processContent = "processContent";
        ticketAssignProcessDTOUnderTest.processTime = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAssignProcessDTOUnderTest.processResult = "processResult";
        ticketAssignProcessDTOUnderTest.siteId = 0L;
        ticketAssignProcessDTOUnderTest.staffCode = "staffCode";
        ticketAssignProcessDTOUnderTest.staffName = "staffName";
        ticketAssignProcessDTOUnderTest.createUser = "createUser";
        ticketAssignProcessDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAssignProcessDTOUnderTest.updateUser = "updateUser";
        ticketAssignProcessDTOUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAssignProcessDTOUnderTest.actTypeId = 0L;
        ticketAssignProcessDTOUnderTest.attachmentFiles = Arrays.asList(new FileDTO());
        ticketAssignProcessDTOUnderTest.resultSqlEx = false;
        ticketAssignProcessDTOUnderTest.status = "status";
        ticketAssignProcessDTOUnderTest.startrecord = 0;
        ticketAssignProcessDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketAssignProcessDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketAssignProcessDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketAssignProcessDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketAssignProcessDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
