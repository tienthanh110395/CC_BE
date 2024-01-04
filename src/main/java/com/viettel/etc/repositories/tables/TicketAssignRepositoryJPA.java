package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketAssignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_assign
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketAssignRepositoryJPA extends JpaRepository<TicketAssignEntity, Long> {
    Optional<TicketAssignEntity> findByTicketId(Long ticketId);

    Optional<TicketAssignEntity> findByTicketAssignIdAndCreateUser(Long ticketAssignId, String createUser);
}
