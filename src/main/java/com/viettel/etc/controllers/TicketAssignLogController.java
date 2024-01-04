package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketAssignLogDTO;
import com.viettel.etc.services.TicketAssignLogService;
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
 * Autogen class: Lop thao tac tim assign log
 *
 * @author ToolGen
 * @date Thu Mar 25 13:34:10 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketAssignLogController {
    @Autowired
    TicketAssignLogService ticketAssignLogService;


    /**
     * Lay thong tin ghi nhan qua trinh ho tro
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @GetMapping(value = "/ticket-assign-logs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketAssignLog(@AuthenticationPrincipal Authentication authentication,
                                                     TicketAssignLogDTO dataParams) {
        Object resultObj = ticketAssignLogService.getTicketAssignLog(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Them moi thong tin ghi nhan ho tro
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/ticket-assign-logs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketAssignLog(@AuthenticationPrincipal Authentication authentication,
                                                      @RequestBody TicketAssignLogDTO dataParams) {
        Object resultObj = ticketAssignLogService.saveTicketAssignLog(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xoa ghi nhan ho tro
     * @param authentication
     * @param ticketAssignLogId
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @DeleteMapping(value = "/ticket-assign-logs/{ticketAssignLogId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeTicketAssignLog(@AuthenticationPrincipal Authentication authentication,
                                                        @Valid @PathVariable(name = "ticketAssignLogId") Long ticketAssignLogId) {
        Object resultObj = ticketAssignLogService.removeTicketAssignLog(ticketAssignLogId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
