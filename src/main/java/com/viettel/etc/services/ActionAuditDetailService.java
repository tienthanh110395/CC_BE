package com.viettel.etc.services;

import com.viettel.etc.dto.ActionAuditDetailDTO;
import org.springframework.security.core.Authentication;

public interface ActionAuditDetailService {
    Object getActAuditDetail(Authentication authentication, ActionAuditDetailDTO actionAuditDetailDTO);
}
