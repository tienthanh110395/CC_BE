package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ActionAuditEntityTest {

    private ActionAuditEntity actionAuditEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actionAuditEntityUnderTest = new ActionAuditEntity();
        actionAuditEntityUnderTest.actionAuditId = 0L;
        actionAuditEntityUnderTest.actTypeId = 0L;
        actionAuditEntityUnderTest.contractId = 0L;
        actionAuditEntityUnderTest.ticketId = 0L;
        actionAuditEntityUnderTest.ticketAssignId = 0L;
        actionAuditEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        actionAuditEntityUnderTest.actionUserFullName = "actionUserFullName";
        actionAuditEntityUnderTest.actionUserName = "actionUserName";
        actionAuditEntityUnderTest.appId = "appId";
        actionAuditEntityUnderTest.ipPc = "ipPc";
        actionAuditEntityUnderTest.description = "description";
        actionAuditEntityUnderTest.status = 0L;
        actionAuditEntityUnderTest.ticketStatus = 0L;
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = actionAuditEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = actionAuditEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = actionAuditEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = actionAuditEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
