package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketExtentEntityTest {

    private TicketExtentEntity ticketExtentEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExtentEntityUnderTest = new TicketExtentEntity();
        ticketExtentEntityUnderTest.ticketExtentId = 0L;
        ticketExtentEntityUnderTest.ticketId = 0L;
        ticketExtentEntityUnderTest.extentDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketExtentEntityUnderTest.extentReasonId = 0L;
        ticketExtentEntityUnderTest.extentReasonCode = "extentReasonCode";
        ticketExtentEntityUnderTest.extentReasonName = "extentReasonName";
        ticketExtentEntityUnderTest.requestExtentDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketExtentEntityUnderTest.managerUserName = "managerUserName";
        ticketExtentEntityUnderTest.managerPhone = "managerPhone";
        ticketExtentEntityUnderTest.status = 0L;
        ticketExtentEntityUnderTest.approveUserName = "approveUserName";
        ticketExtentEntityUnderTest.approveDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketExtentEntityUnderTest.approveReason = "approveReason";
        ticketExtentEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketExtentEntityUnderTest.createUser = "createUser";
        ticketExtentEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketExtentEntityUnderTest.updateUser = "updateUser";
    }

    @Test
    void testEquals() {
        final boolean result = ticketExtentEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketExtentEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketExtentEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketExtentEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
