package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSiteUserEntity;
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

class TicketSiteUserServiceJPATest {

    private TicketSiteUserServiceJPA ticketSiteUserServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteUserServiceJPAUnderTest = new TicketSiteUserServiceJPA();
        ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA = mock(TicketSiteUserRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        final List<TicketSiteUserEntity> expectedResult = Arrays.asList(ticketSiteUserEntity);

        // Configure TicketSiteUserRepositoryJPA.findAll(...).
        final TicketSiteUserEntity ticketSiteUserEntity1 = new TicketSiteUserEntity();
        ticketSiteUserEntity1.setTicketSiteUserId(0L);
        ticketSiteUserEntity1.setSiteId(0L);
        ticketSiteUserEntity1.setUserId("userId");
        ticketSiteUserEntity1.setUserName("userName");
        ticketSiteUserEntity1.setStaffCode("staffCode");
        ticketSiteUserEntity1.setStaffName("staffName");
        ticketSiteUserEntity1.setStatus(0L);
        ticketSiteUserEntity1.setCreateUser("createUser");
        ticketSiteUserEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity1.setUpdateUser("updateUser");
        final List<TicketSiteUserEntity> ticketSiteUserEntities = Arrays.asList(ticketSiteUserEntity1);
        when(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA.findAll()).thenReturn(ticketSiteUserEntities);

        // Run the test
        final List<TicketSiteUserEntity> result = ticketSiteUserServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketSiteUserRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketSiteUserEntity> result = ticketSiteUserServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");

        final TicketSiteUserEntity expectedResult = new TicketSiteUserEntity();
        expectedResult.setTicketSiteUserId(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setUserId("userId");
        expectedResult.setUserName("userName");
        expectedResult.setStaffCode("staffCode");
        expectedResult.setStaffName("staffName");
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");

        // Configure TicketSiteUserRepositoryJPA.save(...).
        final TicketSiteUserEntity ticketSiteUserEntity1 = new TicketSiteUserEntity();
        ticketSiteUserEntity1.setTicketSiteUserId(0L);
        ticketSiteUserEntity1.setSiteId(0L);
        ticketSiteUserEntity1.setUserId("userId");
        ticketSiteUserEntity1.setUserName("userName");
        ticketSiteUserEntity1.setStaffCode("staffCode");
        ticketSiteUserEntity1.setStaffName("staffName");
        ticketSiteUserEntity1.setStatus(0L);
        ticketSiteUserEntity1.setCreateUser("createUser");
        ticketSiteUserEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity1.setUpdateUser("updateUser");
        when(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA.save(new TicketSiteUserEntity())).thenReturn(ticketSiteUserEntity1);

        // Run the test
        final TicketSiteUserEntity result = ticketSiteUserServiceJPAUnderTest.save(ticketSiteUserEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        final Optional<TicketSiteUserEntity> expectedResult = Optional.of(ticketSiteUserEntity);

        // Configure TicketSiteUserRepositoryJPA.findById(...).
        final TicketSiteUserEntity ticketSiteUserEntity2 = new TicketSiteUserEntity();
        ticketSiteUserEntity2.setTicketSiteUserId(0L);
        ticketSiteUserEntity2.setSiteId(0L);
        ticketSiteUserEntity2.setUserId("userId");
        ticketSiteUserEntity2.setUserName("userName");
        ticketSiteUserEntity2.setStaffCode("staffCode");
        ticketSiteUserEntity2.setStaffName("staffName");
        ticketSiteUserEntity2.setStatus(0L);
        ticketSiteUserEntity2.setCreateUser("createUser");
        ticketSiteUserEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity2.setUpdateUser("updateUser");
        final Optional<TicketSiteUserEntity> ticketSiteUserEntity1 = Optional.of(ticketSiteUserEntity2);
        when(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA.findById(0L)).thenReturn(ticketSiteUserEntity1);

        // Run the test
        final Optional<TicketSiteUserEntity> result = ticketSiteUserServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketSiteUserRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketSiteUserEntity> result = ticketSiteUserServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketSiteUserServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketSiteUserEntity expectedResult = new TicketSiteUserEntity();
        expectedResult.setTicketSiteUserId(0L);
        expectedResult.setSiteId(0L);
        expectedResult.setUserId("userId");
        expectedResult.setUserName("userName");
        expectedResult.setStaffCode("staffCode");
        expectedResult.setStaffName("staffName");
        expectedResult.setStatus(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");

        // Configure TicketSiteUserRepositoryJPA.getOne(...).
        final TicketSiteUserEntity ticketSiteUserEntity = new TicketSiteUserEntity();
        ticketSiteUserEntity.setTicketSiteUserId(0L);
        ticketSiteUserEntity.setSiteId(0L);
        ticketSiteUserEntity.setUserId("userId");
        ticketSiteUserEntity.setUserName("userName");
        ticketSiteUserEntity.setStaffCode("staffCode");
        ticketSiteUserEntity.setStaffName("staffName");
        ticketSiteUserEntity.setStatus(0L);
        ticketSiteUserEntity.setCreateUser("createUser");
        ticketSiteUserEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSiteUserEntity.setUpdateUser("updateUser");
        when(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA.getOne(0L)).thenReturn(ticketSiteUserEntity);

        // Run the test
        final TicketSiteUserEntity result = ticketSiteUserServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketSiteUserServiceJPAUnderTest.ticketSiteUserRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketSiteUserServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
