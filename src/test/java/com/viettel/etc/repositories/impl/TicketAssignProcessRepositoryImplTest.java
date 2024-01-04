package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class TicketAssignProcessRepositoryImplTest {
    @InjectMocks
    private TicketAssignProcessRepositoryImpl ticketAssignProcessRepositoryImplUnderTest;



    @Test
    void testGetTicketAssignProcess() {
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
        itemParamsEntity.setStartrecord(1);
        itemParamsEntity.setPagesize(1);

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketAssignProcessRepositoryImplUnderTest.getTicketAssignProcess(itemParamsEntity, null));
    }
}
