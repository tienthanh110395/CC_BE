package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.repositories.TicketCateConfigRepository;
import com.viettel.etc.repositories.tables.TicketTypeRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketCateConfigService;
import com.viettel.etc.services.tables.*;
import com.viettel.etc.utils.FnCommon;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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
import java.util.stream.Collectors;

@Service
public class TicketCateConfigServiceImpl implements TicketCateConfigService {

    private static final Logger log = Logger.getLogger(TicketCateConfigServiceImpl.class);

    @Autowired
    TicketCateConfigRepository ticketCateConfigRepository;

    @Autowired
    TicketTypeServiceJPA ticketTypeServiceJPA;

    @Autowired
    TicketTypeRepositoryJPA ticketTypeRepositoryJPA;

    @Autowired
    TicketCateConfigServiceJPA ticketCateConfigServiceJPA;

    @Autowired
    TicketServiceJPA ticketServiceJPA;

    @Autowired
    TicketSlaServiceJPA ticketSlaServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    ActionAuditServiceJPA actionAuditServiceJPA;


    /**
     * Tao moi loai phan anh
     *
     * @param params         params client
     * @param authentication : thong tin nguoi dung
     * @return
     */
    public Object createOrUpdate(TicketCateConfigDTO params, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start createOrUpdate");
        try {
            TicketTypeEntity entity = new TicketTypeEntity();
            if (params.getTicketTypeId() == null) {
                TicketTypeEntity ticketType = ticketTypeServiceJPA.findByCodeAndLevelTt(params.getTicketTypeCode(), params.getLevelTt());
                if (ticketType != null && ticketType.getTicketTypeId() != null) {
                    return 1L;
                }
                entity.setCreateUser(FnCommon.getUserLogin(authentication));
                entity.setCreateDate(new Date(System.currentTimeMillis()));
                entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                entity.setUpdateDate(new Date(System.currentTimeMillis()));
                entity.setCode(" ");
                entity.setStatus(params.getStatus());
            }
            String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
            TicketTypeEntity oldTicketTypeEntity = null;
            if (params.getTicketTypeId() != null) {
                TicketTypeEntity exist = ticketTypeServiceJPA.findByCodeAndTicketTypeIdNot(params.getTicketTypeCode(), params.getTicketTypeId());
                if (exist != null) {
                    return 1L;
                } else {
                    Optional<TicketTypeEntity> optional = ticketTypeServiceJPA.findById(params.getTicketTypeId());
                    if (optional.isPresent()) {
                        entity = optional.get();
                        oldTicketTypeEntity = (TicketTypeEntity) entity.clone();
                        actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                        entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                        entity.setUpdateDate(new Date(System.currentTimeMillis()));
                    }
                }
            }
            if (params.getParentId() != null) {
                entity.setParentId(params.getParentId());
                entity.setStatus(params.getStatus());
            }
            if (params.getTicketGenre() != null) {
                entity.setParentId(Long.valueOf(params.getTicketGenre()));
                entity.setStatus(params.getStatus());
            }
            entity.setTicketTemplate(params.getTicketTemplate());
            entity.setName(params.getTicketTypeName());
            entity.setDescription(params.getDescription());
            entity.setLevelTt(params.getLevelTt());

            TicketTypeEntity entitylog = ticketTypeServiceJPA.save(entity);
            entitylog.setCode(entitylog.getTicketTypeId().toString());
            ticketTypeServiceJPA.saveAndFlush(entitylog);

            /* Luu log */
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setTicketStatus(entitylog.getStatus());
            actionAuditDTO.setCreateDate(entitylog.getCreateDate());
            actionAuditDTO.setTicketAssignId(entitylog.getTicketTypeId());
            if (params.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GROUP.value) {
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GROUP.value);
                actionAuditDTO.setDescription(entitylog.getName());
            }
            if(params.getParentId() != null) {
                if (params.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GENRE.value) {
                    TicketTypeEntity findNameGroupByParent = ticketTypeServiceJPA.findById(params.getParentId()).get();
                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GENRE.value);
                    actionAuditDTO.setDescription(findNameGroupByParent.getName() + '/' + entitylog.getName());
                }
                if (params.getLevelTt() == TicketTypeEntity.TicketTypeLevel.TYPE.value) {
                    TicketTypeEntity getGenreName = ticketTypeServiceJPA.findById(Long.valueOf(params.getParentId())).get();
                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE.value);
                    actionAuditDTO.setDescription(getGenreName.getName() + '/' + entitylog.getName());
                }
            }

            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketTypeEntity, entitylog, entitylog.getTicketTypeId(), actionType);
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketCateConfigServiceImpl createOrUpdate", ex);
        }
        return false;
    }

    @Override
    @Transactional
    public Object onDelete(Long ticketTypeId, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start onDelete ");
        try {
            TicketTypeEntity ticketType = ticketTypeServiceJPA.findById(ticketTypeId).get();
            List<TicketEntity> lstTicket = ticketServiceJPA.findByL3TicketTypeId(ticketType.getTicketTypeId());
            List<TicketSlaEntity> lstTicketSla = ticketSlaServiceJPA.findByTicketTypeId(ticketType.getTicketTypeId());
            if (lstTicket.size() > 0 || lstTicketSla.size() > 0) {
                return 1L;
            }
            if (ticketType.getTicketTypeId() != null) {
                ticketTypeServiceJPA.doDelete(ticketTypeId);

                /* Luu log */
                String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
                ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                actionAuditDTO.setTicketStatus(ticketType.getStatus());
                actionAuditDTO.setCreateDate(ticketType.getCreateDate());
                actionAuditDTO.setTicketAssignId(ticketType.getTicketTypeId());
                if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GROUP.value) {
                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GROUP.value);
                } else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GENRE.value) {
                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GENRE.value);
                } else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.TYPE.value) {
                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE.value);
                }
                ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), ticketType, null, ticketTypeId, actionType);
                return true;
            }
        } catch (Exception ex) {
            log.error("Has error TicketCateConfigServiceImpl onDelete   ", ex);
        }
        return false;
    }

    @Override
    public Object getDataDetail(Long ticketTypeId, TicketCateConfigDTO params) {
        log.info("TicketCateConfigServiceImpl start getDataDetail ");
        return ticketCateConfigRepository.getDataDetail(ticketTypeId, params);
    }

    @Override
    public Object changeStatus(TicketCateConfigDTO params, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start change status ");
        try {
            TicketTypeEntity ticketType = ticketTypeServiceJPA.findById(params.getTicketTypeId()).get();
            TicketTypeEntity oldEntity = (TicketTypeEntity) ticketType.clone();
            ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
            ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
            if (TicketTypeEntity.Status.INVALID.value.equals(ticketType.getStatus())) {
                ticketType.setStatus(TicketTypeEntity.Status.VALID.value);
            } else {
                ticketType.setStatus(TicketTypeEntity.Status.INVALID.value);
            }
            ticketTypeServiceJPA.save(ticketType);
            /* Luu log */
            String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

            ActionAuditEntity actionAuditEntity = new ActionAuditEntity();

            actionAuditEntity.setStatus(ticketType.getStatus());
            actionAuditEntity.setCreateDate(ticketType.getCreateDate());
            actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
            actionAuditEntity.setIpPc("localhost");
            if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GROUP.value) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GROUP.value);
                actionAuditEntity.setDescription(oldEntity.getName());
            }  else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GENRE.value) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GENRE.value);
                TicketTypeEntity ticketGroupName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                actionAuditEntity.setDescription(ticketGroupName.getName() + "/"+ oldEntity.getName());
            }  else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.TYPE.value) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE.value);
                TicketTypeEntity ticketGenreName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                actionAuditEntity.setDescription(ticketGenreName.getName() + "/"+ oldEntity.getName());
            }
            ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
            actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getTicketTypeId(), actionType);

            return true;
        } catch (Exception ex) {
            log.info("Has error TicketCateConfigServiceImpl change status", ex);
        }
        return false;
    }

    @Override
    public Object createUpdateList(List<TicketCateConfigDTO> params, Authentication authentication) throws Exception {

        List<TicketTypeEntity> dataList = new ArrayList<>();
        String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
        TicketTypeEntity oldTicketTypeEntity = null;
        for (TicketCateConfigDTO dto : params) {

            // Nhóm theo Code
            Map<String, List<TicketCateConfigDTO>> groupByCode =

                    params.stream().collect(Collectors.groupingBy(TicketCateConfigDTO::getTicketTypeCode));

            // Sử dụng vòng lặp for để lặp qua các List<TicketCateConfigDTO>
            for (Map.Entry<String, List<TicketCateConfigDTO>> entry : groupByCode.entrySet()) {

                List<TicketCateConfigDTO> dtoList = entry.getValue();

                if (dtoList.size() > 1) {

                    throw new SQLIntegrityConstraintViolationException();
                }
            }

            TicketTypeEntity item = new TicketTypeEntity();
            if (FnCommon.isNullOrEmpty(dto.getTicketTypeCode()) || FnCommon.isNullOrEmpty(dto.getTicketTypeName())) {

                throw new Exception();
            } else if (ticketTypeRepositoryJPA.existsByCode(dto.getTicketTypeCode())) {

                // throw exception
                throw new SQLIntegrityConstraintViolationException();

            } else {
                item.setName(dto.getTicketTypeName());
                item.setCode(dto.getTicketTypeCode());
                item.setCreateUser(FnCommon.getUserLogin(authentication));
                item.setCreateDate(new Date(System.currentTimeMillis()));
                item.setUpdateUser(FnCommon.getUserLogin(authentication));
                item.setUpdateDate(new Date(System.currentTimeMillis()));
                item.setDescription(dto.getDescription());
                item.setLevelTt(dto.getLevelTt());
                item.setStatus(dto.getStatus());
                if (item.getLevelTt() != null && item.getLevelTt() == 2) {
                    item.setParentId(Long.valueOf(dto.getTicketGroup()).longValue());
                }
                dataList.add(item);
            }
        }

        List<TicketTypeEntity> entitylogs = ticketTypeServiceJPA.saveAll(dataList);
        for (TicketTypeEntity entitylog : entitylogs) {
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setTicketStatus(entitylog.getStatus());
            actionAuditDTO.setCreateDate(entitylog.getCreateDate());
            actionAuditDTO.setTicketAssignId(entitylog.getTicketTypeId());

            if (entitylog.getLevelTt() == 1) {
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GROUP.value);
            } else if (entitylog.getLevelTt() == 2) {
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GENRE.value);
            } else if (entitylog.getLevelTt() == 3) {
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE.value);
            }
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketTypeEntity, entitylog, entitylog.getTicketTypeId(), actionType);
        }
        return true;
    }

    @Override
    @Transactional
    public Object updateStatusMultiple(TicketCateConfigDTO params, Authentication authentication){
        log.info("TicketCateConfigServiceImpl start update status multiple ");
        String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

        try {
            if (!params.getLstIdsActive().isEmpty()) {
                List<TicketTypeEntity> ticketTypes = ticketTypeServiceJPA.findAllById(params.getLstIdsActive());
                for (TicketTypeEntity ticketType: ticketTypes){
                    TicketTypeEntity oldEntity = (TicketTypeEntity) ticketType.clone();
                    ticketType.setStatus(TicketTypeEntity.Status.INVALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketTypeServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setDescription(oldEntity.getName());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GROUP.value) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GROUP.value);
                    } else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GENRE.value) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GENRE.value);
                        TicketTypeEntity ticketGroupName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                        actionAuditEntity.setDescription(ticketGroupName.getName() + "/"+ oldEntity.getName());
                    } else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.TYPE.value) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE.value);
                        TicketTypeEntity ticketGenreName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                        actionAuditEntity.setDescription(ticketGenreName.getName() + "/"+ oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getTicketTypeId(), actionType);
                }
            }
            if (!params.getLstIdsInactive().isEmpty()) {
                List<TicketTypeEntity> ticketTypes = ticketTypeServiceJPA.findAllById(params.getLstIdsInactive());
                for (TicketTypeEntity ticketType: ticketTypes){
                    TicketTypeEntity oldEntity = (TicketTypeEntity) ticketType.clone();
                    ticketType.setStatus(TicketTypeEntity.Status.VALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketTypeServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setDescription(oldEntity.getName());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GROUP.value) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GROUP.value);
                    } else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.GENRE.value) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE_GENRE.value);
                        TicketTypeEntity ticketGroupName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                        actionAuditEntity.setDescription(ticketGroupName.getName() + "/"+ oldEntity.getName());
                    } else if (ticketType.getLevelTt() == TicketTypeEntity.TicketTypeLevel.TYPE.value) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_TYPE.value);
                        TicketTypeEntity ticketGenreName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                        actionAuditEntity.setDescription(ticketGenreName.getName() + "/"+ oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getTicketTypeId(), actionType);
                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketCateConfigServiceImpl update status multiple", ex);
        }
        return false;
    }

    @Override
    public Object searchTicketType(Authentication authentication, TicketConfigSearchDTO params) {
        log.info("TicketCateConfigServiceImpl start search ticket type ");
        return ticketCateConfigRepository.searchTicketType(authentication, params);
    }

    @Override
    public Object getTicketTypeByParentId(TicketCateConfigDTO params, Authentication authentication) {
        log.info("TicketCateConfigServiceImpl start getTicketTypeByParentId");
        return ticketCateConfigRepository.getTicketTypeByParentId(params);
    }

    /**
     * Lấy danh sách thể loại phản ánh ở màn Tạo mới Map nguyên nhân lỗi
     *
     * @param parentId
     * @return
     */
    @Override
    public List<TicketCateConfigDTO> getTicketTypeByParentIdForMapping(Long parentId) {
        return ticketCateConfigRepository.getTicketTypeByParentIdForMapping(parentId);
    }

    @Override
    public List<TicketCateConfigDTO> getTicketTypeForMapConfigTime(Long parentId) {
        return ticketCateConfigRepository.getTicketTypeByParentIdForConfigTime(parentId);
    }

    @Override
    public List<TicketCateConfigDTO> getTicketTypeForConfigTimeDetail(Long ticketTypeId) {
        return ticketCateConfigRepository.getTicketTypeByTicketTypeIdForConfigTime(ticketTypeId);
    }

    private void createCell(XSSFCellStyle style, Row row, int column, Object value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value != null ? String.valueOf(value) : "");
        cell.setCellStyle(style);
    }

    private String mapLevelTt(Long levelTt) {
        if (levelTt == null) {
            return "";
        } else if (levelTt.equals(1L)) {
            return "Nhóm phản ánh";
        } else if (levelTt.equals(2L)) {
            return "Thể loại phản ánh";
        } else if (levelTt.equals(3L)) {
            return "Loại phản ánh";
        } else if (levelTt.equals(4L)) {
            return "Thời gian xử lý";
        } else if (levelTt.equals(5L)) {
            return "Mức độ ưu tiên";
        } else if (levelTt.equals(6L)) {
            return "Nguyên nhân lỗi";
        } else if (levelTt.equals(7L)) {
            return "Map nguyên nhân lỗi";
        } else if (levelTt.equals(8L)) {
            return "Nguyên nhân quá hạn";
        } else if (levelTt.equals(9L)) {
            return "Đơn vị chịu trach nhiệm";
        }
        return "";
    }

    private String mapActType(Long actType) {
        if (actType == null) {
            return "";
        } else if (actType.equals(1L)) {
            return "Thêm mới";
        } else if (actType.equals(2L)) {
            return "Cập nhật";
        } else if (actType.equals(3L)) {
            return "Xóa";
        }
        return "";
    }

}
