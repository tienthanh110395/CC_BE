package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketExtentReasonRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketExtentReasonEntity;
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

class TicketExtentReasonServiceJPATest {

    private TicketExtentReasonServiceJPA ticketExtentReasonServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExtentReasonServiceJPAUnderTest = new TicketExtentReasonServiceJPA();
        ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason = mock(TicketExtentReasonRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketExtentReasonEntity ticketExtentReasonEntity = new TicketExtentReasonEntity();
        ticketExtentReasonEntity.setTicketExtentReasonId(0L);
        ticketExtentReasonEntity.setName("name");
        ticketExtentReasonEntity.setDescription("description");
        ticketExtentReasonEntity.setStatus(0L);
        ticketExtentReasonEntity.setCreateUser("createUser");
        ticketExtentReasonEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentReasonEntity.setUpdateUser("updateUser");
        ticketExtentReasonEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketExtentReasonEntity> expectedResult = Arrays.asList(ticketExtentReasonEntity);

        // Configure TicketExtentReasonRepositoryJPA.findAll(...).
        final TicketExtentReasonEntity ticketExtentReasonEntity1 = new TicketExtentReasonEntity();
        ticketExtentReasonEntity1.setTicketExtentReasonId(0L);
        ticketExtentReasonEntity1.setName("name");
        ticketExtentReasonEntity1.setDescription("description");
        ticketExtentReasonEntity1.setStatus(0L);
        ticketExtentReasonEntity1.setCreateUser("createUser");
        ticketExtentReasonEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentReasonEntity1.setUpdateUser("updateUser");
        ticketExtentReasonEntity1.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<TicketExtentReasonEntity> ticketExtentReasonEntities = Arrays.asList(ticketExtentReasonEntity1);
        when(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason.findAll()).thenReturn(ticketExtentReasonEntities);

        // Run the test
        final List<TicketExtentReasonEntity> result = ticketExtentReasonServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketExtentReasonRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketExtentReasonEntity> result = ticketExtentReasonServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketExtentReasonEntity Ticket_extent_reason = new TicketExtentReasonEntity();
        Ticket_extent_reason.setTicketExtentReasonId(0L);
        Ticket_extent_reason.setName("name");
        Ticket_extent_reason.setDescription("description");
        Ticket_extent_reason.setStatus(0L);
        Ticket_extent_reason.setCreateUser("createUser");
        Ticket_extent_reason.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Ticket_extent_reason.setUpdateUser("updateUser");
        Ticket_extent_reason.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final TicketExtentReasonEntity expectedResult = new TicketExtentReasonEntity();
        expectedResult.setTicketExtentReasonId(0L);
        expectedResult.setName("name");
        expectedResult.setDescription("description");
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketExtentReasonRepositoryJPA.save(...).
        final TicketExtentReasonEntity ticketExtentReasonEntity = new TicketExtentReasonEntity();
        ticketExtentReasonEntity.setTicketExtentReasonId(0L);
        ticketExtentReasonEntity.setName("name");
        ticketExtentReasonEntity.setDescription("description");
        ticketExtentReasonEntity.setStatus(0L);
        ticketExtentReasonEntity.setCreateUser("createUser");
        ticketExtentReasonEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentReasonEntity.setUpdateUser("updateUser");
        ticketExtentReasonEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason.save(new TicketExtentReasonEntity())).thenReturn(ticketExtentReasonEntity);

        // Run the test
        final TicketExtentReasonEntity result = ticketExtentReasonServiceJPAUnderTest.save(Ticket_extent_reason);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketExtentReasonEntity ticketExtentReasonEntity = new TicketExtentReasonEntity();
        ticketExtentReasonEntity.setTicketExtentReasonId(0L);
        ticketExtentReasonEntity.setName("name");
        ticketExtentReasonEntity.setDescription("description");
        ticketExtentReasonEntity.setStatus(0L);
        ticketExtentReasonEntity.setCreateUser("createUser");
        ticketExtentReasonEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentReasonEntity.setUpdateUser("updateUser");
        ticketExtentReasonEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketExtentReasonEntity> expectedResult = Optional.of(ticketExtentReasonEntity);

        // Configure TicketExtentReasonRepositoryJPA.findById(...).
        final TicketExtentReasonEntity ticketExtentReasonEntity2 = new TicketExtentReasonEntity();
        ticketExtentReasonEntity2.setTicketExtentReasonId(0L);
        ticketExtentReasonEntity2.setName("name");
        ticketExtentReasonEntity2.setDescription("description");
        ticketExtentReasonEntity2.setStatus(0L);
        ticketExtentReasonEntity2.setCreateUser("createUser");
        ticketExtentReasonEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentReasonEntity2.setUpdateUser("updateUser");
        ticketExtentReasonEntity2.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Optional<TicketExtentReasonEntity> ticketExtentReasonEntity1 = Optional.of(ticketExtentReasonEntity2);
        when(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason.findById(0L)).thenReturn(ticketExtentReasonEntity1);

        // Run the test
        final Optional<TicketExtentReasonEntity> result = ticketExtentReasonServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketExtentReasonRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketExtentReasonEntity> result = ticketExtentReasonServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketExtentReasonServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketExtentReasonEntity expectedResult = new TicketExtentReasonEntity();
        expectedResult.setTicketExtentReasonId(0L);
        expectedResult.setName("name");
        expectedResult.setDescription("description");
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");
        expectedResult.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure TicketExtentReasonRepositoryJPA.getOne(...).
        final TicketExtentReasonEntity ticketExtentReasonEntity = new TicketExtentReasonEntity();
        ticketExtentReasonEntity.setTicketExtentReasonId(0L);
        ticketExtentReasonEntity.setName("name");
        ticketExtentReasonEntity.setDescription("description");
        ticketExtentReasonEntity.setStatus(0L);
        ticketExtentReasonEntity.setCreateUser("createUser");
        ticketExtentReasonEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentReasonEntity.setUpdateUser("updateUser");
        ticketExtentReasonEntity.setUpdateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason.getOne(0L)).thenReturn(ticketExtentReasonEntity);

        // Run the test
        final TicketExtentReasonEntity result = ticketExtentReasonServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketExtentReasonServiceJPAUnderTest.ticket_extent_reason.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketExtentReasonServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
