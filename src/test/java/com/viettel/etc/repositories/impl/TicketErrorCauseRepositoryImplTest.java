package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketErrorCauseDTO;
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

class TicketErrorCauseRepositoryImplTest {

    private TicketErrorCauseRepositoryImpl ticketErrorCauseRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketErrorCauseRepositoryImplUnderTest = new TicketErrorCauseRepositoryImpl();
    }

    @Test
    void testGetTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO itemParamsEntity = new TicketErrorCauseDTO();
        itemParamsEntity.setTicketErrorCauseId(0L);
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
        final ResultSelectEntity result = ticketErrorCauseRepositoryImplUnderTest.searchTicketErrorCause(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testSearchTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO itemParamsEntity = new TicketErrorCauseDTO();
        itemParamsEntity.setTicketErrorCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };
        // Run the test
        final ResultSelectEntity result = ticketErrorCauseRepositoryImplUnderTest.searchTicketErrorCause(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testSearchTreeTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO itemParamsEntity = new TicketErrorCauseDTO();
        itemParamsEntity.setTicketErrorCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketErrorCauseRepositoryImplUnderTest.searchTreeTicketErrorCause(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
