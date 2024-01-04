package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.TicketAttachmentDTO;
import com.viettel.etc.repositories.TicketAttachmentRepository;
import com.viettel.etc.repositories.tables.TicketAttachmentRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.FileService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketAttachmentServiceImplTest {

    @Mock
    private FileService mockFileService;

    @Mock
    private TicketAttachmentRepositoryJPA mockTicketAttachmentRepositoryJPA;

    @Mock
    private TicketAttachmentRepository mockTicketAttachmentRepository;

    @InjectMocks
    private TicketAttachmentServiceImpl ticketAttachmentServiceImplUnderTest;

    @Mock
    ActionAuditService actionAuditService;

    @Test
    void testSaveTicketAttachment1() {
        // Setup
        final FileDTO fileDTO = new FileDTO();
        fileDTO.setFilePath("filePath");
        fileDTO.setFileName("fileName.jpg");
        fileDTO.setBase64Data("base64Data");
        fileDTO.setAttachmentId(0L);
        final List<FileDTO> attachmentFiles = Arrays.asList(fileDTO);
        final Authentication authentication = null;
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
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.fileService.getRootFolder()).thenReturn("result");
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.getNextValSequenceSerial()).thenReturn(0L);

        // Configure ActionAuditService.saveActAuditDetail(...).
        final ActionAuditDetailEntity actionAuditDetailEntity = new ActionAuditDetailEntity();
        actionAuditDetailEntity.setActionAuditDetailId(0L);
        actionAuditDetailEntity.setActionAuditId(0L);
        actionAuditDetailEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailEntity.setTableName("tableName");
        actionAuditDetailEntity.setPkId(0L);
        actionAuditDetailEntity.setColumnName("columnName");
        actionAuditDetailEntity.setOldValue("oldValue");
        actionAuditDetailEntity.setNewValue("newValue");
        actionAuditDetailEntity.setActionName("actionName");
        final List<ActionAuditDetailEntity> actionAuditDetailEntities = Arrays.asList(actionAuditDetailEntity);
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.actionAuditService.saveActAuditDetail(eq(0L), any(Object.class), eq(new TicketAttachmentEntity()), eq(0L), eq("actionName"))).thenReturn(actionAuditDetailEntities);

        // Configure TicketAttachmentRepositoryJPA.saveAll(...).
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
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.saveAll(Arrays.asList(new TicketAttachmentEntity()))).thenReturn(ticketAttachmentEntities);

        // Run the test
        final List<TicketAttachmentEntity> result = ticketAttachmentServiceImplUnderTest.saveTicketAttachment(attachmentFiles, 0L, authentication, 0L, 0L, 0L, "actionName");

        // Verify the results
    }

    @Test
    void testSaveTicketAttachment2() {
        // Setup
        final TicketAttachmentDTO ticketAttachmentDTO = new TicketAttachmentDTO();
        ticketAttachmentDTO.setAttachmentId(0L);
        ticketAttachmentDTO.setTicketId(0L);
        ticketAttachmentDTO.setFileName("fileName.jpg");
        ticketAttachmentDTO.setFilePath("filePath");
        ticketAttachmentDTO.setDescription("description");
        ticketAttachmentDTO.setType(0L);
        ticketAttachmentDTO.setObjectsId(0L);
        ticketAttachmentDTO.setCreateUser("createUser");
        ticketAttachmentDTO.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketAttachmentDTO.setUpdateUser("updateUser");

        final Authentication authentication = null;

        // Configure ActionAuditService.saveActAudit(...).
        final ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
        actionAuditEntity.setActionAuditId(0L);
        actionAuditEntity.setActTypeId(0L);
        actionAuditEntity.setContractId(0L);
        actionAuditEntity.setTicketId(0L);
        actionAuditEntity.setTicketAssignId(0L);
        actionAuditEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditEntity.setActionUserFullName("actionUserFullName");
        actionAuditEntity.setActionUserName("actionUserName");
        actionAuditEntity.setAppId("appId");
        actionAuditEntity.setIpPc("ipPc");
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.actionAuditService.saveActAudit(null, new ActionAuditDTO())).thenReturn(actionAuditEntity);

        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.fileService.getRootFolder()).thenReturn("result");
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.getNextValSequenceSerial()).thenReturn(0L);

        // Configure ActionAuditService.saveActAuditDetail(...).
        final ActionAuditDetailEntity actionAuditDetailEntity = new ActionAuditDetailEntity();
        actionAuditDetailEntity.setActionAuditDetailId(0L);
        actionAuditDetailEntity.setActionAuditId(0L);
        actionAuditDetailEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        actionAuditDetailEntity.setTableName("tableName");
        actionAuditDetailEntity.setPkId(0L);
        actionAuditDetailEntity.setColumnName("columnName");
        actionAuditDetailEntity.setOldValue("oldValue");
        actionAuditDetailEntity.setNewValue("newValue");
        actionAuditDetailEntity.setActionName("actionName");
        final List<ActionAuditDetailEntity> actionAuditDetailEntities = Arrays.asList(actionAuditDetailEntity);
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.actionAuditService.saveActAuditDetail(eq(0L), any(Object.class), eq(new TicketAttachmentEntity()), eq(0L), eq("actionName"))).thenReturn(actionAuditDetailEntities);

        // Configure TicketAttachmentRepositoryJPA.saveAll(...).
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
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity);
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.saveAll(Arrays.asList(new TicketAttachmentEntity()))).thenReturn(ticketAttachmentEntities);

        // Run the test
        final Object result = ticketAttachmentServiceImplUnderTest.saveTicketAttachment(ticketAttachmentDTO, authentication);

        // Verify the results
    }

    @Test
    void testSaveAttachFile() {
        // Setup

        // Configure TicketAttachmentRepositoryJPA.saveAll(...).
        final TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setAttachmentId(0L);
        ticketAttachmentEntity.setTicketId(0L);
        ticketAttachmentEntity.setFileName("fileName");
        ticketAttachmentEntity.setFilePath("filePath");
        ticketAttachmentEntity.setDescription("description");
        ticketAttachmentEntity.setType(0L);
        ticketAttachmentEntity.setObjectsId(0L);
        ticketAttachmentEntity.setCreateUser("createUser");
        ticketAttachmentEntity.setCreateDate(new Date(0L));
        ticketAttachmentEntity.setUpdateUser("updateUser");
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName("fileName");
        fileDTO.setBase64Data("fileBase64");
        List<FileDTO> attachmentFiles = new ArrayList<>();
        attachmentFiles.add(fileDTO);
        final List<TicketAttachmentEntity> ticketAttachmentEntities = Arrays.asList(ticketAttachmentEntity);

        when(mockFileService.getRootFolder()).thenReturn("/");
        // mock
        new MockUp<FnCommon>() {
            @mockit.Mock
            public boolean checkFileValid(String fileName, byte[] file, Integer maxFileSizeMb) {
                return true;
            }
        };
        new MockUp<FileService>() {
            @mockit.Mock
            void uploadFile(String filePath, byte[] file) {
            }
        };
        // Run the test
        ReflectionTestUtils.setField(ticketAttachmentServiceImplUnderTest, "ticketAttachmentRepositoryJPA", mockTicketAttachmentRepositoryJPA);
        ticketAttachmentServiceImplUnderTest.saveTicketAttachment(attachmentFiles, 1L, null, 0L, 0L, null, null);

        // Verify the results
    }


    @Test
    void testValidateFileAttach() {
        // Setup
        // check type of base64
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName("fileName");
        fileDTO.setBase64Data("fileBase64");
        List<FileDTO> attachmentFiles = new ArrayList<>();
        attachmentFiles.add(fileDTO);
        ReflectionTestUtils.setField(ticketAttachmentServiceImplUnderTest, "maxFileSize", 5);
        // Run the test
        ticketAttachmentServiceImplUnderTest.validateFileAttach(attachmentFiles);

        // Verify the results
    }

    @Test
    void testGetFileAttachInfo() {
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
        ticketAttachmentEntity.setCreateDate(new Date(0L));
        ticketAttachmentEntity.setUpdateUser("updateUser");

        // Configure TicketAttachmentRepository.getFileAttachInfo(...).
        final ResultSelectEntity resultSelectEntity = new ResultSelectEntity();
        resultSelectEntity.setListData(Arrays.asList(ticketAttachmentEntity));
        resultSelectEntity.setCount(1);
        when(mockTicketAttachmentRepository.getTicketAttachment(any())).thenReturn(resultSelectEntity);

        // Run the test
        final ResultSelectEntity result = ticketAttachmentServiceImplUnderTest.getTicketAttachment(ticketAttachmentEntity, null);

        // Verify the results
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetTicketAttachmentById() {
        TicketAttachmentDTO ticketAttachmentDTO = new TicketAttachmentDTO();
        ticketAttachmentDTO.setStartrecord(1);
        ticketAttachmentDTO.setPagesize(1);
        TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
        ticketAttachmentEntity.setTicketId(1L);

    }

    @Test
    void testDeleteTicketAttachment() {
        // Setup
        // Configure TicketAttachmentRepositoryJPA.findById(...).
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
        final Optional<TicketAttachmentEntity> ticketAttachmentEntity = Optional.of(ticketAttachmentEntity1);
        when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(ticketAttachmentEntity);

        // Run the test
        ticketAttachmentServiceImplUnderTest.deleteTicketAttachment(0L, null);

        // Verify the results
        verify(ticketAttachmentServiceImplUnderTest.fileService).removeFile("filePath");
        verify(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA).deleteById(0L);
    }

    @Test
    void testDeleteTicketAttachment_TicketAttachmentRepositoryJPAFindByIdReturnsAbsent() {
        // Setup
        when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        ticketAttachmentServiceImplUnderTest.deleteTicketAttachment(0L, null);

        // Verify the results
    }

    @Test
    void testDownloadTicketAttachment() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure TicketAttachmentRepositoryJPA.findById(...).
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
        final Optional<TicketAttachmentEntity> ticketAttachmentEntity = Optional.of(ticketAttachmentEntity1);
        when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(ticketAttachmentEntity);

        when(ticketAttachmentServiceImplUnderTest.fileService.getFile("filePath")).thenReturn("content".getBytes());

        // Run the test
        final Object result = ticketAttachmentServiceImplUnderTest.downloadTicketAttachment(0L, null, response);

        // Verify the results
    }

    @Test
    void testDownloadTicketAttachment_ThrowsIOException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure TicketAttachmentRepositoryJPA.findById(...).
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
        final Optional<TicketAttachmentEntity> ticketAttachmentEntity = Optional.of(ticketAttachmentEntity1);
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(ticketAttachmentEntity);

        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.fileService.getFile("filePath")).thenReturn("content".getBytes());

        // Run the test
    }

    @Test
    void testDownloadTicketAttachment_TicketAttachmentRepositoryJPAReturnsAbsent() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(Optional.empty());
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.fileService.getFile("filePath")).thenReturn("content".getBytes());

        // Run the test
        final Object result = ticketAttachmentServiceImplUnderTest.downloadTicketAttachment(0L, null, response);

        // Verify the results
    }

    @Test
    void testGetTicketAttachment2_TicketAttachmentRepositoryJPAReturnsAbsent() {
        // Setup
        final TicketAttachmentDTO params = new TicketAttachmentDTO();
        params.setAttachmentId(0L);
        params.setTicketId(0L);
        params.setFileName("fileName");
        params.setFilePath("filePath");
        params.setDescription("description");
        params.setType(0L);
        params.setObjectsId(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        params.setUpdateUser("updateUser");
        params.setStartrecord(Integer.valueOf(1));
        params.setPagesize(Integer.valueOf(1));

        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findByTicketId(eq(0L), any(Pageable.class))).thenReturn(Optional.empty());

        // Run the test
        final ResultSelectEntity result = ticketAttachmentServiceImplUnderTest.getTicketAttachment(params, null);

        // Verify the results
    }

    @Test
    void testGetTicketAttachment2_TicketAttachmentRepositoryJPAReturnsNoItems() {
        // Setup
        final TicketAttachmentDTO params = new TicketAttachmentDTO();
        params.setAttachmentId(0L);
        params.setTicketId(0L);
        params.setFileName("fileName");
        params.setFilePath("filePath");
        params.setDescription("description");
        params.setType(0L);
        params.setObjectsId(0L);
        params.setCreateUser("createUser");
        params.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        params.setUpdateUser("updateUser");
        params.setStartrecord(Integer.valueOf(1));
        params.setPagesize(Integer.valueOf(1));

        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findByTicketId(eq(0L), any(Pageable.class))).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final ResultSelectEntity result = ticketAttachmentServiceImplUnderTest.getTicketAttachment(params, null);

        // Verify the results
    }

    @Test
    void testViewTicketAttachment() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure TicketAttachmentRepositoryJPA.findById(...).
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
        final Optional<TicketAttachmentEntity> ticketAttachmentEntity = Optional.of(ticketAttachmentEntity1);
        when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(ticketAttachmentEntity);

        when(ticketAttachmentServiceImplUnderTest.fileService.getFile("filePath")).thenReturn("content".getBytes());

        // Run the test
        final Object result = ticketAttachmentServiceImplUnderTest.viewTicketAttachment(0L, null, response);

        // Verify the results
    }

    @Test
    void testViewTicketAttachment_ThrowsIOException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure TicketAttachmentRepositoryJPA.findById(...).
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
        final Optional<TicketAttachmentEntity> ticketAttachmentEntity = Optional.of(ticketAttachmentEntity1);
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(ticketAttachmentEntity);

        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.fileService.getFile("filePath")).thenReturn("content".getBytes());

        // Run the test
    }

    @Test
    void testViewTicketAttachment_TicketAttachmentRepositoryJPAReturnsAbsent() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.ticketAttachmentRepositoryJPA.findById(0L)).thenReturn(Optional.empty());
        Mockito.lenient().when(ticketAttachmentServiceImplUnderTest.fileService.getFile("filePath")).thenReturn("content".getBytes());

        // Run the test
        final Object result = ticketAttachmentServiceImplUnderTest.viewTicketAttachment(0L, null, response);

        // Verify the results
    }
}
