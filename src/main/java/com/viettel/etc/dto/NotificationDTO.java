package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import com.viettel.etc.utils.FnCommon;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Autogen class DTO: Lop thao tac day thong bao
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:28 ICT 2021
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class NotificationDTO {

    Long ticketId;

    String type;

    String title;

    String content;

    String phone;

    String email;

    String mobileId;

    Long status;

    String errorMessage;

    List<FileDTO> attachmentFiles;

    Long contractId;

    String message;

    String notificationCode;

    String notificationName;

    public TicketSmsMailPushEntity toTicketSmsMailPushEntity(Authentication authentication) {
        TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketId(ticketId);
        ticketSmsMailPushEntity.setSmsMailPushType(type);
        ticketSmsMailPushEntity.setMessage(content);
        ticketSmsMailPushEntity.setPhone(phone);
        ticketSmsMailPushEntity.setEmail(email);
        ticketSmsMailPushEntity.setMobileId(mobileId);
        ticketSmsMailPushEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketSmsMailPushEntity.setCreateUser(FnCommon.getUserLogin(authentication));
        ticketSmsMailPushEntity.setStatus(status);
        ticketSmsMailPushEntity.setErrorMessage(errorMessage);
        return ticketSmsMailPushEntity;
    }
}
