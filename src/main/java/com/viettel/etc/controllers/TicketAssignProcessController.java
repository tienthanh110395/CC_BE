package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketAssignProcessDTO;
import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.services.TicketAssignProcessService;
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

/**
 * Autogen class: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * @date Tue Mar 02 11:15:23 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketAssignProcessController {

    @Autowired
    TicketAssignProcessService ticketAssignProcessService;


    /**
     * Luu ket qua xu ly ticket
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/ticket-assign-processes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketAssignProcess(@AuthenticationPrincipal Authentication authentication,
                                                          @RequestBody TicketAssignProcessDTO dataParams) {
        Object resultObj = ticketAssignProcessService.saveTicketAssignProcess(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay thong tin thay doi trang thai ho tro phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @GetMapping(value = "/ticket-assign-processes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketAssignProcess(@AuthenticationPrincipal Authentication authentication,
                                                         TicketStatusDTO dataParams) {
        Object resultObj = ticketAssignProcessService.getTicketAssignProcess(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

}
