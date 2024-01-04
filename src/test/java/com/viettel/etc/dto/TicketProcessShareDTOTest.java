package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketProcessShareDetailEntity;
import com.viettel.etc.repositories.tables.entities.TicketProcessShareEntity;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketProcessShareDTOTest {

    private TicketProcessShareDTO ticketProcessShareDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareDTOUnderTest = new TicketProcessShareDTO();
        ticketProcessShareDTOUnderTest.ticketProcessShareId = 0L;
        ticketProcessShareDTOUnderTest.assignTime = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketProcessShareDTOUnderTest.assignUser = "assignUser";
        ticketProcessShareDTOUnderTest.assignType = 0L;
        ticketProcessShareDTOUnderTest.ticketIds = Arrays.asList(0L);
        ticketProcessShareDTOUnderTest.processUsers = Arrays.asList("value");
        ticketProcessShareDTOUnderTest.isSms = false;
        ticketProcessShareDTOUnderTest.startrecord = 0;
        ticketProcessShareDTOUnderTest.pagesize = 0;
        ticketProcessShareDTOUnderTest.actTypeId = 0L;
    }

    @Test
    void testToTicketProcessShareEntity() {
        // Setup
        final TicketProcessShareEntity expectedResult = new TicketProcessShareEntity();
        expectedResult.setTicketProcessShareId(0L);
        expectedResult.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setAssignUser("assignUser");
        expectedResult.setAssignType(0L);

        // Run the test
        final TicketProcessShareEntity result = ticketProcessShareDTOUnderTest.toTicketProcessShareEntity();

        // Verify the results
    }

    @Test
    void testToTicketProcessShareDetailEntity() {
        // Setup
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);

        final TicketProcessShareDetailEntity expectedResult = new TicketProcessShareDetailEntity();
        expectedResult.setTicketProcessShareDetailId(0L);
        expectedResult.setTicketProcessShareId(0L);
        expectedResult.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setTicketId(0L);
        expectedResult.setProcessUser("processUser");

        // Run the test
        final TicketProcessShareDetailEntity result = ticketProcessShareDTOUnderTest.toTicketProcessShareDetailEntity(ticketProcessShareEntity, 0L, "processUser");

        // Verify the results
    }

    @Test
    void testToTicketSmsMailPushEntity() {
        // Setup
        final TicketSmsMailPushEntity expectedResult = new TicketSmsMailPushEntity();
        expectedResult.setTicketSmsMailPushId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSmsMailPushType("smsMailPushType");
        expectedResult.setMessage("message");
        expectedResult.setPhone("phone");
        expectedResult.setEmail("email");
        expectedResult.setMobileId("mobileId");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setCreateUser("createUser");
        expectedResult.setStatus(0L);

        // Run the test
        final TicketSmsMailPushEntity result = ticketProcessShareDTOUnderTest.toTicketSmsMailPushEntity(0L, "content", "phone");

        // Verify the results
    }

    @Test
    void testEquals() {
        final boolean result = ticketProcessShareDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketProcessShareDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketProcessShareDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketProcessShareDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
