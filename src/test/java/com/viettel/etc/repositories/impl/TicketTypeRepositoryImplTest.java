package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

class TicketTypeRepositoryImplTest {

    private TicketTypeRepositoryImpl ticketTypeRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketTypeRepositoryImplUnderTest = new TicketTypeRepositoryImpl();
    }

    @Test
    void testGetTicketType() {
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
        final TicketTypeDTO itemParamsEntity = new TicketTypeDTO(ticketTypeEntity);
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketTypeRepositoryImplUnderTest.getTicketType(itemParamsEntity, null);

        // Verify the results
    }
}
