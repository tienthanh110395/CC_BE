package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketErrorCauseDTO;
import com.viettel.etc.dto.TicketErrorCauseNewDTO;
import com.viettel.etc.dto.TicketErrorCauseSearchDTO;
import com.viettel.etc.repositories.TicketErrorCauseRepository;
import com.viettel.etc.repositories.tables.TicketErrorCauseRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketErrorCauseService;
import com.viettel.etc.services.tables.ActionAuditServiceJPA;
import com.viettel.etc.services.tables.TicketErrorCauseServiceJPA;
//import com.viettel.etc.services.tables.TicketTypeLogServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Autogen class: Lop thao danh muc nguyen nhan loi
 *
 * @author ToolGen
 * @date Thu Jun 03 13:45:57 ICT 2021
 */
@Service
public class TicketErrorCauseServiceImpl implements TicketErrorCauseService {

    private static final Logger log = Logger.getLogger(TicketErrorCauseServiceImpl.class);

    @Autowired
    TicketErrorCauseRepositoryJPA ticketErrorCauseRepositoryJPA;

    @Autowired
    TicketErrorCauseRepository ticketErrorCauseRepository;

    @Autowired
    TicketErrorCauseServiceJPA ticketErrorCauseServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    ActionAuditServiceJPA actionAuditServiceJPA;

    /**
     * Danh muc nguyen nhan loi
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketErrorCause(TicketErrorCauseDTO itemParamsEntity) {
        return ticketErrorCauseRepositoryJPA.findAllByParentIdAndStatusOrderByName(itemParamsEntity.getParentId(), TicketErrorCauseEntity.STATUS.VALID.value);
    }

    /***
     * Tim kiem danh mục nguyên nhân lỗi
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object searchTicketErrorCause(TicketErrorCauseDTO itemParamsEntity, Authentication authentication) {
        return ticketErrorCauseRepository.searchTicketErrorCause(itemParamsEntity);
    }

    /***
     * Tim kiem tree danh mục nguyên nhân lỗi
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object searchTreeTicketErrorCause(TicketErrorCauseDTO itemParamsEntity, Authentication authentication) {
        return ticketErrorCauseRepository.searchTreeTicketErrorCause(itemParamsEntity);
    }


    /**
     * Sua danh muc nguyên nhân lỗi
     *
     * @param dataParams         params client
     * @param ticketErrorCauseId
     * @param authentication     : thong tin nguoi dung
     * @return
     */
    @Override
    public Object updateTicketErrorCause(TicketErrorCauseDTO dataParams, Long ticketErrorCauseId, Authentication authentication) throws EtcException {
        TicketErrorCauseEntity ticketErrorCause = ticketErrorCauseServiceJPA.getOne(ticketErrorCauseId);
        if (ticketErrorCause == null) {
            throw new EtcException("Không có dữ liệu thỏa mãn");
        }
        if (dataParams.getParentId() == null) {
            ticketErrorCause.setParentId(null);
        } else {
            ticketErrorCause.setParentId(dataParams.getParentId());
        }
        ticketErrorCause.setCode(StringUtils.trim(dataParams.getCode()));
        ticketErrorCause.setName(StringUtils.trim(dataParams.getName()));
        ticketErrorCause.setStatus(dataParams.getStatus());
        ticketErrorCause.setDescription(StringUtils.trim(dataParams.getDescription()));
        ticketErrorCause.setCreateDate(new Date(System.currentTimeMillis()));
        ticketErrorCause.setCreateUser(FnCommon.getUserLogin(authentication));
        TicketErrorCauseEntity saveTicketErrorCause = ticketErrorCauseServiceJPA.save(ticketErrorCause);
        return saveTicketErrorCause;
    }

    /***
     * Xóa 1 bản ghi danh mục nguyên nhân lỗi
     * @param ticketErrorCauseId
     * @return
     */
    @Override
    public Boolean removeTicketErrorCause(Long ticketErrorCauseId, Authentication authentication) {
        ticketErrorCauseRepositoryJPA.deleteById(ticketErrorCauseId);
        return true;
    }

    @Override
    public Object createTicketErrorCause(TicketErrorCauseDTO dataParams, Authentication authentication) {
        return null;
    }

    @Override
    public Object getTicketErrorCauseById(TicketErrorCauseNewDTO params, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start getTicketErrorCauseById");
        return ticketErrorCauseRepository.getTicketErrorCauseByParent(params);
    }

    @Override
    public Object searchTicketErrorCauseNew(Authentication authentication, TicketErrorCauseSearchDTO params) {
        log.info("TicketErrorCauseServiceImpl start search ticket error cause");
        return ticketErrorCauseRepository.searchTicketErrorCauseNew(authentication, params);
    }

    @Override
    @Transactional
    public Boolean onDelete(Long ticketErrorCauseId, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start onDelete ");
        try {
            TicketErrorCauseEntity ticketErrorCause = ticketErrorCauseServiceJPA.getOne(ticketErrorCauseId);
            if (ticketErrorCause.getTicketErrorCauseId() != null) {
                ticketErrorCauseServiceJPA.doDelete(ticketErrorCauseId);

                String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
                ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                actionAuditDTO.setTicketStatus(ticketErrorCause.getStatus());
                actionAuditDTO.setCreateDate(ticketErrorCause.getCreateDate());
                actionAuditDTO.setTicketAssignId(ticketErrorCause.getTicketErrorCauseId());
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), ticketErrorCause, null, ticketErrorCauseId, actionType);
                return true;
            }
        } catch (Exception ex) {
            log.error("Has error TicketErrorCauseServiceImpl onDelete", ex);
        }
        return false;
    }

    /**
     * Tạo mới danh mục nguyên nhân lỗi
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @return
     */
    @Override
    public Object createTicketErrorCauseNew(TicketErrorCauseNewDTO dataParams, Authentication authentication) {

        log.info("TicketErrorCauseServiceImpl start createTicketErrorCauseNew");
        try {
            TicketErrorCauseEntity entity = new TicketErrorCauseEntity();

            String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
            TicketErrorCauseEntity oldTicketTicketErrorCauseEntity = null;

            // Nếu là cập nhật
            if (dataParams.getTicketErrorCauseId() != null) {
                TicketErrorCauseEntity exist = ticketErrorCauseServiceJPA.findByCodeAndTicketErrorCauseIdNot(dataParams.getCode(), dataParams.getTicketErrorCauseId());
                if (exist != null) {
                    return 1L;
                } else {
                    Optional<TicketErrorCauseEntity> optional = ticketErrorCauseServiceJPA.findById(dataParams.getTicketErrorCauseId());
                    if (optional.isPresent()) {
                        entity = optional.get();
                        oldTicketTicketErrorCauseEntity = (TicketErrorCauseEntity) entity.clone();
                        actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                        entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                        entity.setUpdateDate(new Date(System.currentTimeMillis()));
                    }
                }
            }

            if (dataParams.getTicketErrorCauseId() == null) {
                if (ticketErrorCauseRepositoryJPA.existsByCode(dataParams.getCode())) {
                    return 1L;
                } else {

                    entity.setCreateUser(FnCommon.getUserLogin(authentication));
                    entity.setCreateDate(new Date(System.currentTimeMillis()));
                    entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                    entity.setUpdateDate(new Date(System.currentTimeMillis()));
//                    entity.setCode(String.valueOf((ticketErrorCauseServiceJPA.getTopupSequenceNo())));
                    entity.setCode(" ");
                }

            }

            entity.setName(dataParams.getName());
            entity.setDescription(dataParams.getDescription());

            if (dataParams.getTicketErrorLevelOne() != null) {
                entity.setParentId(dataParams.getTicketErrorLevelOne());
            }

            if (dataParams.getTicketErrorLevelTwo() != null) {
                entity.setParentId(dataParams.getTicketErrorLevelTwo());
            }

            entity.setStatus(dataParams.getStatus());
            entity.setLevelError(dataParams.getLevelError());

            TicketErrorCauseEntity entitylog = ticketErrorCauseServiceJPA.save(entity);
            entitylog.setCode(entitylog.getTicketErrorCauseId().toString());
            ticketErrorCauseServiceJPA.saveAndFlush(entitylog);
            /* lưu log */
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setTicketStatus(entitylog.getStatus());
            actionAuditDTO.setCreateDate(entitylog.getCreateDate());
            actionAuditDTO.setTicketAssignId(entitylog.getTicketErrorCauseId());
            if (dataParams.getLevelError() == TicketErrorCauseEntity.ErrorCauseLevel.Level_1.value) {
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                actionAuditDTO.setDescription(entitylog.getName());
            } else if (dataParams.getLevelError() == TicketErrorCauseEntity.ErrorCauseLevel.Level_2.value) {
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                TicketErrorCauseEntity findNameLv2 = ticketErrorCauseServiceJPA.findById(dataParams.getTicketErrorLevelOne()).get();
                actionAuditDTO.setDescription(findNameLv2.getName() + '/' + entitylog.getName());
            } else if (dataParams.getLevelError() == TicketErrorCauseEntity.ErrorCauseLevel.Level_3.value) {
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                TicketErrorCauseEntity findNameLv3 = ticketErrorCauseServiceJPA.findById(Long.valueOf(dataParams.getTicketErrorLevelTwo())).get();
                actionAuditDTO.setDescription(findNameLv3.getName() + '/' + entitylog.getName());
            }
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketTicketErrorCauseEntity, entitylog, entitylog.getTicketErrorCauseId(), actionType);
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketErrorCauseServiceImpl createTicketErrorCauseNew", ex);
        }
        return false;
    }


    @Override
    @Transactional
    public boolean changeStatusTicketErrorCause(TicketErrorCauseNewDTO dataParams, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start changeStatusTicketErrorCause ");
        try {

            TicketErrorCauseEntity ticketErrorCause = ticketErrorCauseServiceJPA.findById(dataParams.getTicketErrorCauseId()).get();
            TicketErrorCauseEntity oldEntity = (TicketErrorCauseEntity) ticketErrorCause.clone();
            ticketErrorCause.setUpdateUser(FnCommon.getUserLogin(authentication));
            ticketErrorCause.setUpdateDate(new Date(System.currentTimeMillis()));
            if (TicketErrorCauseEntity.STATUS.INVALID.value.equals(dataParams.getStatus())) {
                ticketErrorCause.setStatus(TicketErrorCauseEntity.STATUS.VALID.value);

            } else {
                ticketErrorCause.setStatus(TicketErrorCauseEntity.STATUS.INVALID.value);
            }
            TicketErrorCauseEntity entitylogStatus = ticketErrorCauseServiceJPA.save(ticketErrorCause);

            /* Luu log */
            String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

            ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
//            actionAuditEntity.setDescription(oldEntity.getName());
            actionAuditEntity.setStatus(ticketErrorCause.getStatus());
            actionAuditEntity.setCreateDate(ticketErrorCause.getCreateDate());
            actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
            actionAuditEntity.setIpPc("localhost");
            TicketErrorCauseEntity levelErrorEntity = ticketErrorCauseServiceJPA.findById(Long.valueOf(dataParams.getTicketErrorCauseId())).get();
            if (levelErrorEntity.getLevelError() == 1) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                actionAuditEntity.setDescription(oldEntity.getName());
            } else if (levelErrorEntity.getLevelError() == 2) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                TicketErrorCauseEntity findNameLv2 = ticketErrorCauseServiceJPA.findById(Long.valueOf(levelErrorEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
            } else if (levelErrorEntity.getLevelError() == 3) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                TicketErrorCauseEntity findNameLv3 = ticketErrorCauseServiceJPA.findById(Long.valueOf(levelErrorEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
            }
            ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
            actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketErrorCause, ticketErrorCause.getTicketErrorCauseId(), actionType);
            return true;
        } catch (Exception ex) {
            log.info("Has error TicketErrorCauseServiceImpl change status", ex);
        }
        return false;
    }

    @Override
    public boolean changeStatusMultipleTicketErrorCause(TicketErrorCauseNewDTO params, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start updateStatusMultipleTicketErrorCause ");
        String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
        try {
            if (!params.getLstIdsActive().isEmpty()) {
                List<TicketErrorCauseEntity> ticketTypes = ticketErrorCauseServiceJPA.findAllById(params.getLstIdsActive());
                for (TicketErrorCauseEntity ticketType : ticketTypes) {
                    TicketErrorCauseEntity oldEntity = (TicketErrorCauseEntity) ticketType.clone();
                    ticketType.setStatus(TicketErrorCauseEntity.STATUS.INVALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketErrorCauseServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelError() == 1) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                        actionAuditEntity.setDescription(oldEntity.getName());
                    } else if (ticketType.getLevelError() == 2) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                        TicketErrorCauseEntity findNameLv2 = ticketErrorCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
                    } else if (ticketType.getLevelError() == 3) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                        TicketErrorCauseEntity findNameLv3 = ticketErrorCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getTicketErrorCauseId(), actionType);
                }
            }
            if (!params.getLstIdsInactive().isEmpty()) {
                List<TicketErrorCauseEntity> ticketTypes = ticketErrorCauseServiceJPA.findAllById(params.getLstIdsInactive());
                for (TicketErrorCauseEntity ticketType : ticketTypes) {
                    TicketErrorCauseEntity oldEntity = (TicketErrorCauseEntity) ticketType.clone();
                    ticketType.setStatus(TicketErrorCauseEntity.STATUS.VALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketErrorCauseServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelError() == 1) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                        actionAuditEntity.setDescription(oldEntity.getName());
                    } else if (ticketType.getLevelError() == 2) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                        TicketErrorCauseEntity findNameLv2 = ticketErrorCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
                    } else if (ticketType.getLevelError() == 3) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_EXPIRE_ERROR.value);
                        TicketErrorCauseEntity findNameLv3 = ticketErrorCauseServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getTicketErrorCauseId(), actionType);
                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketErrorCauseServiceImpl updateStatusMultipleTicketErrorCause", ex);
        }
        return false;
    }

    @Override
    public Object getDataDetailTicketErrorCause(Long ticketErrorCauseId, TicketErrorCauseNewDTO params) {
        log.info("TicketErrorCauseServiceImpl start getDataDetailTicketErrorCause ");
        return ticketErrorCauseRepository.getDataDetailTicketErrorCause(ticketErrorCauseId, params);
    }

    @Override
    public List<TicketErrorCauseEntity> getErrorCauseByParentId(List<Long> lstParentId, Long levelId) {
        return ticketErrorCauseRepository.getErrorCauseByParentId(lstParentId, levelId);
    }


}
