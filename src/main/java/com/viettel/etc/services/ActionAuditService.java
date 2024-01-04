package com.viettel.etc.services;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.dto.ActionAuditSearchDTO;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ActionAuditService {
    ActionAuditEntity saveActAudit(Authentication authentication, ActionAuditDTO actionAuditDTO);

    List<ActionAuditDetailEntity> saveActAuditDetail(Long actionAuditId, Object oldEntity, Object newEntity, Long pkId, String actionName);

    List<ActionAuditDetailEntity> saveActAuditDetailDelete(Long actionAuditId, Object oldEntity, Object newEntity, Long pkId, String actionName);

    void saveActAuditAndActAuditDetail(Authentication authentication, ActionAuditDTO actionAuditDTO, Object oldEntity, Object newEntity, Long pkId, String actionName);

    Object getActionAudit(ActionAuditDTO actionAuditDTO, Authentication authentication);

    Object searchImpactLog(Authentication authentication, ActionAuditSearchDTO params);

    Object getDataDetailImpact(Authentication authentication, ActionAuditDetailDTO params);

    void exportImpactLog(ActionAuditSearchDTO params, HttpServletResponse response);

    List<ActionAuditDetailEntity> saveAllActAuditDetail(Long actionAuditId, Object oldEntity, Object newEntity, Long pkId, String actionName);
}
