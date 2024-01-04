package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ActionAuditDTOTest {

    private ActionAuditDTO actionAuditDTOUnderTest;

    @BeforeEach
    void setUp() {
        actionAuditDTOUnderTest = new ActionAuditDTO();
        actionAuditDTOUnderTest.actionAuditId = 0L;
        actionAuditDTOUnderTest.actTypeId = 0L;
        actionAuditDTOUnderTest.contractId = 0L;
        actionAuditDTOUnderTest.ticketId = 0L;
        actionAuditDTOUnderTest.ticketAssignId = 0L;
        actionAuditDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        actionAuditDTOUnderTest.actionUserFullName = "actionUserFullName";
        actionAuditDTOUnderTest.actionUserName = "actionUserName";
        actionAuditDTOUnderTest.appId = "appId";
        actionAuditDTOUnderTest.ipPc = "ipPc";
        actionAuditDTOUnderTest.description = "description";
        actionAuditDTOUnderTest.status = 0L;
        actionAuditDTOUnderTest.ticketStatus = 0L;
        actionAuditDTOUnderTest.actTypeName = "actTypeName";
        actionAuditDTOUnderTest.staffName = "staffName";
        actionAuditDTOUnderTest.siteName = "siteName";
        actionAuditDTOUnderTest.startrecord = 0;
        actionAuditDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = actionAuditDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = actionAuditDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = actionAuditDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = actionAuditDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
