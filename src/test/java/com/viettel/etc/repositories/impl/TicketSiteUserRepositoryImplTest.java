package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

class TicketSiteUserRepositoryImplTest {

    private TicketSiteUserRepositoryImpl ticketSiteUserRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteUserRepositoryImplUnderTest = new TicketSiteUserRepositoryImpl();
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
        final ResultSelectEntity result = ticketSiteUserRepositoryImplUnderTest.getTicketSiteUser(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
