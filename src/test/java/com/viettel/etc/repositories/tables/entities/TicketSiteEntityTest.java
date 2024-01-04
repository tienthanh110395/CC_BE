package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketSiteEntityTest {

    private TicketSiteEntity ticketSiteEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketSiteEntityUnderTest = new TicketSiteEntity();
        ticketSiteEntityUnderTest.siteId = 0L;
        ticketSiteEntityUnderTest.siteCode = "siteCode";
        ticketSiteEntityUnderTest.siteName = "siteName";
        ticketSiteEntityUnderTest.parentId = 0L;
        ticketSiteEntityUnderTest.address = "address";
        ticketSiteEntityUnderTest.username = "username";
        ticketSiteEntityUnderTest.password = "password";
        ticketSiteEntityUnderTest.email = "email";
        ticketSiteEntityUnderTest.phone = "phone";
        ticketSiteEntityUnderTest.status = TicketSiteEntity.Status.VALID.value;
        ticketSiteEntityUnderTest.createUser = "createUser";
        ticketSiteEntityUnderTest.createDate = mock(Date.class);
        ticketSiteEntityUnderTest.updateUser = "updateUser";
        ticketSiteEntityUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketSiteEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSiteEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketSiteEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketSiteEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
