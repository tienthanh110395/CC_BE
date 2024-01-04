package com.viettel.etc.repositories;

import com.viettel.etc.dto.*;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;

public interface ActionAuditRepository {
    Object getActionAudit(ActionAuditDTO actionAuditDTO);

    Object searchImpactLog(Authentication authentication, ActionAuditSearchDTO params);

    Object getDataDetailImpact(ActionAuditDetailDTO params);

    ResultSelectEntity exportImpactLog(ActionAuditSearchDTO params);
}
