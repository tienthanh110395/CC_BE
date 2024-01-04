package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.dto.ActionAuditSearchDTO;
import com.viettel.etc.repositories.ActionAuditRepository;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.tables.ActionAuditDetailServiceJPA;
import com.viettel.etc.services.tables.ActionAuditServiceJPA;
import com.viettel.etc.utils.FnCommon;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ActionAuditServiceImpl implements ActionAuditService {

    private final static Logger LOG = LoggerFactory.getLogger(ActionAuditServiceImpl.class);

    @Autowired
    ActionAuditServiceJPA actionAuditServiceJPA;

    @Autowired
    ActionAuditDetailServiceJPA actionAuditDetailServiceJPA;

    @Autowired
    ActionAuditRepository actionAuditRepository;

    /**
     * Luu thong tin lich su tac dong
     *
     * @param authentication
     * @param actionAuditDTO
     * @return
     */
    @Override
    public ActionAuditEntity saveActAudit(Authentication authentication, ActionAuditDTO actionAuditDTO) {
        String userLogin = FnCommon.getUserLogin(authentication);
        ActionAuditEntity actionAudit = new ActionAuditEntity();
        actionAudit.setActTypeId(actionAuditDTO.getActTypeId());
        actionAudit.setContractId(actionAuditDTO.getContractId());
        actionAudit.setTicketId(actionAuditDTO.getTicketId());
        actionAudit.setTicketAssignId(actionAuditDTO.getTicketAssignId());
        actionAudit.setActionUserName(userLogin);
        actionAudit.setActionUserFullName(userLogin);
        actionAudit.setAppId(FnCommon.getClientId(authentication));
        actionAudit.setIpPc("localhost");
        actionAudit.setStatus(ActionAuditEntity.Status.SUCCESS.value);
        actionAudit.setTicketStatus(actionAuditDTO.getTicketStatus());
        actionAudit.setDescription(actionAuditDTO.getDescription());
        actionAudit = actionAuditServiceJPA.save(actionAudit);
        return actionAudit;
    }

    /**
     * Luu thong tin chi tiet lich su tac dong
     *
     * @param actionAuditId
     * @param oldEntity
     * @param newEntity
     * @param pkId
     * @param actionName
     * @return
     */
    @Override
    public List<ActionAuditDetailEntity> saveActAuditDetail(Long actionAuditId, Object oldEntity, Object newEntity, Long pkId, String actionName) {
        if (newEntity == null) return null;
        List<ActionAuditDetailEntity> result = new ArrayList<>();
        try {
            String tableName;
            Field[] fields;
            if (Objects.nonNull(oldEntity)) {
                tableName = oldEntity.getClass().getAnnotation(Table.class).name();
                fields = oldEntity.getClass().getDeclaredFields();
            } else {
                tableName = newEntity.getClass().getAnnotation(Table.class).name();
                fields = newEntity.getClass().getDeclaredFields();
            }
            for (Field field : fields) {
                field.setAccessible(true);
                boolean change = true;
                if (Objects.nonNull(oldEntity)) change = !Objects.equals(field.get(oldEntity), field.get(newEntity));
                if (change) {
                    Column column = field.getAnnotation(Column.class);
                    if (Objects.nonNull(column)) {
                        ActionAuditDetailEntity auditDetail = new ActionAuditDetailEntity();
                        auditDetail.setActionAuditId(actionAuditId);
                        auditDetail.setTableName(tableName);
                        auditDetail.setPkId(pkId);
                        auditDetail.setColumnName(column.name());
                        if (Objects.nonNull(oldEntity))
                            auditDetail.setOldValue(getField(field, oldEntity));
                        auditDetail.setNewValue(getField(field, newEntity));
                        auditDetail.setActionName(actionName);
                        result.add(auditDetail);
                    }
                }
            }
            if (!FnCommon.isNullOrEmpty(result)) {
                result = actionAuditDetailServiceJPA.saveAll(result);
            }
        } catch (Exception e) {
            LOG.error("Error saveActAuditDetail: ", e);
        }
        return result;
    }

    public List<ActionAuditDetailEntity> saveActAuditDetailDelete(Long actionAuditId, Object oldEntity, Object newEntity, Long pkId, String actionName) {
        List<ActionAuditDetailEntity> result = new ArrayList<>();
        try {
            String tableName;
            Field[] fields;
            if (Objects.nonNull(oldEntity)) {
                tableName = oldEntity.getClass().getAnnotation(Table.class).name();
                fields = oldEntity.getClass().getDeclaredFields();
            } else {
                tableName = newEntity.getClass().getAnnotation(Table.class).name();
                fields = newEntity.getClass().getDeclaredFields();
            }
            for (Field field : fields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (Objects.nonNull(column)) {
                    ActionAuditDetailEntity auditDetail = new ActionAuditDetailEntity();
                    auditDetail.setActionAuditId(actionAuditId);
                    auditDetail.setTableName(tableName);
                    auditDetail.setPkId(pkId);
                    auditDetail.setColumnName(column.name());
                    if (Objects.nonNull(oldEntity))
                        auditDetail.setOldValue(getField(field, oldEntity));
                    auditDetail.setNewValue(getField(field, newEntity));
                    auditDetail.setActionName(actionName);
                    result.add(auditDetail);
                }
            }
            if (!FnCommon.isNullOrEmpty(result)) {
                result = actionAuditDetailServiceJPA.saveAll(result);
            }
        } catch (Exception e) {
            LOG.error("Error saveActAuditDetail: ", e);
        }
        return result;
    }

    private String getField(Field field, Object entity) {
        String result = "";
        try {
            if (field != null) {
                Class<?> dateType = Date.class;
                if (field.getType().isAssignableFrom(dateType)) {
                    result = FnCommon.convertDateToString((Date) field.get(entity), true, "/");
                } else {
                    result = Objects.toString(field.get(entity), null);
                }
            }
        } catch (Exception e) {
            LOG.error("Error getField: ", e);
        }
        return result;
    }

    /**
     * Luu thong tin lich su tac dong cho act
     *
     * @param authentication
     * @param actionAuditDTO
     * @param oldEntity
     * @param newEntity
     * @param pkId
     * @param actionName
     */
    @Override
    public void saveActAuditAndActAuditDetail(Authentication authentication, ActionAuditDTO actionAuditDTO, Object oldEntity, Object newEntity, Long pkId, String actionName) {
        ActionAuditEntity actionAuditEntity = saveActAudit(authentication, actionAuditDTO);
        saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldEntity, newEntity, pkId, actionName);
    }


    /**
     * Tim kiem thong tin lich su tac dong
     *
     * @param actionAuditDTO
     * @param authentication
     * @return
     */
    @Override
    public Object getActionAudit(ActionAuditDTO actionAuditDTO, Authentication authentication) {
        return actionAuditRepository.getActionAudit(actionAuditDTO);
    }

    @Override
    public Object searchImpactLog(Authentication authentication, ActionAuditSearchDTO params) {
        LOG.info("ActionAuditServiceImpl start searchImpactLog ");
        return actionAuditRepository.searchImpactLog(authentication, params);
    }

    @Override
    public Object getDataDetailImpact(Authentication authentication, ActionAuditDetailDTO params) {
        LOG.info("ActionAuditServiceImpl start getDataDetailImpact ");
        return actionAuditRepository.getDataDetailImpact(params);
    }

    @Override
    public void exportImpactLog(ActionAuditSearchDTO params, HttpServletResponse response) {

        LOG.info("ActionAuditServiceImpl start exportImpactLog ");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("template" + File.separator + "export" + File.separator + "DanhSachLogTacDong.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(is)) {
            List<ActionAuditDTO> resultList = (List<ActionAuditDTO>) actionAuditRepository.exportImpactLog(params).getListData();
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.getRow(3);
            XSSFCellStyle baseStyle = row.getCell(0).getCellStyle();
            int rowIndex = 6, no = 1;
            row = sheet.getRow(rowIndex);
            XSSFCellStyle style = row.getCell(0).getCellStyle();
            for (ActionAuditDTO item : resultList) {
                row = sheet.createRow(rowIndex++);
                createCell(style, row, 0, no++);
                createCell(style, row, 1, item.getActTypeName());
                if (item.getActionName().equalsIgnoreCase((ActionAuditDetailEntity.ActionName.INSERT.value))) {
                    createCell(style, row, 2, "Thêm mới");
                    ;
                } else if (item.getActionName().equalsIgnoreCase((ActionAuditDetailEntity.ActionName.UPDATE.value))) {
                    createCell(style, row, 2, "Cập nhật");
                    ;
                } else {
                    createCell(style, row, 2, "Đổi trạng thái");
                    ;
                }
                createCell(style, row, 3, FnCommon.convertDateToString(item.getCreateDate(), true));
                createCell(style, row, 4, item.getActionUserName());
            }
            ByteArrayOutputStream os = new org.apache.commons.io.output.ByteArrayOutputStream();
            wb.write(os);
            FnCommon.responseFile(response, os.toByteArray(), "DanhSachLogTacDong" + System.currentTimeMillis() + ".xlsx");
            LOG.info("ActionAuditServiceImpl end exportImpactLog ");
        } catch (Exception e) {
            LOG.error("Has Error Export exportImpactLog: ", e);
        }
    }

    private void createCell(XSSFCellStyle style, Row row, int column, Object value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value != null ? String.valueOf(value) : "");
        cell.setCellStyle(style);
    }

    /**
     * Luu thong tin chi tiet lich su tac dong
     *
     * @param actionAuditId
     * @param oldEntity
     * @param newEntity
     * @param pkId
     * @param actionName
     * @return
     */
    @Override
    public List<ActionAuditDetailEntity> saveAllActAuditDetail(Long actionAuditId, Object oldEntity, Object newEntity, Long pkId, String actionName) {
        if (newEntity == null) return null;
        List<ActionAuditDetailEntity> result = new ArrayList<>();
        try {
            String tableName;
            Field[] fields;
            if (Objects.nonNull(oldEntity)) {
                tableName = oldEntity.getClass().getAnnotation(Table.class).name();
                fields = oldEntity.getClass().getDeclaredFields();
            } else {
                tableName = newEntity.getClass().getAnnotation(Table.class).name();
                fields = newEntity.getClass().getDeclaredFields();
            }
            for (Field field : fields) {
                field.setAccessible(true);

                Column column = field.getAnnotation(Column.class);
                if (Objects.nonNull(column)) {
                    ActionAuditDetailEntity auditDetail = new ActionAuditDetailEntity();
                    auditDetail.setActionAuditId(actionAuditId);
                    auditDetail.setTableName(tableName);
                    auditDetail.setPkId(pkId);
                    auditDetail.setColumnName(column.name());
                    if (Objects.nonNull(oldEntity))
                        auditDetail.setOldValue(getField(field, oldEntity));
                    auditDetail.setNewValue(getField(field, newEntity));
                    auditDetail.setActionName(actionName);
                    result.add(auditDetail);
                }
            }
            if (!FnCommon.isNullOrEmpty(result)) {
                result = actionAuditDetailServiceJPA.saveAll(result);
            }
        } catch (Exception e) {
            LOG.error("Error saveActAuditDetail: ", e);
        }
        return result;
    }
}
