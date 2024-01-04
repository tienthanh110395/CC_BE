package com.viettel.etc.services.impl;

import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.NotificationDTO;
import com.viettel.etc.repositories.tables.TicketSmsMailPushRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import com.viettel.etc.services.NotificationService;
import com.viettel.etc.services.TicketSMSEmailService;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Autogen class: Lop thao tac gui sms cho khach hang
 *
 * @author ToolGen
 * @date Tue Jun 01 11:14:42 ICT 2021
 */
@Service
public class TicketSMSEmailServiceImpl implements TicketSMSEmailService {

    @Autowired
    TicketSmsMailPushRepositoryJPA ticketSmsMailPushRepositoryJPA;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    SMSServiceImpl smsService;

    @Autowired
    NotificationService notificationService;

    @Value("${crm.common.max-file-size}")
    Integer maxFileSize;


    /**
     * Gui Email
     *
     * @param notificationDTO
     * @param authentication
     * @return
     */
    @Override
    public Object sendEmail(NotificationDTO notificationDTO, Authentication authentication) {
        String subject = new String(notificationDTO.getTitle().getBytes(), Charset.forName(StandardCharsets.UTF_8.name()));
        String filePath = "template" + File.separator + "template_email.txt";
        Map<String, String> parameter = new HashMap<>();
        parameter.put("PARAM1", notificationDTO.getContent() != null ? notificationDTO.getContent() : "");
        Map<String, String> mapFileAttach = new HashMap<>();
        if (notificationDTO.getAttachmentFiles() != null && !notificationDTO.getAttachmentFiles().isEmpty()) {
            for (FileDTO fileDTO : notificationDTO.getAttachmentFiles()) {
                byte[] file = Base64.decodeBase64(fileDTO.getBase64Data());
                if (!FnCommon.checkFileValid(fileDTO.getFileName(), file, maxFileSize)) {
                    throw new EtcException("common.validate.briefcase.invalid");
                }
                mapFileAttach.put(fileDTO.getFileName(), fileDTO.getBase64Data());
            }
        }
        boolean result = emailService.sendMail(subject, notificationDTO.getEmail(), filePath, parameter, authentication, mapFileAttach);
        notificationDTO.setStatus(result ? 1L : 0L);
        notificationDTO.setErrorMessage(result ? "" : "Send email failed!");
        notificationDTO.setType(TicketSmsMailPushEntity.SMSMailPushType.SEND_EMAIL.value);
        TicketSmsMailPushEntity ticketSmsMailPushEntity = notificationDTO.toTicketSmsMailPushEntity(authentication);
        return ticketSmsMailPushRepositoryJPA.save(ticketSmsMailPushEntity);
    }

    /**
     * Gui SMS
     *
     * @param notificationDTO
     * @param authentication
     * @return
     */
    @Override
    public Object sendSMS(NotificationDTO notificationDTO, Authentication authentication) {
        long status = smsService.sendSMS(notificationDTO.getPhone(), notificationDTO.getContent(), authentication);
        notificationDTO.setStatus(status);
        notificationDTO.setErrorMessage(status == 1L ? "" : "Send sms failed!");
        notificationDTO.setType(TicketSmsMailPushEntity.SMSMailPushType.SEND_SMS.value);
        TicketSmsMailPushEntity ticketSmsMailPushEntity = notificationDTO.toTicketSmsMailPushEntity(authentication);
        return ticketSmsMailPushRepositoryJPA.save(ticketSmsMailPushEntity);
    }


    /**
     * Gui notify toi app CPT
     *
     * @param notificationDTO
     * @param authentication
     * @return
     */
    @Override
    public Object sendNotify(NotificationDTO notificationDTO, Authentication authentication) {
        notificationDTO.setMessage(notificationDTO.getContent());
        notificationDTO.setNotificationCode("CC");
        notificationDTO.setNotificationName(FnCommon.isNullOrEmpty(notificationDTO.getNotificationName()) ? "Thông báo từ Chăm sóc khách hàng" : notificationDTO.getNotificationName());
        boolean result = notificationService.pushNotification(notificationDTO, authentication);
        notificationDTO.setStatus(result ? 1L : 0L);
        notificationDTO.setErrorMessage(result ? "" : "Send notify failed!");
        notificationDTO.setType(TicketSmsMailPushEntity.SMSMailPushType.PUSH_NOTIFY.value);
        TicketSmsMailPushEntity ticketSmsMailPushEntity = notificationDTO.toTicketSmsMailPushEntity(authentication);
        return ticketSmsMailPushRepositoryJPA.save(ticketSmsMailPushEntity);
    }
}
