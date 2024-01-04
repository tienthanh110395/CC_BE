package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketAttachmentDTO;
import com.viettel.etc.services.TicketAttachmentService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Autogen class: Lop thao tac lay lich su trang thai
 *
 * @author ToolGen
 * @date Thu Mar 25 09:03:51 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketAttachmentController {

    @Autowired
    TicketAttachmentService ticketAttachmentService;


    /**
     * Lay thong tin lich su trang thai
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "/ticket-attachment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketAttachment(@AuthenticationPrincipal Authentication authentication,
                                                      TicketAttachmentDTO dataParams) {
        Object resultObj = ticketAttachmentService.getTicketAttachment(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Them moi file attach
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/ticket-attachment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketAttachment(@AuthenticationPrincipal Authentication authentication,
                                                       @RequestBody TicketAttachmentDTO dataParams) {
        Object resultObj = ticketAttachmentService.saveTicketAttachment(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Download file attach
     * @param authentication
     * @param ticketAttachmentId
     * @return
     */
    @PostMapping(value = "/ticket-attachment/{ticketAttachmentId}/download", produces = MediaType.APPLICATION_JSON_VALUE)
    public void downloadTicketAttachment(@AuthenticationPrincipal Authentication authentication,
                                         @PathVariable Long ticketAttachmentId,
                                         HttpServletResponse response) throws IOException {
        ticketAttachmentService.downloadTicketAttachment(ticketAttachmentId, authentication, response);
    }


    /***
     * xoa file attach
     * @param authentication
     * @param ticketAttachmentId
     * @return
     */
    @DeleteMapping(value = "/ticket-attachment/{ticketAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteTicketAttachment(@AuthenticationPrincipal Authentication authentication,
                                                         @PathVariable Long ticketAttachmentId) {
        ticketAttachmentService.deleteTicketAttachment(ticketAttachmentId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

    /***
     * Download file attach
     * @param authentication
     * @param ticketAttachmentId
     * @return
     */
    @GetMapping(value = "/ticket-attachment/{ticketAttachmentId}/view", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> viewTicketAttachment(@AuthenticationPrincipal Authentication authentication,
                                                       @PathVariable Long ticketAttachmentId,
                                                       HttpServletResponse response) throws IOException {
        Object result = ticketAttachmentService.viewTicketAttachment(ticketAttachmentId, authentication, response);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }
}
