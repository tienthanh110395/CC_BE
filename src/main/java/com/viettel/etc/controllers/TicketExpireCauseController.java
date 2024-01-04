package com.viettel.etc.controllers;

import com.viettel.etc.dto.*;
import com.viettel.etc.services.TicketExpireCauseService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.exceptions.EtcException;
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
import java.util.List;

/**
 * Autogen class: Lop thao danh muc nguyen nhan qua han
 *
 * @author ToolGen
 * @date Thu Jun 03 11:31:36 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketExpireCauseController {
    @Autowired
    TicketExpireCauseService ticketExpireCauseService;


    /**
     * Danh muc nguyen nhan qua han
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-expire-causes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketExpireCause(@AuthenticationPrincipal Authentication authentication,
                                                       TicketExpireCauseDTO dataParams) {
        Object resultObj = ticketExpireCauseService.getTicketExpireCause(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Tìm kiếm danh mục nguyên nhân quá hạn
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/category-reason-expire", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchTicketExpireCause(@AuthenticationPrincipal Authentication authentication,
                                                       TicketExpireCauseDTO dataParams) {
        Object resultObj = ticketExpireCauseService.searchTicketExpireCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Tìm kiếm tree danh mục nguyên nhân quá hạn
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/category-reason-expire-tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchTreeTicketExpireCause(@AuthenticationPrincipal Authentication authentication,
                                                              TicketExpireCauseDTO dataParams) {
        Object resultObj = ticketExpireCauseService.searchTreeTicketExpireCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tạo mới danh mục nguyên nhân quá hạn
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/category-reason-expire", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTicketExpireCause(@AuthenticationPrincipal Authentication authentication,
                                                         @RequestBody TicketExpireCauseDTO dataParams) {
        Object resultObj = ticketExpireCauseService.createTicketExpireCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Sửa danh mục nguyên nhân quá hạn
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @param ticketExpireCauseId    params client
     * @return
     */
    @PutMapping(value = "/category-reason-expire/{ticketExpireCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTicketExpireCause(@AuthenticationPrincipal Authentication authentication,
                                                         @PathVariable Long ticketExpireCauseId, @RequestBody TicketExpireCauseDTO dataParams) throws EtcException {
        Object resultObj = ticketExpireCauseService.updateTicketExpireCause(dataParams, ticketExpireCauseId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa 1 bản ghi danh mục nguyên nhân quá hạn
     * @param authentication
     * @param ticketExpireCauseId
     * @return
     */
    @DeleteMapping(value = "/category-reason-expire/{ticketExpireCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeTicketErrorCause(@AuthenticationPrincipal Authentication authentication,
                                                         @Valid @PathVariable(name = "ticketExpireCauseId") Long ticketExpireCauseId) {
        Object resultObj = ticketExpireCauseService.removeTicketExpireCause(ticketExpireCauseId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Hiển thị danh sách và Tìm kiếm danh mục nguyên nhân quá hạn
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-expire-cause/search-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListDataSearch(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketExpireCauseSearchDTO dataParams) {
        Object result = ticketExpireCauseService.getListDataExpireCause(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @PostMapping(value = "/get-ticket-expire-by-parent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getExpireLvByParentId(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketExpireCauseDTO dataParams) {
        Object resultObj = ticketExpireCauseService.getExpireLevelByParentId(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tao moi nguyen nhan loi
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/ticket-expire-cause/save-or-update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOrUpdateTicketExpireCause(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketExpireCauseNewDTO dataParams) {
        Object resultObj = ticketExpireCauseService.createOrUpdate(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa nguyen nhan qua han
     * @param authentication
     * @param ticketExpireCauseId
     * @return
     */
    @DeleteMapping(value = "/delete-expire-cause/{ticketExpireCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDelete(@AuthenticationPrincipal Authentication authentication,
                                           @Valid @PathVariable(name = "ticketExpireCauseId") Long ticketExpireCauseId) {
        Object resultObj = ticketExpireCauseService.doDeleteData(ticketExpireCauseId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet 1 danh muc:  nguyen nhan qua han
     * @param authentication
     * @param ticketExpireCauseId
     * @return
     */
    @GetMapping(value = "/get-data-detail/{ticketExpireCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetail(@AuthenticationPrincipal Authentication authentication,
                                                @Valid @PathVariable(name = "ticketExpireCauseId") Long ticketExpireCauseId, TicketExpireCauseNewDTO params) {
        Object resultObj = ticketExpireCauseService.getDataDetail(ticketExpireCauseId, params);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Đổi trạng thái 1 danh mục : nhóm,loại,thể loại
     *
     * @param authentication: thong tin nguoi dung
     * @param params      params client
     * @return
     */
    @PostMapping(value = "/ticket-expire-cause/change-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatus(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketExpireCauseNewDTO params) {
        Object resultObj = ticketExpireCauseService.changeStatus(params, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Doi trang thai multiple:  nhom, the loai, loai phan anh
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-expire-cause/change-status-multiple", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatusMultiple(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketExpireCauseNewDTO dataParams) {
        Object resultObj = ticketExpireCauseService.updateStatusMultiple(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

}
