package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketProcessShareDetailDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class TicketProcessShareDetailRepositoryImplTest {

    private TicketProcessShareDetailRepositoryImpl ticketProcessShareDetailRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareDetailRepositoryImplUnderTest = new TicketProcessShareDetailRepositoryImpl();
    }

    @Test
    void testGetTicketProcessShareDetail() {
        // Setup
        final TicketProcessShareDetailDTO itemParamsEntity = new TicketProcessShareDetailDTO();
        itemParamsEntity.setTicketProcessShareDetailId(0L);
        itemParamsEntity.setTicketProcessShareId(0L);
        itemParamsEntity.setAssignTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setProcessUser("processUser");
        itemParamsEntity.setStartrecord(0);
        itemParamsEntity.setPagesize(0);

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, List<Object> arrParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketProcessShareDetailRepositoryImplUnderTest.getTicketProcessShareDetail(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
