package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketExtentDTO;
import com.viettel.etc.repositories.TicketAssignRepository;
import com.viettel.etc.repositories.TicketExtentRepository;
import com.viettel.etc.repositories.tables.TicketRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketSmsMailPushRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketEntity;
import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.tables.TicketExtentServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketExtentServiceImplTest {

    @Mock
    private TicketExtentRepository mockTicketExtentRepository;
    @Mock
    private TicketSiteUserRepositoryJPA mockTicketSiteUserRepositoryJPA;
    @Mock
    private TicketServiceJPA mockTicketServiceJPA;
    @Mock
    private TicketRepositoryJPA mockTicketRepositoryJPA;
    @Mock
    private TicketExtentServiceJPA mockTicketExtentServiceJPA;
    @Mock
    private SMSServiceImpl mockSmsService;
    @Mock
    private TicketSmsMailPushRepositoryJPA mockTicketSmsMailPushRepositoryJPA;
    @Mock
    private ActionAuditService mockActionAuditService;
    @Mock
    private TicketAssignRepository mockTicketAssignRepository;

    @InjectMocks
    private TicketExtentServiceImpl ticketExtentServiceImplUnderTest;

    @InjectMocks
    private TicketServiceImpl ticketServiceImplUnderTest;

    @InjectMocks
    private TicketSiteUserServiceImpl ticketSiteUserServiceImplUnderTest;

    @InjectMocks
    private ActionAuditServiceImpl actionAuditServiceImplUnderTest;

    @Test
    void testGetTicketExtent() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO itemParamsEntity = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final Object result = ticketExtentServiceImplUnderTest.getTicketExtent(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testInsertTicketExtent() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO itemParamsEntity = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        // Configure TicketServiceJPA.getOne(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L,
                "plateNumber", "phoneNumber", "custName", "email",
                "custAddress", 0L, 0L, 0L, 0L, 0L,
                "location", "areaCode", "contentReceive", 0L, "supportInfo",
                Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L,
                Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser",
                Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser",
                Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode",
                "provinceName", "districtName", "communeName",
                "phoneContact", 0L, 0L, 0L, "stageName", "stationName",
                0L, "processUser", "feedBack", 0L, 0L);
        when(ticketServiceImplUnderTest.ticketServiceJPA.getOne(0L)).thenReturn(ticketEntity);

        // Configure TicketExtentServiceJPA.save(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.save(any())).thenReturn(ticketExtentEntity2);

        // Run the test
        final Object result = ticketExtentServiceImplUnderTest.insertTicketExtent(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testGetDetailTicketExtent() {
        // Setup
        // Configure TicketExtentServiceJPA.getByTicketIdAndStatus(...).
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.getByTicketIdAndStatus(0L, 1L)).thenReturn(ticketExtentEntity);

        // Run the test
        final Object result = ticketExtentServiceImplUnderTest.getDetailTicketExtent(0L);

        // Verify the results
    }

    @Test
    void testUpdateTicketExtent() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO dataParams = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        // Configure TicketExtentServiceJPA.findByTicketId(...).
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.findByTicketId(0L)).thenReturn(ticketExtentEntity1);

        // Configure TicketExtentServiceJPA.save(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.save(any())).thenReturn(ticketExtentEntity2);

        // Run the test
        final Object result = ticketExtentServiceImplUnderTest.updateTicketExtent(dataParams, 0L, authentication);

        // Verify the results
    }

    @Test
    void testApproveTicketStatus() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);

        final TicketExtentDTO dataParams = new TicketExtentDTO(ticketExtentEntity);
        dataParams.setTicketIds(Arrays.asList(0L));

        final Authentication authentication = null;
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        final List<TicketExtentEntity> expectedResult = Arrays.asList(ticketExtentEntity1);

        // Configure TicketExtentServiceJPA.getByTicketIdAndStatus(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(2L);
        when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.getByTicketIdAndStatus(0L, 1L)).thenReturn(ticketExtentEntity2);

        // Configure TicketServiceJPA.getOne(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        when(ticketServiceImplUnderTest.ticketServiceJPA.getOne(0L)).thenReturn(ticketEntity);

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity1 = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        Mockito.lenient().when(ticketServiceImplUnderTest.ticketServiceJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity1);

        // Configure TicketExtentServiceJPA.saveAll(...).
        final TicketExtentEntity ticketExtentEntity3 = new TicketExtentEntity();
        ticketExtentEntity3.setTicketExtentId(0L);
        ticketExtentEntity3.setTicketId(0L);
        ticketExtentEntity3.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity3.setExtentReasonId(0L);
        ticketExtentEntity3.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity3.setExtentReasonName("extentReasonName");
        ticketExtentEntity3.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity3.setManagerUserName("managerUserName");
        ticketExtentEntity3.setManagerPhone("managerPhone");
        ticketExtentEntity3.setStatus(0L);
        final List<TicketExtentEntity> ticketExtentEntities = Arrays.asList(ticketExtentEntity3);
        Mockito.lenient().when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.saveAll(any())).thenReturn(ticketExtentEntities);

        // Run the test
        final List<TicketExtentEntity> result = ticketExtentServiceImplUnderTest.approveTicketStatus(dataParams, authentication);

        // Verify the results
    }

    @Test
    void testRejectTicketStatus() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO dataParams = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        // Configure TicketExtentServiceJPA.getByTicketIdAndStatus(...).
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.getByTicketIdAndStatus(0L, 1L)).thenReturn(ticketExtentEntity1);

        // Configure TicketExtentServiceJPA.save(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        when(ticketExtentServiceImplUnderTest.ticketExtentServiceJPA.save(any())).thenReturn(ticketExtentEntity2);

        // Run the test
        final Object result = ticketExtentServiceImplUnderTest.rejectTicketStatus(dataParams, 0L, authentication);

        // Verify the results
    }

    @Test
    void testExportTicketExtent() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO dataParams = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        // Configure TicketExtentRepository.getTicketExtent(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketExtentRepository.getTicketExtent(new TicketExtentDTO(new TicketExtentEntity()), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketExtentServiceImplUnderTest.exportTicketExtent(dataParams, authentication);

        // Verify the results
    }

    @Test
    void testExportTicketProcess() {
        // Setup
        final TicketAssignDTO itemParamsEntity = new TicketAssignDTO();
        itemParamsEntity.setTicketId("ticketId");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setContractNo("contractNo");
        itemParamsEntity.setContentReceive("contentReceive");
        itemParamsEntity.setProcessContent("processContent");
        itemParamsEntity.setFromSiteId("fromSiteId");
        itemParamsEntity.setTicketStatus("ticketStatus");
        itemParamsEntity.setUserProcess("userProcess");
        itemParamsEntity.setToSiteL2Id("toSiteL2Id");
        itemParamsEntity.setReasonLevel1("reasonLevel1");

        final Authentication authentication = null;

        // Configure TicketAssignRepository.getTicketAssigns(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketAssignRepository.getTicketAssigns(new TicketAssignDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketExtentServiceImplUnderTest.exportTicketProcess(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testApproveTicketStatus_ThrowsEtcException() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO dataParams = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        // Configure TicketExtentServiceJPA.getByTicketIdAndStatus(...).
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        Mockito.lenient().when(mockTicketExtentServiceJPA.getByTicketIdAndStatus(0L, 0L)).thenReturn(ticketExtentEntity1);

        // Configure TicketServiceJPA.getOne(...).
        final TicketEntity ticketEntity = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        Mockito.lenient().when(mockTicketServiceJPA.getOne(0L)).thenReturn(ticketEntity);

        // Configure TicketRepositoryJPA.save(...).
        final TicketEntity ticketEntity1 = new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L);
        Mockito.lenient().when(mockTicketRepositoryJPA.save(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L))).thenReturn(ticketEntity1);

        // Configure TicketExtentServiceJPA.saveAll(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        final List<TicketExtentEntity> ticketExtentEntities = Arrays.asList(ticketExtentEntity2);
        Mockito.lenient().when(mockTicketExtentServiceJPA.saveAll(Arrays.asList(new TicketExtentEntity()))).thenReturn(ticketExtentEntities);

        // Run the test
    }

    @Test
    void testRejectTicketStatus_ThrowsEtcException() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO dataParams = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        // Configure TicketExtentServiceJPA.getByTicketIdAndStatus(...).
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        Mockito.lenient().when(mockTicketExtentServiceJPA.getByTicketIdAndStatus(0L, 0L)).thenReturn(ticketExtentEntity1);

        // Configure TicketExtentServiceJPA.save(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        Mockito.lenient().when(mockTicketExtentServiceJPA.save(new TicketExtentEntity())).thenReturn(ticketExtentEntity2);

        // Run the test
    }

    @Test
    void testUpdateTicketExtent_ThrowsEtcException() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final TicketExtentDTO dataParams = new TicketExtentDTO(ticketExtentEntity);
        final Authentication authentication = null;

        // Configure TicketExtentServiceJPA.findByTicketId(...).
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        Mockito.lenient().when(mockTicketExtentServiceJPA.findByTicketId(0L)).thenReturn(ticketExtentEntity1);

        // Configure TicketExtentServiceJPA.save(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        Mockito.lenient().when(mockTicketExtentServiceJPA.save(new TicketExtentEntity())).thenReturn(ticketExtentEntity2);

        // Run the test
    }
}
