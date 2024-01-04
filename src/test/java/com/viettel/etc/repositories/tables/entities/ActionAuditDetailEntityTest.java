package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ActionAuditDetailEntityTest {

    private ActionAuditDetailEntity actionAuditDetailEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actionAuditDetailEntityUnderTest = new ActionAuditDetailEntity();
        actionAuditDetailEntityUnderTest.actionAuditDetailId = 0L;
        actionAuditDetailEntityUnderTest.actionAuditId = 0L;
        actionAuditDetailEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        actionAuditDetailEntityUnderTest.tableName = "tableName";
        actionAuditDetailEntityUnderTest.pkId = 0L;
        actionAuditDetailEntityUnderTest.columnName = "columnName";
        actionAuditDetailEntityUnderTest.oldValue = "oldValue";
        actionAuditDetailEntityUnderTest.newValue = "newValue";
        actionAuditDetailEntityUnderTest.actionName = "actionName";
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = actionAuditDetailEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = actionAuditDetailEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = actionAuditDetailEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = actionAuditDetailEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
