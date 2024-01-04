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
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketAttachmentServiceImpl implements TicketAttachmentService {

    @Autowired
    FileService fileService;

    @Autowired
    TicketAttachmentRepositoryJPA ticketAttachmentRepositoryJPA;

    @Autowired
    TicketAttachmentRepository ticketAttachmentRepository;

    @Value("${crm.common.max-file-size}")
    Integer maxFileSize;

    @Autowired
    ActionAuditService actionAuditService;

    /**
     * UPload file attach len minio va save info vao bang TICKET_ATTACHMENT
     *
     * @param attachmentFiles
     * @param objectId
     * @param authentication
     * @param ticketId
     * @param type
     * @return
     */
    @Override
    public List<TicketAttachmentEntity> saveTicketAttachment(List<FileDTO> attachmentFiles, Long objectId, Authentication authentication, Long ticketId, Long type, Long actionAuditId, String actionName) {
        List<TicketAttachmentEntity> ticketAttachmentEntities = new ArrayList<>();
        for (FileDTO fileDTO : attachmentFiles) {
            byte[] file = Base64.decodeBase64(fileDTO.getBase64Data());
            String rootFolder = fileService.getRootFolder();
            String filePath = rootFolder + File.separator + objectId + File.separator + UUID.randomUUID().toString() + "-" + fileDTO.getFileName();
            if (!FnCommon.checkFileValid(filePath, file, maxFileSize)) {
                throw new EtcException("common.validate.briefcase.invalid");
            }
            TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
            ticketAttachmentEntity.setAttachmentId(ticketAttachmentRepositoryJPA.getNextValSequenceSerial());
            ticketAttachmentEntity.setTicketId(ticketId);
            ticketAttachmentEntity.setFileName(fileDTO.getFileName());
            ticketAttachmentEntity.setFilePath(filePath);
            ticketAttachmentEntity.setType(type);
            ticketAttachmentEntity.setObjectsId(objectId);
            ticketAttachmentEntity.setCreateDate(new Date(System.currentTimeMillis()));
            ticketAttachmentEntity.setCreateUser(FnCommon.getUserLogin(authentication));
            fileService.uploadFile(filePath, file);
            ticketAttachmentEntities.add(ticketAttachmentEntity);

            if (actionAuditId != null) {
                actionAuditService.saveActAuditDetail(actionAuditId, null, ticketAttachmentEntity, ticketAttachmentEntity.getAttachmentId(), ActionAuditDetailEntity.ActionName.INSERT.value);
            }
        }

        if (ticketAttachmentEntities.size() > 0) {
            ticketAttachmentRepositoryJPA.saveAll(ticketAttachmentEntities);
        }
        return ticketAttachmentEntities;
    }

    /***
     * validate định dạng file base 64 va kick thuoc file
     * @param attachmentFiles
     */
    @Override
    public void validateFileAttach(List<FileDTO> attachmentFiles) {
        if (attachmentFiles != null) {
            for (FileDTO fileDTO : attachmentFiles) {
                if (!FnCommon.isNullOrEmpty(fileDTO.getBase64Data())) {
                    if (fileDTO.getBase64Data() != null && !Base64.isBase64(fileDTO.getBase64Data())) {
                        throw new EtcException("crm.file.is.not.base64");
                    }
                }
            }
        }
    }

    /***
     *Get file attach info theo ticketid va type
     * @param ticketAttachmentEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketAttachment(TicketAttachmentEntity ticketAttachmentEntity, Authentication authentication) {
        return ticketAttachmentRepository.getTicketAttachment(ticketAttachmentEntity);
    }


    /***
     * Lay danh sach file attach theo ma phan anh
     * @param params
     * @return
     */
    @Override
    public ResultSelectEntity getTicketAttachment(TicketAttachmentDTO params, Authentication authentication) {
        int page = params.getStartrecord() / params.getPagesize();
        Pageable pageable = PageRequest.of(page, params.getPagesize());
        ResultSelectEntity data = new ResultSelectEntity();
        Optional<List<TicketAttachmentEntity>> listAttachment = ticketAttachmentRepositoryJPA.findByTicketId(params.getTicketId(), pageable);
        if (listAttachment.isPresent()) {
            data.setListData(listAttachment.get());
            data.setCount(listAttachment.get().size());
            return data;
        } else {
            return null;
        }

    }

    /***
     * Them moi file attach
     * @param ticketAttachmentDTO
     * @return
     */
    @Override
    public Object saveTicketAttachment(TicketAttachmentDTO ticketAttachmentDTO, Authentication authentication) {
        if (ticketAttachmentDTO.getAttachmentFiles() != null && !ticketAttachmentDTO.getAttachmentFiles().isEmpty()) {
            validateFileAttach(ticketAttachmentDTO.getAttachmentFiles());
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setActTypeId(ticketAttachmentDTO.getActTypeId());
            actionAuditDTO.setTicketId(ticketAttachmentDTO.getTicketId());
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            return saveTicketAttachment(ticketAttachmentDTO.getAttachmentFiles(), ticketAttachmentDTO.getTicketId(), authentication,
                    ticketAttachmentDTO.getTicketId(), ticketAttachmentDTO.getType(), actionAuditEntity.getActionAuditId(), ActionAuditDetailEntity.ActionName.INSERT.value);
        }
        return null;
    }


    /**
     * Download ticket attachment
     *
     * @param ticketAttachmentId
     * @param authentication
     * @return
     */
    @Override
    public Object downloadTicketAttachment(Long ticketAttachmentId, Authentication authentication, HttpServletResponse response) throws IOException {
        Optional<TicketAttachmentEntity> optionTicketAttachmentEntity = ticketAttachmentRepositoryJPA.findById(ticketAttachmentId);
        if (optionTicketAttachmentEntity.isPresent()) {
            byte[] fileByte = fileService.getFile(optionTicketAttachmentEntity.get().getFilePath());
            FnCommon.responseFile(response, fileByte, optionTicketAttachmentEntity.get().getFileName());
        }

        return null;
    }


    /**
     * Delete ticket attachment
     *
     * @param ticketAttachmentId
     * @param authentication
     * @return
     */
    @Override
    public void deleteTicketAttachment(Long ticketAttachmentId, Authentication authentication) {
        Optional<TicketAttachmentEntity> optionTicketAttachmentEntity = ticketAttachmentRepositoryJPA.findById(ticketAttachmentId);
        if (optionTicketAttachmentEntity.isPresent()) {
            fileService.removeFile(optionTicketAttachmentEntity.get().getFilePath());
            ticketAttachmentRepositoryJPA.deleteById(ticketAttachmentId);
        }
    }


    /**
     * @param ticketAttachmentId
     * @param authentication
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    public Object viewTicketAttachment(Long ticketAttachmentId, Authentication authentication, HttpServletResponse response) throws IOException {
        Optional<TicketAttachmentEntity> optionTicketAttachmentEntity = ticketAttachmentRepositoryJPA.findById(ticketAttachmentId);
        return optionTicketAttachmentEntity.map(ticketAttachmentEntity -> fileService.getFile(ticketAttachmentEntity.getFilePath())).orElse(null);
    }
}
