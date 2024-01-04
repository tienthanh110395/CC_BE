package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ActionAuditDetailDTOTest {

    private ActionAuditDetailDTO actionAuditDetailDTOUnderTest;

    @BeforeEach
    void setUp() {
        actionAuditDetailDTOUnderTest = new ActionAuditDetailDTO();
        actionAuditDetailDTOUnderTest.actionAuditDetailId = 0L;
        actionAuditDetailDTOUnderTest.actionAuditId = 0L;
        actionAuditDetailDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        actionAuditDetailDTOUnderTest.tableName = "tableName";
        actionAuditDetailDTOUnderTest.pkId = 0L;
        actionAuditDetailDTOUnderTest.columnName = "columnName";
        actionAuditDetailDTOUnderTest.oldValue = "oldValue";
        actionAuditDetailDTOUnderTest.newValue = "newValue";
        actionAuditDetailDTOUnderTest.actionName = "actionName";
        actionAuditDetailDTOUnderTest.startrecord = 0;
        actionAuditDetailDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = actionAuditDetailDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = actionAuditDetailDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = actionAuditDetailDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = actionAuditDetailDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
