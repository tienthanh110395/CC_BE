package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketAdjustChargeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketAdjustChargeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_adjust_charge
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:44 ICT 2021
 */
@Service
public class TicketAdjustChargeServiceJPA {

    @Autowired
    TicketAdjustChargeRepositoryJPA ticketAdjustChargeRepositoryJPA;

    public List<TicketAdjustChargeEntity> findAll() {
        return this.ticketAdjustChargeRepositoryJPA.findAll();
    }

    public TicketAdjustChargeEntity save(TicketAdjustChargeEntity ticketAdjustChargeEntity) {
        return this.ticketAdjustChargeRepositoryJPA.save(ticketAdjustChargeEntity);
    }

    public Optional<TicketAdjustChargeEntity> findById(Long id) {
        return this.ticketAdjustChargeRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketAdjustChargeRepositoryJPA.deleteById(id);
    }

    public TicketAdjustChargeEntity getOne(Long id) {
        return this.ticketAdjustChargeRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketAdjustChargeRepositoryJPA.existsById(id);
    }


}
