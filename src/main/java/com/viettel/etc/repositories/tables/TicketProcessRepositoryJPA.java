package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_process
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketProcessRepositoryJPA extends JpaRepository<TicketProcessEntity, Long> {
    TicketProcessEntity findByTicketId(Long ticketId);
}
