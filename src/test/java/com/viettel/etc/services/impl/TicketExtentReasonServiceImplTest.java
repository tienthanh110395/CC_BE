package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketExtentReasonDTO;
import com.viettel.etc.repositories.TicketExtentReasonRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketExtentReasonServiceImplTest {

    @Mock
    private TicketExtentReasonRepository mockTicketExtentReasonRepository;

    @InjectMocks
    private TicketExtentReasonServiceImpl ticketExtentReasonServiceImplUnderTest;

    @Test
    void testGetTicketExtentReason() {
        // Setup
        final TicketExtentReasonDTO itemParamsEntity = new TicketExtentReasonDTO();
        itemParamsEntity.setTicketExtentReasonId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setStartrecord(0);
        itemParamsEntity.setPagesize(0);

        // Configure TicketExtentReasonRepository.getTicketExtentReason(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        Mockito.lenient().when(mockTicketExtentReasonRepository.getTicketExtentReason(new TicketExtentReasonDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketExtentReasonServiceImplUnderTest.getTicketExtentReason(itemParamsEntity);

        // Verify the results
    }
}
