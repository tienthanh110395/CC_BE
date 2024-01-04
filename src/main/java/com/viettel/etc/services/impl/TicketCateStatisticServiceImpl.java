package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketCateStatisticDTO;
import com.viettel.etc.dto.TicketCateStatisticSearchDTO;
import com.viettel.etc.repositories.TicketCateStatisticRepository;
import com.viettel.etc.repositories.tables.TicketCateStatisticRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketCateStatisticService;
import com.viettel.etc.services.tables.ActionAuditServiceJPA;
import com.viettel.etc.services.tables.TicketCateStatisticServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketCateStatisticServiceImpl implements TicketCateStatisticService {
    private final int STT = 0;
    private final int STATISTIC_LV1_CODE = 1;
    private final int STATISTIC_LV1_NAME = 2;
    private final int STATISTIC_LV2_CODE = 3;
    private final int STATISTIC_LV2_NAME = 4;
    private final int STATISTIC_LV3_CODE = 5;
    private final int STATISTIC_LV3_NAME = 6;
    private final int STATISTIC_LV4_CODE = 7;
    private final int STATISTIC_LV4_NAME = 8;
    private final int STATISTIC_LV5_CODE = 9;
    private final int STATISTIC_LV5_NAME = 10;
    private final int STATISTIC_TEMPLATE = 11;
    private final int CREATE_USER = 12;
    private final int CREATE_DATE = 13;
    private final int UPDATE_USER = 14;
    private final int UPDATE_DATE = 15;
    private final int RESULT = 16;
    private final int DESC = 17;

    private static final Logger log = Logger.getLogger(TicketCateStatisticServiceImpl.class);
    @Autowired
    TicketCateStatisticRepository ticketCateStatisticRepository;

    @Autowired
    TicketCateStatisticServiceJPA ticketCateStatisticServiceJPA;

    @Autowired
    TicketCateStatisticRepositoryJPA ticketCateStatisticRepositoryJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    ActionAuditServiceJPA actionAuditServiceJPA;

    @Override
    public Object searchTicketCateStatistic(Authentication authentication, TicketCateStatisticSearchDTO dataParams) {
        log.info("TicketCateStatisticsServiceImpl start search ticket cate statistics");
        return ticketCateStatisticRepository.searchTicketCateStatistic(authentication, dataParams);
    }

    @Override
    @Transactional
    public boolean changeStatusTicketCateStatistic(TicketCateStatisticDTO dataParams, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start changeStatusTicketErrorCause ");
        try {

            StatisticTypeEntity statisticType = ticketCateStatisticServiceJPA.findById(dataParams.getStatisticTypeId()).get();
            StatisticTypeEntity oldEntity = (StatisticTypeEntity) statisticType.clone();
            statisticType.setUpdateUser(FnCommon.getUserLogin(authentication));
            statisticType.setUpdateDate(new Date(System.currentTimeMillis()));
            if (StatisticTypeEntity.STATUS.INVALID.value.equals(dataParams.getStatus())) {
                statisticType.setStatus(StatisticTypeEntity.STATUS.VALID.value);
            } else {
                statisticType.setStatus(StatisticTypeEntity.STATUS.INVALID.value);
            }
            ticketCateStatisticServiceJPA.save(statisticType);

            /* Luu log */
            String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

            ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
            actionAuditEntity.setStatus(statisticType.getStatus());
            actionAuditEntity.setCreateDate(statisticType.getCreateDate());
            actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
            actionAuditEntity.setTicketAssignId(statisticType.getStatisticTypeId());
            actionAuditEntity.setIpPc("localhost");
            StatisticTypeEntity levelEntity = ticketCateStatisticServiceJPA.findById(Long.valueOf(dataParams.getStatisticTypeId())).get();
            if (levelEntity.getLevelStatistic() == 1) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                actionAuditEntity.setDescription(oldEntity.getName());
            } else if (levelEntity.getLevelStatistic() == 2) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv2 = ticketCateStatisticServiceJPA.findById(Long.valueOf(levelEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
            } else if (levelEntity.getLevelStatistic() == 3) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv3 = ticketCateStatisticServiceJPA.findById(Long.valueOf(levelEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
            }
            else if (levelEntity.getLevelStatistic() == 4) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv4 = ticketCateStatisticServiceJPA.findById(Long.valueOf(levelEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv4.getName() + '/' + oldEntity.getName());
            }
            else if (levelEntity.getLevelStatistic() == 5) {
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv5 = ticketCateStatisticServiceJPA.findById(Long.valueOf(levelEntity.getParentId())).get();
                actionAuditEntity.setDescription(findNameLv5.getName() + '/' + oldEntity.getName());
            }
            ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
            actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, statisticType, statisticType.getStatisticTypeId(), actionType);
            return true;
        } catch (Exception ex) {
            log.info("Has error TicketErrorCauseServiceImpl change status", ex);
        }
        return false;
    }

    @Override
    public boolean changeStatusMultipleTicketCateStatistic(TicketCateStatisticDTO dataParams, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start updateStatusMultipleTicketErrorCause ");
        String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

        try {
            if (!dataParams.getLstIdsActive().isEmpty()) {
                List<StatisticTypeEntity> ticketTypes = ticketCateStatisticServiceJPA.findAllById(dataParams.getLstIdsActive());
                for (StatisticTypeEntity ticketType: ticketTypes){
                    StatisticTypeEntity oldEntity = (StatisticTypeEntity) ticketType.clone();
                    ticketType.setStatus(StatisticTypeEntity.STATUS.INVALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketCateStatisticServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelStatistic() == 1) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        actionAuditEntity.setDescription(oldEntity.getName());
                    } else if (ticketType.getLevelStatistic() == 2) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv2 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
                    } else if (ticketType.getLevelStatistic() == 3) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv3 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
                    }
                    else if (ticketType.getLevelStatistic() == 4) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv4 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv4.getName() + '/' + oldEntity.getName());
                    }
                    else if (ticketType.getLevelStatistic() == 5) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv5 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv5.getName() + '/' + oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getStatisticTypeId(), actionType);
                }
            }
            if (!dataParams.getLstIdsInactive().isEmpty()) {
                List<StatisticTypeEntity> ticketTypes = ticketCateStatisticServiceJPA.findAllById(dataParams.getLstIdsInactive());
                for (StatisticTypeEntity ticketType: ticketTypes){
                    StatisticTypeEntity oldEntity = (StatisticTypeEntity) ticketType.clone();
                    ticketType.setStatus(StatisticTypeEntity.STATUS.VALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketCateStatisticServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    if (ticketType.getLevelStatistic() == 1) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        actionAuditEntity.setDescription(oldEntity.getName());
                    } else if (ticketType.getLevelStatistic() == 2) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv2 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv2.getName() + '/' + oldEntity.getName());
                    } else if (ticketType.getLevelStatistic() == 3) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv3 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv3.getName() + '/' + oldEntity.getName());
                    }
                    else if (ticketType.getLevelStatistic() == 4) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv4 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv4.getName() + '/' + oldEntity.getName());
                    }
                    else if (ticketType.getLevelStatistic() == 5) {
                        actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                        StatisticTypeEntity findNameLv5 = ticketCateStatisticServiceJPA.findById(Long.valueOf(ticketType.getParentId())).get();
                        actionAuditEntity.setDescription(findNameLv5.getName() + '/' + oldEntity.getName());
                    }
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getStatisticTypeId(), actionType);
                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketErrorCauseServiceImpl updateStatusMultipleTicketErrorCause", ex);
        }
        return false;
    }

    @Override
    public Object getTicketCateStatisticById(TicketCateStatisticDTO params, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start getTicketErrorCauseById");
        return ticketCateStatisticRepository.getTicketCateStatisticByParent(params);
    }

    @Override
    public Boolean onDeleteStatistic(Long statisticTypeId, Authentication authentication) {
        log.info("TicketErrorCauseServiceImpl start onDelete ");
        try {
            StatisticTypeEntity statisticTypeEntity = ticketCateStatisticServiceJPA.getOne(statisticTypeId);
            if (statisticTypeEntity.getStatisticTypeId() != null) {
                ticketCateStatisticServiceJPA.doDelete(statisticTypeId);

                String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
                ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                actionAuditDTO.setTicketStatus(statisticTypeEntity.getStatus());
                actionAuditDTO.setCreateDate(statisticTypeEntity.getCreateDate());
                actionAuditDTO.setTicketAssignId(statisticTypeEntity.getStatisticTypeId());
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), statisticTypeEntity, null, statisticTypeId, actionType);

                return true;
            }
        } catch (Exception ex) {
            log.error("Has error TicketErrorCauseServiceImpl onDelete", ex);
        }
        return false;
    }

    @Override
    public Object createCateStatistic(TicketCateStatisticDTO dataParams, Authentication authentication) {
        log.info("TicketCateStatisticServiceImpl start createTicketCateStatistic");
        try {
//            StatisticTypeEntity StatisticList = ticketCateStatisticServiceJPA.findAllByCodeAndLevelStatistic(dataParams.getTicketCateStatisticsCode(), dataParams.getLevelStatistic());
            StatisticTypeEntity entity = new StatisticTypeEntity();

            String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
            StatisticTypeEntity oldTicketStatisticEntity = null;
            // Nếu là cập nhật
            if (dataParams.getStatisticTypeId() != null) {
                StatisticTypeEntity exist = ticketCateStatisticServiceJPA.findByCodeAndStatisticTypeIdNot(dataParams.getTicketCateStatisticsCode(), dataParams.getStatisticTypeId());
                if (exist != null) {
                    return 1L;
//                    throw new EtcException("ticket.category.duplicate.ticket.code");
                } else {
                    Optional<StatisticTypeEntity> optional = ticketCateStatisticServiceJPA.findById(dataParams.getStatisticTypeId());
                    if (optional.isPresent()) {
                        entity = optional.get();
                        oldTicketStatisticEntity = (StatisticTypeEntity) entity.clone();
                        actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                        entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                        entity.setUpdateDate(new Date(System.currentTimeMillis()));
                    }
                }
            }

            if (dataParams.getStatisticTypeId() == null) {
                if (ticketCateStatisticRepositoryJPA.existsByCode(dataParams.getTicketCateStatisticsCode())) {
                    return 1L;
                } else {
                    entity.setCreateUser(FnCommon.getUserLogin(authentication));
                    entity.setCreateDate(new Date(System.currentTimeMillis()));
                    entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                    entity.setUpdateDate(new Date(System.currentTimeMillis()));
                    entity.setStatus(dataParams.getStatus());
//                    entity.setCode(String.valueOf(new Date(System.currentTimeMillis()).getTime()));
//                    entity.setCode(String.valueOf((ticketCateStatisticServiceJPA.getTopupSequenceNo())));
                    entity.setCode(" ");
                }
            }

            entity.setName(dataParams.getTicketCateStatisticsName());
            entity.setDescription(dataParams.getDescription());
            entity.setTemplate(dataParams.getTemplate());

            if (dataParams.getTicketStatisticsLevelOne() != null) {
                entity.setParentId(dataParams.getTicketStatisticsLevelOne());
            }

            if (dataParams.getTicketStatisticsLevelTwo() != null) {
                entity.setParentId(dataParams.getTicketStatisticsLevelTwo());
            }

            if (dataParams.getTicketStatisticsLevelThree() != null) {
                entity.setParentId(dataParams.getTicketStatisticsLevelThree());
            }

            if (dataParams.getTicketStatisticsLevelFour() != null) {
                entity.setParentId(dataParams.getTicketStatisticsLevelFour());
            }

            entity.setLevelStatistic(dataParams.getLevelStatistic());

            StatisticTypeEntity entitylog = ticketCateStatisticServiceJPA.save(entity);
            entitylog.setCode(entitylog.getStatisticTypeId().toString());
            ticketCateStatisticServiceJPA.saveAndFlush(entitylog);
            /* lưu log */
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setTicketStatus(entitylog.getStatus());
            actionAuditDTO.setCreateDate(entitylog.getCreateDate());
            actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
            if (dataParams.getLevelStatistic() == TicketExpireCauseEntity.ExpireCauseLevel.Level_1.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                actionAuditDTO.setDescription(entitylog.getName());
            }  else if(dataParams.getLevelStatistic()==StatisticTypeEntity.StatisticTypeLevel.Level_2.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv2 = ticketCateStatisticServiceJPA.findById(dataParams.getTicketStatisticsLevelOne()).get();
                actionAuditDTO.setDescription(findNameLv2.getName() + '/' + entitylog.getName());
            }  else if(dataParams.getLevelStatistic()==StatisticTypeEntity.StatisticTypeLevel.Level_3.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv3 = ticketCateStatisticServiceJPA.findById(Long.valueOf(dataParams.getTicketStatisticsLevelTwo())).get();
                actionAuditDTO.setDescription(findNameLv3.getName() + '/' + entitylog.getName());
            }
            else if(dataParams.getLevelStatistic()==StatisticTypeEntity.StatisticTypeLevel.Level_4.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv4 = ticketCateStatisticServiceJPA.findById(dataParams.getTicketStatisticsLevelThree()).get();
                actionAuditDTO.setDescription(findNameLv4.getName() + '/' + entitylog.getName());
            }  else if(dataParams.getLevelStatistic()==StatisticTypeEntity.StatisticTypeLevel.Level_5.value){
                actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.STATISTIC_TYPE.value);
                StatisticTypeEntity findNameLv5 = ticketCateStatisticServiceJPA.findById(Long.valueOf(dataParams.getTicketStatisticsLevelFour())).get();
                actionAuditDTO.setDescription(findNameLv5.getName() + '/' + entitylog.getName());
            }
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketStatisticEntity, entitylog, entitylog.getStatisticTypeId(), actionType);
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketErrorCauseServiceImpl createTicketErrorCauseNew", ex);
        }
        return false;
    }

    @Override
    public Object getDataDetailTicketCateStatistic(Long statisticTypeId, TicketCateStatisticDTO params) {
        log.info("TicketErrorCauseServiceImpl start getDataDetailTicketErrorCause ");
        return ticketCateStatisticRepository.getDataDetailTicketCateStatistic(statisticTypeId, params);
    }

    @Override
    public void exportFile(TicketCateStatisticSearchDTO params, HttpServletResponse response) {
        log.info("TicketCateConfigServiceImpl start export statisticLog ");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("template" + File.separator + "export" + File.separator + "DANH_SACH_THONG_KE.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(is)) {
            List<TicketCateStatisticDTO> resultList = (List<TicketCateStatisticDTO>) ticketCateStatisticRepository.exportFile(params).getListData();
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowIndex = 3, no = 1;
            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCellStyle style = row.getCell(0).getCellStyle();
            for (TicketCateStatisticDTO item : resultList) {
                row = sheet.createRow(rowIndex++);
                createCell(style, row, 0, no++);
                createCell(style, row, 1, item.getTicketCateStatisticsCode1());
                createCell(style, row, 2, item.getCateStatisticNameParent1());
                createCell(style, row, 3, state(item.getStatus1()));
                createCell(style, row, 4, item.getTicketCateStatisticsCode2());
                createCell(style, row, 5, item.getCateStatisticNameParent2());
                createCell(style, row, 6, state(item.getStatus2()));
                createCell(style, row, 7, item.getTicketCateStatisticsCode3());
                createCell(style, row, 8, item.getCateStatisticNameParent3());
                createCell(style, row, 9, state(item.getStatus3()));
                createCell(style, row, 10, item.getTicketCateStatisticsCode4());
                createCell(style, row, 11, item.getCateStatisticNameParent4());
                createCell(style, row, 12, state(item.getStatus4()));
                createCell(style, row, 13, item.getTicketCateStatisticsCode5());
                createCell(style, row, 14, item.getCateStatisticNameParent5());
                createCell(style, row, 15, state(item.getStatus5()));
                createCell(style, row, 16, item.getTemplate());
                createCell(style, row, 17, item.getCreateUser());
                createCell(style, row, 18, FnCommon.convertDateToString(item.getCreateDate(), true));
                createCell(style, row, 19, item.getUpdateUser());
                createCell(style, row, 20, FnCommon.convertDateToString(item.getUpdateDate(), true));
            }
            ByteArrayOutputStream os = new org.apache.commons.io.output.ByteArrayOutputStream();
            wb.write(os);
            FnCommon.responseFile(response, os.toByteArray(), "DANH_SACH_THONG_KE" + System.currentTimeMillis() + ".xlsx");
            log.info("TicketCateStatisticServiceImpl end exportFile ");
        } catch (Exception e) {
            log.error("Has Error Export exportFileStatistic: ", e);
        }
    }

    //    private String headerContent() {
//        StringBuilder headerContent = new StringBuilder();
//        headerContent.append("STT").append("statisticCodeLevel1").append("statisticNameLevel1").append("statisticCodeLevel2")
//                .append("statisticNameLevel2").append("statisticCodeLevel3").append("statisticNameLevel3").append("statisticCodeLevel4")
//                .append("statisticNameLevel4").append("statisticCodeLevel5").append("statisticNameLevel5").append("template")
//        .append("createUser").append("createDate").append("updateUser").append("updateDate");
//        return headerContent.toString();
//
//    }
    private String state(Long status) {
        if (status == null) {
            return "";
        } else if (status == 1) {
            return "Đang hiệu lực";
        } else if (status == 0) {
            return "Không hiệu lực";
        }
        return "";
    }

    @Override
    public ResponseEntity<?> importFile(Authentication authentication, MultipartFile fileImport) throws EtcException, IOException {
        byte[] byteArr = fileImport.getBytes();
        String fileName = fileImport.getOriginalFilename();
        assert fileName != null;
        long failedLines = 0;
        boolean isExcel = FnCommon.validateFileName(fileName);
        StringBuilder hasError = new StringBuilder();
        String token = FnCommon.getStringToken(authentication);
        String customer = FnCommon.getUserLogin(authentication);
        List<?> listProcessingTimeDTO = new ArrayList<>();
        List<String> listError = new ArrayList<>();
        if (!isExcel) {
            throw new EtcException("common.validate.err.data.excel");
        }
        byte[] bytes = new byte[0];
        try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byteArr))) {
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(3) != null && sheet.getRow(3).getLastCellNum() < 16) {
                throw new EtcException("crm.vehicles.exception.excel");
            }
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (!FnCommon.rowIsEmpty(row)) {
                    String failCell = validateRow(row);
                    if (failCell != null) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", failCell + " không đúng định dạng");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STT)) || FnCommon.isNullOrBlank(row.getCell(STT).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Số thứ tự không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV1_CODE)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV1_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã thống kê cấp 1 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV1_NAME)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV1_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên thống kê cấp 1 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV2_CODE)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV2_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã thống kê cấp 2 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV2_NAME)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV2_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên thống kê cấp 2 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV3_CODE)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV3_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã thống kê cấp 3 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV3_NAME)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV3_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên thống kê cấp 3 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV4_CODE)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV4_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã thống kê cấp 4 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV4_NAME)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV4_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên thống kê cấp 4 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV5_CODE)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV5_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã thống kê cấp 5 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV5_NAME)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV5_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên thống kê cấp 5 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(STATISTIC_TEMPLATE)) || FnCommon.isNullOrBlank(row.getCell(STATISTIC_TEMPLATE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Form nhập không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(CREATE_USER)) || FnCommon.isNullOrBlank(row.getCell(CREATE_USER).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Người tạo không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(CREATE_DATE)) || FnCommon.isNullOrBlank(row.getCell(CREATE_DATE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Ngày tạo không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(UPDATE_USER)) || FnCommon.isNullOrBlank(row.getCell(UPDATE_USER).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Người cập nhật không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(UPDATE_DATE)) || FnCommon.isNullOrBlank(row.getCell(UPDATE_DATE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Ngày cập nhật không được để trống");
                        failedLines++;
                        continue;
                    }

                }
            }
//            boolean validateHeaderCell = FnCommon.validateHeaderCell(sheet, 3, 15, headerContent());
//            if (validateHeaderCell) {
            org.apache.commons.io.output.ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            bytes = os.toByteArray();
//            } else {
//                return new ResponseEntity<>(FnCommon.responseToClient(new EtcException("common.validate.err.data.excel")), HttpStatus.NOT_FOUND);
//            }
        } catch (Exception e) {
            System.out.println("ex" + e);
            log.error("Import noi dung khong thanh cong ", e);
        }
//        return returnFileExcel(bytes, fileName, hasError);
        return null;
    }

    private void createCell(XSSFCellStyle style, Row row, int column, Object value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value != null ? String.valueOf(value) : "");
        cell.setCellStyle(style);
    }

    private String validateRow(Row row) {
        if (!FnCommon.isNullOrBlank(row.getCell(STT)) && row.getCell(STT).getCellType() != CellType.STRING) {
            return "STT";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV1_CODE)) && row.getCell(STATISTIC_LV1_CODE).getCellType() != CellType.STRING) {
            return "Mã thống kê cấp 1";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV1_NAME)) && row.getCell(STATISTIC_LV1_NAME).getCellType() != CellType.STRING) {
            return "Tên thống kê cấp 1";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV2_CODE)) && row.getCell(STATISTIC_LV2_CODE).getCellType() != CellType.STRING) {
            return "Mã thống kê cấp 2";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV2_NAME)) && row.getCell(STATISTIC_LV2_NAME).getCellType() != CellType.STRING) {
            return "Tên thống kê cấp 2";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV3_CODE)) && row.getCell(STATISTIC_LV3_CODE).getCellType() != CellType.STRING) {
            return "Mã thống kê cấp 3";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV3_NAME)) && row.getCell(STATISTIC_LV3_NAME).getCellType() != CellType.STRING) {
            return "Tên thống kê cấp 3";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV4_CODE)) && row.getCell(STATISTIC_LV4_CODE).getCellType() != CellType.STRING) {
            return "Mã thống kê cấp 4";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV4_NAME)) && row.getCell(STATISTIC_LV4_NAME).getCellType() != CellType.STRING) {
            return "Tên thống kê cấp 4";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV5_CODE)) && row.getCell(STATISTIC_LV5_CODE).getCellType() != CellType.STRING) {
            return "Mã thống kê cấp 5";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_LV5_NAME)) && row.getCell(STATISTIC_LV5_NAME).getCellType() != CellType.STRING) {
            return "Tên thống kê cấp 5";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(STATISTIC_TEMPLATE)) && row.getCell(STATISTIC_TEMPLATE).getCellType() != CellType.STRING) {
            return "Form nhập";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(CREATE_USER)) && row.getCell(CREATE_USER).getCellType() != CellType.STRING) {
            return "Người tạo";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(CREATE_DATE)) && row.getCell(CREATE_DATE).getCellType() != CellType.STRING) {
            return "Ngày tạo";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(UPDATE_USER)) && row.getCell(UPDATE_USER).getCellType() != CellType.STRING) {
            return "Người cập nhật";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(UPDATE_DATE)) && row.getCell(UPDATE_DATE).getCellType() != CellType.STRING) {
            return "Ngày cập nhật";
        }
        return null;
    }

    private void resultImport(Cell resultCell, Cell descCell, String result, String desc) {
        if (resultCell != null && descCell != null) {
            resultCell.setCellType(CellType.STRING);
            resultCell.setCellValue(result);
            descCell.setCellType(CellType.STRING);
            descCell.setCellValue(desc);
        }
    }

    /**
     * Tai file template
     *
     * @param authentication Ma xac thuc
     * @return duong dan tai file ket qua
     */
    @Override
    public void downloadTemplate(Authentication authentication, HttpServletResponse response) {
        log.info("StatisticServiceImpl start download Template");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("template" + File.separator + "export" + File.separator + "FORM_IMPORT_THONG_KE.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(is)) {
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowIndex = 2;
            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCellStyle style = row.getCell(0).getCellStyle();
            ByteArrayOutputStream os = new org.apache.commons.io.output.ByteArrayOutputStream();
            wb.write(os);
            FnCommon.responseFile(response, os.toByteArray(), "FORM_IMPORT_THONG_KE.xlsx");
            log.info("StatisticServiceImpl end Template ");
        } catch (Exception e) {
            log.error("Has Error Export Template: ", e);
        }
    }

}

