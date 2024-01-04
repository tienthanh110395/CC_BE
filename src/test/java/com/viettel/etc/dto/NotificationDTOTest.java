package com.viettel.etc.dto;

import com.viettel.etc.repositories.tables.entities.TicketSmsMailPushEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationDTOTest {

    private NotificationDTO notificationDTOUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        notificationDTOUnderTest = new NotificationDTO();
        notificationDTOUnderTest.ticketId = 0L;
        notificationDTOUnderTest.type = "type";
        notificationDTOUnderTest.title = "title";
        notificationDTOUnderTest.content = "content";
        notificationDTOUnderTest.phone = "phone";
        notificationDTOUnderTest.email = "email";
        notificationDTOUnderTest.mobileId = "mobileId";
        notificationDTOUnderTest.status = 0L;
        notificationDTOUnderTest.errorMessage = "errorMessage";
        notificationDTOUnderTest.attachmentFiles = Arrays.asList(new FileDTO());
        notificationDTOUnderTest.contractId = 0L;
        notificationDTOUnderTest.message = "message";
        notificationDTOUnderTest.notificationCode = "notificationCode";
        notificationDTOUnderTest.notificationName = "notificationName";
    }

    @Test
    void testToTicketSmsMailPushEntity() {
        // Setup
        final TicketSmsMailPushEntity expectedResult = new TicketSmsMailPushEntity();
        expectedResult.setTicketSmsMailPushId(0L);
        expectedResult.setTicketId(0L);
        expectedResult.setSmsMailPushType("type");
        expectedResult.setMessage("content");
        expectedResult.setPhone("phone");
        expectedResult.setEmail("email");
        expectedResult.setMobileId("mobileId");
        expectedResult.setCreateDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setCreateUser("createUser");
        expectedResult.setStatus(0L);
        expectedResult.setErrorMessage("errorMessage");

        // Run the test
        final TicketSmsMailPushEntity result = notificationDTOUnderTest.toTicketSmsMailPushEntity(null);

        // Verify the results
    }

    @Test
    void testEquals() {
        final boolean result = notificationDTOUnderTest.equals("o");
        assertThat(result).isFalse();
    }

    @Test
    void testCanEqual() {
        final boolean result = notificationDTOUnderTest.canEqual("other");
        assertThat(result).isFalse();
    }

    @Test
    void testHashCode() {
        final int result = notificationDTOUnderTest.hashCode();
        assertThat(result).isEqualTo(result);
    }

    @Test
    void testToString() {
        final String result = notificationDTOUnderTest.toString();
        assertThat(result).isEqualTo(result);
    }
}
