package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResKeycloakDTOTest {

    private ResKeycloakDTO resKeycloakDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        resKeycloakDTOUnderTest = new ResKeycloakDTO();
        resKeycloakDTOUnderTest.code = 0;
        resKeycloakDTOUnderTest.message = "message";
        resKeycloakDTOUnderTest.error = "error";
        resKeycloakDTOUnderTest.access_token = "access_token";
    }

    @Test
    void testEquals() {
        final boolean result = resKeycloakDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = resKeycloakDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = resKeycloakDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = resKeycloakDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
