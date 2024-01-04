package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSiteDTOTest {

    private TicketSiteDTO ticketSiteDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteDTOUnderTest = new TicketSiteDTO();
        ticketSiteDTOUnderTest.siteId = 0L;
        ticketSiteDTOUnderTest.siteCode = "siteCode";
        ticketSiteDTOUnderTest.siteName = "siteName";
        ticketSiteDTOUnderTest.parentId = 0L;
        ticketSiteDTOUnderTest.address = "address";
        ticketSiteDTOUnderTest.username = "username";
        ticketSiteDTOUnderTest.password = "password";
        ticketSiteDTOUnderTest.email = "email";
        ticketSiteDTOUnderTest.phone = "phone";
        ticketSiteDTOUnderTest.status = 0L;
        ticketSiteDTOUnderTest.createUser = "createUser";
        ticketSiteDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketSiteDTOUnderTest.updateUser = "updateUser";
        ticketSiteDTOUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
    }

    @Test
    void testEquals() {
        final boolean result = ticketSiteDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketSiteDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketSiteDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketSiteDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
