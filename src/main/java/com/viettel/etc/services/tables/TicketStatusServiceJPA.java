package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketStatusRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_status
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketStatusServiceJPA {

    @Autowired
    TicketStatusRepositoryJPA ticketStatusRepositoryJPA;

    public List<TicketStatusEntity> findAll() {
        return this.ticketStatusRepositoryJPA.findAll();
    }

    public TicketStatusEntity save(TicketStatusEntity ticketStatusEntity) {
        return this.ticketStatusRepositoryJPA.save(ticketStatusEntity);
    }

    public Optional<TicketStatusEntity> findById(Long id) {
        return this.ticketStatusRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketStatusRepositoryJPA.deleteById(id);
    }

    public TicketStatusEntity getOne(Long id) {
        return this.ticketStatusRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketStatusRepositoryJPA.existsById(id);
    }


}
