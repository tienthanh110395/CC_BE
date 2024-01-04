package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketSiteConfigRepositoryJPA extends JpaRepository<TicketSiteEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE TICKET_SITE SET status =:status WHERE site_id IN (:lstIds)", nativeQuery = true)
    void updateStatusMultiple(List<Long> lstIds, Long status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TICKET_SITE WHERE site_id IN (:lstIds)", nativeQuery = true)
    void onDelete(List<Long> lstIds);

    TicketSiteEntity findBySiteCodeAndSiteIdNot(String siteCode, Long siteId);

    TicketSiteEntity findBySiteCode(String siteCode);
}
