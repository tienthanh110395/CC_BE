package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketAssignProcessEntityTest {

    private TicketAssignProcessEntity ticketAssignProcessEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignProcessEntityUnderTest = new TicketAssignProcessEntity();
        ticketAssignProcessEntityUnderTest.ticketAssignProcessId = 0L;
        ticketAssignProcessEntityUnderTest.ticketAssignId = 0L;
        ticketAssignProcessEntityUnderTest.ticketId = 0L;
        ticketAssignProcessEntityUnderTest.processContent = "processContent";
        ticketAssignProcessEntityUnderTest.processTime = mock(Date.class);
        ticketAssignProcessEntityUnderTest.processResult = "processResult";
        ticketAssignProcessEntityUnderTest.siteId = 0L;
        ticketAssignProcessEntityUnderTest.staffCode = "staffCode";
        ticketAssignProcessEntityUnderTest.staffName = "staffName";
        ticketAssignProcessEntityUnderTest.createUser = "createUser";
        ticketAssignProcessEntityUnderTest.createDate = mock(Date.class);
        ticketAssignProcessEntityUnderTest.updateUser = "updateUser";
        ticketAssignProcessEntityUnderTest.updateDate = mock(Date.class);
        ticketAssignProcessEntityUnderTest.actTypeId = 0L;
        ticketAssignProcessEntityUnderTest.status = TicketAssignProcessEntity.Status.IN_PROGRESS.value;
    }

    @Test
    void testEquals() throws Exception {
        // Setup

        // Run the test
        final boolean result = ticketAssignProcessEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        // Setup

        // Run the test
        final int result = ticketAssignProcessEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        // Setup

        // Run the test
        final String result = ticketAssignProcessEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        // Setup

        // Run the test
        final boolean result = ticketAssignProcessEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
