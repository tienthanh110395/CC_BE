package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSmsMailPushEntityTest {

    private TicketSmsMailPushEntity ticketSmsMailPushEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSmsMailPushEntityUnderTest = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntityUnderTest.ticketSmsMailPushId = 0L;
        ticketSmsMailPushEntityUnderTest.ticketId = 0L;
        ticketSmsMailPushEntityUnderTest.smsMailPushType = "smsMailPushType";
        ticketSmsMailPushEntityUnderTest.message = "message";
        ticketSmsMailPushEntityUnderTest.phone = "phone";
        ticketSmsMailPushEntityUnderTest.email = "email";
        ticketSmsMailPushEntityUnderTest.mobileId = "mobileId";
        ticketSmsMailPushEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketSmsMailPushEntityUnderTest.createUser = "createUser";
        ticketSmsMailPushEntityUnderTest.status = 0L;
        ticketSmsMailPushEntityUnderTest.errorMessage = "errorMessage";
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketSmsMailPushEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSmsMailPushEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketSmsMailPushEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketSmsMailPushEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
