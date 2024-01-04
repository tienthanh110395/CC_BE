package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryDTOTest {

    private CategoryDTO categoryDTOUnderTest;

    @BeforeEach
    void setUp() {
        categoryDTOUnderTest = new CategoryDTO(new CategoryDTO.Categories(Arrays.asList(new CategoryDTO.Category("name", "code", 0L, "description"))));
    }

    @Test
    void testEquals() {
        final boolean result = categoryDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = categoryDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = categoryDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = categoryDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
