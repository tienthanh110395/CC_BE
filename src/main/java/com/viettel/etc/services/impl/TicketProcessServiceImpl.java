package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketProcessDTO;
import com.viettel.etc.dto.TicketSLADTO;
import com.viettel.etc.repositories.TicketCategoryRepository;
import com.viettel.etc.repositories.TicketProcessRepository;
import com.viettel.etc.repositories.tables.*;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.services.TicketCategoryService;
import com.viettel.etc.services.TicketProcessService;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Lop thao tac tien xu ly cua CSKH
 *
 * @author ToolGen
 * @date Tue Mar 02 16:00:43 ICT 2021
 */
@Service
public class TicketProcessServiceImpl implements TicketProcessService {

    @Autowired
    TicketProcessRepository ticketProcessRepository;

    @Autowired
    TicketProcessRepositoryJPA ticketProcessRepositoryJPA;

    @Autowired
    TicketAttachmentService ticketAttachmentService;

    @Autowired
    TicketStatusRepositoryJPA ticketStatusRepositoryJPA;

    @Autowired
    TicketRepositoryJPA ticketRepositoryJPA;

    @Autowired
    TicketAssignRepositoryJPA ticketAssignRepositoryJPA;

    @Autowired
    TicketAssignProcessRepositoryJPA ticketAssignProcessRepositoryJPA;

    @Autowired
    TicketCategoryRepository ticketCategoryRepository;

    @Autowired
    TicketSiteUserRepositoryJPA ticketSiteUserRepositoryJPA;

    @Autowired
    TicketAttachmentServiceJPA ticketAttachmentServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    TicketCategoryService ticketCategoryService;

    /**
     * Luu ket qua tien xu ly cu CSKH
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object saveTicketProcess(TicketProcessDTO itemParamsEntity, Authentication authentication) {
        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.validateFileAttach(itemParamsEntity.getAttachmentFiles());
        }
        TicketProcessEntity ticketProcessEntityCheck = ticketProcessRepositoryJPA.findByTicketId(itemParamsEntity.getTicketId());
        if (ticketProcessEntityCheck != null) {
            itemParamsEntity.setTicketProcessId(ticketProcessEntityCheck.getTicketProcessId());
        }
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketProcessId()) && itemParamsEntity.getStatus().equals(TicketStatusEntity.TicketStatus.NEW.value)) {
            throw new EtcException("Khong the chuyen trang thai phan anh sang tao moi !");
        }

        ModelMapper mapper = new ModelMapper();
        TicketProcessEntity ticketProcessEntity = mapper.map(itemParamsEntity, TicketProcessEntity.class);
        ticketProcessEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketProcessEntity.setUpdateUser(FnCommon.getUserLogin(authentication));


        // Valid
        TicketEntity ticketEntity = ticketRepositoryJPA.findById(ticketProcessEntity.getTicketId()).get();
        TicketEntity oldTicketEntity = new TicketEntity();
        mapper.map(ticketEntity, oldTicketEntity);
        ticketEntity.setPhoneNumber(itemParamsEntity.getPhoneNumber());
//        if (ticketEntity.getPhoneNumber() != null && !StringUtils.equals(ticketEntity.getPhoneNumber(), itemParamsEntity.getPhoneNumber())) {
//            ticketEntity.setPhoneNumber(itemParamsEntity.getPhoneNumber());
//        }
        /* Luu log */
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setTicketId(ticketEntity.getTicketId());
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketStatus(ticketProcessEntity.getStatus());
        actionAuditDTO.setDescription(ticketEntity.getNote());
        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);


        if (ticketEntity.getProcessUser() == null) {
            throw new EtcException("Ticket chua duoc giao xu ly");
        }
        TicketSiteUserEntity ticketSiteUserEntity = ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase(ticketEntity.getProcessUser());
        if (ticketSiteUserEntity == null) {
            throw new EtcException("Bạn không có quyền hoặc chưa được giao xử lý ticket");
        }

        String actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        TicketProcessEntity oldTicketProcessEntity = null;
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketProcessId())) {
            actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
            oldTicketProcessEntity = ticketProcessRepositoryJPA.findById(itemParamsEntity.getTicketProcessId()).get();
            ticketProcessEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
            ticketProcessEntity.setUpdateUser(FnCommon.getUserLogin(authentication));
        } else {
            ticketProcessEntity.setCreateUser(FnCommon.getUserLogin(authentication));
            ticketProcessEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        }
        ticketProcessEntity.setProcessTime(itemParamsEntity.getProcessTime());
        ticketProcessRepositoryJPA.save(ticketProcessEntity);

        /* Luu log */
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketProcessEntity, ticketProcessEntity, ticketProcessEntity.getTicketProcessId(), actionName);

        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.saveTicketAttachment(itemParamsEntity.getAttachmentFiles(),
                    ticketProcessEntity.getTicketProcessId(), authentication, ticketProcessEntity.getTicketId(),
                    TicketAttachmentEntity.Type.TICKET_PROCESS.value, actionAuditEntity.getActionAuditId(), actionName);
        }

        TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketId(ticketProcessEntity.getTicketId());
        ticketStatusEntity.setSiteId(itemParamsEntity.getSiteId());
        ticketStatusEntity.setTicketStatus(ticketProcessEntity.getStatus());
        ticketStatusEntity.setProcessTime(new java.sql.Date(System.currentTimeMillis()));
        ticketStatusEntity.setCreateUser(itemParamsEntity.getCreateUser());
        ticketStatusEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketStatusEntity.setProcessContent(ticketProcessEntity.getProcessContent());
        ticketStatusEntity.setUpdateUser(itemParamsEntity.getCreateUser());
        ticketStatusEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketStatusRepositoryJPA.save(ticketStatusEntity);

        /* Luu log */
        actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), null, ticketStatusEntity, ticketStatusEntity.getTicketStatusId(), actionName);


        TicketSLADTO ticketSLADTO = new TicketSLADTO();
        ticketSLADTO.setSourceId(ticketEntity.getSourceId());
        ticketSLADTO.setSiteId(ticketSiteUserEntity.getSiteId());
        if (itemParamsEntity.getL3TicketTypeId() != null) {
            ticketSLADTO.setTicketTypeId(itemParamsEntity.getL3TicketTypeId());
        } else if (itemParamsEntity.getL2TicketTypeId() != null) {
            ticketSLADTO.setTicketTypeId(itemParamsEntity.getL2TicketTypeId());
        } else if (itemParamsEntity.getL1TicketTypeId() != null) {
            ticketSLADTO.setTicketTypeId(itemParamsEntity.getL1TicketTypeId());
        }
        ticketEntity.setL1TicketTypeId(itemParamsEntity.getL1TicketTypeId());
        ticketEntity.setL2TicketTypeId(itemParamsEntity.getL2TicketTypeId());
        ticketEntity.setL3TicketTypeId(itemParamsEntity.getL3TicketTypeId());
        ticketEntity.setStatus(ticketProcessEntity.getStatus());
        ticketEntity.setUpdateUser(ticketProcessEntity.getUpdateUser());
        ticketEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketRepositoryJPA.save(ticketEntity);

        /* Luu log */
        actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketEntity, ticketEntity, ticketEntity.getTicketId(), actionName);


        if (ticketProcessEntity.getStatus().equals(TicketStatusEntity.TicketStatus.CLOSE.value)
                || ticketProcessEntity.getStatus().equals(TicketStatusEntity.TicketStatus.CANCEL.value)) {
            Optional<TicketAssignEntity> ticketAssignEntity = ticketAssignRepositoryJPA.findByTicketId(ticketProcessEntity.getTicketId());
            if (ticketAssignEntity.isPresent()) {
                TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
                ticketAssignProcessEntity.setTicketAssignId(ticketAssignEntity.get().getTicketAssignId());
                ticketAssignProcessEntity.setTicketId(ticketAssignEntity.get().getTicketId());
                ticketAssignProcessEntity.setProcessTime(new Date(System.currentTimeMillis()));
                ticketAssignProcessEntity.setSiteId(ticketAssignEntity.get().getSiteId());
                ticketAssignProcessEntity.setStaffCode(ticketProcessEntity.getStaffCode());
                ticketAssignProcessEntity.setStaffName(ticketProcessEntity.getStaffName());
                ticketAssignProcessEntity.setCreateUser(itemParamsEntity.getCreateUser());
                ticketAssignProcessEntity.setCreateDate(new Date(System.currentTimeMillis()));
                ticketAssignEntity.get().setUserProcess(itemParamsEntity.getCreateUser());
                ticketAssignEntity.get().setUpdateUser(itemParamsEntity.getCreateUser());
                if (ticketAssignEntity.get().getTicketStatus().equals(TicketAssignEntity.TicketStatus.NEW.value)
                        || ticketAssignEntity.get().getTicketStatus().equals(TicketAssignEntity.TicketStatus.IN_PROGRESS.value)
                        || ticketAssignEntity.get().getTicketStatus().equals(TicketAssignEntity.TicketStatus.CONCLUDE.value)) {
                    ticketAssignEntity.get().setTicketStatus(TicketAssignEntity.TicketStatus.CANCEL.value);
                    ticketAssignProcessEntity.setStatus(TicketAssignEntity.TicketStatus.CANCEL.value);
                    ticketAssignProcessEntity.setProcessResult("CSKH hủy");
                    ticketAssignProcessEntity.setProcessContent("CSKH đã hủy phản ánh");
                    ticketAssignRepositoryJPA.save(ticketAssignEntity.get());
                    ticketAssignProcessRepositoryJPA.save(ticketAssignProcessEntity);
                } else if (ticketAssignEntity.get().getTicketStatus().equals(TicketAssignEntity.TicketStatus.FINISH.value)) {
                    ticketAssignEntity.get().setTicketStatus(TicketAssignEntity.TicketStatus.CLOSE.value);
                    ticketAssignProcessEntity.setStatus(TicketAssignEntity.TicketStatus.CLOSE.value);
                    ticketAssignProcessEntity.setProcessResult("CSKH đóng");
                    ticketAssignProcessEntity.setProcessContent("CSKH đã đóng phản ánh");
                    ticketAssignRepositoryJPA.save(ticketAssignEntity.get());
                    ticketAssignProcessRepositoryJPA.save(ticketAssignProcessEntity);
                }

                /* Luu log */
                actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), null, ticketAssignProcessEntity, ticketAssignProcessEntity.getTicketAssignProcessId(), actionName);
            }
        }
        return ticketProcessEntity;
    }

    /***
     * Thong tin xy ly phan anh
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    public Object getTicketProcessDetails(TicketProcessDTO itemParamsEntity, Authentication authentication) {
        ResultSelectEntity resultSelectEntity = ticketProcessRepository.getTicketProcessInfo(itemParamsEntity);
        List<TicketProcessDTO> list = (List<TicketProcessDTO>) resultSelectEntity.getListData();

        List<TicketAttachmentEntity> ticketAttachmentEntities = ticketAttachmentServiceJPA.getTicketAttachment(itemParamsEntity.getTicketId(),
                TicketAttachmentEntity.Type.TICKET_PROCESS.value);
        list.get(0).setListFiles(ticketAttachmentEntities);
        resultSelectEntity.setListData(list);
        return resultSelectEntity;
    }
}
