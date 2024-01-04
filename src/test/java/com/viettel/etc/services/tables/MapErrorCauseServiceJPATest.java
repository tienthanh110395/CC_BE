package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.MapErrorCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
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

class MapErrorCauseServiceJPATest {

    private MapErrorCauseServiceJPA mapErrorCauseServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mapErrorCauseServiceJPAUnderTest = new MapErrorCauseServiceJPA();
        mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA = mock(MapErrorCauseRepositoryJPA.class);
    }

    @Test
    void testSaveAll() {
        // Setup
        final MapErrorEntity mapErrorEntity = new MapErrorEntity();
        mapErrorEntity.setMapId(0L);
        mapErrorEntity.setTicketTypeId(0L);
        mapErrorEntity.setTicketErrorId(0L);
        mapErrorEntity.setTicketErrorLv2Id(0L);
        mapErrorEntity.setTicketErrorLv3Id(0L);
        mapErrorEntity.setCreateUser("createUser");
        mapErrorEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        mapErrorEntity.setUpdateUser("updateUser");
        mapErrorEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<MapErrorEntity> dataList = Arrays.asList(mapErrorEntity);
        final MapErrorEntity mapErrorEntity1 = new MapErrorEntity();
        mapErrorEntity1.setMapId(0L);
        mapErrorEntity1.setTicketTypeId(0L);
        mapErrorEntity1.setTicketErrorId(0L);
        mapErrorEntity1.setTicketErrorLv2Id(0L);
        mapErrorEntity1.setTicketErrorLv3Id(0L);
        mapErrorEntity1.setCreateUser("createUser");
        mapErrorEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        mapErrorEntity1.setUpdateUser("updateUser");
        mapErrorEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<MapErrorEntity> expectedResult = Arrays.asList(mapErrorEntity1);

        // Configure MapErrorCauseRepositoryJPA.saveAll(...).
        final MapErrorEntity mapErrorEntity2 = new MapErrorEntity();
        mapErrorEntity2.setMapId(0L);
        mapErrorEntity2.setTicketTypeId(0L);
        mapErrorEntity2.setTicketErrorId(0L);
        mapErrorEntity2.setTicketErrorLv2Id(0L);
        mapErrorEntity2.setTicketErrorLv3Id(0L);
        mapErrorEntity2.setCreateUser("createUser");
        mapErrorEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        mapErrorEntity2.setUpdateUser("updateUser");
        mapErrorEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<MapErrorEntity> mapErrorEntities = Arrays.asList(mapErrorEntity2);
        when(mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA.saveAll(
                Arrays.asList(new MapErrorEntity()))).thenReturn(mapErrorEntities);

        // Run the test
        final List<MapErrorEntity> result = mapErrorCauseServiceJPAUnderTest.saveAll(dataList);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSaveAll_MapErrorCauseRepositoryJPAReturnsNoItems() {
        // Setup
        final MapErrorEntity mapErrorEntity = new MapErrorEntity();
        mapErrorEntity.setMapId(0L);
        mapErrorEntity.setTicketTypeId(0L);
        mapErrorEntity.setTicketErrorId(0L);
        mapErrorEntity.setTicketErrorLv2Id(0L);
        mapErrorEntity.setTicketErrorLv3Id(0L);
        mapErrorEntity.setCreateUser("createUser");
        mapErrorEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        mapErrorEntity.setUpdateUser("updateUser");
        mapErrorEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<MapErrorEntity> dataList = Arrays.asList(mapErrorEntity);
        when(mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA.saveAll(
                Arrays.asList(new MapErrorEntity()))).thenReturn(Collections.emptyList());

        // Run the test
        final List<MapErrorEntity> result = mapErrorCauseServiceJPAUnderTest.saveAll(dataList);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final MapErrorEntity expectedResult = new MapErrorEntity();
        expectedResult.setMapId(0L);
        expectedResult.setTicketTypeId(0L);
        expectedResult.setTicketErrorId(0L);
        expectedResult.setTicketErrorLv2Id(0L);
        expectedResult.setTicketErrorLv3Id(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure MapErrorCauseRepositoryJPA.getOne(...).
        final MapErrorEntity mapErrorEntity = new MapErrorEntity();
        mapErrorEntity.setMapId(0L);
        mapErrorEntity.setTicketTypeId(0L);
        mapErrorEntity.setTicketErrorId(0L);
        mapErrorEntity.setTicketErrorLv2Id(0L);
        mapErrorEntity.setTicketErrorLv3Id(0L);
        mapErrorEntity.setCreateUser("createUser");
        mapErrorEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        mapErrorEntity.setUpdateUser("updateUser");
        mapErrorEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA.getOne(0L)).thenReturn(mapErrorEntity);

        // Run the test
        final MapErrorEntity result = mapErrorCauseServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        mapErrorCauseServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA).deleteById(0L);
    }

    @Test
    void testDeleteDataByListId() {
        // Setup
        // Run the test
        mapErrorCauseServiceJPAUnderTest.deleteDataByListId(Arrays.asList(0L));

        // Verify the results
        verify(mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA).deleteDataByListId(Arrays.asList(0L));
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final MapErrorEntity mapErrorEntity = new MapErrorEntity();
        mapErrorEntity.setMapId(0L);
        mapErrorEntity.setTicketTypeId(0L);
        mapErrorEntity.setTicketErrorId(0L);
        mapErrorEntity.setTicketErrorLv2Id(0L);
        mapErrorEntity.setTicketErrorLv3Id(0L);
        mapErrorEntity.setCreateUser("createUser");
        mapErrorEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        mapErrorEntity.setUpdateUser("updateUser");
        mapErrorEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<MapErrorEntity> expectedResult = Optional.of(mapErrorEntity);

        // Configure MapErrorCauseRepositoryJPA.findById(...).
        final MapErrorEntity mapErrorEntity2 = new MapErrorEntity();
        mapErrorEntity2.setMapId(0L);
        mapErrorEntity2.setTicketTypeId(0L);
        mapErrorEntity2.setTicketErrorId(0L);
        mapErrorEntity2.setTicketErrorLv2Id(0L);
        mapErrorEntity2.setTicketErrorLv3Id(0L);
        mapErrorEntity2.setCreateUser("createUser");
        mapErrorEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        mapErrorEntity2.setUpdateUser("updateUser");
        mapErrorEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<MapErrorEntity> mapErrorEntity1 = Optional.of(mapErrorEntity2);
        when(mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA.findById(0L)).thenReturn(mapErrorEntity1);

        // Run the test
        final Optional<MapErrorEntity> result = mapErrorCauseServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_MapErrorCauseRepositoryJPAReturnsAbsent() {
        // Setup
        when(mapErrorCauseServiceJPAUnderTest.mapErrorCauseRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<MapErrorEntity> result = mapErrorCauseServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }
}
