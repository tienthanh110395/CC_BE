package com.viettel.etc.controllers;

import com.viettel.etc.dto.*;
import com.viettel.etc.services.TicketSiteService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1 + "/ticket-site")
public class TicketSiteController {

    @Autowired
    TicketSiteService ticketSiteService;

    /**
     * Tao moi don vi chiu trach nhiem
     *
     * @param authentication: thong tin nguoi dung
     * @param dto             params client
     * @return
     */
    @PostMapping(value = "/save-or-update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOrUpdate(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSiteConfigDTO dto, TicketSiteUserDTO dtoSiteUser) {
        Object resultObj = ticketSiteService.createUpdate(authentication, dto, dtoSiteUser);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Đổi trạng thái 1 don vi chiu trach nhiem
     *
     * @param authentication: thong tin nguoi dung
     * @param dto             params client
     * @return
     */
    @PostMapping(value = "/change-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatus(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSiteConfigDTO dto) {
        Object resultObj = ticketSiteService.changeStatus(dto, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Doi trang thai multiple don vi chiu trach nhiem
     * @param dto
     * @return
     */
    @PostMapping(value = "/change-status-multiple", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatusMultiple(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSiteConfigDTO dto) {
        Object resultObj = ticketSiteService.updateStatusMultiple(dto, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tim kiem thong tin don vi chiu trach nhiem
     *
     * @param authentication
     * @param dto
     * @return
     */
    @PostMapping(value = "/search-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doSearch(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSiteSearchDTO dto) {
        Object result = ticketSiteService.searchTicketSite(authentication, dto);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /**
     * Lay du lieu don vi chiu trach nhiem
     *
     * @param authentication
     * @return
     */
    @PostMapping(value = "/get-ticket-site", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSite(@AuthenticationPrincipal Authentication authentication) {
        Object resultObj = ticketSiteService.getTicketSite(authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xoa don vi chiu trach nhiem
     * @param authentication
     * @param ticketSiteId
     * @return
     */
    @PostMapping(value = "/delete/{ticketSiteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDelete(@AuthenticationPrincipal Authentication authentication,
                                           @Valid @PathVariable(name = "ticketSiteId") Long ticketSiteId) {
        Object resultObj = ticketSiteService.onDelete(ticketSiteId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet don vi chiu trach nhiem
     * @param authentication
     * @param ticketSiteId
     * @return
     */
    @GetMapping(value = "/data-detail/{ticketSiteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetail(@AuthenticationPrincipal Authentication authentication,
                                                @Valid @PathVariable(name = "ticketSiteId") Long ticketSiteId, TicketSiteConfigDTO dto) {
        Object resultObj = ticketSiteService.getDataDetail(ticketSiteId, dto);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay thong tin user client
     * @param authentication
     * @return
     */
    @GetMapping(value = "/get-all-user-by-client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUserByClientId(@AuthenticationPrincipal Authentication authentication) {
        Object resultObj = ticketSiteService.getAllUser(authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-ticket-site-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllSiteUser(@AuthenticationPrincipal Authentication authentication) {
        Object resultObj = ticketSiteService.getAllSiteUser(authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
