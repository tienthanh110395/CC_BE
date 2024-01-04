package com.viettel.etc.services.impl;

import com.viettel.etc.dto.TicketErrorCauseDTO;
import com.viettel.etc.repositories.TicketErrorCauseRepository;
import com.viettel.etc.repositories.tables.TicketErrorCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import com.viettel.etc.services.tables.TicketErrorCauseServiceJPA;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class TicketErrorCauseServiceImplTest {

    private TicketErrorCauseServiceImpl ticketErrorCauseServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketErrorCauseServiceImplUnderTest = new TicketErrorCauseServiceImpl();
        ticketErrorCauseServiceImplUnderTest.ticketErrorCauseRepositoryJPA = mock(TicketErrorCauseRepositoryJPA.class);
        ticketErrorCauseServiceImplUnderTest.ticketErrorCauseRepository = mock(TicketErrorCauseRepository.class);
        ticketErrorCauseServiceImplUnderTest.ticketErrorCauseServiceJPA = mock(TicketErrorCauseServiceJPA.class);
    }

    @Test
    void testGetTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO itemParamsEntity = new TicketErrorCauseDTO();
        itemParamsEntity.setTicketErrorCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure TicketErrorCauseRepositoryJPA.findAllByParentIdAndStatusOrderByName(...).
        final TicketErrorCauseEntity ticketErrorCauseEntity = new TicketErrorCauseEntity();
        ticketErrorCauseEntity.setTicketErrorCauseId(0L);
        ticketErrorCauseEntity.setName("name");
        ticketErrorCauseEntity.setCode("code");
        ticketErrorCauseEntity.setDescription("description");
        ticketErrorCauseEntity.setParentId(0L);
        ticketErrorCauseEntity.setStatus(0L);
        ticketErrorCauseEntity.setCreateUser("createUser");
        ticketErrorCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketErrorCauseEntity.setUpdateUser("updateUser");
        ticketErrorCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketErrorCauseEntity> ticketErrorCauseEntities = Arrays.asList(ticketErrorCauseEntity);
        when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseRepositoryJPA.findAllByParentIdAndStatusOrderByName(0L, 0L)).thenReturn(ticketErrorCauseEntities);

        // Run the test
        final Object result = ticketErrorCauseServiceImplUnderTest.getTicketErrorCause(itemParamsEntity);

        // Verify the results
    }

    @Test
    void testGetTicketErrorCause_TicketErrorCauseRepositoryJPAReturnsNoItems() {
        // Setup
        final TicketErrorCauseDTO itemParamsEntity = new TicketErrorCauseDTO();
        itemParamsEntity.setTicketErrorCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseRepositoryJPA.findAllByParentIdAndStatusOrderByName(0L, 0L)).thenReturn(null);

        // Run the test
        final Object result = ticketErrorCauseServiceImplUnderTest.getTicketErrorCause(itemParamsEntity);

        // Verify the results
    }

    @Test
    void testSearchTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO itemParamsEntity = new TicketErrorCauseDTO();
        itemParamsEntity.setTicketErrorCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure TicketErrorCauseRepository.searchTicketErrorCause(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseRepository.searchTicketErrorCause(new TicketErrorCauseDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketErrorCauseServiceImplUnderTest.searchTicketErrorCause(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testSearchTreeTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO itemParamsEntity = new TicketErrorCauseDTO();
        itemParamsEntity.setTicketErrorCauseId(0L);
        itemParamsEntity.setName("name");
        itemParamsEntity.setCode("code");
        itemParamsEntity.setDescription("description");
        itemParamsEntity.setParentId(0L);
        itemParamsEntity.setStatus(0L);
        itemParamsEntity.setCreateUser("createUser");
        itemParamsEntity.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        itemParamsEntity.setUpdateUser("updateUser");
        itemParamsEntity.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure TicketErrorCauseRepository.searchTreeTicketErrorCause(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList("value"));
        resultSelectEntity.setCount("count");
        when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseRepository.searchTreeTicketErrorCause(new TicketErrorCauseDTO())).thenReturn(resultSelectEntity);

        // Run the test
        final Object result = ticketErrorCauseServiceImplUnderTest.searchTreeTicketErrorCause(itemParamsEntity, null);

        // Verify the results
    }

    @Test
    void testCreateTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO dataParams = new TicketErrorCauseDTO();
        dataParams.setTicketErrorCauseId(0L);
        dataParams.setName("name");
        dataParams.setCode("code");
        dataParams.setDescription("description");
        dataParams.setParentId(0L);
        dataParams.setStatus(0L);
        dataParams.setCreateUser("createUser");
        dataParams.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dataParams.setUpdateUser("updateUser");
        dataParams.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Authentication authentication = null;

        // Configure TicketErrorCauseServiceJPA.save(...).
        final TicketErrorCauseEntity ticketErrorCauseEntity = new TicketErrorCauseEntity();
        ticketErrorCauseEntity.setTicketErrorCauseId(0L);
        ticketErrorCauseEntity.setName("name");
        ticketErrorCauseEntity.setCode("code");
        ticketErrorCauseEntity.setDescription("description");
        ticketErrorCauseEntity.setParentId(0L);
        ticketErrorCauseEntity.setStatus(0L);
        ticketErrorCauseEntity.setCreateUser("createUser");
        ticketErrorCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketErrorCauseEntity.setUpdateUser("updateUser");
        ticketErrorCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseServiceJPA.save(new TicketErrorCauseEntity())).thenReturn(ticketErrorCauseEntity);

        // Run the test
        final Object result = ticketErrorCauseServiceImplUnderTest.createTicketErrorCause(dataParams, authentication);

        // Verify the results
    }

    @Test
    void testUpdateTicketErrorCause() {
        // Setup
        final TicketErrorCauseDTO dataParams = new TicketErrorCauseDTO();
        dataParams.setTicketErrorCauseId(0L);
        dataParams.setName("name");
        dataParams.setCode("code");
        dataParams.setDescription("description");
        dataParams.setParentId(0L);
        dataParams.setStatus(0L);
        dataParams.setCreateUser("createUser");
        dataParams.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dataParams.setUpdateUser("updateUser");
        dataParams.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Authentication authentication = null;

        // Configure TicketErrorCauseServiceJPA.getOne(...).
        final TicketErrorCauseEntity ticketErrorCauseEntity = new TicketErrorCauseEntity();
        ticketErrorCauseEntity.setTicketErrorCauseId(0L);
        ticketErrorCauseEntity.setName("name");
        ticketErrorCauseEntity.setCode("code");
        ticketErrorCauseEntity.setDescription("description");
        ticketErrorCauseEntity.setParentId(0L);
        ticketErrorCauseEntity.setStatus(0L);
        ticketErrorCauseEntity.setCreateUser("createUser");
        ticketErrorCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketErrorCauseEntity.setUpdateUser("updateUser");
        ticketErrorCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseServiceJPA.getOne(0L)).thenReturn(ticketErrorCauseEntity);

        // Configure TicketErrorCauseServiceJPA.save(...).
        final TicketErrorCauseEntity ticketErrorCauseEntity1 = new TicketErrorCauseEntity();
        ticketErrorCauseEntity1.setTicketErrorCauseId(0L);
        ticketErrorCauseEntity1.setName("name");
        ticketErrorCauseEntity1.setCode("code");
        ticketErrorCauseEntity1.setDescription("description");
        ticketErrorCauseEntity1.setParentId(0L);
        ticketErrorCauseEntity1.setStatus(0L);
        ticketErrorCauseEntity1.setCreateUser("createUser");
        ticketErrorCauseEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketErrorCauseEntity1.setUpdateUser("updateUser");
        ticketErrorCauseEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseServiceJPA.save(new TicketErrorCauseEntity())).thenReturn(ticketErrorCauseEntity1);

        // Run the test
        final Object result = ticketErrorCauseServiceImplUnderTest.updateTicketErrorCause(dataParams, 0L, authentication);

        // Verify the results
    }

    @Test
    void testRemoveTicketErrorCause() {
        // Setup
        // Run the test
        final Boolean result = ticketErrorCauseServiceImplUnderTest.removeTicketErrorCause(0L, null);

        // Verify the results
        assertThat(result).isTrue();
        verify(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseRepositoryJPA).deleteById(0L);
    }

    @Test
    void testUpdateTicketErrorCause_ThrowsEtcException() {
        // Setup
        final TicketErrorCauseDTO dataParams = new TicketErrorCauseDTO();
        dataParams.setTicketErrorCauseId(0L);
        dataParams.setName("name");
        dataParams.setCode("code");
        dataParams.setDescription("description");
        dataParams.setParentId(0L);
        dataParams.setStatus(0L);
        dataParams.setCreateUser("createUser");
        dataParams.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dataParams.setUpdateUser("updateUser");
        dataParams.setUpdateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Authentication authentication = null;

        // Configure TicketErrorCauseServiceJPA.getOne(...).
        final TicketErrorCauseEntity ticketErrorCauseEntity = new TicketErrorCauseEntity();
        ticketErrorCauseEntity.setTicketErrorCauseId(0L);
        ticketErrorCauseEntity.setName("name");
        ticketErrorCauseEntity.setCode("code");
        ticketErrorCauseEntity.setDescription("description");
        ticketErrorCauseEntity.setParentId(0L);
        ticketErrorCauseEntity.setStatus(0L);
        ticketErrorCauseEntity.setCreateUser("createUser");
        ticketErrorCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketErrorCauseEntity.setUpdateUser("updateUser");
        ticketErrorCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Mockito.lenient().when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseServiceJPA.getOne(0L)).thenReturn(ticketErrorCauseEntity);

        // Configure TicketErrorCauseServiceJPA.save(...).
        final TicketErrorCauseEntity ticketErrorCauseEntity1 = new TicketErrorCauseEntity();
        ticketErrorCauseEntity1.setTicketErrorCauseId(0L);
        ticketErrorCauseEntity1.setName("name");
        ticketErrorCauseEntity1.setCode("code");
        ticketErrorCauseEntity1.setDescription("description");
        ticketErrorCauseEntity1.setParentId(0L);
        ticketErrorCauseEntity1.setStatus(0L);
        ticketErrorCauseEntity1.setCreateUser("createUser");
        ticketErrorCauseEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketErrorCauseEntity1.setUpdateUser("updateUser");
        ticketErrorCauseEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Mockito.lenient().when(ticketErrorCauseServiceImplUnderTest.ticketErrorCauseServiceJPA.save(new TicketErrorCauseEntity())).thenReturn(ticketErrorCauseEntity1);

        // Run the test
    }
}
