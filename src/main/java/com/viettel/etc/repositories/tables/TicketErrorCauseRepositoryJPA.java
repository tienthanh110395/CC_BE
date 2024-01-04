package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_error_cause
 *
 * @author ToolGen
 * @date Thu Jun 03 13:45:57 ICT 2021
 */
@Repository
public interface TicketErrorCauseRepositoryJPA extends JpaRepository<TicketErrorCauseEntity, Long> {
    List<TicketErrorCauseEntity> findAllByParentIdAndStatusOrderByName(Long parentId, Long status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ticket_error_cause SET status =:status WHERE ticket_error_cause_id IN (:lstIds)",nativeQuery = true)
    void updateStatusMultipleTicketErrorCause(List<Long> lstIds, Long status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ticket_error_cause SET status =:status WHERE ticket_error_cause_id =:ticketErrorCauseId" ,nativeQuery = true)
    void updateStatusTicketErrorCause(Long ticketErrorCauseId, Long status);

    TicketErrorCauseEntity findByCodeAndTicketErrorCauseIdNot(String code, Long ticketErrorCauseId);

    boolean existsByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "delete (select *FROM ticket_error_cause  tec LEFT JOIN ticket_error_cause  tec2 ON tec.parent_id = tec2.ticket_error_cause_id LEFT JOIN ticket_error_cause  tec3 ON tec2.parent_id = tec3.ticket_error_cause_id WHERE tec.parent_id IN ( :ticketErrorCauseId ) OR ( tec.ticket_error_cause_id IN ( :ticketErrorCauseId) OR tec2.parent_id IN ( :ticketErrorCauseId ) ))" ,nativeQuery = true)
    int doDelete(Long ticketErrorCauseId);

    @Query(value = "SELECT CC.TICKET_ERROR_CAUSE_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceNo();

}
