package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketAssignLogDTO;
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
class TicketAssignLogRepositoryImplTest {
    @InjectMocks
    private TicketAssignLogRepositoryImpl ticketAssignLogRepositoryImplUnderTest;

    @Test
    void testGetAssignLogByTicketId() {
        // Setup
        final TicketAssignLogDTO itemParamsEntity = new TicketAssignLogDTO();
        itemParamsEntity.setTicketAssignLogId(0L);
        itemParamsEntity.setTicketAssignId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setLogContent("logContent");
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setLogType(0L);
        itemParamsEntity.setStartrecord(1);
        itemParamsEntity.setPagesize(1);


        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketAssignLogRepositoryImplUnderTest.getTicketAssignLog(itemParamsEntity, null));
    }

    @Test
    void testGetTicketAssignLog() {
        // Setup
        final TicketAssignLogDTO itemParamsEntity = new TicketAssignLogDTO();
        itemParamsEntity.setTicketAssignLogId(0L);
        itemParamsEntity.setTicketAssignId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setLogContent("logContent");
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setLogType(0L);
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketAssignLogRepositoryImplUnderTest.getTicketAssignLog(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNull(result);
    }
}
