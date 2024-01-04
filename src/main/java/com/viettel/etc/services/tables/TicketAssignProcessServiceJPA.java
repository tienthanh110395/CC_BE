package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAssignProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignProcessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_assign_process
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketAssignProcessServiceJPA {

    @Autowired
    TicketAssignProcessRepositoryJPA ticketAssignProcessRepositoryJPA;

    public List<TicketAssignProcessEntity> findAll() {
        return this.ticketAssignProcessRepositoryJPA.findAll();
    }

    public TicketAssignProcessEntity save(TicketAssignProcessEntity ticketAssignProcessEntity) {
        return this.ticketAssignProcessRepositoryJPA.save(ticketAssignProcessEntity);
    }

    public Optional<TicketAssignProcessEntity> findById(Long id) {
        return this.ticketAssignProcessRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketAssignProcessRepositoryJPA.deleteById(id);
    }

    public TicketAssignProcessEntity getOne(Long id) {
        return this.ticketAssignProcessRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketAssignProcessRepositoryJPA.existsById(id);
    }


}
