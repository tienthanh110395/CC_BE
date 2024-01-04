package com.viettel.etc.utils.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EtcExceptionTest {

    private EtcException etcExceptionUnderTest;

    @BeforeEach
    void setUp() {
        etcExceptionUnderTest = new EtcException("error", "message", 0);
    }

    @Test
    void testGetMessage() {
        assertThat(etcExceptionUnderTest.getMessage()).isEqualTo("message");
    }

    @Test
    void testGet() {
        // Setup
        // Run the test
        final EtcException result = etcExceptionUnderTest.get();

        // Verify the results
    }
}
