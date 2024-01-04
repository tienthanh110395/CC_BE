package com.viettel.etc.services.impl;

import com.viettel.etc.dto.FileDTO;
import com.viettel.etc.dto.NotificationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationServiceImplTest {

    private NotificationServiceImpl notificationServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        notificationServiceImplUnderTest = new NotificationServiceImpl();
        notificationServiceImplUnderTest.wsPushNotificationContract = "wsPushNotificationContract";
    }

    @Test
    void testPushNotification() {
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

        // Run the test
        final boolean result = notificationServiceImplUnderTest.pushNotification(notificationDTO, authentication);

        // Verify the results
        assertThat(result).isTrue();
    }
}
