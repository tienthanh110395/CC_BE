package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSiteRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketExpireCauseEntity;
import com.viettel.etc.repositories.tables.entities.TicketSiteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_site
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketSiteServiceJPA {

    @Autowired
    TicketSiteRepositoryJPA ticketSiteRepositoryJPA;

    public List<TicketSiteEntity> findAll() {
        return this.ticketSiteRepositoryJPA.findAll();
    }

    public TicketSiteEntity save(TicketSiteEntity ticketSiteEntity) {
        return this.ticketSiteRepositoryJPA.save(ticketSiteEntity);
    }
    public TicketSiteEntity saveAndFlush(TicketSiteEntity ticketSiteEntity) {
        return this.ticketSiteRepositoryJPA.saveAndFlush(ticketSiteEntity);
    }
    public List<TicketSiteEntity> findAllById(List<Long> id) {
        return this.ticketSiteRepositoryJPA.findAllById(id);
    }
    public Optional<TicketSiteEntity> findById(Long id) {
        return this.ticketSiteRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketSiteRepositoryJPA.deleteById(id);
    }

    public TicketSiteEntity getOne(Long id) {
        return this.ticketSiteRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketSiteRepositoryJPA.existsById(id);
    }

    public  int doDelete( Long ticketSiteId) {
        return this.ticketSiteRepositoryJPA.doDelete(ticketSiteId);
    }

    public Long getTopupSequenceNo() {
        return ticketSiteRepositoryJPA.getNextValSequenceNo();
    }
}
