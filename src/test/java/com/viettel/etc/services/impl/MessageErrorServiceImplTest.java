package com.viettel.etc.services.impl;

import com.viettel.etc.repositories.tables.MessageErrorRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MessageErrorEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageErrorServiceImplTest {

    private MessageErrorServiceImpl messageErrorServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        messageErrorServiceImplUnderTest = new MessageErrorServiceImpl();
        messageErrorServiceImplUnderTest.messageErrorRepositoryJPA = mock(MessageErrorRepositoryJPA.class);
    }

    @Test
    void testGetAll() {
        // Setup
        final List<MessageErrorEntity> expectedResult = Arrays.asList(new MessageErrorEntity(0L, "message", "messageVi", "messageEn"));

        // Configure MessageErrorRepositoryJPA.findAll(...).
        final List<MessageErrorEntity> messageErrorEntities = Arrays.asList(new MessageErrorEntity(0L, "message", "messageVi", "messageEn"));
        when(messageErrorServiceImplUnderTest.messageErrorRepositoryJPA.findAll()).thenReturn(messageErrorEntities);

        // Run the test
        final List<MessageErrorEntity> result = messageErrorServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAll_MessageErrorRepositoryJPAReturnsNoItems() {
        // Setup
        when(messageErrorServiceImplUnderTest.messageErrorRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<MessageErrorEntity> result = messageErrorServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
