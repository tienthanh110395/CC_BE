package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAssignRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_assign
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:44 ICT 2021
 */
@Service
public class TicketAssignServiceJPA {

    @Autowired
    TicketAssignRepositoryJPA ticketAssignRepositoryJPA;

    public List<TicketAssignEntity> findAll() {
        return this.ticketAssignRepositoryJPA.findAll();
    }

    public TicketAssignEntity save(TicketAssignEntity ticketAssignEntity) {
        return this.ticketAssignRepositoryJPA.save(ticketAssignEntity);
    }

    public Optional<TicketAssignEntity> findById(Long id) {
        return this.ticketAssignRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketAssignRepositoryJPA.deleteById(id);
    }

    public TicketAssignEntity getOne(Long id) {
        return this.ticketAssignRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketAssignRepositoryJPA.existsById(id);
    }


}
