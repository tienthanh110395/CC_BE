package com.viettel.etc.controllers;

import com.viettel.etc.dto.*;
import com.viettel.etc.services.TicketCategoryService;
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
import java.util.Date;

/**
 * Autogen class: Lop thao tac  danh sach cac danh muc
 *
 * @author ToolGen
 * @date Tue Mar 02 14:25:35 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketCategoryController {

    @Autowired
    TicketCategoryService ticketCategoryService;


    /**
     * Lay thong tin nguon phan anh
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-sources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSource(@AuthenticationPrincipal Authentication authentication,
                                                  TicketSourceDTO dataParams) {
        Object resultObj = ticketCategoryService.getTicketSource(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach loai  phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/ticket-types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketType(@AuthenticationPrincipal Authentication authentication, TicketTypeDTO dataParams) {
        Object resultObj = ticketCategoryService.getTicketTypes(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach loai  phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-types/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketTypeTree(@AuthenticationPrincipal Authentication authentication, TicketTypeDTO dataParams) {
        Object resultObj = ticketCategoryService.getTicketTypesTree(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach phong ban
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-sites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSiteByParentId(@AuthenticationPrincipal Authentication authentication, TicketSiteDTO dataParams) {
        Object resultObj = ticketCategoryService.getTicketSiteByParentId(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach thoi hạn xu ly phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-slas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSla(@AuthenticationPrincipal Authentication authentication, TicketSLADTO dataParams) {
        Object resultObj = ticketCategoryService.getTicketSla(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach thoi hạn xu ly phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-slas-new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDateTicketSlaNew(@AuthenticationPrincipal Authentication authentication, TicketSLADTO dataParams) {
        TicketSLADTO ticketSLADTO = new TicketSLADTO();
        Date slaDate = ticketCategoryService.getDateTicketSlaNew(dataParams.getPriorityId(), dataParams.getTicketTypeId(), authentication, ticketSLADTO.getSla());
        ticketSLADTO.setSlaDate(slaDate);
        return new ResponseEntity<>(FunctionCommon.responseToClient(ticketSLADTO), HttpStatus.OK);
    }

    /***
     * Lay danh sach thoi hạn xu ly phan anh
     * @param authentication
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @PostMapping(value = "/ticket-slas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTicketSla(@AuthenticationPrincipal Authentication authentication, TicketSLADTO dataParams) {
        Object resultObj = ticketCategoryService.saveTicketSla(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay thoi han xu ly cua 1 phan anh
     * @param authentication
     * @param siteId
     * @param sourceId
     * @param ticketTypeId
     * @param dataParams
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-slas/{siteId}/{sourceId}/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSlaDetail(@AuthenticationPrincipal Authentication authentication,
                                                     @Valid @PathVariable(name = "siteId") Long siteId,
                                                     @Valid @PathVariable(name = "sourceId") Long sourceId,
                                                     @Valid @PathVariable(name = "ticketTypeId") Long ticketTypeId,
                                                     TicketSLADTO dataParams) {
        dataParams.setSiteId(siteId);
        dataParams.setSourceId(sourceId);
        dataParams.setTicketTypeId(ticketTypeId);
        Object resultObj = ticketCategoryService.getTicketSlaDetail(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach nhan vien quan ly
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/ticket-sites-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSiteUser(@AuthenticationPrincipal Authentication authentication, TicketSiteUserDTO dataParams) {
        Object resultObj = ticketCategoryService.getTicketSiteUser(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay danh sach ly do gia han
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/ticket-extent-reasons", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketExtentReason(@AuthenticationPrincipal Authentication authentication, TicketExtentReasonDTO dataParams) {
        Object resultObj = ticketCategoryService.getTicketExtentReason(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
