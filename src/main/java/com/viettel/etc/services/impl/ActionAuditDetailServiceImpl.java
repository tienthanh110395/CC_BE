package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.repositories.ActionAuditDetailRepository;
import com.viettel.etc.repositories.tables.ActionAuditDetailRepositoryJPA;
import com.viettel.etc.services.ActionAuditDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

@Repository
public class ActionAuditDetailServiceImpl implements ActionAuditDetailService {

    @Autowired
    ActionAuditDetailRepository actionAuditDetailRepository;

    @Autowired
    ActionAuditDetailRepositoryJPA actionAuditDetailRepositoryJPA;

    @Override
    public Object getActAuditDetail(Authentication authentication, ActionAuditDetailDTO actionAuditDetailDTO) {
        return actionAuditDetailRepository.getActionAuditDetails(actionAuditDetailDTO);
    }
}
