package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketProcessShareDTO;
import com.viettel.etc.repositories.TicketProcessShareRepository;
import com.viettel.etc.repositories.TicketRepository;
import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketSmsMailPushRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.tables.TicketProcessShareDetailServiceJPA;
import com.viettel.etc.services.tables.TicketProcessShareServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketProcessShareServiceImplTest {

    private TicketProcessShareServiceImpl ticketProcessShareServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareServiceImplUnderTest = new TicketProcessShareServiceImpl();
        ticketProcessShareServiceImplUnderTest.ticketProcessShareRepository = mock(TicketProcessShareRepository.class);
        ticketProcessShareServiceImplUnderTest.ticketServiceJPA = mock(TicketServiceJPA.class);
        ticketProcessShareServiceImplUnderTest.ticketRepository = mock(TicketRepository.class);
        ticketProcessShareServiceImplUnderTest.ticketProcessShareServiceJPA = mock(TicketProcessShareServiceJPA.class);
        ticketProcessShareServiceImplUnderTest.ticketProcessShareDetailServiceJPA = mock(TicketProcessShareDetailServiceJPA.class);
        ticketProcessShareServiceImplUnderTest.smsService = mock(SMSServiceImpl.class);
        ticketProcessShareServiceImplUnderTest.ticketSiteUserRepositoryJPA = mock(TicketSiteUserRepositoryJPA.class);
        ticketProcessShareServiceImplUnderTest.ticketSmsMailPushRepositoryJPA = mock(TicketSmsMailPushRepositoryJPA.class);
        ticketProcessShareServiceImplUnderTest.actionAuditService = mock(ActionAuditService.class);
    }

    @Test
    void testGetTicketProcessShare() {
        // Setup
        final TicketProcessShareDTO itemParamsEntity = new TicketProcessShareDTO();
        itemParamsEntity.setTicketProcessShareId(0L);
        itemParamsEntity.setAssignTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setAssignUser("assignUser");
        itemParamsEntity.setAssignType(0L);
        itemParamsEntity.setTicketIds(Arrays.asList(0L));
        itemParamsEntity.setProcessUsers(Arrays.asList("value"));
        itemParamsEntity.setIsSms(false);
        itemParamsEntity.setStartrecord(0);
        itemParamsEntity.setPagesize(0);
        itemParamsEntity.setActTypeId(0L);

        // Configure TicketProcessShareRepository.getTicketProcessShare(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketProcessShareServiceImplUnderTest.ticketProcessShareRepository.getTicketProcessShare(new TicketProcessShareDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketProcessShareServiceImplUnderTest.getTicketProcessShare(itemParamsEntity);

        // Verify the results
    }

    @Test
    void testSaveTicketProcessShare() {
        // Setup
        final TicketProcessShareDTO req = new TicketProcessShareDTO();
        req.setTicketProcessShareId(0L);
        req.setAssignTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        req.setAssignUser("assignUser");
        req.setAssignType(0L);
        req.setTicketIds(Arrays.asList(1L));
        req.setProcessUsers(Arrays.asList("value"));
        req.setIsSms(false);
        req.setStartrecord(0);
        req.setPagesize(0);
        req.setActTypeId(0L);

        final Authentication authentication = null;
        when(ticketProcessShareServiceImplUnderTest.ticketProcessShareDetailServiceJPA.existsByTicketIdAndProcessUserIsNotNull(req.getTicketIds())).thenReturn(true);

        // Configure TicketServiceJPA.findById(...).
        final Optional<TicketEntity> ticketEntity = Optional.of(new TicketEntity(1L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketServiceJPA.findById(1L)).thenReturn(ticketEntity);

        // Configure ActionAuditService.saveActAudit(...).
        final ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
        actionAuditEntity.setActionAuditId(0L);
        actionAuditEntity.setActTypeId(0L);
        actionAuditEntity.setContractId(0L);
        actionAuditEntity.setTicketId(1L);
        actionAuditEntity.setTicketAssignId(0L);
        actionAuditEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity.setActionUserFullName("actionUserFullName");
        actionAuditEntity.setActionUserName("actionUserName");
        actionAuditEntity.setAppId("appId");
        actionAuditEntity.setIpPc("ipPc");
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(req.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.get().getContractId());
        actionAuditDTO.setTicketStatus(ticketEntity.get().getStatus());
        actionAuditDTO.setTicketId(ticketEntity.get().getTicketId());
        when(ticketProcessShareServiceImplUnderTest.actionAuditService.saveActAudit(null, actionAuditDTO)).thenReturn(actionAuditEntity);

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
        when(ticketProcessShareServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

        // Configure TicketProcessShareServiceJPA.save(...).
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);
        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketProcessShareServiceJPA.save(any())).thenReturn(ticketProcessShareEntity);

        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketProcessShareDetailServiceJPA.getNextValSequenceSerial()).thenReturn(0L);

        // Configure TicketSiteUserRepositoryJPA.findByUserNameIgnoreCase(...).
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase("userName")).thenReturn(ticketSiteUserEntity);

        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.smsService.sendSMS("phone", "content", null)).thenReturn(0);
        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketSmsMailPushRepositoryJPA.getNextValSequenceSerial()).thenReturn(0L);

        // Configure TicketServiceJPA.saveAll(...).
        final List<TicketEntity> ticketEntities = Arrays.asList(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketServiceJPA.saveAll(anyList())).thenReturn(ticketEntities);

        // Configure TicketProcessShareDetailServiceJPA.saveAll(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> ticketProcessShareDetailEntities = Arrays.asList(ticketProcessShareDetailEntity);
        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketProcessShareDetailServiceJPA.saveAll(anyList())).thenReturn(ticketProcessShareDetailEntities);

        // Configure TicketSmsMailPushRepositoryJPA.saveAll(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        final List<TicketSmsMailPushEntity> ticketSmsMailPushEntities = Arrays.asList(ticketSmsMailPushEntity);
        Mockito.lenient().when(ticketProcessShareServiceImplUnderTest.ticketSmsMailPushRepositoryJPA.saveAll(anyList())).thenReturn(ticketSmsMailPushEntities);

        // Run the test
        final Object result = ticketProcessShareServiceImplUnderTest.saveTicketProcessShare(req, authentication);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testSaveTicketProcessShare_TicketServiceJPAFindByIdReturnsAbsent() {
        // Setup
        final TicketProcessShareDTO req = new TicketProcessShareDTO();
        req.setTicketProcessShareId(0L);
        req.setAssignTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        req.setAssignUser("assignUser");
        req.setAssignType(0L);
        req.setTicketIds(Arrays.asList(0L));
        req.setProcessUsers(Arrays.asList("value"));
        req.setIsSms(false);
        req.setStartrecord(0);
        req.setPagesize(0);
        req.setActTypeId(0L);

        final Authentication authentication = null;
        when(ticketProcessShareServiceImplUnderTest.ticketProcessShareDetailServiceJPA.existsByTicketIdAndProcessUserIsNotNull(Arrays.asList(0L))).thenReturn(true);
        when(ticketProcessShareServiceImplUnderTest.ticketServiceJPA.findById(0L)).thenReturn(Optional.empty());

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
        when(ticketProcessShareServiceImplUnderTest.actionAuditService.saveActAudit(any(), any())).thenReturn(actionAuditEntity);

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
        when(ticketProcessShareServiceImplUnderTest.actionAuditService.saveActAuditDetail(0L, new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L), 0L, "actionName")).thenReturn(actionAuditDetailEntities);

        // Configure TicketProcessShareServiceJPA.save(...).
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);
        when(ticketProcessShareServiceImplUnderTest.ticketProcessShareServiceJPA.save(new TicketProcessShareEntity())).thenReturn(ticketProcessShareEntity);

        when(ticketProcessShareServiceImplUnderTest.ticketProcessShareDetailServiceJPA.getNextValSequenceSerial()).thenReturn(0L);

        // Configure TicketSiteUserRepositoryJPA.findByUserNameIgnoreCase(...).
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        when(ticketProcessShareServiceImplUnderTest.ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase("userName")).thenReturn(ticketSiteUserEntity);

        when(ticketProcessShareServiceImplUnderTest.smsService.sendSMS("phone", "content", null)).thenReturn(0);
        when(ticketProcessShareServiceImplUnderTest.ticketSmsMailPushRepositoryJPA.getNextValSequenceSerial()).thenReturn(0L);

        // Configure TicketServiceJPA.saveAll(...).
        final List<TicketEntity> ticketEntities = Arrays.asList(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L));
        when(ticketProcessShareServiceImplUnderTest.ticketServiceJPA.saveAll(Arrays.asList(new TicketEntity(0L, 0L, "contractNo", 0L, "plateNumber", "phoneNumber", "custName", "email", "custAddress", 0L, 0L, 0L, 0L, 0L, "location", "areaCode", "contentReceive", 0L, "supportInfo", Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)), "note", 0L, 0L, Date.valueOf(LocalDate.of(2020, 1, 1)), 0L, "createUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "updateUser", Date.valueOf(LocalDate.of(2020, 1, 1)), "plateTypeCode", "provinceName", "districtName", "communeName", "phoneContact", 0L, 0L, 0L, "stageName", "stationName", 0L, "processUser", "feedBack", 0L, 0L)))).thenReturn(ticketEntities);

        // Configure TicketProcessShareDetailServiceJPA.saveAll(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> ticketProcessShareDetailEntities = Arrays.asList(ticketProcessShareDetailEntity);
        when(ticketProcessShareServiceImplUnderTest.ticketProcessShareDetailServiceJPA.saveAll(Arrays.asList(new TicketProcessShareDetailEntity()))).thenReturn(ticketProcessShareDetailEntities);

        // Configure TicketSmsMailPushRepositoryJPA.saveAll(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        final List<TicketSmsMailPushEntity> ticketSmsMailPushEntities = Arrays.asList(ticketSmsMailPushEntity);
        when(ticketProcessShareServiceImplUnderTest.ticketSmsMailPushRepositoryJPA.saveAll(Arrays.asList(new TicketSmsMailPushEntity()))).thenReturn(ticketSmsMailPushEntities);

        // Run the test
        final Object result = ticketProcessShareServiceImplUnderTest.saveTicketProcessShare(req, authentication);

        // Verify the results
    }
}
