package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.OtherCategoriesEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Other-categories
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
public interface TicketLevelCateRepositoryJPA extends  JpaRepository<OtherCategoriesEntity, Long> {

    boolean existsByCode(String code);
    boolean existsByType(Long type);
    boolean existsByPriorityValue(Long priorityValue);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OtherCategoriesEntity SET isActive =:isActive WHERE id =:ticketLevelCateId")
    void updateStatus(Long ticketLevelCateId, Long isActive);
    OtherCategoriesEntity findByCodeAndTypeAndIdNot(String ticketLevelCateCode, Long type,Long ticketLevelCateId);

    @Query(value = "SELECT CC.TICKET_PRIORITIES_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceNo();
}
