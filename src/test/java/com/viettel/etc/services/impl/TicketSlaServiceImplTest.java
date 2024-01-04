package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketSLADTO;
import com.viettel.etc.repositories.TicketSlaRepository;
import com.viettel.etc.services.tables.TicketSlaServiceJPA;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketSlaServiceImplTest {

    @Mock
    private TicketSlaRepository mockTicketSlaRepository;
    @Mock
    private TicketSlaServiceJPA mockTicketSlaServiceJPA;

    @InjectMocks
    private TicketSlaServiceImpl ticketSlaServiceImplUnderTest;

    @Test
    void testGetTicketSla() {
        // Setup
        final TicketSLADTO itemParamsEntity = new TicketSLADTO(0L, "slaName", 0L, "description", 0L, 0L, 0L, 0L, 0L, "createUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "updateUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, 0, false);

        // Configure TicketSlaRepository.getTicketSla(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketSlaRepository.getTicketSla(new TicketSLADTO(0L, "slaName", 0L, "description", 0L, 0L, 0L, 0L, 0L, "createUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "updateUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, 0, false))).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketSlaServiceImplUnderTest.getTicketSla(itemParamsEntity);

        // Verify the results
    }

    @Test
    void testGetTicketSlaDetail() {
        // Setup
        // Configure TicketSlaRepository.getTicketSlaDetail(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketSlaRepository.getTicketSlaDetail(0L)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketSlaServiceImplUnderTest.getTicketSlaDetail(0L, null);

        // Verify the results
    }
}
