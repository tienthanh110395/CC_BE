package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSiteSearchDTOTest {

    private TicketSiteSearchDTO ticketSiteSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteSearchDTOUnderTest = new TicketSiteSearchDTO();
        ticketSiteSearchDTOUnderTest.ticketSiteId = 0L;
        ticketSiteSearchDTOUnderTest.ticketSiteCode = "ticketSiteCode";
        ticketSiteSearchDTOUnderTest.ticketSiteName = "ticketSiteName";
        ticketSiteSearchDTOUnderTest.parentId = 0L;
        ticketSiteSearchDTOUnderTest.createUser = "createUser";
        ticketSiteSearchDTOUnderTest.startRecord = 0;
        ticketSiteSearchDTOUnderTest.pageSize = 0;
        ticketSiteSearchDTOUnderTest.inEffect = false;
        ticketSiteSearchDTOUnderTest.expire = false;
        ticketSiteSearchDTOUnderTest.formDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketSiteSearchDTOUnderTest.toDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketSiteSearchDTOUnderTest.status = Arrays.asList(0L);
        ticketSiteSearchDTOUnderTest.levelSite = 0L;
        ticketSiteSearchDTOUnderTest.lstTicketSite = Arrays.asList(0L);
//        ticketSiteSearchDTOUnderTest.lstTicketSiteLv2 = Arrays.asList(0L);
//        ticketSiteSearchDTOUnderTest.lstTicketSiteLv3 = Arrays.asList(0L);
        ticketSiteSearchDTOUnderTest.lstSiteLv = Arrays.asList(0L);
        ticketSiteSearchDTOUnderTest.siteUser = "siteUser";
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketSiteSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketSiteSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSiteSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketSiteSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
