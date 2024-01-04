package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketProcessShareEntityTest {

    private TicketProcessShareEntity ticketProcessShareEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareEntityUnderTest = new TicketProcessShareEntity();
        ticketProcessShareEntityUnderTest.ticketProcessShareId = 0L;
        ticketProcessShareEntityUnderTest.assignTime = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketProcessShareEntityUnderTest.assignUser = "assignUser";
        ticketProcessShareEntityUnderTest.assignType = 0L;
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketProcessShareEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketProcessShareEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketProcessShareEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketProcessShareEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
