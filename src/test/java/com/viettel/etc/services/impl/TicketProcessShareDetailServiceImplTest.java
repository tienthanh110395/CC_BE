package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketProcessShareDetailDTO;
import com.viettel.etc.repositories.TicketProcessShareDetailRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketProcessShareDetailServiceImplTest {

    @Mock
    private TicketProcessShareDetailRepository mockTicketProcessShareDetailRepository;

    @InjectMocks
    private TicketProcessShareDetailServiceImpl ticketProcessShareDetailServiceImplUnderTest;

    @Test
    void testGetTicketProcessShareDetail() {
        // Setup
        final TicketProcessShareDetailDTO itemParamsEntity = new TicketProcessShareDetailDTO();
        itemParamsEntity.setTicketProcessShareDetailId(0L);
        itemParamsEntity.setTicketProcessShareId(0L);
        itemParamsEntity.setAssignTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setProcessUser("processUser");
        itemParamsEntity.setStartrecord(0);
        itemParamsEntity.setPagesize(0);

        // Configure TicketProcessShareDetailRepository.getTicketProcessShareDetail(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketProcessShareDetailRepository.getTicketProcessShareDetail(new TicketProcessShareDetailDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketProcessShareDetailServiceImplUnderTest.getTicketProcessShareDetail(itemParamsEntity);

        // Verify the results
    }
}
