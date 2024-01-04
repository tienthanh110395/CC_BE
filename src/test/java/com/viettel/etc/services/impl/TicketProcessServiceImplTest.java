package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.TicketProcessDTO;
import com.viettel.etc.repositories.TicketCategoryRepository;
import com.viettel.etc.repositories.TicketProcessRepository;
import com.viettel.etc.repositories.tables.*;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketProcessServiceImplTest {

    @Mock
    private TicketProcessRepository mockTicketProcessRepository;
    @Mock
    private TicketProcessRepositoryJPA mockTicketProcessRepositoryJPA;
    @Mock
    private TicketAttachmentServiceImpl mockTicketAttachmentService;
    @Mock
    private TicketStatusRepositoryJPA mockTicketStatusRepositoryJPA;
    @Mock
    private TicketRepositoryJPA mockTicketRepositoryJPA;
    @Mock
    private TicketAttachmentRepositoryJPA mockTicketAttachmentRepositoryJPA;
    @Mock
    private TicketAssignRepositoryJPA mockTicketAssignRepositoryJPA;
    @Mock
    private TicketAssignProcessRepositoryJPA mockTicketAssignProcessRepositoryJPA;
    @Mock
    private TicketCategoryRepository mockTicketCategoryRepository;
    @Mock
    private TicketSiteUserRepositoryJPA mockTicketSiteUserRepositoryJPA;
    @InjectMocks
    private TicketProcessServiceImpl ticketProcessServiceImplUnderTest;
    @Mock
    ActionAuditService actionAuditService;
    @Mock
    TicketAttachmentServiceJPA mockTicketAttachmentServiceJPA;

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
        itemParamsEntity.setReasonLevel2("reasonLevel1");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setStaffCode("staffCode");

        // Configure TicketProcessRepository.getTicketprocessInfo(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(itemParamsEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketProcessRepository.getTicketProcessInfo(any())).thenReturn(resultSelectEntity);

        // Configure TicketAttachmentService.getFileAttachInfo(...).

        // Run the test
        final Object result = ticketProcessServiceImplUnderTest.getTicketProcessDetails(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketProcessDetails() {
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

        // Configure TicketProcessRepository.getTicketProcessInfo(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        TicketProcessDTO ticketProcessDTO = new TicketProcessDTO();
        resultSelectEntity.setListData(Arrays.asList(ticketProcessDTO));
        resultSelectEntity.setCount("count");

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
        when(mockTicketProcessRepository.getTicketProcessInfo(itemParamsEntity)).thenReturn(resultSelectEntity);
        when(mockTicketAttachmentServiceJPA.getTicketAttachment(0L, 2L)).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketProcessServiceImplUnderTest.getTicketProcessDetails(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testSaveTicketProcess() {
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

        final Authentication authentication = null;

        // Configure TicketProcessRepositoryJPA.findByTicketId(...).
        final TicketProcessEntity ticketProcessEntity = new TicketProcessEntity();
        ticketProcessEntity.setTicketProcessId(0L);
        ticketProcessEntity.setTicketId(0L);
        ticketProcessEntity.setDestroyReason("destroyReason");
        ticketProcessEntity.setProcessResult("processResult");
        ticketProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity.setStatus(0L);
        ticketProcessEntity.setReasonLevel1("reasonLevel1");
        ticketProcessEntity.setReasonLevel2("reasonLevel2");
        ticketProcessEntity.setProcessContent("processContent");
        ticketProcessEntity.setStaffCode("staffCode");
        when(ticketProcessServiceImplUnderTest.ticketProcessRepositoryJPA.findByTicketId(0L)).thenReturn(ticketProcessEntity);

        // Configure TicketRepositoryJPA.findById(...).
        final Optional<TicketEntity> ticketEntity = Optional.of(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        when(ticketProcessServiceImplUnderTest.ticketRepositoryJPA.findById(0L)).thenReturn(ticketEntity);

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
        lenient().when(ticketProcessServiceImplUnderTest.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

        // Configure TicketSiteUserRepositoryJPA.findByUserNameIgnoreCase(...).
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        when(ticketProcessServiceImplUnderTest.ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase(any())).thenReturn(ticketSiteUserEntity);

        // Configure TicketProcessRepositoryJPA.findById(...).
        final TicketProcessEntity ticketProcessEntity2 = new TicketProcessEntity();
        ticketProcessEntity2.setTicketProcessId(0L);
        ticketProcessEntity2.setTicketId(0L);
        ticketProcessEntity2.setDestroyReason("destroyReason");
        ticketProcessEntity2.setProcessResult("processResult");
        ticketProcessEntity2.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity2.setStatus(0L);
        ticketProcessEntity2.setReasonLevel1("reasonLevel1");
        ticketProcessEntity2.setReasonLevel2("reasonLevel2");
        ticketProcessEntity2.setProcessContent("processContent");
        ticketProcessEntity2.setStaffCode("staffCode");
        final Optional<TicketProcessEntity> ticketProcessEntity1 = Optional.of(ticketProcessEntity2);
        when(ticketProcessServiceImplUnderTest.ticketProcessRepositoryJPA.findById(0L)).thenReturn(ticketProcessEntity1);

        // Configure TicketProcessRepositoryJPA.save(...).
        final TicketProcessEntity ticketProcessEntity3 = new TicketProcessEntity();
        ticketProcessEntity3.setTicketProcessId(0L);
        ticketProcessEntity3.setTicketId(0L);
        ticketProcessEntity3.setDestroyReason("destroyReason");
        ticketProcessEntity3.setProcessResult("processResult");
        ticketProcessEntity3.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity3.setStatus(0L);
        ticketProcessEntity3.setReasonLevel1("reasonLevel1");
        ticketProcessEntity3.setReasonLevel2("reasonLevel2");
        ticketProcessEntity3.setProcessContent("processContent");
        ticketProcessEntity3.setStaffCode("staffCode");
        when(ticketProcessServiceImplUnderTest.ticketProcessRepositoryJPA.save(any())).thenReturn(ticketProcessEntity3);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketStatusRepositoryJPA.save(new TicketStatusEntity())).thenReturn(ticketStatusEntity);

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity1 = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity1);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAssignRepositoryJPA.findByTicketId(0L)).thenReturn(ticketAssignEntity);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAssignRepositoryJPA.save(new TicketAssignEntity())).thenReturn(ticketAssignEntity2);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAssignProcessRepositoryJPA.save(new TicketAssignProcessEntity())).thenReturn(ticketAssignProcessEntity);

        // Run the test
        final Object result = ticketProcessServiceImplUnderTest.saveTicketProcess(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testSaveTicketProcess_TicketAssignRepositoryJPAFindByTicketIdReturnsAbsent() {
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

        final Authentication authentication = null;

        // Configure TicketProcessRepositoryJPA.findByTicketId(...).
        final TicketProcessEntity ticketProcessEntity = new TicketProcessEntity();
        ticketProcessEntity.setTicketProcessId(0L);
        ticketProcessEntity.setTicketId(0L);
        ticketProcessEntity.setDestroyReason("destroyReason");
        ticketProcessEntity.setProcessResult("processResult");
        ticketProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity.setStatus(0L);
        ticketProcessEntity.setReasonLevel1("reasonLevel1");
        ticketProcessEntity.setReasonLevel2("reasonLevel2");
        ticketProcessEntity.setProcessContent("processContent");
        ticketProcessEntity.setStaffCode("staffCode");
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketProcessRepositoryJPA.findByTicketId(0L)).thenReturn(ticketProcessEntity);

        // Configure TicketRepositoryJPA.findById(...).
        final Optional<TicketEntity> ticketEntity = Optional.of(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketRepositoryJPA.findById(0L)).thenReturn(ticketEntity);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

        // Configure TicketSiteUserRepositoryJPA.findByUserNameIgnoreCase(...).
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase(any())).thenReturn(ticketSiteUserEntity);

        // Configure TicketProcessRepositoryJPA.findById(...).
        final TicketProcessEntity ticketProcessEntity2 = new TicketProcessEntity();
        ticketProcessEntity2.setTicketProcessId(0L);
        ticketProcessEntity2.setTicketId(0L);
        ticketProcessEntity2.setDestroyReason("destroyReason");
        ticketProcessEntity2.setProcessResult("processResult");
        ticketProcessEntity2.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity2.setStatus(0L);
        ticketProcessEntity2.setReasonLevel1("reasonLevel1");
        ticketProcessEntity2.setReasonLevel2("reasonLevel2");
        ticketProcessEntity2.setProcessContent("processContent");
        ticketProcessEntity2.setStaffCode("staffCode");
        final Optional<TicketProcessEntity> ticketProcessEntity1 = Optional.of(ticketProcessEntity2);
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketProcessRepositoryJPA.findById(0L)).thenReturn(ticketProcessEntity1);

        // Configure TicketProcessRepositoryJPA.save(...).
        final TicketProcessEntity ticketProcessEntity3 = new TicketProcessEntity();
        ticketProcessEntity3.setTicketProcessId(0L);
        ticketProcessEntity3.setTicketId(0L);
        ticketProcessEntity3.setDestroyReason("destroyReason");
        ticketProcessEntity3.setProcessResult("processResult");
        ticketProcessEntity3.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity3.setStatus(0L);
        ticketProcessEntity3.setReasonLevel1("reasonLevel1");
        ticketProcessEntity3.setReasonLevel2("reasonLevel2");
        ticketProcessEntity3.setProcessContent("processContent");
        ticketProcessEntity3.setStaffCode("staffCode");
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketProcessRepositoryJPA.save(new TicketProcessEntity())).thenReturn(ticketProcessEntity3);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketProcessEntity(), new TicketProcessEntity(), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketStatusRepositoryJPA.save(new TicketStatusEntity())).thenReturn(ticketStatusEntity);

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity1 = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity1);

        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAssignRepositoryJPA.findByTicketId(0L)).thenReturn(Optional.empty());

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAssignRepositoryJPA.save(new TicketAssignEntity())).thenReturn(ticketAssignEntity);

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
        Mockito.lenient().when(ticketProcessServiceImplUnderTest.ticketAssignProcessRepositoryJPA.save(new TicketAssignProcessEntity())).thenReturn(ticketAssignProcessEntity);

        // Run the test
        final Object result = ticketProcessServiceImplUnderTest.saveTicketProcess(itemParamsEntity, authentication);

        // Verify the results
    }
}