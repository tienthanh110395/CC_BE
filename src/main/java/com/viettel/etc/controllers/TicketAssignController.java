package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.services.TicketAssignService;
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
 * Autogen class: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:43 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketAssignController {
    @Autowired
    TicketAssignService ticketAssignService;


    /**
     * Lay du lieu yeu cau phoi hop xu ly
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @GetMapping(value = "/ticket-assigns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketAssigns(@AuthenticationPrincipal Authentication authentication,
                                                   TicketAssignDTO dataParams) {
        Object resultObj = ticketAssignService.getTicketAssigns(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat file excel yeu cau phoi hop
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/ticket-assigns/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicketAssigns(@AuthenticationPrincipal Authentication authentication,
                                    @RequestBody TicketAssignDTO dataParams, HttpServletResponse response) throws IOException {
        String fileName = (String) ticketAssignService.exportTicketAssigns(dataParams, authentication);
        FnCommon.responseFile(response, fileName);

    }

    /***
     * Cap nhat thong tin yeu cau phoi hop
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PutMapping(value = "/ticket-assigns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> receiveTicketToHandle(@AuthenticationPrincipal Authentication authentication,
                                                        @RequestBody TicketAssignDTO dataParams) {
        Object resultObj = ticketAssignService.receiveTicketToHandle(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay thong tin yeu cau phoi hop theo ma phan anh
     * @param authentication
     * @param ticketId
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @GetMapping(value = "/ticket-assigns/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketAssignByTicketId(@AuthenticationPrincipal Authentication authentication,
                                                            @Valid @PathVariable(name = "ticketId") String ticketId,
                                                            TicketAssignDTO dataParams) {
        dataParams.setTicketId(ticketId);
        Object resultObj = ticketAssignService.getTicketAssignByTicketId(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay thong tin 1 yeu cau phoi hop
     * @param authentication
     * @param ticketId
     * @param ticketAssignId
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @GetMapping(value = "/tickets/{ticketId}/ticket-assigns/{ticketAssignId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketAssignById(@AuthenticationPrincipal Authentication authentication,
                                                      @Valid @PathVariable(name = "ticketId") String ticketId,
                                                      @Valid @PathVariable(name = "ticketAssignId") Long ticketAssignId,
                                                      TicketAssignDTO dataParams) {
        dataParams.setTicketId(ticketId);
        dataParams.setTicketAssignId(ticketAssignId);
        Object resultObj = ticketAssignService.getTicketAssignById(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Them moi yeu cau phoi hop
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @PostMapping(value = "/ticket-assigns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketAssign(@AuthenticationPrincipal Authentication authentication,
                                                   @RequestBody TicketAssignDTO dataParams) {
        Object resultObj = ticketAssignService.saveTicketAssign(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xoa yeu cau phoi hop moi tao
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @DeleteMapping(value = "/ticket-assigns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeTicketAssignById(@AuthenticationPrincipal Authentication authentication,
                                                         TicketAssignDTO dataParams) {
        Object resultObj = ticketAssignService.removeTicketAssignById(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
