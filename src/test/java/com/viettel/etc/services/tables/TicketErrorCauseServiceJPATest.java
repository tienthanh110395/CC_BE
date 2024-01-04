package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketErrorCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
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

class TicketErrorCauseServiceJPATest {

    private TicketErrorCauseServiceJPA ticketErrorCauseServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketErrorCauseServiceJPAUnderTest = new TicketErrorCauseServiceJPA();
        ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA = mock(TicketErrorCauseRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
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
        final List<TicketErrorCauseEntity> expectedResult = Arrays.asList(ticketErrorCauseEntity);

        // Configure TicketErrorCauseRepositoryJPA.findAll(...).
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
        final List<TicketErrorCauseEntity> ticketErrorCauseEntities = Arrays.asList(ticketErrorCauseEntity1);
        when(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA.findAll()).thenReturn(ticketErrorCauseEntities);

        // Run the test
        final List<TicketErrorCauseEntity> result = ticketErrorCauseServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketErrorCauseRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketErrorCauseEntity> result = ticketErrorCauseServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
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

        final TicketErrorCauseEntity expectedResult = new TicketErrorCauseEntity();
        expectedResult.setTicketErrorCauseId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(0L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketErrorCauseRepositoryJPA.save(...).
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
        when(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA.save(new TicketErrorCauseEntity())).thenReturn(ticketErrorCauseEntity1);

        // Run the test
        final TicketErrorCauseEntity result = ticketErrorCauseServiceJPAUnderTest.save(ticketErrorCauseEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
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
        final Optional<TicketErrorCauseEntity> expectedResult = Optional.of(ticketErrorCauseEntity);

        // Configure TicketErrorCauseRepositoryJPA.findById(...).
        final TicketErrorCauseEntity ticketErrorCauseEntity2 = new TicketErrorCauseEntity();
        ticketErrorCauseEntity2.setTicketErrorCauseId(0L);
        ticketErrorCauseEntity2.setName("name");
        ticketErrorCauseEntity2.setCode("code");
        ticketErrorCauseEntity2.setDescription("description");
        ticketErrorCauseEntity2.setParentId(0L);
        ticketErrorCauseEntity2.setStatus(0L);
        ticketErrorCauseEntity2.setCreateUser("createUser");
        ticketErrorCauseEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketErrorCauseEntity2.setUpdateUser("updateUser");
        ticketErrorCauseEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketErrorCauseEntity> ticketErrorCauseEntity1 = Optional.of(ticketErrorCauseEntity2);
        when(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA.findById(0L)).thenReturn(ticketErrorCauseEntity1);

        // Run the test
        final Optional<TicketErrorCauseEntity> result = ticketErrorCauseServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketErrorCauseRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketErrorCauseEntity> result = ticketErrorCauseServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketErrorCauseServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketErrorCauseEntity expectedResult = new TicketErrorCauseEntity();
        expectedResult.setTicketErrorCauseId(0L);
        expectedResult.setName("name");
        expectedResult.setCode("code");
        expectedResult.setDescription("description");
        expectedResult.setParentId(0L);
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketErrorCauseRepositoryJPA.getOne(...).
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
        when(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA.getOne(0L)).thenReturn(ticketErrorCauseEntity);

        // Run the test
        final TicketErrorCauseEntity result = ticketErrorCauseServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketErrorCauseServiceJPAUnderTest.ticketErrorCauseRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketErrorCauseServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
