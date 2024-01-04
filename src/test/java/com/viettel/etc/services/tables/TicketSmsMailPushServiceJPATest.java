package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSmsMailPushRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
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

class TicketSmsMailPushServiceJPATest {

    private TicketSmsMailPushServiceJPA ticketSmsMailPushServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSmsMailPushServiceJPAUnderTest = new TicketSmsMailPushServiceJPA();
        ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA = mock(TicketSmsMailPushRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        final List<TicketSmsMailPushEntity> expectedResult = Arrays.asList(ticketSmsMailPushEntity);

        // Configure TicketSmsMailPushRepositoryJPA.findAll(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity1 = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity1.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity1.setTicketId(0L);
        ticketSmsMailPushEntity1.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity1.setMessage("message");
        ticketSmsMailPushEntity1.setPhone("phone");
        ticketSmsMailPushEntity1.setEmail("email");
        ticketSmsMailPushEntity1.setMobileId("mobileId");
        ticketSmsMailPushEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity1.setCreateUser("createUser");
        ticketSmsMailPushEntity1.setStatus(0L);
        final List<TicketSmsMailPushEntity> ticketSmsMailPushEntities = Arrays.asList(ticketSmsMailPushEntity1);
        when(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA.findAll()).thenReturn(ticketSmsMailPushEntities);

        // Run the test
        final List<TicketSmsMailPushEntity> result = ticketSmsMailPushServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketSmsMailPushRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketSmsMailPushEntity> result = ticketSmsMailPushServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);

        final TicketSmsMailPushEntity expectedResult = new TicketSmsMailPushEntity();
        expectedResult.setTicketSmsMailPushId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSmsMailPushType("smsMailPushType");
        expectedResult.setMessage("message");
        expectedResult.setPhone("phone");
        expectedResult.setEmail("email");
        expectedResult.setMobileId("mobileId");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setCreateUser("createUser");
        expectedResult.setStatus(0L);

        // Configure TicketSmsMailPushRepositoryJPA.save(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity1 = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity1.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity1.setTicketId(0L);
        ticketSmsMailPushEntity1.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity1.setMessage("message");
        ticketSmsMailPushEntity1.setPhone("phone");
        ticketSmsMailPushEntity1.setEmail("email");
        ticketSmsMailPushEntity1.setMobileId("mobileId");
        ticketSmsMailPushEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity1.setCreateUser("createUser");
        ticketSmsMailPushEntity1.setStatus(0L);
        when(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA.save(new TicketSmsMailPushEntity())).thenReturn(ticketSmsMailPushEntity1);

        // Run the test
        final TicketSmsMailPushEntity result = ticketSmsMailPushServiceJPAUnderTest.save(ticketSmsMailPushEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        final Optional<TicketSmsMailPushEntity> expectedResult = Optional.of(ticketSmsMailPushEntity);

        // Configure TicketSmsMailPushRepositoryJPA.findById(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity2 = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity2.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity2.setTicketId(0L);
        ticketSmsMailPushEntity2.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity2.setMessage("message");
        ticketSmsMailPushEntity2.setPhone("phone");
        ticketSmsMailPushEntity2.setEmail("email");
        ticketSmsMailPushEntity2.setMobileId("mobileId");
        ticketSmsMailPushEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity2.setCreateUser("createUser");
        ticketSmsMailPushEntity2.setStatus(0L);
        final Optional<TicketSmsMailPushEntity> ticketSmsMailPushEntity1 = Optional.of(ticketSmsMailPushEntity2);
        when(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA.findById(0L)).thenReturn(ticketSmsMailPushEntity1);

        // Run the test
        final Optional<TicketSmsMailPushEntity> result = ticketSmsMailPushServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketSmsMailPushRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketSmsMailPushEntity> result = ticketSmsMailPushServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketSmsMailPushServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketSmsMailPushEntity expectedResult = new TicketSmsMailPushEntity();
        expectedResult.setTicketSmsMailPushId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSmsMailPushType("smsMailPushType");
        expectedResult.setMessage("message");
        expectedResult.setPhone("phone");
        expectedResult.setEmail("email");
        expectedResult.setMobileId("mobileId");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setCreateUser("createUser");
        expectedResult.setStatus(0L);

        // Configure TicketSmsMailPushRepositoryJPA.getOne(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        when(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA.getOne(0L)).thenReturn(ticketSmsMailPushEntity);

        // Run the test
        final TicketSmsMailPushEntity result = ticketSmsMailPushServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketSmsMailPushServiceJPAUnderTest.ticketSmsMailPushRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketSmsMailPushServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
