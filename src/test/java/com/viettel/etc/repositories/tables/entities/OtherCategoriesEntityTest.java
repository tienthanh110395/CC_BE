package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class OtherCategoriesEntityTest {

    private OtherCategoriesEntity otherCategoriesEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        otherCategoriesEntityUnderTest = new OtherCategoriesEntity();
        otherCategoriesEntityUnderTest.id = 0L;
        otherCategoriesEntityUnderTest.name = "name";
        otherCategoriesEntityUnderTest.code = "code";
        otherCategoriesEntityUnderTest.priorityValue = 0L;
        otherCategoriesEntityUnderTest.type = 0L;
        otherCategoriesEntityUnderTest.description = "description";
        otherCategoriesEntityUnderTest.isActive = 0L;
        otherCategoriesEntityUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        otherCategoriesEntityUnderTest.createUser = "createUser";
        otherCategoriesEntityUnderTest.typeName = "typeName";
        otherCategoriesEntityUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        otherCategoriesEntityUnderTest.updateUser = "updateUser";
    }

    @Test
    void testClone() throws Exception {
        final Object result = otherCategoriesEntityUnderTest.clone();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testEquals() throws Exception {
        assertThat(otherCategoriesEntityUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() throws Exception {
        assertThat(otherCategoriesEntityUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        final int result = otherCategoriesEntityUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        final String result = otherCategoriesEntityUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
