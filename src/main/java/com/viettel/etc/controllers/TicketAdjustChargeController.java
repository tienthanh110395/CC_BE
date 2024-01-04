package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketAdjustChargeDTO;
import com.viettel.etc.services.TicketAdjustChargeService;
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
 * Autogen class: Lop thao tac tim kiem ticket
 *
 * @author ToolGen
 * @date Wed Mar 03 10:22:45 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketAdjustChargeController {

    @Autowired
    TicketAdjustChargeService ticketAdjustChargeService;


    /**
     * Tao yeu cau dieu chinh cuoc
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/adjust-charges", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketAdjustCharge(@AuthenticationPrincipal Authentication authentication,
                                                         @RequestBody TicketAdjustChargeDTO dataParams) {
        Object resultObj = ticketAdjustChargeService.saveTicketAdjustCharge(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay yeu cau dieu chinh cuoc
     * @param authentication
     * @param id
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/adjust-charges/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketAdjustCharge(@AuthenticationPrincipal Authentication authentication,
                                                        @Valid @PathVariable(name = "id") Long id,
                                                        TicketAdjustChargeDTO dataParams) {
        dataParams.setTicketId(id);
        Object resultObj = ticketAdjustChargeService.getTicketAdjustCharge(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
