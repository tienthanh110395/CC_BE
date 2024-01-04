package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticTypeEntityTest {

    private StatisticTypeEntity statisticTypeEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        statisticTypeEntityUnderTest = new StatisticTypeEntity();
        statisticTypeEntityUnderTest.statisticTypeId = 0L;
        statisticTypeEntityUnderTest.name = "name";
        statisticTypeEntityUnderTest.code = "code";
        statisticTypeEntityUnderTest.description = "description";
        statisticTypeEntityUnderTest.parentId = 1L;
        statisticTypeEntityUnderTest.status = 0L;
        statisticTypeEntityUnderTest.createUser = "createUser";
        statisticTypeEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        statisticTypeEntityUnderTest.updateUser = "updateUser";
        statisticTypeEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        statisticTypeEntityUnderTest.myccId = "myccId";
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = statisticTypeEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = statisticTypeEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = statisticTypeEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = statisticTypeEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
