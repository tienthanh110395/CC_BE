package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdminDTOTest {

    private AdminDTO adminDTOUnderTest;

    @BeforeEach
    void setUp() {
        adminDTOUnderTest = new AdminDTO("username", "password", "clientId", "secret");
    }

    @Test
    void testEquals() {
        final boolean result = adminDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = adminDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = adminDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = adminDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
