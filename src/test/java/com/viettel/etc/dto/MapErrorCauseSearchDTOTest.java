package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class MapErrorCauseSearchDTOTest {

    private MapErrorCauseSearchDTO mapErrorCauseSearchDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mapErrorCauseSearchDTOUnderTest = new MapErrorCauseSearchDTO();
        mapErrorCauseSearchDTOUnderTest.formDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        mapErrorCauseSearchDTOUnderTest.toDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        mapErrorCauseSearchDTOUnderTest.createUser = "createUser";
        mapErrorCauseSearchDTOUnderTest.startRecord = 0;
        mapErrorCauseSearchDTOUnderTest.pageSize = 0;
        mapErrorCauseSearchDTOUnderTest.lstTicketTypeGroupId = Arrays.asList(0L);
        mapErrorCauseSearchDTOUnderTest.lstTicketCategoryId = Arrays.asList(0L);
        mapErrorCauseSearchDTOUnderTest.lstErrorCauseLv1 = Arrays.asList(0L);
        mapErrorCauseSearchDTOUnderTest.lstErrorCauseLv2 = Arrays.asList(0L);
        mapErrorCauseSearchDTOUnderTest.lstErrorCauseLv3 = Arrays.asList(0L);
        mapErrorCauseSearchDTOUnderTest.lstParentLvId2 = Arrays.asList(0L);
        mapErrorCauseSearchDTOUnderTest.lstParentLvId3 = Arrays.asList(0L);
    }

    @Test
    void testEquals() throws Exception {
        assertThat(mapErrorCauseSearchDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(mapErrorCauseSearchDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = mapErrorCauseSearchDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = mapErrorCauseSearchDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
