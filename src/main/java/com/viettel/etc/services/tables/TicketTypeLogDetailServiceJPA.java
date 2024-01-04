/*
package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketTypeLogDetailRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketTypeLogDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketTypeLogDetailServiceJPA {
    @Autowired
    TicketTypeLogDetailRepositoryJPA ticketTypeLogDetailRepositoryJPA;

    public List<TicketTypeLogDetailEntity> findAll() {
        return this.ticketTypeLogDetailRepositoryJPA.findAll();
    }

    public TicketTypeLogDetailEntity save(TicketTypeLogDetailEntity ticketTypeLogEntity) {
        return this.ticketTypeLogDetailRepositoryJPA.save(ticketTypeLogEntity);
    }

    public Optional<TicketTypeLogDetailEntity> findById(Long id) {
        return this.ticketTypeLogDetailRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketTypeLogDetailRepositoryJPA.deleteById(id);
    }

    public TicketTypeLogDetailEntity getOne(Long id) {
        return this.ticketTypeLogDetailRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketTypeLogDetailRepositoryJPA.existsById(id);
    }

    public List<TicketTypeLogDetailEntity> saveAll(List<TicketTypeLogDetailEntity> dataList) {
        return this.ticketTypeLogDetailRepositoryJPA.saveAll(dataList);
    }
}
*/
