package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketSourceDTOTest {

    private TicketSourceDTO ticketSourceDTOUnderTest;

    @BeforeEach
    void setUp() {
        ticketSourceDTOUnderTest = new TicketSourceDTO();
        ticketSourceDTOUnderTest.ticketSourceId = 0L;
        ticketSourceDTOUnderTest.name = "name";
        ticketSourceDTOUnderTest.sourceCode = "sourceCode";
        ticketSourceDTOUnderTest.description = "description";
        ticketSourceDTOUnderTest.status = 0L;
        ticketSourceDTOUnderTest.createUser = "createUser";
        ticketSourceDTOUnderTest.createDate = mock(Date.class);
        ticketSourceDTOUnderTest.updateUser = "updateUser";
        ticketSourceDTOUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() {
        final boolean result = ticketSourceDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketSourceDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketSourceDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketSourceDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
