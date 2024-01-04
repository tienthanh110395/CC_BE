package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSourceRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSourceEntity;
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

class TicketSourceServiceJPATest {

    private TicketSourceServiceJPA ticketSourceServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSourceServiceJPAUnderTest = new TicketSourceServiceJPA();
        ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA = mock(TicketSourceRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketSourceEntity ticketSourceEntity = new TicketSourceEntity();
        ticketSourceEntity.setTicketSourceId(0L);
        ticketSourceEntity.setName("name");
        ticketSourceEntity.setSourceCode("sourceCode");
        ticketSourceEntity.setDescription("description");
        ticketSourceEntity.setStatus(0L);
        ticketSourceEntity.setCreateUser("createUser");
        ticketSourceEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSourceEntity.setUpdateUser("updateUser");
        ticketSourceEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketSourceEntity> expectedResult = Arrays.asList(ticketSourceEntity);

        // Configure TicketSourceRepositoryJPA.findAll(...).
        final TicketSourceEntity ticketSourceEntity1 = new TicketSourceEntity();
        ticketSourceEntity1.setTicketSourceId(0L);
        ticketSourceEntity1.setName("name");
        ticketSourceEntity1.setSourceCode("sourceCode");
        ticketSourceEntity1.setDescription("description");
        ticketSourceEntity1.setStatus(0L);
        ticketSourceEntity1.setCreateUser("createUser");
        ticketSourceEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSourceEntity1.setUpdateUser("updateUser");
        ticketSourceEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketSourceEntity> ticketSourceEntities = Arrays.asList(ticketSourceEntity1);
        when(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA.findAll()).thenReturn(ticketSourceEntities);

        // Run the test
        final List<TicketSourceEntity> result = ticketSourceServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketSourceRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketSourceEntity> result = ticketSourceServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketSourceEntity ticketSourceEntity = new TicketSourceEntity();
        ticketSourceEntity.setTicketSourceId(0L);
        ticketSourceEntity.setName("name");
        ticketSourceEntity.setSourceCode("sourceCode");
        ticketSourceEntity.setDescription("description");
        ticketSourceEntity.setStatus(0L);
        ticketSourceEntity.setCreateUser("createUser");
        ticketSourceEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSourceEntity.setUpdateUser("updateUser");
        ticketSourceEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final TicketSourceEntity expectedResult = new TicketSourceEntity();
        expectedResult.setTicketSourceId(0L);
        expectedResult.setName("name");
        expectedResult.setSourceCode("sourceCode");
        expectedResult.setDescription("description");
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketSourceRepositoryJPA.save(...).
        final TicketSourceEntity ticketSourceEntity1 = new TicketSourceEntity();
        ticketSourceEntity1.setTicketSourceId(0L);
        ticketSourceEntity1.setName("name");
        ticketSourceEntity1.setSourceCode("sourceCode");
        ticketSourceEntity1.setDescription("description");
        ticketSourceEntity1.setStatus(0L);
        ticketSourceEntity1.setCreateUser("createUser");
        ticketSourceEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSourceEntity1.setUpdateUser("updateUser");
        ticketSourceEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA.save(new TicketSourceEntity())).thenReturn(ticketSourceEntity1);

        // Run the test
        final TicketSourceEntity result = ticketSourceServiceJPAUnderTest.save(ticketSourceEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketSourceEntity ticketSourceEntity = new TicketSourceEntity();
        ticketSourceEntity.setTicketSourceId(0L);
        ticketSourceEntity.setName("name");
        ticketSourceEntity.setSourceCode("sourceCode");
        ticketSourceEntity.setDescription("description");
        ticketSourceEntity.setStatus(0L);
        ticketSourceEntity.setCreateUser("createUser");
        ticketSourceEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSourceEntity.setUpdateUser("updateUser");
        ticketSourceEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketSourceEntity> expectedResult = Optional.of(ticketSourceEntity);

        // Configure TicketSourceRepositoryJPA.findById(...).
        final TicketSourceEntity ticketSourceEntity2 = new TicketSourceEntity();
        ticketSourceEntity2.setTicketSourceId(0L);
        ticketSourceEntity2.setName("name");
        ticketSourceEntity2.setSourceCode("sourceCode");
        ticketSourceEntity2.setDescription("description");
        ticketSourceEntity2.setStatus(0L);
        ticketSourceEntity2.setCreateUser("createUser");
        ticketSourceEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSourceEntity2.setUpdateUser("updateUser");
        ticketSourceEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketSourceEntity> ticketSourceEntity1 = Optional.of(ticketSourceEntity2);
        when(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA.findById(0L)).thenReturn(ticketSourceEntity1);

        // Run the test
        final Optional<TicketSourceEntity> result = ticketSourceServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketSourceRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketSourceEntity> result = ticketSourceServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketSourceServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketSourceEntity expectedResult = new TicketSourceEntity();
        expectedResult.setTicketSourceId(0L);
        expectedResult.setName("name");
        expectedResult.setSourceCode("sourceCode");
        expectedResult.setDescription("description");
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketSourceRepositoryJPA.getOne(...).
        final TicketSourceEntity ticketSourceEntity = new TicketSourceEntity();
        ticketSourceEntity.setTicketSourceId(0L);
        ticketSourceEntity.setName("name");
        ticketSourceEntity.setSourceCode("sourceCode");
        ticketSourceEntity.setDescription("description");
        ticketSourceEntity.setStatus(0L);
        ticketSourceEntity.setCreateUser("createUser");
        ticketSourceEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSourceEntity.setUpdateUser("updateUser");
        ticketSourceEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA.getOne(0L)).thenReturn(ticketSourceEntity);

        // Run the test
        final TicketSourceEntity result = ticketSourceServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketSourceServiceJPAUnderTest.ticketSourceRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketSourceServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
