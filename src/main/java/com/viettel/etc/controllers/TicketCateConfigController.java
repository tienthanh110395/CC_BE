package com.viettel.etc.controllers;

import com.viettel.etc.dto.*;
import com.viettel.etc.services.TicketCateConfigService;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1 + "/ticket-cate-config")
public class TicketCateConfigController {

    @Autowired
    TicketCateConfigService ticketCateConfigService;

    /**
     * Tao moi danh muc: nhom, the loai, loai phan anh
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/save-or-update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOrUpdate(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketCateConfigDTO dataParams) {
        Object resultObj = ticketCateConfigService.createOrUpdate(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    @PostMapping(value = "/save-list-ticket-group", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveListTicketGroup(@AuthenticationPrincipal Authentication authentication, @RequestBody List<TicketCateConfigDTO> dataParams) {
        Object resultObj = null;
        try {
            resultObj = ticketCateConfigService.createUpdateList(dataParams, authentication);
        } catch (SQLIntegrityConstraintViolationException throwables) {
            return ResponseEntity.badRequest().body("duplicate-ticket-group");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("null-data");
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa 1 danh muc: nhom, the loai, loai phan anh
     * @param authentication
     * @param ticketTypeId
     * @return
     */
    @DeleteMapping(value = "/delete/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDelete(@AuthenticationPrincipal Authentication authentication,
                                           @Valid @PathVariable(name = "ticketTypeId") Long ticketTypeId) {
        Object resultObj = ticketCateConfigService.onDelete(ticketTypeId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet 1 danh muc:  nhom, the loai, loai phan anh
     * @param authentication
     * @param ticketTypeId
     * @return
     */
    @GetMapping(value = "/data-detail/{ticketTypeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetail(@AuthenticationPrincipal Authentication authentication,
                                                @Valid @PathVariable(name = "ticketTypeId") Long ticketTypeId, TicketCateConfigDTO params) {
        Object resultObj = ticketCateConfigService.getDataDetail(ticketTypeId, params);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tim kiem thong tin 1 danh muc: nhom, the loai, loai
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/search-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doSearch(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketConfigSearchDTO dataParams) {
        Object result = ticketCateConfigService.searchTicketType(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /**
     * Đổi trạng thái 1 danh mục : nhóm,loại,thể loại
     *
     * @param authentication: thong tin nguoi dung
     * @param params          params client
     * @return
     */
    @PostMapping(value = "/change-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatus(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketCateConfigDTO params) {
        Object resultObj = ticketCateConfigService.changeStatus(params, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Doi trang thai multiple:  nhom, the loai, loai phan anh
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/change-status-multiple", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatusMultiple(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketCateConfigDTO dataParams) {
        Object resultObj = ticketCateConfigService.updateStatusMultiple(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * lay thong tin theo list
     *
     * @param authentication
     * @param params
     * @return
     */
    @PostMapping(value = "/ticket-type-by-parent-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketTypeByParentId(@AuthenticationPrincipal Authentication authentication,
                                                          @RequestBody TicketCateConfigDTO params) {
        Object resultObj = ticketCateConfigService.getTicketTypeByParentId(params, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
