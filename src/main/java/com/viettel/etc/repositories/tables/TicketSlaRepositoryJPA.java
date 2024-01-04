package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketSlaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_sla
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketSlaRepositoryJPA extends JpaRepository<TicketSlaEntity, Long> {
    Optional<TicketSlaEntity> findBySiteIdAndAndSourceIdAndAndTicketTypeIdAndStatus(Long siteId, long sourceId, Long ticketTypeId, Long status);

    Optional<TicketSlaEntity> findByPriorityIdAndAndTicketTypeIdAndStatus(Long priorityId, Long ticketTypeId, Long status);

    TicketSlaEntity findByTicketTypeId(Long ticketTypeId);

    List<TicketSlaEntity> findAllByTicketTypeId(Long ticketTypeId);




    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TICKET_SLA WHERE TICKET_TYPE_ID = (:ticketTypeId)", nativeQuery = true)
    void onDelete(Long ticketTypeId);
}
