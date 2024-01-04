package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAssignLogRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAssignLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_assign_log
 *
 * @author ToolGen
 * @date Thu Mar 25 13:34:13 ICT 2021
 */
@Service
public class TicketAssignLogServiceJPA {

    @Autowired
    TicketAssignLogRepositoryJPA ticketAssignLogRepositoryJPA;

    public List<TicketAssignLogEntity> findAll() {
        return this.ticketAssignLogRepositoryJPA.findAll();
    }

    public TicketAssignLogEntity save(TicketAssignLogEntity ticketAssignLogEntity) {
        return this.ticketAssignLogRepositoryJPA.save(ticketAssignLogEntity);
    }

    public Optional<TicketAssignLogEntity> findById(Long id) {
        return this.ticketAssignLogRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketAssignLogRepositoryJPA.deleteById(id);
    }

    public TicketAssignLogEntity getOne(Long id) {
        return this.ticketAssignLogRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketAssignLogRepositoryJPA.existsById(id);
    }


}
