package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.repositories.ActionAuditRepository;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import com.viettel.etc.services.tables.ActionAuditDetailServiceJPA;
import com.viettel.etc.services.tables.ActionAuditServiceJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActionAuditServiceImplTest {

    private ActionAuditServiceImpl actionAuditServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actionAuditServiceImplUnderTest = new ActionAuditServiceImpl();
        actionAuditServiceImplUnderTest.actionAuditServiceJPA = mock(ActionAuditServiceJPA.class);
        actionAuditServiceImplUnderTest.actionAuditDetailServiceJPA = mock(ActionAuditDetailServiceJPA.class);
        actionAuditServiceImplUnderTest.actionAuditRepository = mock(ActionAuditRepository.class);
    }

    @Test
    void testSaveActAudit() {
        // Setup
        final Authentication authentication = null;
        final ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActionAuditId(0L);
        actionAuditDTO.setActTypeId(0L);
        actionAuditDTO.setContractId(0L);
        actionAuditDTO.setTicketId(0L);
        actionAuditDTO.setTicketAssignId(0L);
        actionAuditDTO.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDTO.setActionUserFullName("actionUserFullName");
        actionAuditDTO.setActionUserName("actionUserName");
        actionAuditDTO.setAppId("appId");
        actionAuditDTO.setIpPc("ipPc");

        final ActionAuditEntity expectedResult = new ActionAuditEntity();
        expectedResult.setActionAuditId(0L);
        expectedResult.setActTypeId(0L);
        expectedResult.setContractId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setTicketAssignId(0L);
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setActionUserFullName("actionUserFullName");
        expectedResult.setActionUserName("actionUserName");
        expectedResult.setAppId("appId");
        expectedResult.setIpPc("ipPc");

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
        actionAuditEntity.setIpPc("ipPc");
        when(actionAuditServiceImplUnderTest.actionAuditServiceJPA.save(any())).thenReturn(actionAuditEntity);

        // Run the test
        final ActionAuditEntity result = actionAuditServiceImplUnderTest.saveActAudit(authentication, actionAuditDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSaveActAuditDetail() {
        // Setup
        final List<ActionAuditDetailEntity> expectedResult = new ArrayList<>();

        // Configure ActionAuditDetailServiceJPA.saveAll(...).
        final ActionAuditDetailEntity actionAuditDetailEntity1 = new ActionAuditDetailEntity();
        actionAuditDetailEntity1.setActionAuditDetailId(0L);
        actionAuditDetailEntity1.setActionAuditId(0L);
        actionAuditDetailEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailEntity1.setTableName("tableName");
        actionAuditDetailEntity1.setPkId(0L);
        actionAuditDetailEntity1.setColumnName("columnName");
        actionAuditDetailEntity1.setOldValue("oldValue");
        actionAuditDetailEntity1.setNewValue("newValue");
        actionAuditDetailEntity1.setActionName("actionName");
        final List<ActionAuditDetailEntity> actionAuditDetailEntities = Arrays.asList(actionAuditDetailEntity1);
        when(actionAuditServiceImplUnderTest.actionAuditDetailServiceJPA.saveAll(anyList())).thenReturn(actionAuditDetailEntities);

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditServiceImplUnderTest.saveActAuditDetail(0L, "oldEntity", "newEntity", 0L, "actionName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSaveActAuditDetail_ActionAuditDetailServiceJPAReturnsNoItems() {
        // Setup
        when(actionAuditServiceImplUnderTest.actionAuditDetailServiceJPA.saveAll(Arrays.asList(new ActionAuditDetailEntity()))).thenReturn(Collections.emptyList());

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditServiceImplUnderTest.saveActAuditDetail(0L, "oldEntity", "newEntity", 0L, "actionName");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetActionAudit() {
        // Setup
        final ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActionAuditId(0L);
        actionAuditDTO.setActTypeId(0L);
        actionAuditDTO.setContractId(0L);
        actionAuditDTO.setTicketId(0L);
        actionAuditDTO.setTicketAssignId(0L);
        actionAuditDTO.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDTO.setActionUserFullName("actionUserFullName");
        actionAuditDTO.setActionUserName("actionUserName");
        actionAuditDTO.setAppId("appId");
        actionAuditDTO.setIpPc("ipPc");

        when(actionAuditServiceImplUnderTest.actionAuditRepository.getActionAudit(new ActionAuditDTO())).thenReturn("result");

        // Run the test
        final Object result = actionAuditServiceImplUnderTest.getActionAudit(actionAuditDTO, null);

        // Verify the results
    }

    @Test
    void testSaveActAuditAndActAuditDetail() {
        // Setup
        final Authentication authentication = null;
        final ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActionAuditId(0L);
        actionAuditDTO.setActTypeId(0L);
        actionAuditDTO.setContractId(0L);
        actionAuditDTO.setTicketId(0L);
        actionAuditDTO.setTicketAssignId(0L);
        actionAuditDTO.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDTO.setActionUserFullName("actionUserFullName");
        actionAuditDTO.setActionUserName("actionUserName");
        actionAuditDTO.setAppId("appId");
        actionAuditDTO.setIpPc("ipPc");

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
        actionAuditEntity.setIpPc("ipPc");
        when(actionAuditServiceImplUnderTest.actionAuditServiceJPA.save(any())).thenReturn(actionAuditEntity);

        // Configure ActionAuditDetailServiceJPA.saveAll(...).
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
        when(actionAuditServiceImplUnderTest.actionAuditDetailServiceJPA.saveAll(Arrays.asList(new ActionAuditDetailEntity()))).thenReturn(actionAuditDetailEntities);

        // Run the test
        actionAuditServiceImplUnderTest.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, actionAuditEntity, actionAuditDetailEntity, 0L, "actionName");

        // Verify the results
    }

    @Test
    void testSaveActAuditAndActAuditDetail_ActionAuditDetailServiceJPAReturnsNoItems() {
        // Setup
        final Authentication authentication = null;
        final ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActionAuditId(0L);
        actionAuditDTO.setActTypeId(0L);
        actionAuditDTO.setContractId(0L);
        actionAuditDTO.setTicketId(0L);
        actionAuditDTO.setTicketAssignId(0L);
        actionAuditDTO.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDTO.setActionUserFullName("actionUserFullName");
        actionAuditDTO.setActionUserName("actionUserName");
        actionAuditDTO.setAppId("appId");
        actionAuditDTO.setIpPc("ipPc");

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
        actionAuditEntity.setIpPc("ipPc");
        when(actionAuditServiceImplUnderTest.actionAuditServiceJPA.save(any())).thenReturn(actionAuditEntity);

        when(actionAuditServiceImplUnderTest.actionAuditDetailServiceJPA.saveAll(Arrays.asList(new ActionAuditDetailEntity()))).thenReturn(Collections.emptyList());

        // Run the test
        actionAuditServiceImplUnderTest.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, "oldEntity", "newEntity", 0L, "actionName");

        // Verify the results
    }
}
