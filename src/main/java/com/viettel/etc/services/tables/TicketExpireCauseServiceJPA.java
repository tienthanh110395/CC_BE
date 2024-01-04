package com.viettel.etc.services.tables;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.repositories.tables.TicketExpireCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketExpireCauseEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.services.TicketExpireCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_expire_cause
 *
 * @author ToolGen
 * @date Thu Jun 03 11:31:39 ICT 2021
 */
@Service
public class TicketExpireCauseServiceJPA {

    @Autowired
    TicketExpireCauseRepositoryJPA ticketExpireCauseRepositoryJPA;

    public List<TicketExpireCauseEntity> findAll() {
        return this.ticketExpireCauseRepositoryJPA.findAll();
    }

    public TicketExpireCauseEntity save(TicketExpireCauseEntity ticketExpireCauseEntity) {
        return this.ticketExpireCauseRepositoryJPA.save(ticketExpireCauseEntity);
    }
    public TicketExpireCauseEntity saveAndFlush(TicketExpireCauseEntity ticketExpireCauseEntity) {
        return this.ticketExpireCauseRepositoryJPA.saveAndFlush(ticketExpireCauseEntity);
    }

    public Optional<TicketExpireCauseEntity> findById(Long id) {
        return this.ticketExpireCauseRepositoryJPA.findById(id);
    }
    public List<TicketExpireCauseEntity> findAllById(List<Long> id) {
        return this.ticketExpireCauseRepositoryJPA.findAllById(id);
    }
    public void deleteById(Long id) {
        this.ticketExpireCauseRepositoryJPA.deleteById(id);
    }

    public TicketExpireCauseEntity getOne(Long id) {
        return this.ticketExpireCauseRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketExpireCauseRepositoryJPA.existsById(id);
    }

    public List<TicketExpireCauseEntity> findAllByCodeAndLevelExpire(String code, Long levelTt) {
        return this.ticketExpireCauseRepositoryJPA.findAllByCodeAndLevelExpire(code, levelTt);
    }

    public void updateStatus(Long ticketExpireCauseId, Long status) {
        this.ticketExpireCauseRepositoryJPA.updateStatus(ticketExpireCauseId, status);
    }

    public void updateStatusMultiple(List<Long> lstIds, Long status) {
        this.ticketExpireCauseRepositoryJPA.updateStatusMultiple(lstIds, status);
    }

    public TicketExpireCauseEntity findByCodeAndTicketTypeIdNot(String expireCauseCode, Long ticketExpireCauseId) {
        return this.ticketExpireCauseRepositoryJPA.findByCodeAndAndTicketExpireCauseIdNot(expireCauseCode, ticketExpireCauseId);
    }

    public TicketExpireCauseEntity findByCodeAndLevelExpire(String expireCauseCode, Long levelExpire) {
        return this.ticketExpireCauseRepositoryJPA.findByCodeAndLevelExpire(expireCauseCode, levelExpire);
    }
    public  int doDelete( Long ticketExpireCauseId) {
        return this.ticketExpireCauseRepositoryJPA.doDelete(ticketExpireCauseId);
    }
    public Long getTopupSequenceNo() {
        return ticketExpireCauseRepositoryJPA.getNextValSequenceNo();
    }

}
