package com.viettel.etc.services.tables;


import com.viettel.etc.repositories.tables.TicketCateStatisticRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.StatisticTypeEntity;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import com.viettel.etc.repositories.tables.entities.TicketExpireCauseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketCateStatisticServiceJPA {

    @Autowired
    TicketCateStatisticRepositoryJPA TicketCateStatisticRepositoryJPA;

    public void updateStatusTicketStatisticType(Long statisticTypeId, Long status) {
        this.TicketCateStatisticRepositoryJPA.updateStatusTicketStatisticType(statisticTypeId, status);
    }

    public void updateStatusMultipleTicketStatistic(List<Long> lstIds, Long status) {
        this.TicketCateStatisticRepositoryJPA.updateStatusMultipleTicketStatistic(lstIds, status);
    }

    public StatisticTypeEntity getOne(Long id) {
        return this.TicketCateStatisticRepositoryJPA.getOne(id);
    }

    public void deleteStatisticById(Long id) {
        this.TicketCateStatisticRepositoryJPA.deleteById(id);
    }

    public StatisticTypeEntity save(StatisticTypeEntity statisticTypeEntity) {
        return this.TicketCateStatisticRepositoryJPA.save(statisticTypeEntity);
    }
    public StatisticTypeEntity saveAndFlush(StatisticTypeEntity statisticTypeEntity) {
        return this.TicketCateStatisticRepositoryJPA.saveAndFlush(statisticTypeEntity);
    }
    public List<StatisticTypeEntity> findAllById(List<Long> id) {
        return this.TicketCateStatisticRepositoryJPA.findAllById(id);
    }

    public Optional<StatisticTypeEntity> findById(Long id) {
        return this.TicketCateStatisticRepositoryJPA.findById(id);
    }

    public StatisticTypeEntity findByCodeAndStatisticTypeIdNot(String ticketCateStatisticsCode, Long statisticTypeId) {
        return this.TicketCateStatisticRepositoryJPA.findByCodeAndStatisticTypeIdNot(ticketCateStatisticsCode, statisticTypeId);
    }

    public StatisticTypeEntity findAllByCodeAndLevelStatistic(String ticketCateStatisticsCode, Long levelStatistic) {
        return this.TicketCateStatisticRepositoryJPA.findAllByCodeAndLevelStatistic(ticketCateStatisticsCode, levelStatistic);
    }

    public List<StatisticTypeEntity> saveAll(List<StatisticTypeEntity> entities) {
        return this.TicketCateStatisticRepositoryJPA.saveAll(entities);
    }

    public  int doDelete( Long statisticTypeId) {
        return this.TicketCateStatisticRepositoryJPA.doDelete(statisticTypeId);
    }

    public Long getTopupSequenceNo() {
        return TicketCateStatisticRepositoryJPA.getNextValSequenceNo();
    }

}
