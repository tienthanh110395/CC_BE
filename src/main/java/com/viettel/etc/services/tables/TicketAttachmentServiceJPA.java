package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAttachmentRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_attachment
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketAttachmentServiceJPA {

    @Autowired
    TicketAttachmentRepositoryJPA ticketAttachmentRepositoryJPA;

    public List<TicketAttachmentEntity> findAll() {
        return this.ticketAttachmentRepositoryJPA.findAll();
    }

    public TicketAttachmentEntity save(TicketAttachmentEntity ticketAttachmentEntity) {
        return this.ticketAttachmentRepositoryJPA.save(ticketAttachmentEntity);
    }

    public Optional<TicketAttachmentEntity> findById(Long id) {
        return this.ticketAttachmentRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketAttachmentRepositoryJPA.deleteById(id);
    }

    public TicketAttachmentEntity getOne(Long id) {
        return this.ticketAttachmentRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketAttachmentRepositoryJPA.existsById(id);
    }

    public List<TicketAttachmentEntity> getTicketAttachment(Long ticketId, List<Long> types) {
        return ticketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(ticketId, types);
    }

    public List<TicketAttachmentEntity> getTicketAttachment(Long ticketId, Long type) {
        return ticketAttachmentRepositoryJPA.findAllByTicketIdAndType(ticketId, type);
    }

    public List<TicketAttachmentEntity> getByTicketIdAndTypeAssign(Long ticketId) {
        List<Long> types = new ArrayList<>();
        types.add(TicketAttachmentEntity.Type.TICKET_ASSIGN.value);
        types.add(TicketAttachmentEntity.Type.TICKET_ASSIGN_PROCESS.value);
        return ticketAttachmentRepositoryJPA.findAllByTicketIdAndTypeIn(ticketId, types);
    }
}
