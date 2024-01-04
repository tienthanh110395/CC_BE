package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketAdjustChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_adjust_charge
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketAdjustChargeRepositoryJPA extends JpaRepository<TicketAdjustChargeEntity, Long> {
    List<TicketAdjustChargeEntity> findAllByTicketIdAndStatus(Long ticketId, Long status);
}
