package com.viettel.etc.controllers;

import com.viettel.etc.dto.SearchTicketDTO;
import com.viettel.etc.dto.TicketStatisticDTO;
import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.services.TicketStatisticService;
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

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Thu Dec 02 09:01:12 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketStatisticController {
    @Autowired 
    private TicketStatisticService ticketStatisticService;

    /**
     * Lay danh sach thong ke
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "/ticket-statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketStatistic(@AuthenticationPrincipal Authentication authentication,
                                                         TicketStatisticDTO dataParams) {
        Object resultObj = ticketStatisticService.getTicketStatistic(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat excel danh sach bao cao thong ke
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/ticket-statistic/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicket(@AuthenticationPrincipal Authentication authentication,
                             @RequestBody TicketStatisticDTO dataParams, HttpServletResponse response) throws IOException {
        String fileName = (String) ticketStatisticService.exportTicketStatistic(dataParams, authentication);
        FnCommon.responseFile(response, fileName);
    }

    /**
     * Them moi thong ke
     * 
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return 
     */
    @PostMapping(value = "/ticket-statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insertTicketStatistic(@AuthenticationPrincipal Authentication authentication,
                                                        @RequestBody TicketStatisticDTO dataParams) {
        Object resultObj = ticketStatisticService.insertTicketStatistic(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}