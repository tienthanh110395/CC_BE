package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSLADetailDTOTest {

    private TicketSLADetailDTO ticketSLADetailDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSLADetailDTOUnderTest = new TicketSLADetailDTO(0L, 0L, 0L, 0L, 0L, 0L, 0L, "ticketGroupName",
                "ticketGenreName", "ticketTypeName", "priorityName", 0L, 0L, "receptionTimeFrom", "receptionTimeTo",
                0L);
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketSLADetailDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketSLADetailDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSLADetailDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
//        assertThat(ticketSLADetailDTOUnderTest.toString()).isEqualTo("result");
    }

}
