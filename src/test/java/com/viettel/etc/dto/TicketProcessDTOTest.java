package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TicketProcessDTOTest {

    private TicketProcessDTO ticketProcessDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessDTOUnderTest = new TicketProcessDTO();
        ticketProcessDTOUnderTest.ticketProcessId = 0L;
        ticketProcessDTOUnderTest.ticketId = 0L;
        ticketProcessDTOUnderTest.destroyReason = "destroyReason";
        ticketProcessDTOUnderTest.processResult = "processResult";
        ticketProcessDTOUnderTest.processTime = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketProcessDTOUnderTest.status = 0L;
        ticketProcessDTOUnderTest.reasonLevel1 = "reasonLevel1";
        ticketProcessDTOUnderTest.reasonLevel2 = "reasonLevel2";
        ticketProcessDTOUnderTest.processContent = "processContent";
        ticketProcessDTOUnderTest.staffCode = "staffCode";
        ticketProcessDTOUnderTest.staffName = "staffName";
        ticketProcessDTOUnderTest.createUser = "createUser";
        ticketProcessDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketProcessDTOUnderTest.updateUser = "updateUser";
        ticketProcessDTOUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketProcessDTOUnderTest.phoneNumber = "phoneNumber";
        ticketProcessDTOUnderTest.attachmentFiles = Arrays.asList(new FileDTO());
        ticketProcessDTOUnderTest.siteId = 0L;
        ticketProcessDTOUnderTest.resultSqlEx = false;
        ticketProcessDTOUnderTest.l1TicketTypeId = 0L;
        ticketProcessDTOUnderTest.l2TicketTypeId = 0L;
        ticketProcessDTOUnderTest.l3TicketTypeId = 0L;
        ticketProcessDTOUnderTest.groupPA = "groupPA";
        ticketProcessDTOUnderTest.subgroupPA = "subgroupPA";
        ticketProcessDTOUnderTest.detailPA = "detailPA";
        ticketProcessDTOUnderTest.priorityId = 0L;
        ticketProcessDTOUnderTest.listFiles = Arrays.asList(new TicketAttachmentEntity());
        ticketProcessDTOUnderTest.sourceId = 0L;
        ticketProcessDTOUnderTest.ticketErrorCauseIdL1 = 0L;
        ticketProcessDTOUnderTest.ticketErrorCauseIdL2 = 0L;
        ticketProcessDTOUnderTest.ticketErrorCauseIdL3 = 0L;
        ticketProcessDTOUnderTest.reasonLevel3 = "reasonLevel3";
        ticketProcessDTOUnderTest.ticketExpireCauseIdL1 = 0L;
        ticketProcessDTOUnderTest.ticketExpireCauseIdL2 = 0L;
        ticketProcessDTOUnderTest.ticketExpireCauseIdL3 = 0L;
        ticketProcessDTOUnderTest.ticketExpireCauseNameL1 = "ticketExpireCauseNameL1";
        ticketProcessDTOUnderTest.ticketExpireCauseNameL2 = "ticketExpireCauseNameL2";
        ticketProcessDTOUnderTest.ticketExpireCauseNameL3 = "ticketExpireCauseNameL3";
        ticketProcessDTOUnderTest.ticketExpireSiteId = 0L;
        ticketProcessDTOUnderTest.ticketSiteIdL1 = 0L;
        ticketProcessDTOUnderTest.ticketSiteIdL2 = 0L;
        ticketProcessDTOUnderTest.ticketSiteIdL3 = 0L;
        ticketProcessDTOUnderTest.actTypeId = 0L;
        ticketProcessDTOUnderTest.slaDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketProcessDTOUnderTest.startrecord = 0;
        ticketProcessDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketProcessDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketProcessDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketProcessDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketProcessDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
