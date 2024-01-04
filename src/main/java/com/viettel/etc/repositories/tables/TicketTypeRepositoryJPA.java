package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_type
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Repository
public interface TicketTypeRepositoryJPA extends JpaRepository<TicketTypeEntity, Long> {
    TicketTypeEntity findByCodeAndLevelTt(String code, Long levelTt);

    TicketTypeEntity findByCode(String code);

    boolean existsByCode(String code);

    TicketTypeEntity findByCodeAndTicketTypeIdNot(String code, Long ticketTypeId);

    List<TicketTypeEntity> findByLevelTt(Long levelTT);

    TicketTypeEntity findNameByTicketTypeId(Long Id);

    @Modifying
    @Transactional
    @Query(value = "delete (select *FROM TICKET_TYPE  tt LEFT JOIN TICKET_TYPE  tt2 ON tt.parent_id = tt2.TICKET_TYPE_ID LEFT JOIN TICKET_TYPE  tt3 ON tt2.parent_id = tt3.TICKET_TYPE_ID WHERE tt.parent_id IN ( :ticketTypeId ) OR ( tt.TICKET_TYPE_ID IN ( :ticketTypeId) OR tt2.parent_id IN ( :ticketTypeId ) ))" ,nativeQuery = true)
    int doDelete(Long ticketTypeId);

}
