package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketExpireCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketExpireCauseEntity;
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

class TicketExpireCauseServiceJPATest {

    private TicketExpireCauseServiceJPA ticketExpireCauseServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExpireCauseServiceJPAUnderTest = new TicketExpireCauseServiceJPA();
        ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA = mock(TicketExpireCauseRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketExpireCauseEntity ticketExpireCauseEntity = new TicketExpireCauseEntity();
        ticketExpireCauseEntity.setTicketExpireCauseId(0L);
        ticketExpireCauseEntity.setName("name");
        ticketExpireCauseEntity.setCode("code");
        ticketExpireCauseEntity.setDescription("description");
        ticketExpireCauseEntity.setParentId(0L);
        ticketExpireCauseEntity.setStatus(0L);
        ticketExpireCauseEntity.setCreateUser("createUser");
        ticketExpireCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExpireCauseEntity.setUpdateUser("updateUser");
        ticketExpireCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketExpireCauseEntity> expectedResult = Arrays.asList(ticketExpireCauseEntity);

        // Configure TicketExpireCauseRepositoryJPA.findAll(...).
        final TicketExpireCauseEntity ticketExpireCauseEntity1 = new TicketExpireCauseEntity();
        ticketExpireCauseEntity1.setTicketExpireCauseId(0L);
        ticketExpireCauseEntity1.setName("name");
        ticketExpireCauseEntity1.setCode("code");
        ticketExpireCauseEntity1.setDescription("description");
        ticketExpireCauseEntity1.setParentId(0L);
        ticketExpireCauseEntity1.setStatus(0L);
        ticketExpireCauseEntity1.setCreateUser("createUser");
        ticketExpireCauseEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExpireCauseEntity1.setUpdateUser("updateUser");
        ticketExpireCauseEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketExpireCauseEntity> ticketExpireCauseEntities = Arrays.asList(ticketExpireCauseEntity1);
        when(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA.findAll()).thenReturn(ticketExpireCauseEntities);

        // Run the test
        final List<TicketExpireCauseEntity> result = ticketExpireCauseServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketExpireCauseRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketExpireCauseEntity> result = ticketExpireCauseServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketExpireCauseEntity ticketExpireCauseEntity = new TicketExpireCauseEntity();
        ticketExpireCauseEntity.setTicketExpireCauseId(0L);
        ticketExpireCauseEntity.setName("name");
        ticketExpireCauseEntity.setCode("code");
        ticketExpireCauseEntity.setDescription("description");
        ticketExpireCauseEntity.setParentId(0L);
        ticketExpireCauseEntity.setStatus(0L);
        ticketExpireCauseEntity.setCreateUser("createUser");
        ticketExpireCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExpireCauseEntity.setUpdateUser("updateUser");
        ticketExpireCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final TicketExpireCauseEntity expectedResult = new TicketExpireCauseEntity();
        expectedResult.setTicketExpireCauseId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(0L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketExpireCauseRepositoryJPA.save(...).
        final TicketExpireCauseEntity ticketExpireCauseEntity1 = new TicketExpireCauseEntity();
        ticketExpireCauseEntity1.setTicketExpireCauseId(0L);
        ticketExpireCauseEntity1.setName("name");
        ticketExpireCauseEntity1.setCode("code");
        ticketExpireCauseEntity1.setDescription("description");
        ticketExpireCauseEntity1.setParentId(0L);
        ticketExpireCauseEntity1.setStatus(0L);
        ticketExpireCauseEntity1.setCreateUser("createUser");
        ticketExpireCauseEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExpireCauseEntity1.setUpdateUser("updateUser");
        ticketExpireCauseEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA.save(new TicketExpireCauseEntity())).thenReturn(ticketExpireCauseEntity1);

        // Run the test
        final TicketExpireCauseEntity result = ticketExpireCauseServiceJPAUnderTest.save(ticketExpireCauseEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketExpireCauseEntity ticketExpireCauseEntity = new TicketExpireCauseEntity();
        ticketExpireCauseEntity.setTicketExpireCauseId(0L);
        ticketExpireCauseEntity.setName("name");
        ticketExpireCauseEntity.setCode("code");
        ticketExpireCauseEntity.setDescription("description");
        ticketExpireCauseEntity.setParentId(0L);
        ticketExpireCauseEntity.setStatus(0L);
        ticketExpireCauseEntity.setCreateUser("createUser");
        ticketExpireCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExpireCauseEntity.setUpdateUser("updateUser");
        ticketExpireCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketExpireCauseEntity> expectedResult = Optional.of(ticketExpireCauseEntity);

        // Configure TicketExpireCauseRepositoryJPA.findById(...).
        final TicketExpireCauseEntity ticketExpireCauseEntity2 = new TicketExpireCauseEntity();
        ticketExpireCauseEntity2.setTicketExpireCauseId(0L);
        ticketExpireCauseEntity2.setName("name");
        ticketExpireCauseEntity2.setCode("code");
        ticketExpireCauseEntity2.setDescription("description");
        ticketExpireCauseEntity2.setParentId(0L);
        ticketExpireCauseEntity2.setStatus(0L);
        ticketExpireCauseEntity2.setCreateUser("createUser");
        ticketExpireCauseEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExpireCauseEntity2.setUpdateUser("updateUser");
        ticketExpireCauseEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketExpireCauseEntity> ticketExpireCauseEntity1 = Optional.of(ticketExpireCauseEntity2);
        when(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA.findById(0L)).thenReturn(ticketExpireCauseEntity1);

        // Run the test
        final Optional<TicketExpireCauseEntity> result = ticketExpireCauseServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketExpireCauseRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketExpireCauseEntity> result = ticketExpireCauseServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketExpireCauseServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketExpireCauseEntity expectedResult = new TicketExpireCauseEntity();
        expectedResult.setTicketExpireCauseId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(0L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketExpireCauseRepositoryJPA.getOne(...).
        final TicketExpireCauseEntity ticketExpireCauseEntity = new TicketExpireCauseEntity();
        ticketExpireCauseEntity.setTicketExpireCauseId(0L);
        ticketExpireCauseEntity.setName("name");
        ticketExpireCauseEntity.setCode("code");
        ticketExpireCauseEntity.setDescription("description");
        ticketExpireCauseEntity.setParentId(0L);
        ticketExpireCauseEntity.setStatus(0L);
        ticketExpireCauseEntity.setCreateUser("createUser");
        ticketExpireCauseEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExpireCauseEntity.setUpdateUser("updateUser");
        ticketExpireCauseEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA.getOne(0L)).thenReturn(ticketExpireCauseEntity);

        // Run the test
        final TicketExpireCauseEntity result = ticketExpireCauseServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketExpireCauseServiceJPAUnderTest.ticketExpireCauseRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketExpireCauseServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
