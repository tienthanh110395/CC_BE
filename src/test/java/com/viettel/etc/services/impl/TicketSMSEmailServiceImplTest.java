package com.viettel.etc.services.impl;

import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.NotificationDTO;
import com.viettel.etc.repositories.tables.TicketSmsMailPushRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import com.viettel.etc.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketSMSEmailServiceImplTest {

    private TicketSMSEmailServiceImpl ticketSMSEmailServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        ticketSMSEmailServiceImplUnderTest = new TicketSMSEmailServiceImpl();
        ticketSMSEmailServiceImplUnderTest.ticketSmsMailPushRepositoryJPA = mock(TicketSmsMailPushRepositoryJPA.class);
        ticketSMSEmailServiceImplUnderTest.emailService = mock(EmailServiceImpl.class);
        ticketSMSEmailServiceImplUnderTest.smsService = mock(SMSServiceImpl.class);
        ticketSMSEmailServiceImplUnderTest.notificationService = mock(NotificationService.class);
    }

    @Test
    void testSendSMS() {
        // Setup
        final NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTicketId(0L);
        notificationDTO.setType("type");
        notificationDTO.setTitle("title");
        notificationDTO.setContent("content");
        notificationDTO.setPhone("phone");
        notificationDTO.setEmail("email");
        notificationDTO.setMobileId("mobileId");
        notificationDTO.setStatus(0L);
        notificationDTO.setErrorMessage("errorMessage");
        final FileDTO fileDTO = new FileDTO();
        fileDTO.setFilePath("filePath");
        fileDTO.setFileName("fileName");
        fileDTO.setBase64Data("base64Data");
        fileDTO.setAttachmentId(0L);
        notificationDTO.setAttachmentFiles(Arrays.asList(fileDTO));

        final Authentication authentication = null;
        when(ticketSMSEmailServiceImplUnderTest.smsService.sendSMS("phone", "content", null)).thenReturn(0);

        // Configure TicketSmsMailPushRepositoryJPA.save(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        when(ticketSMSEmailServiceImplUnderTest.ticketSmsMailPushRepositoryJPA.save(new TicketSmsMailPushEntity())).thenReturn(ticketSmsMailPushEntity);

        // Run the test
        final Object result = ticketSMSEmailServiceImplUnderTest.sendSMS(notificationDTO, authentication);

        // Verify the results
    }

    @Test
    void testSendNotify() {
        // Setup
        final NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTicketId(0L);
        notificationDTO.setType("type");
        notificationDTO.setTitle("title");
        notificationDTO.setContent("content");
        notificationDTO.setPhone("phone");
        notificationDTO.setEmail("email");
        notificationDTO.setMobileId("mobileId");
        notificationDTO.setStatus(0L);
        notificationDTO.setErrorMessage("errorMessage");
        final FileDTO fileDTO = new FileDTO();
        fileDTO.setFilePath("filePath");
        fileDTO.setFileName("fileName");
        fileDTO.setBase64Data("base64Data");
        fileDTO.setAttachmentId(0L);
        notificationDTO.setAttachmentFiles(Arrays.asList(fileDTO));

        final Authentication authentication = null;
        when(ticketSMSEmailServiceImplUnderTest.notificationService.pushNotification(new NotificationDTO(), null)).thenReturn(false);

        // Configure TicketSmsMailPushRepositoryJPA.save(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        when(ticketSMSEmailServiceImplUnderTest.ticketSmsMailPushRepositoryJPA.save(new TicketSmsMailPushEntity())).thenReturn(ticketSmsMailPushEntity);

        // Run the test
        final Object result = ticketSMSEmailServiceImplUnderTest.sendNotify(notificationDTO, authentication);

        // Verify the results
    }

    @Test
    void testSendEmail() {
        // Setup
        final NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTicketId(0L);
        notificationDTO.setType("type");
        notificationDTO.setTitle("title");
        notificationDTO.setContent("content");
        notificationDTO.setPhone("phone");
        notificationDTO.setEmail("email");
        notificationDTO.setMobileId("mobileId");
        notificationDTO.setStatus(0L);
        notificationDTO.setErrorMessage("errorMessage");
        final FileDTO fileDTO = new FileDTO();
        fileDTO.setFilePath("filePath");
        fileDTO.setFileName("fileName.jpg");
        fileDTO.setBase64Data("base64Data");
        fileDTO.setAttachmentId(0L);
        notificationDTO.setAttachmentFiles(Arrays.asList(fileDTO));

        final Authentication authentication = null;
        when(ticketSMSEmailServiceImplUnderTest.emailService.sendMail("subject", "mailReceive", "filePath", new HashMap<>(), null, new HashMap<>())).thenReturn(false);

        // Configure TicketSmsMailPushRepositoryJPA.save(...).
        final TicketSmsMailPushEntity ticketSmsMailPushEntity = new TicketSmsMailPushEntity();
        ticketSmsMailPushEntity.setTicketSmsMailPushId(0L);
        ticketSmsMailPushEntity.setTicketId(0L);
        ticketSmsMailPushEntity.setSmsMailPushType("smsMailPushType");
        ticketSmsMailPushEntity.setMessage("message");
        ticketSmsMailPushEntity.setPhone("phone");
        ticketSmsMailPushEntity.setEmail("email");
        ticketSmsMailPushEntity.setMobileId("mobileId");
        ticketSmsMailPushEntity.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        ticketSmsMailPushEntity.setCreateUser("createUser");
        ticketSmsMailPushEntity.setStatus(0L);
        when(ticketSMSEmailServiceImplUnderTest.ticketSmsMailPushRepositoryJPA.save(new TicketSmsMailPushEntity())).thenReturn(ticketSmsMailPushEntity);

        // Run the test
        final Object result = ticketSMSEmailServiceImplUnderTest.sendEmail(notificationDTO, authentication);

        // Verify the results
    }
}
