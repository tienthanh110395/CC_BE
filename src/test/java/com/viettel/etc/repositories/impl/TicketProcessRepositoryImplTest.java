package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketProcessDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class TicketProcessRepositoryImplTest {
    @InjectMocks
    private TicketProcessRepositoryImpl ticketProcessRepositoryImplUnderTest;

    @Test
    void testGetTicketprocessInfo() {
        // Setup
        final TicketProcessDTO itemParamsEntity = new TicketProcessDTO();
        itemParamsEntity.setTicketProcessId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setDestroyReason("destroyReason");
        itemParamsEntity.setProcessResult("processResult");
        itemParamsEntity.setProcessTime(new Date(0L));
        itemParamsEntity.setStatus(1L);
        itemParamsEntity.setReasonLevel1("reasonLevel1");
        itemParamsEntity.setReasonLevel2("reasonLevel2");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStartrecord(1);
        itemParamsEntity.setPagesize(1);



        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketProcessRepositoryImplUnderTest.getTicketProcessInfo(itemParamsEntity));
    }

    @Test
    void testGetTicketProcessInfo() {
        // Setup
        final TicketProcessDTO itemParamsEntity = new TicketProcessDTO();
        itemParamsEntity.setTicketProcessId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setDestroyReason("destroyReason");
        itemParamsEntity.setProcessResult("processResult");
        itemParamsEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setReasonLevel1("reasonLevel1");
        itemParamsEntity.setReasonLevel2("reasonLevel2");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setStaffCode("staffCode");
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketProcessRepositoryImplUnderTest.getTicketProcessInfo(itemParamsEntity);

        // Verify the results
        Assertions.assertNull(result);
    }
}
