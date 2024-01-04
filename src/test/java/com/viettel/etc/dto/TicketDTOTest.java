package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketDTOTest {

    private TicketDTO ticketDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketDTOUnderTest = new TicketDTO();
        ticketDTOUnderTest.ticketId = 0L;
        ticketDTOUnderTest.custId = 0L;
        ticketDTOUnderTest.contractId = 0L;
        ticketDTOUnderTest.contractNo = "contractNo";
        ticketDTOUnderTest.custTypeId = 0L;
        ticketDTOUnderTest.plateNumber = "plateNumber";
        ticketDTOUnderTest.phoneNumber = "phoneNumber";
        ticketDTOUnderTest.custName = "custName";
        ticketDTOUnderTest.email = "email";
        ticketDTOUnderTest.custAddress = "custAddress";
        ticketDTOUnderTest.priorityId = 0L;
        ticketDTOUnderTest.sourceId = 0L;
        ticketDTOUnderTest.l1TicketTypeId = 0L;
        ticketDTOUnderTest.l2TicketTypeId = 0L;
        ticketDTOUnderTest.l3TicketTypeId = 0L;
        ticketDTOUnderTest.l1TicketTypeName = "l1TicketTypeName";
        ticketDTOUnderTest.l2TicketTypeName = "l2TicketTypeName";
        ticketDTOUnderTest.l3TicketTypeName = "l3TicketTypeName";
        ticketDTOUnderTest.location = "location";
        ticketDTOUnderTest.areaCode = "areaCode";
        ticketDTOUnderTest.contentReceive = "contentReceive";
        ticketDTOUnderTest.ticketKind = 0L;
        ticketDTOUnderTest.supportInfo = "supportInfo";
        ticketDTOUnderTest.requestDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.slaDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.note = "note";
        ticketDTOUnderTest.status = 0L;
        ticketDTOUnderTest.statusName = "statusName";
        ticketDTOUnderTest.responseStatus = 0L;
        ticketDTOUnderTest.reopenDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.assignType = 0L;
        ticketDTOUnderTest.createUser = "createUser";
        ticketDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.processTime = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.updateUser = "updateUser";
        ticketDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.plateTypeCode = "plateTypeCode";
        ticketDTOUnderTest.attachmentFiles = Arrays.asList(new FileDTO());
        ticketDTOUnderTest.siteId = 0L;
        ticketDTOUnderTest.provinceName = "provinceName";
        ticketDTOUnderTest.districtName = "districtName";
        ticketDTOUnderTest.communeName = "communeName";
        ticketDTOUnderTest.phoneContact = "phoneContact";
        ticketDTOUnderTest.sla = 0L;
        ticketDTOUnderTest.stageId = 0L;
        ticketDTOUnderTest.stationId = 0L;
        ticketDTOUnderTest.stageName = "stageName";
        ticketDTOUnderTest.stationName = "stationName";
        ticketDTOUnderTest.ticketTimes = 0L;
        ticketDTOUnderTest.feedBack = "feedBack";
        ticketDTOUnderTest.ticketChannel = 0L;
        ticketDTOUnderTest.staffName = "staffName";
        ticketDTOUnderTest.processContent = "processContent";
        ticketDTOUnderTest.fileName = "fileName";
        ticketDTOUnderTest.attachmentId = "attachmentId";
        ticketDTOUnderTest.otp = "otp";
        ticketDTOUnderTest.ticketIds = "ticketIds";
        ticketDTOUnderTest.contractNos = "contractNos";
        ticketDTOUnderTest.fromDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketDTOUnderTest.startrecord = 0;
        ticketDTOUnderTest.pagesize = 0;
        ticketDTOUnderTest.actTypeId = 0L;
        ticketDTOUnderTest.inDueDate = "inDueDate";
        ticketDTOUnderTest.outOfDate = "outOfDate";
        ticketDTOUnderTest.closeQuantity = "closeQuantity";
        ticketDTOUnderTest.sourceName = "sourceName";
        ticketDTOUnderTest.processUser = "processUser";
        ticketDTOUnderTest.staffCode = "staffCode";
        ticketDTOUnderTest.processUsers = "processUsers";
    }

    @Test
    void testToActionAuditDTO() {
        // Setup
        final ActionAuditDTO expectedResult = new ActionAuditDTO();
        expectedResult.setActionAuditId(0L);
        expectedResult.setActTypeId(0L);
        expectedResult.setContractId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setTicketAssignId(0L);
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setActionUserFullName("actionUserFullName");
        expectedResult.setActionUserName("actionUserName");
        expectedResult.setAppId("appId");
        expectedResult.setIpPc("ipPc");

        // Run the test
        final ActionAuditDTO result = ticketDTOUnderTest.toActionAuditDTO();

        // Verify the results
    }

    @Test
    void testEquals() {
        final boolean result = ticketDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
