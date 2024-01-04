package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.repositories.tables.NotifyConfigRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import com.viettel.etc.repositories.tables.entities.NotifyConfigEntity;
import com.viettel.etc.repositories.tables.entities.OtherCategoriesEntity;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.NotifyConfigService;
import com.viettel.etc.utils.FnCommon;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotifyConfigServiceImpl implements NotifyConfigService {

    private static final Logger log = Logger.getLogger(NotifyConfigServiceImpl.class);

    @Autowired
    private NotifyConfigRepositoryJPA notifyConfigRepositoryJPA;

    @Autowired
    private ActionAuditService actionAuditService;

    @Override
    public Object getNotificationConfigByType(Long type) {
        return notifyConfigRepositoryJPA.findByType(type);
    }

    @Override
    public Object saveListNotifyConfig(List<NotifyConfigEntity> lstDataConfig, Authentication authentication) {

        /**
         * 1. Lấy ra list old
         * 2. save list moi
         */
        try {
            String userNameLogin = FnCommon.getUserLogin(authentication);
            ActionAuditDTO logsUpdate = new ActionAuditDTO();
            logsUpdate.setCreateDate(new Date(System.currentTimeMillis()));
            logsUpdate.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.NOTIFITY_CONFIG.value);
            logsUpdate.setStatus(1L);

            List<NotifyConfigEntity> lstDataSave = new ArrayList<>();
            List<NotifyConfigEntity> lstDataOld = new ArrayList<NotifyConfigEntity>();
            List<NotifyConfigEntity> lstDataTemp = notifyConfigRepositoryJPA.findByType(lstDataConfig.get(0).getType());
            lstDataOld = FnCommon.clone(lstDataTemp);
            for (NotifyConfigEntity notifyConfigEntity : lstDataConfig) {
                NotifyConfigEntity oldTemp = notifyConfigRepositoryJPA.getOne(notifyConfigEntity.getType());
                NotifyConfigEntity notifyOld = (NotifyConfigEntity) oldTemp;

                boolean valueChange = false;
                if (notifyOld.getIsMail() != notifyConfigEntity.getIsMail()) {
                    notifyOld.setIsMail(notifyConfigEntity.getIsMail());
                    valueChange = true;
                }
                if (notifyOld.getIsSms() != notifyConfigEntity.getIsSms()) {
                    notifyOld.setIsSms(notifyConfigEntity.getIsSms());
                    valueChange = true;
                }
                if (valueChange) {
                    notifyOld.setUpdateDate(new Date(System.currentTimeMillis()));
                    notifyOld.setUpdateUser(userNameLogin);
                    lstDataSave.add(notifyOld);
                }
            }
            List<NotifyConfigEntity> entitylogs = notifyConfigRepositoryJPA.saveAll(lstDataConfig);
            //save logs
            String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

            for (NotifyConfigEntity entitylog : entitylogs) {
                NotifyConfigEntity oldEntity = lstDataOld.stream().filter(x -> entitylog.getNotifyConfigId().equals(x.getNotifyConfigId()))
                        .findAny()
                        .orElse(null);
                ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                actionAuditDTO.setDescription(entitylog.getName());
                actionAuditDTO.setCreateDate(entitylog.getCreateDate());
                actionAuditDTO.setTicketAssignId(entitylog.getNotifyConfigId());
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.NOTIFITY_CONFIG.value);
                ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldEntity, entitylog, entitylog.getNotifyConfigId(), actionType);
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketSlaServiceImpl createUpdate", ex);
        }
        return null;
    }
}
