package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.TicketAdjustChargeDTO;
import com.viettel.etc.repositories.TicketAdjustChargeRepository;
import com.viettel.etc.repositories.tables.TicketAdjustChargeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketAdjustChargeServiceImplTest {

    @Mock
    TicketAdjustChargeRepository mockTicketAdjustChargeRepository;

    @Mock
    TicketAttachmentService mockTicketAttachmentService;

    @Mock
    TicketAdjustChargeRepositoryJPA mockTicketAdjustChargeRepositoryJPA;

    @Mock
    TicketAttachmentServiceJPA ticketAttachmentServiceJPA;

    @Mock
    TicketServiceJPA mockTicketServiceJPA;

    @Mock
    ActionAuditService mockActionAuditService;

    @InjectMocks
    TicketAdjustChargeServiceImpl ticketAdjustChargeServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAdjustChargeServiceImplUnderTest.actionAuditService = mock(ActionAuditService.class);
    }

    @Test
    void testGetTicketAdjustCharge() {
        // Setup
        final TicketAdjustChargeDTO itemParamsEntity = new TicketAdjustChargeDTO();
        itemParamsEntity.setTicketAdjustChargeId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setPlateTypeCode("plateTypeCode");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPayType("payType");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setAccountType("accountType");
        itemParamsEntity.setAdjustAmount(0L);
        itemParamsEntity.setReason("reason");
        itemParamsEntity.setAdjustContent("adjustContent");

        // Configure TicketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity.setTicketId(0L);
        ticketAdjustChargeEntity.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity.setPayType("payType");
        ticketAdjustChargeEntity.setContractNo("contractNo");
        ticketAdjustChargeEntity.setAccountType("accountType");
        ticketAdjustChargeEntity.setAdjustAmount(0L);
        ticketAdjustChargeEntity.setReason("reason");
        ticketAdjustChargeEntity.setAdjustContent("adjustContent");
        final List<TicketAdjustChargeEntity> ticketAdjustChargeEntities = Arrays.asList(ticketAdjustChargeEntity);
        when(ticketAdjustChargeServiceImplUnderTest.ticketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(any(), any())).thenReturn(ticketAdjustChargeEntities);

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
        when(ticketAdjustChargeServiceImplUnderTest.ticketAttachmentServiceJPA.getTicketAttachment(0L, 3L)).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAdjustChargeServiceImplUnderTest.getTicketAdjustCharge(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketAdjustCharge_TicketAdjustChargeRepositoryJPAReturnsNoItems() {
        // Setup
        final TicketAdjustChargeDTO itemParamsEntity = new TicketAdjustChargeDTO();
        itemParamsEntity.setTicketAdjustChargeId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setPlateTypeCode("plateTypeCode");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPayType("payType");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setAccountType("accountType");
        itemParamsEntity.setAdjustAmount(0L);
        itemParamsEntity.setReason("reason");
        itemParamsEntity.setAdjustContent("adjustContent");
        when(ticketAdjustChargeServiceImplUnderTest.ticketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(0L, 1L)).thenReturn(Collections.emptyList());

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
        when(ticketAdjustChargeServiceImplUnderTest.ticketAttachmentServiceJPA.getTicketAttachment(0L, 0L)).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAdjustChargeServiceImplUnderTest.getTicketAdjustCharge(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketAdjustCharge_TicketAttachmentServiceJPAReturnsNoItems() {
        // Setup
        final TicketAdjustChargeDTO itemParamsEntity = new TicketAdjustChargeDTO();
        itemParamsEntity.setTicketAdjustChargeId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setPlateTypeCode("plateTypeCode");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPayType("payType");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setAccountType("accountType");
        itemParamsEntity.setAdjustAmount(0L);
        itemParamsEntity.setReason("reason");
        itemParamsEntity.setAdjustContent("adjustContent");

        // Configure TicketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity.setTicketId(0L);
        ticketAdjustChargeEntity.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity.setPayType("payType");
        ticketAdjustChargeEntity.setContractNo("contractNo");
        ticketAdjustChargeEntity.setAccountType("accountType");
        ticketAdjustChargeEntity.setAdjustAmount(0L);
        ticketAdjustChargeEntity.setReason("reason");
        ticketAdjustChargeEntity.setAdjustContent("adjustContent");
        final List<TicketAdjustChargeEntity> ticketAdjustChargeEntities = Arrays.asList(ticketAdjustChargeEntity);
        when(ticketAdjustChargeServiceImplUnderTest.ticketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(0L, 1L)).thenReturn(ticketAdjustChargeEntities);

        when(ticketAdjustChargeServiceImplUnderTest.ticketAttachmentServiceJPA.getTicketAttachment(0L, 3L)).thenReturn(Collections.emptyList());

        // Run the test
        final Object result = ticketAdjustChargeServiceImplUnderTest.getTicketAdjustCharge(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketAdjustInfo() {
        // Setup
        final TicketAdjustChargeDTO itemParamsEntity = new TicketAdjustChargeDTO();
        itemParamsEntity.setTicketAdjustChargeId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setPlateTypeCode("plateTypeCode");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPayType("payType");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setAccountType("accountType");
        itemParamsEntity.setAdjustAmount(0L);
        itemParamsEntity.setReason("reason");
        itemParamsEntity.setAdjustContent("adjustContent");

        // Configure TicketAdjustChargeRepositoryJPA.findByTicketId(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity1 = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity1.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity1.setTicketId(0L);
        ticketAdjustChargeEntity1.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity1.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity1.setPayType("payType");
        ticketAdjustChargeEntity1.setContractNo("contractNo");
        ticketAdjustChargeEntity1.setAccountType("accountType");
        ticketAdjustChargeEntity1.setAdjustAmount(0L);
        ticketAdjustChargeEntity1.setReason("reason");
        ticketAdjustChargeEntity1.setAdjustContent("adjustContent");
        final List<TicketAdjustChargeEntity> ticketAdjustChargeEntity = Collections.singletonList(ticketAdjustChargeEntity1);
        when(mockTicketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(0L, 1L)).thenReturn(ticketAdjustChargeEntity);

        // Configure TicketAttachmentService.getFileAttachInfo(...).
        // Run the test
        final Object result = ticketAdjustChargeServiceImplUnderTest.getTicketAdjustCharge(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketAdjustInfo_TicketAdjustChargeRepositoryJPAReturnsAbsent() {
        // Setup
        final TicketAdjustChargeDTO itemParamsEntity = new TicketAdjustChargeDTO();
        itemParamsEntity.setTicketAdjustChargeId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setPlateTypeCode("plateTypeCode");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPayType("payType");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setAccountType("accountType");
        itemParamsEntity.setAdjustAmount(0L);
        itemParamsEntity.setReason("reason");
        itemParamsEntity.setAdjustContent("adjustContent");

        when(mockTicketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(0L, 1L)).thenReturn(null);

        // Verify the results
        Assertions.assertNull(ticketAdjustChargeServiceImplUnderTest.getTicketAdjustCharge(itemParamsEntity, null));
    }

    @Test
    void testSaveTicketAdjustCharge() {
        // Setup
        final TicketAdjustChargeDTO itemParamsEntity = new TicketAdjustChargeDTO();
        itemParamsEntity.setTicketAdjustChargeId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setPlateTypeCode("plateTypeCode");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setPayType("payType");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setAccountType("accountType");
        itemParamsEntity.setAdjustAmount(0L);
        itemParamsEntity.setReason("reason");
        itemParamsEntity.setAdjustContent("adjustContent");

        final Authentication authentication = null;

        // Configure TicketAdjustChargeRepositoryJPA.findById(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity1 = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity1.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity1.setTicketId(0L);
        ticketAdjustChargeEntity1.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity1.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity1.setPayType("payType");
        ticketAdjustChargeEntity1.setContractNo("contractNo");
        ticketAdjustChargeEntity1.setAccountType("accountType");
        ticketAdjustChargeEntity1.setAdjustAmount(0L);
        ticketAdjustChargeEntity1.setReason("reason");
        ticketAdjustChargeEntity1.setAdjustContent("adjustContent");
        final Optional<TicketAdjustChargeEntity> ticketAdjustChargeEntity = Optional.of(ticketAdjustChargeEntity1);
        when(ticketAdjustChargeServiceImplUnderTest.ticketAdjustChargeRepositoryJPA.findById(0L)).thenReturn(ticketAdjustChargeEntity);

        // Configure TicketAdjustChargeRepositoryJPA.save(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity2 = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity2.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity2.setTicketId(0L);
        ticketAdjustChargeEntity2.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity2.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity2.setPayType("payType");
        ticketAdjustChargeEntity2.setContractNo("contractNo");
        ticketAdjustChargeEntity2.setAccountType("accountType");
        ticketAdjustChargeEntity2.setAdjustAmount(0L);
        ticketAdjustChargeEntity2.setReason("reason");
        ticketAdjustChargeEntity2.setAdjustContent("adjustContent");
        when(ticketAdjustChargeServiceImplUnderTest.ticketAdjustChargeRepositoryJPA.save(new TicketAdjustChargeEntity())).thenReturn(ticketAdjustChargeEntity2);

        final TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicketId(0L);
        ticketEntity.setContractId(1L);
        when(mockTicketServiceJPA.getOne(itemParamsEntity.getTicketId())).thenReturn(ticketEntity);

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
        when(ticketAdjustChargeServiceImplUnderTest.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Configure ActionAuditService.saveActAudit(...).
        final ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
        actionAuditEntity.setActionAuditId(0L);
        actionAuditEntity.setActTypeId(0L);
        actionAuditEntity.setContractId(0L);
        actionAuditEntity.setTicketId(1L);
        actionAuditEntity.setTicketAssignId(0L);
        actionAuditEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity.setActionUserFullName("actionUserFullName");
        actionAuditEntity.setActionUserName("actionUserName");
        actionAuditEntity.setAppId("appId");
        actionAuditEntity.setIpPc("ipPc");
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        actionAuditDTO.setTicketId(ticketEntity.getTicketId());
        when(ticketAdjustChargeServiceImplUnderTest.actionAuditService.saveActAudit(null, actionAuditDTO)).thenReturn(actionAuditEntity);

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
        when(ticketAdjustChargeServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

        // Run the test
//        final Object result = ticketAdjustChargeServiceImplUnderTest.saveTicketAdjustCharge(itemParamsEntity, authentication);
        // Verify the results

    }
}
