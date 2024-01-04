package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAdjustChargeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAdjustChargeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TicketAdjustChargeServiceJPATest {

    private TicketAdjustChargeServiceJPA ticketAdjustChargeServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        ticketAdjustChargeServiceJPAUnderTest = new TicketAdjustChargeServiceJPA();
        ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA = mock(TicketAdjustChargeRepositoryJPA.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final TicketAdjustChargeEntity ticketAdjustChargeEntity = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity.setTicketId(0L);
        ticketAdjustChargeEntity.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity.setPayType("payType");
        ticketAdjustChargeEntity.setContractNo("contractNo");
        ticketAdjustChargeEntity.setAccountType("accountType");
        ticketAdjustChargeEntity.setAdjustAmount(0L);
        ticketAdjustChargeEntity.setReason("reason");
        ticketAdjustChargeEntity.setAdjustContent("adjustContent");
        final List<TicketAdjustChargeEntity> expectedResult = Arrays.asList(ticketAdjustChargeEntity);

        // Configure TicketAdjustChargeRepositoryJPA.findAll(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity1 = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity1.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity1.setTicketId(0L);
        ticketAdjustChargeEntity1.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity1.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity1.setPayType("payType");
        ticketAdjustChargeEntity1.setContractNo("contractNo");
        ticketAdjustChargeEntity1.setAccountType("accountType");
        ticketAdjustChargeEntity1.setAdjustAmount(0L);
        ticketAdjustChargeEntity1.setReason("reason");
        ticketAdjustChargeEntity1.setAdjustContent("adjustContent");
        final List<TicketAdjustChargeEntity> ticketAdjustChargeEntities = Arrays.asList(ticketAdjustChargeEntity1);
        when(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA.findAll()).thenReturn(ticketAdjustChargeEntities);

        // Run the test
        final List<TicketAdjustChargeEntity> result = ticketAdjustChargeServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketAdjustChargeRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAdjustChargeEntity> result = ticketAdjustChargeServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final TicketAdjustChargeEntity ticketAdjustChargeEntity = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity.setTicketId(0L);
        ticketAdjustChargeEntity.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity.setPayType("payType");
        ticketAdjustChargeEntity.setContractNo("contractNo");
        ticketAdjustChargeEntity.setAccountType("accountType");
        ticketAdjustChargeEntity.setAdjustAmount(0L);
        ticketAdjustChargeEntity.setReason("reason");
        ticketAdjustChargeEntity.setAdjustContent("adjustContent");

        final TicketAdjustChargeEntity expectedResult = new TicketAdjustChargeEntity();
        expectedResult.setTicketAdjustChargeId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setPlateTypeCode("plateTypeCode");
        expectedResult.setPlateNumber("plateNumber");
        expectedResult.setPayType("payType");
        expectedResult.setContractNo("contractNo");
        expectedResult.setAccountType("accountType");
        expectedResult.setAdjustAmount(0L);
        expectedResult.setReason("reason");
        expectedResult.setAdjustContent("adjustContent");

        // Configure TicketAdjustChargeRepositoryJPA.save(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity1 = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity1.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity1.setTicketId(0L);
        ticketAdjustChargeEntity1.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity1.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity1.setPayType("payType");
        ticketAdjustChargeEntity1.setContractNo("contractNo");
        ticketAdjustChargeEntity1.setAccountType("accountType");
        ticketAdjustChargeEntity1.setAdjustAmount(0L);
        ticketAdjustChargeEntity1.setReason("reason");
        ticketAdjustChargeEntity1.setAdjustContent("adjustContent");
        when(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA.save(new TicketAdjustChargeEntity())).thenReturn(ticketAdjustChargeEntity1);

        // Run the test
        final TicketAdjustChargeEntity result = ticketAdjustChargeServiceJPAUnderTest.save(ticketAdjustChargeEntity);

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup
        final TicketAdjustChargeEntity ticketAdjustChargeEntity = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity.setTicketId(0L);
        ticketAdjustChargeEntity.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity.setPayType("payType");
        ticketAdjustChargeEntity.setContractNo("contractNo");
        ticketAdjustChargeEntity.setAccountType("accountType");
        ticketAdjustChargeEntity.setAdjustAmount(0L);
        ticketAdjustChargeEntity.setReason("reason");
        ticketAdjustChargeEntity.setAdjustContent("adjustContent");
        final Optional<TicketAdjustChargeEntity> expectedResult = Optional.of(ticketAdjustChargeEntity);

        // Configure TicketAdjustChargeRepositoryJPA.findById(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity2 = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity2.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity2.setTicketId(0L);
        ticketAdjustChargeEntity2.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity2.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity2.setPayType("payType");
        ticketAdjustChargeEntity2.setContractNo("contractNo");
        ticketAdjustChargeEntity2.setAccountType("accountType");
        ticketAdjustChargeEntity2.setAdjustAmount(0L);
        ticketAdjustChargeEntity2.setReason("reason");
        ticketAdjustChargeEntity2.setAdjustContent("adjustContent");
        final Optional<TicketAdjustChargeEntity> ticketAdjustChargeEntity1 = Optional.of(ticketAdjustChargeEntity2);
        when(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA.findById(0L)).thenReturn(ticketAdjustChargeEntity1);

        // Run the test
        final Optional<TicketAdjustChargeEntity> result = ticketAdjustChargeServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketAdjustChargeRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketAdjustChargeEntity> result = ticketAdjustChargeServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        ticketAdjustChargeServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() {
        // Setup
        final TicketAdjustChargeEntity expectedResult = new TicketAdjustChargeEntity();
        expectedResult.setTicketAdjustChargeId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setPlateTypeCode("plateTypeCode");
        expectedResult.setPlateNumber("plateNumber");
        expectedResult.setPayType("payType");
        expectedResult.setContractNo("contractNo");
        expectedResult.setAccountType("accountType");
        expectedResult.setAdjustAmount(0L);
        expectedResult.setReason("reason");
        expectedResult.setAdjustContent("adjustContent");

        // Configure TicketAdjustChargeRepositoryJPA.getOne(...).
        final TicketAdjustChargeEntity ticketAdjustChargeEntity = new TicketAdjustChargeEntity();
        ticketAdjustChargeEntity.setTicketAdjustChargeId(0L);
        ticketAdjustChargeEntity.setTicketId(0L);
        ticketAdjustChargeEntity.setPlateTypeCode("plateTypeCode");
        ticketAdjustChargeEntity.setPlateNumber("plateNumber");
        ticketAdjustChargeEntity.setPayType("payType");
        ticketAdjustChargeEntity.setContractNo("contractNo");
        ticketAdjustChargeEntity.setAccountType("accountType");
        ticketAdjustChargeEntity.setAdjustAmount(0L);
        ticketAdjustChargeEntity.setReason("reason");
        ticketAdjustChargeEntity.setAdjustContent("adjustContent");
        when(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA.getOne(0L)).thenReturn(ticketAdjustChargeEntity);

        // Run the test
        final TicketAdjustChargeEntity result = ticketAdjustChargeServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() {
        // Setup
        when(ticketAdjustChargeServiceJPAUnderTest.ticketAdjustChargeRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketAdjustChargeServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }
}
