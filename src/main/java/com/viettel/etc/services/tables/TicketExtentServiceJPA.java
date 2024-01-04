package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.repositories.tables.TicketExtentRepositoryJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Autogen class: Create Service For Table Name Ticket_extent
 *
 * @author ToolGen
 * @date Fri Jan 07 16:32:09 ICT 2022
 */
@Service
public class TicketExtentServiceJPA {

    @Autowired
    TicketExtentRepositoryJPA ticket_extent;

    public List<TicketExtentEntity> findAll() {
        return this.ticket_extent.findAll();
    }

    public TicketExtentEntity save(TicketExtentEntity Ticket_extent) {
        return this.ticket_extent.save(Ticket_extent);
    }

    public Optional<TicketExtentEntity> findById(Long id) {
        return this.ticket_extent.findById(id);
    }

    public List<TicketExtentEntity> saveAll(List<TicketExtentEntity> ticketExtentEntityList) {
        return this.ticket_extent.saveAll(ticketExtentEntityList);
    }

    public void deleteById(Long id) {
        this.ticket_extent.deleteById(id);
    }

    public TicketExtentEntity getOne(Long id) {
        return this.ticket_extent.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticket_extent.existsById(id);
    }

    public TicketExtentEntity findByTicketId(Long ticketId) {
        return this.ticket_extent.findByTicketId(ticketId);
    }

    public TicketExtentEntity getByTicketIdAndStatus(Long ticketId, Long status) {
        return this.ticket_extent.getByTicketIdAndStatus(ticketId, status);
    }
}