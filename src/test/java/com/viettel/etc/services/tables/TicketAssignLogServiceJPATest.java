package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAssignLogRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignLogEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TicketAssignLogServiceJPATest {

    private TicketAssignLogServiceJPA ticketAssignLogServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        ticketAssignLogServiceJPAUnderTest = new TicketAssignLogServiceJPA();
        ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA = mock(TicketAssignLogRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
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
        final List<TicketAssignLogEntity> expectedResult = Arrays.asList(ticketAssignLogEntity);

        // Configure TicketAssignLogRepositoryJPA.findAll(...).
        final TicketAssignLogEntity ticketAssignLogEntity1 = new TicketAssignLogEntity();
        ticketAssignLogEntity1.setTicketAssignLogId(0L);
        ticketAssignLogEntity1.setTicketAssignId(0L);
        ticketAssignLogEntity1.setTicketId(0L);
        ticketAssignLogEntity1.setLogContent("logContent");
        ticketAssignLogEntity1.setSiteId(0L);
        ticketAssignLogEntity1.setCreateUser("createUser");
        ticketAssignLogEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity1.setUpdateUser("updateUser");
        ticketAssignLogEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity1.setLogType(0L);
        final List<TicketAssignLogEntity> ticketAssignLogEntities = Arrays.asList(ticketAssignLogEntity1);
        when(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA.findAll()).thenReturn(ticketAssignLogEntities);

        // Run the test
        final List<TicketAssignLogEntity> result = ticketAssignLogServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketAssignLogRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAssignLogEntity> result = ticketAssignLogServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
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

        final TicketAssignLogEntity expectedResult = new TicketAssignLogEntity();
        expectedResult.setTicketAssignLogId(0L);
        expectedResult.setTicketAssignId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setLogContent("logContent");
        expectedResult.setSiteId(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setLogType(0L);

        // Configure TicketAssignLogRepositoryJPA.save(...).
        final TicketAssignLogEntity ticketAssignLogEntity1 = new TicketAssignLogEntity();
        ticketAssignLogEntity1.setTicketAssignLogId(0L);
        ticketAssignLogEntity1.setTicketAssignId(0L);
        ticketAssignLogEntity1.setTicketId(0L);
        ticketAssignLogEntity1.setLogContent("logContent");
        ticketAssignLogEntity1.setSiteId(0L);
        ticketAssignLogEntity1.setCreateUser("createUser");
        ticketAssignLogEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity1.setUpdateUser("updateUser");
        ticketAssignLogEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity1.setLogType(0L);
        when(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA.save(new TicketAssignLogEntity())).thenReturn(ticketAssignLogEntity1);

        // Run the test
        final TicketAssignLogEntity result = ticketAssignLogServiceJPAUnderTest.save(ticketAssignLogEntity);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
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
        final Optional<TicketAssignLogEntity> expectedResult = Optional.of(ticketAssignLogEntity);

        // Configure TicketAssignLogRepositoryJPA.findById(...).
        final TicketAssignLogEntity ticketAssignLogEntity2 = new TicketAssignLogEntity();
        ticketAssignLogEntity2.setTicketAssignLogId(0L);
        ticketAssignLogEntity2.setTicketAssignId(0L);
        ticketAssignLogEntity2.setTicketId(0L);
        ticketAssignLogEntity2.setLogContent("logContent");
        ticketAssignLogEntity2.setSiteId(0L);
        ticketAssignLogEntity2.setCreateUser("createUser");
        ticketAssignLogEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity2.setUpdateUser("updateUser");
        ticketAssignLogEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignLogEntity2.setLogType(0L);
        final Optional<TicketAssignLogEntity> ticketAssignLogEntity1 = Optional.of(ticketAssignLogEntity2);
        when(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA.findById(0L)).thenReturn(ticketAssignLogEntity1);

        // Run the test
        final Optional<TicketAssignLogEntity> result = ticketAssignLogServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketAssignLogRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketAssignLogEntity> result = ticketAssignLogServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        ticketAssignLogServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
        final TicketAssignLogEntity expectedResult = new TicketAssignLogEntity();
        expectedResult.setTicketAssignLogId(0L);
        expectedResult.setTicketAssignId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setLogContent("logContent");
        expectedResult.setSiteId(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setLogType(0L);

        // Configure TicketAssignLogRepositoryJPA.getOne(...).
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
        when(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA.getOne(0L)).thenReturn(ticketAssignLogEntity);

        // Run the test
        final TicketAssignLogEntity result = ticketAssignLogServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(ticketAssignLogServiceJPAUnderTest.ticketAssignLogRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketAssignLogServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
