package com.viettel.etc.controllers;

import com.viettel.etc.dto.*;
import com.viettel.etc.services.TicketService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Autogen class: Lop thao tac them moi ticket
 *
 * @author ToolGen
 * @date Tue Mar 02 14:49:44 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketController {

    @Autowired
    TicketService ticketService;


    /**
     * Them moi ticket
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicket(@AuthenticationPrincipal Authentication authentication,
                                             @RequestBody TicketDTO dataParams) {
        Object resultObj = ticketService.saveTicket(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay thong tin cua 1 phan anh
     * @param authentication
     * @param ticketId
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/tickets/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketDetails(@AuthenticationPrincipal Authentication authentication,
                                                   @Valid @PathVariable(name = "ticketId") String ticketId,
                                                   TicketInfoDTO dataParams) {
        dataParams.setTicketId(ticketId);
        Object resultObj = ticketService.getTicketDetails(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay lich su cua 1 phan anh
     * @param authentication
     * @param ticketId
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/tickets/ticket-histories/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketHistory(@AuthenticationPrincipal Authentication authentication,
                                                   @Valid @PathVariable(name = "ticketId") String ticketId,
                                                   TicketHistoryDTO dataParams) {
        dataParams.setTicketId(ticketId);
        Object resultObj = ticketService.getTicketHistory(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach lich su phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/tickets/ticket-histories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListTicketHistories(@AuthenticationPrincipal Authentication authentication,
                                                         TicketHistoryListDTO dataParams) {
        Object resultObj = ticketService.getListTicketHistories(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat excel lich su phan anh
     * @param authentication
     * @param params
     * @param response
     * @throws Exception
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/tickets/ticket-histories/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportHistoryTicket(@AuthenticationPrincipal Authentication authentication,
                                    @RequestBody TicketHistoryListDTO params,
                                    HttpServletResponse response) throws Exception {
        String fileName = (String) ticketService.exportHistoryTicket(params);
        FnCommon.responseFile(response, fileName);
    }

    /***
     * Lay sanh sach phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchTicket(@AuthenticationPrincipal Authentication authentication,
                                               SearchTicketDTO dataParams) {
        Object resultObj = ticketService.searchTicket(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat excel danh sach phan anh
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/tickets/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicket(@AuthenticationPrincipal Authentication authentication,
                             @RequestBody SearchTicketDTO dataParams, HttpServletResponse response) throws IOException {
        String fileName = (String) ticketService.exportTicket(dataParams, authentication);
        FnCommon.responseFile(response, fileName);
    }

    /***
     * update so lan phan anh
     * @param authentication
     * @param dataParams
     * @throws IOException
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PutMapping(value = "/tickets/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTicket(@AuthenticationPrincipal Authentication authentication,
                                               @Valid @PathVariable(name = "ticketId") Long ticketId,
                                               @RequestBody TicketDTO dataParams) {
        dataParams.setTicketId(ticketId);
        Object resultObj = ticketService.updateTicket(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }


    /**
     * Luu thong tin phan anh, gop y tu CPT
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = Constants.MOBILE + "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketForCPT(@AuthenticationPrincipal Authentication authentication,
                                                   @Valid @RequestBody TicketDTO dataParams) {
        Object resultObj = ticketService.saveTicketForCPT(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }


    /**
     * Get thong tin phan anh, gop y tu CPT
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = Constants.MOBILE + "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketForCPT(@AuthenticationPrincipal Authentication authentication,
                                                  TicketDTO dataParams) {
        Object resultObj = ticketService.getTicketForCPT(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }


    /***
     * Lay sanh sach phan anh chua assign
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/tickets-not-assign", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketNotAssign(@AuthenticationPrincipal Authentication authentication,
                                                     SearchTicketDTO dataParams) {
        Object resultObj = ticketService.getTicketNotAssign(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat excel danh sach bao cao chua assign
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/tickets-not-assign/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicketNotAssign(@AuthenticationPrincipal Authentication authentication,
                             @RequestBody SearchTicketDTO dataParams, HttpServletResponse response) throws IOException {
        String fileName = (String) ticketService.exportTicketNotAssign(dataParams, authentication);
        FnCommon.responseFile(response, fileName);
    }

    /***
     * Lay danh sach bao cao nang suat xu ly phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/tickets-report-performmance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketReportPerformmance(@AuthenticationPrincipal Authentication authentication,
                                                              SearchTicketDTO dataParams) {
        Object resultObj = ticketService.getTicketReportPerformmance(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat excel danh sach bao cao nang suat xu ly
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/tickets-report-performmance/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicketReportPerformance(@AuthenticationPrincipal Authentication authentication,
                             @RequestBody SearchTicketDTO dataParams, HttpServletResponse response) throws IOException {
        String fileName = (String) ticketService.exportTicketReportPerformance(dataParams, authentication);
        FnCommon.responseFile(response, fileName);
    }

    /***
     * update phản ánh
     * @param authentication
     * @param dataParams
     * @throws IOException
     */
    @PutMapping(value = "/ticket/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> editTicket(@AuthenticationPrincipal Authentication authentication,
                                             @PathVariable Long ticketId, @RequestBody TicketInfoDTO dataParams) throws Exception {
        Object resultObj = ticketService.editTicket(dataParams, ticketId,authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
