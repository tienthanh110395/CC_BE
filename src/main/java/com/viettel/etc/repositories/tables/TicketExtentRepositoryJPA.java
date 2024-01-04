package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_extent
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:32:09 ICT 2022
 */
@Repository
public interface TicketExtentRepositoryJPA extends JpaRepository<TicketExtentEntity, Long> {

    TicketExtentEntity findByTicketId(Long ticketId);

    TicketExtentEntity getByTicketIdAndStatus(Long ticketId, Long status);
}