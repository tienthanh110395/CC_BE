package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketProcessShareRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketProcessShareEntity;
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

class TicketProcessShareServiceJPATest {

    private TicketProcessShareServiceJPA ticketProcessShareServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareServiceJPAUnderTest = new TicketProcessShareServiceJPA();
        ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA = mock(TicketProcessShareRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);
        final List<TicketProcessShareEntity> expectedResult = Arrays.asList(ticketProcessShareEntity);

        // Configure TicketProcessShareRepositoryJPA.findAll(...).
        final TicketProcessShareEntity ticketProcessShareEntity1 = new TicketProcessShareEntity();
        ticketProcessShareEntity1.setTicketProcessShareId(0L);
        ticketProcessShareEntity1.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity1.setAssignUser("assignUser");
        ticketProcessShareEntity1.setAssignType(0L);
        final List<TicketProcessShareEntity> ticketProcessShareEntities = Arrays.asList(ticketProcessShareEntity1);
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.findAll()).thenReturn(ticketProcessShareEntities);

        // Run the test
        final List<TicketProcessShareEntity> result = ticketProcessShareServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketProcessShareRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketProcessShareEntity> result = ticketProcessShareServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);

        final TicketProcessShareEntity expectedResult = new TicketProcessShareEntity();
        expectedResult.setTicketProcessShareId(0L);
        expectedResult.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setAssignUser("assignUser");
        expectedResult.setAssignType(0L);

        // Configure TicketProcessShareRepositoryJPA.save(...).
        final TicketProcessShareEntity ticketProcessShareEntity1 = new TicketProcessShareEntity();
        ticketProcessShareEntity1.setTicketProcessShareId(0L);
        ticketProcessShareEntity1.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity1.setAssignUser("assignUser");
        ticketProcessShareEntity1.setAssignType(0L);
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.save(new TicketProcessShareEntity())).thenReturn(ticketProcessShareEntity1);

        // Run the test
        final TicketProcessShareEntity result = ticketProcessShareServiceJPAUnderTest.save(ticketProcessShareEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);
        final Optional<TicketProcessShareEntity> expectedResult = Optional.of(ticketProcessShareEntity);

        // Configure TicketProcessShareRepositoryJPA.findById(...).
        final TicketProcessShareEntity ticketProcessShareEntity2 = new TicketProcessShareEntity();
        ticketProcessShareEntity2.setTicketProcessShareId(0L);
        ticketProcessShareEntity2.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity2.setAssignUser("assignUser");
        ticketProcessShareEntity2.setAssignType(0L);
        final Optional<TicketProcessShareEntity> ticketProcessShareEntity1 = Optional.of(ticketProcessShareEntity2);
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.findById(0L)).thenReturn(ticketProcessShareEntity1);

        // Run the test
        final Optional<TicketProcessShareEntity> result = ticketProcessShareServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketProcessShareRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketProcessShareEntity> result = ticketProcessShareServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketProcessShareServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketProcessShareEntity expectedResult = new TicketProcessShareEntity();
        expectedResult.setTicketProcessShareId(0L);
        expectedResult.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setAssignUser("assignUser");
        expectedResult.setAssignType(0L);

        // Configure TicketProcessShareRepositoryJPA.getOne(...).
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.getOne(0L)).thenReturn(ticketProcessShareEntity);

        // Run the test
        final TicketProcessShareEntity result = ticketProcessShareServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketProcessShareServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }

    @Test
    void testSaveAll() {
        // Setup
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);
        final List<TicketProcessShareEntity> ticketProcessShareEntities = Arrays.asList(ticketProcessShareEntity);
        final TicketProcessShareEntity ticketProcessShareEntity1 = new TicketProcessShareEntity();
        ticketProcessShareEntity1.setTicketProcessShareId(0L);
        ticketProcessShareEntity1.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity1.setAssignUser("assignUser");
        ticketProcessShareEntity1.setAssignType(0L);
        final List<TicketProcessShareEntity> expectedResult = Arrays.asList(ticketProcessShareEntity1);

        // Configure TicketProcessShareRepositoryJPA.saveAll(...).
        final TicketProcessShareEntity ticketProcessShareEntity2 = new TicketProcessShareEntity();
        ticketProcessShareEntity2.setTicketProcessShareId(0L);
        ticketProcessShareEntity2.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity2.setAssignUser("assignUser");
        ticketProcessShareEntity2.setAssignType(0L);
        final List<TicketProcessShareEntity> ticketProcessShareEntities1 = Arrays.asList(ticketProcessShareEntity2);
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.saveAll(Arrays.asList(new TicketProcessShareEntity()))).thenReturn(ticketProcessShareEntities1);

        // Run the test
        final List<TicketProcessShareEntity> result = ticketProcessShareServiceJPAUnderTest.saveAll(ticketProcessShareEntities);

        // Verify the results
    }

    @Test
    void testSaveAll_TicketProcessShareRepositoryJPAReturnsNoItems() {
        // Setup
        final TicketProcessShareEntity ticketProcessShareEntity = new TicketProcessShareEntity();
        ticketProcessShareEntity.setTicketProcessShareId(0L);
        ticketProcessShareEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareEntity.setAssignUser("assignUser");
        ticketProcessShareEntity.setAssignType(0L);
        final List<TicketProcessShareEntity> ticketProcessShareEntities = Arrays.asList(ticketProcessShareEntity);
        when(ticketProcessShareServiceJPAUnderTest.ticketProcessShareRepositoryJPA.saveAll(Arrays.asList(new TicketProcessShareEntity()))).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketProcessShareEntity> result = ticketProcessShareServiceJPAUnderTest.saveAll(ticketProcessShareEntities);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
