package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketLevelCateDTO;
import com.viettel.etc.dto.TicketLevelCateSearchDTO;
import com.viettel.etc.repositories.TicketLevelCateRepository;
import com.viettel.etc.repositories.tables.TicketLevelCateRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketLevelCateService;
import com.viettel.etc.services.tables.ActionAuditServiceJPA;
import com.viettel.etc.services.tables.TicketLevelCateServiceJPA;
//import com.viettel.etc.services.tables.TicketTypeLogServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import io.minio.errors.ErrorResponseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class TicketLevelCateServiceImpl implements TicketLevelCateService {
    private static final Logger log = Logger.getLogger(TicketCateConfigServiceImpl.class);

    @Autowired
    TicketLevelCateRepository ticketLevelCateRepository;

    @Autowired
    TicketLevelCateRepositoryJPA ticketLevelCateRepositoryJPA;

    @Autowired
    TicketLevelCateServiceJPA ticketLevelCateServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    ActionAuditServiceJPA actionAuditServiceJPA;

    @Override
    @Transactional
    public boolean changeStatus(TicketLevelCateDTO params, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start changeStatus ");
        try {
            OtherCategoriesEntity otherCategories = ticketLevelCateServiceJPA.findById(params.getTicketLevelCateId()).get();
            OtherCategoriesEntity oldEntity = (OtherCategoriesEntity) otherCategories.clone();
            otherCategories.setUpdateUser(FnCommon.getUserLogin(authentication));
            otherCategories.setUpdateDate(new Date(System.currentTimeMillis()));
            if (OtherCategoriesEntity.IsActive.INVALID.value.equals(params.getStatus())) {
//                ticketLevelCateServiceJPA.updateStatus(params.getTicketLevelCateId(), OtherCategoriesEntity.IsActive.VALID.value);
                otherCategories.setIsActive(OtherCategoriesEntity.IsActive.VALID.value);
            } else {
//                ticketLevelCateServiceJPA.updateStatus(params.getTicketLevelCateId(), OtherCategoriesEntity.IsActive.INVALID.value);
                otherCategories.setIsActive(OtherCategoriesEntity.IsActive.INVALID.value);
            }
            ticketLevelCateServiceJPA.save(otherCategories);

            /* Luu log */
            String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

            ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
            actionAuditEntity.setStatus(otherCategories.getIsActive());
            actionAuditEntity.setDescription(oldEntity.getName());
            actionAuditEntity.setCreateDate(otherCategories.getCreateDate());
            actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
            actionAuditEntity.setIpPc("localhost");
            actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_PRIORITIES.value);
            ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
            actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, otherCategories, otherCategories.getId(), actionType);

            return true;
        } catch (Exception ex) {
            log.info("Has error TicketCateConfigServiceImpl changeStatus", ex);
        }
        return false;
    }

    @Override
    public Object createUpdate(TicketLevelCateDTO params, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start createOrUpdate");
        try {
//            TicketTypeEntity ticketType = ticketTypeServiceJPA.findByCodeAndLevelTt(params.getTicketTypeCode(), params.getLevelTt());
            OtherCategoriesEntity entity = new OtherCategoriesEntity();
            String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
            OtherCategoriesEntity oldTicketLevelCateEntity = null;

            // Nếu là cập nhật
            if (params.getTicketLevelCateId() != null) {
                OtherCategoriesEntity exist = ticketLevelCateServiceJPA.findByCodeAndTypeAndIdNot(params.getTicketLevelCateCode(), params.getType(), params.getTicketLevelCateId());
                if (exist != null) {
                    throw new EtcException("ticket.category.duplicate.ticket.code");
                } else {
                    // update
                    Optional<OtherCategoriesEntity> optional = ticketLevelCateRepositoryJPA.findById(params.getTicketLevelCateId());
                    if (optional.isPresent()) {
                        entity = optional.get();
                        oldTicketLevelCateEntity = (OtherCategoriesEntity) entity.clone();
                        actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                        entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                        entity.setUpdateDate(new Date(System.currentTimeMillis()));
                    }
                }
            }
            if (params.getTicketLevelCateId() == null) {
                entity.setCreateUser(FnCommon.getUserLogin(authentication));
                entity.setCreateDate(new Date(System.currentTimeMillis()));
                entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                entity.setUpdateDate(new Date(System.currentTimeMillis()));
                entity.setCode(" ");
                entity.setIsActive(params.getStatus());
                entity.setType(params.getType());
            }
            entity.setName(params.getTicketLevelCateName());
            entity.setDescription(params.getDescription());
            if (params.getTicketLevelCateId() != null) {
                entity.setId(params.getTicketLevelCateId());
            }
            OtherCategoriesEntity entitylog = ticketLevelCateRepositoryJPA.save(entity);
            entitylog.setCode(entitylog.getId().toString());
            ticketLevelCateServiceJPA.saveAndFlush(entitylog);


            /* Luu log */
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setDescription(entitylog.getName());
            actionAuditDTO.setTicketStatus(entitylog.getIsActive());
            actionAuditDTO.setCreateDate(entitylog.getCreateDate());
            actionAuditDTO.setTicketAssignId(entitylog.getId());
            actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_PRIORITIES.value);
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketLevelCateEntity, entitylog, entitylog.getId(), actionType);

            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            log.error("Has error TicketLevelCateServiceImpl createOrUpdate", ex);
        }
        return false;
    }

    @Override
    public Boolean onDelete(Long ticketLevelCateId, Authentication authentication) {
        log.info("TicketLevelCateServiceImpl start onDelete ");
        try {
            OtherCategoriesEntity ticketLevelCate = ticketLevelCateServiceJPA.getOne(ticketLevelCateId);
            if (ticketLevelCate.getId() != null) {
                ticketLevelCateServiceJPA.deleteById(ticketLevelCateId);

                /* Luu log */
                String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
                ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                actionAuditDTO.setTicketStatus(ticketLevelCate.getIsActive());
                actionAuditDTO.setCreateDate(ticketLevelCate.getCreateDate());
                actionAuditDTO.setTicketAssignId(ticketLevelCate.getId());
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_PRIORITIES.value);
                ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), ticketLevelCate, null, ticketLevelCateId, actionType);

                return true;
            }
        } catch (Exception ex) {
            log.error("Has error TicketLevelCateServiceImpl onDelete", ex);
        }
        return false;
    }

    @Override
    public Object getDataDetail(Long ticketLevelCateId, TicketLevelCateDTO params) {
        log.info("TicketLevelCateServiceImpl start getDataDetail ");
        return ticketLevelCateRepository.getDataDetail(ticketLevelCateId, params);
    }


    @Override
    public Object createUpdateList(List<TicketLevelCateDTO> params, Authentication authentication) throws Exception {
        List<OtherCategoriesEntity> dataList = new ArrayList<>();
        String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
        OtherCategoriesEntity oldTicketLevelCateEntity = null;
        for (TicketLevelCateDTO dto : params) {

            OtherCategoriesEntity item = new OtherCategoriesEntity();

            // Nhóm theo Code
            Map<String, List<TicketLevelCateDTO>> groupByCode =

                    params.stream().collect(Collectors.groupingBy(TicketLevelCateDTO::getTicketLevelCateCode));

            // Sử dụng vòng lặp for để lặp qua các List<TicketCateConfigDTO>
            for (Map.Entry<String, List<TicketLevelCateDTO>> entry : groupByCode.entrySet()) {

                List<TicketLevelCateDTO> dtoList = entry.getValue();

                if (dtoList.size() > 1) {

                    throw new SQLIntegrityConstraintViolationException();
                }
            }

            if (FnCommon.isNullOrEmpty(dto.getTicketLevelCateCode()) || FnCommon.isNullOrEmpty(dto.getTicketLevelCateName())) {

                throw new Exception();
            } else if (ticketLevelCateRepositoryJPA.existsByCode(dto.getTicketLevelCateCode())) {
                // throw exception
                throw new SQLIntegrityConstraintViolationException();
            } else {
                item.setName(dto.getTicketLevelCateName());
                item.setCode(dto.getTicketLevelCateCode());
                item.setCreateUser(FnCommon.getUserLogin(authentication));
                item.setCreateDate(new Date(System.currentTimeMillis()));
                item.setUpdateUser(FnCommon.getUserLogin(authentication));
                item.setUpdateDate(new Date(System.currentTimeMillis()));
                item.setType(dto.getType());
                item.setDescription(dto.getDescription());
                item.setIsActive(dto.getStatus());
                item.setId(dto.getTicketLevelCateId());
                dataList.add(item);
            }

        }
        List<OtherCategoriesEntity> entitylogs = ticketLevelCateServiceJPA.saveAll(dataList);
        for (OtherCategoriesEntity entitylog : entitylogs) {
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setTicketStatus(entitylog.getIsActive());
            actionAuditDTO.setCreateDate(entitylog.getCreateDate());
            actionAuditDTO.setTicketAssignId(entitylog.getId());
            actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_PRIORITIES.value);
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketLevelCateEntity, entitylog, entitylog.getId(), actionType);
        }
        return true;
    }

    @Override
    public Object searchTicketLevelCate(Authentication authentication, TicketLevelCateSearchDTO params) {
        log.info("TicketCateConfigServiceImpl start search ticket type ");
        return ticketLevelCateRepository.searchTicketLevelCate(authentication, params);
    }

    @Override
    public Object getListOtherCateConfigProcessTime(Authentication authentication) {
        log.info("TicketLevelCateServiceImpl start getTicketTypeByParentId");
        return ticketLevelCateRepository.getTicketProcessTime(authentication);
    }

    @Override
    public Object getTicketLevelCateNameById(TicketLevelCateDTO params, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start getTicketTypeByParentId");
        return ticketLevelCateRepository.getTicketLevelCateNameById(params);
    }
}
