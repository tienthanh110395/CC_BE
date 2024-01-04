package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
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

class TicketTypeServiceJPATest {

    private TicketTypeServiceJPA ticketTypeServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketTypeServiceJPAUnderTest = new TicketTypeServiceJPA();
        ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA = mock(TicketTypeRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketTypeEntity> expectedResult = Arrays.asList(ticketTypeEntity);

        // Configure TicketTypeRepositoryJPA.findAll(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode("code");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("createUser");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketTypeEntity> ticketTypeEntities = Arrays.asList(ticketTypeEntity1);
        when(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA.findAll()).thenReturn(ticketTypeEntities);

        // Run the test
        final List<TicketTypeEntity> result = ticketTypeServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketTypeRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketTypeEntity> result = ticketTypeServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final TicketTypeEntity expectedResult = new TicketTypeEntity();
        expectedResult.setTicketTypeId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(0L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketTypeRepositoryJPA.save(...).
        final TicketTypeEntity ticketTypeEntity1 = new TicketTypeEntity();
        ticketTypeEntity1.setTicketTypeId(0L);
        ticketTypeEntity1.setName("name");
        ticketTypeEntity1.setCode("code");
        ticketTypeEntity1.setDescription("description");
        ticketTypeEntity1.setParentId(0L);
        ticketTypeEntity1.setStatus(0L);
        ticketTypeEntity1.setCreateUser("createUser");
        ticketTypeEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity1.setUpdateUser("updateUser");
        ticketTypeEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA.save(new TicketTypeEntity())).thenReturn(ticketTypeEntity1);

        // Run the test
        final TicketTypeEntity result = ticketTypeServiceJPAUnderTest.save(ticketTypeEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketTypeEntity> expectedResult = Optional.of(ticketTypeEntity);

        // Configure TicketTypeRepositoryJPA.findById(...).
        final TicketTypeEntity ticketTypeEntity2 = new TicketTypeEntity();
        ticketTypeEntity2.setTicketTypeId(0L);
        ticketTypeEntity2.setName("name");
        ticketTypeEntity2.setCode("code");
        ticketTypeEntity2.setDescription("description");
        ticketTypeEntity2.setParentId(0L);
        ticketTypeEntity2.setStatus(0L);
        ticketTypeEntity2.setCreateUser("createUser");
        ticketTypeEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity2.setUpdateUser("updateUser");
        ticketTypeEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketTypeEntity> ticketTypeEntity1 = Optional.of(ticketTypeEntity2);
        when(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA.findById(0L)).thenReturn(ticketTypeEntity1);

        // Run the test
        final Optional<TicketTypeEntity> result = ticketTypeServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketTypeRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketTypeEntity> result = ticketTypeServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketTypeServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketTypeEntity expectedResult = new TicketTypeEntity();
        expectedResult.setTicketTypeId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(0L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketTypeRepositoryJPA.getOne(...).
        final TicketTypeEntity ticketTypeEntity = new TicketTypeEntity();
        ticketTypeEntity.setTicketTypeId(0L);
        ticketTypeEntity.setName("name");
        ticketTypeEntity.setCode("code");
        ticketTypeEntity.setDescription("description");
        ticketTypeEntity.setParentId(0L);
        ticketTypeEntity.setStatus(0L);
        ticketTypeEntity.setCreateUser("createUser");
        ticketTypeEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketTypeEntity.setUpdateUser("updateUser");
        ticketTypeEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA.getOne(0L)).thenReturn(ticketTypeEntity);

        // Run the test
        final TicketTypeEntity result = ticketTypeServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketTypeServiceJPAUnderTest.ticketTypeRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketTypeServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
