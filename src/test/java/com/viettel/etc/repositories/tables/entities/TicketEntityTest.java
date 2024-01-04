package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketEntityTest {

    private TicketEntity ticketEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketEntityUnderTest = new TicketEntity();
        ticketEntityUnderTest.ticketId = 0L;
        ticketEntityUnderTest.custId = 0L;
        ticketEntityUnderTest.contractNo = "contractNo";
        ticketEntityUnderTest.custTypeId = 0L;
        ticketEntityUnderTest.plateNumber = "plateNumber";
        ticketEntityUnderTest.phoneNumber = "phoneNumber";
        ticketEntityUnderTest.custName = "custName";
        ticketEntityUnderTest.email = "email";
        ticketEntityUnderTest.custAddress = "custAddress";
        ticketEntityUnderTest.priorityId = 0L;
        ticketEntityUnderTest.sourceId = 0L;
        ticketEntityUnderTest.l1TicketTypeId = 0L;
        ticketEntityUnderTest.l2TicketTypeId = 0L;
        ticketEntityUnderTest.l3TicketTypeId = 0L;
        ticketEntityUnderTest.location = "location";
        ticketEntityUnderTest.areaCode = "areaCode";
        ticketEntityUnderTest.contentReceive = "contentReceive";
        ticketEntityUnderTest.ticketKind = 0L;
        ticketEntityUnderTest.supportInfo = "supportInfo";
        ticketEntityUnderTest.requestDate = mock(Date.class);
        ticketEntityUnderTest.slaDate = mock(Date.class);
        ticketEntityUnderTest.note = "note";
        ticketEntityUnderTest.status = 0L;
        ticketEntityUnderTest.responseStatus = 0L;
        ticketEntityUnderTest.reopenDate = mock(Date.class);
        ticketEntityUnderTest.assignType = 0L;
        ticketEntityUnderTest.createUser = "createUser";
        ticketEntityUnderTest.createDate = mock(Date.class);
        ticketEntityUnderTest.updateUser = "updateUser";
        ticketEntityUnderTest.updateDate = mock(Date.class);
        ticketEntityUnderTest.plateTypeCode = "plateTypeCode";
        ticketEntityUnderTest.provinceName = "provinceName";
        ticketEntityUnderTest.districtName = "districtName";
        ticketEntityUnderTest.communeName = "communeName";
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = ticketEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
