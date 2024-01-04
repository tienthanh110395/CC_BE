package com.viettel.etc.controllers;

import com.viettel.etc.dto.*;
import com.viettel.etc.services.TicketCateConfigService;
import com.viettel.etc.services.TicketSlaService;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.UserSystemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.viettel.etc.utils.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Wed Jan 26 09:59:22 ICT 2022
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketSlaController {
    @Autowired
    private TicketSlaService ticketSlaService;

    @Autowired
    TicketCateConfigService ticketCateConfigService;

    @Value("${crm.common.max-file-size}")
    private Long maxFileSize;

    /**
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @GetMapping(value = "ticket-sla", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSla(@AuthenticationPrincipal Authentication authentication,
                                               TicketSLADTO dataParams) {
        Object resultObj = ticketSlaService.getTicketSla(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Lay chi tiet sla
     *
     * @param authentication: thong tin serial
     * @param ticketTypeId
     * @return
     */
    @GetMapping(value = "/ticket-sla/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketSlaDetail(@AuthenticationPrincipal Authentication authentication,
                                                     @PathVariable Long ticketTypeId) {
        Object resultObj = ticketSlaService.getTicketSlaDetail(ticketTypeId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Hiển thị danh sách và Tìm kiếm cấu hình thời gian xử lý
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-sla/search-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataSearch(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSlaSearchDTO dataParams) {
        Object result = ticketSlaService.getListDataTicketSLA(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /***
     * Them moi cau hinh thoi gian xu ly
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-sla/save-or-update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOrUpdateTicketSLA(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSLANewDTO dataParams) {
        Object resultObj = ticketSlaService.createOrUpdate(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet 1 cau hinh thoi gian
     * @param authentication
     * @param ticketTypeId
     * @return
     */
    @GetMapping(value = "/ticket-sla/get-data-detail/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetail(@AuthenticationPrincipal Authentication authentication,
                                                @Valid @PathVariable(name = "ticketTypeId") Long ticketTypeId, TicketSLANewDTO params) {
        Object resultObj = ticketSlaService.getDataDetail(ticketTypeId, params);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa cau hinh thoi gian xu ly
     * @param authentication
     * @param ticketTypeId
     * @return
     */
    @DeleteMapping(value = "/ticket-sla/delete-ticket-sla/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDelete(@AuthenticationPrincipal Authentication authentication,
                                           @Valid @PathVariable(name = "ticketTypeId") Long ticketTypeId) {
        Object resultObj = ticketSlaService.doDeleteData(ticketTypeId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }


    /***
     * Xuat file excel cau hinh thoi gian xu ly
     * @param authentication
     * @param dataParams
     * @param response
     * @throws IOException
     */
    @PostMapping(value = "/ticket-sla/exports", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicketExtent(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSlaSearchDTO dataParams, HttpServletResponse response) {
        ticketSlaService.exportTicketSla(dataParams, response);
    }

    /**
     * Tai file template cau hinh thoi gian xu ly
     *
     * @param authentication Ma xac thuc
     * @return File template response
     */
    @PostMapping("/ticket-sla/download-template")
    public void downloadServicePlanTemplate(@AuthenticationPrincipal Authentication authentication, HttpServletResponse response) {
        ticketSlaService.downloadServicePlanTemplate(authentication, response);
    }

    /**
     * Import du lieu bang cuoc gia ve bang file excel
     *
     * @param authentication Ma xac thuc
     * @return File excel
     */
    @PostMapping("/ticket-sla/import-processing-time")
    public ResponseEntity<?> importExceptionList(@AuthenticationPrincipal Authentication authentication,
                                                 @RequestPart(name = "file") MultipartFile excelFile) throws IOException {
        return ticketSlaService.importTicketProcessTime(authentication, excelFile);
    }
    /***
     * cap nhat thoi gian xu ly
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-sla/update-reception-time", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateReceptionTime(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketSLANewDTO dataParams) {
        Object resultObj = ticketSlaService.updateReception(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    @GetMapping(value = "/ticket-sla/ticket-type-for-map-config-time/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketTypeForMapConfigTime(@AuthenticationPrincipal Authentication authentication,
                                                                @PathVariable Long parentId) {
        Object resultObj = ticketCateConfigService.getTicketTypeForMapConfigTime(parentId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    @GetMapping(value = "/ticket-sla/ticket-type-for-config-time-detail/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketTypeForConfigTimeDetail(@AuthenticationPrincipal Authentication authentication,
                                                                @PathVariable Long ticketTypeId) {
        Object resultObj = ticketCateConfigService.getTicketTypeForConfigTimeDetail(ticketTypeId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}