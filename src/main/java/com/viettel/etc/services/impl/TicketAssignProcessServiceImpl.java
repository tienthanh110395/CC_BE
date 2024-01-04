package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketAssignProcessDTO;
import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.repositories.TicketAssignProcessRepository;
import com.viettel.etc.repositories.tables.TicketAssignProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketAssignRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketAssignProcessService;
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.utils.FnCommon;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Autogen class: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * @date Tue Mar 02 11:15:25 ICT 2021
 */
@Service
public class TicketAssignProcessServiceImpl implements TicketAssignProcessService {

    @Autowired
    TicketAssignProcessRepository ticketAssignProcessRepository;

    @Autowired
    TicketAssignProcessRepositoryJPA ticketAssignProcessRepositoryJPA;

    @Autowired
    TicketAssignRepositoryJPA ticketAssignRepositoryJPA;

    @Autowired
    TicketAttachmentService ticketAttachmentService;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    TicketServiceJPA ticketServiceJPA;


    /**
     * Luu ket qua xu ly ticket
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object saveTicketAssignProcess(TicketAssignProcessDTO itemParamsEntity, Authentication authentication) {
        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.validateFileAttach(itemParamsEntity.getAttachmentFiles());
        }
        String userLogin = FnCommon.getUserLogin(authentication);

        ModelMapper mapper = new ModelMapper();
        TicketAssignProcessEntity ticketAssignProcessEntity = mapper.map(itemParamsEntity, TicketAssignProcessEntity.class);

        ticketAssignProcessEntity.setCreateUser(userLogin);
        ticketAssignProcessEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketAssignProcessRepositoryJPA.save(ticketAssignProcessEntity);

        /* Luu log */
        String actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        TicketEntity ticketEntity = ticketServiceJPA.getOne(ticketAssignProcessEntity.getTicketId());
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setTicketId(ticketEntity.getTicketId());
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        actionAuditDTO.setDescription(ticketEntity.getNote());
        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), null, ticketAssignProcessEntity, ticketAssignProcessEntity.getTicketAssignProcessId(), actionName);

        TicketAssignEntity ticketAssignEntity = ticketAssignRepositoryJPA.findById(ticketAssignProcessEntity.getTicketAssignId()).get();
        TicketAssignEntity oldTicketAssignEntity = new TicketAssignEntity();
        mapper.map(ticketAssignEntity, oldTicketAssignEntity);
        
        ticketAssignEntity.setTicketStatus(ticketAssignProcessEntity.getStatus());
        ticketAssignEntity.setUpdateUser(userLogin);
        ticketAssignEntity.setUserProcess(userLogin);
        ticketAssignEntity.setToUsername(userLogin);
        ticketAssignEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketAssignEntity.setResolveDate(new java.sql.Date(System.currentTimeMillis()));
        ticketAssignRepositoryJPA.save(ticketAssignEntity);

        /* Luu log */
        actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketAssignEntity, ticketAssignEntity, ticketAssignEntity.getTicketAssignId(), actionName);

        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.saveTicketAttachment(itemParamsEntity.getAttachmentFiles(),
                    ticketAssignProcessEntity.getTicketAssignProcessId(), authentication,
                    ticketAssignProcessEntity.getTicketId(), TicketAttachmentEntity.Type.TICKET_ASSIGN_PROCESS.value, actionAuditEntity.getActionAuditId(), actionName);
        }
        return ticketAssignProcessEntity;
    }

    /***
     * Lay thong tin xu ly phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object getTicketAssignProcess(TicketStatusDTO itemParamsEntity, Authentication authentication) {
        return ticketAssignProcessRepository.getTicketAssignProcess(itemParamsEntity, authentication);
    }
}
