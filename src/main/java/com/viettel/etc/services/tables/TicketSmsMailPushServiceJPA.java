package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSmsMailPushRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_sms_mail_push
 *
 * @author ToolGen
 * @date Tue Jun 01 11:14:42 ICT 2021
 */
@Service
public class TicketSmsMailPushServiceJPA {

    @Autowired
    TicketSmsMailPushRepositoryJPA ticketSmsMailPushRepositoryJPA;

    public List<TicketSmsMailPushEntity> findAll() {
        return this.ticketSmsMailPushRepositoryJPA.findAll();
    }

    public TicketSmsMailPushEntity save(TicketSmsMailPushEntity ticketSmsMailPushEntity) {
        return this.ticketSmsMailPushRepositoryJPA.save(ticketSmsMailPushEntity);
    }

    public Optional<TicketSmsMailPushEntity> findById(Long id) {
        return this.ticketSmsMailPushRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketSmsMailPushRepositoryJPA.deleteById(id);
    }

    public TicketSmsMailPushEntity getOne(Long id) {
        return this.ticketSmsMailPushRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketSmsMailPushRepositoryJPA.existsById(id);
    }


}
