package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketSourceEntityTest {

    private TicketSourceEntity ticketSourceEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketSourceEntityUnderTest = new TicketSourceEntity();
        ticketSourceEntityUnderTest.ticketSourceId = 0L;
        ticketSourceEntityUnderTest.name = "name";
        ticketSourceEntityUnderTest.sourceCode = "sourceCode";
        ticketSourceEntityUnderTest.description = "description";
        ticketSourceEntityUnderTest.status = TicketSourceEntity.Status.INVALID.value;
        ticketSourceEntityUnderTest.createUser = "createUser";
        ticketSourceEntityUnderTest.createDate = mock(Date.class);
        ticketSourceEntityUnderTest.updateUser = "updateUser";
        ticketSourceEntityUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = ticketSourceEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketSourceEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketSourceEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketSourceEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
