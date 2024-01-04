package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketExtentRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
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

class TicketExtentServiceJPATest {

    private TicketExtentServiceJPA ticketExtentServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketExtentServiceJPAUnderTest = new TicketExtentServiceJPA();
        ticketExtentServiceJPAUnderTest.ticket_extent = mock(TicketExtentRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final List<TicketExtentEntity> expectedResult = Arrays.asList(ticketExtentEntity);

        // Configure TicketExtentRepositoryJPA.findAll(...).
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        final List<TicketExtentEntity> ticketExtentEntities = Arrays.asList(ticketExtentEntity1);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.findAll()).thenReturn(ticketExtentEntities);

        // Run the test
        final List<TicketExtentEntity> result = ticketExtentServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketExtentRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketExtentServiceJPAUnderTest.ticket_extent.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketExtentEntity> result = ticketExtentServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketExtentEntity Ticket_extent = new TicketExtentEntity();
        Ticket_extent.setTicketExtentId(0L);
        Ticket_extent.setTicketId(0L);
        Ticket_extent.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Ticket_extent.setExtentReasonId(0L);
        Ticket_extent.setExtentReasonCode("extentReasonCode");
        Ticket_extent.setExtentReasonName("extentReasonName");
        Ticket_extent.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        Ticket_extent.setManagerUserName("managerUserName");
        Ticket_extent.setManagerPhone("managerPhone");
        Ticket_extent.setStatus(0L);

        final TicketExtentEntity expectedResult = new TicketExtentEntity();
        expectedResult.setTicketExtentId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setExtentReasonId(0L);
        expectedResult.setExtentReasonCode("extentReasonCode");
        expectedResult.setExtentReasonName("extentReasonName");
        expectedResult.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setManagerUserName("managerUserName");
        expectedResult.setManagerPhone("managerPhone");
        expectedResult.setStatus(0L);

        // Configure TicketExtentRepositoryJPA.save(...).
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.save(new TicketExtentEntity())).thenReturn(ticketExtentEntity);

        // Run the test
        final TicketExtentEntity result = ticketExtentServiceJPAUnderTest.save(Ticket_extent);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final Optional<TicketExtentEntity> expectedResult = Optional.of(ticketExtentEntity);

        // Configure TicketExtentRepositoryJPA.findById(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        final Optional<TicketExtentEntity> ticketExtentEntity1 = Optional.of(ticketExtentEntity2);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.findById(0L)).thenReturn(ticketExtentEntity1);

        // Run the test
        final Optional<TicketExtentEntity> result = ticketExtentServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketExtentRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketExtentServiceJPAUnderTest.ticket_extent.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketExtentEntity> result = ticketExtentServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testSaveAll() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final List<TicketExtentEntity> ticketExtentEntityList = Arrays.asList(ticketExtentEntity);
        final TicketExtentEntity ticketExtentEntity1 = new TicketExtentEntity();
        ticketExtentEntity1.setTicketExtentId(0L);
        ticketExtentEntity1.setTicketId(0L);
        ticketExtentEntity1.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setExtentReasonId(0L);
        ticketExtentEntity1.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity1.setExtentReasonName("extentReasonName");
        ticketExtentEntity1.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity1.setManagerUserName("managerUserName");
        ticketExtentEntity1.setManagerPhone("managerPhone");
        ticketExtentEntity1.setStatus(0L);
        final List<TicketExtentEntity> expectedResult = Arrays.asList(ticketExtentEntity1);

        // Configure TicketExtentRepositoryJPA.saveAll(...).
        final TicketExtentEntity ticketExtentEntity2 = new TicketExtentEntity();
        ticketExtentEntity2.setTicketExtentId(0L);
        ticketExtentEntity2.setTicketId(0L);
        ticketExtentEntity2.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setExtentReasonId(0L);
        ticketExtentEntity2.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity2.setExtentReasonName("extentReasonName");
        ticketExtentEntity2.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity2.setManagerUserName("managerUserName");
        ticketExtentEntity2.setManagerPhone("managerPhone");
        ticketExtentEntity2.setStatus(0L);
        final List<TicketExtentEntity> ticketExtentEntities = Arrays.asList(ticketExtentEntity2);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.saveAll(Arrays.asList(new TicketExtentEntity()))).thenReturn(ticketExtentEntities);

        // Run the test
        final List<TicketExtentEntity> result = ticketExtentServiceJPAUnderTest.saveAll(ticketExtentEntityList);

        // Verify the results
    }

    @Test
    void testSaveAll_TicketExtentRepositoryJPAReturnsNoItems() {
        // Setup
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        final List<TicketExtentEntity> ticketExtentEntityList = Arrays.asList(ticketExtentEntity);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.saveAll(Arrays.asList(new TicketExtentEntity()))).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketExtentEntity> result = ticketExtentServiceJPAUnderTest.saveAll(ticketExtentEntityList);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketExtentServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketExtentServiceJPAUnderTest.ticket_extent).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketExtentEntity expectedResult = new TicketExtentEntity();
        expectedResult.setTicketExtentId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setExtentReasonId(0L);
        expectedResult.setExtentReasonCode("extentReasonCode");
        expectedResult.setExtentReasonName("extentReasonName");
        expectedResult.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setManagerUserName("managerUserName");
        expectedResult.setManagerPhone("managerPhone");
        expectedResult.setStatus(0L);

        // Configure TicketExtentRepositoryJPA.getOne(...).
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.getOne(0L)).thenReturn(ticketExtentEntity);

        // Run the test
        final TicketExtentEntity result = ticketExtentServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketExtentServiceJPAUnderTest.ticket_extent.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketExtentServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }

    @Test
    void testFindByTicketId() {
        // Setup
        final TicketExtentEntity expectedResult = new TicketExtentEntity();
        expectedResult.setTicketExtentId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setExtentReasonId(0L);
        expectedResult.setExtentReasonCode("extentReasonCode");
        expectedResult.setExtentReasonName("extentReasonName");
        expectedResult.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setManagerUserName("managerUserName");
        expectedResult.setManagerPhone("managerPhone");
        expectedResult.setStatus(0L);

        // Configure TicketExtentRepositoryJPA.findByTicketId(...).
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.findByTicketId(0L)).thenReturn(ticketExtentEntity);

        // Run the test
        final TicketExtentEntity result = ticketExtentServiceJPAUnderTest.findByTicketId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetByTicketIdAndStatus() {
        // Setup
        final TicketExtentEntity expectedResult = new TicketExtentEntity();
        expectedResult.setTicketExtentId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setExtentReasonId(0L);
        expectedResult.setExtentReasonCode("extentReasonCode");
        expectedResult.setExtentReasonName("extentReasonName");
        expectedResult.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setManagerUserName("managerUserName");
        expectedResult.setManagerPhone("managerPhone");
        expectedResult.setStatus(0L);

        // Configure TicketExtentRepositoryJPA.getByTicketIdAndStatus(...).
        final TicketExtentEntity ticketExtentEntity = new TicketExtentEntity();
        ticketExtentEntity.setTicketExtentId(0L);
        ticketExtentEntity.setTicketId(0L);
        ticketExtentEntity.setExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setExtentReasonId(0L);
        ticketExtentEntity.setExtentReasonCode("extentReasonCode");
        ticketExtentEntity.setExtentReasonName("extentReasonName");
        ticketExtentEntity.setRequestExtentDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketExtentEntity.setManagerUserName("managerUserName");
        ticketExtentEntity.setManagerPhone("managerPhone");
        ticketExtentEntity.setStatus(0L);
        when(ticketExtentServiceJPAUnderTest.ticket_extent.getByTicketIdAndStatus(0L, 0L)).thenReturn(ticketExtentEntity);

        // Run the test
        final TicketExtentEntity result = ticketExtentServiceJPAUnderTest.getByTicketIdAndStatus(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
