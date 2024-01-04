package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_type
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketTypeServiceJPA {

    @Autowired
    TicketTypeRepositoryJPA ticketTypeRepositoryJPA;

    public List<TicketTypeEntity> findAll() {
        return this.ticketTypeRepositoryJPA.findAll();
    }
    public List<TicketTypeEntity> findAllById(List<Long> id) {
        return this.ticketTypeRepositoryJPA.findAllById(id);
    }

    public TicketTypeEntity save(TicketTypeEntity ticketTypeEntity) {
        return this.ticketTypeRepositoryJPA.save(ticketTypeEntity);
    }

    public Optional<TicketTypeEntity> findById(Long id) {
        return this.ticketTypeRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketTypeRepositoryJPA.deleteById(id);
    }

    public TicketTypeEntity getOne(Long id) {
        return this.ticketTypeRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketTypeRepositoryJPA.existsById(id);
    }

    public List<TicketTypeEntity> saveAll(List<TicketTypeEntity> dataList) {
        return this.ticketTypeRepositoryJPA.saveAll(dataList);
    }

    public TicketTypeEntity findByCodeAndLevelTt(String ticketTypeCode, Long levelTt) {
        return this.ticketTypeRepositoryJPA.findByCodeAndLevelTt(ticketTypeCode, levelTt);
    }

    public TicketTypeEntity findByCodeAndTicketTypeIdNot(String ticketTypeCode, Long ticketTypeId) {
        return this.ticketTypeRepositoryJPA.findByCodeAndTicketTypeIdNot(ticketTypeCode, ticketTypeId);
    }

    public List<TicketTypeEntity> findByTicketTypeLevel(Long levelTT) {
        return this.ticketTypeRepositoryJPA.findByLevelTt(levelTT);
    }

    public  int doDelete( Long ticketTypeId) {
        return this.ticketTypeRepositoryJPA.doDelete(ticketTypeId);
    }

    public TicketTypeEntity saveAndFlush(TicketTypeEntity ticketTypeEntity) {
        return this.ticketTypeRepositoryJPA.saveAndFlush(ticketTypeEntity);
    }
}
