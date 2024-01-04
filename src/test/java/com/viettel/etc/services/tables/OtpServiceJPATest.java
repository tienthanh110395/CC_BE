package com.viettel.etc.services.tables;

import com.viettel.etc.repositories.tables.OtpRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.OtpEntity;
import com.viettel.etc.repositories.tables.entities.OtpIdentify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OtpServiceJPATest {

    private OtpServiceJPA otpServiceJPAUnderTest;

    @BeforeEach
    void setUp() {
        otpServiceJPAUnderTest = new OtpServiceJPA();
        otpServiceJPAUnderTest.otpRepositoryJPA = mock(OtpRepositoryJPA.class);
    }

    @Test
    void testExistsById() {
        // Setup
        final OtpIdentify id = new OtpIdentify("phone", 0);
        when(otpServiceJPAUnderTest.otpRepositoryJPA.existsById(new OtpIdentify("phone", 0))).thenReturn(false);

        // Run the test
        final Boolean result = otpServiceJPAUnderTest.existsById(id);

        // Verify the results
    }

    @Test
    void testGetById() {
        // Setup
        final OtpIdentify id = new OtpIdentify("phone", 0);
        final OtpEntity expectedResult = new OtpEntity();
        expectedResult.setPhone("phone");
        expectedResult.setConfirmType(0);
        expectedResult.setOtp("otp");
        expectedResult.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setDuration(0);

        // Configure OtpRepositoryJPA.getOne(...).
        final OtpEntity otpEntity = new OtpEntity();
        otpEntity.setPhone("phone");
        otpEntity.setConfirmType(0);
        otpEntity.setOtp("otp");
        otpEntity.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otpEntity.setDuration(0);
        when(otpServiceJPAUnderTest.otpRepositoryJPA.getOne(new OtpIdentify("phone", 0))).thenReturn(otpEntity);

        // Run the test
        final OtpEntity result = otpServiceJPAUnderTest.getById(id);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave() {
        // Setup
        final OtpEntity otp = new OtpEntity();
        otp.setPhone("phone");
        otp.setConfirmType(0);
        otp.setOtp("otp");
        otp.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otp.setDuration(0);

        final OtpEntity expectedResult = new OtpEntity();
        expectedResult.setPhone("phone");
        expectedResult.setConfirmType(0);
        expectedResult.setOtp("otp");
        expectedResult.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setDuration(0);

        // Configure OtpRepositoryJPA.save(...).
        final OtpEntity otpEntity = new OtpEntity();
        otpEntity.setPhone("phone");
        otpEntity.setConfirmType(0);
        otpEntity.setOtp("otp");
        otpEntity.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otpEntity.setDuration(0);
        when(otpServiceJPAUnderTest.otpRepositoryJPA.save(new OtpEntity())).thenReturn(otpEntity);

        // Run the test
        final OtpEntity result = otpServiceJPAUnderTest.save(otp);

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        final OtpEntity otp = new OtpEntity();
        otp.setPhone("phone");
        otp.setConfirmType(0);
        otp.setOtp("otp");
        otp.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otp.setDuration(0);

        // Run the test
        otpServiceJPAUnderTest.delete(otp);

        // Verify the results
    }

    @Test
    void testIsSendOTPByPhoneAndConfirmType() {
        // Setup
        // Configure OtpRepositoryJPA.findByPhoneAndConfirmType(...).
        final OtpEntity otpEntity = new OtpEntity();
        otpEntity.setPhone("phone");
        otpEntity.setConfirmType(0);
        otpEntity.setOtp("otp");
        otpEntity.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otpEntity.setDuration(0);
        final List<OtpEntity> otpEntities = Arrays.asList(otpEntity);
        when(otpServiceJPAUnderTest.otpRepositoryJPA.findByPhoneAndConfirmType("phone", 0)).thenReturn(otpEntities);

        // Run the test
        final boolean result = otpServiceJPAUnderTest.isSendOTPByPhoneAndConfirmType("phone", 0);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testIsSendOTPByPhoneAndConfirmType_OtpRepositoryJPAReturnsNoItems() {
        // Setup
        when(otpServiceJPAUnderTest.otpRepositoryJPA.findByPhoneAndConfirmType("phone", 0)).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = otpServiceJPAUnderTest.isSendOTPByPhoneAndConfirmType("phone", 0);

        // Verify the results
    }
}
