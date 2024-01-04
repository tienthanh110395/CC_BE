package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketAssignLogEntityTest {

    private TicketAssignLogEntity ticketAssignLogEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignLogEntityUnderTest = new TicketAssignLogEntity();
        ticketAssignLogEntityUnderTest.ticketAssignLogId = 0L;
        ticketAssignLogEntityUnderTest.ticketAssignId = 0L;
        ticketAssignLogEntityUnderTest.ticketId = 0L;
        ticketAssignLogEntityUnderTest.logContent = "logContent";
        ticketAssignLogEntityUnderTest.siteId = 0L;
        ticketAssignLogEntityUnderTest.createUser = "createUser";
        ticketAssignLogEntityUnderTest.createDate = mock(Date.class);
        ticketAssignLogEntityUnderTest.updateUser = "updateUser";
        ticketAssignLogEntityUnderTest.updateDate = mock(Date.class);
        ticketAssignLogEntityUnderTest.logType = 0L;
    }

    @Test
    void testEquals() throws Exception {
        // Setup

        // Run the test
        final boolean result = ticketAssignLogEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        // Setup

        // Run the test
        final int result = ticketAssignLogEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        // Setup

        // Run the test
        final String result = ticketAssignLogEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        // Setup

        // Run the test
        final boolean result = ticketAssignLogEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
