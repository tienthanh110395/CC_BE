package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketExpireCauseDTO;
import com.viettel.etc.dto.TicketExpireCauseNewDTO;
import com.viettel.etc.dto.TicketExpireCauseSearchDTO;
import com.viettel.etc.repositories.TicketExpireCauseRepository;
import com.viettel.etc.repositories.tables.TicketExpireCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketExpireCauseService;
import com.viettel.etc.services.tables.ActionAuditServiceJPA;
import com.viettel.etc.services.tables.TicketExpireCauseServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Lop thao danh muc nguyen nhan qua han
 *
 * @author ToolGen
 * @date Thu Jun 03 11:31:38 ICT 2021
 */
@Service
public class TicketExpireCauseServiceImpl implements TicketExpireCauseService {

    private static final Logger log = Logger.getLogger(TicketExpireCauseServiceImpl.class);

    @Autowired
    TicketExpireCauseRepositoryJPA ticketExpireCauseRepositoryJPA;

    @Autowired
    TicketExpireCauseRepository ticketExpireCauseRepository;

    @Autowired
    TicketExpireCauseServiceJPA ticketExpireCauseServiceJPA;

    @Autowired
    ActionAuditServiceJPA actionAuditServiceJPA;


    @Autowired
    TicketServiceJPA ticketServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    /**
     * Danh muc nguyen nhan qua han
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketExpireCause(TicketExpireCauseDTO itemParamsEntity) {
        return ticketExpireCauseRepositoryJPA.findAllByParentIdAndStatusOrderByName(itemParamsEntity.getParentId(), TicketExpireCauseEntity.STATUS.VALID.value);
    }

    /***
     * Tim kiem danh mục nguyên nhân quá hạn
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object searchTicketExpireCause(TicketExpireCauseDTO itemParamsEntity, Authentication authentication) {
        return ticketExpireCauseRepository.searchTicketExpireCause(itemParamsEntity);
    }

    /***
     * Tim kiem tree danh mục nguyên nhân quá hạn
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object searchTreeTicketExpireCause(TicketExpireCauseDTO itemParamsEntity, Authentication authentication) {
        return ticketExpireCauseRepository.searchTreeTicketExpireCause(itemParamsEntity);
    }

    /**
     * Tạo mới danh mục nguyên nhân quá hạn
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @Override
    public Object createTicketExpireCause(TicketExpireCauseDTO dataParams, Authentication authentication) {
        TicketExpireCauseEntity ticketExpireCauseEntity = new TicketExpireCauseEntity();
        if (dataParams.getParentId() == null) {
            ticketExpireCauseEntity.setParentId(null);
        } else {
            ticketExpireCauseEntity.setParentId(dataParams.getParentId());
        }
        ticketExpireCauseEntity.setCode(StringUtils.trim(dataParams.getCode()));
        ticketExpireCauseEntity.setName(StringUtils.trim(dataParams.getName()));
        ticketExpireCauseEntity.setStatus(dataParams.getStatus());
        ticketExpireCauseEntity.setDescription(StringUtils.trim(dataParams.getDescription()));
        ticketExpireCauseEntity.setCreateDate(new Date(System.currentTimeMillis()));
        ticketExpireCauseEntity.setCreateUser(FnCommon.getUserLogin(authentication));
        TicketExpireCauseEntity saveTicketExpireCause = ticketExpireCauseServiceJPA.save(ticketExpireCauseEntity);
        return saveTicketExpireCause;
    }

    /**
     * Sua danh muc nguyên nhân quá hạn
     *
     * @param dataParams          params client
     * @param ticketExpireCauseId
     * @param authentication      : thong tin nguoi dung
     * @return
     */
    @Override
    public Object updateTicketExpireCause(TicketExpireCauseDTO dataParams, Long ticketExpireCauseId, Authentication authentication) throws EtcException {
        TicketExpireCauseEntity ticketExpire = ticketExpireCauseServiceJPA.getOne(ticketExpireCauseId);
        if (ticketExpire == null) {
            throw new EtcException("Không có dữ liệu thỏa mãn");
        }
        if (dataParams.getParentId() == null) {
            ticketExpire.setParentId(null);
        } else {
            ticketExpire.setParentId(dataParams.getParentId());
        }
        ticketExpire.setCode(StringUtils.trim(dataParams.getCode()));
        ticketExpire.setName(StringUtils.trim(dataParams.getName()));
        ticketExpire.setStatus(dataParams.getStatus());
        ticketExpire.setDescription(StringUtils.trim(dataParams.getDescription()));
        ticketExpire.setCreateDate(new Date(System.currentTimeMillis()));
        ticketExpire.setCreateUser(FnCommon.getUserLogin(authentication));
        TicketExpireCauseEntity saveTicketExpireCause = ticketExpireCauseServiceJPA.save(ticketExpire);
        return saveTicketExpireCause;
    }

    /***
     * Xóa 1 bản ghi danh mục nguyên nhân quá hạn
     * @param ticketExpireCauseId
     * @return
     */
    @Override
    @Transactional
    public Object removeTicketExpireCause(Long ticketExpireCauseId, Authentication authentication) {
        log.info("TicketExpireCauseServiceImpl start onDelete ");
        try {
            TicketExpireCauseEntity ticketExpireCause = ticketExpireCauseServiceJPA.findById(ticketExpireCauseId).get();
            List<TicketEntity> lstTicket = ticketServiceJPA.findByL3TicketTypeId(ticketExpireCauseId);
            if (lstTicket.size() > 0) {
                return 1L;
            }
            ticketExpireCauseRepositoryJPA.deleteById(ticketExpireCauseId);

            return true;
        } catch (Exception ex) {
            log.error("Has error TicketExpireCauseServiceImpl onDelete   ", ex);
        }
        return false;
    }

    @Override
    public Object getListDataExpireCause(Authentication authentication, TicketExpireCauseSearchDTO params) {

        log.info("TicketCateConfigServiceImpl start search ticket type ");
        return ticketExpireCauseRepository.getListExpireCause(authentication, params);
    }

    @Override
    public Object getExpireLevelByParentId(TicketExpireCauseDTO dataParams, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start getTicketTypeByParentId");
        return ticketExpireCauseRepository.getExpireLevelByParent(dataParams);
    }

    @Override
    public Object createOrUpdate(TicketExpireCauseNewDTO params, Authentication authentication) {
        log.info("TicketExpireCauseServiceImpl start createOrUpdateUpdate");
        try {
            TicketExpireCauseEntity ticketType = ticketExpireCauseServiceJPA.findByCodeAndLevelExpire(params.getExpireCauseCode(), params.getLevelExpire());
            TicketExpireCauseEntity entity = new TicketExpireCauseEntity();

            String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
            TicketExpireCauseEntity oldTicketTicketExpireCauseEntity = null;
            // Nếu là cập nhật
            if (params.getTicketExpireCauseId() != null) {
                TicketExpireCauseEntity exist = ticketExpireCauseServiceJPA.findByCodeAndTicketTypeIdNot(params.getExpireCauseCode(), params.getTicketExpireCauseId());
                if (exist != null) {
                    return 1L;
                } else {
                    // update
                    Optional<TicketExpireCauseEntity> optional = ticketExpireCauseServiceJPA.findById(params.getTicketExpireCauseId());
                    if (optional.isPresent()) {
                        entity = optional.get();
                        oldTicketTicketExpireCauseEntity = (TicketExpireCauseEntity) entity.clone();
                        actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                        entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                        entity.setUpdateDate(new Date(System.currentTimeMillis()));
                    }
                }
            }

            if (params.getTicketExpireCauseId() == null) {
                if (ticketExpireCauseRepositoryJPA.existsByCode(entity.getCode())) {
                    return 1L;
                } else {
                entity.setCreateUser(FnCommon.getUserLogin(authentication));
                entity.setCreateDate(new Date(System.currentTimeMillis()));
                entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                entity.setUpdateDate(new Date(System.currentTimeMillis()));
                entity.setCode(" ");
//                entity.setCode(String.valueOf((ticketExpireCauseServiceJPA.getTopupSequenceNo())));
                }
            }

            entity.setName(params.getExpireCauseName());
            entity.setDescription(params.getDescription());

            if (params.getTicketExpireLevelOne() != null) {
                entity.setParentId(params.getTicketExpireLevelOne());
            }

            if (params.getTicketExpireLevelTwo() != null) {
                entity.setParentId(params.getTicketExpireLevelTwo());
            }
            entity.setStatus(params.getStatus());
            entity.setLevelExpire(params.getLevelExpire());

            TicketExpireCauseEntity entitylog = ticketExpireCauseServiceJPA.save(entity);
            entitylog.setCode(entitylog.getTicketExpireCauseId().toString());
            ticketExpireCauseServiceJPA.saveAndFlush(entitylog);

            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setTicketStatus(entitylog.getStatus());
            actionAuditDTO.setCreateDate(entitylog.getCreateDate());
            actionAuditDTO.setTicketAssignId(entitylog.getTicketExpireCauseId());
            if (params.getLevelExpire() == TicketExpireCauseEntity.ExpireCauseLevel.Level_1.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                actionAuditDTO.setDescription(entitylog.getName());
            }  else if(params.getLevelExpire()==TicketExpireCauseEntity.ExpireCauseLevel.Level_2.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                TicketExpireCauseEntity findNameLv2 = ticketExpireCauseServiceJPA.findById(params.getTicketExpireLevelOne()).get();
                actionAuditDTO.setDescription(findNameLv2.getName() + '/' + entitylog.getName());
            }  else if(params.getLevelExpire()==TicketExpireCauseEntity.ExpireCauseLevel.Level_3.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                TicketExpireCauseEntity findNameLv3 = ticketExpireCauseServiceJPA.findById(Long.valueOf(params.getTicketExpireLevelTwo())).get();
                actionAuditDTO.setDescription(findNameLv3.getName() + '/' + entitylog.getName());
            }
            actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketTicketExpireCauseEntity, entitylog, entitylog.getTicketExpireCauseId(), actionType);
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketCateConfigServiceImpl createOrUpdate", ex);
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean doDeleteData(Long ticketExpireCauseId, Authentication authentication) {
        log.info("TicketExpireCauseServiceImpl start onDelete ");
        try {
            TicketExpireCauseEntity ticketExpireCause = ticketExpireCauseServiceJPA.getOne(ticketExpireCauseId);
            if (ticketExpireCause.getTicketExpireCauseId() != null) {
                ticketExpireCauseServiceJPA.doDelete(ticketExpireCauseId);

                /* Luu log */
                String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
                ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                actionAuditDTO.setTicketStatus(ticketExpireCause.getStatus());
                actionAuditDTO.setCreateDate(ticketExpireCause.getCreateDate());
                actionAuditDTO.setTicketAssignId(ticketExpireCause.getTicketExpireCauseId());
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), ticketExpireCause, null, ticketExpireCauseId, actionType);

                return true;
            }
        } catch (Exception ex) {
            log.error("Has error TicketExpireCauseServiceImpl onDelete", ex);
        }
        return false;
    }

    @Override
    public Object getDataDetail(Long ticketExpireCauseId, TicketExpireCauseNewDTO params) {
        log.info("TicketExpireCauseServiceImpl start getDataDetail ");
        return ticketExpireCauseRepository.getDataDetailById(ticketExpireCauseId, params);
    }

    @Override
    public Object changeStatus(TicketExpireCauseNewDTO params, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start change status ");
        try {

            TicketExpireCauseEntity ticketExpireCause = ticketExpireCauseServiceJPA.findById(params.getTicketExpireCauseId()).get();
            TicketExpireCauseEntity oldEntity = (TicketExpireCauseEntity) ticketExpireCause.clone();
            ticketExpireCause.setUpdateUser(FnCommon.getUserLogin(authentication));
            ticketExpireCause.setUpdateDate(new Date(System.currentTimeMillis()));

            if (TicketExpireCauseEntity.STATUS.INVALID.value.equals(params.getStatus())) {
                ticketExpireCause.setStatus(TicketExpireCauseEntity.STATUS.VALID.value);

            } else {
                ticketExpireCause.setStatus(TicketExpireCauseEntity.STATUS.INVALID.value);
            }
            ticketExpireCauseServiceJPA.save(ticketExpireCause);

            /* Luu log */
            String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
            ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
            actionAuditEntity.setStatus(ticketExpireCause.getStatus());
            actionAuditEntity.setCreateDate(ticketExpireCause.getCreateDate());
            actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
            actionAuditEntity.setIpPc("localhost");
            TicketExpireCauseEntity levelEntity = ticketExpireCauseServiceJPA.findById(Long.valueOf(params.getTicketExpireCauseId())).get();
            if (levelEntity.getLevelExpire() == 1) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                actionAuditEntity.setDescription(oldEntity.getName());
            } else if (levelEntity.getLevelExpire() == 2) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                TicketExpireCauseEntity findNameLv2 = ticketExpireCauseServiceJPA.findById(Long.valueOf(levelEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
            } else if (levelEntity.getLevelExpire() == 3) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                TicketExpireCauseEntity findNameLv3 = ticketExpireCauseServiceJPA.findById(Long.valueOf(levelEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
            }
            ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
            actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketExpireCause, ticketExpireCause.getTicketExpireCauseId(), actionType);

            return true;
        } catch (Exception ex) {
            log.info("Has error TicketExpireCauseServiceImpl change status", ex);
        }
        return false;
    }

    @Override
    @Transactional
    public Object updateStatusMultiple(TicketExpireCauseNewDTO params, Authentication authentication) {
        log.info("TicketExpireCauseServiceImpl start update status multiple ");
        String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

        try {
            if (!params.getLstIdsActive().isEmpty()) {
                List<TicketExpireCauseEntity> ticketTypes = ticketExpireCauseServiceJPA.findAllById(params.getLstIdsActive());
                for (TicketExpireCauseEntity ticketType: ticketTypes){
                    TicketExpireCauseEntity oldEntity = (TicketExpireCauseEntity) ticketType.clone();
                    ticketType.setStatus(TicketExpireCauseEntity.STATUS.INVALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketExpireCauseServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelExpire() == 1) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                        actionAuditEntity.setDescription(oldEntity.getName());
                    } else if (ticketType.getLevelExpire() == 2) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                        TicketExpireCauseEntity findNameLv2 = ticketExpireCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
                    } else if (ticketType.getLevelExpire() == 3) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                        TicketExpireCauseEntity findNameLv3 = ticketExpireCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getTicketExpireCauseId(), actionType);
                }
            }
            if (!params.getLstIdsInactive().isEmpty()) {
                List<TicketExpireCauseEntity> ticketTypes = ticketExpireCauseServiceJPA.findAllById(params.getLstIdsInactive());
                for (TicketExpireCauseEntity ticketType: ticketTypes){
                    TicketExpireCauseEntity oldEntity = (TicketExpireCauseEntity) ticketType.clone();
                    ticketType.setStatus(TicketExpireCauseEntity.STATUS.VALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketExpireCauseServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelExpire() == 1) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                        actionAuditEntity.setDescription(oldEntity.getName());
                    } else if (ticketType.getLevelExpire() == 2) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                        TicketExpireCauseEntity findNameLv2 = ticketExpireCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
                    } else if (ticketType.getLevelExpire() == 3) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_CAUSE.value);
                        TicketExpireCauseEntity findNameLv3 = ticketExpireCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getTicketExpireCauseId(), actionType);
                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketCateConfigServiceImpl update status multiple", ex);
        }
        return false;
    }
}
