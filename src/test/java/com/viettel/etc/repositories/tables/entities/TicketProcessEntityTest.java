package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketProcessEntityTest {

    private TicketProcessEntity ticketProcessEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketProcessEntityUnderTest = new TicketProcessEntity();
        ticketProcessEntityUnderTest.ticketProcessId = 0L;
        ticketProcessEntityUnderTest.ticketId = 0L;
        ticketProcessEntityUnderTest.destroyReason = "destroyReason";
        ticketProcessEntityUnderTest.processResult = "processResult";
        ticketProcessEntityUnderTest.processTime = mock(Date.class);
        ticketProcessEntityUnderTest.status = 1L;
        ticketProcessEntityUnderTest.reasonLevel1 = "reasonLevel1";
        ticketProcessEntityUnderTest.reasonLevel2 = "reasonLevel2";
        ticketProcessEntityUnderTest.processContent = "processContent";
        ticketProcessEntityUnderTest.staffCode = "staffCode";
        ticketProcessEntityUnderTest.staffName = "staffName";
        ticketProcessEntityUnderTest.createUser = "createUser";
        ticketProcessEntityUnderTest.createDate = mock(Date.class);
        ticketProcessEntityUnderTest.updateUser = "updateUser";
        ticketProcessEntityUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = ticketProcessEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketProcessEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketProcessEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketProcessEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
