package com.viettel.etc.services;

import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.TicketAttachmentDTO;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface TicketAttachmentService {
    List<TicketAttachmentEntity> saveTicketAttachment(List<FileDTO> attachmentFiles, Long objectId, Authentication authentication, Long ticketId, Long type, Long actionAuditId, String actionName);

    void validateFileAttach(List<FileDTO> attachmentFiles);

    ResultSelectEntity getTicketAttachment(TicketAttachmentEntity ticketAttachmentEntity, Authentication authentication);

    ResultSelectEntity getTicketAttachment(TicketAttachmentDTO ticketAttachmentDTO, Authentication authentication);

    Object saveTicketAttachment(TicketAttachmentDTO ticketAttachmentDTO, Authentication authentication);

    Object downloadTicketAttachment(Long ticketAttachmentId, Authentication authentication, HttpServletResponse response) throws IOException;

    void deleteTicketAttachment(Long ticketAttachmentId, Authentication authentication);

    Object viewTicketAttachment(Long ticketAttachmentId, Authentication authentication, HttpServletResponse response) throws IOException;
}
