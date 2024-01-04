package com.viettel.etc.services.impl;

import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketAssignProcessIdDTO;
import com.viettel.etc.repositories.TicketAssignRepository;
import com.viettel.etc.repositories.tables.TicketAssignProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketAssignRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketAssignServiceImplTest {

    @Mock
    private TicketAssignRepository mockTicketAssignRepository;
    @Mock
    private TicketAssignRepositoryJPA mockTicketAssignRepositoryJPA;
    @Mock
    private TicketAttachmentService mockTicketAttachmentService;
    @Mock
    private TicketAssignProcessRepositoryJPA mockTicketAssignProcessRepositoryJPA;
    @Mock
    private TicketRepositoryJPA  mocktTicketRepositoryJPA;
    @Mock
    TicketAttachmentServiceJPA ticketAttachmentServiceJPA;
    @InjectMocks
    private TicketAssignServiceImpl ticketAssignServiceImplUnderTest;

    @Mock
    ActionAuditService actionAuditService;

    @Test
    void testGetTicketAssigns() {
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

        // Configure TicketAssignRepository.getTicketAssigns(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketAssignRepository.getTicketAssigns(any(), any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.getTicketAssigns(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testExportTicketAssigns() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("1");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonlevel1");

        // Configure TicketAssignRepository.getTicketAssigns(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketAssignRepository.getTicketAssigns(any(), any())).thenReturn(resultSelectEntity);
        new MockUp<FunctionCommon>() {
            @mockit.Mock
            public Boolean exportFileExcell(ExcellSheet sheetExprort, String strPathExportFile, String strTitle) {
                return true;
            }
        };
        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.exportTicketAssigns(itemParamsEntity, null);
        // Verify the results
        Assertions.assertNotNull(result);
        new MockUp<FunctionCommon>() {
            @mockit.Mock
            public Boolean exportFileExcell(ExcellSheet sheetExprort, String strPathExportFile, String strTitle) {
                return false;
            }
        };
        Assertions.assertNull(ticketAssignServiceImplUnderTest.exportTicketAssigns(itemParamsEntity, null));


    }

    @Test
    void testReceiveTicketToHandle() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("0");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonlevel1");

        // Configure TicketAssignRepositoryJPA.getOne(...).
        final TicketAssignEntity ticketAssignEntity = new TicketAssignEntity();
        ticketAssignEntity.setTicketAssignId(0L);
        ticketAssignEntity.setTicketId(0L);
        ticketAssignEntity.setSiteId(0L);
        ticketAssignEntity.setTicketStatus(1L);
        ticketAssignEntity.setFromUsername("fromUsername");
        ticketAssignEntity.setToUsername("toUsername");
        ticketAssignEntity.setAssignDate(new Date(0L));
        ticketAssignEntity.setResolveDate(new Date(0L));
        ticketAssignEntity.setFromSiteId(0L);
        ticketAssignEntity.setSlaDate(new Date(0L));
        ticketAssignEntity.setAssignType(0L);
        when(mockTicketAssignRepositoryJPA.getOne(any())).thenReturn(ticketAssignEntity);

        // Configure TicketAssignRepositoryJPA.save(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(new Date(0L));
        ticketAssignEntity1.setResolveDate(new Date(0L));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(new Date(0L));
        ticketAssignEntity1.setAssignType(0L);
        when(mockTicketAssignRepositoryJPA.save(any())).thenReturn(ticketAssignEntity1);

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.receiveTicketToHandle(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketAssignByTicketId() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("0");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonlevel1");

        // Configure TicketAssignRepositoryJPA.findByTicketId(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(new Date(0L));
        ticketAssignEntity1.setResolveDate(new Date(0L));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(new Date(0L));
        ticketAssignEntity1.setAssignType(0L);
        final Optional<TicketAssignEntity> ticketAssignEntity = Optional.of(ticketAssignEntity1);
        when(mockTicketAssignRepositoryJPA.findByTicketId(0L)).thenReturn(ticketAssignEntity);

        // Configure TicketAssignProcessRepositoryJPA.findByTicketId(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity1 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity1.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity1.setTicketAssignId(0L);
        ticketAssignProcessEntity1.setTicketId(0L);
        ticketAssignProcessEntity1.setProcessContent("processContent");
        ticketAssignProcessEntity1.setProcessTime(new Date(0L));
        ticketAssignProcessEntity1.setProcessResult("processResult");
        ticketAssignProcessEntity1.setSiteId(0L);
        ticketAssignProcessEntity1.setStaffCode("staffCode");
        ticketAssignProcessEntity1.setStaffName("staffName");
        ticketAssignProcessEntity1.setCreateUser("createUser");
        final List<TicketAssignProcessEntity> ticketAssignProcessEntity = Arrays.asList(ticketAssignProcessEntity1);
        when(mockTicketAssignProcessRepositoryJPA.findAllByTicketId(0L)).thenReturn(ticketAssignProcessEntity);

        // Configure TicketAttachmentService.getFileAttachInfo(...).

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.getTicketAssignByTicketId(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);


    }

    @Test
    void testGetTicketAssignByTicketId_TicketAssignRepositoryJPAReturnsAbsent() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("0");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonlevel1");

        when(mockTicketAssignRepositoryJPA.findByTicketId(0L)).thenReturn(Optional.empty());
        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.getTicketAssignByTicketId(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNull(result);
    }


    @Test
    void testGetTicketAssignById() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("0");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonlevel1");

        // Configure TicketAssignRepository.getTicketAssignById(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        TicketAssignProcessIdDTO ticketAssignProcessIdDTO = new TicketAssignProcessIdDTO();
        ticketAssignProcessIdDTO.setTicketId("1");
        resultSelectEntity.setListData(Arrays.asList(ticketAssignProcessIdDTO));
        resultSelectEntity.setCount(1);
        when(mockTicketAssignRepository.getTicketAssignById(any(), any())).thenReturn(resultSelectEntity);

        // Configure TicketAttachmentService.getFileAttachInfo(...).

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.getTicketAssignById(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testRemoveTicketAssignById() {
        TicketAssignDTO ticketAssignDTO = new TicketAssignDTO();
        ticketAssignDTO.setTicketAssignId(1L);
        ticketAssignDTO.setCreateUser("user");
        TicketAssignEntity ticketAssignEntity = new TicketAssignEntity();
        ticketAssignEntity.setTicketAssignId(1L);
        ticketAssignEntity.setCreateUser("user");
        ticketAssignEntity.setTicketStatus(1L);
        Optional<TicketAssignEntity> ticketAssignEntity1 = Optional.of(ticketAssignEntity);
        when(mockTicketAssignRepositoryJPA.findByTicketAssignIdAndCreateUser(any(),any())).thenReturn(ticketAssignEntity1);
        Assertions.assertNotNull(ticketAssignServiceImplUnderTest.removeTicketAssignById(ticketAssignDTO, null));
        ticketAssignEntity.setTicketStatus(2L);
        Assertions.assertNotNull(ticketAssignServiceImplUnderTest.removeTicketAssignById(ticketAssignDTO, null));
    }

    @Test
    void testGetTicketAssignByTicketId_TicketAssignProcessRepositoryJPAReturnsNoItems() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonLevel1");

        // Configure TicketAssignRepositoryJPA.findByTicketId(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setAssignType(0L);
        final Optional<TicketAssignEntity> ticketAssignEntity = Optional.of(ticketAssignEntity1);
        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.findByTicketId(0L)).thenReturn(ticketAssignEntity);

        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.findAllByTicketId(0L)).thenReturn(Collections.emptyList());

        // Configure TicketAttachmentServiceJPA.getByTicketIdAndTypeAssign(...).
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
        when(ticketAssignServiceImplUnderTest.ticketAttachmentServiceJPA.getByTicketIdAndTypeAssign(0L)).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.getTicketAssignByTicketId(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketAssignByTicketId_TicketAttachmentServiceJPAReturnsNoItems() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonLevel1");

        // Configure TicketAssignRepositoryJPA.findByTicketId(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setAssignType(0L);
        final Optional<TicketAssignEntity> ticketAssignEntity = Optional.of(ticketAssignEntity1);
        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.findByTicketId(0L)).thenReturn(ticketAssignEntity);

        // Configure TicketAssignProcessRepositoryJPA.findAllByTicketId(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
        ticketAssignProcessEntity.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity.setTicketAssignId(0L);
        ticketAssignProcessEntity.setTicketId(0L);
        ticketAssignProcessEntity.setProcessContent("processContent");
        ticketAssignProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity.setProcessResult("processResult");
        ticketAssignProcessEntity.setSiteId(0L);
        ticketAssignProcessEntity.setStaffCode("staffCode");
        ticketAssignProcessEntity.setStaffName("staffName");
        ticketAssignProcessEntity.setCreateUser("createUser");
        final List<TicketAssignProcessEntity> ticketAssignProcessEntities = Arrays.asList(ticketAssignProcessEntity);
        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.findAllByTicketId(0L)).thenReturn(ticketAssignProcessEntities);

        when(ticketAssignServiceImplUnderTest.ticketAttachmentServiceJPA.getByTicketIdAndTypeAssign(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.getTicketAssignByTicketId(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testRemoveTicketAssignById_TicketAssignRepositoryJPAFindByTicketAssignIdAndCreateUserReturnsAbsent() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonLevel1");

        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.findByTicketAssignIdAndCreateUser(0L, "createUser")).thenReturn(Optional.empty());

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.removeTicketAssignById(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testSaveTicketAssign() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId(String.valueOf(0L));
        itemParamsEntity.setTicketStatus(String.valueOf(0L));
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id(String.valueOf(0L));
        itemParamsEntity.setReasonLevel1("reasonLevel1");

        final Authentication authentication = null;

        // Configure TicketRepositoryJPA.getOne(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketAssignServiceImplUnderTest.ticketRepositoryJPA.getOne(0L)).thenReturn(ticketEntity);

        // Configure TicketAssignRepositoryJPA.findById(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setAssignType(0L);
        final Optional<TicketAssignEntity> ticketAssignEntity = Optional.of(ticketAssignEntity1);
        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.findById(0L)).thenReturn(ticketAssignEntity);

        // Configure TicketAssignRepositoryJPA.save(...).
        final TicketAssignEntity ticketAssignEntity2 = new TicketAssignEntity();
        ticketAssignEntity2.setTicketAssignId(0L);
        ticketAssignEntity2.setTicketId(0L);
        ticketAssignEntity2.setSiteId(0L);
        ticketAssignEntity2.setFromUsername("fromUsername");
        ticketAssignEntity2.setToUsername("toUsername");
        ticketAssignEntity2.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setFromSiteId(0L);
        ticketAssignEntity2.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setAssignType(0L);
        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.save(new TicketAssignEntity())).thenReturn(ticketAssignEntity2);

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
        when(ticketAssignServiceImplUnderTest.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

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
        when(ticketAssignServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketAssignEntity(), new TicketAssignEntity(), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

        // Configure TicketAssignProcessRepositoryJPA.findByTicketIdAndStatus(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity1 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity1.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity1.setTicketAssignId(0L);
        ticketAssignProcessEntity1.setTicketId(0L);
        ticketAssignProcessEntity1.setProcessContent("processContent");
        ticketAssignProcessEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity1.setProcessResult("processResult");
        ticketAssignProcessEntity1.setSiteId(0L);
        ticketAssignProcessEntity1.setStaffCode("staffCode");
        ticketAssignProcessEntity1.setStaffName("staffName");
        ticketAssignProcessEntity1.setCreateUser("createUser");
        final Optional<TicketAssignProcessEntity> ticketAssignProcessEntity = Optional.of(ticketAssignProcessEntity1);
        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.findByTicketIdAndStatus(0L, 0L)).thenReturn(ticketAssignProcessEntity);

        // Configure TicketAssignProcessRepositoryJPA.save(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity2 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity2.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity2.setTicketAssignId(0L);
        ticketAssignProcessEntity2.setTicketId(0L);
        ticketAssignProcessEntity2.setProcessContent("processContent");
        ticketAssignProcessEntity2.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity2.setProcessResult("processResult");
        ticketAssignProcessEntity2.setSiteId(0L);
        ticketAssignProcessEntity2.setStaffCode("staffCode");
        ticketAssignProcessEntity2.setStaffName("staffName");
        ticketAssignProcessEntity2.setCreateUser("createUser");
        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.save(new TicketAssignProcessEntity())).thenReturn(ticketAssignProcessEntity2);

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
        when(ticketAssignServiceImplUnderTest.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.saveTicketAssign(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testSaveTicketAssign_TicketAssignProcessRepositoryJPAFindByTicketIdAndStatusReturnsAbsent() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId(String.valueOf(0L));
        itemParamsEntity.setTicketStatus(String.valueOf(0L));
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id(String.valueOf(0L));
        itemParamsEntity.setReasonLevel1("reasonLevel1");

        final Authentication authentication = null;

        // Configure TicketRepositoryJPA.getOne(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketAssignServiceImplUnderTest.ticketRepositoryJPA.getOne(0L)).thenReturn(ticketEntity);

        // Configure TicketAssignRepositoryJPA.findById(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setAssignType(0L);
        final Optional<TicketAssignEntity> ticketAssignEntity = Optional.of(ticketAssignEntity1);
        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.findById(0L)).thenReturn(ticketAssignEntity);

        // Configure TicketAssignRepositoryJPA.save(...).
        final TicketAssignEntity ticketAssignEntity2 = new TicketAssignEntity();
        ticketAssignEntity2.setTicketAssignId(0L);
        ticketAssignEntity2.setTicketId(0L);
        ticketAssignEntity2.setSiteId(0L);
        ticketAssignEntity2.setFromUsername("fromUsername");
        ticketAssignEntity2.setToUsername("toUsername");
        ticketAssignEntity2.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setFromSiteId(0L);
        ticketAssignEntity2.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setAssignType(0L);
        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.save(new TicketAssignEntity())).thenReturn(ticketAssignEntity2);

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
        when(ticketAssignServiceImplUnderTest.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

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
        when(ticketAssignServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketAssignEntity(), new TicketAssignEntity(), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.findByTicketIdAndStatus(0L, 0L)).thenReturn(Optional.empty());

        // Configure TicketAssignProcessRepositoryJPA.save(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
        ticketAssignProcessEntity.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity.setTicketAssignId(0L);
        ticketAssignProcessEntity.setTicketId(0L);
        ticketAssignProcessEntity.setProcessContent("processContent");
        ticketAssignProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity.setProcessResult("processResult");
        ticketAssignProcessEntity.setSiteId(0L);
        ticketAssignProcessEntity.setStaffCode("staffCode");
        ticketAssignProcessEntity.setStaffName("staffName");
        ticketAssignProcessEntity.setCreateUser("createUser");
        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.save(new TicketAssignProcessEntity())).thenReturn(ticketAssignProcessEntity);

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
        when(ticketAssignServiceImplUnderTest.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.saveTicketAssign(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testSaveTicketAssign_TicketAssignRepositoryJPAFindByIdReturnsAbsent() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId(String.valueOf(0L));
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId(String.valueOf(0L));
        itemParamsEntity.setTicketStatus(String.valueOf(0L));
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id(String.valueOf(0L));
        itemParamsEntity.setReasonLevel1("reasonLevel1");

        final Authentication authentication = null;

        // Configure TicketRepositoryJPA.getOne(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketAssignServiceImplUnderTest.ticketRepositoryJPA.getOne(0L)).thenReturn(ticketEntity);

        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Configure TicketAssignRepositoryJPA.save(...).
        final TicketAssignEntity ticketAssignEntity = new TicketAssignEntity();
        ticketAssignEntity.setTicketAssignId(0L);
        ticketAssignEntity.setTicketId(0L);
        ticketAssignEntity.setSiteId(0L);
        ticketAssignEntity.setFromUsername("fromUsername");
        ticketAssignEntity.setToUsername("toUsername");
        ticketAssignEntity.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setFromSiteId(0L);
        ticketAssignEntity.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setAssignType(0L);
        when(ticketAssignServiceImplUnderTest.ticketAssignRepositoryJPA.save(new TicketAssignEntity())).thenReturn(ticketAssignEntity);

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
        when(ticketAssignServiceImplUnderTest.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

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
        when(ticketAssignServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketAssignEntity(), new TicketAssignEntity(), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

        // Configure TicketAssignProcessRepositoryJPA.findByTicketIdAndStatus(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity1 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity1.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity1.setTicketAssignId(0L);
        ticketAssignProcessEntity1.setTicketId(0L);
        ticketAssignProcessEntity1.setProcessContent("processContent");
        ticketAssignProcessEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity1.setProcessResult("processResult");
        ticketAssignProcessEntity1.setSiteId(0L);
        ticketAssignProcessEntity1.setStaffCode("staffCode");
        ticketAssignProcessEntity1.setStaffName("staffName");
        ticketAssignProcessEntity1.setCreateUser("createUser");
        final Optional<TicketAssignProcessEntity> ticketAssignProcessEntity = Optional.of(ticketAssignProcessEntity1);
        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.findByTicketIdAndStatus(0L, 0L)).thenReturn(ticketAssignProcessEntity);

        // Configure TicketAssignProcessRepositoryJPA.save(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity2 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity2.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity2.setTicketAssignId(0L);
        ticketAssignProcessEntity2.setTicketId(0L);
        ticketAssignProcessEntity2.setProcessContent("processContent");
        ticketAssignProcessEntity2.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity2.setProcessResult("processResult");
        ticketAssignProcessEntity2.setSiteId(0L);
        ticketAssignProcessEntity2.setStaffCode("staffCode");
        ticketAssignProcessEntity2.setStaffName("staffName");
        ticketAssignProcessEntity2.setCreateUser("createUser");
        when(ticketAssignServiceImplUnderTest.ticketAssignProcessRepositoryJPA.save(new TicketAssignProcessEntity())).thenReturn(ticketAssignProcessEntity2);

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
        when(ticketAssignServiceImplUnderTest.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAssignServiceImplUnderTest.saveTicketAssign(itemParamsEntity, authentication);

        // Verify the results
    }
}
