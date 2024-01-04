package com.viettel.etc.controllers;import com.viettel.etc.dto.TicketDTO;import com.viettel.etc.dto.TicketProcessShareDTO;import com.viettel.etc.dto.TicketStatisticDTO;import com.viettel.etc.services.TicketProcessShareService;import com.viettel.etc.utils.Constants;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.xlibrary.core.constants.FunctionCommon;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.security.core.Authentication;import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.web.bind.annotation.*;import javax.annotation.security.RolesAllowed;import javax.servlet.http.HttpServletResponse;import javax.validation.Valid;import java.io.IOException;/** * Autogen class: * * @author ToolGen * @date Tue Aug 31 13:49:50 ICT 2021 */@RestController@RequestMapping(Constants.REQUEST_MAPPING_V1)public class TicketProcessShareController {    @Autowired    private TicketProcessShareService ticketProcessShareService;    /**     * @param authentication: thong tin nguoi dung     * @param dataParams      params client     * @return     */    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})    @GetMapping(value = "/ticket-process-shares", produces = MediaType.APPLICATION_JSON_VALUE)    public ResponseEntity<Object> getTicketProcessShare(@AuthenticationPrincipal Authentication authentication,                                                        TicketProcessShareDTO dataParams) {        Object resultObj = ticketProcessShareService.getTicketProcessShare(dataParams);        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);    }    /**     * @param authentication: thong tin nguoi dung     * @param dataParams      params client     * @return     */    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})    @PostMapping(value = "/ticket-process-shares", produces = MediaType.APPLICATION_JSON_VALUE)    public ResponseEntity<Object> saveTicketProcessShare(@AuthenticationPrincipal Authentication authentication,                                                         @Valid @RequestBody TicketProcessShareDTO dataParams) {        Object resultObj = ticketProcessShareService.saveTicketProcessShare(dataParams, authentication);        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);    }}