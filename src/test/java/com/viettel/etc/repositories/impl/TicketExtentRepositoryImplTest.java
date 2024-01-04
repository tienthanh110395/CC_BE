package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketExtentDTO;
import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
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

class TicketExtentRepositoryImplTest {

    private TicketExtentRepositoryImpl ticketExtentRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExtentRepositoryImplUnderTest = new TicketExtentRepositoryImpl();
    }

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
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };
        // Run the test
        final ResultSelectEntity result = ticketExtentRepositoryImplUnderTest.getTicketExtent(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
