package com.viettel.etc.repositories.tables.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TicketAttachmentEntityTest {

    private TicketAttachmentEntity ticketAttachmentEntityUnderTest;

    @BeforeEach
    void setUp() {
        ticketAttachmentEntityUnderTest = new TicketAttachmentEntity();
        ticketAttachmentEntityUnderTest.attachmentId = 0L;
        ticketAttachmentEntityUnderTest.ticketId = 0L;
        ticketAttachmentEntityUnderTest.fileName = "fileName";
        ticketAttachmentEntityUnderTest.filePath = "filePath";
        ticketAttachmentEntityUnderTest.description = "description";
        ticketAttachmentEntityUnderTest.type = TicketAttachmentEntity.Type.TICKET_ASSIGN_PROCESS.value;
        ticketAttachmentEntityUnderTest.objectsId = 0L;
        ticketAttachmentEntityUnderTest.createUser = "createUser";
        ticketAttachmentEntityUnderTest.createDate = mock(Date.class);
        ticketAttachmentEntityUnderTest.updateUser = "updateUser";
        ticketAttachmentEntityUnderTest.updateDate = mock(Date.class);
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = ticketAttachmentEntityUnderTest.equals("o");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = ticketAttachmentEntityUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = ticketAttachmentEntityUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = ticketAttachmentEntityUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isFalse();
    }
}
