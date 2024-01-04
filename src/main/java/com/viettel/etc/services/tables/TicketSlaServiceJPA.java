package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSlaRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.TicketSlaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_sla
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketSlaServiceJPA {

    @Autowired
    TicketSlaRepositoryJPA ticketSlaRepositoryJPA;

    public List<TicketSlaEntity> findAll() {
        return this.ticketSlaRepositoryJPA.findAll();
    }

    public TicketSlaEntity save(TicketSlaEntity ticketSlaEntity) {
        return this.ticketSlaRepositoryJPA.save(ticketSlaEntity);
    }

    public Optional<TicketSlaEntity> findById(Long id) {
        return this.ticketSlaRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketSlaRepositoryJPA.deleteById(id);
    }

    public TicketSlaEntity getOne(Long id) {
        return this.ticketSlaRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketSlaRepositoryJPA.existsById(id);
    }

    public TicketSlaEntity getByTicketTypeId(Long ticketTypeId) {
        return this.ticketSlaRepositoryJPA.findByTicketTypeId(ticketTypeId);
    }

    public List<TicketSlaEntity> findByTicketTypeId(Long ticketTypeId) {
        return this.ticketSlaRepositoryJPA.findAllByTicketTypeId(ticketTypeId);
    }

    public List<TicketSlaEntity> saveAll(List<TicketSlaEntity> ticketSlaEntityList) {
        return ticketSlaRepositoryJPA.saveAll(ticketSlaEntityList);
    }

    public void onDelete(Long ticketTypeId) {
        this.ticketSlaRepositoryJPA.onDelete(ticketTypeId);
    }
}
