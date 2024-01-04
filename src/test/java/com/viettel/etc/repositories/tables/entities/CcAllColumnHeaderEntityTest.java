package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CcAllColumnHeaderEntityTest {

    private CcAllColumnHeaderEntity ccAllColumnHeaderEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ccAllColumnHeaderEntityUnderTest = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntityUnderTest.ccAllColumnHeaderId = 0L;
        ccAllColumnHeaderEntityUnderTest.tableName = "tableName";
        ccAllColumnHeaderEntityUnderTest.columnName = "columnName";
        ccAllColumnHeaderEntityUnderTest.dataType = "dataType";
        ccAllColumnHeaderEntityUnderTest.status = 0L;
        ccAllColumnHeaderEntityUnderTest.columnHeader = "columnHeader";
        ccAllColumnHeaderEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ccAllColumnHeaderEntityUnderTest.updateUser = "updateUser";
    }

    @Test
    void testEquals() throws Exception {
        final boolean result = ccAllColumnHeaderEntityUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = ccAllColumnHeaderEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = ccAllColumnHeaderEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        final boolean result = ccAllColumnHeaderEntityUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }
}
