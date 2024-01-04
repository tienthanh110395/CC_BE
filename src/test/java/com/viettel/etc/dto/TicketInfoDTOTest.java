package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TicketInfoDTOTest {

    private TicketInfoDTO ticketInfoDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketInfoDTOUnderTest = new TicketInfoDTO();
        ticketInfoDTOUnderTest.ticketId = "ticketId";
        ticketInfoDTOUnderTest.contractId = 0L;
        ticketInfoDTOUnderTest.plateNumber = "plateNumber";
        ticketInfoDTOUnderTest.custId = "custId";
        ticketInfoDTOUnderTest.custName = "custName";
        ticketInfoDTOUnderTest.custTypeId = "custTypeId";
        ticketInfoDTOUnderTest.phoneNumber = "phoneNumber";
        ticketInfoDTOUnderTest.email = "email";
        ticketInfoDTOUnderTest.status = "status";
        ticketInfoDTOUnderTest.sourceId = "sourceId";
        ticketInfoDTOUnderTest.sourceName = "sourceName";
        ticketInfoDTOUnderTest.custAddress = "custAddress";
        ticketInfoDTOUnderTest.contentReceive = "contentReceive";
        ticketInfoDTOUnderTest.contractNo = "contractNo";
        ticketInfoDTOUnderTest.ticketAttachmentEntityList = Arrays.asList(new TicketAttachmentEntity());
    }

    @Test
    void testEquals() {
        final boolean result = ticketInfoDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketInfoDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketInfoDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketInfoDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
