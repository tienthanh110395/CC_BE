package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketProcessEntity;
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

class TicketProcessServiceJPATest {

    private TicketProcessServiceJPA ticketProcessServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketProcessServiceJPAUnderTest = new TicketProcessServiceJPA();
        ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA = mock(TicketProcessRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketProcessEntity ticketProcessEntity = new TicketProcessEntity();
        ticketProcessEntity.setTicketProcessId(0L);
        ticketProcessEntity.setTicketId(0L);
        ticketProcessEntity.setDestroyReason("destroyReason");
        ticketProcessEntity.setProcessResult("processResult");
        ticketProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity.setStatus(0L);
        ticketProcessEntity.setReasonLevel1("reasonLevel1");
        ticketProcessEntity.setReasonLevel2("reasonLevel2");
        ticketProcessEntity.setProcessContent("processContent");
        ticketProcessEntity.setStaffCode("staffCode");
        final List<TicketProcessEntity> expectedResult = Arrays.asList(ticketProcessEntity);

        // Configure TicketProcessRepositoryJPA.findAll(...).
        final TicketProcessEntity ticketProcessEntity1 = new TicketProcessEntity();
        ticketProcessEntity1.setTicketProcessId(0L);
        ticketProcessEntity1.setTicketId(0L);
        ticketProcessEntity1.setDestroyReason("destroyReason");
        ticketProcessEntity1.setProcessResult("processResult");
        ticketProcessEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity1.setStatus(0L);
        ticketProcessEntity1.setReasonLevel1("reasonLevel1");
        ticketProcessEntity1.setReasonLevel2("reasonLevel2");
        ticketProcessEntity1.setProcessContent("processContent");
        ticketProcessEntity1.setStaffCode("staffCode");
        final List<TicketProcessEntity> ticketProcessEntities = Arrays.asList(ticketProcessEntity1);
        when(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA.findAll()).thenReturn(ticketProcessEntities);

        // Run the test
        final List<TicketProcessEntity> result = ticketProcessServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketProcessRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketProcessEntity> result = ticketProcessServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketProcessEntity ticketProcessEntity = new TicketProcessEntity();
        ticketProcessEntity.setTicketProcessId(0L);
        ticketProcessEntity.setTicketId(0L);
        ticketProcessEntity.setDestroyReason("destroyReason");
        ticketProcessEntity.setProcessResult("processResult");
        ticketProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity.setStatus(0L);
        ticketProcessEntity.setReasonLevel1("reasonLevel1");
        ticketProcessEntity.setReasonLevel2("reasonLevel2");
        ticketProcessEntity.setProcessContent("processContent");
        ticketProcessEntity.setStaffCode("staffCode");

        final TicketProcessEntity expectedResult = new TicketProcessEntity();
        expectedResult.setTicketProcessId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setDestroyReason("destroyReason");
        expectedResult.setProcessResult("processResult");
        expectedResult.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setStatus(0L);
        expectedResult.setReasonLevel1("reasonLevel1");
        expectedResult.setReasonLevel2("reasonLevel2");
        expectedResult.setProcessContent("processContent");
        expectedResult.setStaffCode("staffCode");

        // Configure TicketProcessRepositoryJPA.save(...).
        final TicketProcessEntity ticketProcessEntity1 = new TicketProcessEntity();
        ticketProcessEntity1.setTicketProcessId(0L);
        ticketProcessEntity1.setTicketId(0L);
        ticketProcessEntity1.setDestroyReason("destroyReason");
        ticketProcessEntity1.setProcessResult("processResult");
        ticketProcessEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity1.setStatus(0L);
        ticketProcessEntity1.setReasonLevel1("reasonLevel1");
        ticketProcessEntity1.setReasonLevel2("reasonLevel2");
        ticketProcessEntity1.setProcessContent("processContent");
        ticketProcessEntity1.setStaffCode("staffCode");
        when(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA.save(new TicketProcessEntity())).thenReturn(ticketProcessEntity1);

        // Run the test
        final TicketProcessEntity result = ticketProcessServiceJPAUnderTest.save(ticketProcessEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketProcessEntity ticketProcessEntity = new TicketProcessEntity();
        ticketProcessEntity.setTicketProcessId(0L);
        ticketProcessEntity.setTicketId(0L);
        ticketProcessEntity.setDestroyReason("destroyReason");
        ticketProcessEntity.setProcessResult("processResult");
        ticketProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity.setStatus(0L);
        ticketProcessEntity.setReasonLevel1("reasonLevel1");
        ticketProcessEntity.setReasonLevel2("reasonLevel2");
        ticketProcessEntity.setProcessContent("processContent");
        ticketProcessEntity.setStaffCode("staffCode");
        final Optional<TicketProcessEntity> expectedResult = Optional.of(ticketProcessEntity);

        // Configure TicketProcessRepositoryJPA.findById(...).
        final TicketProcessEntity ticketProcessEntity2 = new TicketProcessEntity();
        ticketProcessEntity2.setTicketProcessId(0L);
        ticketProcessEntity2.setTicketId(0L);
        ticketProcessEntity2.setDestroyReason("destroyReason");
        ticketProcessEntity2.setProcessResult("processResult");
        ticketProcessEntity2.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity2.setStatus(0L);
        ticketProcessEntity2.setReasonLevel1("reasonLevel1");
        ticketProcessEntity2.setReasonLevel2("reasonLevel2");
        ticketProcessEntity2.setProcessContent("processContent");
        ticketProcessEntity2.setStaffCode("staffCode");
        final Optional<TicketProcessEntity> ticketProcessEntity1 = Optional.of(ticketProcessEntity2);
        when(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA.findById(0L)).thenReturn(ticketProcessEntity1);

        // Run the test
        final Optional<TicketProcessEntity> result = ticketProcessServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketProcessRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketProcessEntity> result = ticketProcessServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketProcessServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketProcessEntity expectedResult = new TicketProcessEntity();
        expectedResult.setTicketProcessId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setDestroyReason("destroyReason");
        expectedResult.setProcessResult("processResult");
        expectedResult.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setStatus(0L);
        expectedResult.setReasonLevel1("reasonLevel1");
        expectedResult.setReasonLevel2("reasonLevel2");
        expectedResult.setProcessContent("processContent");
        expectedResult.setStaffCode("staffCode");

        // Configure TicketProcessRepositoryJPA.getOne(...).
        final TicketProcessEntity ticketProcessEntity = new TicketProcessEntity();
        ticketProcessEntity.setTicketProcessId(0L);
        ticketProcessEntity.setTicketId(0L);
        ticketProcessEntity.setDestroyReason("destroyReason");
        ticketProcessEntity.setProcessResult("processResult");
        ticketProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketProcessEntity.setStatus(0L);
        ticketProcessEntity.setReasonLevel1("reasonLevel1");
        ticketProcessEntity.setReasonLevel2("reasonLevel2");
        ticketProcessEntity.setProcessContent("processContent");
        ticketProcessEntity.setStaffCode("staffCode");
        when(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA.getOne(0L)).thenReturn(ticketProcessEntity);

        // Run the test
        final TicketProcessEntity result = ticketProcessServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketProcessServiceJPAUnderTest.ticketProcessRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketProcessServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
