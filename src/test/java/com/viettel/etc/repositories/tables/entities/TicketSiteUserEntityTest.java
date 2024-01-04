package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketSiteUserEntityTest {

    private TicketSiteUserEntity ticketSiteUserEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketSiteUserEntityUnderTest = new TicketSiteUserEntity();
        ticketSiteUserEntityUnderTest.ticketSiteUserId = 0L;
        ticketSiteUserEntityUnderTest.siteId = 0L;
        ticketSiteUserEntityUnderTest.userId = "userId";
        ticketSiteUserEntityUnderTest.userName = "userName";
        ticketSiteUserEntityUnderTest.staffCode = "staffCode";
        ticketSiteUserEntityUnderTest.staffName = "staffName";
        ticketSiteUserEntityUnderTest.status = TicketSiteUserEntity.Status.VALID.value;
        ticketSiteUserEntityUnderTest.createUser = "createUser";
        ticketSiteUserEntityUnderTest.createDate = mock(Date.class);
        ticketSiteUserEntityUnderTest.updateUser = "updateUser";
        ticketSiteUserEntityUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = ticketSiteUserEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketSiteUserEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketSiteUserEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketSiteUserEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
