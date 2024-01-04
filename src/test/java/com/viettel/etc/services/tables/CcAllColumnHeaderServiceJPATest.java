package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.CcAllColumnHeaderRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.CcAllColumnHeaderEntity;
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

class CcAllColumnHeaderServiceJPATest {

    private CcAllColumnHeaderServiceJPA ccAllColumnHeaderServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        ccAllColumnHeaderServiceJPAUnderTest = new CcAllColumnHeaderServiceJPA();
        ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA = mock(CcAllColumnHeaderRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final CcAllColumnHeaderEntity ccAllColumnHeaderEntity = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntity.setCcAllColumnHeaderId(0L);
        ccAllColumnHeaderEntity.setTableName("tableName");
        ccAllColumnHeaderEntity.setColumnName("columnName");
        ccAllColumnHeaderEntity.setDataType("dataType");
        ccAllColumnHeaderEntity.setStatus(0L);
        ccAllColumnHeaderEntity.setColumnHeader("columnHeader");
        ccAllColumnHeaderEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ccAllColumnHeaderEntity.setUpdateUser("updateUser");
        final List<CcAllColumnHeaderEntity> expectedResult = Arrays.asList(ccAllColumnHeaderEntity);

        // Configure CcAllColumnHeaderRepositoryJPA.findAll(...).
        final CcAllColumnHeaderEntity ccAllColumnHeaderEntity1 = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntity1.setCcAllColumnHeaderId(0L);
        ccAllColumnHeaderEntity1.setTableName("tableName");
        ccAllColumnHeaderEntity1.setColumnName("columnName");
        ccAllColumnHeaderEntity1.setDataType("dataType");
        ccAllColumnHeaderEntity1.setStatus(0L);
        ccAllColumnHeaderEntity1.setColumnHeader("columnHeader");
        ccAllColumnHeaderEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ccAllColumnHeaderEntity1.setUpdateUser("updateUser");
        final List<CcAllColumnHeaderEntity> ccAllColumnHeaderEntities = Arrays.asList(ccAllColumnHeaderEntity1);
        when(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA.findAll()).thenReturn(ccAllColumnHeaderEntities);

        // Run the test
        final List<CcAllColumnHeaderEntity> result = ccAllColumnHeaderServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_CcAllColumnHeaderRepositoryJPAReturnsNoItems() {
        // Setup
        when(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<CcAllColumnHeaderEntity> result = ccAllColumnHeaderServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final CcAllColumnHeaderEntity ccAllColumnHeaderEntity = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntity.setCcAllColumnHeaderId(0L);
        ccAllColumnHeaderEntity.setTableName("tableName");
        ccAllColumnHeaderEntity.setColumnName("columnName");
        ccAllColumnHeaderEntity.setDataType("dataType");
        ccAllColumnHeaderEntity.setStatus(0L);
        ccAllColumnHeaderEntity.setColumnHeader("columnHeader");
        ccAllColumnHeaderEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ccAllColumnHeaderEntity.setUpdateUser("updateUser");

        final CcAllColumnHeaderEntity expectedResult = new CcAllColumnHeaderEntity();
        expectedResult.setCcAllColumnHeaderId(0L);
        expectedResult.setTableName("tableName");
        expectedResult.setColumnName("columnName");
        expectedResult.setDataType("dataType");
        expectedResult.setStatus(0L);
        expectedResult.setColumnHeader("columnHeader");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");

        // Configure CcAllColumnHeaderRepositoryJPA.save(...).
        final CcAllColumnHeaderEntity ccAllColumnHeaderEntity1 = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntity1.setCcAllColumnHeaderId(0L);
        ccAllColumnHeaderEntity1.setTableName("tableName");
        ccAllColumnHeaderEntity1.setColumnName("columnName");
        ccAllColumnHeaderEntity1.setDataType("dataType");
        ccAllColumnHeaderEntity1.setStatus(0L);
        ccAllColumnHeaderEntity1.setColumnHeader("columnHeader");
        ccAllColumnHeaderEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ccAllColumnHeaderEntity1.setUpdateUser("updateUser");
        when(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA.save(new CcAllColumnHeaderEntity())).thenReturn(ccAllColumnHeaderEntity1);

        // Run the test
        final CcAllColumnHeaderEntity result = ccAllColumnHeaderServiceJPAUnderTest.save(ccAllColumnHeaderEntity);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
        final CcAllColumnHeaderEntity ccAllColumnHeaderEntity = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntity.setCcAllColumnHeaderId(0L);
        ccAllColumnHeaderEntity.setTableName("tableName");
        ccAllColumnHeaderEntity.setColumnName("columnName");
        ccAllColumnHeaderEntity.setDataType("dataType");
        ccAllColumnHeaderEntity.setStatus(0L);
        ccAllColumnHeaderEntity.setColumnHeader("columnHeader");
        ccAllColumnHeaderEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ccAllColumnHeaderEntity.setUpdateUser("updateUser");
        final Optional<CcAllColumnHeaderEntity> expectedResult = Optional.of(ccAllColumnHeaderEntity);

        // Configure CcAllColumnHeaderRepositoryJPA.findById(...).
        final CcAllColumnHeaderEntity ccAllColumnHeaderEntity2 = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntity2.setCcAllColumnHeaderId(0L);
        ccAllColumnHeaderEntity2.setTableName("tableName");
        ccAllColumnHeaderEntity2.setColumnName("columnName");
        ccAllColumnHeaderEntity2.setDataType("dataType");
        ccAllColumnHeaderEntity2.setStatus(0L);
        ccAllColumnHeaderEntity2.setColumnHeader("columnHeader");
        ccAllColumnHeaderEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ccAllColumnHeaderEntity2.setUpdateUser("updateUser");
        final Optional<CcAllColumnHeaderEntity> ccAllColumnHeaderEntity1 = Optional.of(ccAllColumnHeaderEntity2);
        when(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA.findById(0L)).thenReturn(ccAllColumnHeaderEntity1);

        // Run the test
        final Optional<CcAllColumnHeaderEntity> result = ccAllColumnHeaderServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_CcAllColumnHeaderRepositoryJPAReturnsAbsent() {
        // Setup
        when(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<CcAllColumnHeaderEntity> result = ccAllColumnHeaderServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        ccAllColumnHeaderServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
        final CcAllColumnHeaderEntity expectedResult = new CcAllColumnHeaderEntity();
        expectedResult.setCcAllColumnHeaderId(0L);
        expectedResult.setTableName("tableName");
        expectedResult.setColumnName("columnName");
        expectedResult.setDataType("dataType");
        expectedResult.setStatus(0L);
        expectedResult.setColumnHeader("columnHeader");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");

        // Configure CcAllColumnHeaderRepositoryJPA.getOne(...).
        final CcAllColumnHeaderEntity ccAllColumnHeaderEntity = new CcAllColumnHeaderEntity();
        ccAllColumnHeaderEntity.setCcAllColumnHeaderId(0L);
        ccAllColumnHeaderEntity.setTableName("tableName");
        ccAllColumnHeaderEntity.setColumnName("columnName");
        ccAllColumnHeaderEntity.setDataType("dataType");
        ccAllColumnHeaderEntity.setStatus(0L);
        ccAllColumnHeaderEntity.setColumnHeader("columnHeader");
        ccAllColumnHeaderEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ccAllColumnHeaderEntity.setUpdateUser("updateUser");
        when(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA.getOne(0L)).thenReturn(ccAllColumnHeaderEntity);

        // Run the test
        final CcAllColumnHeaderEntity result = ccAllColumnHeaderServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(ccAllColumnHeaderServiceJPAUnderTest.ccAllColumnHeaderRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ccAllColumnHeaderServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
