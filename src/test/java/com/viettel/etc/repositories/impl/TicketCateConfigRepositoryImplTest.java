package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.dto.TicketTypeLogDTO;
import com.viettel.etc.dto.TicketTypeLogDetailDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class TicketCateConfigRepositoryImplTest {

    private TicketCateConfigRepositoryImpl ticketCateConfigRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketCateConfigRepositoryImplUnderTest = new TicketCateConfigRepositoryImpl();
    }

    @Test
    void testSearchTicketType() {
        // Setup
        final TicketConfigSearchDTO params = new TicketConfigSearchDTO();
        params.setTicketTypeName("ticketTypeName");
        params.setTicketTypeCode("ticketTypeCode");
        params.setInEffect(false);
        params.setExpire(false);
        params.setFormDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setCreateUser("createUser");
        params.setLstTicketTypeGroupId(Arrays.asList(0L));
        params.setLstTicketTypeGenreId(Arrays.asList(0L));
        params.setLstTicketCategoryId(Arrays.asList(0L));
        params.setStartRecord(0);
        params.setPageSize(0);
        params.setTicketTypeLevel(0L);
        params.setStatus(Arrays.asList(0L));

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Verify the results
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.searchTicketType(null, params));
    }

    @Test
    void testGetDataDetail() {
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getFirstData(StringBuilder queryString, HashMap<String, Object> hmapParams, Class<?> classOfT){
                return null;
            }
        };
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.getDataDetail(0L, new TicketCateConfigDTO()));
    }

    @Test
    void testGetDataDetailImpact() {
        // Setup
        final TicketTypeLogDetailDTO params = new TicketTypeLogDetailDTO();
        params.setTicketTypeLogDetailId(0L);
        params.setTicketTypeLogId(0L);
        params.setTableId(0L);
        params.setTableName("tableName");
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setOldValue("oldValue");
        params.setNewValue("newValue");
        params.setColumnName("columnName");
        params.setStartRecord(0);
        params.setPageSize(0);

        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Verify the results
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.getDataDetailImpact(params));
    }

    @Test
    void testGetTicketTypeByParentId() {
        // Setup
        final TicketCateConfigDTO params = new TicketCateConfigDTO();
        params.setTicketTypeId(0L);
        params.setTicketTypeName("ticketTypeName");
        params.setTicketTypeCode("ticketTypeCode");
        params.setDescription("description");
        params.setParentId(0L);
        params.setLstParentId(Arrays.asList(0L));
        params.setStatus(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setUpdateUser("updateUser");
        params.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setIsCpt(0L);
        params.setType(0L);
        params.setTicketTemplate("ticketTemplate");
        params.setSearch(0L);
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Verify the results
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.getTicketTypeByParentId(params));
    }

    @Test
    void testExportImpactLog() {
        // Setup
        final TicketTypeLogDTO params = new TicketTypeLogDTO();
        params.setTicketTypeLogId(0L);
        params.setActType(0L);
        params.setTableName("tableName");
        params.setCreateUser("createUser");
        params.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setUpdateUser("updateUser");
        params.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setStartRecord(0);
        params.setPageSize(0);
        params.setFromDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setToDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        params.setCateType(0L);
        params.setImpactType(0L);
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Verify the results
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.exportImpactLog(params));
    }

    @Test
    void testGetTicketTypeByParentIdForMapping() {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("ticketTypeName");
        ticketCateConfigDTO.setTicketTypeCode("ticketTypeCode");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setUpdateUser("updateUser");
        ticketCateConfigDTO.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setIsCpt(0L);
        ticketCateConfigDTO.setType(0L);
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setSearch(0L);
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public List<TicketCateConfigDTO> getListData(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Verify the results
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.getTicketTypeByParentIdForMapping(0L));
    }

    @Test
    void testGetTicketTypeByParentIdForConfigTime() {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("ticketTypeName");
        ticketCateConfigDTO.setTicketTypeCode("ticketTypeCode");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setUpdateUser("updateUser");
        ticketCateConfigDTO.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setIsCpt(0L);
        ticketCateConfigDTO.setType(0L);
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setSearch(0L);
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public List<TicketCateConfigDTO> getListData(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Verify the results
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.getTicketTypeByParentIdForConfigTime(0L));
    }

    @Test
    void testGetTicketTypeByTicketTypeIdForConfigTime() {
        // Setup
        final TicketCateConfigDTO ticketCateConfigDTO = new TicketCateConfigDTO();
        ticketCateConfigDTO.setTicketTypeId(0L);
        ticketCateConfigDTO.setTicketTypeName("ticketTypeName");
        ticketCateConfigDTO.setTicketTypeCode("ticketTypeCode");
        ticketCateConfigDTO.setDescription("description");
        ticketCateConfigDTO.setParentId(0L);
        ticketCateConfigDTO.setLstParentId(Arrays.asList(0L));
        ticketCateConfigDTO.setStatus(0L);
        ticketCateConfigDTO.setCreateUser("createUser");
        ticketCateConfigDTO.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setUpdateUser("updateUser");
        ticketCateConfigDTO.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        ticketCateConfigDTO.setIsCpt(0L);
        ticketCateConfigDTO.setType(0L);
        ticketCateConfigDTO.setTicketTemplate("ticketTemplate");
        ticketCateConfigDTO.setSearch(0L);
        new MockUp<CommonDataBaseRepository>(){
            @mockit.Mock
            public List<TicketCateConfigDTO> getListData(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT){
                return null;
            }
        };

        // Verify the results
        Assertions.assertNull(ticketCateConfigRepositoryImplUnderTest.getTicketTypeByTicketTypeIdForConfigTime(0L));
    }
}
