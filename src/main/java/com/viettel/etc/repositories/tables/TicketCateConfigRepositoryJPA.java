package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketCateConfigRepositoryJPA extends JpaRepository<TicketTypeEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE ticket_type SET status =:status WHERE ticket_type_id IN (:lstIds)", nativeQuery = true)
    void updateStatusMultiple(List<Long> lstIds, Long status);

    @Query(value = "SELECT CC.TICKET_TYPE_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceNo();
}
