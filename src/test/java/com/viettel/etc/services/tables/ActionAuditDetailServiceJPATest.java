package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.ActionAuditDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ActionAuditDetailServiceJPATest {

    private ActionAuditDetailServiceJPA actionAuditDetailServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        actionAuditDetailServiceJPAUnderTest = new ActionAuditDetailServiceJPA();
        actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA = mock(ActionAuditDetailRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
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
        final List<ActionAuditDetailEntity> expectedResult = Arrays.asList(actionAuditDetailEntity);

        // Configure ActionAuditDetailRepositoryJPA.findAll(...).
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
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.findAll()).thenReturn(actionAuditDetailEntities);

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_ActionAuditDetailRepositoryJPAReturnsNoItems() {
        // Setup
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
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

        final ActionAuditDetailEntity expectedResult = new ActionAuditDetailEntity();
        expectedResult.setActionAuditDetailId(0L);
        expectedResult.setActionAuditId(0L);
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setTableName("tableName");
        expectedResult.setPkId(0L);
        expectedResult.setColumnName("columnName");
        expectedResult.setOldValue("oldValue");
        expectedResult.setNewValue("newValue");
        expectedResult.setActionName("actionName");

        // Configure ActionAuditDetailRepositoryJPA.save(...).
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
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.save(new ActionAuditDetailEntity())).thenReturn(actionAuditDetailEntity1);

        // Run the test
        final ActionAuditDetailEntity result = actionAuditDetailServiceJPAUnderTest.save(actionAuditDetailEntity);

        // Verify the results
    }

    @Test
    void testSaveAll() {
        // Setup
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
        final Iterable<ActionAuditDetailEntity> actionAuditDetailList = Arrays.asList(actionAuditDetailEntity);
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
        final List<ActionAuditDetailEntity> expectedResult = Arrays.asList(actionAuditDetailEntity1);

        // Configure ActionAuditDetailRepositoryJPA.saveAll(...).
        final ActionAuditDetailEntity actionAuditDetailEntity2 = new ActionAuditDetailEntity();
        actionAuditDetailEntity2.setActionAuditDetailId(0L);
        actionAuditDetailEntity2.setActionAuditId(0L);
        actionAuditDetailEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailEntity2.setTableName("tableName");
        actionAuditDetailEntity2.setPkId(0L);
        actionAuditDetailEntity2.setColumnName("columnName");
        actionAuditDetailEntity2.setOldValue("oldValue");
        actionAuditDetailEntity2.setNewValue("newValue");
        actionAuditDetailEntity2.setActionName("actionName");
        final List<ActionAuditDetailEntity> actionAuditDetailEntities = Arrays.asList(actionAuditDetailEntity2);
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.saveAll(any(Iterable.class))).thenReturn(actionAuditDetailEntities);

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.saveAll(actionAuditDetailList);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSaveAll_ActionAuditDetailRepositoryJPAReturnsNoItems() {
        // Setup
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
        final Iterable<ActionAuditDetailEntity> actionAuditDetailList = Arrays.asList(actionAuditDetailEntity);
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.saveAll(any(Iterable.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.saveAll(actionAuditDetailList);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindById() {
        // Setup
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
        final Optional<ActionAuditDetailEntity> expectedResult = Optional.of(actionAuditDetailEntity);

        // Configure ActionAuditDetailRepositoryJPA.findById(...).
        final ActionAuditDetailEntity actionAuditDetailEntity2 = new ActionAuditDetailEntity();
        actionAuditDetailEntity2.setActionAuditDetailId(0L);
        actionAuditDetailEntity2.setActionAuditId(0L);
        actionAuditDetailEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailEntity2.setTableName("tableName");
        actionAuditDetailEntity2.setPkId(0L);
        actionAuditDetailEntity2.setColumnName("columnName");
        actionAuditDetailEntity2.setOldValue("oldValue");
        actionAuditDetailEntity2.setNewValue("newValue");
        actionAuditDetailEntity2.setActionName("actionName");
        final Optional<ActionAuditDetailEntity> actionAuditDetailEntity1 = Optional.of(actionAuditDetailEntity2);
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.findById(0L)).thenReturn(actionAuditDetailEntity1);

        // Run the test
        final Optional<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ActionAuditDetailRepositoryJPAReturnsAbsent() {
        // Setup
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        actionAuditDetailServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
        final ActionAuditDetailEntity expectedResult = new ActionAuditDetailEntity();
        expectedResult.setActionAuditDetailId(0L);
        expectedResult.setActionAuditId(0L);
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setTableName("tableName");
        expectedResult.setPkId(0L);
        expectedResult.setColumnName("columnName");
        expectedResult.setOldValue("oldValue");
        expectedResult.setNewValue("newValue");
        expectedResult.setActionName("actionName");

        // Configure ActionAuditDetailRepositoryJPA.getOne(...).
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
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.getOne(0L)).thenReturn(actionAuditDetailEntity);

        // Run the test
        final ActionAuditDetailEntity result = actionAuditDetailServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = actionAuditDetailServiceJPAUnderTest.existsById(0L);
        assertThat(result).isFalse();
        // Verify the results
    }

    @Test
    void testFindAllByActionAuditId() {
        // Setup
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
        final List<ActionAuditDetailEntity> expectedResult = Arrays.asList(actionAuditDetailEntity);

        // Configure ActionAuditDetailRepositoryJPA.findAllByActionAuditId(...).
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
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.findAllByActionAuditId(0L)).thenReturn(actionAuditDetailEntities);

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.findAllByActionAuditId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllByActionAuditId_ActionAuditDetailRepositoryJPAReturnsNoItems() {
        // Setup
        when(actionAuditDetailServiceJPAUnderTest.actionAuditDetailRepositoryJPA.findAllByActionAuditId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<ActionAuditDetailEntity> result = actionAuditDetailServiceJPAUnderTest.findAllByActionAuditId(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
