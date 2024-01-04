package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketAdjustChargeEntityTest {

    private TicketAdjustChargeEntity ticketAdjustChargeEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAdjustChargeEntityUnderTest = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntityUnderTest.ticketAdjustChargeId = 0L;
        ticketAdjustChargeEntityUnderTest.ticketId = 0L;
        ticketAdjustChargeEntityUnderTest.plateTypeCode = "plateTypeCode";
        ticketAdjustChargeEntityUnderTest.plateNumber = "plateNumber";
        ticketAdjustChargeEntityUnderTest.payType = "payType";
        ticketAdjustChargeEntityUnderTest.contractNo = "contractNo";
        ticketAdjustChargeEntityUnderTest.accountType = "accountType";
        ticketAdjustChargeEntityUnderTest.adjustAmount = 0L;
        ticketAdjustChargeEntityUnderTest.reason = "reason";
        ticketAdjustChargeEntityUnderTest.adjustContent = "adjustContent";
        ticketAdjustChargeEntityUnderTest.requestDate = mock(Date.class);
        ticketAdjustChargeEntityUnderTest.status = TicketAdjustChargeEntity.Status.INVALID.value;
        ticketAdjustChargeEntityUnderTest.createUser = "createUser";
        ticketAdjustChargeEntityUnderTest.createDate = mock(Date.class);
        ticketAdjustChargeEntityUnderTest.updateUser = "updateUser";
        ticketAdjustChargeEntityUnderTest.updateDate = mock(Date.class);
        ticketAdjustChargeEntityUnderTest.siteId = 0L;
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketAdjustChargeEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketAdjustChargeEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketAdjustChargeEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketAdjustChargeEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
