package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketAssignProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_assign_process
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketAssignProcessRepositoryJPA extends JpaRepository<TicketAssignProcessEntity, Long> {
    List<TicketAssignProcessEntity> findAllByTicketId(Long ticketId);

    Optional<TicketAssignProcessEntity> findByTicketIdAndStatus(Long ticketId, Long status);
}
