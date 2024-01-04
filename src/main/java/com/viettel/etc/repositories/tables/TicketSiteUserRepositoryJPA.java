package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketSiteUserEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_site_user
 *
 * @author ToolGen
 * @date Mon Apr 05 09:30:08 ICT 2021
 */
@Repository
public interface TicketSiteUserRepositoryJPA extends JpaRepository<TicketSiteUserEntity, Long> {
    TicketSiteUserEntity findByUserNameIgnoreCase(String userName);

    @Query("SELECT DISTINCT userName FROM TicketSiteUserEntity ")
    List<String> findDistinctByUserName();

    TicketSiteUserEntity findBySiteIdAndUserName(Long siteId, String userName);

    List<TicketSiteUserEntity> findBySiteId(Long siteId);
}
