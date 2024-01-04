package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileDTOTest {

    private FileDTO fileDTOUnderTest;

    @BeforeEach
    void setUp() {
        fileDTOUnderTest = new FileDTO();
        fileDTOUnderTest.filePath = "filePath";
        fileDTOUnderTest.fileName = "fileName";
        fileDTOUnderTest.base64Data = "base64Data";
        fileDTOUnderTest.attachmentId = 0L;
    }

    @Test
    void testEquals() {
        final boolean result = fileDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = fileDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = fileDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = fileDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
