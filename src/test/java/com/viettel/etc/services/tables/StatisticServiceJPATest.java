package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.StatisticRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.StatisticEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StatisticServiceJPATest {

    private StatisticServiceJPA statisticServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        statisticServiceJPAUnderTest = new StatisticServiceJPA();
        statisticServiceJPAUnderTest.statistic = mock(StatisticRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticId(0L);
        statisticEntity.setContractNoUserName("contractNoUserName");
        statisticEntity.setPlateNumber("plateNumber");
        statisticEntity.setSystemPhoneNumber("systemPhoneNumber");
        statisticEntity.setCallPhoneNumber("callPhoneNumber");
        statisticEntity.setSourceId(0L);
        statisticEntity.setL1StatisticTypeId(0L);
        statisticEntity.setL2StatisticTypeId(0L);
        statisticEntity.setL3StatisticTypeId(0L);
        statisticEntity.setL4StatisticTypeId(0L);
        final List<StatisticEntity> expectedResult = Arrays.asList(statisticEntity);

        // Configure StatisticRepositoryJPA.findAll(...).
        final StatisticEntity statisticEntity1 = new StatisticEntity();
        statisticEntity1.setStatisticId(0L);
        statisticEntity1.setContractNoUserName("contractNoUserName");
        statisticEntity1.setPlateNumber("plateNumber");
        statisticEntity1.setSystemPhoneNumber("systemPhoneNumber");
        statisticEntity1.setCallPhoneNumber("callPhoneNumber");
        statisticEntity1.setSourceId(0L);
        statisticEntity1.setL1StatisticTypeId(0L);
        statisticEntity1.setL2StatisticTypeId(0L);
        statisticEntity1.setL3StatisticTypeId(0L);
        statisticEntity1.setL4StatisticTypeId(0L);
        final List<StatisticEntity> statisticEntities = Arrays.asList(statisticEntity1);
        when(statisticServiceJPAUnderTest.statistic.findAll()).thenReturn(statisticEntities);

        // Run the test
        final List<StatisticEntity> result = statisticServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_StatisticRepositoryJPAReturnsNoItems() {
        // Setup
        when(statisticServiceJPAUnderTest.statistic.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<StatisticEntity> result = statisticServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final StatisticEntity Statistic = new StatisticEntity();
        Statistic.setStatisticId(0L);
        Statistic.setContractNoUserName("contractNoUserName");
        Statistic.setPlateNumber("plateNumber");
        Statistic.setSystemPhoneNumber("systemPhoneNumber");
        Statistic.setCallPhoneNumber("callPhoneNumber");
        Statistic.setSourceId(0L);
        Statistic.setL1StatisticTypeId(0L);
        Statistic.setL2StatisticTypeId(0L);
        Statistic.setL3StatisticTypeId(0L);
        Statistic.setL4StatisticTypeId(0L);

        final StatisticEntity expectedResult = new StatisticEntity();
        expectedResult.setStatisticId(0L);
        expectedResult.setContractNoUserName("contractNoUserName");
        expectedResult.setPlateNumber("plateNumber");
        expectedResult.setSystemPhoneNumber("systemPhoneNumber");
        expectedResult.setCallPhoneNumber("callPhoneNumber");
        expectedResult.setSourceId(0L);
        expectedResult.setL1StatisticTypeId(0L);
        expectedResult.setL2StatisticTypeId(0L);
        expectedResult.setL3StatisticTypeId(0L);
        expectedResult.setL4StatisticTypeId(0L);

        // Configure StatisticRepositoryJPA.save(...).
        final StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticId(0L);
        statisticEntity.setContractNoUserName("contractNoUserName");
        statisticEntity.setPlateNumber("plateNumber");
        statisticEntity.setSystemPhoneNumber("systemPhoneNumber");
        statisticEntity.setCallPhoneNumber("callPhoneNumber");
        statisticEntity.setSourceId(0L);
        statisticEntity.setL1StatisticTypeId(0L);
        statisticEntity.setL2StatisticTypeId(0L);
        statisticEntity.setL3StatisticTypeId(0L);
        statisticEntity.setL4StatisticTypeId(0L);
        when(statisticServiceJPAUnderTest.statistic.save(new StatisticEntity())).thenReturn(statisticEntity);

        // Run the test
        final StatisticEntity result = statisticServiceJPAUnderTest.save(Statistic);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
        final StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticId(0L);
        statisticEntity.setContractNoUserName("contractNoUserName");
        statisticEntity.setPlateNumber("plateNumber");
        statisticEntity.setSystemPhoneNumber("systemPhoneNumber");
        statisticEntity.setCallPhoneNumber("callPhoneNumber");
        statisticEntity.setSourceId(0L);
        statisticEntity.setL1StatisticTypeId(0L);
        statisticEntity.setL2StatisticTypeId(0L);
        statisticEntity.setL3StatisticTypeId(0L);
        statisticEntity.setL4StatisticTypeId(0L);
        final Optional<StatisticEntity> expectedResult = Optional.of(statisticEntity);

        // Configure StatisticRepositoryJPA.findById(...).
        final StatisticEntity statisticEntity2 = new StatisticEntity();
        statisticEntity2.setStatisticId(0L);
        statisticEntity2.setContractNoUserName("contractNoUserName");
        statisticEntity2.setPlateNumber("plateNumber");
        statisticEntity2.setSystemPhoneNumber("systemPhoneNumber");
        statisticEntity2.setCallPhoneNumber("callPhoneNumber");
        statisticEntity2.setSourceId(0L);
        statisticEntity2.setL1StatisticTypeId(0L);
        statisticEntity2.setL2StatisticTypeId(0L);
        statisticEntity2.setL3StatisticTypeId(0L);
        statisticEntity2.setL4StatisticTypeId(0L);
        final Optional<StatisticEntity> statisticEntity1 = Optional.of(statisticEntity2);
        when(statisticServiceJPAUnderTest.statistic.findById(0L)).thenReturn(statisticEntity1);

        // Run the test
        final Optional<StatisticEntity> result = statisticServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_StatisticRepositoryJPAReturnsAbsent() {
        // Setup
        when(statisticServiceJPAUnderTest.statistic.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<StatisticEntity> result = statisticServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        statisticServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(statisticServiceJPAUnderTest.statistic).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
        final StatisticEntity expectedResult = new StatisticEntity();
        expectedResult.setStatisticId(0L);
        expectedResult.setContractNoUserName("contractNoUserName");
        expectedResult.setPlateNumber("plateNumber");
        expectedResult.setSystemPhoneNumber("systemPhoneNumber");
        expectedResult.setCallPhoneNumber("callPhoneNumber");
        expectedResult.setSourceId(0L);
        expectedResult.setL1StatisticTypeId(0L);
        expectedResult.setL2StatisticTypeId(0L);
        expectedResult.setL3StatisticTypeId(0L);
        expectedResult.setL4StatisticTypeId(0L);

        // Configure StatisticRepositoryJPA.getOne(...).
        final StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticId(0L);
        statisticEntity.setContractNoUserName("contractNoUserName");
        statisticEntity.setPlateNumber("plateNumber");
        statisticEntity.setSystemPhoneNumber("systemPhoneNumber");
        statisticEntity.setCallPhoneNumber("callPhoneNumber");
        statisticEntity.setSourceId(0L);
        statisticEntity.setL1StatisticTypeId(0L);
        statisticEntity.setL2StatisticTypeId(0L);
        statisticEntity.setL3StatisticTypeId(0L);
        statisticEntity.setL4StatisticTypeId(0L);
        when(statisticServiceJPAUnderTest.statistic.getOne(0L)).thenReturn(statisticEntity);

        // Run the test
        final StatisticEntity result = statisticServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(statisticServiceJPAUnderTest.statistic.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = statisticServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
