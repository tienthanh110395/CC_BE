package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketCateConfigRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TicketCateConfigServiceJPATest {

    private TicketCateConfigServiceJPA ticketCateConfigServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketCateConfigServiceJPAUnderTest = new TicketCateConfigServiceJPA();
        ticketCateConfigServiceJPAUnderTest.ticketCateConfigRepositoryJPA = mock(TicketCateConfigRepositoryJPA.class);
    }

    @Test
    void testUpdateStatusMultiple() {
        // Setup
        // Run the test
        ticketCateConfigServiceJPAUnderTest.updateStatusMultiple(Arrays.asList(0L), 0L);

        // Verify the results
        verify(ticketCateConfigServiceJPAUnderTest.ticketCateConfigRepositoryJPA).updateStatusMultiple(
                Arrays.asList(0L), 0L);
    }

    @Test
    void testGetTopupSequenceNo() {
        // Setup
        when(ticketCateConfigServiceJPAUnderTest.ticketCateConfigRepositoryJPA.getNextValSequenceNo()).thenReturn(0L);

        // Run the test
        final Long result = ticketCateConfigServiceJPAUnderTest.getTopupSequenceNo();

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }
}
