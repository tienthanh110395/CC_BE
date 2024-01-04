package com.viettel.etc.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketRepository;
import com.viettel.etc.repositories.tables.TicketRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketStatusRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.CRMService;
import com.viettel.etc.services.TicketCategoryService;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import mockit.MockUp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketServiceImplTest {
    @InjectMocks
    private TicketServiceImpl ticketService;
    @Mock
    private TicketRepository mockTicketRepository;
    @Mock
    private TicketRepositoryJPA mockTicketRepositoryJPA;
    @Mock
    private TicketServiceJPA mockTicketServiceJPA;
    @Mock
    TicketCategoryService ticketCategoryService;
    @Mock
    private TicketAttachmentServiceImpl mockTicketAttachmentService;
    @Mock
    private TicketStatusRepositoryJPA mockTicketStatusRepositoryJPA;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    TicketAttachmentServiceJPA ticketAttachmentServiceJPA;
    @Mock
    ActionAuditService actionAuditService;

    @Mock
    CRMService crmService;

    @Test
    void testGetTicketDetails_TicketAttachmentServiceJPAReturnsNoItems() {
        // Setup
        final TicketInfoDTO itemParamsEntity = new TicketInfoDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setContractId(0L);
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setCustTypeId("custTypeId");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setStatus("status");
        itemParamsEntity.setSourceId("sourceId");

        // Configure TicketRepository.getTicketInfo(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketRepository.getTicketInfo(itemParamsEntity)).thenReturn(resultSelectEntity);

        when(ticketService.ticketAttachmentServiceJPA.getTicketAttachment(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final Object result = ticketService.getTicketDetails(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketForCPT_TicketRepositoryReturnsNoItems() {
        // Setup
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

        final Authentication authentication = null;
        when(ticketService.ticketRepository.getTicket(new TicketDTO(), null)).thenReturn(Collections.emptyList());

        // Run the test
        final Object result = ticketService.getTicketForCPT(ticketDTO, authentication);

        // Verify the results
    }

    @Test
    void testUpdateTicket_TicketRepositoryJPAFindByIdReturnsAbsent() {
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
        when(ticketService.ticketRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketService.ticketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity);

        // Run the test
        final Object result = ticketService.updateTicket(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketInfo() {
        // Setup
        final TicketInfoDTO itemParamsEntity = new TicketInfoDTO();
        itemParamsEntity.setTicketId(String.valueOf(1L));
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setCustTypeId("custTypeId");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setStatus("status");
        itemParamsEntity.setSourceId("sourceId");
        itemParamsEntity.setSourceName("sourceName");

        // Configure TicketRepository.getTicketInfo(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketRepository.getTicketInfo(itemParamsEntity)).thenReturn(resultSelectEntity);

        // Configure TicketAttachmentService.getFileAttachInfo(...).
        TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setType(1L);
        ticketAttachmentEntity.setTicketId(1L);
        // Run the test
       ticketService.getTicketDetails(itemParamsEntity, null);

        // Verify the results

    }

    @Test
    void testGetTicketHistory() {
        // Setup
        final TicketHistoryDTO itemParamsEntity = new TicketHistoryDTO();
        itemParamsEntity.setTicketAssignId("1");
        itemParamsEntity.setTicketId("1");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setProcessTime(new Date(0L));
        itemParamsEntity.setProcessResult("processResult");
        itemParamsEntity.setSiteId("siteId");
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStaffName("staffName");
        itemParamsEntity.setActTypeId("actTypeId");
        itemParamsEntity.setSiteName("siteName");

        // Configure TicketRepository.getTicketHistory(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketRepository.getTicketHistory(any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.getTicketHistory(itemParamsEntity);

        // Verify the results
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

        // Configure TicketRepository.getListTicketHistories(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount("count");
        when(mockTicketRepository.getListTicketHistories(any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.getListTicketHistories(itemParamsEntity);

        // Verify the results
    }

    @Test
    void testExportHistoryTicket() {
        // Setup
        final TicketHistoryListDTO itemParamsEntity = new TicketHistoryListDTO();
        itemParamsEntity.setTicketId("1");
        itemParamsEntity.setTicketAssignId("ticketAssignId");
        itemParamsEntity.setL1TicketTypeId("l1TicketTypeId");
        itemParamsEntity.setGroupPA("groupPA");
        itemParamsEntity.setL2TicketTypeId("l2TicketTypeId");
        itemParamsEntity.setSubgroupPA("subgroupPA");
        itemParamsEntity.setL3TicketTypeId("l3TicketTypeId");
        itemParamsEntity.setDetailPA("detailPA");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setTicketStatus("ticketStatus");

        // Configure TicketRepository.getListTicketHistories(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketRepository.getListTicketHistories(any())).thenReturn(resultSelectEntity);
        new MockUp<FunctionCommon>() {
            @mockit.Mock
            public Boolean exportFileExcell(ExcellSheet sheetExprort, String strPathExportFile, String strTitle) {
                return true;
            }
        };
        // Run the test
        final Object result = ticketService.exportHistoryTicket(itemParamsEntity);

        // Verify the results
//        Assertions.assertNotNull(result);
        new MockUp<FunctionCommon>() {
            @mockit.Mock
            public Boolean exportFileExcell(ExcellSheet sheetExprort, String strPathExportFile, String strTitle) {
                return false;
            }
        };
//        Assertions.assertNull(ticketService.exportHistoryTicket(itemParamsEntity));
    }

    @Test
    void testSearchTicket() {
        // Setup
        final SearchTicketDTO itemParamsEntity = new SearchTicketDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setCustTypeId("custTypeId");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setCustAddress("custAddress");
        itemParamsEntity.setPriorityId("priorityId");
        itemParamsEntity.setCustTypeId("1");

        // Configure TicketRepository.searchTicket(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketRepository.searchTicket(itemParamsEntity)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.searchTicket(itemParamsEntity);

        // Verify the results
    }

    @Test
    void testExportTicketNotAssign() {
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

        final Authentication authentication = null;

        // Configure TicketRepository.getTicketNotAssign(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketService.ticketRepository.getTicketNotAssign(new SearchTicketDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.exportTicketNotAssign(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketForCPT() {
        // Setup
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

        final Authentication authentication = null;

        // Configure TicketRepository.getTicket(...).
        final TicketDTO ticketDTO1 = new TicketDTO();
        ticketDTO1.setTicketId(0L);
        ticketDTO1.setCustId(0L);
        ticketDTO1.setContractId(0L);
        ticketDTO1.setContractNo("contractNo");
        ticketDTO1.setCustTypeId(0L);
        ticketDTO1.setPlateNumber("plateNumber");
        ticketDTO1.setPhoneNumber("phoneNumber");
        ticketDTO1.setCustName("custName");
        ticketDTO1.setEmail("email");
        ticketDTO1.setCustAddress("custAddress");
        final List<TicketDTO> ticketDTOS = Arrays.asList(ticketDTO1);
        when(ticketService.ticketRepository.getTicket(new TicketDTO(), null)).thenReturn(ticketDTOS);

        // Run the test
        final Object result = ticketService.getTicketForCPT(ticketDTO, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketNotAssign() {
        // Setup
        final SearchTicketDTO searchTicketDTO = new SearchTicketDTO();
        searchTicketDTO.setTicketId("0L");
        searchTicketDTO.setCustId("0L");
        searchTicketDTO.setContractId(0L);
        searchTicketDTO.setContractNo("contractNo");
        searchTicketDTO.setCustTypeId("0L");
        searchTicketDTO.setPlateNumber("plateNumber");
        searchTicketDTO.setPhoneNumber("phoneNumber");
        searchTicketDTO.setCustName("custName");
        searchTicketDTO.setEmail("email");
        searchTicketDTO.setCustAddress("custAddress");

        final Authentication authentication = null;

        // Configure TicketRepository.getTicketNotAssign(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketService.ticketRepository.getTicketNotAssign(new SearchTicketDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.getTicketNotAssign(searchTicketDTO, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketReportPerformmance() {
        // Setup
        final SearchTicketDTO ticketDTO = new SearchTicketDTO();
        ticketDTO.setTicketId("ticketId");
        ticketDTO.setCustId("custId");
        ticketDTO.setContractId(0L);
        ticketDTO.setContractNo("contractNo");
        ticketDTO.setCustTypeId("custTypeId");
        ticketDTO.setPlateNumber("plateNumber");
        ticketDTO.setPhoneNumber("phoneNumber");
        ticketDTO.setCustName("custName");
        ticketDTO.setEmail("email");
        ticketDTO.setCustAddress("custAddress");

        final Authentication authentication = null;

        // Configure TicketRepository.getTicketReportPerformmance(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketService.ticketRepository.getTicketReportPerformmance(new SearchTicketDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.getTicketReportPerformmance(ticketDTO, authentication);

        // Verify the results
    }

    @Test
    void testUpdateTicket() {
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

        // Configure TicketRepositoryJPA.findById(...).
        final Optional<TicketEntity> ticketEntity = Optional.of(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        when(ticketService.ticketRepositoryJPA.findById(0L)).thenReturn(ticketEntity);

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity1 = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketService.ticketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity1);

        // Run the test
        final Object result = ticketService.updateTicket(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testEditTicket() {
        // Setup
        final TicketInfoDTO itemParamsEntity = new TicketInfoDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setContractId(0L);
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setCustTypeId(String.valueOf(0L));
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setStatus(String.valueOf(0L));
        itemParamsEntity.setSourceId(String.valueOf(0L));

        final Authentication authentication = null;

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(mockTicketServiceJPA.getOne(0L)).thenReturn(ticketEntity);
        when(ticketService.ticketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity);

        // Configure ActionAuditService.saveActAudit(...).
        final ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
        actionAuditEntity.setActionAuditId(0L);
        actionAuditEntity.setActTypeId(0L);
        actionAuditEntity.setContractId(0L);
        actionAuditEntity.setTicketId(0L);
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketAssignId(0L);
        actionAuditEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity.setActionUserFullName("actionUserFullName");
        actionAuditEntity.setActionUserName("actionUserName");
        actionAuditEntity.setAppId("appId");
        actionAuditEntity.setIpPc("ipPc");
        when(ticketService.actionAuditService.saveActAudit(null, null)).thenReturn(actionAuditEntity);

        // Configure TicketAttachmentService.saveTicketAttachment(...).
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity);
        when(ticketService.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketService.editTicket(itemParamsEntity, 0L, authentication);

        // Verify the results
    }

    @Test
    void testExportTicket() {
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

        // Configure TicketRepository.searchTicket(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketService.ticketRepository.searchTicket(new SearchTicketDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.exportTicket(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testExportTicketReportPerformance() {
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

        final Authentication authentication = null;

        // Configure TicketRepository.getTicketReportPerformmance(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketService.ticketRepository.getTicketReportPerformmance(new SearchTicketDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketService.exportTicketReportPerformance(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketDetails() {
        // Setup
        final TicketInfoDTO itemParamsEntity = new TicketInfoDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setContractId(0L);
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setCustId("custId");
        itemParamsEntity.setCustName("custName");
        itemParamsEntity.setCustTypeId("custTypeId");
        itemParamsEntity.setPhoneNumber("phoneNumber");
        itemParamsEntity.setEmail("email");
        itemParamsEntity.setStatus("status");
        itemParamsEntity.setSourceId("sourceId");

        // Configure TicketRepository.getTicketInfo(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketRepository.getTicketInfo(itemParamsEntity)).thenReturn(resultSelectEntity);

        // Configure TicketAttachmentServiceJPA.getTicketAttachment(...).
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity);
        when(ticketService.ticketAttachmentServiceJPA.getTicketAttachment(0L, 0L)).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketService.getTicketDetails(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testSaveTicket() {
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

        // Configure TicketRepositoryJPA.findById(...).
        final Optional<TicketEntity> ticketEntity = Optional.of(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        when(ticketService.ticketRepositoryJPA.findById(0L)).thenReturn(ticketEntity);

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity1 = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketService.ticketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity1);

        when(ticketCategoryService.getDateTicketSlaNew(itemParamsEntity.getPriorityId(), itemParamsEntity.getL3TicketTypeId(), authentication, null)).thenReturn(null);

        // Configure ActionAuditService.saveActAudit(...).
        final ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
        actionAuditEntity.setActionAuditId(0L);
        actionAuditEntity.setActTypeId(0L);
        actionAuditEntity.setContractId(0L);
        actionAuditEntity.setTicketId(0L);
        actionAuditEntity.setTicketAssignId(0L);
        actionAuditEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity.setActionUserFullName("actionUserFullName");
        actionAuditEntity.setActionUserName("actionUserName");
        actionAuditEntity.setAppId("appId");
        actionAuditEntity.setIpPc("ipPc");
        lenient().when(ticketService.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

        // Configure ActionAuditService.saveActAuditDetail(...).
        final ActionAuditDetailEntity actionAuditDetailEntity = new ActionAuditDetailEntity();
        actionAuditDetailEntity.setActionAuditDetailId(0L);
        actionAuditDetailEntity.setActionAuditId(0L);
        actionAuditDetailEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailEntity.setTableName("tableName");
        actionAuditDetailEntity.setPkId(0L);
        actionAuditDetailEntity.setColumnName("columnName");
        actionAuditDetailEntity.setOldValue("oldValue");
        actionAuditDetailEntity.setNewValue("newValue");
        actionAuditDetailEntity.setActionName("actionName");
        final List<ActionAuditDetailEntity> actionAuditDetailEntities = Arrays.asList(actionAuditDetailEntity);
        when(ticketService.actionAuditService.saveActAuditDetail(0L, new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

        // Configure TicketAttachmentService.saveTicketAttachment(...).
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity);
        when(ticketService.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Configure TicketStatusRepositoryJPA.save(...).
        final TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketStatusId(0L);
        ticketStatusEntity.setTicketId(0L);
        ticketStatusEntity.setSiteId(0L);
        ticketStatusEntity.setTicketStatus(0L);
        ticketStatusEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setNote("note");
        ticketStatusEntity.setCreateUser("createUser");
        ticketStatusEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setUpdateUser("updateUser");
        ticketStatusEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketService.ticketStatusRepositoryJPA.save(new TicketStatusEntity())).thenReturn(ticketStatusEntity);

        // Run the test
        final Object result = ticketService.saveTicket(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testSaveTicketForCPT() {
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
        new MockUp<FnCommon>() {
            @mockit.Mock
            public String getUserLogin(Authentication authentication) {
                return "contractNo";
            }
        };

        final ContractDetailDTO contractDetailDTO = new ContractDetailDTO();
        contractDetailDTO.setCusTypeId(0L);
        contractDetailDTO.setUserName("userName");
        contractDetailDTO.setUserId("userId");
        contractDetailDTO.setBirth("birth");
        contractDetailDTO.setGender("gender");
        contractDetailDTO.setAddress("address");
        contractDetailDTO.setIdentifier("identifier");
        contractDetailDTO.setRepIdentifier("repIdentifier");
        contractDetailDTO.setRepIdentifierType(0L);
        contractDetailDTO.setDateOfIssue("dateOfIssue");
        when(ticketService.crmService.getContractDetails(null)).thenReturn(contractDetailDTO);

        final Authentication authentication = null;

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketService.ticketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity);

        // Configure ActionAuditService.saveActAudit(...).
        final ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
        actionAuditEntity.setActionAuditId(0L);
        actionAuditEntity.setActTypeId(0L);
        actionAuditEntity.setContractId(0L);
        actionAuditEntity.setTicketId(0L);
        actionAuditEntity.setTicketAssignId(0L);
        actionAuditEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity.setActionUserFullName("actionUserFullName");
        actionAuditEntity.setActionUserName("actionUserName");
        actionAuditEntity.setAppId("appId");
        actionAuditEntity.setIpPc("ipPc");
        when(ticketService.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

        // Configure ActionAuditService.saveActAuditDetail(...).
        final ActionAuditDetailEntity actionAuditDetailEntity = new ActionAuditDetailEntity();
        actionAuditDetailEntity.setActionAuditDetailId(0L);
        actionAuditDetailEntity.setActionAuditId(0L);
        actionAuditDetailEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailEntity.setTableName("tableName");
        actionAuditDetailEntity.setPkId(0L);
        actionAuditDetailEntity.setColumnName("columnName");
        actionAuditDetailEntity.setOldValue("oldValue");
        actionAuditDetailEntity.setNewValue("newValue");
        actionAuditDetailEntity.setActionName("actionName");
        final List<ActionAuditDetailEntity> actionAuditDetailEntities = Arrays.asList(actionAuditDetailEntity);
        when(ticketService.actionAuditService.saveActAuditDetail(eq(0L), any(Object.class), eq(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L)), eq(0L), eq("actionName"))).thenReturn(actionAuditDetailEntities);

        // Configure TicketAttachmentService.saveTicketAttachment(...).
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity);
        when(ticketService.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Configure TicketStatusRepositoryJPA.save(...).
        final TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketStatusId(0L);
        ticketStatusEntity.setTicketId(0L);
        ticketStatusEntity.setSiteId(0L);
        ticketStatusEntity.setTicketStatus(0L);
        ticketStatusEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setNote("note");
        ticketStatusEntity.setCreateUser("createUser");
        ticketStatusEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setUpdateUser("updateUser");
        ticketStatusEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketService.ticketStatusRepositoryJPA.save(new TicketStatusEntity())).thenReturn(ticketStatusEntity);

        // Run the test
        final Object result = ticketService.saveTicketForCPT(itemParamsEntity, authentication);

        // Verify the results
    }
}
