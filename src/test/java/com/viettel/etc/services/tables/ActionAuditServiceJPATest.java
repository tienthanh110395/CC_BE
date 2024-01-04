package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.ActionAuditRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ActionAuditServiceJPATest {

    private ActionAuditServiceJPA actionAuditServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        actionAuditServiceJPAUnderTest = new ActionAuditServiceJPA();
        actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA = mock(ActionAuditRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
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
        final List<ActionAuditEntity> expectedResult = Arrays.asList(actionAuditEntity);

        // Configure ActionAuditRepositoryJPA.findAll(...).
        final ActionAuditEntity actionAuditEntity1 = new ActionAuditEntity();
        actionAuditEntity1.setActionAuditId(0L);
        actionAuditEntity1.setActTypeId(0L);
        actionAuditEntity1.setContractId(0L);
        actionAuditEntity1.setTicketId(0L);
        actionAuditEntity1.setTicketAssignId(0L);
        actionAuditEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity1.setActionUserFullName("actionUserFullName");
        actionAuditEntity1.setActionUserName("actionUserName");
        actionAuditEntity1.setAppId("appId");
        actionAuditEntity1.setIpPc("ipPc");
        final List<ActionAuditEntity> actionAuditEntities = Arrays.asList(actionAuditEntity1);
        when(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA.findAll()).thenReturn(actionAuditEntities);

        // Run the test
        final List<ActionAuditEntity> result = actionAuditServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_ActionAuditRepositoryJPAReturnsNoItems() {
        // Setup
        when(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ActionAuditEntity> result = actionAuditServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
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

        // Configure ActionAuditRepositoryJPA.save(...).
        final ActionAuditEntity actionAuditEntity1 = new ActionAuditEntity();
        actionAuditEntity1.setActionAuditId(0L);
        actionAuditEntity1.setActTypeId(0L);
        actionAuditEntity1.setContractId(0L);
        actionAuditEntity1.setTicketId(0L);
        actionAuditEntity1.setTicketAssignId(0L);
        actionAuditEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity1.setActionUserFullName("actionUserFullName");
        actionAuditEntity1.setActionUserName("actionUserName");
        actionAuditEntity1.setAppId("appId");
        actionAuditEntity1.setIpPc("ipPc");
        when(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA.save(new ActionAuditEntity())).thenReturn(actionAuditEntity1);

        // Run the test
        final ActionAuditEntity result = actionAuditServiceJPAUnderTest.save(actionAuditEntity);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
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
        final Optional<ActionAuditEntity> expectedResult = Optional.of(actionAuditEntity);

        // Configure ActionAuditRepositoryJPA.findById(...).
        final ActionAuditEntity actionAuditEntity2 = new ActionAuditEntity();
        actionAuditEntity2.setActionAuditId(0L);
        actionAuditEntity2.setActTypeId(0L);
        actionAuditEntity2.setContractId(0L);
        actionAuditEntity2.setTicketId(0L);
        actionAuditEntity2.setTicketAssignId(0L);
        actionAuditEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity2.setActionUserFullName("actionUserFullName");
        actionAuditEntity2.setActionUserName("actionUserName");
        actionAuditEntity2.setAppId("appId");
        actionAuditEntity2.setIpPc("ipPc");
        final Optional<ActionAuditEntity> actionAuditEntity1 = Optional.of(actionAuditEntity2);
        when(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA.findById(0L)).thenReturn(actionAuditEntity1);

        // Run the test
        final Optional<ActionAuditEntity> result = actionAuditServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ActionAuditRepositoryJPAReturnsAbsent() {
        // Setup
        when(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<ActionAuditEntity> result = actionAuditServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        actionAuditServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
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

        // Configure ActionAuditRepositoryJPA.getOne(...).
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
        when(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA.getOne(0L)).thenReturn(actionAuditEntity);

        // Run the test
        final ActionAuditEntity result = actionAuditServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(actionAuditServiceJPAUnderTest.actionAuditRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = actionAuditServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
