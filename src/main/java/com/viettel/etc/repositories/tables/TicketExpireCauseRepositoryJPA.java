package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketExpireCauseEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_expire_cause
 *
 * @author ToolGen
 * @date Thu Jun 03 11:31:40 ICT 2021
 */
@Repository
public interface TicketExpireCauseRepositoryJPA extends JpaRepository<TicketExpireCauseEntity, Long> {
    List<TicketExpireCauseEntity> findAllByParentIdAndStatusOrderByName(Long parentId, Long status);

    List<TicketExpireCauseEntity> findAllByCodeAndLevelExpire(String code, Long levelTt);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TICKET_EXPIRE_CAUSE SET status =:status WHERE TICKET_EXPIRE_CAUSE_ID =:ticketExpireCauseId", nativeQuery = true)
    void updateStatus(Long ticketExpireCauseId, Long status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TICKET_EXPIRE_CAUSE SET status =:status WHERE TICKET_EXPIRE_CAUSE_ID IN (:lstIds)", nativeQuery = true)
    void updateStatusMultiple(List<Long> lstIds, Long status);

    TicketExpireCauseEntity findByCodeAndAndTicketExpireCauseIdNot(String code, Long ticketTypeId);

    TicketExpireCauseEntity findByCodeAndLevelExpire(String code, Long levelExpire);

    boolean existsByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "delete (select *FROM TICKET_EXPIRE_CAUSE  tec LEFT JOIN TICKET_EXPIRE_CAUSE  tec2 ON tec.parent_id = tec2.TICKET_EXPIRE_CAUSE_ID LEFT JOIN TICKET_EXPIRE_CAUSE  tec3 ON tec2.parent_id = tec3.TICKET_EXPIRE_CAUSE_ID WHERE tec.parent_id IN ( :ticketExpireCauseId ) OR ( tec.TICKET_EXPIRE_CAUSE_ID IN ( :ticketExpireCauseId) OR tec2.parent_id IN ( :ticketExpireCauseId ) ))" ,nativeQuery = true)
    int doDelete(Long ticketExpireCauseId);

    @Query(value = "SELECT CC.TICKET_EXPIRE_CAUSE_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceNo();
}
