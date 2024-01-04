package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketExtentReasonEntityTest {

    private TicketExtentReasonEntity ticketExtentReasonEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExtentReasonEntityUnderTest = new TicketExtentReasonEntity();
        ticketExtentReasonEntityUnderTest.ticketExtentReasonId = 0L;
        ticketExtentReasonEntityUnderTest.name = "name";
        ticketExtentReasonEntityUnderTest.description = "description";
        ticketExtentReasonEntityUnderTest.status = 0L;
        ticketExtentReasonEntityUnderTest.createUser = "createUser";
        ticketExtentReasonEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketExtentReasonEntityUnderTest.updateUser = "updateUser";
        ticketExtentReasonEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketExtentReasonEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketExtentReasonEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketExtentReasonEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketExtentReasonEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
