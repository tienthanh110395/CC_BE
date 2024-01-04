package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketSLADTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

class TicketSlaRepositoryImplTest {

    private TicketSlaRepositoryImpl ticketSlaRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSlaRepositoryImplUnderTest = new TicketSlaRepositoryImpl();
    }

    @Test
    void testGetTicketSlaDetail() {
        // Setup
        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketSlaRepositoryImplUnderTest.getTicketSlaDetail(0L);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
