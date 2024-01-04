package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_site
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketSiteRepositoryJPA extends JpaRepository<TicketSiteEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete (select *FROM TICKET_SITE  ts LEFT JOIN TICKET_SITE  ts2 ON ts.parent_id = ts2.SITE_ID LEFT JOIN TICKET_SITE  ts3 ON ts2.parent_id = ts3.SITE_ID WHERE ts.parent_id IN ( :ticketSiteId ) OR ( ts.SITE_ID IN ( :ticketSiteId) OR ts2.parent_id IN ( :ticketSiteId ) ))" ,nativeQuery = true)
    int doDelete(Long ticketSiteId);

    @Query(value = "SELECT CC.TICKET_SITE_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceNo();
}
