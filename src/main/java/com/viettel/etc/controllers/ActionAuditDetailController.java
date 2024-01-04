package com.viettel.etc.controllers;

import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.services.ActionAuditDetailService;
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

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class ActionAuditDetailController {

    @Autowired
    ActionAuditDetailService actionAuditDetailService;

    /**
     * Api tim kiem thong tin chi tiet lich su tac dong
     *
     * @param authentication
     * @param actionAuditDetailDTO
     * @return
     */
    @RolesAllowed({Constants.ROLE_CC.ROLE_ADMIN_CC, Constants.ROLE_CC.ROLE_HOSO_CC})
    @GetMapping(value = "/action-audit-details/{actionAuditId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllByActionAuditId(@AuthenticationPrincipal Authentication authentication,
                                                         ActionAuditDetailDTO actionAuditDetailDTO,
                                                         @PathVariable Long actionAuditId) {
        actionAuditDetailDTO.setActionAuditId(actionAuditId);
        Object result = actionAuditDetailService.getActAuditDetail(authentication, actionAuditDetailDTO);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }


    @GetMapping(value = "/audit-details-by-audit-id/{actionAuditId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getActionAuditDetailByAuditId(@AuthenticationPrincipal Authentication authentication,
                                                                ActionAuditDetailDTO actionAuditDetailDTO,
                                                                @PathVariable Long actionAuditId) {
        actionAuditDetailDTO.setActionAuditId(actionAuditId);
        Object result = actionAuditDetailService.getActAuditDetail(authentication, actionAuditDetailDTO);
        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }
}
