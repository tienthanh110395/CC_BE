package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketSlaEntityTest {

    private TicketSlaEntity ticketSlaEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketSlaEntityUnderTest = new TicketSlaEntity();
        ticketSlaEntityUnderTest.ticketSlaId = 0L;
        ticketSlaEntityUnderTest.slaName = "slaName";
        ticketSlaEntityUnderTest.sla = 1L;
        ticketSlaEntityUnderTest.description = "description";
        ticketSlaEntityUnderTest.status = TicketSlaEntity.Status.INVALID.value;
        ticketSlaEntityUnderTest.siteId = 0L;
        ticketSlaEntityUnderTest.sourceId = 0L;
        ticketSlaEntityUnderTest.ticketTypeId = 0L;
        ticketSlaEntityUnderTest.createUser = "createUser";
        ticketSlaEntityUnderTest.createDate = mock(Date.class);
        ticketSlaEntityUnderTest.updateUser = "updateUser";
        ticketSlaEntityUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = ticketSlaEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketSlaEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketSlaEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketSlaEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
