package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketExtentDTO;
import com.viettel.etc.repositories.tables.entities.TicketExtentEntity;
import com.viettel.etc.services.TicketExtentService;
import com.viettel.etc.services.TicketProcessService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:32:06 ICT 2022
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketExtentController {
    @Autowired 
    private TicketExtentService ticketExtentService;


    /**
     *
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "ticket-extents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketExtent(@AuthenticationPrincipal Authentication authentication,
                                                  TicketExtentDTO dataParams) {
        Object resultObj = ticketExtentService.getTicketExtent(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Xin gia han xu ly
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-extents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insertTicketExtent(@AuthenticationPrincipal Authentication authentication,
                                                     @RequestBody TicketExtentDTO dataParams) {
        Object resultObj = ticketExtentService.insertTicketExtent(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Lấy chi tiết 1 phản ánh cần gia hạn
     *
     * @param authentication
     * @param ticketId
     * @return
     */
    @GetMapping(value = "/ticket-extents/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDetailTicketExtent(@AuthenticationPrincipal Authentication authentication,
                                                        @PathVariable Long ticketId) {
        Object resultObj = ticketExtentService.getDetailTicketExtent(ticketId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Sua phản ánh cần gia hạn
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @param ticketId    params client
     * @return
     */
    @PutMapping(value = "/ticket-extents/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTicketExtent(@AuthenticationPrincipal Authentication authentication,
                                                     @PathVariable Long ticketId, @RequestBody TicketExtentDTO dataParams) throws EtcException {
        Object resultObj = ticketExtentService.updateTicketExtent(dataParams, ticketId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Phê duyệt phản ánh cần gia hạn
     *
     * @param authentication: thong tin nguoi dung
     * @return
     */
    @PutMapping(value = "/ticket-extents/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> approveTicketStatus(@AuthenticationPrincipal Authentication authentication,
                                                      @RequestBody TicketExtentDTO dataParams) throws EtcException {
        List<TicketExtentEntity> response = ticketExtentService.approveTicketStatus(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(response), HttpStatus.OK);
    }

    /**
     * Từ chối gia hạn phản ánh
     *
     * @param authentication: thong tin nguoi dung
     * @param ticketId    params client
     * @return
     */
    @PutMapping(value = "/ticket-extents/{ticketId}/reject", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> rejectTicketStatus(@AuthenticationPrincipal Authentication authentication,
                                                     @PathVariable Long ticketId,
                                                     @RequestBody TicketExtentDTO dataParams) throws EtcException {
        Object resultObj = ticketExtentService.rejectTicketStatus(dataParams, ticketId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat file excel yeu cau gia hạn phản ánh
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/ticket-extents/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicketExtent(@AuthenticationPrincipal Authentication authentication,
                                   @RequestBody TicketExtentDTO dataParams, HttpServletResponse response) throws IOException {
        String fileName = (String) ticketExtentService.exportTicketExtent(dataParams, authentication);
        FnCommon.responseFile(response, fileName);
    }

    /***
     * Xuat excel danh sach gia han xu ly phan anh
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/ticket-processes/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicketProcess(@AuthenticationPrincipal Authentication authentication,
                                    @RequestBody TicketAssignDTO dataParams, HttpServletResponse response) throws IOException {
        String fileName = (String) ticketExtentService.exportTicketProcess(dataParams, authentication);
        FnCommon.responseFile(response, fileName);
    }
}
