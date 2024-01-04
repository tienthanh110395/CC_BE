package com.viettel.etc.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TicketAttachmentDTOTest {

    private TicketAttachmentDTO ticketAttachmentDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAttachmentDTOUnderTest = new TicketAttachmentDTO();
        ticketAttachmentDTOUnderTest.attachmentId = 0L;
        ticketAttachmentDTOUnderTest.ticketId = 0L;
        ticketAttachmentDTOUnderTest.fileName = "fileName";
        ticketAttachmentDTOUnderTest.filePath = "filePath";
        ticketAttachmentDTOUnderTest.description = "description";
        ticketAttachmentDTOUnderTest.type = 0L;
        ticketAttachmentDTOUnderTest.objectsId = 0L;
        ticketAttachmentDTOUnderTest.createUser = "createUser";
        ticketAttachmentDTOUnderTest.createDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAttachmentDTOUnderTest.updateUser = "updateUser";
        ticketAttachmentDTOUnderTest.updateDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        ticketAttachmentDTOUnderTest.attachmentFiles = Arrays.asList(new FileDTO());
        ticketAttachmentDTOUnderTest.actTypeId = 0L;
        ticketAttachmentDTOUnderTest.startrecord = 0;
        ticketAttachmentDTOUnderTest.pagesize = 0;
    }

    @Test
    void testEquals() {
        final boolean result = ticketAttachmentDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketAttachmentDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketAttachmentDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketAttachmentDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
