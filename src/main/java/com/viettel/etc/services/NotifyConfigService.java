package com.viettel.etc.services;

import com.viettel.etc.repositories.tables.entities.NotifyConfigEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NotifyConfigService {

    Object getNotificationConfigByType(Long type);

    Object saveListNotifyConfig(List<NotifyConfigEntity> lstDataConfig, Authentication authentication);

}
