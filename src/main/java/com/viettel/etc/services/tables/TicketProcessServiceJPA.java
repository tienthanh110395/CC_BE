package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketProcessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_process
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketProcessServiceJPA {

    @Autowired
    TicketProcessRepositoryJPA ticketProcessRepositoryJPA;

    public List<TicketProcessEntity> findAll() {
        return this.ticketProcessRepositoryJPA.findAll();
    }

    public TicketProcessEntity save(TicketProcessEntity ticketProcessEntity) {
        return this.ticketProcessRepositoryJPA.save(ticketProcessEntity);
    }

    public Optional<TicketProcessEntity> findById(Long id) {
        return this.ticketProcessRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketProcessRepositoryJPA.deleteById(id);
    }

    public TicketProcessEntity getOne(Long id) {
        return this.ticketProcessRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketProcessRepositoryJPA.existsById(id);
    }


}
