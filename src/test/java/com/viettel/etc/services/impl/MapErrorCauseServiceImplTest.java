package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.dto.MapErrorUpdateDTO;
import com.viettel.etc.repositories.MapErrorCauseRepository;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.tables.MapErrorCauseServiceJPA;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MapErrorCauseServiceImplTest {

    private MapErrorCauseServiceImpl mapErrorCauseServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mapErrorCauseServiceImplUnderTest = new MapErrorCauseServiceImpl();
        mapErrorCauseServiceImplUnderTest.mapErrorCauseRepository = mock(MapErrorCauseRepository.class);
        mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA = mock(MapErrorCauseServiceJPA.class);
        mapErrorCauseServiceImplUnderTest.actionAuditService = mock(ActionAuditService.class);
    }

    @Test
    void testSearchMapErrorCause() {
        // Setup
        final Authentication authentication = null;
        final MapErrorCauseSearchDTO params = new MapErrorCauseSearchDTO();
        params.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setCreateUser("createUser");
        params.setStartRecord(0);
        params.setPageSize(0);
        params.setLstTicketTypeGroupId(Arrays.asList(0L));
        params.setLstTicketCategoryId(Arrays.asList(0L));
        params.setLstErrorCauseLv1(Arrays.asList(0L));
        params.setLstErrorCauseLv2(Arrays.asList(0L));
        params.setLstErrorCauseLv3(Arrays.asList(0L));
        params.setLstParentLvId2(Arrays.asList(0L));
        params.setLstParentLvId3(Arrays.asList(0L));

        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseRepository.searchMapErrorCause(null,
                new MapErrorCauseSearchDTO())).thenReturn("result");

        // Run the test
        final Object result = mapErrorCauseServiceImplUnderTest.searchMapErrorCause(authentication, params);

        // Verify the results
    }

    @Test
    void testSaveMapError() {
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
        final List<MapErrorEntity> dataParams = Arrays.asList(mapErrorEntity);
        final Authentication authentication = null;

        // Configure MapErrorCauseServiceJPA.saveAll(...).
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
        final List<MapErrorEntity> mapErrorEntities = Arrays.asList(mapErrorEntity1);
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.saveAll(
                Arrays.asList(new MapErrorEntity()))).thenReturn(mapErrorEntities);

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
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAudit(null, new ActionAuditDTO()))
                .thenReturn(actionAuditEntity);

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
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new MapErrorEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = mapErrorCauseServiceImplUnderTest.saveMapError(dataParams, authentication);

        // Verify the results
//        verify(mapErrorCauseServiceImplUnderTest.actionAuditService).saveActAuditDetail(0L, new TicketTypeEntity(),
//                new MapErrorEntity(), 0L, "value");
    }

    @Test
    void testSaveMapError_MapErrorCauseServiceJPAReturnsNoItems() {
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
        final List<MapErrorEntity> dataParams = Arrays.asList(mapErrorEntity);
        final Authentication authentication = null;
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.saveAll(
                Arrays.asList(new MapErrorEntity()))).thenReturn(Collections.emptyList());

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
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAudit(null, new ActionAuditDTO()))
                .thenReturn(actionAuditEntity);

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
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketTypeEntity(),
                new MapErrorEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = mapErrorCauseServiceImplUnderTest.saveMapError(dataParams, authentication);

        // Verify the results
    }

    @Test
    void testOnDelete() {
        // Setup
        final Authentication authentication = null;

        // Configure MapErrorCauseServiceJPA.findById(...).
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
        final Optional<MapErrorEntity> mapErrorEntity = Optional.of(mapErrorEntity1);
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.findById(0L)).thenReturn(mapErrorEntity);

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
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAudit(null, new ActionAuditDTO()))
                .thenReturn(actionAuditEntity);

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
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAuditDetail(eq(0L), eq(new MapErrorEntity()),
                any(Object.class), eq(0L), eq("value"))).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Object result = mapErrorCauseServiceImplUnderTest.onDelete(0L, authentication);

        // Verify the results
        verify(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA).deleteById(0L);
    }

    @Test
    void testOnDelete_MapErrorCauseServiceJPAFindByIdReturnsAbsent() {
        // Setup
        final Authentication authentication = null;
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Object result = mapErrorCauseServiceImplUnderTest.onDelete(0L, authentication);

        // Verify the results
    }

    @Test
    void testSearchDataMapForUpdate() {
        // Setup
        final Authentication authentication = null;
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseRepository.searchDataMapForUpdate(0L, null))
                .thenReturn("result");

        // Run the test
        final Object result = mapErrorCauseServiceImplUnderTest.searchDataMapForUpdate(0L, authentication);

        // Verify the results
    }

    @Test
    void testExportMapErrorCause() {
        // Setup
        final MapErrorCauseSearchDTO params = new MapErrorCauseSearchDTO();
        params.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setCreateUser("createUser");
        params.setStartRecord(0);
        params.setPageSize(0);
        params.setLstTicketTypeGroupId(Arrays.asList(0L));
        params.setLstTicketCategoryId(Arrays.asList(0L));
        params.setLstErrorCauseLv1(Arrays.asList(0L));
        params.setLstErrorCauseLv2(Arrays.asList(0L));
        params.setLstErrorCauseLv3(Arrays.asList(0L));
        params.setLstParentLvId2(Arrays.asList(0L));
        params.setLstParentLvId3(Arrays.asList(0L));

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure MapErrorCauseRepository.exportImpact(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseRepository.exportImpact(
                new MapErrorCauseSearchDTO())).thenReturn(resultSelectEntity);

        // Run the test
        mapErrorCauseServiceImplUnderTest.exportMapErrorCause(params, response);

        // Verify the results
    }

    @Test
    void testDownloadMapErrorCauseTemplate() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        mapErrorCauseServiceImplUnderTest.downloadMapErrorCauseTemplate(null, response);

        // Verify the results
    }

    @Test
    void testGetErrorCauseByParentIdForUpdateMap() {
        // Setup
        final Authentication authentication = null;
        final MapErrorCauseSearchDTO dataParams = new MapErrorCauseSearchDTO();
        dataParams.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dataParams.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dataParams.setCreateUser("createUser");
        dataParams.setStartRecord(0);
        dataParams.setPageSize(0);
        dataParams.setLstTicketTypeGroupId(Arrays.asList(0L));
        dataParams.setLstTicketCategoryId(Arrays.asList(0L));
        dataParams.setLstErrorCauseLv1(Arrays.asList(0L));
        dataParams.setLstErrorCauseLv2(Arrays.asList(0L));
        dataParams.setLstErrorCauseLv3(Arrays.asList(0L));
        dataParams.setLstParentLvId2(Arrays.asList(0L));
        dataParams.setLstParentLvId3(Arrays.asList(0L));

        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseRepository.getErrorCauseByParentIdForUpdateMap(null,
                new MapErrorCauseSearchDTO())).thenReturn("result");

        // Run the test
        final Object result = mapErrorCauseServiceImplUnderTest.getErrorCauseByParentIdForUpdateMap(authentication,
                dataParams);

        // Verify the results
    }

    @Test
    void testUpdateMapError() {
        // Setup
        final MapErrorUpdateDTO mapErrorUpdateDTO = new MapErrorUpdateDTO();
        mapErrorUpdateDTO.setMapErrorCauseId(Arrays.asList(0L));
        mapErrorUpdateDTO.setTicketTypeId(0L);
        mapErrorUpdateDTO.setTicketErrorId(0L);
        mapErrorUpdateDTO.setTicketErrorLv2Id(0L);
        mapErrorUpdateDTO.setTicketErrorLv3Id(0L);
        final List<MapErrorUpdateDTO> dataParams = Arrays.asList(mapErrorUpdateDTO);
        final Authentication authentication = null;

        // Configure MapErrorCauseServiceJPA.findById(...).
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
        final Optional<MapErrorEntity> mapErrorEntity = Optional.of(mapErrorEntity1);
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.findById(0L)).thenReturn(mapErrorEntity);

        // Configure MapErrorCauseServiceJPA.saveAll(...).
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
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.saveAll(
                Arrays.asList(new MapErrorEntity()))).thenReturn(mapErrorEntities);

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
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAudit(null, new ActionAuditDTO()))
                .thenReturn(actionAuditEntity);

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
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new MapErrorEntity(),
                new MapErrorEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Boolean result = mapErrorCauseServiceImplUnderTest.updateMapError(dataParams, authentication);

        // Verify the results
    }

    @Test
    void testUpdateMapError_MapErrorCauseServiceJPAFindByIdReturnsAbsent() {
        // Setup
        final MapErrorUpdateDTO mapErrorUpdateDTO = new MapErrorUpdateDTO();
        mapErrorUpdateDTO.setMapErrorCauseId(Arrays.asList(0L));
        mapErrorUpdateDTO.setTicketTypeId(0L);
        mapErrorUpdateDTO.setTicketErrorId(0L);
        mapErrorUpdateDTO.setTicketErrorLv2Id(0L);
        mapErrorUpdateDTO.setTicketErrorLv3Id(0L);
        final List<MapErrorUpdateDTO> dataParams = Arrays.asList(mapErrorUpdateDTO);
        final Authentication authentication = null;
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.findById(0L)).thenReturn(Optional.empty());

        // Configure MapErrorCauseServiceJPA.saveAll(...).
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
        final List<MapErrorEntity> mapErrorEntities = Arrays.asList(mapErrorEntity);
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.saveAll(
                Arrays.asList(new MapErrorEntity()))).thenReturn(mapErrorEntities);

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
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAudit(null, new ActionAuditDTO()))
                .thenReturn(actionAuditEntity);

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
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new MapErrorEntity(),
                new MapErrorEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Boolean result = mapErrorCauseServiceImplUnderTest.updateMapError(dataParams, authentication);

        // Verify the results
    }

    @Test
    void testUpdateMapError_MapErrorCauseServiceJPASaveAllReturnsNoItems() {
        // Setup
        final MapErrorUpdateDTO mapErrorUpdateDTO = new MapErrorUpdateDTO();
        mapErrorUpdateDTO.setMapErrorCauseId(Arrays.asList(0L));
        mapErrorUpdateDTO.setTicketTypeId(0L);
        mapErrorUpdateDTO.setTicketErrorId(0L);
        mapErrorUpdateDTO.setTicketErrorLv2Id(0L);
        mapErrorUpdateDTO.setTicketErrorLv3Id(0L);
        final List<MapErrorUpdateDTO> dataParams = Arrays.asList(mapErrorUpdateDTO);
        final Authentication authentication = null;

        // Configure MapErrorCauseServiceJPA.findById(...).
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
        final Optional<MapErrorEntity> mapErrorEntity = Optional.of(mapErrorEntity1);
        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.findById(0L)).thenReturn(mapErrorEntity);

        when(mapErrorCauseServiceImplUnderTest.mapErrorCauseServiceJPA.saveAll(
                Arrays.asList(new MapErrorEntity()))).thenReturn(Collections.emptyList());

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
        actionAuditEntity.setDescription("description");
        actionAuditEntity.setStatus(0L);
        actionAuditEntity.setTicketStatus(0L);
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAudit(null, new ActionAuditDTO()))
                .thenReturn(actionAuditEntity);

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
        when(mapErrorCauseServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new MapErrorEntity(),
                new MapErrorEntity(), 0L, "value")).thenReturn(actionAuditDetailEntities);

        // Run the test
        final Boolean result = mapErrorCauseServiceImplUnderTest.updateMapError(dataParams, authentication);

        // Verify the results
    }
}
