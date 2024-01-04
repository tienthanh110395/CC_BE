package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketStatisticDTO;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class TicketStatisticRepositoryImplTest {

    private TicketStatisticRepositoryImpl ticketStatisticRepositoryImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketStatisticRepositoryImplUnderTest = new TicketStatisticRepositoryImpl();
    }

    @Test
    void testGetTicketStatistic() {
        // Setup
        final TicketStatisticDTO itemParamsEntity = new TicketStatisticDTO();
        itemParamsEntity.setStatisticId(0L);
        itemParamsEntity.setContractNoUserName("contractNoUserName");
        itemParamsEntity.setPlateNumber("plateNumber");
        itemParamsEntity.setSystemPhoneNumber("systemPhoneNumber");
        itemParamsEntity.setCallPhoneNumber("callPhoneNumber");
        itemParamsEntity.setSourceId(0L);
        itemParamsEntity.setL1StatisticTypeId(0L);
        itemParamsEntity.setL2StatisticTypeId(0L);
        itemParamsEntity.setL3StatisticTypeId(0L);
        itemParamsEntity.setL4StatisticTypeId(0L);

        new MockUp<CommonDataBaseRepository>() {
            @mockit.Mock
            public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
                return new ResultSelectEntity();
            }
        };

        // Run the test
        final ResultSelectEntity result = ticketStatisticRepositoryImplUnderTest.getTicketStatistic(itemParamsEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }
}
