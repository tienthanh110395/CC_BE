package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketProcessShareDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketProcessShareDetailEntity;
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

class TicketProcessShareDetailServiceJPATest {

    private TicketProcessShareDetailServiceJPA ticketProcessShareDetailServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessShareDetailServiceJPAUnderTest = new TicketProcessShareDetailServiceJPA();
        ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA = mock(TicketProcessShareDetailRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> expectedResult = Arrays.asList(ticketProcessShareDetailEntity);

        // Configure TicketProcessShareDetailRepositoryJPA.findAll(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity1 = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity1.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity1.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity1.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity1.setTicketId(0L);
        ticketProcessShareDetailEntity1.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> ticketProcessShareDetailEntities = Arrays.asList(ticketProcessShareDetailEntity1);
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.findAll()).thenReturn(ticketProcessShareDetailEntities);

        // Run the test
        final List<TicketProcessShareDetailEntity> result = ticketProcessShareDetailServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketProcessShareDetailRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketProcessShareDetailEntity> result = ticketProcessShareDetailServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");

        final TicketProcessShareDetailEntity expectedResult = new TicketProcessShareDetailEntity();
        expectedResult.setTicketProcessShareDetailId(0L);
        expectedResult.setTicketProcessShareId(0L);
        expectedResult.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setTicketId(0L);
        expectedResult.setProcessUser("processUser");

        // Configure TicketProcessShareDetailRepositoryJPA.save(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity1 = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity1.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity1.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity1.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity1.setTicketId(0L);
        ticketProcessShareDetailEntity1.setProcessUser("processUser");
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.save(new TicketProcessShareDetailEntity())).thenReturn(ticketProcessShareDetailEntity1);

        // Run the test
        final TicketProcessShareDetailEntity result = ticketProcessShareDetailServiceJPAUnderTest.save(ticketProcessShareDetailEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        final Optional<TicketProcessShareDetailEntity> expectedResult = Optional.of(ticketProcessShareDetailEntity);

        // Configure TicketProcessShareDetailRepositoryJPA.findById(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity2 = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity2.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity2.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity2.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity2.setTicketId(0L);
        ticketProcessShareDetailEntity2.setProcessUser("processUser");
        final Optional<TicketProcessShareDetailEntity> ticketProcessShareDetailEntity1 = Optional.of(ticketProcessShareDetailEntity2);
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.findById(0L)).thenReturn(ticketProcessShareDetailEntity1);

        // Run the test
        final Optional<TicketProcessShareDetailEntity> result = ticketProcessShareDetailServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketProcessShareDetailRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketProcessShareDetailEntity> result = ticketProcessShareDetailServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketProcessShareDetailServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketProcessShareDetailEntity expectedResult = new TicketProcessShareDetailEntity();
        expectedResult.setTicketProcessShareDetailId(0L);
        expectedResult.setTicketProcessShareId(0L);
        expectedResult.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setTicketId(0L);
        expectedResult.setProcessUser("processUser");

        // Configure TicketProcessShareDetailRepositoryJPA.getOne(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.getOne(0L)).thenReturn(ticketProcessShareDetailEntity);

        // Run the test
        final TicketProcessShareDetailEntity result = ticketProcessShareDetailServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketProcessShareDetailServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }

    @Test
    void testSaveAll() {
        // Setup
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> ticketProcessShareDetailEntities = Arrays.asList(ticketProcessShareDetailEntity);
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity1 = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity1.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity1.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity1.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity1.setTicketId(0L);
        ticketProcessShareDetailEntity1.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> expectedResult = Arrays.asList(ticketProcessShareDetailEntity1);

        // Configure TicketProcessShareDetailRepositoryJPA.saveAll(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity2 = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity2.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity2.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity2.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity2.setTicketId(0L);
        ticketProcessShareDetailEntity2.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> ticketProcessShareDetailEntities1 = Arrays.asList(ticketProcessShareDetailEntity2);
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.saveAll(Arrays.asList(new TicketProcessShareDetailEntity()))).thenReturn(ticketProcessShareDetailEntities1);

        // Run the test
        final List<TicketProcessShareDetailEntity> result = ticketProcessShareDetailServiceJPAUnderTest.saveAll(ticketProcessShareDetailEntities);

        // Verify the results
    }

    @Test
    void testSaveAll_TicketProcessShareDetailRepositoryJPAReturnsNoItems() {
        // Setup
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> ticketProcessShareDetailEntities = Arrays.asList(ticketProcessShareDetailEntity);
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.saveAll(Arrays.asList(new TicketProcessShareDetailEntity()))).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketProcessShareDetailEntity> result = ticketProcessShareDetailServiceJPAUnderTest.saveAll(ticketProcessShareDetailEntities);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testExistsByTicketIdAndProcessUserIsNotNull() {
        // Setup
        // Configure TicketProcessShareDetailRepositoryJPA.findAllByTicketIdInAndProcessUserIsNotNull(...).
        final TicketProcessShareDetailEntity ticketProcessShareDetailEntity = new TicketProcessShareDetailEntity();
        ticketProcessShareDetailEntity.setTicketProcessShareDetailId(0L);
        ticketProcessShareDetailEntity.setTicketProcessShareId(0L);
        ticketProcessShareDetailEntity.setAssignTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessShareDetailEntity.setTicketId(0L);
        ticketProcessShareDetailEntity.setProcessUser("processUser");
        final List<TicketProcessShareDetailEntity> ticketProcessShareDetailEntities = Arrays.asList(ticketProcessShareDetailEntity);
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.findAllByTicketIdInAndProcessUserIsNotNull(Arrays.asList(0L))).thenReturn(ticketProcessShareDetailEntities);

        // Run the test
        final boolean result = ticketProcessShareDetailServiceJPAUnderTest.existsByTicketIdAndProcessUserIsNotNull(Arrays.asList(0L));

        // Verify the results
    }

    @Test
    void testExistsByTicketIdAndProcessUserIsNotNull_TicketProcessShareDetailRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.findAllByTicketIdInAndProcessUserIsNotNull(Arrays.asList(0L))).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = ticketProcessShareDetailServiceJPAUnderTest.existsByTicketIdAndProcessUserIsNotNull(Arrays.asList(0L));

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testGetNextValSequenceSerial() {
        // Setup
        when(ticketProcessShareDetailServiceJPAUnderTest.ticketProcessShareDetailRepositoryJPA.getNextValSequenceSerial()).thenReturn(0L);

        // Run the test
        final Long result = ticketProcessShareDetailServiceJPAUnderTest.getNextValSequenceSerial();

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }
}
