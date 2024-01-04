package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.repositories.TicketStatusRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketStatusServiceImplTest {

    @Mock
    private TicketStatusRepository mockTicketStatusRepository;

    @InjectMocks
    private TicketStatusServiceImpl ticketStatusServiceImplUnderTest;

    @Test
    void testGetTicketStatusHistory() {
        // Setup
        final TicketStatusDTO itemParamsEntity = new TicketStatusDTO();
        itemParamsEntity.setTicketStatusId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setTicketStatus(0L);
        itemParamsEntity.setProcessTime(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setNote("note");
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());

        // Configure TicketStatusRepository.getTicketStatusHistory(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketStatusRepository.getTicketStatus(any(), any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketStatusServiceImplUnderTest.getTicketStatus(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketStatus() {
        // Setup
        final TicketStatusDTO itemParamsEntity = new TicketStatusDTO();
        itemParamsEntity.setTicketStatusId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setTicketStatus(0L);
        itemParamsEntity.setProcessTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setNote("note");
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Authentication authentication = null;

        // Configure TicketStatusRepository.getTicketStatus(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        Mockito.lenient().when(ticketStatusServiceImplUnderTest.ticketStatusRepository.getTicketStatus(new TicketStatusDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = mockTicketStatusRepository.getTicketStatus(itemParamsEntity, authentication);

        // Verify the results
    }
}
