package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketProcessShareDetailEntityTest {

    private TicketProcessShareDetailEntity ticketProcessShareDetailEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareDetailEntityUnderTest = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntityUnderTest.ticketProcessShareDetailId = 0L;
        ticketProcessShareDetailEntityUnderTest.ticketProcessShareId = 0L;
        ticketProcessShareDetailEntityUnderTest.assignTime = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketProcessShareDetailEntityUnderTest.ticketId = 0L;
        ticketProcessShareDetailEntityUnderTest.processUser = "processUser";
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketProcessShareDetailEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketProcessShareDetailEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketProcessShareDetailEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketProcessShareDetailEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
