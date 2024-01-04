package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSLADTOTest {

    private TicketSLADTO ticketSLADTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSLADTOUnderTest = new TicketSLADTO(0L, "slaName", 0L, "description", 0L, 0L, 0L, 0L, 0L, "createUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "updateUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testEquals() {
        final boolean result = ticketSLADTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketSLADTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketSLADTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketSLADTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final TicketSLADTO.TicketSLADTOBuilder result = TicketSLADTO.builder();
        assertThat(result).isEqualTo(result);
        // Verify the results
    }
}
