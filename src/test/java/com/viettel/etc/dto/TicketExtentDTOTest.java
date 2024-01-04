package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TicketExtentDTOTest {

    @Mock
    private TicketExtentEntity mockTicketExtentEntity;

    private TicketExtentDTO ticketExtentDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExtentDTOUnderTest = new TicketExtentDTO(mockTicketExtentEntity);
    }

    @Test
    void testEquals() {
        final boolean result = ticketExtentDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketExtentDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketExtentDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketExtentDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
