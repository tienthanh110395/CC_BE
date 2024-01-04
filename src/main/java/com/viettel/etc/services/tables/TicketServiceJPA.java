package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:44 ICT 2021
 */
@Service
public class TicketServiceJPA {

    @Autowired
    TicketRepositoryJPA ticketRepositoryJPA;

    public List<TicketEntity> findAll() {
        return this.ticketRepositoryJPA.findAll();
    }

    public TicketEntity save(TicketEntity ticketEntity) {
        return this.ticketRepositoryJPA.save(ticketEntity);
    }

    public Optional<TicketEntity> findById(Long id) {
        return this.ticketRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketRepositoryJPA.deleteById(id);
    }

    public TicketEntity getOne(Long id) {
        return this.ticketRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketRepositoryJPA.existsById(id);
    }

    public boolean existsByTicketIdAndProcessUserIsNotNull(List<Long> ticketIds) {
        List<TicketEntity> ticketEntities = ticketRepositoryJPA.findAllByTicketIdInAndProcessUserIsNotNull(ticketIds);
        return ticketEntities == null || ticketEntities.isEmpty();
    }

    public List<TicketEntity> saveAll(List<TicketEntity> ticketEntities) {
        return this.ticketRepositoryJPA.saveAll(ticketEntities);
    }

    public List<TicketEntity> findByL3TicketTypeId(Long l3TicketTypeId) {
        return this.ticketRepositoryJPA.findByL3TicketTypeId(l3TicketTypeId);
    }

}
