package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketAdjustChargeDTO;
import com.viettel.etc.repositories.tables.TicketAdjustChargeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketAdjustChargeService;
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.utils.FnCommon;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * Autogen class: Lop thao tac tim kiem ticket
 *
 * @author ToolGen
 * @date Wed Mar 03 10:22:47 ICT 2021
 */
@Service
public class TicketAdjustChargeServiceImpl implements TicketAdjustChargeService {

    @Autowired
    TicketAttachmentService ticketAttachmentService;

    @Autowired
    TicketAdjustChargeRepositoryJPA ticketAdjustChargeRepositoryJPA;

    @Autowired
    TicketAttachmentServiceJPA ticketAttachmentServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    TicketServiceJPA ticketServiceJPA;

    /**
     * Tao yeu cau dieu chinh cuoc
     *
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    @Transactional
    public Object saveTicketAdjustCharge(TicketAdjustChargeDTO itemParamsEntity, Authentication authentication) {
        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.validateFileAttach(itemParamsEntity.getAttachmentFiles());
        }
        String actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        ModelMapper mapper = new ModelMapper();
        TicketAdjustChargeEntity ticketAdjustChargeEntity = mapper.map(itemParamsEntity, TicketAdjustChargeEntity.class);
        TicketAdjustChargeEntity oldTicketAdjustChargeEntity = null;
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketAdjustChargeId())) {
            oldTicketAdjustChargeEntity = ticketAdjustChargeRepositoryJPA.findById(itemParamsEntity.getTicketAdjustChargeId()).get();
            actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
            ticketAdjustChargeEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
            ticketAdjustChargeEntity.setUpdateUser(FnCommon.getUserLogin(authentication));
        } else {
            ticketAdjustChargeEntity.setStatus(TicketAdjustChargeEntity.Status.VALID.value);
            ticketAdjustChargeEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
            ticketAdjustChargeEntity.setCreateUser(FnCommon.getUserLogin(authentication));
        }
        TicketEntity ticketEntity = ticketServiceJPA.getOne(ticketAdjustChargeEntity.getTicketId());
        ticketAdjustChargeRepositoryJPA.save(ticketAdjustChargeEntity);
        /* Luu log */
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setTicketId(ticketAdjustChargeEntity.getTicketId());
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketAdjustChargeEntity, ticketAdjustChargeEntity, ticketAdjustChargeEntity.getTicketAdjustChargeId(), actionName);

        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.saveTicketAttachment(itemParamsEntity.getAttachmentFiles(),
                    ticketAdjustChargeEntity.getTicketAdjustChargeId(), authentication,
                    ticketAdjustChargeEntity.getTicketId(), TicketAttachmentEntity.Type.TICKET_ADJUST_CHARGE.value, actionAuditEntity.getActionAuditId(), actionName);
        }
        return ticketAdjustChargeEntity;
    }

    /**
     * Lay thong tin yeu cau dieu chinh cuoc
     *
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object getTicketAdjustCharge(TicketAdjustChargeDTO itemParamsEntity, Authentication authentication) {
        List<TicketAdjustChargeEntity> ticketAdjustChargeEntity = ticketAdjustChargeRepositoryJPA.findAllByTicketIdAndStatus(itemParamsEntity.getTicketId(), TicketAdjustChargeEntity.Status.VALID.value);
        if (FnCommon.isNullOrEmpty(ticketAdjustChargeEntity)) {
            return null;
        }
        if(ticketAdjustChargeEntity.stream().max(Comparator.comparing(TicketAdjustChargeEntity::getTicketAdjustChargeId)).isPresent()) {
            TicketAdjustChargeEntity ticketAdjustChargeMax = ticketAdjustChargeEntity.stream().max(Comparator.comparing(TicketAdjustChargeEntity::getTicketAdjustChargeId)).orElse(null);
            ModelMapper mapper = new ModelMapper();
            TicketAdjustChargeDTO ticketAdjustChargeDTO = mapper.map(ticketAdjustChargeMax, TicketAdjustChargeDTO.class);

            List<TicketAttachmentEntity> ticketAttachmentEntities = ticketAttachmentServiceJPA.getTicketAttachment(ticketAdjustChargeDTO.getTicketId(),
                    TicketAttachmentEntity.Type.TICKET_ADJUST_CHARGE.value);
            ticketAdjustChargeDTO.setTicketAttachmentEntityList(ticketAttachmentEntities);
            return ticketAdjustChargeDTO;
        }
        return null;
    }
}
