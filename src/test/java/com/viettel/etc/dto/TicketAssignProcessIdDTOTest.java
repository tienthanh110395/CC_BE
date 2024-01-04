package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TicketAssignProcessIdDTOTest {

    private TicketAssignProcessIdDTO ticketAssignProcessIdDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignProcessIdDTOUnderTest = new TicketAssignProcessIdDTO();
        ticketAssignProcessIdDTOUnderTest.custId = "custId";
        ticketAssignProcessIdDTOUnderTest.custName = "custName";
        ticketAssignProcessIdDTOUnderTest.plateNumber = "plateNumber";
        ticketAssignProcessIdDTOUnderTest.plateTypeCode = "plateTypeCode";
        ticketAssignProcessIdDTOUnderTest.phoneNumber = "phoneNumber";
        ticketAssignProcessIdDTOUnderTest.email = "email";
        ticketAssignProcessIdDTOUnderTest.contractNo = "contractNo";
        ticketAssignProcessIdDTOUnderTest.custAddress = "custAddress";
        ticketAssignProcessIdDTOUnderTest.priorityId = "priorityId";
        ticketAssignProcessIdDTOUnderTest.ticketKind = "ticketKind";
        ticketAssignProcessIdDTOUnderTest.location = "location";
        ticketAssignProcessIdDTOUnderTest.areaCode = "areaCode";
        ticketAssignProcessIdDTOUnderTest.contentReceive = "contentReceive";
        ticketAssignProcessIdDTOUnderTest.supportInfo = "supportInfo";
        ticketAssignProcessIdDTOUnderTest.requestDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAssignProcessIdDTOUnderTest.note = "note";
        ticketAssignProcessIdDTOUnderTest.sourceCode = "sourceCode";
        ticketAssignProcessIdDTOUnderTest.sourceName = "sourceName";
        ticketAssignProcessIdDTOUnderTest.l1TicketTypeId = "l1TicketTypeId";
        ticketAssignProcessIdDTOUnderTest.groupPA = "groupPA";
        ticketAssignProcessIdDTOUnderTest.l2TicketTypeId = "l2TicketTypeId";
        ticketAssignProcessIdDTOUnderTest.subgroupPA = "subgroupPA";
        ticketAssignProcessIdDTOUnderTest.l3TicketTypeId = "l3TicketTypeId";
        ticketAssignProcessIdDTOUnderTest.detailPA = "detailPA";
        ticketAssignProcessIdDTOUnderTest.assignContent = "assignContent";
        ticketAssignProcessIdDTOUnderTest.ticketId = "ticketId";
        ticketAssignProcessIdDTOUnderTest.ticketAssignId = "ticketAssignId";
        ticketAssignProcessIdDTOUnderTest.siteId = "siteId";
        ticketAssignProcessIdDTOUnderTest.siteName = "siteName";
        ticketAssignProcessIdDTOUnderTest.fileNames = new String[]{"value"};
        ticketAssignProcessIdDTOUnderTest.filePaths = new String[]{"value"};
        ticketAssignProcessIdDTOUnderTest.custTypeId = 0L;
        ticketAssignProcessIdDTOUnderTest.status = "status";
        ticketAssignProcessIdDTOUnderTest.listAttachFiles = Arrays.asList(new TicketAttachmentEntity());
        ticketAssignProcessIdDTOUnderTest.ticketAssignProcessId = 0L;
        ticketAssignProcessIdDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAssignProcessIdDTOUnderTest.createUser = "createUser";
        ticketAssignProcessIdDTOUnderTest.slaDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAssignProcessIdDTOUnderTest.userProcess = "userProcess";
        ticketAssignProcessIdDTOUnderTest.processTime = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAssignProcessIdDTOUnderTest.sourceId = 0L;
        ticketAssignProcessIdDTOUnderTest.toSiteId = "toSiteId";
        ticketAssignProcessIdDTOUnderTest.updateUser = "updateUser";
    }

    @Test
    void testEquals() {
        final boolean result = ticketAssignProcessIdDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketAssignProcessIdDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketAssignProcessIdDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketAssignProcessIdDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
