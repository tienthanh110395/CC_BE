package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

class TicketStatisticTypeRepositoryImplTest {

    private TicketStatisticTypeRepositoryImpl ticketStatisticTypeRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketStatisticTypeRepositoryImplUnderTest = new TicketStatisticTypeRepositoryImpl();
    }

    @Test
    void testGetTicketStatisticType() {
        // Setup
        final StatisticTypeEntity statisticTypeEntity = new StatisticTypeEntity();
        statisticTypeEntity.setStatisticTypeId(0L);
        statisticTypeEntity.setName("name");
        statisticTypeEntity.setCode("code");
        statisticTypeEntity.setDescription("description");
        statisticTypeEntity.setParentId(1L);
        statisticTypeEntity.setStatus(0L);
        statisticTypeEntity.setCreateUser("createUser");
        statisticTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        statisticTypeEntity.setUpdateUser("updateUser");
        statisticTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final TicketStatisticTypeDTO itemParamsEntity = new TicketStatisticTypeDTO(statisticTypeEntity);

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketStatisticTypeRepositoryImplUnderTest.getTicketStatisticType(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
