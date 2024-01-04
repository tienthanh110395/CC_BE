package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSiteUserDTOTest {

    private TicketSiteUserDTO ticketSiteUserDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteUserDTOUnderTest = new TicketSiteUserDTO();
        ticketSiteUserDTOUnderTest.ticketSiteUserId = 0L;
        ticketSiteUserDTOUnderTest.siteId = 0L;
        ticketSiteUserDTOUnderTest.siteName = "siteName";
        ticketSiteUserDTOUnderTest.userId = "userId";
        ticketSiteUserDTOUnderTest.userName = "userName";
        ticketSiteUserDTOUnderTest.staffCode = "staffCode";
        ticketSiteUserDTOUnderTest.staffName = "staffName";
        ticketSiteUserDTOUnderTest.status = 0L;
        ticketSiteUserDTOUnderTest.createUser = "createUser";
        ticketSiteUserDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketSiteUserDTOUnderTest.updateUser = "updateUser";
        ticketSiteUserDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketSiteUserDTOUnderTest.email = "email";
        ticketSiteUserDTOUnderTest.phone = "phone";
        ticketSiteUserDTOUnderTest.keySearch = "keySearch";
        ticketSiteUserDTOUnderTest.startrecord = 0;
        ticketSiteUserDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketSiteUserDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketSiteUserDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketSiteUserDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketSiteUserDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
