package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.repositories.TicketStatisticTypeRepository;
import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import com.viettel.etc.services.tables.StatisticTypeServiceJPA;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketStatisticTypeServiceImplTest {

    @Mock
    private TicketStatisticTypeRepository mockTicketStatisticTypeRepository;
    @Mock
    private StatisticTypeServiceJPA mockStatisticTypeServiceJPA;

    @InjectMocks
    private TicketStatisticTypeServiceImpl ticketStatisticTypeServiceImplUnderTest;

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

        // Configure TicketStatisticTypeRepository.getTicketStatisticType(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        when(mockTicketStatisticTypeRepository.getTicketStatisticType(new TicketStatisticTypeDTO(new StatisticTypeEntity()), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketStatisticTypeServiceImplUnderTest.getTicketStatisticType(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testGetTicketStatisticTypeDetail() {
        // Setup
        // Configure StatisticTypeServiceJPA.getOne(...).
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
        when(mockStatisticTypeServiceJPA.getOne(0L)).thenReturn(statisticTypeEntity);

        // Run the test
        final Object result = ticketStatisticTypeServiceImplUnderTest.getTicketStatisticTypeDetail(0L);

        // Verify the results
    }
}
