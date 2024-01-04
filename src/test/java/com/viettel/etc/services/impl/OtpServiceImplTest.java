package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ContractDetailDTO;
import com.viettel.etc.dto.OtpDTO;
import com.viettel.etc.repositories.tables.entities.OtpEntity;
import com.viettel.etc.repositories.tables.entities.OtpIdentify;
import com.viettel.etc.services.CRMService;
import com.viettel.etc.services.CategoriesService;
import com.viettel.etc.services.tables.OtpServiceJPA;
import com.viettel.etc.utils.exceptions.EtcException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OtpServiceImplTest {

    private OtpServiceImpl otpServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        otpServiceImplUnderTest = new OtpServiceImpl();
        otpServiceImplUnderTest.otpServiceJPA = mock(OtpServiceJPA.class);
        otpServiceImplUnderTest.smsService = mock(SMSServiceImpl.class);
        otpServiceImplUnderTest.categoriesService = mock(CategoriesService.class);
        otpServiceImplUnderTest.crmService = mock(CRMService.class);
    }

    @Test
    void testRequestOTPContract() {
        // Setup
        final OtpDTO params = new OtpDTO(0L, "phone", "user", 0, 0L, "plateTypeCode");
        final Authentication authentication = null;

        // Configure CRMService.getContractDetails(...).
        final ContractDetailDTO contractDetailDTO = new ContractDetailDTO();
        contractDetailDTO.setCusTypeId(0L);
        contractDetailDTO.setUserName("userName");
        contractDetailDTO.setUserId("userId");
        contractDetailDTO.setBirth("birth");
        contractDetailDTO.setGender("gender");
        contractDetailDTO.setAddress("address");
        contractDetailDTO.setIdentifier("identifier");
        contractDetailDTO.setRepIdentifier("repIdentifier");
        contractDetailDTO.setRepIdentifierType(0L);
        contractDetailDTO.setDateOfIssue("dateOfIssue");
        when(otpServiceImplUnderTest.crmService.getContractDetails(null)).thenReturn(contractDetailDTO);

        when(otpServiceImplUnderTest.otpServiceJPA.isSendOTPByPhoneAndConfirmType("phone", 0)).thenReturn(false);
        when(otpServiceImplUnderTest.otpServiceJPA.existsById(new OtpIdentify("phone", 0))).thenReturn(false);

        // Configure OtpServiceJPA.getById(...).
        final OtpEntity otpEntity = new OtpEntity();
        otpEntity.setPhone("phone");
        otpEntity.setConfirmType(0);
        otpEntity.setOtp("otp");
        otpEntity.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otpEntity.setDuration(0);
        when(otpServiceImplUnderTest.otpServiceJPA.getById(new OtpIdentify("phone", 0))).thenReturn(otpEntity);

        when(otpServiceImplUnderTest.smsService.sendSMS("phone", "content", null)).thenReturn(0);

        // Configure OtpServiceJPA.save(...).
        final OtpEntity otpEntity1 = new OtpEntity();
        otpEntity1.setPhone("phone");
        otpEntity1.setConfirmType(0);
        otpEntity1.setOtp("otp");
        otpEntity1.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otpEntity1.setDuration(0);
        when(otpServiceImplUnderTest.otpServiceJPA.save(new OtpEntity())).thenReturn(otpEntity1);

        // Run the test
        final Object result = otpServiceImplUnderTest.requestOTPContract(params, authentication);

        // Verify the results
    }

    @Test
    void testCheckOtpImportantService() {
        // Setup
        final Authentication authentication = null;
        when(otpServiceImplUnderTest.categoriesService.getConfigOtp("code", null)).thenReturn(false);

        // Configure CRMService.getContractDetails(...).
        final ContractDetailDTO contractDetailDTO = new ContractDetailDTO();
        contractDetailDTO.setCusTypeId(0L);
        contractDetailDTO.setUserName("userName");
        contractDetailDTO.setUserId("userId");
        contractDetailDTO.setBirth("birth");
        contractDetailDTO.setGender("gender");
        contractDetailDTO.setAddress("address");
        contractDetailDTO.setIdentifier("identifier");
        contractDetailDTO.setRepIdentifier("repIdentifier");
        contractDetailDTO.setRepIdentifierType(0L);
        contractDetailDTO.setDateOfIssue("dateOfIssue");
        when(otpServiceImplUnderTest.crmService.getContractDetails(null)).thenReturn(contractDetailDTO);

        when(otpServiceImplUnderTest.otpServiceJPA.existsById(new OtpIdentify("phone", 0))).thenReturn(false);

        // Configure OtpServiceJPA.getById(...).
        final OtpEntity otpEntity = new OtpEntity();
        otpEntity.setPhone("phone");
        otpEntity.setConfirmType(0);
        otpEntity.setOtp("otp");
        otpEntity.setSignDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        otpEntity.setDuration(0);
        when(otpServiceImplUnderTest.otpServiceJPA.getById(new OtpIdentify("phone", 0))).thenReturn(otpEntity);

        // Run the test
        final boolean result = otpServiceImplUnderTest.checkOtpImportantService("otp", "categoryCode", 0, authentication);

        // Verify the results
    }

    @Test
    void testIsSendOtp() {
        // Setup
        when(otpServiceImplUnderTest.otpServiceJPA.isSendOTPByPhoneAndConfirmType("phone", 0)).thenReturn(false);

        // Run the test
        final boolean result = otpServiceImplUnderTest.isSendOtp("phone", 0);

        // Verify the results
    }

    @Test
    void testValidateOtp() {
        // Setup
        final OtpIdentify otpIdentify = new OtpIdentify("phone", 0);
        when(otpServiceImplUnderTest.otpServiceJPA.existsById(any())).thenReturn(true);

        // Configure OtpServiceJPA.getById(...).
        final OtpEntity otpEntity = new OtpEntity();
        otpEntity.setPhone("phone");
        otpEntity.setConfirmType(0);
        otpEntity.setOtp("otp");
        otpEntity.setSignDate(Calendar.getInstance().getTime());
        otpEntity.setDuration(10);
        when(otpServiceImplUnderTest.otpServiceJPA.getById(any())).thenReturn(otpEntity);

        // Run the test
        final boolean result = otpServiceImplUnderTest.validateOtp(otpIdentify, "otp");

        // Verify the results
        assertThat(result).isTrue();
        verify(otpServiceImplUnderTest.otpServiceJPA).delete(any());
    }
}
