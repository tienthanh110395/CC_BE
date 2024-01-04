package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.services.TicketSiteUserService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * Autogen class: Lop thao tac ticket site user
 *
 * @author ToolGen
 * @date Mon Apr 05 09:30:07 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketSiteUserController {

    @Autowired
    TicketSiteUserService ticketSiteUserService;


    /**
     * Lay thong tin phong ban cua 1 user
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-site-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOneTicketSiteUser(@AuthenticationPrincipal Authentication authentication,
                                                       TicketSiteUserDTO dataParams) {
        Object resultObj = ticketSiteUserService.getOneTicketSiteUser(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Lay danh sach user
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC})
    @GetMapping(value = "/ticket-site-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSiteUser(@AuthenticationPrincipal Authentication authentication,
                                                    TicketSiteUserDTO dataParams) {
        Object resultObj = ticketSiteUserService.getTicketSiteUser(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
