package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TicketStatisticTypeDTOTest {

    @Mock
    private StatisticTypeEntity mockStatisticType;

    private TicketStatisticTypeDTO ticketStatisticTypeDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketStatisticTypeDTOUnderTest = new TicketStatisticTypeDTO(mockStatisticType);
    }

    @Test
    void testEquals() {
        final boolean result = ticketStatisticTypeDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = ticketStatisticTypeDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = ticketStatisticTypeDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = ticketStatisticTypeDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
