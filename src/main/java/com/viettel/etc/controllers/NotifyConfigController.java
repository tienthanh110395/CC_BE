package com.viettel.etc.controllers;


import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.repositories.tables.entities.MapErrorEntity;
import com.viettel.etc.repositories.tables.entities.NotifyConfigEntity;
import com.viettel.etc.services.NotifyConfigService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_V1)
public class NotifyConfigController {

    @Autowired
    private NotifyConfigService notifyConfigService;


    @GetMapping(value = "/get-notification-config-by-type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNotificationConfigByType(@AuthenticationPrincipal Authentication authentication,
                                                              @PathVariable Long type) {
        return new ResponseEntity<>(FunctionCommon.responseToClient(notifyConfigService.getNotificationConfigByType(type)), HttpStatus.OK);
    }

    @PostMapping(value = "/notify-config/save-notify-config", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveNotifyConfig(@AuthenticationPrincipal Authentication authentication, @RequestBody List<NotifyConfigEntity> lstDataConfig) {
        Object resultObj = notifyConfigService.saveListNotifyConfig(lstDataConfig, authentication);
        return new ResponseEntity<>(FunctionCommon.responseToClient(resultObj), HttpStatus.OK);
    }
}
