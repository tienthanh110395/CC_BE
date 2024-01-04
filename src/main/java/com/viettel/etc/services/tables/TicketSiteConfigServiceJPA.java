package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketSiteConfigRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSiteEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketSiteConfigServiceJPA {
    @Autowired
    TicketSiteConfigRepositoryJPA ticketSiteConfigRepositoryJPA;


    public List<TicketSiteEntity> findAll() {
        return this.ticketSiteConfigRepositoryJPA.findAll();
    }

    public TicketSiteEntity save(TicketSiteEntity ticketTypeEntity) {
        return this.ticketSiteConfigRepositoryJPA.save(ticketTypeEntity);
    }

    public Optional<TicketSiteEntity> findById(Long id) {
        return this.ticketSiteConfigRepositoryJPA.findById(id);
    }

    public void deleteById(Long id) {
        this.ticketSiteConfigRepositoryJPA.deleteById(id);
    }

    public TicketSiteEntity getOne(Long id) {
        return this.ticketSiteConfigRepositoryJPA.getOne(id);
    }

    public Boolean existsById(Long id) {
        return this.ticketSiteConfigRepositoryJPA.existsById(id);
    }

    public List<TicketSiteEntity> saveAll(List<TicketSiteEntity> dataList) {
        return this.ticketSiteConfigRepositoryJPA.saveAll(dataList);
    }

    public void updateStatusMultiple(List<Long> lstIds, Long status) {
        this.ticketSiteConfigRepositoryJPA.updateStatusMultiple(lstIds, status);
    }

    public void onDelete(List<Long> lstIds) {
        this.ticketSiteConfigRepositoryJPA.onDelete(lstIds);
    }

    public TicketSiteEntity findBySiteCodeAndSiteIdNot(String siteCode, Long siteId) {
        return this.ticketSiteConfigRepositoryJPA.findBySiteCodeAndSiteIdNot(siteCode, siteId);
    }

    public TicketSiteEntity findBySiteCode(String siteCode) {
        return this.ticketSiteConfigRepositoryJPA.findBySiteCode(siteCode);
    }
}
