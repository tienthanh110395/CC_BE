package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketProcessDTO;
import com.viettel.etc.services.TicketProcessService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

/**
 * Autogen class: Lop thao tac tien xu ly cua CSKH
 *
 * @author ToolGen
 * @date Tue Mar 02 16:00:41 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketProcessController {

    @Autowired
    TicketProcessService ticketProcessService;


    /**
     * Luu ket qua tien xu ly cu CSKH
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/ticket-processes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketProcess(@AuthenticationPrincipal Authentication authentication,
                                                    @RequestBody TicketProcessDTO dataParams) {
        Object resultObj = ticketProcessService.saveTicketProcess(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay ket qua xu ly 1 phan anh
     * @param authentication
     * @param ticketProcessId
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-processes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketProcessDetails(@AuthenticationPrincipal Authentication authentication,
                                                          @Valid @PathVariable(name = "id") Long ticketProcessId,
                                                          TicketProcessDTO dataParams) {
        dataParams.setTicketId(ticketProcessId);
        Object resultObj = ticketProcessService.getTicketProcessDetails(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
