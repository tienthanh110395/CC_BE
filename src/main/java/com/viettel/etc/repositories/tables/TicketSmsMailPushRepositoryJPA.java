package com.viettel.etc.repositories.tables;

import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Autogen class Repository Interface: Create Repository For Table Name Ticket_sms_mail_push
 *
 * @author ToolGen
 * @date Tue Jun 01 11:14:42 ICT 2021
 */
@Repository
public interface TicketSmsMailPushRepositoryJPA extends JpaRepository<TicketSmsMailPushEntity, Long> {
    @Query(value = "SELECT TICKET_SMS_MAIL_PUSH_SEQ.nextval FROM dual", nativeQuery = true)
    Long getNextValSequenceSerial();
}
