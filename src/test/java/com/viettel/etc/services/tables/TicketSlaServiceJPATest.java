package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSlaRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSlaEntity;
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

class TicketSlaServiceJPATest {

    private TicketSlaServiceJPA ticketSlaServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSlaServiceJPAUnderTest = new TicketSlaServiceJPA();
        ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA = mock(TicketSlaRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketSlaEntity> expectedResult = Arrays.asList(ticketSlaEntity);

        // Configure TicketSlaRepositoryJPA.findAll(...).
        final TicketSlaEntity ticketSlaEntity1 = new TicketSlaEntity();
        ticketSlaEntity1.setTicketSlaId(0L);
        ticketSlaEntity1.setSlaName("slaName");
        ticketSlaEntity1.setSla(0L);
        ticketSlaEntity1.setDescription("description");
        ticketSlaEntity1.setStatus(0L);
        ticketSlaEntity1.setSiteId(0L);
        ticketSlaEntity1.setSourceId(0L);
        ticketSlaEntity1.setTicketTypeId(0L);
        ticketSlaEntity1.setCreateUser("createUser");
        ticketSlaEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketSlaEntity> ticketSlaEntities = Arrays.asList(ticketSlaEntity1);
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.findAll()).thenReturn(ticketSlaEntities);

        // Run the test
        final List<TicketSlaEntity> result = ticketSlaServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketSlaRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketSlaEntity> result = ticketSlaServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final TicketSlaEntity expectedResult = new TicketSlaEntity();
        expectedResult.setTicketSlaId(0L);
        expectedResult.setSlaName("slaName");
        expectedResult.setSla(0L);
        expectedResult.setDescription("description");
        expectedResult.setStatus(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setSourceId(0L);
        expectedResult.setTicketTypeId(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketSlaRepositoryJPA.save(...).
        final TicketSlaEntity ticketSlaEntity1 = new TicketSlaEntity();
        ticketSlaEntity1.setTicketSlaId(0L);
        ticketSlaEntity1.setSlaName("slaName");
        ticketSlaEntity1.setSla(0L);
        ticketSlaEntity1.setDescription("description");
        ticketSlaEntity1.setStatus(0L);
        ticketSlaEntity1.setSiteId(0L);
        ticketSlaEntity1.setSourceId(0L);
        ticketSlaEntity1.setTicketTypeId(0L);
        ticketSlaEntity1.setCreateUser("createUser");
        ticketSlaEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.save(new TicketSlaEntity())).thenReturn(ticketSlaEntity1);

        // Run the test
        final TicketSlaEntity result = ticketSlaServiceJPAUnderTest.save(ticketSlaEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketSlaEntity> expectedResult = Optional.of(ticketSlaEntity);

        // Configure TicketSlaRepositoryJPA.findById(...).
        final TicketSlaEntity ticketSlaEntity2 = new TicketSlaEntity();
        ticketSlaEntity2.setTicketSlaId(0L);
        ticketSlaEntity2.setSlaName("slaName");
        ticketSlaEntity2.setSla(0L);
        ticketSlaEntity2.setDescription("description");
        ticketSlaEntity2.setStatus(0L);
        ticketSlaEntity2.setSiteId(0L);
        ticketSlaEntity2.setSourceId(0L);
        ticketSlaEntity2.setTicketTypeId(0L);
        ticketSlaEntity2.setCreateUser("createUser");
        ticketSlaEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketSlaEntity> ticketSlaEntity1 = Optional.of(ticketSlaEntity2);
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.findById(0L)).thenReturn(ticketSlaEntity1);

        // Run the test
        final Optional<TicketSlaEntity> result = ticketSlaServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketSlaRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketSlaEntity> result = ticketSlaServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketSlaServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketSlaEntity expectedResult = new TicketSlaEntity();
        expectedResult.setTicketSlaId(0L);
        expectedResult.setSlaName("slaName");
        expectedResult.setSla(0L);
        expectedResult.setDescription("description");
        expectedResult.setStatus(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setSourceId(0L);
        expectedResult.setTicketTypeId(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketSlaRepositoryJPA.getOne(...).
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.getOne(0L)).thenReturn(ticketSlaEntity);

        // Run the test
        final TicketSlaEntity result = ticketSlaServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketSlaServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }

    @Test
    void testGetByTicketTypeId() {
        // Setup
        final TicketSlaEntity expectedResult = new TicketSlaEntity();
        expectedResult.setTicketSlaId(0L);
        expectedResult.setSlaName("slaName");
        expectedResult.setSla(0L);
        expectedResult.setDescription("description");
        expectedResult.setStatus(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setSourceId(0L);
        expectedResult.setTicketTypeId(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketSlaRepositoryJPA.findByTicketTypeId(...).
        final TicketSlaEntity ticketSlaEntity = new TicketSlaEntity();
        ticketSlaEntity.setTicketSlaId(0L);
        ticketSlaEntity.setSlaName("slaName");
        ticketSlaEntity.setSla(0L);
        ticketSlaEntity.setDescription("description");
        ticketSlaEntity.setStatus(0L);
        ticketSlaEntity.setSiteId(0L);
        ticketSlaEntity.setSourceId(0L);
        ticketSlaEntity.setTicketTypeId(0L);
        ticketSlaEntity.setCreateUser("createUser");
        ticketSlaEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketSlaServiceJPAUnderTest.ticketSlaRepositoryJPA.findByTicketTypeId(0L)).thenReturn(ticketSlaEntity);

        // Run the test
        final TicketSlaEntity result = ticketSlaServiceJPAUnderTest.getByTicketTypeId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
