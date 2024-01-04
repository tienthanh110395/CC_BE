package com.viettel.etc.controllers;

import com.viettel.etc.dto.OtpDTO;
import com.viettel.etc.services.OtpService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class OTPController {

    @Autowired
    OtpService otpService;

    /**
     * Yeu cau ma OTP
     *
     * @param dataParams params client
     * @return
     */
    @GetMapping(value = "/contracts/request-otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> requestOTPContract(@AuthenticationPrincipal Authentication authentication, OtpDTO dataParams) {
        otpService.requestOTPContract(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }
}
