package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketExpireCauseDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

class TicketExpireCauseRepositoryImplTest {

    private TicketExpireCauseRepositoryImpl ticketExpireCauseRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExpireCauseRepositoryImplUnderTest = new TicketExpireCauseRepositoryImpl();
    }

    @Test
    void testSearchTicketExpireCause() {
        // Setup
        final TicketExpireCauseDTO itemParamsEntity = new TicketExpireCauseDTO();
        itemParamsEntity.setTicketExpireCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new java.util.Date());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new java.util.Date());
        itemParamsEntity.setPagesize(1);
        itemParamsEntity.setStartrecord(1);
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };
        // Run the test
        final ResultSelectEntity result = ticketExpireCauseRepositoryImplUnderTest.searchTicketExpireCause(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testSearchTreeTicketExpireCause() {
        // Setup
        final TicketExpireCauseDTO itemParamsEntity = new TicketExpireCauseDTO();
        itemParamsEntity.setTicketExpireCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };
        // Run the test
        final ResultSelectEntity result = ticketExpireCauseRepositoryImplUnderTest.searchTreeTicketExpireCause(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
