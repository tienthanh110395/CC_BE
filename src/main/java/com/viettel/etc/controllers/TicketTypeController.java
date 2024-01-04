package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.services.TicketTypeService;
import com.viettel.etc.utils.exceptions.EtcException;
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

import javax.validation.Valid;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Sun Jan 23 17:12:54 ICT 2022
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketTypeController {
    @Autowired 
    private TicketTypeService ticketTypeService;


    /**
     * Lay danh sach danh muc nhom, the loai, phan anh
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "tickets-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketType(@AuthenticationPrincipal Authentication authentication,
                                                TicketTypeDTO dataParams) {
        Object resultObj = ticketTypeService.getTicketType(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet 1 danh muc, the loai
     * @param authentication
     * @param ticketTypeId
     * @return
     */
    @GetMapping(value = "/ticket-type/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketTypeDetails(@AuthenticationPrincipal Authentication authentication,
                                                       @PathVariable Long ticketTypeId) {
        Object resultObj = ticketTypeService.getTicketTypeDetails(ticketTypeId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tao moi danh muc nhom, the loai, loai PA
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/tickets-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTicketType(@AuthenticationPrincipal Authentication authentication,
                                                   @RequestBody TicketTypeDTO dataParams) {
        Object resultObj = ticketTypeService.createTicketType(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Sua danh muc nhom, the loai, loai PA
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @param ticketTypeId    params client
     * @return
     */
    @PutMapping(value = "/tickets-type/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTicketType(@AuthenticationPrincipal Authentication authentication,
                                                   @PathVariable Long ticketTypeId, @RequestBody TicketTypeDTO dataParams) throws EtcException {
        Object resultObj = ticketTypeService.updateTicketType(dataParams, ticketTypeId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa 1 bản ghi danh muc nhom, the loai, loai PA
     * @param authentication
     * @param ticketTypeId
     * @return
     */
    @DeleteMapping(value = "/tickets-type/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeTicketErrorCause(@AuthenticationPrincipal Authentication authentication,
                                                         @Valid @PathVariable(name = "ticketTypeId") Long ticketTypeId) {
        Object resultObj = ticketTypeService.removeTicketType(ticketTypeId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}