package com.viettel.etc.repositories;

import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

public interface TicketAttachmentRepository {
    ResultSelectEntity getTicketAttachment(TicketAttachmentEntity ticketAttachmentEntity);
}
