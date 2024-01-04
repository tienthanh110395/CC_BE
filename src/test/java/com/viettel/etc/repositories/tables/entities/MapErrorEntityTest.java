package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MapErrorEntityTest {

    private MapErrorEntity mapErrorEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mapErrorEntityUnderTest = new MapErrorEntity();
        mapErrorEntityUnderTest.mapId = 0L;
        mapErrorEntityUnderTest.ticketTypeId = 0L;
        mapErrorEntityUnderTest.ticketErrorId = 0L;
        mapErrorEntityUnderTest.ticketErrorLv2Id = 0L;
        mapErrorEntityUnderTest.ticketErrorLv3Id = 0L;
        mapErrorEntityUnderTest.createUser = "createUser";
        mapErrorEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        mapErrorEntityUnderTest.updateUser = "updateUser";
        mapErrorEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
    }

    @Test
    void testEquals() throws Exception {
        assertThat(mapErrorEntityUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(mapErrorEntityUnderTest.canEqual("other")).isFalse();
    }
}
