package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TicketSiteConfigDTOTest {

    private TicketSiteConfigDTO ticketSiteConfigDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteConfigDTOUnderTest = new TicketSiteConfigDTO();
        ticketSiteConfigDTOUnderTest.ticketSiteId = 0L;
        ticketSiteConfigDTOUnderTest.ticketSiteCode = "ticketSiteCode";
        ticketSiteConfigDTOUnderTest.ticketSiteName = "ticketSiteName";
        ticketSiteConfigDTOUnderTest.parentId = 0L;
//        ticketSiteConfigDTOUnderTest.ticketSiteLv = 0L;
//        ticketSiteConfigDTOUnderTest.ticketSiteLv1 = "ticketSiteLv1";
//        ticketSiteConfigDTOUnderTest.ticketSiteLv2 = "ticketSiteLv2";
//        ticketSiteConfigDTOUnderTest.ticketSiteLv1Id = 0L;
//        ticketSiteConfigDTOUnderTest.ticketSiteLv2Id = 0L;
        ticketSiteConfigDTOUnderTest.lstParentId = Arrays.asList(0L);
        ticketSiteConfigDTOUnderTest.status = 0L;
        ticketSiteConfigDTOUnderTest.levelSite = 0L;
        ticketSiteConfigDTOUnderTest.createUser = "createUser";
        ticketSiteConfigDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketSiteConfigDTOUnderTest.updateUser = "updateUser";
        ticketSiteConfigDTOUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketSiteConfigDTOUnderTest.formDate = "formDate";
        ticketSiteConfigDTOUnderTest.toDate = "toDate";
        ticketSiteConfigDTOUnderTest.lstIdsActive = Arrays.asList(0L);
        ticketSiteConfigDTOUnderTest.lstIdsInactive = Arrays.asList(0L);
        ticketSiteConfigDTOUnderTest.lstIds = Arrays.asList(0L);
        ticketSiteConfigDTOUnderTest.lstUserHandle = Arrays.asList(new TicketSiteConfigDTO.UserHandle());
        ticketSiteConfigDTOUnderTest.siteUser = "siteUser";
        ticketSiteConfigDTOUnderTest.siteNameParent = "siteNameParent";
    }

    @Test
    void testEquals() throws Exception {
        assertThat(ticketSiteConfigDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(ticketSiteConfigDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ticketSiteConfigDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ticketSiteConfigDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
