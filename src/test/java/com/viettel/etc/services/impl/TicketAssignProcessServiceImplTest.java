package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.TicketAssignProcessDTO;
import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.repositories.TicketAssignProcessRepository;
import com.viettel.etc.repositories.tables.TicketAssignProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketAssignRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketAssignProcessServiceImplTest {

    private TicketAssignProcessServiceImpl ticketAssignProcessServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignProcessServiceImplUnderTest = new TicketAssignProcessServiceImpl();
        ticketAssignProcessServiceImplUnderTest.ticketAssignProcessRepository = mock(TicketAssignProcessRepository.class);
        ticketAssignProcessServiceImplUnderTest.ticketAssignProcessRepositoryJPA = mock(TicketAssignProcessRepositoryJPA.class);
        ticketAssignProcessServiceImplUnderTest.ticketAssignRepositoryJPA = mock(TicketAssignRepositoryJPA.class);
        ticketAssignProcessServiceImplUnderTest.ticketAttachmentService = mock(TicketAttachmentService.class);
        ticketAssignProcessServiceImplUnderTest.actionAuditService = mock(ActionAuditService.class);
        ticketAssignProcessServiceImplUnderTest.ticketServiceJPA = mock(TicketServiceJPA.class);
    }

    @Test
    void testGetTicketAssignProcess() {
        // Setup
        final TicketStatusDTO itemParamsEntity = new TicketStatusDTO();
        itemParamsEntity.setTicketStatusId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setTicketStatus(0L);
        itemParamsEntity.setProcessTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setNote("note");
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Authentication authentication = null;

        // Configure TicketAssignProcessRepository.getTicketAssignProcess(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketAssignProcessServiceImplUnderTest.ticketAssignProcessRepository.getTicketAssignProcess(new TicketStatusDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketAssignProcessServiceImplUnderTest.getTicketAssignProcess(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testSaveTicketAssignProcess() {
        // Setup
        final TicketAssignProcessDTO itemParamsEntity = new TicketAssignProcessDTO();
        itemParamsEntity.setTicketAssignProcessId(0L);
        itemParamsEntity.setTicketAssignId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        itemParamsEntity.setProcessResult("processResult");
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStaffName("staffName");
        itemParamsEntity.setCreateUser("createUser");

        final Authentication authentication = null;

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
        when(ticketAssignProcessServiceImplUnderTest.ticketAssignProcessRepositoryJPA.save(new TicketAssignProcessEntity())).thenReturn(ticketAssignProcessEntity);

        // Configure TicketServiceJPA.getOne(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketAssignProcessServiceImplUnderTest.ticketServiceJPA.getOne(0L)).thenReturn(ticketEntity);

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
        when(ticketAssignProcessServiceImplUnderTest.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

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
        when(ticketAssignProcessServiceImplUnderTest.actionAuditService.saveActAuditDetail(eq(0L), any(Object.class), eq(new TicketAssignProcessEntity()), eq(0L), eq("actionName"))).thenReturn(actionAuditDetailEntities);

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
        when(ticketAssignProcessServiceImplUnderTest.ticketAssignRepositoryJPA.findById(0L)).thenReturn(ticketAssignEntity);

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
        when(ticketAssignProcessServiceImplUnderTest.ticketAssignRepositoryJPA.save(new TicketAssignEntity())).thenReturn(ticketAssignEntity2);

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
        when(ticketAssignProcessServiceImplUnderTest.ticketAttachmentService.saveTicketAttachment(Arrays.asList(new FileDTO()), 0L, null, 0L, 0L, 0L, "actionName")).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAssignProcessServiceImplUnderTest.saveTicketAssignProcess(itemParamsEntity, authentication);

        // Verify the results
    }
}
