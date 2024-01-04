package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_attachment
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketAttachmentRepositoryJPA extends JpaRepository<TicketAttachmentEntity, Long> {
    List<TicketAttachmentEntity> findAllByTicketIdAndType(Long ticketId, Long type);

    Optional<List<TicketAttachmentEntity>> findByTicketId(Long ticketId, Pageable pageable);

    List<TicketAttachmentEntity> findAllByTicketIdAndTypeIn(Long ticketId, List<Long> type);

    @Query(value = "SELECT TICKET_ATTACHMENT_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceSerial();
}
