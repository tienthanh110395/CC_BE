package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketExtentReasonDTO;
import com.viettel.etc.services.TicketExtentReasonService;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.UserSystemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.viettel.etc.utils.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:57:16 ICT 2022
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketExtentReasonController {
    @Autowired 
    private TicketExtentReasonService ticketExtentReasonService;
    

    /**
     * 
     * 
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return 
     */
    @GetMapping(value = "getTicketExtentReason", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketExtentReason(@AuthenticationPrincipal Authentication authentication, 
                                              TicketExtentReasonDTO dataParams) {
        /*
        ==========================================================
        authenEntity: user info and role
        dataParams: danh sach bien client co the truyen len
        ==========================================================
        */
        Object resultObj = ticketExtentReasonService.getTicketExtentReason(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}