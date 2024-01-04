package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketAssignEntityTest {

    private TicketAssignEntity ticketAssignEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignEntityUnderTest = new TicketAssignEntity();
        ticketAssignEntityUnderTest.ticketAssignId = 0L;
        ticketAssignEntityUnderTest.ticketId = 0L;
        ticketAssignEntityUnderTest.siteId = 0L;
        ticketAssignEntityUnderTest.fromUsername = "fromUsername";
        ticketAssignEntityUnderTest.toUsername = "toUsername";
        ticketAssignEntityUnderTest.assignDate = mock(Date.class);
        ticketAssignEntityUnderTest.resolveDate = mock(Date.class);
        ticketAssignEntityUnderTest.fromSiteId = 0L;
        ticketAssignEntityUnderTest.slaDate = mock(Date.class);
        ticketAssignEntityUnderTest.assignType = 0L;
        ticketAssignEntityUnderTest.ticketStatus = TicketAssignEntity.TicketStatus.NEW.value;
        ticketAssignEntityUnderTest.userProcess = "userProcess";
        ticketAssignEntityUnderTest.toSiteId = 0L;
        ticketAssignEntityUnderTest.assignLevel = 0L;
        ticketAssignEntityUnderTest.assignContent = "assignContent";
        ticketAssignEntityUnderTest.createUser = "createUser";
        ticketAssignEntityUnderTest.createDate = mock(Date.class);
        ticketAssignEntityUnderTest.updateUser = "updateUser";
        ticketAssignEntityUnderTest.updateDate = mock(Date.class);
        ticketAssignEntityUnderTest.toSiteL2Id = 0L;
        ticketAssignEntityUnderTest.toSiteEmail = "toSiteEmail";
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ticketAssignEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketAssignEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketAssignEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ticketAssignEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
