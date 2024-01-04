package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketProcessShareDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class TicketProcessShareRepositoryImplTest {

    private TicketProcessShareRepositoryImpl ticketProcessShareRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareRepositoryImplUnderTest = new TicketProcessShareRepositoryImpl();
    }

    @Test
    void testGetTicketProcessShare() {
        // Setup
        final TicketProcessShareDTO itemParamsEntity = new TicketProcessShareDTO();
        itemParamsEntity.setTicketProcessShareId(0L);
        itemParamsEntity.setAssignTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setAssignUser("assignUser");
        itemParamsEntity.setAssignType(0L);
        itemParamsEntity.setTicketIds(Arrays.asList(0L));
        itemParamsEntity.setProcessUsers(Arrays.asList("value"));
        itemParamsEntity.setIsSms(false);
        itemParamsEntity.setStartrecord(0);
        itemParamsEntity.setPagesize(0);
        itemParamsEntity.setActTypeId(0L);

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, List<Object> arrParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketProcessShareRepositoryImplUnderTest.getTicketProcessShare(itemParamsEntity);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
