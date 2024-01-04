package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class SearchTicketDTOTest {

    private SearchTicketDTO searchTicketDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        searchTicketDTOUnderTest = new SearchTicketDTO();
        searchTicketDTOUnderTest.ticketId = "ticketId";
        searchTicketDTOUnderTest.custId = "custId";
        searchTicketDTOUnderTest.contractId = 0L;
        searchTicketDTOUnderTest.contractNo = "contractNo";
        searchTicketDTOUnderTest.custTypeId = "custTypeId";
        searchTicketDTOUnderTest.plateNumber = "plateNumber";
        searchTicketDTOUnderTest.phoneNumber = "phoneNumber";
        searchTicketDTOUnderTest.custName = "custName";
        searchTicketDTOUnderTest.email = "email";
        searchTicketDTOUnderTest.custAddress = "custAddress";
        searchTicketDTOUnderTest.priorityId = "priorityId";
        searchTicketDTOUnderTest.sourceId = "sourceId";
        searchTicketDTOUnderTest.l1TicketTypeId = "l1TicketTypeId";
        searchTicketDTOUnderTest.l2TicketTypeId = "l2TicketTypeId";
        searchTicketDTOUnderTest.l3TicketTypeId = "l3TicketTypeId";
        searchTicketDTOUnderTest.location = "location";
        searchTicketDTOUnderTest.areaCode = "areaCode";
        searchTicketDTOUnderTest.contentReceive = "contentReceive";
        searchTicketDTOUnderTest.ticketKind = "ticketKind";
        searchTicketDTOUnderTest.supportInfo = "supportInfo";
        searchTicketDTOUnderTest.note = "note";
        searchTicketDTOUnderTest.status = "status";
        searchTicketDTOUnderTest.responseStatus = 0L;
        searchTicketDTOUnderTest.assignType = "assignType";
        searchTicketDTOUnderTest.createUser = "createUser";
        searchTicketDTOUnderTest.updateUser = "updateUser";
        searchTicketDTOUnderTest.plateTypeCode = "plateTypeCode";
        searchTicketDTOUnderTest.detailPA = "detailPA";
        searchTicketDTOUnderTest.groupPA = "groupPA";
        searchTicketDTOUnderTest.subgroupPA = "subgroupPA";
        searchTicketDTOUnderTest.sourceName = "sourceName";
        searchTicketDTOUnderTest.processContent = "processContent";
        searchTicketDTOUnderTest.processUser = "processUser";
        searchTicketDTOUnderTest.processCreateUser = "processCreateUser";
        searchTicketDTOUnderTest.assignContent = "assignContent";
        searchTicketDTOUnderTest.siteName = "siteName";
        searchTicketDTOUnderTest.reasonLevel1 = "reasonLevel1";
        searchTicketDTOUnderTest.reasonLevel2 = "reasonLevel2";
        searchTicketDTOUnderTest.reasonLevel3 = "reasonLevel3";
        searchTicketDTOUnderTest.staffCode = "staffCode";
        searchTicketDTOUnderTest.staffName = "staffName";
        searchTicketDTOUnderTest.toSiteName = "toSiteName";
        searchTicketDTOUnderTest.toSiteEmail = "toSiteEmail";
        searchTicketDTOUnderTest.toSiteL2Name = "toSiteL2Name";
        searchTicketDTOUnderTest.toSiteL2Id = 0L;
        searchTicketDTOUnderTest.totalProcessTime = 0.0;
        searchTicketDTOUnderTest.totalInnerProcessTime = 0.0;
        searchTicketDTOUnderTest.expireTime = 0.0;
        searchTicketDTOUnderTest.provinceName = "provinceName";
        searchTicketDTOUnderTest.districtName = "districtName";
        searchTicketDTOUnderTest.communeName = "communeName";
        searchTicketDTOUnderTest.userHandle = "userHandle";
        searchTicketDTOUnderTest.phoneContact = "phoneContact";
        searchTicketDTOUnderTest.ticketTimes = 0L;
        searchTicketDTOUnderTest.stageId = 0L;
        searchTicketDTOUnderTest.stageName = "stageName";
        searchTicketDTOUnderTest.stationId = 0L;
        searchTicketDTOUnderTest.stationName = "stationName";
        searchTicketDTOUnderTest.expireStatus = "expireStatus";
        searchTicketDTOUnderTest.ticketSiteIdL1Name = "ticketSiteIdL1Name";
        searchTicketDTOUnderTest.ticketSiteIdL2Name = "ticketSiteIdL2Name";
        searchTicketDTOUnderTest.ticketSiteIdL3Name = "ticketSiteIdL3Name";
        searchTicketDTOUnderTest.ticketExpireCauseIdL1 = 0L;
        searchTicketDTOUnderTest.ticketExpireCauseIdL2 = 0L;
        searchTicketDTOUnderTest.ticketExpireCauseIdL3 = 0L;
        searchTicketDTOUnderTest.ticketExpireCauseIdL1Name = "ticketExpireCauseIdL1Name";
        searchTicketDTOUnderTest.ticketExpireCauseIdL2Name = "ticketExpireCauseIdL2Name";
        searchTicketDTOUnderTest.ticketExpireCauseIdL3Name = "ticketExpireCauseIdL3Name";
        searchTicketDTOUnderTest.ticketExpireSiteId = 0L;
        searchTicketDTOUnderTest.ticketExpireSiteName = "ticketExpireSiteName";
        searchTicketDTOUnderTest.ticketErrorCauseIdL1 = 0L;
        searchTicketDTOUnderTest.ticketErrorCauseIdL2 = 0L;
        searchTicketDTOUnderTest.ticketErrorCauseIdL3 = 0L;
        searchTicketDTOUnderTest.ticketErrorCauseIdL1Name = "ticketErrorCauseIdL1Name";
        searchTicketDTOUnderTest.ticketErrorCauseIdL2Name = "ticketErrorCauseIdL2Name";
        searchTicketDTOUnderTest.ticketErrorCauseIdL3Name = "ticketErrorCauseIdL3Name";
        searchTicketDTOUnderTest.startReceiveDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.endReceiveDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.startProcessDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.endProcessDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.dateHandle = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.concludeProcessTime = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.assignDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.processTime = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.timeToReceive = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.timeToConlucdeProcess = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.reopenDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.deadlineProcess = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.siteId = 0L;
        searchTicketDTOUnderTest.requestDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.slaDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        searchTicketDTOUnderTest.receiveDate = "receiveDate";
        searchTicketDTOUnderTest.receiveTime = "receiveTime";
        searchTicketDTOUnderTest.startrecord = 0;
        searchTicketDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = searchTicketDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = searchTicketDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = searchTicketDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = searchTicketDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
