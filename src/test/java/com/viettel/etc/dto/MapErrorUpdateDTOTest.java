package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MapErrorUpdateDTOTest {

    private MapErrorUpdateDTO mapErrorUpdateDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mapErrorUpdateDTOUnderTest = new MapErrorUpdateDTO();
        mapErrorUpdateDTOUnderTest.mapErrorCauseId = Arrays.asList(0L);
        mapErrorUpdateDTOUnderTest.ticketTypeId = 0L;
        mapErrorUpdateDTOUnderTest.ticketErrorId = 0L;
        mapErrorUpdateDTOUnderTest.ticketErrorLv2Id = 0L;
        mapErrorUpdateDTOUnderTest.ticketErrorLv3Id = 0L;
    }

    @Test
    void testEquals() throws Exception {
        assertThat(mapErrorUpdateDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(mapErrorUpdateDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = mapErrorUpdateDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = mapErrorUpdateDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
