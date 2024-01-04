package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketStatusEntityTest {

    private TicketStatusEntity ticketStatusEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketStatusEntityUnderTest = new TicketStatusEntity();
        ticketStatusEntityUnderTest.ticketStatusId = 0L;
        ticketStatusEntityUnderTest.ticketId = 0L;
        ticketStatusEntityUnderTest.siteId = 0L;
        ticketStatusEntityUnderTest.ticketStatus = TicketStatusEntity.TicketStatus.NEW.value;
        ticketStatusEntityUnderTest.processTime = mock(Date.class);
        ticketStatusEntityUnderTest.note = "note";
        ticketStatusEntityUnderTest.createUser = "createUser";
        ticketStatusEntityUnderTest.createDate = mock(Date.class);
        ticketStatusEntityUnderTest.updateUser = "updateUser";
        ticketStatusEntityUnderTest.updateDate = mock(Date.class);
        ticketStatusEntityUnderTest.processContent = "process_content";
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = ticketStatusEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketStatusEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketStatusEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketStatusEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
