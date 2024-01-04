package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class MapErrorCauseDTOTest {

    private MapErrorCauseDTO mapErrorCauseDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mapErrorCauseDTOUnderTest = new MapErrorCauseDTO();
        mapErrorCauseDTOUnderTest.mapErrorCauseId = 0L;
        mapErrorCauseDTOUnderTest.ticketErrorName1 = "ticketErrorName1";
        mapErrorCauseDTOUnderTest.ticketErrorName2 = "ticketErrorName2";
        mapErrorCauseDTOUnderTest.ticketErrorName3 = "ticketErrorName3";
        mapErrorCauseDTOUnderTest.ticketErrorId1 = 0L;
        mapErrorCauseDTOUnderTest.ticketErrorId2 = 0L;
        mapErrorCauseDTOUnderTest.ticketErrorId3 = 0L;
        mapErrorCauseDTOUnderTest.ticketGenreId = 0L;
        mapErrorCauseDTOUnderTest.ticketGenre = "ticketGenre";
        mapErrorCauseDTOUnderTest.ticketGenreStatus = "ticketGenreStatus";
        mapErrorCauseDTOUnderTest.ticketGroup = "ticketGroup";
        mapErrorCauseDTOUnderTest.ticketGroupStatus = "ticketGroupStatus";
        mapErrorCauseDTOUnderTest.ticketGroupId = 0L;
        mapErrorCauseDTOUnderTest.createDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        mapErrorCauseDTOUnderTest.createUser = "createUser";
        mapErrorCauseDTOUnderTest.updateUser = "updateUser";
        mapErrorCauseDTOUnderTest.updateDate = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
        mapErrorCauseDTOUnderTest.parentIdLv2 = 0L;
        mapErrorCauseDTOUnderTest.parentIdLv3 = 0L;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(mapErrorCauseDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(mapErrorCauseDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = mapErrorCauseDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = mapErrorCauseDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
