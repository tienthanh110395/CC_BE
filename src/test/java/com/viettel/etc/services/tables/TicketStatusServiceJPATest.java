package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketStatusRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketStatusEntity;
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

class TicketStatusServiceJPATest {

    private TicketStatusServiceJPA ticketStatusServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketStatusServiceJPAUnderTest = new TicketStatusServiceJPA();
        ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA = mock(TicketStatusRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketStatusId(0L);
        ticketStatusEntity.setTicketId(0L);
        ticketStatusEntity.setSiteId(0L);
        ticketStatusEntity.setTicketStatus(0L);
        ticketStatusEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setNote("note");
        ticketStatusEntity.setCreateUser("createUser");
        ticketStatusEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setUpdateUser("updateUser");
        ticketStatusEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketStatusEntity> expectedResult = Arrays.asList(ticketStatusEntity);

        // Configure TicketStatusRepositoryJPA.findAll(...).
        final TicketStatusEntity ticketStatusEntity1 = new TicketStatusEntity();
        ticketStatusEntity1.setTicketStatusId(0L);
        ticketStatusEntity1.setTicketId(0L);
        ticketStatusEntity1.setSiteId(0L);
        ticketStatusEntity1.setTicketStatus(0L);
        ticketStatusEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity1.setNote("note");
        ticketStatusEntity1.setCreateUser("createUser");
        ticketStatusEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity1.setUpdateUser("updateUser");
        ticketStatusEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketStatusEntity> ticketStatusEntities = Arrays.asList(ticketStatusEntity1);
        when(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA.findAll()).thenReturn(ticketStatusEntities);

        // Run the test
        final List<TicketStatusEntity> result = ticketStatusServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketStatusRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketStatusEntity> result = ticketStatusServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketStatusId(0L);
        ticketStatusEntity.setTicketId(0L);
        ticketStatusEntity.setSiteId(0L);
        ticketStatusEntity.setTicketStatus(0L);
        ticketStatusEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setNote("note");
        ticketStatusEntity.setCreateUser("createUser");
        ticketStatusEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setUpdateUser("updateUser");
        ticketStatusEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final TicketStatusEntity expectedResult = new TicketStatusEntity();
        expectedResult.setTicketStatusId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setTicketStatus(0L);
        expectedResult.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setNote("note");
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketStatusRepositoryJPA.save(...).
        final TicketStatusEntity ticketStatusEntity1 = new TicketStatusEntity();
        ticketStatusEntity1.setTicketStatusId(0L);
        ticketStatusEntity1.setTicketId(0L);
        ticketStatusEntity1.setSiteId(0L);
        ticketStatusEntity1.setTicketStatus(0L);
        ticketStatusEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity1.setNote("note");
        ticketStatusEntity1.setCreateUser("createUser");
        ticketStatusEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity1.setUpdateUser("updateUser");
        ticketStatusEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA.save(new TicketStatusEntity())).thenReturn(ticketStatusEntity1);

        // Run the test
        final TicketStatusEntity result = ticketStatusServiceJPAUnderTest.save(ticketStatusEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketStatusId(0L);
        ticketStatusEntity.setTicketId(0L);
        ticketStatusEntity.setSiteId(0L);
        ticketStatusEntity.setTicketStatus(0L);
        ticketStatusEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setNote("note");
        ticketStatusEntity.setCreateUser("createUser");
        ticketStatusEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setUpdateUser("updateUser");
        ticketStatusEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketStatusEntity> expectedResult = Optional.of(ticketStatusEntity);

        // Configure TicketStatusRepositoryJPA.findById(...).
        final TicketStatusEntity ticketStatusEntity2 = new TicketStatusEntity();
        ticketStatusEntity2.setTicketStatusId(0L);
        ticketStatusEntity2.setTicketId(0L);
        ticketStatusEntity2.setSiteId(0L);
        ticketStatusEntity2.setTicketStatus(0L);
        ticketStatusEntity2.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity2.setNote("note");
        ticketStatusEntity2.setCreateUser("createUser");
        ticketStatusEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity2.setUpdateUser("updateUser");
        ticketStatusEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketStatusEntity> ticketStatusEntity1 = Optional.of(ticketStatusEntity2);
        when(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA.findById(0L)).thenReturn(ticketStatusEntity1);

        // Run the test
        final Optional<TicketStatusEntity> result = ticketStatusServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketStatusRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketStatusEntity> result = ticketStatusServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketStatusServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketStatusEntity expectedResult = new TicketStatusEntity();
        expectedResult.setTicketStatusId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setTicketStatus(0L);
        expectedResult.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setNote("note");
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketStatusRepositoryJPA.getOne(...).
        final TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketStatusId(0L);
        ticketStatusEntity.setTicketId(0L);
        ticketStatusEntity.setSiteId(0L);
        ticketStatusEntity.setTicketStatus(0L);
        ticketStatusEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setNote("note");
        ticketStatusEntity.setCreateUser("createUser");
        ticketStatusEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketStatusEntity.setUpdateUser("updateUser");
        ticketStatusEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA.getOne(0L)).thenReturn(ticketStatusEntity);

        // Run the test
        final TicketStatusEntity result = ticketStatusServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketStatusServiceJPAUnderTest.ticketStatusRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketStatusServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
