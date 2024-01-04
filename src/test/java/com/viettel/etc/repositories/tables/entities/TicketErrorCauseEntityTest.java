package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketErrorCauseEntityTest {

    private TicketErrorCauseEntity ticketErrorCauseEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketErrorCauseEntityUnderTest = new TicketErrorCauseEntity();
        ticketErrorCauseEntityUnderTest.ticketErrorCauseId = 0L;
        ticketErrorCauseEntityUnderTest.name = "name";
        ticketErrorCauseEntityUnderTest.code = "code";
        ticketErrorCauseEntityUnderTest.description = "description";
        ticketErrorCauseEntityUnderTest.parentId = 0L;
        ticketErrorCauseEntityUnderTest.status = 0L;
        ticketErrorCauseEntityUnderTest.createUser = "createUser";
        ticketErrorCauseEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketErrorCauseEntityUnderTest.updateUser = "updateUser";
        ticketErrorCauseEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketErrorCauseEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketErrorCauseEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketErrorCauseEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketErrorCauseEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
