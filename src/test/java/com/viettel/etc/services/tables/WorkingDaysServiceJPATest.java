package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.WorkingDaysRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.WorkingDaysEntity;
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

class WorkingDaysServiceJPATest {

    private WorkingDaysServiceJPA workingDaysServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        workingDaysServiceJPAUnderTest = new WorkingDaysServiceJPA();
        workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA = mock(WorkingDaysRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final WorkingDaysEntity workingDaysEntity = new WorkingDaysEntity();
        workingDaysEntity.setYyyy(0L);
        workingDaysEntity.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        workingDaysEntity.setType("type");
        workingDaysEntity.setIsLeave(0L);
        workingDaysEntity.setLeaveType(0L);
        final List<WorkingDaysEntity> expectedResult = Arrays.asList(workingDaysEntity);

        // Configure WorkingDaysRepositoryJPA.findAll(...).
        final WorkingDaysEntity workingDaysEntity1 = new WorkingDaysEntity();
        workingDaysEntity1.setYyyy(0L);
        workingDaysEntity1.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        workingDaysEntity1.setType("type");
        workingDaysEntity1.setIsLeave(0L);
        workingDaysEntity1.setLeaveType(0L);
        final List<WorkingDaysEntity> workingDaysEntities = Arrays.asList(workingDaysEntity1);
        when(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA.findAll()).thenReturn(workingDaysEntities);

        // Run the test
        final List<WorkingDaysEntity> result = workingDaysServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_WorkingDaysRepositoryJPAReturnsNoItems() {
        // Setup
        when(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<WorkingDaysEntity> result = workingDaysServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final WorkingDaysEntity workingDaysEntity = new WorkingDaysEntity();
        workingDaysEntity.setYyyy(0L);
        workingDaysEntity.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        workingDaysEntity.setType("type");
        workingDaysEntity.setIsLeave(0L);
        workingDaysEntity.setLeaveType(0L);

        final WorkingDaysEntity expectedResult = new WorkingDaysEntity();
        expectedResult.setYyyy(0L);
        expectedResult.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setType("type");
        expectedResult.setIsLeave(0L);
        expectedResult.setLeaveType(0L);

        // Configure WorkingDaysRepositoryJPA.save(...).
        final WorkingDaysEntity workingDaysEntity1 = new WorkingDaysEntity();
        workingDaysEntity1.setYyyy(0L);
        workingDaysEntity1.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        workingDaysEntity1.setType("type");
        workingDaysEntity1.setIsLeave(0L);
        workingDaysEntity1.setLeaveType(0L);
        when(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA.save(new WorkingDaysEntity())).thenReturn(workingDaysEntity1);

        // Run the test
        final WorkingDaysEntity result = workingDaysServiceJPAUnderTest.save(workingDaysEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final WorkingDaysEntity workingDaysEntity = new WorkingDaysEntity();
        workingDaysEntity.setYyyy(0L);
        workingDaysEntity.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        workingDaysEntity.setType("type");
        workingDaysEntity.setIsLeave(0L);
        workingDaysEntity.setLeaveType(0L);
        final Optional<WorkingDaysEntity> expectedResult = Optional.of(workingDaysEntity);

        // Configure WorkingDaysRepositoryJPA.findById(...).
        final WorkingDaysEntity workingDaysEntity2 = new WorkingDaysEntity();
        workingDaysEntity2.setYyyy(0L);
        workingDaysEntity2.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        workingDaysEntity2.setType("type");
        workingDaysEntity2.setIsLeave(0L);
        workingDaysEntity2.setLeaveType(0L);
        final Optional<WorkingDaysEntity> workingDaysEntity1 = Optional.of(workingDaysEntity2);
        when(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA.findById(0L)).thenReturn(workingDaysEntity1);

        // Run the test
        final Optional<WorkingDaysEntity> result = workingDaysServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_WorkingDaysRepositoryJPAReturnsAbsent() {
        // Setup
        when(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<WorkingDaysEntity> result = workingDaysServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        workingDaysServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final WorkingDaysEntity expectedResult = new WorkingDaysEntity();
        expectedResult.setYyyy(0L);
        expectedResult.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setType("type");
        expectedResult.setIsLeave(0L);
        expectedResult.setLeaveType(0L);

        // Configure WorkingDaysRepositoryJPA.getOne(...).
        final WorkingDaysEntity workingDaysEntity = new WorkingDaysEntity();
        workingDaysEntity.setYyyy(0L);
        workingDaysEntity.setDd(Date.valueOf(LocalDate.of(2020, 1, 1)));
        workingDaysEntity.setType("type");
        workingDaysEntity.setIsLeave(0L);
        workingDaysEntity.setLeaveType(0L);
        when(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA.getOne(0L)).thenReturn(workingDaysEntity);

        // Run the test
        final WorkingDaysEntity result = workingDaysServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(workingDaysServiceJPAUnderTest.workingDaysRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = workingDaysServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
