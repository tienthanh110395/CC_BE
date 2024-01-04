package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketTypeDTOTest {

    private TicketTypeDTO ticketTypeDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketTypeDTOUnderTest = new TicketTypeDTO();
        ticketTypeDTOUnderTest.ticketTypeId = 0L;
        ticketTypeDTOUnderTest.name = "name";
        ticketTypeDTOUnderTest.code = "code";
        ticketTypeDTOUnderTest.description = "description";
        ticketTypeDTOUnderTest.parentId = "parentId";
        ticketTypeDTOUnderTest.status = 0L;
        ticketTypeDTOUnderTest.createUser = "createUser";
        ticketTypeDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketTypeDTOUnderTest.updateUser = "updateUser";
        ticketTypeDTOUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketTypeDTOUnderTest.level = 0L;
        ticketTypeDTOUnderTest.type = 0L;
        ticketTypeDTOUnderTest.ticketTemplate = "ticketTemplate";
        ticketTypeDTOUnderTest.deadTimeType = 0L;
        ticketTypeDTOUnderTest.hotSla = 0L;
        ticketTypeDTOUnderTest.othersSla = 0L;
    }

    @Test
    void testEquals() {
        final boolean result = ticketTypeDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketTypeDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketTypeDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketTypeDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
