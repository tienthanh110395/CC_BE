package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CustTypeDTOTest {

    private CustTypeDTO custTypeDTOUnderTest;

    @BeforeEach
    void setUp() {
        custTypeDTOUnderTest = new CustTypeDTO();
        custTypeDTOUnderTest.custTypeId = "custTypeId";
        custTypeDTOUnderTest.code = "code";
        custTypeDTOUnderTest.name = "name";
        custTypeDTOUnderTest.type = 0L;
        custTypeDTOUnderTest.description = "description";
        custTypeDTOUnderTest.status = "status";
        custTypeDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        custTypeDTOUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        custTypeDTOUnderTest.createUser = "createUser";
        custTypeDTOUnderTest.updateUser = "updateUser";
    }

    @Test
    void testEquals() {
        final boolean result = custTypeDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = custTypeDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = custTypeDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = custTypeDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
