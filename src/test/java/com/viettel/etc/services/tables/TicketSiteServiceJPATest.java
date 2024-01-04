package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSiteRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSiteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TicketSiteServiceJPATest {

    private TicketSiteServiceJPA ticketSiteServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSiteServiceJPAUnderTest = new TicketSiteServiceJPA();
        ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA = mock(TicketSiteRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketSiteEntity ticketSiteEntity = new TicketSiteEntity();
        ticketSiteEntity.setSiteId(0L);
        ticketSiteEntity.setSiteCode("siteCode");
        ticketSiteEntity.setSiteName("siteName");
        ticketSiteEntity.setParentId(0L);
        ticketSiteEntity.setAddress("address");
        ticketSiteEntity.setUsername("username");
        ticketSiteEntity.setPassword("password");
        ticketSiteEntity.setEmail("email");
        ticketSiteEntity.setPhone("phone");
        ticketSiteEntity.setStatus(0L);
        final List<TicketSiteEntity> expectedResult = Arrays.asList(ticketSiteEntity);

        // Configure TicketSiteRepositoryJPA.findAll(...).
        final TicketSiteEntity ticketSiteEntity1 = new TicketSiteEntity();
        ticketSiteEntity1.setSiteId(0L);
        ticketSiteEntity1.setSiteCode("siteCode");
        ticketSiteEntity1.setSiteName("siteName");
        ticketSiteEntity1.setParentId(0L);
        ticketSiteEntity1.setAddress("address");
        ticketSiteEntity1.setUsername("username");
        ticketSiteEntity1.setPassword("password");
        ticketSiteEntity1.setEmail("email");
        ticketSiteEntity1.setPhone("phone");
        ticketSiteEntity1.setStatus(0L);
        final List<TicketSiteEntity> ticketSiteEntities = Arrays.asList(ticketSiteEntity1);
        when(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA.findAll()).thenReturn(ticketSiteEntities);

        // Run the test
        final List<TicketSiteEntity> result = ticketSiteServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketSiteRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketSiteEntity> result = ticketSiteServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketSiteEntity ticketSiteEntity = new TicketSiteEntity();
        ticketSiteEntity.setSiteId(0L);
        ticketSiteEntity.setSiteCode("siteCode");
        ticketSiteEntity.setSiteName("siteName");
        ticketSiteEntity.setParentId(0L);
        ticketSiteEntity.setAddress("address");
        ticketSiteEntity.setUsername("username");
        ticketSiteEntity.setPassword("password");
        ticketSiteEntity.setEmail("email");
        ticketSiteEntity.setPhone("phone");
        ticketSiteEntity.setStatus(0L);

        final TicketSiteEntity expectedResult = new TicketSiteEntity();
        expectedResult.setSiteId(0L);
        expectedResult.setSiteCode("siteCode");
        expectedResult.setSiteName("siteName");
        expectedResult.setParentId(0L);
        expectedResult.setAddress("address");
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setPhone("phone");
        expectedResult.setStatus(0L);

        // Configure TicketSiteRepositoryJPA.save(...).
        final TicketSiteEntity ticketSiteEntity1 = new TicketSiteEntity();
        ticketSiteEntity1.setSiteId(0L);
        ticketSiteEntity1.setSiteCode("siteCode");
        ticketSiteEntity1.setSiteName("siteName");
        ticketSiteEntity1.setParentId(0L);
        ticketSiteEntity1.setAddress("address");
        ticketSiteEntity1.setUsername("username");
        ticketSiteEntity1.setPassword("password");
        ticketSiteEntity1.setEmail("email");
        ticketSiteEntity1.setPhone("phone");
        ticketSiteEntity1.setStatus(0L);
        when(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA.save(new TicketSiteEntity())).thenReturn(ticketSiteEntity1);

        // Run the test
        final TicketSiteEntity result = ticketSiteServiceJPAUnderTest.save(ticketSiteEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketSiteEntity ticketSiteEntity = new TicketSiteEntity();
        ticketSiteEntity.setSiteId(0L);
        ticketSiteEntity.setSiteCode("siteCode");
        ticketSiteEntity.setSiteName("siteName");
        ticketSiteEntity.setParentId(0L);
        ticketSiteEntity.setAddress("address");
        ticketSiteEntity.setUsername("username");
        ticketSiteEntity.setPassword("password");
        ticketSiteEntity.setEmail("email");
        ticketSiteEntity.setPhone("phone");
        ticketSiteEntity.setStatus(0L);
        final Optional<TicketSiteEntity> expectedResult = Optional.of(ticketSiteEntity);

        // Configure TicketSiteRepositoryJPA.findById(...).
        final TicketSiteEntity ticketSiteEntity2 = new TicketSiteEntity();
        ticketSiteEntity2.setSiteId(0L);
        ticketSiteEntity2.setSiteCode("siteCode");
        ticketSiteEntity2.setSiteName("siteName");
        ticketSiteEntity2.setParentId(0L);
        ticketSiteEntity2.setAddress("address");
        ticketSiteEntity2.setUsername("username");
        ticketSiteEntity2.setPassword("password");
        ticketSiteEntity2.setEmail("email");
        ticketSiteEntity2.setPhone("phone");
        ticketSiteEntity2.setStatus(0L);
        final Optional<TicketSiteEntity> ticketSiteEntity1 = Optional.of(ticketSiteEntity2);
        when(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA.findById(0L)).thenReturn(ticketSiteEntity1);

        // Run the test
        final Optional<TicketSiteEntity> result = ticketSiteServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketSiteRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketSiteEntity> result = ticketSiteServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketSiteServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketSiteEntity expectedResult = new TicketSiteEntity();
        expectedResult.setSiteId(0L);
        expectedResult.setSiteCode("siteCode");
        expectedResult.setSiteName("siteName");
        expectedResult.setParentId(0L);
        expectedResult.setAddress("address");
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setPhone("phone");
        expectedResult.setStatus(0L);

        // Configure TicketSiteRepositoryJPA.getOne(...).
        final TicketSiteEntity ticketSiteEntity = new TicketSiteEntity();
        ticketSiteEntity.setSiteId(0L);
        ticketSiteEntity.setSiteCode("siteCode");
        ticketSiteEntity.setSiteName("siteName");
        ticketSiteEntity.setParentId(0L);
        ticketSiteEntity.setAddress("address");
        ticketSiteEntity.setUsername("username");
        ticketSiteEntity.setPassword("password");
        ticketSiteEntity.setEmail("email");
        ticketSiteEntity.setPhone("phone");
        ticketSiteEntity.setStatus(0L);
        when(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA.getOne(0L)).thenReturn(ticketSiteEntity);

        // Run the test
        final TicketSiteEntity result = ticketSiteServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketSiteServiceJPAUnderTest.ticketSiteRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketSiteServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
