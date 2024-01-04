package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TicketRepositoryImplTest {
    @InjectMocks
    private TicketRepositoryImpl ticketRepositoryImplUnderTest;


    @Test
    void testGetTicketInfo() {
        // Setup
        HashMap<String, Object> hmapParams = new HashMap<>();
        final TicketInfoDTO itemParamsEntity = new TicketInfoDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setCustTypeId("custTypeId");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setStatus("status");
        itemParamsEntity.setSourceId("sourceId");
        itemParamsEntity.setSourceName("sourceName");
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.getTicketInfo(itemParamsEntity));
    }

    @Test
    void testGetTicketHistory() {
        // Setup
        final TicketHistoryDTO itemParamsEntity = new TicketHistoryDTO();
        itemParamsEntity.setTicketAssignId("ticketAssignId");
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setProcessTime(new Date(0L));
        itemParamsEntity.setProcessResult("processResult");
        itemParamsEntity.setSiteId("siteId");
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStaffName("staffName");
        itemParamsEntity.setActTypeId("actTypeId");
        itemParamsEntity.setSiteName("siteName");
        itemParamsEntity.setPagesize(1);
        itemParamsEntity.setStartrecord(1);

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.getTicketHistory(itemParamsEntity));
    }

    @Test
    void testGetListTicketHistories() {
        // Setup
        final TicketHistoryListDTO itemParamsEntity = new TicketHistoryListDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setTicketAssignId("ticketAssignId");
        itemParamsEntity.setL1TicketTypeId("l1TicketTypeId");
        itemParamsEntity.setGroupPA("groupPA");
        itemParamsEntity.setL2TicketTypeId("l2TicketTypeId");
        itemParamsEntity.setSubgroupPA("subgroupPA");
        itemParamsEntity.setL3TicketTypeId("l3TicketTypeId");
        itemParamsEntity.setDetailPA("detailPA");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setPagesize(1);
        itemParamsEntity.setStartrecord(1);
        itemParamsEntity.setPhoneNumber("0912345678");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setPlateNumber("123456");
        itemParamsEntity.setPlateTypeCode("12");
        itemParamsEntity.setStartDate(new java.util.Date());

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.getListTicketHistories(itemParamsEntity));
        itemParamsEntity.setStartDate(null);
        itemParamsEntity.setEndDate(new java.util.Date());

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.getListTicketHistories(itemParamsEntity));
        itemParamsEntity.setStartDate(new java.util.Date());
        itemParamsEntity.setEndDate(new java.util.Date());

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.getListTicketHistories(itemParamsEntity));
    }

    @Test
    void testSearchTicket() {
        // Setup
        final SearchTicketDTO itemParamsEntity = new SearchTicketDTO();
        itemParamsEntity.setTicketId("1");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setCustTypeId("custTypeId");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setCustAddress("custAddress");
        itemParamsEntity.setPriorityId("1");
        itemParamsEntity.setPhoneNumber("0912345678");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setPlateNumber("123456");
        itemParamsEntity.setPlateTypeCode("12");
        itemParamsEntity.setSourceId("1");
        itemParamsEntity.setL1TicketTypeId("1");
        itemParamsEntity.setL2TicketTypeId("1");
        itemParamsEntity.setL3TicketTypeId("1");
        itemParamsEntity.setStatus("1");
        itemParamsEntity.setLocation("location");
        itemParamsEntity.setCreateUser("crateUser");
        itemParamsEntity.setStaffName("name");
        itemParamsEntity.setPagesize(1);
        itemParamsEntity.setStartrecord(1);
        itemParamsEntity.setProvinceName("ha noi");
        itemParamsEntity.setDistrictName("ha dong");
        itemParamsEntity.setCommuneName("kien hung");

        itemParamsEntity.setStartReceiveDate(new java.util.Date());
        itemParamsEntity.setStartProcessDate(new java.util.Date());
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.searchTicket(itemParamsEntity));

        itemParamsEntity.setStartReceiveDate(new java.util.Date());
        itemParamsEntity.setStartProcessDate(new java.util.Date());
        itemParamsEntity.setEndReceiveDate(new java.util.Date());
        itemParamsEntity.setEndProcessDate(new java.util.Date());

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.searchTicket(itemParamsEntity));

        itemParamsEntity.setStartReceiveDate(null);
        itemParamsEntity.setStartProcessDate(null);
        itemParamsEntity.setEndReceiveDate(new java.util.Date());
        itemParamsEntity.setEndProcessDate(new java.util.Date());

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketRepositoryImplUnderTest.searchTicket(itemParamsEntity));
    }

    @Test
    void testGetTicket() {
        // Setup
        final TicketDTO itemParamsEntity = new TicketDTO();
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setCustId(0L);
        itemParamsEntity.setContractId(0L);
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setCustTypeId(0L);
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setCustAddress("custAddress");

        final Authentication authentication = null;
        final TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(0L);
        ticketDTO.setCustId(0L);
        ticketDTO.setContractId(0L);
        ticketDTO.setContractNo("contractNo");
        ticketDTO.setCustTypeId(0L);
        ticketDTO.setPlateNumber("plateNumber");
        ticketDTO.setPhoneNumber("phoneNumber");
        ticketDTO.setCustName("custName");
        ticketDTO.setEmail("email");
        ticketDTO.setCustAddress("custAddress");
        final List<TicketDTO> expectedResult = Arrays.asList(ticketDTO);

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                ResultSelectEntity resultSelect = new ResultSelectEntity();
                resultSelect.setListData(expectedResult);
                return resultSelect;
            }
        };

        // Run the test
        final List<TicketDTO> result = ticketRepositoryImplUnderTest.getTicket(itemParamsEntity, authentication);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTicketNotAssign() {
        // Setup
        final SearchTicketDTO itemParamsEntity = new SearchTicketDTO();
        itemParamsEntity.setTicketId("0L");
        itemParamsEntity.setCustId("0L");
        itemParamsEntity.setContractId(0L);
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setCustTypeId("0L");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setCustAddress("custAddress");

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketRepositoryImplUnderTest.getTicketNotAssign(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketReportPerformmance() {
        // Setup
        final SearchTicketDTO itemParamsEntity = new SearchTicketDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setContractId(0L);
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setCustTypeId("custTypeId");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setCustAddress("custAddress");

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketRepositoryImplUnderTest.getTicketReportPerformmance(itemParamsEntity, null);

        // Verify the results
    }
}
