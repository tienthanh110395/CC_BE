package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageErrorEntityTest {

    private MessageErrorEntity messageErrorEntityUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        messageErrorEntityUnderTest = new MessageErrorEntity(0L, "message", "messageVi", "messageEn");
    }

    @Test
    void testEquals() throws Exception {
        // Setup
        MessageErrorEntity messageErrorEntity = new MessageErrorEntity();
        // Run the test
        final boolean result = messageErrorEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() throws Exception {
        // Setup

        // Run the test
        final int result = messageErrorEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() throws Exception {
        // Setup

        // Run the test
        final String result = messageErrorEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() throws Exception {
        // Setup

        // Run the test
        final boolean result = messageErrorEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testBuilder() {
        // Setup

        // Run the test
        final MessageErrorEntity.MessageErrorEntityBuilder result = MessageErrorEntity.builder();

        // Verify the results
    }
}
