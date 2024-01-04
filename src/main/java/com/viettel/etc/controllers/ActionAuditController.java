package com.viettel.etc.controllers;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.dto.ActionAuditSearchDTO;
import com.viettel.etc.services.ActionAuditService;
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
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class ActionAuditController {

    @Autowired
    ActionAuditService actionAuditService;

    /**
     * Api tim kiem thong tin lich su tac dong
     *
     * @param authentication
     * @param actionAuditDTO
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/action-audits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getActionAudit(@AuthenticationPrincipal Authentication authentication, ActionAuditDTO actionAuditDTO) {
        Object result = actionAuditService.getActionAudit(actionAuditDTO, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /**
     * Tim kiem thong tin lich su log tac dong
     *
     * @param authentication
     * @param dataParams
     * @return
     */
    @PostMapping(value = "/search-data-impact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchDataImpact(@AuthenticationPrincipal Authentication authentication, @RequestBody ActionAuditSearchDTO dataParams) {
        Object result = actionAuditService.searchImpactLog(authentication, dataParams);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    /***
     * Lay chi tiet thong tin log tac dong
     * @param authentication
     * @param params
     * @return
     */
    @PostMapping(value = "/data-detail-impact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataDetailImpact(@AuthenticationPrincipal Authentication authentication, @RequestBody ActionAuditDetailDTO params) {
        Object resultObj = actionAuditService.getDataDetailImpact(authentication, params);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }

    /***
     * Xuat excel danh sach lich su tac dong
     * @param params
     * @param response
     */
    @PostMapping(value = "/export-impact-log", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportTicket(@RequestBody ActionAuditSearchDTO params, HttpServletResponse response) {
        actionAuditService.exportImpactLog(params, response);
    }
}
