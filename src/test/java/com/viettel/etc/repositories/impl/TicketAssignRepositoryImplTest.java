package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class TicketAssignRepositoryImplTest {
    @InjectMocks
    private TicketAssignRepositoryImpl ticketAssignRepositoryImplUnderTest;


    @Test
    void testGetTicketAssigns() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("1");
        itemParamsEntity.setTicketStatus("1");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("1");
        itemParamsEntity.setReasonLevel1("reasonlevel1");
        itemParamsEntity.setStartrecord(1);
        itemParamsEntity.setPagesize(1);


        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketAssignRepositoryImplUnderTest.getTicketAssigns(itemParamsEntity, null));

        itemParamsEntity.setStartReceiveDate(null);
        itemParamsEntity.setStartProcessDate(null);


        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketAssignRepositoryImplUnderTest.getTicketAssigns(itemParamsEntity, null));

        itemParamsEntity.setEndReceiveDate(null);
        itemParamsEntity.setEndProcessDate(null);


        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketAssignRepositoryImplUnderTest.getTicketAssigns(itemParamsEntity, null));
    }

    @Test
    void testGetTicketAssignById() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonlevel1");
        itemParamsEntity.setTicketAssignId(1L);
        itemParamsEntity.setStartrecord(1);
        itemParamsEntity.setPagesize(1);
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketAssignRepositoryImplUnderTest.getTicketAssignById(itemParamsEntity, null));
    }
}
