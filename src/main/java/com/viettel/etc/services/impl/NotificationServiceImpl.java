package com.viettel.etc.services.impl;

import com.viettel.etc.dto.NotificationDTO;
import com.viettel.etc.services.NotificationService;
import com.viettel.etc.utils.FnCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Autogen class: Lop thao tac day thong bao
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:30 ICT 2021
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Value("${ws.push.notification.contract}")
    String wsPushNotificationContract;


    /**
     * Push notification to app
     *
     * @param notificationDTO
     * @param authentication
     * @return
     */
    @Override
    public boolean pushNotification(NotificationDTO notificationDTO, Authentication authentication) {
        String url = wsPushNotificationContract.replace("{contractId}", String.valueOf(notificationDTO.getContractId()));
        try {
            FnCommon.doPostRequest(url, FnCommon.getStringToken(authentication), notificationDTO);
        } catch (Exception e) {
            LOGGER.error(" Push notification contractId = {}, error ", notificationDTO.getContractId(), e);
            return false;
        }
        return true;
    }
}
