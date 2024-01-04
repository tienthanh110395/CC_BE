package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSLANewDTOTest {

    private TicketSLANewDTO ticketSLANewDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSLANewDTOUnderTest = new TicketSLANewDTO(0L, "slaName", 0L, "description", 0L, 0L, 0L, 0L, 0L,
                "createUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "updateUser",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), false, 0L, "ticketGroupName",
                "ticketGroupCode", "ticketGenreName", "ticketGenreCode", "ticketTypeName", "ticketTypeCode",
                "priorityName", 0L, 0L, "receptionTimeFrom", "receptionTimeTo", 0L, Arrays.asList(0L),
                Arrays.asList(new TicketLevelCateDTO()), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "ticketGroupStatus", "ticketGenreStatus",
                "ticketTypeStatus","ticketTemplate","priorityCode");
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketSLANewDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketSLANewDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSLANewDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketSLANewDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final TicketSLANewDTO.TicketSLANewDTOBuilder result = TicketSLANewDTO.builder();

        // Verify the results
    }
}
