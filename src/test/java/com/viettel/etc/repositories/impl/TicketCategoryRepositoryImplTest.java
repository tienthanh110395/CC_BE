package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class TicketCategoryRepositoryImplTest {
    @InjectMocks
    private TicketCategoryRepositoryImpl ticketCategoryRepositoryImplUnderTest;


    @Test
    void testGetTicketTypes() {
        // Setup
        final TicketTypeDTO dataParams = new TicketTypeDTO();
        dataParams.setTicketTypeId(0L);
        dataParams.setName("name");
        dataParams.setCode("code");
        dataParams.setDescription("description");
        dataParams.setParentId("1");
        dataParams.setStatus(0L);
        dataParams.setCreateUser("createUser");
        dataParams.setCreateDate(new Date(0L));
        dataParams.setUpdateUser("updateUser");
        dataParams.setUpdateDate(new Date(0L));



        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketCategoryRepositoryImplUnderTest.getTicketTypes(dataParams, null));
        dataParams.setParentId(null);
        Assertions.assertNull(ticketCategoryRepositoryImplUnderTest.getTicketTypes(dataParams, null));
    }

    @Test
    void testGetTicketSiteByParentId() {
        // Setup
        final TicketSiteDTO dataParams = new TicketSiteDTO();
        dataParams.setSiteId(0L);
        dataParams.setSiteCode("siteCode");
        dataParams.setSiteName("siteName");
        dataParams.setParentId(0L);
        dataParams.setAddress("address");
        dataParams.setUsername("username");
        dataParams.setPassword("password");
        dataParams.setEmail("email");
        dataParams.setPhone("phone");
        dataParams.setStatus(0L);



        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketCategoryRepositoryImplUnderTest.getTicketSiteByParentId(dataParams));
        dataParams.setParentId(null);
        Assertions.assertNull(ticketCategoryRepositoryImplUnderTest.getTicketSiteByParentId(dataParams));
    }

    @Test
    void testGetTicketSla() {
        // Setup
        final TicketSLADTO itemParamsEntity = new TicketSLADTO();
        itemParamsEntity.setSlaName("slaName");
        itemParamsEntity.setSla(1L);
        itemParamsEntity.setDescription("desciption");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setSourceId(0L);
        itemParamsEntity.setTicketTypeId(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new Date(0L));
        itemParamsEntity.setUpdateUser("updateUser");



        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketCategoryRepositoryImplUnderTest.getTicketSla(itemParamsEntity));
    }
    @Test
    void testGetTicketSource() {
        TicketSourceDTO ticketSourceDTO = new TicketSourceDTO();
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };
        // Verify the results
        Assertions.assertNull(ticketCategoryRepositoryImplUnderTest.getTicketSource(ticketSourceDTO));
    }

    @Test
    void testGetTicketTypesTree() {
        // Setup
        final TicketTypeDTO itemParamsEntity = new TicketTypeDTO();
        itemParamsEntity.setTicketTypeId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId("parentId");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketCategoryRepositoryImplUnderTest.getTicketTypesTree(itemParamsEntity);

        // Verify the results
        Assertions.assertNull(result);
    }

    @Test
    void testGetTicketExtentReason() {
        // Setup
        final TicketExtentReasonDTO itemParamsEntity = new TicketExtentReasonDTO();
        itemParamsEntity.setTicketExtentReasonId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setStartrecord(0);
        itemParamsEntity.setPagesize(0);
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };
        // Run the test
        final ResultSelectEntity result = ticketCategoryRepositoryImplUnderTest.getTicketExtentReason(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketSiteUser() {
        // Setup
        final TicketSiteUserDTO itemParamsEntity = new TicketSiteUserDTO();
        itemParamsEntity.setTicketSiteUserId(0L);
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setSiteName("siteName");
        itemParamsEntity.setUserId("userId");
        itemParamsEntity.setUserName("userName");
        itemParamsEntity.setStaffCode("staffCode");
        itemParamsEntity.setStaffName("staffName");
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };
        // Run the test
        final ResultSelectEntity result = ticketCategoryRepositoryImplUnderTest.getTicketSiteUser(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
