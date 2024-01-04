package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketInfoDTO;
import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.services.TicketStatisticTypeService;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.viettel.etc.utils.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.validation.Valid;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Wed Dec 01 13:45:03 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketStatisticTypeController {
    @Autowired 
    private TicketStatisticTypeService ticketStatisticTypeService;
    

    /**
     * Lay thong tin loai thong ke
     * 
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return 
     */
    @GetMapping(value = "/ticket-statistic-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketStatisticType(@AuthenticationPrincipal Authentication authentication, 
                                              TicketStatisticTypeDTO dataParams) {
        Object resultObj = ticketStatisticTypeService.getTicketStatisticType(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay thong tin cua 1 loai thong ke
     * @param authentication
     * @param parentId
     * @return
     */
    @GetMapping(value = "/ticket-statistic-type/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketStatisticTypeDetail(@AuthenticationPrincipal Authentication authentication,
                                                               @PathVariable Long parentId) {
        Object resultObj = ticketStatisticTypeService.getTicketStatisticTypeDetail(parentId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}