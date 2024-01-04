package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSiteUserKeyCloakDTOTest {

    private TicketSiteUserKeyCloakDTO ticketSiteUserKeyCloakDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteUserKeyCloakDTOUnderTest = new TicketSiteUserKeyCloakDTO();
        ticketSiteUserKeyCloakDTOUnderTest.id = "id";
        ticketSiteUserKeyCloakDTOUnderTest.username = "username";
        ticketSiteUserKeyCloakDTOUnderTest.createdTimestamp = "createdTimestamp";
        ticketSiteUserKeyCloakDTOUnderTest.enabled = false;
        ticketSiteUserKeyCloakDTOUnderTest.emailVerified = false;
        ticketSiteUserKeyCloakDTOUnderTest.lastName = "lastName";
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketSiteUserKeyCloakDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketSiteUserKeyCloakDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSiteUserKeyCloakDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketSiteUserKeyCloakDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
