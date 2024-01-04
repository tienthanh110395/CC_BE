package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticEntityTest {

    private StatisticEntity statisticEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        statisticEntityUnderTest = new StatisticEntity();
        statisticEntityUnderTest.statisticId = 0L;
        statisticEntityUnderTest.contractNoUserName = "contractNoUserName";
        statisticEntityUnderTest.plateNumber = "plateNumber";
        statisticEntityUnderTest.systemPhoneNumber = "systemPhoneNumber";
        statisticEntityUnderTest.callPhoneNumber = "callPhoneNumber";
        statisticEntityUnderTest.sourceId = 0L;
        statisticEntityUnderTest.l1StatisticTypeId = 0L;
        statisticEntityUnderTest.l2StatisticTypeId = 0L;
        statisticEntityUnderTest.l3StatisticTypeId = 0L;
        statisticEntityUnderTest.l4StatisticTypeId = 0L;
        statisticEntityUnderTest.l5StatisticTypeId = 0L;
        statisticEntityUnderTest.statisticContent = "statisticContent";
        statisticEntityUnderTest.custReaction = "custReaction";
        statisticEntityUnderTest.createUser = "createUser";
        statisticEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        statisticEntityUnderTest.updateUser = "updateUser";
        statisticEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = statisticEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = statisticEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = statisticEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = statisticEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
