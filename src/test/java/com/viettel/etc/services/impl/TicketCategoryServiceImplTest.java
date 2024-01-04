package com.viettel.etc.services.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketCategoryRepository;
import com.viettel.etc.repositories.tables.TicketSlaRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSlaEntity;
import com.viettel.etc.repositories.tables.entities.TicketSourceEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Assertions;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketCategoryServiceImplTest {

    @Mock
    private TicketCategoryRepository mockTicketCategoryRepository;
    @Mock
    private TicketSlaRepositoryJPA mockTicketSlaRepositoryJPA;

    @InjectMocks
    private TicketCategoryServiceImpl ticketCategoryServiceImplUnderTest;

    @Test
    void testGetTicketSource() {
        // Setup
        final TicketSourceDTO itemParamsEntity = new TicketSourceDTO();
        itemParamsEntity.setName("name");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");


        // Configure TicketSourceRepositoryJPA.findByStatus(...).
        final TicketSourceEntity ticketSourceEntity = new TicketSourceEntity();
        ticketSourceEntity.setTicketSourceId(0L);
        ticketSourceEntity.setName("name");
        ticketSourceEntity.setSourceCode("sourceCode");
        ticketSourceEntity.setDescription("description");
        ticketSourceEntity.setStatus(1L);
        ticketSourceEntity.setCreateUser("createUser");
        ticketSourceEntity.setCreateDate(new Date(0L));
        ticketSourceEntity.setUpdateUser("updateUser");
        ticketSourceEntity.setUpdateDate(new Date(0L));
        ResultSelectEntity data = new ResultSelectEntity();
        data.setListData(Arrays.asList(ticketSourceEntity));
        when(mockTicketCategoryRepository.getTicketSource(any())).thenReturn(data);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketSource(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketTypes() {
        // Setup
        final TicketTypeDTO params = new TicketTypeDTO();
        params.setTicketTypeId(0L);
        params.setName("name");
        params.setCode("code");
        params.setDescription("description");
        params.setParentId("parentId");
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new Date(0L));
        params.setUpdateUser("updateUser");
        params.setUpdateDate(new Date(0L));

        // Configure TicketCategoryRepository.getTicketTypes(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(params));
        resultSelectEntity.setCount(1);
        when(mockTicketCategoryRepository.getTicketTypes(any(), any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketTypes(params, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketSiteByParentId() {
        // Setup
        final TicketSiteDTO params = new TicketSiteDTO();
        params.setSiteId(0L);
        params.setSiteCode("siteCode");
        params.setSiteName("siteName");
        params.setParentId(0L);
        params.setAddress("address");
        params.setUsername("username");
        params.setPassword("password");
        params.setEmail("email");
        params.setPhone("phone");
        params.setStatus(0L);

        // Configure TicketCategoryRepository.getTicketSiteByParentId(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(params));
        resultSelectEntity.setCount(1);
        when(mockTicketCategoryRepository.getTicketSiteByParentId(any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketSiteByParentId(params);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketSla() {
        // Setup
        final TicketSLADTO params = new TicketSLADTO();
        params.setSlaName("slaName");
        params.setSla(1L);
        params.setDescription("desciption");
        params.setStatus(0L);
        params.setSiteId(0L);
        params.setSourceId(0L);
        params.setTicketTypeId(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new Date(0L));
        params.setUpdateUser("updateUser");

        // Configure TicketCategoryRepository.getTicketSla(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(params));
        resultSelectEntity.setCount(1);
        when(mockTicketCategoryRepository.getTicketSla(any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketSla(params);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketSlaDetail() {
        // Setup
        final TicketSLADTO params = new TicketSLADTO();
        params.setSlaName("slaName");
        params.setSla(1L);
        params.setDescription("desciption");
        params.setStatus(0L);
        params.setSiteId(0L);
        params.setSourceId(0L);
        params.setTicketTypeId(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new Date(0L));
        params.setUpdateUser("updateUser");

        // Configure TicketSlaRepositoryJPA.findBySiteIdAndAndSourceIdAndAndTicketTypeIdAndStatus(...).
        final TicketSlaEntity ticketSlaEntity1 = new TicketSlaEntity();
        ticketSlaEntity1.setTicketSlaId(0L);
        ticketSlaEntity1.setSlaName("slaName");
        ticketSlaEntity1.setSla(1L);
        ticketSlaEntity1.setDescription("description");
        ticketSlaEntity1.setStatus(0L);
        ticketSlaEntity1.setSiteId(0L);
        ticketSlaEntity1.setSourceId(0L);
        ticketSlaEntity1.setTicketTypeId(0L);
        ticketSlaEntity1.setCreateUser("createUser");
        ticketSlaEntity1.setCreateDate(new Date(0L));
        final Optional<TicketSlaEntity> ticketSlaEntity = Optional.of(ticketSlaEntity1);
        when(mockTicketSlaRepositoryJPA.findBySiteIdAndAndSourceIdAndAndTicketTypeIdAndStatus(0L, 0L, 0L, 1L)).thenReturn(ticketSlaEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketSlaDetail(params);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketSlaDetail_TicketSlaRepositoryJPAReturnsAbsent() {
        // Setup
        final TicketSLADTO params = new TicketSLADTO();
        params.setSlaName("slaName");
        params.setSla(1L);
        params.setDescription("desciption");
        params.setStatus(0L);
        params.setSiteId(0L);
        params.setSourceId(0L);
        params.setTicketTypeId(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new Date(0L));
        params.setUpdateUser("updateUser");

        when(mockTicketSlaRepositoryJPA.findBySiteIdAndAndSourceIdAndAndTicketTypeIdAndStatus(0L, 0L, 0L, 1L)).thenReturn(Optional.empty());

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketSlaDetail(params);

        // Verify the results
        Assertions.assertNull(result);
    }

    @Test
    void testGetDateTicketSlaNew() {
        // Setup
        // Configure TicketSlaRepositoryJPA.findByPriorityIdAndAndTicketTypeIdAndStatus(...).
        final TicketSlaEntity ticketSlaEntity1 = new TicketSlaEntity();
        ticketSlaEntity1.setTicketSlaId(0L);
        ticketSlaEntity1.setSlaName("slaName");
        ticketSlaEntity1.setSla(0L);
        ticketSlaEntity1.setDescription("description");
        ticketSlaEntity1.setStatus(1L);
        ticketSlaEntity1.setSiteId(0L);
        ticketSlaEntity1.setSourceId(0L);
        ticketSlaEntity1.setTicketTypeId(0L);
        ticketSlaEntity1.setCreateUser("createUser");
        ticketSlaEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketSlaEntity> ticketSlaEntity = Optional.of(ticketSlaEntity1);
        when(ticketCategoryServiceImplUnderTest.ticketSlaRepositoryJPA.findByPriorityIdAndAndTicketTypeIdAndStatus(0L, 0L, 1L)).thenReturn(ticketSlaEntity);

        // Run the test
        final java.util.Date result = ticketCategoryServiceImplUnderTest.getDateTicketSlaNew(0L, 0L, null, 0L);

        // Verify the results
    }

    @Test
    void testGetTicketExtentReason() {
        // Setup
        final TicketExtentReasonDTO params = new TicketExtentReasonDTO();
        params.setTicketExtentReasonId(0L);
        params.setName("name");
        params.setDescription("description");
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setUpdateUser("updateUser");
        params.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setStartrecord(0);
        params.setPagesize(0);

        final Authentication authentication = null;

        // Configure TicketCategoryRepository.getTicketExtentReason(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        Mockito.lenient().when(ticketCategoryServiceImplUnderTest.ticketCategoryRepository.getTicketExtentReason(new TicketExtentReasonDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketExtentReason(params, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketSiteUser() {
        // Setup
        final TicketSiteUserDTO params = new TicketSiteUserDTO();
        params.setTicketSiteUserId(0L);
        params.setSiteId(0L);
        params.setSiteName("siteName");
        params.setUserId("userId");
        params.setUserName("userName");
        params.setStaffCode("staffCode");
        params.setStaffName("staffName");
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Authentication authentication = null;

        // Configure TicketCategoryRepository.getTicketSiteUser(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        Mockito.lenient().when(ticketCategoryServiceImplUnderTest.ticketCategoryRepository.getTicketSiteUser(new TicketSiteUserDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketSiteUser(params, authentication);

        // Verify the results
    }

    @Test
    void testGetTicketTypesTree() {
        // Setup
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final TicketTypeDTO params = new TicketTypeDTO(ticketTypeEntity);

        // Configure TicketCategoryRepository.getTicketTypesTree(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        Mockito.lenient().when(ticketCategoryServiceImplUnderTest.ticketCategoryRepository.getTicketTypesTree(new TicketTypeDTO(new TicketTypeEntity()))).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.getTicketTypesTree(params);

        // Verify the results
    }

    @Test
    void testSaveTicketSla() {
        // Setup
        final TicketSLADTO ticketSLADTO = new TicketSLADTO(0L, "slaName", 0L, "description", 0L, 0L, 0L, 0L, 0L, "createUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "updateUser", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, 0, false);
        final Authentication authentication = null;

        // Configure TicketSlaRepositoryJPA.save(...).
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Mockito.lenient().when(ticketCategoryServiceImplUnderTest.ticketSlaRepositoryJPA.save(new TicketSlaEntity())).thenReturn(ticketSlaEntity);

        // Run the test
        final Object result = ticketCategoryServiceImplUnderTest.saveTicketSla(ticketSLADTO, authentication);

        // Verify the results
    }

    @Test
    void testGetDateTicketSlaNew_TicketSlaRepositoryJPAReturnsAbsent() {
        // Setup
        Mockito.lenient().when(ticketCategoryServiceImplUnderTest.ticketSlaRepositoryJPA.findByPriorityIdAndAndTicketTypeIdAndStatus(0L, 0L, 1L)).thenReturn(Optional.empty());

        // Run the test
        final java.util.Date result = ticketCategoryServiceImplUnderTest.getDateTicketSlaNew(0L, 0L, null, 0L);

        // Verify the results
    }
}
