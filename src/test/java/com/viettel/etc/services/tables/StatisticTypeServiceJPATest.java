package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.StatisticTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
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

class StatisticTypeServiceJPATest {

    private StatisticTypeServiceJPA statisticTypeServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        statisticTypeServiceJPAUnderTest = new StatisticTypeServiceJPA();
        statisticTypeServiceJPAUnderTest.statistic_type = mock(StatisticTypeRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final StatisticTypeEntity statisticTypeEntity = new StatisticTypeEntity();
        statisticTypeEntity.setStatisticTypeId(0L);
        statisticTypeEntity.setName("name");
        statisticTypeEntity.setCode("code");
        statisticTypeEntity.setDescription("description");
        statisticTypeEntity.setParentId(1L);
        statisticTypeEntity.setStatus(0L);
        statisticTypeEntity.setCreateUser("createUser");
        statisticTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        statisticTypeEntity.setUpdateUser("updateUser");
        statisticTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<StatisticTypeEntity> expectedResult = Arrays.asList(statisticTypeEntity);

        // Configure StatisticTypeRepositoryJPA.findAll(...).
        final StatisticTypeEntity statisticTypeEntity1 = new StatisticTypeEntity();
        statisticTypeEntity1.setStatisticTypeId(0L);
        statisticTypeEntity1.setName("name");
        statisticTypeEntity1.setCode("code");
        statisticTypeEntity1.setDescription("description");
        statisticTypeEntity1.setParentId(1L);
        statisticTypeEntity1.setStatus(0L);
        statisticTypeEntity1.setCreateUser("createUser");
        statisticTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        statisticTypeEntity1.setUpdateUser("updateUser");
        statisticTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<StatisticTypeEntity> statisticTypeEntities = Arrays.asList(statisticTypeEntity1);
        when(statisticTypeServiceJPAUnderTest.statistic_type.findAll()).thenReturn(statisticTypeEntities);

        // Run the test
        final List<StatisticTypeEntity> result = statisticTypeServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_StatisticTypeRepositoryJPAReturnsNoItems() {
        // Setup
        when(statisticTypeServiceJPAUnderTest.statistic_type.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<StatisticTypeEntity> result = statisticTypeServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final StatisticTypeEntity Statistic_type = new StatisticTypeEntity();
        Statistic_type.setStatisticTypeId(0L);
        Statistic_type.setName("name");
        Statistic_type.setCode("code");
        Statistic_type.setDescription("description");
        Statistic_type.setParentId(1L);
        Statistic_type.setStatus(0L);
        Statistic_type.setCreateUser("createUser");
        Statistic_type.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Statistic_type.setUpdateUser("updateUser");
        Statistic_type.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final StatisticTypeEntity expectedResult = new StatisticTypeEntity();
        expectedResult.setStatisticTypeId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(1L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure StatisticTypeRepositoryJPA.save(...).
        final StatisticTypeEntity statisticTypeEntity = new StatisticTypeEntity();
        statisticTypeEntity.setStatisticTypeId(0L);
        statisticTypeEntity.setName("name");
        statisticTypeEntity.setCode("code");
        statisticTypeEntity.setDescription("description");
        statisticTypeEntity.setParentId(1L);
        statisticTypeEntity.setStatus(0L);
        statisticTypeEntity.setCreateUser("createUser");
        statisticTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        statisticTypeEntity.setUpdateUser("updateUser");
        statisticTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(statisticTypeServiceJPAUnderTest.statistic_type.save(new StatisticTypeEntity())).thenReturn(statisticTypeEntity);

        // Run the test
        final StatisticTypeEntity result = statisticTypeServiceJPAUnderTest.save(Statistic_type);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
        final StatisticTypeEntity statisticTypeEntity = new StatisticTypeEntity();
        statisticTypeEntity.setStatisticTypeId(0L);
        statisticTypeEntity.setName("name");
        statisticTypeEntity.setCode("code");
        statisticTypeEntity.setDescription("description");
        statisticTypeEntity.setParentId(1L);
        statisticTypeEntity.setStatus(0L);
        statisticTypeEntity.setCreateUser("createUser");
        statisticTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        statisticTypeEntity.setUpdateUser("updateUser");
        statisticTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<StatisticTypeEntity> expectedResult = Optional.of(statisticTypeEntity);

        // Configure StatisticTypeRepositoryJPA.findById(...).
        final StatisticTypeEntity statisticTypeEntity2 = new StatisticTypeEntity();
        statisticTypeEntity2.setStatisticTypeId(0L);
        statisticTypeEntity2.setName("name");
        statisticTypeEntity2.setCode("code");
        statisticTypeEntity2.setDescription("description");
        statisticTypeEntity2.setParentId(1L);
        statisticTypeEntity2.setStatus(0L);
        statisticTypeEntity2.setCreateUser("createUser");
        statisticTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        statisticTypeEntity2.setUpdateUser("updateUser");
        statisticTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<StatisticTypeEntity> statisticTypeEntity1 = Optional.of(statisticTypeEntity2);
        when(statisticTypeServiceJPAUnderTest.statistic_type.findById(0L)).thenReturn(statisticTypeEntity1);

        // Run the test
        final Optional<StatisticTypeEntity> result = statisticTypeServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_StatisticTypeRepositoryJPAReturnsAbsent() {
        // Setup
        when(statisticTypeServiceJPAUnderTest.statistic_type.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<StatisticTypeEntity> result = statisticTypeServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        statisticTypeServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(statisticTypeServiceJPAUnderTest.statistic_type).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
        final StatisticTypeEntity expectedResult = new StatisticTypeEntity();
        expectedResult.setStatisticTypeId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(1L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure StatisticTypeRepositoryJPA.getOne(...).
        final StatisticTypeEntity statisticTypeEntity = new StatisticTypeEntity();
        statisticTypeEntity.setStatisticTypeId(0L);
        statisticTypeEntity.setName("name");
        statisticTypeEntity.setCode("code");
        statisticTypeEntity.setDescription("description");
        statisticTypeEntity.setParentId(1L);
        statisticTypeEntity.setStatus(0L);
        statisticTypeEntity.setCreateUser("createUser");
        statisticTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        statisticTypeEntity.setUpdateUser("updateUser");
        statisticTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(statisticTypeServiceJPAUnderTest.statistic_type.getOne(0L)).thenReturn(statisticTypeEntity);

        // Run the test
        final StatisticTypeEntity result = statisticTypeServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(statisticTypeServiceJPAUnderTest.statistic_type.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = statisticTypeServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
