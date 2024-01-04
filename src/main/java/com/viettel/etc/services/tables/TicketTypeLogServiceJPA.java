/*
package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketTypeLogRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketTypeLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketTypeLogServiceJPA {
    @Autowired
    TicketTypeLogRepositoryJPA ticketTypeLogRepositoryJPA;

    public List<TicketTypeLogEntity> findAll() {
        return this.ticketTypeLogRepositoryJPA.findAll();
    }

    public TicketTypeLogEntity save(TicketTypeLogEntity ticketTypeLogEntity) {
        return this.ticketTypeLogRepositoryJPA.save(ticketTypeLogEntity);
    }

    public Optional<TicketTypeLogEntity> findById(Long id) {
        return this.ticketTypeLogRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketTypeLogRepositoryJPA.deleteById(id);
    }

    public TicketTypeLogEntity getOne(Long id) {
        return this.ticketTypeLogRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketTypeLogRepositoryJPA.existsById(id);
    }

    public List<TicketTypeLogEntity> saveAll(List<TicketTypeLogEntity> dataList) {
        return this.ticketTypeLogRepositoryJPA.saveAll(dataList);
    }
}
*/
