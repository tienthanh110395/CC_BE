package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAssignRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignEntity;
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

class TicketAssignServiceJPATest {

    private TicketAssignServiceJPA ticketAssignServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAssignServiceJPAUnderTest = new TicketAssignServiceJPA();
        ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA = mock(TicketAssignRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final TicketAssignEntity ticketAssignEntity = new TicketAssignEntity();
        ticketAssignEntity.setTicketAssignId(0L);
        ticketAssignEntity.setTicketId(0L);
        ticketAssignEntity.setSiteId(0L);
        ticketAssignEntity.setFromUsername("fromUsername");
        ticketAssignEntity.setToUsername("toUsername");
        ticketAssignEntity.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setFromSiteId(0L);
        ticketAssignEntity.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setAssignType(0L);
        final List<TicketAssignEntity> expectedResult = Arrays.asList(ticketAssignEntity);

        // Configure TicketAssignRepositoryJPA.findAll(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setAssignType(0L);
        final List<TicketAssignEntity> ticketAssignEntities = Arrays.asList(ticketAssignEntity1);
        when(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA.findAll()).thenReturn(ticketAssignEntities);

        // Run the test
        final List<TicketAssignEntity> result = ticketAssignServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketAssignRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAssignEntity> result = ticketAssignServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketAssignEntity ticketAssignEntity = new TicketAssignEntity();
        ticketAssignEntity.setTicketAssignId(0L);
        ticketAssignEntity.setTicketId(0L);
        ticketAssignEntity.setSiteId(0L);
        ticketAssignEntity.setFromUsername("fromUsername");
        ticketAssignEntity.setToUsername("toUsername");
        ticketAssignEntity.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setFromSiteId(0L);
        ticketAssignEntity.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setAssignType(0L);

        final TicketAssignEntity expectedResult = new TicketAssignEntity();
        expectedResult.setTicketAssignId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setFromUsername("fromUsername");
        expectedResult.setToUsername("toUsername");
        expectedResult.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setFromSiteId(0L);
        expectedResult.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setAssignType(0L);

        // Configure TicketAssignRepositoryJPA.save(...).
        final TicketAssignEntity ticketAssignEntity1 = new TicketAssignEntity();
        ticketAssignEntity1.setTicketAssignId(0L);
        ticketAssignEntity1.setTicketId(0L);
        ticketAssignEntity1.setSiteId(0L);
        ticketAssignEntity1.setFromUsername("fromUsername");
        ticketAssignEntity1.setToUsername("toUsername");
        ticketAssignEntity1.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setFromSiteId(0L);
        ticketAssignEntity1.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity1.setAssignType(0L);
        when(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA.save(new TicketAssignEntity())).thenReturn(ticketAssignEntity1);

        // Run the test
        final TicketAssignEntity result = ticketAssignServiceJPAUnderTest.save(ticketAssignEntity);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
        final TicketAssignEntity ticketAssignEntity = new TicketAssignEntity();
        ticketAssignEntity.setTicketAssignId(0L);
        ticketAssignEntity.setTicketId(0L);
        ticketAssignEntity.setSiteId(0L);
        ticketAssignEntity.setFromUsername("fromUsername");
        ticketAssignEntity.setToUsername("toUsername");
        ticketAssignEntity.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setFromSiteId(0L);
        ticketAssignEntity.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setAssignType(0L);
        final Optional<TicketAssignEntity> expectedResult = Optional.of(ticketAssignEntity);

        // Configure TicketAssignRepositoryJPA.findById(...).
        final TicketAssignEntity ticketAssignEntity2 = new TicketAssignEntity();
        ticketAssignEntity2.setTicketAssignId(0L);
        ticketAssignEntity2.setTicketId(0L);
        ticketAssignEntity2.setSiteId(0L);
        ticketAssignEntity2.setFromUsername("fromUsername");
        ticketAssignEntity2.setToUsername("toUsername");
        ticketAssignEntity2.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setFromSiteId(0L);
        ticketAssignEntity2.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity2.setAssignType(0L);
        final Optional<TicketAssignEntity> ticketAssignEntity1 = Optional.of(ticketAssignEntity2);
        when(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA.findById(0L)).thenReturn(ticketAssignEntity1);

        // Run the test
        final Optional<TicketAssignEntity> result = ticketAssignServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketAssignRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketAssignEntity> result = ticketAssignServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        ticketAssignServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketAssignEntity expectedResult = new TicketAssignEntity();
        expectedResult.setTicketAssignId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setFromUsername("fromUsername");
        expectedResult.setToUsername("toUsername");
        expectedResult.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setFromSiteId(0L);
        expectedResult.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setAssignType(0L);

        // Configure TicketAssignRepositoryJPA.getOne(...).
        final TicketAssignEntity ticketAssignEntity = new TicketAssignEntity();
        ticketAssignEntity.setTicketAssignId(0L);
        ticketAssignEntity.setTicketId(0L);
        ticketAssignEntity.setSiteId(0L);
        ticketAssignEntity.setFromUsername("fromUsername");
        ticketAssignEntity.setToUsername("toUsername");
        ticketAssignEntity.setAssignDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setResolveDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setFromSiteId(0L);
        ticketAssignEntity.setSlaDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignEntity.setAssignType(0L);
        when(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA.getOne(0L)).thenReturn(ticketAssignEntity);

        // Run the test
        final TicketAssignEntity result = ticketAssignServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketAssignServiceJPAUnderTest.ticketAssignRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketAssignServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
