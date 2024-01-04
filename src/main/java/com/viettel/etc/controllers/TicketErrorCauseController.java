package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketErrorCauseDTO;
import com.viettel.etc.dto.TicketErrorCauseNewDTO;
import com.viettel.etc.dto.TicketErrorCauseSearchDTO;
import com.viettel.etc.services.TicketErrorCauseService;
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
 * Autogen class: Lop thao danh muc nguyen nhan loi
 *
 * @author ToolGen
 * @date Thu Jun 03 13:45:55 ICT 2021
 */
@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketErrorCauseController {

    @Autowired
    TicketErrorCauseService ticketErrorCauseService;

    /**
     * Danh muc nguyen nhan loi
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/ticket-error-causes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketErrorCause(@AuthenticationPrincipal Authentication authentication,
                                                      TicketErrorCauseDTO dataParams) {
        Object resultObj = ticketErrorCauseService.getTicketErrorCause(dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Tìm kiếm danh mục nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/category-reason-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchTicketErrorCause(@AuthenticationPrincipal Authentication authentication,
                                                         TicketErrorCauseDTO dataParams) {
        Object resultObj = ticketErrorCauseService.searchTicketErrorCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Tìm kiếm tree danh mục nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @return
     */
    @GetMapping(value = "/category-reason-error-tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchTreeTicketErrorCause(@AuthenticationPrincipal Authentication authentication,
                                                             TicketErrorCauseDTO dataParams) {
        Object resultObj = ticketErrorCauseService.searchTreeTicketErrorCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tạo mới danh mục nguyên nhân lỗi
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/category-reason-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTicketErrorCause(@AuthenticationPrincipal Authentication authentication,
                                                         @RequestBody TicketErrorCauseDTO dataParams) {
        Object resultObj = ticketErrorCauseService.createTicketErrorCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Sửa danh mục nguyên nhân lỗi
     *
     * @param authentication:    thong tin nguoi dung
     * @param dataParams         params client
     * @param ticketErrorCauseId params client
     * @return
     */
    @PutMapping(value = "/ticket-error-cause/{ticketErrorCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTicketErrorCause(@AuthenticationPrincipal Authentication authentication,
                                                         @PathVariable Long ticketErrorCauseId, @RequestBody TicketErrorCauseDTO dataParams) throws EtcException {
        Object resultObj = ticketErrorCauseService.updateTicketErrorCause(dataParams, ticketErrorCauseId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xóa 1 bản ghi danh mục nguyên nhân lỗi
     * @param authentication
     * @param ticketErrorCauseId
     * @return
     */
    @DeleteMapping(value = "/delete-error-cause/{ticketErrorCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDelete(@AuthenticationPrincipal Authentication authentication,
                                           @Valid @PathVariable(name = "ticketErrorCauseId") Long ticketErrorCauseId) {
        Object resultObj = ticketErrorCauseService.onDelete(ticketErrorCauseId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lấy danh sách nguyên nhân lỗi
     * @param authentication
     * @param dataParams
     * @return
     */

    @PostMapping(value = "/search-data-ticket-error-cause", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doSearch(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketErrorCauseSearchDTO dataParams) {
        Object result = ticketErrorCauseService.searchTicketErrorCauseNew(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /***
     * lay list data nguyên nhân lỗi
     * @param authentication
     * @param params
     * @return
     */
    @PostMapping(value = "/ticket-error-cause-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketErrorCauseSearch(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketErrorCauseNewDTO params) {
        Object resultObj = ticketErrorCauseService.getTicketErrorCauseById(params, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tạo mới danh mục nguyên nhân lỗi
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-error-cause-create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTicketErrorCauseNew(@AuthenticationPrincipal Authentication authentication,
                                                            @RequestBody TicketErrorCauseNewDTO dataParams) {
        Object resultObj = ticketErrorCauseService.createTicketErrorCauseNew(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Đổi trạng thái 1 danh mục
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/change-status-ticket-error-cause", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatusTicketErrorCause(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketErrorCauseNewDTO dataParams) {
        Object resultObj = ticketErrorCauseService.changeStatusTicketErrorCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Doi trang thai multiple:  nguyen nhan loi
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/change-status-multiple-ticket-error-cause", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatusTicketErrorCause(@RequestBody TicketErrorCauseNewDTO dataParams, @AuthenticationPrincipal Authentication authentication) {
        Object resultObj = ticketErrorCauseService.changeStatusMultipleTicketErrorCause(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet 1 danh muc:  nhom, the loai, loai phan anh
     * @param authentication
     * @param ticketErrorCauseId
     * @return
     */
    @GetMapping(value = "/data-detail/{ticketErrorCauseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetail(@AuthenticationPrincipal Authentication authentication,
                                                @Valid @PathVariable(name = "ticketErrorCauseId") Long ticketErrorCauseId, TicketErrorCauseNewDTO params) {
        Object resultObj = ticketErrorCauseService.getDataDetailTicketErrorCause(ticketErrorCauseId, params);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }


    @PostMapping(value = "/error-cause-by-parent-id/{levelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getErrorCauseByParentId(@AuthenticationPrincipal Authentication authentication,
                                                          @RequestBody List<Long> lstParentId,
                                                          @PathVariable Long levelId) {
        Object resultObj = ticketErrorCauseService.getErrorCauseByParentId(lstParentId, levelId);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
