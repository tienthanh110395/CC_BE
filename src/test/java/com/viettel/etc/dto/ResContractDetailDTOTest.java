package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ResContractDetailDTOTest {

    private ResContractDetailDTO resContractDetailDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        resContractDetailDTOUnderTest = new ResContractDetailDTO();
        resContractDetailDTOUnderTest.data = mock(ContractDetailDTO.class);
    }

    @Test
    void testEquals() {
        final boolean result = resContractDetailDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = resContractDetailDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = resContractDetailDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = resContractDetailDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
