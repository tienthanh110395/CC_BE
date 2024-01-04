package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketAssignProcessEntity;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketAssignDTOTest {

    private TicketAssignDTO ticketAssignDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignDTOUnderTest = new TicketAssignDTO();
        ticketAssignDTOUnderTest.ticketId = "ticketId";
        ticketAssignDTOUnderTest.plateNumber = "plateNumber";
        ticketAssignDTOUnderTest.contractNo = "contractNo";
        ticketAssignDTOUnderTest.contentReceive = "contentReceive";
        ticketAssignDTOUnderTest.processContent = "processContent";
        ticketAssignDTOUnderTest.fromSiteId = "fromSiteId";
        ticketAssignDTOUnderTest.ticketStatus = "ticketStatus";
        ticketAssignDTOUnderTest.userProcess = "userProcess";
        ticketAssignDTOUnderTest.toSiteL2Id = "toSiteL2Id";
        ticketAssignDTOUnderTest.reasonLevel1 = "reasonLevel1";
        ticketAssignDTOUnderTest.reasonLevel2 = "reasonLevel2";
        ticketAssignDTOUnderTest.toSiteId = "toSiteId";
        ticketAssignDTOUnderTest.assignDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.resolveDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.fromSiteCode = "fromSiteCode";
        ticketAssignDTOUnderTest.fromSiteName = "fromSiteName";
        ticketAssignDTOUnderTest.toSiteCode = "toSiteCode";
        ticketAssignDTOUnderTest.toSiteName = "toSiteName";
        ticketAssignDTOUnderTest.toSiteL2Code = "toSiteL2Code";
        ticketAssignDTOUnderTest.toSiteL2Name = "toSiteL2Name";
        ticketAssignDTOUnderTest.ticketAssignId = 0L;
        ticketAssignDTOUnderTest.assignType = 0L;
        ticketAssignDTOUnderTest.slaDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.startrecord = 0;
        ticketAssignDTOUnderTest.pagesize = 0;
        ticketAssignDTOUnderTest.resultSqlEx = false;
        ticketAssignDTOUnderTest.attachmentFiles = Arrays.asList(new FileDTO());
        ticketAssignDTOUnderTest.siteId = 0L;
        ticketAssignDTOUnderTest.fromUsername = "fromUsername";
        ticketAssignDTOUnderTest.toUsername = "toUsername";
        ticketAssignDTOUnderTest.assignLevel = 0L;
        ticketAssignDTOUnderTest.assignContent = "assignContent";
        ticketAssignDTOUnderTest.createUser = "createUser";
        ticketAssignDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.updateUser = "updateUser";
        ticketAssignDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.custTypeId = "custTypeId";
        ticketAssignDTOUnderTest.toSiteEmail = "toSiteEmail";
        ticketAssignDTOUnderTest.ticketAssignProcessEntitys = Arrays.asList(new TicketAssignProcessEntity());
        ticketAssignDTOUnderTest.staffCode = "staffCode";
        ticketAssignDTOUnderTest.staffName = "staffName";
        ticketAssignDTOUnderTest.ticketAttachmentEntityList = Arrays.asList(new TicketAttachmentEntity());
        ticketAssignDTOUnderTest.ticketErrorCauseIdL1 = 0L;
        ticketAssignDTOUnderTest.ticketErrorCauseIdL2 = 0L;
        ticketAssignDTOUnderTest.ticketErrorCauseIdL3 = 0L;
        ticketAssignDTOUnderTest.ticketErrorCauseIdL1Name = "ticketErrorCauseIdL1Name";
        ticketAssignDTOUnderTest.ticketErrorCauseIdL2Name = "ticketErrorCauseIdL2Name";
        ticketAssignDTOUnderTest.ticketErrorCauseIdL3Name = "ticketErrorCauseIdL3Name";
        ticketAssignDTOUnderTest.txtProcessTicket = "txtProcessTicket";
        ticketAssignDTOUnderTest.hourProcessTicket = "hourProcessTicket";
        ticketAssignDTOUnderTest.processTime = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.startReceiveDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.endReceiveDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.startProcessDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.endProcessDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAssignDTOUnderTest.actTypeId = 0L;
    }

    @Test
    void testEquals() {
        final boolean result = ticketAssignDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketAssignDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketAssignDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketAssignDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
