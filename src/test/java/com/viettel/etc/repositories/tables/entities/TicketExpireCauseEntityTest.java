package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketExpireCauseEntityTest {

    private TicketExpireCauseEntity ticketExpireCauseEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExpireCauseEntityUnderTest = new TicketExpireCauseEntity();
        ticketExpireCauseEntityUnderTest.ticketExpireCauseId = 0L;
        ticketExpireCauseEntityUnderTest.name = "name";
        ticketExpireCauseEntityUnderTest.code = "code";
        ticketExpireCauseEntityUnderTest.description = "description";
        ticketExpireCauseEntityUnderTest.parentId = 0L;
        ticketExpireCauseEntityUnderTest.status = 0L;
        ticketExpireCauseEntityUnderTest.createUser = "createUser";
        ticketExpireCauseEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketExpireCauseEntityUnderTest.updateUser = "updateUser";
        ticketExpireCauseEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketExpireCauseEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketExpireCauseEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketExpireCauseEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketExpireCauseEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
