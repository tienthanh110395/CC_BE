package com.viettel.etc.services;

import com.viettel.etc.dto.NotificationDTO;
import org.springframework.security.core.Authentication;

/**
 * Autogen class: Lop thao tac day thong bao
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:30 ICT 2021
 */
public interface NotificationService {
    boolean pushNotification(NotificationDTO notificationDTO, Authentication authentication);
}
