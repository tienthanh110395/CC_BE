package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAttachmentRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
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

class TicketAttachmentServiceJPATest {

    private TicketAttachmentServiceJPA ticketAttachmentServiceJPAUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketAttachmentServiceJPAUnderTest = new TicketAttachmentServiceJPA();
        ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA = mock(TicketAttachmentRepositoryJPA.class);
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> expectedResult = Arrays.asList(ticketAttachmentEntity);

        // Configure TicketAttachmentRepositoryJPA.findAll(...).
        final TicketAttachmentEntity ticketAttachmentEntity1 = new TicketAttachmentEntity();
        ticketAttachmentEntity1.setAttachmentId(0L);
        ticketAttachmentEntity1.setTicketId(0L);
        ticketAttachmentEntity1.setFileName("fileName");
        ticketAttachmentEntity1.setFilePath("filePath");
        ticketAttachmentEntity1.setDescription("description");
        ticketAttachmentEntity1.setType(0L);
        ticketAttachmentEntity1.setObjectsId(0L);
        ticketAttachmentEntity1.setCreateUser("createUser");
        ticketAttachmentEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity1.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity1);
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAll()).thenReturn(ticketAttachmentEntities);

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TicketAttachmentRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");

        final TicketAttachmentEntity expectedResult = new TicketAttachmentEntity();
        expectedResult.setAttachmentId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setFileName("fileName");
        expectedResult.setFilePath("filePath");
        expectedResult.setDescription("description");
        expectedResult.setType(0L);
        expectedResult.setObjectsId(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");

        // Configure TicketAttachmentRepositoryJPA.save(...).
        final TicketAttachmentEntity ticketAttachmentEntity1 = new TicketAttachmentEntity();
        ticketAttachmentEntity1.setAttachmentId(0L);
        ticketAttachmentEntity1.setTicketId(0L);
        ticketAttachmentEntity1.setFileName("fileName");
        ticketAttachmentEntity1.setFilePath("filePath");
        ticketAttachmentEntity1.setDescription("description");
        ticketAttachmentEntity1.setType(0L);
        ticketAttachmentEntity1.setObjectsId(0L);
        ticketAttachmentEntity1.setCreateUser("createUser");
        ticketAttachmentEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity1.setUpdateUser("updateUser");
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.save(new TicketAttachmentEntity())).thenReturn(ticketAttachmentEntity1);

        // Run the test
        final TicketAttachmentEntity result = ticketAttachmentServiceJPAUnderTest.save(ticketAttachmentEntity);

        // Verify the results
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final Optional<TicketAttachmentEntity> expectedResult = Optional.of(ticketAttachmentEntity);

        // Configure TicketAttachmentRepositoryJPA.findById(...).
        final TicketAttachmentEntity ticketAttachmentEntity2 = new TicketAttachmentEntity();
        ticketAttachmentEntity2.setAttachmentId(0L);
        ticketAttachmentEntity2.setTicketId(0L);
        ticketAttachmentEntity2.setFileName("fileName");
        ticketAttachmentEntity2.setFilePath("filePath");
        ticketAttachmentEntity2.setDescription("description");
        ticketAttachmentEntity2.setType(0L);
        ticketAttachmentEntity2.setObjectsId(0L);
        ticketAttachmentEntity2.setCreateUser("createUser");
        ticketAttachmentEntity2.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity2.setUpdateUser("updateUser");
        final Optional<TicketAttachmentEntity> ticketAttachmentEntity1 = Optional.of(ticketAttachmentEntity2);
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(ticketAttachmentEntity1);

        // Run the test
        final Optional<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_TicketAttachmentRepositoryJPAReturnsAbsent() {
        // Setup
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        // Run the test
        ticketAttachmentServiceJPAUnderTest.deleteById(0L);

        // Verify the results
        verify(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA).deleteById(0L);
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        final TicketAttachmentEntity expectedResult = new TicketAttachmentEntity();
        expectedResult.setAttachmentId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setFileName("fileName");
        expectedResult.setFilePath("filePath");
        expectedResult.setDescription("description");
        expectedResult.setType(0L);
        expectedResult.setObjectsId(0L);
        expectedResult.setCreateUser("createUser");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setUpdateUser("updateUser");

        // Configure TicketAttachmentRepositoryJPA.getOne(...).
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.getOne(0L)).thenReturn(ticketAttachmentEntity);

        // Run the test
        final TicketAttachmentEntity result = ticketAttachmentServiceJPAUnderTest.getOne(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExistsById() throws Exception {
        // Setup
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.existsById(0L)).thenReturn(false);

        // Run the test
        final Boolean result = ticketAttachmentServiceJPAUnderTest.existsById(0L);

        // Verify the results
    }

    @Test
    void testGetTicketAttachment1() {
        // Setup
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> expectedResult = Arrays.asList(ticketAttachmentEntity);

        // Configure TicketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(...).
        final TicketAttachmentEntity ticketAttachmentEntity1 = new TicketAttachmentEntity();
        ticketAttachmentEntity1.setAttachmentId(0L);
        ticketAttachmentEntity1.setTicketId(0L);
        ticketAttachmentEntity1.setFileName("fileName");
        ticketAttachmentEntity1.setFilePath("filePath");
        ticketAttachmentEntity1.setDescription("description");
        ticketAttachmentEntity1.setType(0L);
        ticketAttachmentEntity1.setObjectsId(0L);
        ticketAttachmentEntity1.setCreateUser("createUser");
        ticketAttachmentEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity1.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity1);
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(0L, Arrays.asList(0L))).thenReturn(ticketAttachmentEntities);

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.getTicketAttachment(0L, Arrays.asList(0L));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTicketAttachment1_TicketAttachmentRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(0L, Arrays.asList(0L))).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.getTicketAttachment(0L, Arrays.asList(0L));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTicketAttachment2() {
        // Setup
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> expectedResult = Arrays.asList(ticketAttachmentEntity);

        // Configure TicketAttachmentRepositoryJPA.findAllByTicketIdAndType(...).
        final TicketAttachmentEntity ticketAttachmentEntity1 = new TicketAttachmentEntity();
        ticketAttachmentEntity1.setAttachmentId(0L);
        ticketAttachmentEntity1.setTicketId(0L);
        ticketAttachmentEntity1.setFileName("fileName");
        ticketAttachmentEntity1.setFilePath("filePath");
        ticketAttachmentEntity1.setDescription("description");
        ticketAttachmentEntity1.setType(0L);
        ticketAttachmentEntity1.setObjectsId(0L);
        ticketAttachmentEntity1.setCreateUser("createUser");
        ticketAttachmentEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity1.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity1);
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAllByTicketIdAndType(0L, 0L)).thenReturn(ticketAttachmentEntities);

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.getTicketAttachment(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTicketAttachment2_TicketAttachmentRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAllByTicketIdAndType(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.getTicketAttachment(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetByTicketIdAndTypeAssign() {
        // Setup
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> expectedResult = Arrays.asList(ticketAttachmentEntity);

        // Configure TicketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(...).
        final TicketAttachmentEntity ticketAttachmentEntity1 = new TicketAttachmentEntity();
        ticketAttachmentEntity1.setAttachmentId(0L);
        ticketAttachmentEntity1.setTicketId(0L);
        ticketAttachmentEntity1.setFileName("fileName");
        ticketAttachmentEntity1.setFilePath("filePath");
        ticketAttachmentEntity1.setDescription("description");
        ticketAttachmentEntity1.setType(0L);
        ticketAttachmentEntity1.setObjectsId(0L);
        ticketAttachmentEntity1.setCreateUser("createUser");
        ticketAttachmentEntity1.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentEntity1.setUpdateUser("updateUser");
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity1);
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(0L, Arrays.asList(0L))).thenReturn(ticketAttachmentEntities);

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.getByTicketIdAndTypeAssign(0L);

        // Verify the results
    }

    @Test
    void testGetByTicketIdAndTypeAssign_TicketAttachmentRepositoryJPAReturnsNoItems() {
        // Setup
        when(ticketAttachmentServiceJPAUnderTest.ticketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(0L, Arrays.asList(0L))).thenReturn(Collections.emptyList());

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceJPAUnderTest.getByTicketIdAndTypeAssign(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
