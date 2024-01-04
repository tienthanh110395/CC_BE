package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class TicketAdjustChargeDTOTest {

    private TicketAdjustChargeDTO ticketAdjustChargeDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAdjustChargeDTOUnderTest = new TicketAdjustChargeDTO();
        ticketAdjustChargeDTOUnderTest.ticketAdjustChargeId = 0L;
        ticketAdjustChargeDTOUnderTest.ticketId = 0L;
        ticketAdjustChargeDTOUnderTest.plateTypeCode = "plateTypeCode";
        ticketAdjustChargeDTOUnderTest.plateNumber = "plateNumber";
        ticketAdjustChargeDTOUnderTest.payType = "payType";
        ticketAdjustChargeDTOUnderTest.contractNo = "contractNo";
        ticketAdjustChargeDTOUnderTest.accountType = "accountType";
        ticketAdjustChargeDTOUnderTest.adjustAmount = 0L;
        ticketAdjustChargeDTOUnderTest.reason = "reason";
        ticketAdjustChargeDTOUnderTest.adjustContent = "adjustContent";
        ticketAdjustChargeDTOUnderTest.requestDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAdjustChargeDTOUnderTest.status = "status";
        ticketAdjustChargeDTOUnderTest.createUser = "createUser";
        ticketAdjustChargeDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAdjustChargeDTOUnderTest.updateUser = "updateUser";
        ticketAdjustChargeDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        ticketAdjustChargeDTOUnderTest.siteId = 0L;
        ticketAdjustChargeDTOUnderTest.attachmentFiles = Arrays.asList(new FileDTO());
        ticketAdjustChargeDTOUnderTest.ticketAttachmentEntityList = Arrays.asList(new TicketAttachmentEntity());
        ticketAdjustChargeDTOUnderTest.actTypeId = 0L;
        ticketAdjustChargeDTOUnderTest.startrecord = 0;
        ticketAdjustChargeDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketAdjustChargeDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketAdjustChargeDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketAdjustChargeDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketAdjustChargeDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
