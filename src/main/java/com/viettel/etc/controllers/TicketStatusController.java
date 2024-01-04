package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.services.TicketStatusService;
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

import javax.annotation.security.RolesAllowed;

/**
 * Autogen class: Lop thao tac lay lich su trang thai
 *
 * @author ToolGen
 * @date Thu Mar 25 09:03:51 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketStatusController {

    @Autowired
    TicketStatusService ticketStatusService;


    /**
     * Lay thong tin lich su trang thai
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketStatus(@AuthenticationPrincipal Authentication authentication,
                                                  TicketStatusDTO dataParams) {
        Object resultObj = ticketStatusService.getTicketStatus(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
