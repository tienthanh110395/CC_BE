package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketErrorCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import com.viettel.etc.repositories.tables.entities.TicketExpireCauseEntity;
import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_error_cause
 *
 * @author ToolGen
 * @date Thu Jun 03 13:45:57 ICT 2021
 */
@Service
public class TicketErrorCauseServiceJPA {

    @Autowired
    TicketErrorCauseRepositoryJPA ticketErrorCauseRepositoryJPA;

    public List<TicketErrorCauseEntity> findAll() {
        return this.ticketErrorCauseRepositoryJPA.findAll();
    }

    public TicketErrorCauseEntity save(TicketErrorCauseEntity ticketErrorCauseEntity) {
        return this.ticketErrorCauseRepositoryJPA.save(ticketErrorCauseEntity);
    }
    public TicketErrorCauseEntity saveAndFlush(TicketErrorCauseEntity ticketErrorCauseEntity) {
        return this.ticketErrorCauseRepositoryJPA.saveAndFlush(ticketErrorCauseEntity);
    }
    public List<TicketErrorCauseEntity> findAllById(List<Long> id) {
        return this.ticketErrorCauseRepositoryJPA.findAllById(id);
    }
    public Optional<TicketErrorCauseEntity> findById(Long id) {
        return this.ticketErrorCauseRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketErrorCauseRepositoryJPA.deleteById(id);
    }

    public TicketErrorCauseEntity getOne(Long id) {
        return this.ticketErrorCauseRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketErrorCauseRepositoryJPA.existsById(id);
    }

    public void updateStatusMultipleTicketErrorCause(List<Long> lstIds, Long status) {
        this.ticketErrorCauseRepositoryJPA.updateStatusMultipleTicketErrorCause(lstIds, status);
    }
    public void updateStatusTicketErrorCause(Long ticketErrorCauseId, Long status) {
        this.ticketErrorCauseRepositoryJPA.updateStatusTicketErrorCause(ticketErrorCauseId, status);
    }

    public TicketErrorCauseEntity findByCodeAndTicketErrorCauseIdNot(String code, Long ticketErrorCauseId) {
        return this.ticketErrorCauseRepositoryJPA.findByCodeAndTicketErrorCauseIdNot(code, ticketErrorCauseId);
    }
    public  int doDelete( Long ticketErrorCauseId) {
        return this.ticketErrorCauseRepositoryJPA.doDelete(ticketErrorCauseId);
    }
    public Long getTopupSequenceNo() {
        return ticketErrorCauseRepositoryJPA.getNextValSequenceNo();
    }
}
