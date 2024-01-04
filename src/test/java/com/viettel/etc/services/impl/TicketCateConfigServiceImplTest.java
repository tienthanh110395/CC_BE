package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.repositories.TicketCateConfigRepository;
import com.viettel.etc.repositories.tables.TicketTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.tables.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TicketCateConfigServiceImplTest {

    private TicketCateConfigServiceImpl ticketCateConfigServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketCateConfigServiceImplUnderTest = new TicketCateConfigServiceImpl();
        ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository = mock(TicketCateConfigRepository.class);
        ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA = mock(TicketTypeServiceJPA.class);
        ticketCateConfigServiceImplUnderTest.ticketTypeRepositoryJPA = mock(TicketTypeRepositoryJPA.class);
        ticketCateConfigServiceImplUnderTest.ticketCateConfigServiceJPA = mock(TicketCateConfigServiceJPA.class);
        ticketCateConfigServiceImplUnderTest.ticketServiceJPA = mock(TicketServiceJPA.class);
        ticketCateConfigServiceImplUnderTest.ticketSlaServiceJPA = mock(TicketSlaServiceJPA.class);
        ticketCateConfigServiceImplUnderTest.actionAuditService = mock(ActionAuditService.class);
        ticketCateConfigServiceImplUnderTest.actionAuditServiceJPA = mock(ActionAuditServiceJPA.class);
    }

    @Test
    void testCreateOrUpdate() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        final Authentication authentication = null;

        // Configure TicketTypeServiceJPA.findByCodeAndLevelTt(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode(" ");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("actionUserName");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setIsCPT(0L);
        ticketTypeEntity.setType(0L);
        ticketTypeEntity.setTicketTemplate("ticketTemplate");
        ticketTypeEntity.setDeadTimeType(0L);
        ticketTypeEntity.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndLevelTt(" ", 0L))
                .thenReturn(ticketTypeEntity);

        // Configure TicketTypeServiceJPA.findByCodeAndTicketTypeIdNot(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode(" ");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("actionUserName");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setIsCPT(0L);
        ticketTypeEntity1.setType(0L);
        ticketTypeEntity1.setTicketTemplate("ticketTemplate");
        ticketTypeEntity1.setDeadTimeType(0L);
        ticketTypeEntity1.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndTicketTypeIdNot(" ",
                0L)).thenReturn(ticketTypeEntity1);

        // Configure TicketTypeServiceJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity3 = new TicketTypeEntity();
        ticketTypeEntity3.setTicketTypeId(0L);
        ticketTypeEntity3.setName("name");
        ticketTypeEntity3.setCode(" ");
        ticketTypeEntity3.setDescription("description");
        ticketTypeEntity3.setParentId(0L);
        ticketTypeEntity3.setStatus(0L);
        ticketTypeEntity3.setCreateUser("actionUserName");
        ticketTypeEntity3.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setUpdateUser("updateUser");
        ticketTypeEntity3.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setIsCPT(0L);
        ticketTypeEntity3.setType(0L);
        ticketTypeEntity3.setTicketTemplate("ticketTemplate");
        ticketTypeEntity3.setDeadTimeType(0L);
        ticketTypeEntity3.setLevelTt(0L);
        final Optional<TicketTypeEntity> ticketTypeEntity2 = Optional.of(ticketTypeEntity3);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(ticketTypeEntity2);

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity4 = new TicketTypeEntity();
        ticketTypeEntity4.setTicketTypeId(0L);
        ticketTypeEntity4.setName("name");
        ticketTypeEntity4.setCode(" ");
        ticketTypeEntity4.setDescription("description");
        ticketTypeEntity4.setParentId(0L);
        ticketTypeEntity4.setStatus(0L);
        ticketTypeEntity4.setCreateUser("actionUserName");
        ticketTypeEntity4.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity4.setUpdateUser("updateUser");
        ticketTypeEntity4.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity4.setIsCPT(0L);
        ticketTypeEntity4.setType(0L);
        ticketTypeEntity4.setTicketTemplate("ticketTemplate");
        ticketTypeEntity4.setDeadTimeType(0L);
        ticketTypeEntity4.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.save(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity4);

        // Configure TicketTypeServiceJPA.saveAndFlush(...).
        final TicketTypeEntity ticketTypeEntity5 = new TicketTypeEntity();
        ticketTypeEntity5.setTicketTypeId(0L);
        ticketTypeEntity5.setName("name");
        ticketTypeEntity5.setCode(" ");
        ticketTypeEntity5.setDescription("description");
        ticketTypeEntity5.setParentId(0L);
        ticketTypeEntity5.setStatus(0L);
        ticketTypeEntity5.setCreateUser("actionUserName");
        ticketTypeEntity5.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity5.setUpdateUser("updateUser");
        ticketTypeEntity5.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity5.setIsCPT(0L);
        ticketTypeEntity5.setType(0L);
        ticketTypeEntity5.setTicketTemplate("ticketTemplate");
        ticketTypeEntity5.setDeadTimeType(0L);
        ticketTypeEntity5.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.saveAndFlush(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity5);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.createOrUpdate(params, authentication);

    }

    @Test
    void testCreateOrUpdate_TicketTypeServiceJPAFindByCodeAndLevelTtReturnsNull() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        final Authentication authentication = null;
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndLevelTt(" ", 0L)).thenReturn(null);

        // Configure TicketTypeServiceJPA.findByCodeAndTicketTypeIdNot(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode(" ");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("actionUserName");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setIsCPT(0L);
        ticketTypeEntity.setType(0L);
        ticketTypeEntity.setTicketTemplate("ticketTemplate");
        ticketTypeEntity.setDeadTimeType(0L);
        ticketTypeEntity.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndTicketTypeIdNot(" ",
                0L)).thenReturn(ticketTypeEntity);

        // Configure TicketTypeServiceJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity2 = new TicketTypeEntity();
        ticketTypeEntity2.setTicketTypeId(0L);
        ticketTypeEntity2.setName("name");
        ticketTypeEntity2.setCode(" ");
        ticketTypeEntity2.setDescription("description");
        ticketTypeEntity2.setParentId(0L);
        ticketTypeEntity2.setStatus(0L);
        ticketTypeEntity2.setCreateUser("actionUserName");
        ticketTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setUpdateUser("updateUser");
        ticketTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setIsCPT(0L);
        ticketTypeEntity2.setType(0L);
        ticketTypeEntity2.setTicketTemplate("ticketTemplate");
        ticketTypeEntity2.setDeadTimeType(0L);
        ticketTypeEntity2.setLevelTt(0L);
        final Optional<TicketTypeEntity> ticketTypeEntity1 = Optional.of(ticketTypeEntity2);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(ticketTypeEntity1);

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity3 = new TicketTypeEntity();
        ticketTypeEntity3.setTicketTypeId(0L);
        ticketTypeEntity3.setName("name");
        ticketTypeEntity3.setCode(" ");
        ticketTypeEntity3.setDescription("description");
        ticketTypeEntity3.setParentId(0L);
        ticketTypeEntity3.setStatus(0L);
        ticketTypeEntity3.setCreateUser("actionUserName");
        ticketTypeEntity3.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setUpdateUser("updateUser");
        ticketTypeEntity3.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setIsCPT(0L);
        ticketTypeEntity3.setType(0L);
        ticketTypeEntity3.setTicketTemplate("ticketTemplate");
        ticketTypeEntity3.setDeadTimeType(0L);
        ticketTypeEntity3.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.save(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity3);

        // Configure TicketTypeServiceJPA.saveAndFlush(...).
        final TicketTypeEntity ticketTypeEntity4 = new TicketTypeEntity();
        ticketTypeEntity4.setTicketTypeId(0L);
        ticketTypeEntity4.setName("name");
        ticketTypeEntity4.setCode(" ");
        ticketTypeEntity4.setDescription("description");
        ticketTypeEntity4.setParentId(0L);
        ticketTypeEntity4.setStatus(0L);
        ticketTypeEntity4.setCreateUser("actionUserName");
        ticketTypeEntity4.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity4.setUpdateUser("updateUser");
        ticketTypeEntity4.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity4.setIsCPT(0L);
        ticketTypeEntity4.setType(0L);
        ticketTypeEntity4.setTicketTemplate("ticketTemplate");
        ticketTypeEntity4.setDeadTimeType(0L);
        ticketTypeEntity4.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.saveAndFlush(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity4);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.createOrUpdate(params, authentication);

        // Verify the results
    }

    @Test
    void testCreateOrUpdate_TicketTypeServiceJPAFindByCodeAndTicketTypeIdNotReturnsNull() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        final Authentication authentication = null;

        // Configure TicketTypeServiceJPA.findByCodeAndLevelTt(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode(" ");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("actionUserName");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setIsCPT(0L);
        ticketTypeEntity.setType(0L);
        ticketTypeEntity.setTicketTemplate("ticketTemplate");
        ticketTypeEntity.setDeadTimeType(0L);
        ticketTypeEntity.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndLevelTt(" ", 0L))
                .thenReturn(ticketTypeEntity);

        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndTicketTypeIdNot(" ",
                0L)).thenReturn(null);

        // Configure TicketTypeServiceJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity2 = new TicketTypeEntity();
        ticketTypeEntity2.setTicketTypeId(0L);
        ticketTypeEntity2.setName("name");
        ticketTypeEntity2.setCode(" ");
        ticketTypeEntity2.setDescription("description");
        ticketTypeEntity2.setParentId(0L);
        ticketTypeEntity2.setStatus(0L);
        ticketTypeEntity2.setCreateUser("actionUserName");
        ticketTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setUpdateUser("updateUser");
        ticketTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setIsCPT(0L);
        ticketTypeEntity2.setType(0L);
        ticketTypeEntity2.setTicketTemplate("ticketTemplate");
        ticketTypeEntity2.setDeadTimeType(0L);
        ticketTypeEntity2.setLevelTt(0L);
        final Optional<TicketTypeEntity> ticketTypeEntity1 = Optional.of(ticketTypeEntity2);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(ticketTypeEntity1);

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity3 = new TicketTypeEntity();
        ticketTypeEntity3.setTicketTypeId(0L);
        ticketTypeEntity3.setName("name");
        ticketTypeEntity3.setCode(" ");
        ticketTypeEntity3.setDescription("description");
        ticketTypeEntity3.setParentId(0L);
        ticketTypeEntity3.setStatus(0L);
        ticketTypeEntity3.setCreateUser("actionUserName");
        ticketTypeEntity3.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setUpdateUser("updateUser");
        ticketTypeEntity3.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setIsCPT(0L);
        ticketTypeEntity3.setType(0L);
        ticketTypeEntity3.setTicketTemplate("ticketTemplate");
        ticketTypeEntity3.setDeadTimeType(0L);
        ticketTypeEntity3.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.save(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity3);

        // Configure TicketTypeServiceJPA.saveAndFlush(...).
        final TicketTypeEntity ticketTypeEntity4 = new TicketTypeEntity();
        ticketTypeEntity4.setTicketTypeId(0L);
        ticketTypeEntity4.setName("name");
        ticketTypeEntity4.setCode(" ");
        ticketTypeEntity4.setDescription("description");
        ticketTypeEntity4.setParentId(0L);
        ticketTypeEntity4.setStatus(0L);
        ticketTypeEntity4.setCreateUser("actionUserName");
        ticketTypeEntity4.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity4.setUpdateUser("updateUser");
        ticketTypeEntity4.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity4.setIsCPT(0L);
        ticketTypeEntity4.setType(0L);
        ticketTypeEntity4.setTicketTemplate("ticketTemplate");
        ticketTypeEntity4.setDeadTimeType(0L);
        ticketTypeEntity4.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.saveAndFlush(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity4);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.createOrUpdate(params, authentication);
    }

    @Test
    void testCreateOrUpdate_TicketTypeServiceJPAFindByIdReturnsAbsent() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        final Authentication authentication = null;

        // Configure TicketTypeServiceJPA.findByCodeAndLevelTt(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode(" ");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("actionUserName");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setIsCPT(0L);
        ticketTypeEntity.setType(0L);
        ticketTypeEntity.setTicketTemplate("ticketTemplate");
        ticketTypeEntity.setDeadTimeType(0L);
        ticketTypeEntity.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndLevelTt(" ", 0L))
                .thenReturn(ticketTypeEntity);

        // Configure TicketTypeServiceJPA.findByCodeAndTicketTypeIdNot(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode(" ");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("actionUserName");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setIsCPT(0L);
        ticketTypeEntity1.setType(0L);
        ticketTypeEntity1.setTicketTemplate("ticketTemplate");
        ticketTypeEntity1.setDeadTimeType(0L);
        ticketTypeEntity1.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findByCodeAndTicketTypeIdNot(" ",
                0L)).thenReturn(ticketTypeEntity1);

        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(Optional.empty());

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity2 = new TicketTypeEntity();
        ticketTypeEntity2.setTicketTypeId(0L);
        ticketTypeEntity2.setName("name");
        ticketTypeEntity2.setCode(" ");
        ticketTypeEntity2.setDescription("description");
        ticketTypeEntity2.setParentId(0L);
        ticketTypeEntity2.setStatus(0L);
        ticketTypeEntity2.setCreateUser("actionUserName");
        ticketTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setUpdateUser("updateUser");
        ticketTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setIsCPT(0L);
        ticketTypeEntity2.setType(0L);
        ticketTypeEntity2.setTicketTemplate("ticketTemplate");
        ticketTypeEntity2.setDeadTimeType(0L);
        ticketTypeEntity2.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.save(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity2);

        // Configure TicketTypeServiceJPA.saveAndFlush(...).
        final TicketTypeEntity ticketTypeEntity3 = new TicketTypeEntity();
        ticketTypeEntity3.setTicketTypeId(0L);
        ticketTypeEntity3.setName("name");
        ticketTypeEntity3.setCode(" ");
        ticketTypeEntity3.setDescription("description");
        ticketTypeEntity3.setParentId(0L);
        ticketTypeEntity3.setStatus(0L);
        ticketTypeEntity3.setCreateUser("actionUserName");
        ticketTypeEntity3.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setUpdateUser("updateUser");
        ticketTypeEntity3.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity3.setIsCPT(0L);
        ticketTypeEntity3.setType(0L);
        ticketTypeEntity3.setTicketTemplate("ticketTemplate");
        ticketTypeEntity3.setDeadTimeType(0L);
        ticketTypeEntity3.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.saveAndFlush(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity3);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.createOrUpdate(params, authentication);

        // Verify the results
    }

    @Test
    void testOnDelete() {
        // Setup
        final Authentication authentication = null;

        // Configure TicketTypeServiceJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode(" ");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("actionUserName");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setIsCPT(0L);
        ticketTypeEntity1.setType(0L);
        ticketTypeEntity1.setTicketTemplate("ticketTemplate");
        ticketTypeEntity1.setDeadTimeType(0L);
        ticketTypeEntity1.setLevelTt(0L);
        final Optional<TicketTypeEntity> ticketTypeEntity = Optional.of(ticketTypeEntity1);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(ticketTypeEntity);

        // Configure TicketServiceJPA.findByL3TicketTypeId(...).
        final List<TicketEntity> ticketEntities = Arrays.asList(
                new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email",
                        "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo",
                        Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L,
                        Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser",
                        Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)),
                        "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L,
                        "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        when(ticketCateConfigServiceImplUnderTest.ticketServiceJPA.findByL3TicketTypeId(0L)).thenReturn(ticketEntities);

        // Configure TicketSlaServiceJPA.findByTicketTypeId(...).
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSlaEntity.setUpdateUser("updateUser");
        ticketSlaEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSlaEntity.setProcessTime(0L);
        ticketSlaEntity.setProcessTimeType(0L);
        ticketSlaEntity.setCombineTimeL1(0L);
        final List<TicketSlaEntity> ticketSlaEntities = Arrays.asList(ticketSlaEntity);
        when(ticketCateConfigServiceImplUnderTest.ticketSlaServiceJPA.findByTicketTypeId(0L))
                .thenReturn(ticketSlaEntities);

        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.doDelete(0L)).thenReturn(0);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(eq(0L),
                eq(new TicketTypeEntity()), any(Object.class), eq(0L), eq("value")))
                .thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.onDelete(0L, authentication);
    }

    @Test
    void testOnDelete_TicketTypeServiceJPAFindByIdReturnsAbsent() {
        // Setup
        final Authentication authentication = null;
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.onDelete(0L, authentication);

        // Verify the results
    }

    @Test
    void testOnDelete_TicketServiceJPAReturnsNoItems() {
        // Setup
        final Authentication authentication = null;

        // Configure TicketTypeServiceJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode(" ");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("actionUserName");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setIsCPT(0L);
        ticketTypeEntity1.setType(0L);
        ticketTypeEntity1.setTicketTemplate("ticketTemplate");
        ticketTypeEntity1.setDeadTimeType(0L);
        ticketTypeEntity1.setLevelTt(0L);
        final Optional<TicketTypeEntity> ticketTypeEntity = Optional.of(ticketTypeEntity1);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(ticketTypeEntity);

        when(ticketCateConfigServiceImplUnderTest.ticketServiceJPA.findByL3TicketTypeId(0L))
                .thenReturn(Collections.emptyList());

        // Configure TicketSlaServiceJPA.findByTicketTypeId(...).
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSlaEntity.setUpdateUser("updateUser");
        ticketSlaEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSlaEntity.setProcessTime(0L);
        ticketSlaEntity.setProcessTimeType(0L);
        ticketSlaEntity.setCombineTimeL1(0L);
        final List<TicketSlaEntity> ticketSlaEntities = Arrays.asList(ticketSlaEntity);
        when(ticketCateConfigServiceImplUnderTest.ticketSlaServiceJPA.findByTicketTypeId(0L))
                .thenReturn(ticketSlaEntities);

        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.doDelete(0L)).thenReturn(0);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(eq(0L),
                eq(new TicketTypeEntity()), any(Object.class), eq(0L), eq("value")))
                .thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.onDelete(0L, authentication);

        // Verify the results
    }

    @Test
    void testOnDelete_TicketSlaServiceJPAReturnsNoItems() {
        // Setup
        final Authentication authentication = null;

        // Configure TicketTypeServiceJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode(" ");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("actionUserName");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setIsCPT(0L);
        ticketTypeEntity1.setType(0L);
        ticketTypeEntity1.setTicketTemplate("ticketTemplate");
        ticketTypeEntity1.setDeadTimeType(0L);
        ticketTypeEntity1.setLevelTt(0L);
        final Optional<TicketTypeEntity> ticketTypeEntity = Optional.of(ticketTypeEntity1);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(ticketTypeEntity);

        // Configure TicketServiceJPA.findByL3TicketTypeId(...).
        final List<TicketEntity> ticketEntities = Arrays.asList(
                new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email",
                        "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo",
                        Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L,
                        Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser",
                        Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)),
                        "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L,
                        "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        when(ticketCateConfigServiceImplUnderTest.ticketServiceJPA.findByL3TicketTypeId(0L)).thenReturn(ticketEntities);

        when(ticketCateConfigServiceImplUnderTest.ticketSlaServiceJPA.findByTicketTypeId(0L))
                .thenReturn(Collections.emptyList());
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.doDelete(0L)).thenReturn(0);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(eq(0L),
                eq(new TicketTypeEntity()), any(Object.class), eq(0L), eq("value")))
                .thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.onDelete(0L, authentication);
    }

    @Test
    void testGetDataDetail() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getDataDetail(0L,
                new TicketCateConfigDTO())).thenReturn("result");

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.getDataDetail(0L, params);

        // Verify the results
    }

    @Test
    void testChangeStatus() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        // Configure TicketTypeServiceJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode(" ");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("actionUserName");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setIsCPT(0L);
        ticketTypeEntity1.setType(0L);
        ticketTypeEntity1.setTicketTemplate("ticketTemplate");
        ticketTypeEntity1.setDeadTimeType(0L);
        ticketTypeEntity1.setLevelTt(0L);
        final Optional<TicketTypeEntity> ticketTypeEntity = Optional.of(ticketTypeEntity1);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(ticketTypeEntity);

        // Configure TicketTypeServiceJPA.save(...).
        final TicketTypeEntity ticketTypeEntity2 = new TicketTypeEntity();
        ticketTypeEntity2.setTicketTypeId(0L);
        ticketTypeEntity2.setName("name");
        ticketTypeEntity2.setCode(" ");
        ticketTypeEntity2.setDescription("description");
        ticketTypeEntity2.setParentId(0L);
        ticketTypeEntity2.setStatus(0L);
        ticketTypeEntity2.setCreateUser("actionUserName");
        ticketTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setUpdateUser("updateUser");
        ticketTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setIsCPT(0L);
        ticketTypeEntity2.setType(0L);
        ticketTypeEntity2.setTicketTemplate("ticketTemplate");
        ticketTypeEntity2.setDeadTimeType(0L);
        ticketTypeEntity2.setLevelTt(0L);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.save(new TicketTypeEntity()))
                .thenReturn(ticketTypeEntity2);

        // Configure ActionAuditServiceJPA.save(...).
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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditServiceJPA.save(new ActionAuditEntity()))
                .thenReturn(actionAuditEntity);

        // Configure ActionAuditService.saveActAuditDetail(...).
        final ActionAuditDetailEntity actionAuditDetailEntity = new ActionAuditDetailEntity();
        final Authentication authentication = null;
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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.changeStatus(params, authentication);
    }

    @Test
    void testChangeStatus_TicketTypeServiceJPAFindByIdReturnsAbsent() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        final Authentication authentication = null;
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.changeStatus(params, authentication);

        // Verify the results
    }

    @Test
    void testCreateUpdateList() throws Exception {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("name");
        ticketCateConfigDTO.setTicketTypeCode(" ");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setTicketGenre("ticketGenre");
        ticketCateConfigDTO.setTicketGroup("ticketGroup");
        ticketCateConfigDTO.setLevelTt(0L);
        ticketCateConfigDTO.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> params = Arrays.asList(ticketCateConfigDTO);
        final Authentication authentication = null;
        when(ticketCateConfigServiceImplUnderTest.ticketTypeRepositoryJPA.existsByCode(" ")).thenReturn(false);

        // Configure TicketTypeServiceJPA.saveAll(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode(" ");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("actionUserName");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setIsCPT(0L);
        ticketTypeEntity.setType(0L);
        ticketTypeEntity.setTicketTemplate("ticketTemplate");
        ticketTypeEntity.setDeadTimeType(0L);
        ticketTypeEntity.setLevelTt(0L);
        final List<TicketTypeEntity> ticketTypeEntities = Arrays.asList(ticketTypeEntity);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.saveAll(
                Arrays.asList(new TicketTypeEntity()))).thenReturn(ticketTypeEntities);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.createUpdateList(params, authentication);
    }

    @Test
    void testCreateUpdateList_TicketTypeServiceJPAReturnsNoItems() throws Exception {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("name");
        ticketCateConfigDTO.setTicketTypeCode(" ");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setTicketGenre("ticketGenre");
        ticketCateConfigDTO.setTicketGroup("ticketGroup");
        ticketCateConfigDTO.setLevelTt(0L);
        ticketCateConfigDTO.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> params = Arrays.asList(ticketCateConfigDTO);
        final Authentication authentication = null;
        when(ticketCateConfigServiceImplUnderTest.ticketTypeRepositoryJPA.existsByCode(" ")).thenReturn(false);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.saveAll(
                Arrays.asList(new TicketTypeEntity()))).thenReturn(Collections.emptyList());

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.createUpdateList(params, authentication);

        // Verify the results
    }

    @Test
    void testCreateUpdateList_ThrowsException() {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("name");
        ticketCateConfigDTO.setTicketTypeCode(" ");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setTicketGenre("ticketGenre");
        ticketCateConfigDTO.setTicketGroup("ticketGroup");
        ticketCateConfigDTO.setLevelTt(0L);
        ticketCateConfigDTO.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> params = Arrays.asList(ticketCateConfigDTO);
        final Authentication authentication = null;
        when(ticketCateConfigServiceImplUnderTest.ticketTypeRepositoryJPA.existsByCode(" ")).thenReturn(false);

        // Configure TicketTypeServiceJPA.saveAll(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode(" ");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("actionUserName");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setIsCPT(0L);
        ticketTypeEntity.setType(0L);
        ticketTypeEntity.setTicketTemplate("ticketTemplate");
        ticketTypeEntity.setDeadTimeType(0L);
        ticketTypeEntity.setLevelTt(0L);
        final List<TicketTypeEntity> ticketTypeEntities = Arrays.asList(ticketTypeEntity);
        when(ticketCateConfigServiceImplUnderTest.ticketTypeServiceJPA.saveAll(
                Arrays.asList(new TicketTypeEntity()))).thenReturn(ticketTypeEntities);

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
        actionAuditEntity.setIpPc("localhost");
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAudit(null,
                new ActionAuditDTO())).thenReturn(actionAuditEntity);

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
        when(ticketCateConfigServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new TicketTypeEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
//        assertThatThrownBy(
//                () -> ticketCateConfigServiceImplUnderTest.createUpdateList(params, authentication))
//                .isInstanceOf(Exception.class);
    }

    @Test
    void testUpdateStatusMultiple() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        final Authentication authentication = null;
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.updateStatusMultiple(params, authentication);

        // Verify the results
    }

    @Test
    void testSearchTicketType() {
        // Setup
        final Authentication authentication = null;
        final TicketConfigSearchDTO params = new TicketConfigSearchDTO();
        params.setTicketTypeName("ticketTypeName");
        params.setTicketTypeCode("ticketTypeCode");
        params.setInEffect(false);
        params.setExpire(false);
        params.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setCreateUser("createUser");
        params.setLstTicketTypeGroupId(Arrays.asList(0L));
        params.setLstTicketTypeGenreId(Arrays.asList(0L));
        params.setLstTicketCategoryId(Arrays.asList(0L));
        params.setStartRecord(0);
        params.setPageSize(0);
        params.setTicketTypeLevel(0L);
        params.setStatus(Arrays.asList(0L));

        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.searchTicketType(null,
                new TicketConfigSearchDTO())).thenReturn("result");

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.searchTicketType(authentication, params);

        // Verify the results
    }

    @Test
    void testGetTicketTypeByParentId() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("name");
        params.setTicketTypeCode(" ");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setTicketTemplate("ticketTemplate");
        params.setTicketGenre("ticketGenre");
        params.setTicketGroup("ticketGroup");
        params.setLevelTt(0L);
        params.setLstIdsActive(Arrays.asList(0L));
        params.setLstIdsInactive(Arrays.asList(0L));

        // Configure TicketCateConfigRepository.getTicketTypeByParentId(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getTicketTypeByParentId(
                new TicketCateConfigDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketCateConfigServiceImplUnderTest.getTicketTypeByParentId(params, null);

        // Verify the results
    }

    @Test
    void testGetTicketTypeByParentIdForMapping() {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("name");
        ticketCateConfigDTO.setTicketTypeCode(" ");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setTicketGenre("ticketGenre");
        ticketCateConfigDTO.setTicketGroup("ticketGroup");
        ticketCateConfigDTO.setLevelTt(0L);
        ticketCateConfigDTO.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> expectedResult = Arrays.asList(ticketCateConfigDTO);

        // Configure TicketCateConfigRepository.getTicketTypeByParentIdForMapping(...).
        final TicketCateConfigDTO ticketCateConfigDTO1 = new TicketCateConfigDTO();
        ticketCateConfigDTO1.setTicketTypeId(0L);
        ticketCateConfigDTO1.setTicketTypeName("name");
        ticketCateConfigDTO1.setTicketTypeCode(" ");
        ticketCateConfigDTO1.setDescription("description");
        ticketCateConfigDTO1.setParentId(0L);
        ticketCateConfigDTO1.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO1.setStatus(0L);
        ticketCateConfigDTO1.setCreateUser("createUser");
        ticketCateConfigDTO1.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO1.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO1.setTicketGenre("ticketGenre");
        ticketCateConfigDTO1.setTicketGroup("ticketGroup");
        ticketCateConfigDTO1.setLevelTt(0L);
        ticketCateConfigDTO1.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO1.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> ticketCateConfigDTOS = Arrays.asList(ticketCateConfigDTO1);
        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getTicketTypeByParentIdForMapping(
                0L)).thenReturn(ticketCateConfigDTOS);

        // Run the test
        final List<TicketCateConfigDTO> result = ticketCateConfigServiceImplUnderTest.getTicketTypeByParentIdForMapping(
                0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTicketTypeByParentIdForMapping_TicketCateConfigRepositoryReturnsNoItems() {
        // Setup
        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getTicketTypeByParentIdForMapping(
                0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketCateConfigDTO> result = ticketCateConfigServiceImplUnderTest.getTicketTypeByParentIdForMapping(
                0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTicketTypeForMapConfigTime() {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("name");
        ticketCateConfigDTO.setTicketTypeCode(" ");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setTicketGenre("ticketGenre");
        ticketCateConfigDTO.setTicketGroup("ticketGroup");
        ticketCateConfigDTO.setLevelTt(0L);
        ticketCateConfigDTO.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> expectedResult = Arrays.asList(ticketCateConfigDTO);

        // Configure TicketCateConfigRepository.getTicketTypeByParentIdForConfigTime(...).
        final TicketCateConfigDTO ticketCateConfigDTO1 = new TicketCateConfigDTO();
        ticketCateConfigDTO1.setTicketTypeId(0L);
        ticketCateConfigDTO1.setTicketTypeName("name");
        ticketCateConfigDTO1.setTicketTypeCode(" ");
        ticketCateConfigDTO1.setDescription("description");
        ticketCateConfigDTO1.setParentId(0L);
        ticketCateConfigDTO1.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO1.setStatus(0L);
        ticketCateConfigDTO1.setCreateUser("createUser");
        ticketCateConfigDTO1.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO1.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO1.setTicketGenre("ticketGenre");
        ticketCateConfigDTO1.setTicketGroup("ticketGroup");
        ticketCateConfigDTO1.setLevelTt(0L);
        ticketCateConfigDTO1.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO1.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> ticketCateConfigDTOS = Arrays.asList(ticketCateConfigDTO1);
        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getTicketTypeByParentIdForConfigTime(
                0L)).thenReturn(ticketCateConfigDTOS);

        // Run the test
        final List<TicketCateConfigDTO> result = ticketCateConfigServiceImplUnderTest.getTicketTypeForMapConfigTime(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTicketTypeForMapConfigTime_TicketCateConfigRepositoryReturnsNoItems() {
        // Setup
        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getTicketTypeByParentIdForConfigTime(
                0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketCateConfigDTO> result = ticketCateConfigServiceImplUnderTest.getTicketTypeForMapConfigTime(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTicketTypeForConfigTimeDetail() {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("name");
        ticketCateConfigDTO.setTicketTypeCode(" ");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setTicketGenre("ticketGenre");
        ticketCateConfigDTO.setTicketGroup("ticketGroup");
        ticketCateConfigDTO.setLevelTt(0L);
        ticketCateConfigDTO.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> expectedResult = Arrays.asList(ticketCateConfigDTO);

        // Configure TicketCateConfigRepository.getTicketTypeByTicketTypeIdForConfigTime(...).
        final TicketCateConfigDTO ticketCateConfigDTO1 = new TicketCateConfigDTO();
        ticketCateConfigDTO1.setTicketTypeId(0L);
        ticketCateConfigDTO1.setTicketTypeName("name");
        ticketCateConfigDTO1.setTicketTypeCode(" ");
        ticketCateConfigDTO1.setDescription("description");
        ticketCateConfigDTO1.setParentId(0L);
        ticketCateConfigDTO1.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO1.setStatus(0L);
        ticketCateConfigDTO1.setCreateUser("createUser");
        ticketCateConfigDTO1.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO1.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO1.setTicketGenre("ticketGenre");
        ticketCateConfigDTO1.setTicketGroup("ticketGroup");
        ticketCateConfigDTO1.setLevelTt(0L);
        ticketCateConfigDTO1.setLstIdsActive(Arrays.asList(0L));
        ticketCateConfigDTO1.setLstIdsInactive(Arrays.asList(0L));
        final List<TicketCateConfigDTO> ticketCateConfigDTOS = Arrays.asList(ticketCateConfigDTO1);
        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getTicketTypeByTicketTypeIdForConfigTime(
                0L)).thenReturn(ticketCateConfigDTOS);

        // Run the test
        final List<TicketCateConfigDTO> result = ticketCateConfigServiceImplUnderTest.getTicketTypeForConfigTimeDetail(
                0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTicketTypeForConfigTimeDetail_TicketCateConfigRepositoryReturnsNoItems() {
        // Setup
        when(ticketCateConfigServiceImplUnderTest.ticketCateConfigRepository.getTicketTypeByTicketTypeIdForConfigTime(
                0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketCateConfigDTO> result = ticketCateConfigServiceImplUnderTest.getTicketTypeForConfigTimeDetail(
                0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
