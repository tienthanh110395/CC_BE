package com.viettel.etc.services;

import com.viettel.etc.dto.OtpDTO;
import com.viettel.etc.repositories.tables.entities.OtpIdentify;
import org.springframework.security.core.Authentication;

public interface OtpService {
    Object requestOTPContract(OtpDTO params, Authentication authentication);

    boolean checkOtpImportantService(String otp, String categoryCode, Integer confirmType, Authentication authentication);

    boolean validateOtp(OtpIdentify otpIdentify, String otp);

    boolean isSendOtp(String phone, Integer confirmType);
}
