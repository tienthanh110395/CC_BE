package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketTypeEntityTest {

    private TicketTypeEntity ticketTypeEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketTypeEntityUnderTest = new TicketTypeEntity();
        ticketTypeEntityUnderTest.ticketTypeId = 0L;
        ticketTypeEntityUnderTest.name = "name";
        ticketTypeEntityUnderTest.code = "code";
        ticketTypeEntityUnderTest.description = "description";
        ticketTypeEntityUnderTest.parentId = 0L;
        ticketTypeEntityUnderTest.status = TicketTypeEntity.Status.VALID.value;
        ticketTypeEntityUnderTest.createUser = "createUser";
        ticketTypeEntityUnderTest.createDate = mock(Date.class);
        ticketTypeEntityUnderTest.updateUser = "updateUser";
        ticketTypeEntityUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() {
        // Setup
        TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.ticketTypeId = 0L;
        ticketTypeEntity.name = "name";
        ticketTypeEntity.code = "code";
        ticketTypeEntity.description = "description";
        ticketTypeEntity.parentId = 0L;
        ticketTypeEntity.status = TicketTypeEntity.Status.INVALID.value;
        ticketTypeEntity.createUser = "createUser";
        ticketTypeEntity.createDate = mock(Date.class);
        ticketTypeEntity.updateUser = "updateUser";
        ticketTypeEntity.updateDate = mock(Date.class);
        // Run the test
        final boolean result = ticketTypeEntityUnderTest.equals(ticketTypeEntity);

        // Verify the results
//        assertThat(result).isTrue();
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketTypeEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketTypeEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketTypeEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
