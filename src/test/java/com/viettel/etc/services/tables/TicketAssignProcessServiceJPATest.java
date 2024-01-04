package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAssignProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignProcessEntity;
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

class TicketAssignProcessServiceJPATest {

    private TicketAssignProcessServiceJPA ticketAssignProcessServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        ticketAssignProcessServiceJPAUnderTest = new TicketAssignProcessServiceJPA();
        ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA = mock(TicketAssignProcessRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
        ticketAssignProcessEntity.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity.setTicketAssignId(0L);
        ticketAssignProcessEntity.setTicketId(0L);
        ticketAssignProcessEntity.setProcessContent("processContent");
        ticketAssignProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity.setProcessResult("processResult");
        ticketAssignProcessEntity.setSiteId(0L);
        ticketAssignProcessEntity.setStaffCode("staffCode");
        ticketAssignProcessEntity.setStaffName("staffName");
        ticketAssignProcessEntity.setCreateUser("createUser");
        final List<TicketAssignProcessEntity> expectedResult = Arrays.asList(ticketAssignProcessEntity);

        // Configure TicketAssignProcessRepositoryJPA.findAll(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity1 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity1.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity1.setTicketAssignId(0L);
        ticketAssignProcessEntity1.setTicketId(0L);
        ticketAssignProcessEntity1.setProcessContent("processContent");
        ticketAssignProcessEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity1.setProcessResult("processResult");
        ticketAssignProcessEntity1.setSiteId(0L);
        ticketAssignProcessEntity1.setStaffCode("staffCode");
        ticketAssignProcessEntity1.setStaffName("staffName");
        ticketAssignProcessEntity1.setCreateUser("createUser");
        final List<TicketAssignProcessEntity> ticketAssignProcessEntities = Arrays.asList(ticketAssignProcessEntity1);
        when(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA.findAll()).thenReturn(ticketAssignProcessEntities);

        // Run the test
        final List<TicketAssignProcessEntity> result = ticketAssignProcessServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketAssignProcessRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAssignProcessEntity> result = ticketAssignProcessServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
        ticketAssignProcessEntity.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity.setTicketAssignId(0L);
        ticketAssignProcessEntity.setTicketId(0L);
        ticketAssignProcessEntity.setProcessContent("processContent");
        ticketAssignProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity.setProcessResult("processResult");
        ticketAssignProcessEntity.setSiteId(0L);
        ticketAssignProcessEntity.setStaffCode("staffCode");
        ticketAssignProcessEntity.setStaffName("staffName");
        ticketAssignProcessEntity.setCreateUser("createUser");

        final TicketAssignProcessEntity expectedResult = new TicketAssignProcessEntity();
        expectedResult.setTicketAssignProcessId(0L);
        expectedResult.setTicketAssignId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setProcessContent("processContent");
        expectedResult.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setProcessResult("processResult");
        expectedResult.setSiteId(0L);
        expectedResult.setStaffCode("staffCode");
        expectedResult.setStaffName("staffName");
        expectedResult.setCreateUser("createUser");

        // Configure TicketAssignProcessRepositoryJPA.save(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity1 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity1.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity1.setTicketAssignId(0L);
        ticketAssignProcessEntity1.setTicketId(0L);
        ticketAssignProcessEntity1.setProcessContent("processContent");
        ticketAssignProcessEntity1.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity1.setProcessResult("processResult");
        ticketAssignProcessEntity1.setSiteId(0L);
        ticketAssignProcessEntity1.setStaffCode("staffCode");
        ticketAssignProcessEntity1.setStaffName("staffName");
        ticketAssignProcessEntity1.setCreateUser("createUser");
        when(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA.save(new TicketAssignProcessEntity())).thenReturn(ticketAssignProcessEntity1);

        // Run the test
        final TicketAssignProcessEntity result = ticketAssignProcessServiceJPAUnderTest.save(ticketAssignProcessEntity);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
        final TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
        ticketAssignProcessEntity.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity.setTicketAssignId(0L);
        ticketAssignProcessEntity.setTicketId(0L);
        ticketAssignProcessEntity.setProcessContent("processContent");
        ticketAssignProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity.setProcessResult("processResult");
        ticketAssignProcessEntity.setSiteId(0L);
        ticketAssignProcessEntity.setStaffCode("staffCode");
        ticketAssignProcessEntity.setStaffName("staffName");
        ticketAssignProcessEntity.setCreateUser("createUser");
        final Optional<TicketAssignProcessEntity> expectedResult = Optional.of(ticketAssignProcessEntity);

        // Configure TicketAssignProcessRepositoryJPA.findById(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity2 = new TicketAssignProcessEntity();
        ticketAssignProcessEntity2.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity2.setTicketAssignId(0L);
        ticketAssignProcessEntity2.setTicketId(0L);
        ticketAssignProcessEntity2.setProcessContent("processContent");
        ticketAssignProcessEntity2.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity2.setProcessResult("processResult");
        ticketAssignProcessEntity2.setSiteId(0L);
        ticketAssignProcessEntity2.setStaffCode("staffCode");
        ticketAssignProcessEntity2.setStaffName("staffName");
        ticketAssignProcessEntity2.setCreateUser("createUser");
        final Optional<TicketAssignProcessEntity> ticketAssignProcessEntity1 = Optional.of(ticketAssignProcessEntity2);
        when(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA.findById(0L)).thenReturn(ticketAssignProcessEntity1);

        // Run the test
        final Optional<TicketAssignProcessEntity> result = ticketAssignProcessServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketAssignProcessRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketAssignProcessEntity> result = ticketAssignProcessServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        ticketAssignProcessServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
        final TicketAssignProcessEntity expectedResult = new TicketAssignProcessEntity();
        expectedResult.setTicketAssignProcessId(0L);
        expectedResult.setTicketAssignId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setProcessContent("processContent");
        expectedResult.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setProcessResult("processResult");
        expectedResult.setSiteId(0L);
        expectedResult.setStaffCode("staffCode");
        expectedResult.setStaffName("staffName");
        expectedResult.setCreateUser("createUser");

        // Configure TicketAssignProcessRepositoryJPA.getOne(...).
        final TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
        ticketAssignProcessEntity.setTicketAssignProcessId(0L);
        ticketAssignProcessEntity.setTicketAssignId(0L);
        ticketAssignProcessEntity.setTicketId(0L);
        ticketAssignProcessEntity.setProcessContent("processContent");
        ticketAssignProcessEntity.setProcessTime(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAssignProcessEntity.setProcessResult("processResult");
        ticketAssignProcessEntity.setSiteId(0L);
        ticketAssignProcessEntity.setStaffCode("staffCode");
        ticketAssignProcessEntity.setStaffName("staffName");
        ticketAssignProcessEntity.setCreateUser("createUser");
        when(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA.getOne(0L)).thenReturn(ticketAssignProcessEntity);

        // Run the test
        final TicketAssignProcessEntity result = ticketAssignProcessServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(ticketAssignProcessServiceJPAUnderTest.ticketAssignProcessRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketAssignProcessServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
