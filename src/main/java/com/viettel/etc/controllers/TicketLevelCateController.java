package com.viettel.etc.controllers;

import com.viettel.etc.dto.TicketLevelCateDTO;
import com.viettel.etc.dto.TicketLevelCateSearchDTO;
import com.viettel.etc.services.TicketLevelCateService;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class TicketLevelCateController {
    @Autowired
    TicketLevelCateService ticketLevelCateService;

    /***
     * Xóa 1 danh muc
     * @param
     *
     * @param ticketLevelCateId
     * @return
     */
    @DeleteMapping(value = "/ticket-cate-config/delete-ticket-level-cate/{ticketLevelCateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> onDelete(@AuthenticationPrincipal Authentication authentication,
                                           @Valid @PathVariable(name = "ticketLevelCateId") Long ticketLevelCateId) {
        Object resultObj = ticketLevelCateService.onDelete(ticketLevelCateId, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Đổi trạng thái 1 danh mục
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/ticket-cate-config/change-status-ticket-level-cate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeStatus(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketLevelCateDTO dataParams) {
        Object resultObj = ticketLevelCateService.changeStatus(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * Tim kiem thong tin 1 danh muc: nhom, the loai, loai
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/ticket-cate-config/search-ticket-level-cate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchTicketLevelCate(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketLevelCateSearchDTO dataParams) {
        Object result = ticketLevelCateService.searchTicketLevelCate(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /**
     * Tao moi
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @PostMapping(value = "/ticket-cate-config/save-or-update-ticket-level-cate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveOrUpdate(@AuthenticationPrincipal Authentication authentication, @RequestBody TicketLevelCateDTO dataParams) {
        Object resultObj = ticketLevelCateService.createUpdate(dataParams, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    @PostMapping(value = "/ticket-cate-config/save-list-ticket-level-cate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveListTicketLevelCate(@AuthenticationPrincipal Authentication authentication, @RequestBody List<TicketLevelCateDTO> dataParams) {
        Object resultObj = null;
        try {
            resultObj = ticketLevelCateService.createUpdateList(dataParams, authentication);
        } catch (SQLIntegrityConstraintViolationException throwables) {
            return ResponseEntity.badRequest().body("duplicate-ticket-level-cate");
        } catch (ArithmeticException ar) {
            return ResponseEntity.badRequest().body("duplicate-value");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("null-data");
        }
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Lay chi tiet 1 danh muc:
     * @param authentication
     * @param ticketLevelCateId
     * @return
     */
    @GetMapping(value = "/ticket-cate-config/data-detail-level-cate/{ticketLevelCateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetail(@AuthenticationPrincipal Authentication authentication,
                                                @Valid @PathVariable(name = "ticketLevelCateId") Long ticketLevelCateId, TicketLevelCateDTO params) {
        Object resultObj = ticketLevelCateService.getDataDetail(ticketLevelCateId, params);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    @GetMapping(value = "/ticket-cate-config/get-list-ticket-other-cate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListOtherCateByConfigTime(@AuthenticationPrincipal Authentication authentication) {
        Object resultObj = ticketLevelCateService.getListOtherCateConfigProcessTime(authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     * lay thong tin theo list
     *
     * @param authentication
     * @param params
     * @return
     */
    @PostMapping(value = "/ticket-cate-config/get-list-ticket-level-cate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketLevelCateNameById(@AuthenticationPrincipal Authentication authentication,
                                                             @RequestBody TicketLevelCateDTO params) {
        Object resultObj = ticketLevelCateService.getTicketLevelCateNameById(params, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}


