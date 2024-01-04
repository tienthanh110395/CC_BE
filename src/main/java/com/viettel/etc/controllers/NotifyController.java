package com.viettel.etc.controllers;

import com.viettel.etc.dto.NotificationDTO;
import com.viettel.etc.services.TicketSMSEmailService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class NotifyController {
    @Autowired
    TicketSMSEmailService ticketSMSEmailService;

    /***
     * Gui mail thong bao cho nguoi phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/notify/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendEmail(@AuthenticationPrincipal Authentication authentication,
                                            @RequestBody NotificationDTO dataParams) {
        Object result = ticketSMSEmailService.sendEmail(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /***
     * Gui tin nhan cho khach hang
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/notify/sms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendSMS(@AuthenticationPrincipal Authentication authentication,
                                          @RequestBody NotificationDTO dataParams) {
        Object result = ticketSMSEmailService.sendSMS(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /**
     * Day notification
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/notify/push", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> pushNotification(@AuthenticationPrincipal Authentication authentication,
                                                   @RequestBody NotificationDTO dataParams) {
        Object resultObj = ticketSMSEmailService.sendNotify(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
