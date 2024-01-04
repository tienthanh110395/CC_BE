package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketRepositoryJPA extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findAllByTicketIdInAndProcessUserIsNotNull(List<Long> ticketId);

    List<TicketEntity> findByL3TicketTypeId(Long l3TicketTypeId);

}
