package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSiteUserEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Create Service For Table Name Ticket_site_user
 *
 * @author ToolGen
 * @date Mon Apr 05 09:30:08 ICT 2021
 */
@Service
public class TicketSiteUserServiceJPA {

    @Autowired
    TicketSiteUserRepositoryJPA ticketSiteUserRepositoryJPA;

    public List<TicketSiteUserEntity> findAll() {
        return this.ticketSiteUserRepositoryJPA.findAll();
    }

    public TicketSiteUserEntity save(TicketSiteUserEntity ticketSiteUserEntity) {
        return this.ticketSiteUserRepositoryJPA.save(ticketSiteUserEntity);
    }

    public TicketSiteUserEntity findBySiteIdAndUserName(Long siteId,String userName) {
        return this.ticketSiteUserRepositoryJPA.findBySiteIdAndUserName(siteId,userName);
    }

    public     List<TicketSiteUserEntity> findBySiteId(Long siteId) {
        return this.ticketSiteUserRepositoryJPA.findBySiteId(siteId);
    }

    public     List<TicketSiteUserEntity> saveAll(List<TicketSiteUserEntity> entities) {
        return this.ticketSiteUserRepositoryJPA.saveAll(entities);
    }

    public Optional<TicketSiteUserEntity> findById(Long id) {
        return this.ticketSiteUserRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketSiteUserRepositoryJPA.deleteById(id);
    }

    public TicketSiteUserEntity getOne(Long id) {
        return this.ticketSiteUserRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketSiteUserRepositoryJPA.existsById(id);
    }

    public List<String> findDistinctByUserNameIn() {
        return this.ticketSiteUserRepositoryJPA.findDistinctByUserName();
    }

}
