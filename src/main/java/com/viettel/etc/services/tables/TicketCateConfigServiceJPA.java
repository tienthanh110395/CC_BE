package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketCateConfigRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketEntity;
import com.viettel.etc.repositories.tables.entities.TicketSlaEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_type
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Service
public class TicketCateConfigServiceJPA {
    @Autowired
    TicketCateConfigRepositoryJPA ticketCateConfigRepositoryJPA;

    public void updateStatusMultiple(List<Long> lstIds, Long status) {
        this.ticketCateConfigRepositoryJPA.updateStatusMultiple(lstIds, status);
    }

    public Long getTopupSequenceNo() {
        return ticketCateConfigRepositoryJPA.getNextValSequenceNo();
    }

}
