package com.viettel.etc;

import com.viettel.etc.repositories.tables.entities.MessageErrorEntity;
import com.viettel.etc.services.JedisCacheService;
import com.viettel.etc.services.MessageErrorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ServiceApplicationTest {

    private ServiceApplication serviceApplicationUnderTest;

    @BeforeEach
    void setUp() {
        serviceApplicationUnderTest = new ServiceApplication();
        serviceApplicationUnderTest.messageErrorService = mock(MessageErrorService.class);
        serviceApplicationUnderTest.jedisCluster = mock(JedisCacheService.class);
    }

    @Test
    void testRun() {
        // Setup
        // Configure MessageErrorService.getAll(...).
        final List<MessageErrorEntity> messageErrorEntities = Arrays.asList(new MessageErrorEntity(0L, "message", "messageVi", "messageEn"));
        when(serviceApplicationUnderTest.messageErrorService.getAll()).thenReturn(messageErrorEntities);

        // Run the test
        serviceApplicationUnderTest.run("args");

        // Verify the results
    }

    @Test
    void testRun_MessageErrorServiceReturnsNoItems() {
        // Setup
        when(serviceApplicationUnderTest.messageErrorService.getAll()).thenReturn(Collections.emptyList());

        // Run the test
        serviceApplicationUnderTest.run("args");

        // Verify the results
    }
}
