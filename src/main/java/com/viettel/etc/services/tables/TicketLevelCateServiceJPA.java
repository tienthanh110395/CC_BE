package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.TicketLevelCateRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.OtherCategoriesEntity;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
import java.util.List;
import java.util.Optional;

@Service
public class TicketLevelCateServiceJPA {
    @Autowired
    TicketLevelCateRepositoryJPA ticketLevelCateRepositoryJPA;

    public OtherCategoriesEntity save(OtherCategoriesEntity otherCategoriesEntity) {
        return this.ticketLevelCateRepositoryJPA.save(otherCategoriesEntity);
    }
    public OtherCategoriesEntity saveAndFlush(OtherCategoriesEntity otherCategoriesEntity) {
        return this.ticketLevelCateRepositoryJPA.saveAndFlush(otherCategoriesEntity);
    }
    public List<OtherCategoriesEntity> saveAll(List<OtherCategoriesEntity> dataList) {
        return this.ticketLevelCateRepositoryJPA.saveAll(dataList);
    }
    public OtherCategoriesEntity getOne(Long id) {
        return this.ticketLevelCateRepositoryJPA.getOne(id);
    }
    public void deleteById(Long id) {
        this.ticketLevelCateRepositoryJPA.deleteById(id);
    }
    public void updateStatus(Long ticketLevelCateId, Long isActive) {
        this.ticketLevelCateRepositoryJPA.updateStatus(ticketLevelCateId, isActive);
    }
    public OtherCategoriesEntity findByCodeAndTypeAndIdNot(String ticketLevelCateCode, Long type, Long ticketLevelCateId) {
        return this.ticketLevelCateRepositoryJPA.findByCodeAndTypeAndIdNot(ticketLevelCateCode,type, ticketLevelCateId);
    }

    public Optional<OtherCategoriesEntity> findById(Long id) {
        return this.ticketLevelCateRepositoryJPA.findById(id);
    }

    public Long getTopupSequenceNo() {
        return ticketLevelCateRepositoryJPA.getNextValSequenceNo();
    }
}
