package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class ActionAuditSearchDTOTest {

    private ActionAuditSearchDTO actionAuditSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actionAuditSearchDTOUnderTest = new ActionAuditSearchDTO();
        actionAuditSearchDTOUnderTest.fromDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        actionAuditSearchDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        actionAuditSearchDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        actionAuditSearchDTOUnderTest.updateUser = "updateUser";
        actionAuditSearchDTOUnderTest.startRecord = 0;
        actionAuditSearchDTOUnderTest.pageSize = 0;
        actionAuditSearchDTOUnderTest.actType = 0L;
        actionAuditSearchDTOUnderTest.impactType = "impactType";
    }

    @Test
    void testEquals() throws Exception {
        assertThat(actionAuditSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(actionAuditSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = actionAuditSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = actionAuditSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
