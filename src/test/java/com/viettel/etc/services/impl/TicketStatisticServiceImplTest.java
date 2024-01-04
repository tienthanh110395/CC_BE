package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketStatisticDTO;
import com.viettel.etc.repositories.TicketStatisticRepository;
import com.viettel.etc.repositories.tables.entities.StatisticEntity;
import com.viettel.etc.services.tables.StatisticServiceJPA;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketStatisticServiceImplTest {

    @Mock
    private TicketStatisticRepository mockTicketStatisticRepository;
    @Mock
    private StatisticServiceJPA mockStatisticServiceJPA;

    @InjectMocks
    private TicketStatisticServiceImpl ticketStatisticServiceImplUnderTest;

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

        final Authentication authentication = null;

        // Configure TicketStatisticRepository.getTicketStatistic(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketStatisticRepository.getTicketStatistic(new TicketStatisticDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketStatisticServiceImplUnderTest.getTicketStatistic(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testExportTicketStatistic() {
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

        final Authentication authentication = null;

        // Configure TicketStatisticRepository.getTicketStatistic(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketStatisticRepository.getTicketStatistic(new TicketStatisticDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketStatisticServiceImplUnderTest.exportTicketStatistic(itemParamsEntity, authentication);

        // Verify the results
    }

    @Test
    void testInsertTicketStatistic() {
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

        // Configure StatisticServiceJPA.save(...).
        final StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticId(0L);
        statisticEntity.setContractNoUserName("contractNoUserName");
        statisticEntity.setPlateNumber("plateNumber");
        statisticEntity.setSystemPhoneNumber("systemPhoneNumber");
        statisticEntity.setCallPhoneNumber("callPhoneNumber");
        statisticEntity.setSourceId(0L);
        statisticEntity.setL1StatisticTypeId(0L);
        statisticEntity.setL2StatisticTypeId(0L);
        statisticEntity.setL3StatisticTypeId(0L);
        statisticEntity.setL4StatisticTypeId(0L);
        when(mockStatisticServiceJPA.save(new StatisticEntity())).thenReturn(statisticEntity);

        // Run the test
        final Object result = ticketStatisticServiceImplUnderTest.insertTicketStatistic(itemParamsEntity, null);

        // Verify the results
    }
}
