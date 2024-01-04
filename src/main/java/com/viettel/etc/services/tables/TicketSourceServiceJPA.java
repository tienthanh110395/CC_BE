package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSourceRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_source
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketSourceServiceJPA {

    @Autowired
    TicketSourceRepositoryJPA ticketSourceRepositoryJPA;

    public List<TicketSourceEntity> findAll() {
        return this.ticketSourceRepositoryJPA.findAll();
    }

    public TicketSourceEntity save(TicketSourceEntity ticketSourceEntity) {
        return this.ticketSourceRepositoryJPA.save(ticketSourceEntity);
    }

    public Optional<TicketSourceEntity> findById(Long id) {
        return this.ticketSourceRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketSourceRepositoryJPA.deleteById(id);
    }

    public TicketSourceEntity getOne(Long id) {
        return this.ticketSourceRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketSourceRepositoryJPA.existsById(id);
    }


}
