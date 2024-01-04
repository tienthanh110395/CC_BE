package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketAssignLogDTO;
import com.viettel.etc.repositories.TicketAssignLogRepository;
import com.viettel.etc.repositories.tables.TicketAssignLogRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignLogEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.Assertions;
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
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketAssignLogServiceImplTest {

    @Mock
    private TicketAssignLogRepository mockTicketAssignLogRepository;
    @Mock
    private TicketAssignLogRepositoryJPA mockTicketAssignLogRepositoryJPA;

    @InjectMocks
    private TicketAssignLogServiceImpl ticketAssignLogServiceImplUnderTest;

    @Test
    void testGetAssignLogByTicketId() {
        // Setup
        final TicketAssignLogDTO itemParamsEntity = new TicketAssignLogDTO();
        itemParamsEntity.setTicketAssignLogId(0L);
        itemParamsEntity.setTicketAssignId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setLogContent("logContent");
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setLogType(0L);

        // Configure TicketAssignLogRepository.getAssignLogByTicketId(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(mockTicketAssignLogRepository.getTicketAssignLog(any(),any())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketAssignLogServiceImplUnderTest.getTicketAssignLog(itemParamsEntity, null);

        Assertions.assertNotNull(result);
    }

    @Test
    void testAddNewAssignLog() {
        // Setup
        final TicketAssignLogDTO itemParamsEntity = new TicketAssignLogDTO();
        itemParamsEntity.setTicketAssignLogId(0L);
        itemParamsEntity.setTicketAssignId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setLogContent("logContent");
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setLogType(0L);

        // Configure TicketAssignLogRepositoryJPA.save(...).
        final TicketAssignLogEntity ticketAssignLogEntity = new TicketAssignLogEntity();
        ticketAssignLogEntity.setTicketAssignLogId(0L);
        ticketAssignLogEntity.setTicketAssignId(0L);
        ticketAssignLogEntity.setTicketId(0L);
        ticketAssignLogEntity.setLogContent("logContent");
        ticketAssignLogEntity.setSiteId(0L);
        ticketAssignLogEntity.setCreateUser("createUser");
        ticketAssignLogEntity.setCreateDate(new Date(0L));
        ticketAssignLogEntity.setUpdateUser("updateUser");
        ticketAssignLogEntity.setUpdateDate(new Date(0L));
        ticketAssignLogEntity.setLogType(0L);
        when(mockTicketAssignLogRepositoryJPA.save(any())).thenReturn(ticketAssignLogEntity);

        // Run the test
        final Object result = ticketAssignLogServiceImplUnderTest.saveTicketAssignLog(itemParamsEntity,any());

        // Verify the results
    }

    @Test
    void testRemoveAssignLog() {
        // Setup

        // Run the test
        final Object result = ticketAssignLogServiceImplUnderTest.removeTicketAssignLog(0L, null);

        // Verify the results
        verify(mockTicketAssignLogRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetTicketAssignLog() {
        // Setup
        final TicketAssignLogDTO itemParamsEntity = new TicketAssignLogDTO();
        itemParamsEntity.setTicketAssignLogId(0L);
        itemParamsEntity.setTicketAssignId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setLogContent("logContent");
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setLogType(0L);

        // Configure TicketAssignLogRepository.getTicketAssignLog(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        when(ticketAssignLogServiceImplUnderTest.ticketAssignLogRepository.getTicketAssignLog(new TicketAssignLogDTO(), null)).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketAssignLogServiceImplUnderTest.getTicketAssignLog(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testRemoveTicketAssignLog() {
        // Setup
        // Run the test
        final Object result = ticketAssignLogServiceImplUnderTest.removeTicketAssignLog(0L, null);

        // Verify the results
        verify(ticketAssignLogServiceImplUnderTest.ticketAssignLogRepositoryJPA).deleteById(0L);
    }

    @Test
    void testSaveTicketAssignLog() {
        // Setup
        final TicketAssignLogDTO itemParamsEntity = new TicketAssignLogDTO();
        itemParamsEntity.setTicketAssignLogId(0L);
        itemParamsEntity.setTicketAssignId(0L);
        itemParamsEntity.setTicketId(0L);
        itemParamsEntity.setLogContent("logContent");
        itemParamsEntity.setSiteId(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setLogType(0L);

        final Authentication authentication = null;

        // Configure TicketAssignLogRepositoryJPA.save(...).
        final TicketAssignLogEntity ticketAssignLogEntity = new TicketAssignLogEntity();
        ticketAssignLogEntity.setTicketAssignLogId(0L);
        ticketAssignLogEntity.setTicketAssignId(0L);
        ticketAssignLogEntity.setTicketId(0L);
        ticketAssignLogEntity.setLogContent("logContent");
        ticketAssignLogEntity.setSiteId(0L);
        ticketAssignLogEntity.setCreateUser("createUser");
        ticketAssignLogEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity.setUpdateUser("updateUser");
        ticketAssignLogEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity.setLogType(0L);
        when(ticketAssignLogServiceImplUnderTest.ticketAssignLogRepositoryJPA.save(new TicketAssignLogEntity())).thenReturn(ticketAssignLogEntity);

        // Run the test
        final Object result = ticketAssignLogServiceImplUnderTest.saveTicketAssignLog(itemParamsEntity, authentication);

        // Verify the results
    }
}
