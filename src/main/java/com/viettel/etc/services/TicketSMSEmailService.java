package com.viettel.etc.services;

import com.viettel.etc.dto.NotificationDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac gui sms cho khach hang
 *
 * @author ToolGen
 * @date Tue Jun 01 11:14:42 ICT 2021
 */
public interface TicketSMSEmailService {

    Object sendEmail(NotificationDTO notificationDTO, Authentication authentication);

    Object sendSMS(NotificationDTO notificationDTO, Authentication authentication);

    Object sendNotify(NotificationDTO notificationDTO, Authentication authentication);
}
