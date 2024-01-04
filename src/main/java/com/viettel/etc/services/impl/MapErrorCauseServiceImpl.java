package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.MapErrorCauseDTO;
import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.dto.MapErrorUpdateDTO;
import com.viettel.etc.repositories.MapErrorCauseRepository;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.MapErrorCauseService;
//import com.viettel.etc.services.TicketTypeLogDetailService;
import com.viettel.etc.services.tables.MapErrorCauseServiceJPA;
//import com.viettel.etc.services.tables.TicketTypeLogServiceJPA;
import com.viettel.etc.services.tables.TicketTypeServiceJPA;
import com.viettel.etc.utils.FnCommon;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ThaiBQ
 * @date 07/06/2023
 */

@Service
public class MapErrorCauseServiceImpl implements MapErrorCauseService {

    private static final Logger log = Logger.getLogger(MapErrorCauseServiceImpl.class);

    @Autowired
    MapErrorCauseRepository mapErrorCauseRepository;

    @Autowired
    MapErrorCauseServiceJPA mapErrorCauseServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    TicketTypeServiceJPA ticketTypeServiceJPA;

    /**
     * Tìm kiếm Map nguyên nhân lỗi
     *
     * @param authentication
     * @param params
     * @return
     */
    @Override
    public Object searchMapErrorCause(Authentication authentication, MapErrorCauseSearchDTO params) {
        log.info("MapErrorCauseServiceImpl start search map error cause");
        return mapErrorCauseRepository.searchMapErrorCause(authentication, params);
    }

    /**
     * Thêm mới map nguyên nhân lỗi
     *
     * @param dataParams
     * @param authentication
     * @return
     */
    @Override
    public Object saveMapError(List<MapErrorEntity> dataParams, Authentication authentication) {

        try {
            String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
            if (!dataParams.isEmpty()) {
                for (MapErrorEntity item : dataParams) {
                    item.setCreateUser(FnCommon.getUserLogin(authentication));
                    item.setCreateDate(new Date(System.currentTimeMillis()));
                    item.setUpdateDate(new Date(System.currentTimeMillis()));
                    item.setUpdateUser(FnCommon.getUserLogin(authentication));
                }
                List<MapErrorEntity> entitylogs = mapErrorCauseServiceJPA.saveAll(dataParams);
                for (MapErrorEntity entitylog : entitylogs) {
                    TicketTypeEntity ticketType = ticketTypeServiceJPA.findById(entitylog.getTicketTypeId()).get();
                    TicketTypeEntity parentName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                    ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                    actionAuditDTO.setDescription(parentName.getName()+'/'+ticketType.getName());
                    actionAuditDTO.setTicketStatus(0L);
                    actionAuditDTO.setCreateDate(entitylog.getCreateDate());

                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.MAPPING_EXPIRE_ERROR.value);
                    ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                    actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), null, entitylog, entitylog.getTicketTypeId(), actionType);
                }

                return true;
            } else {
                return null;
            }

        } catch (Exception ex) {

            log.error("Has error MapErrorCauseServiceImpl save", ex);
        }
        return null;
    }

    @Override
    @Transactional
    public Object onDelete(Long mapErrorCauseId, Authentication authentication) {
        log.info("MapErrorCauseServiceImpl start onDelete ");
        try {
            MapErrorEntity mapErrorEntity = mapErrorCauseServiceJPA.findById(mapErrorCauseId).get();
            mapErrorCauseServiceJPA.deleteById(mapErrorCauseId);
            String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setTicketStatus(1L);
            actionAuditDTO.setCreateDate(mapErrorEntity.getCreateDate());
            actionAuditDTO.setTicketAssignId(mapErrorEntity.getMapId());
            actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.MAPPING_EXPIRE_ERROR.value);
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), mapErrorEntity, null, mapErrorCauseId, actionType);
            return true;
        } catch (Exception ex) {
            log.error("Has error MapErrorCauseServiceImpl onDelete", ex);
        }
        return false;
    }

    /**
     * Lấy data Map nguyên nhân lỗi theo "thể loại phản ánh" ở màn "Cập nhật map nguyên nhân lỗi"
     *
     * @param authentication
     * @param ticketGenreId
     * @return
     */
    @Override
    public Object searchDataMapForUpdate(Long ticketGenreId, Authentication authentication) {
        log.info("MapErrorCauseServiceImpl start search map error cause");
        return mapErrorCauseRepository.searchDataMapForUpdate(ticketGenreId, authentication);
    }

    private void createCell(XSSFCellStyle style, Row row, int column, Object value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value != null ? String.valueOf(value) : "");
        cell.setCellStyle(style);
    }

    /**
     * Export danh sách map nguyên nhân lỗi ra file excel
     *
     * @param params
     * @param response
     */
    @Override
    public void exportMapErrorCause(MapErrorCauseSearchDTO params, HttpServletResponse response) {
        log.info("MapErrorCauseServiceImpl start exportImpactLog ");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("template" + File.separator
                + "export" + File.separator + "DANH_SACH_MAPING_NNL.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(is)) {
            List<MapErrorCauseDTO> resultList = (List<MapErrorCauseDTO>) mapErrorCauseRepository.exportImpact(params).getListData();
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowIndex = 3, no = 1;
            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCellStyle style = row.getCell(0).getCellStyle();
            for (MapErrorCauseDTO item : resultList) {
                row = sheet.createRow(rowIndex++);
                createCell(style, row, 0, no++);
                createCell(style, row, 1, item.getTicketGroupCode());
                createCell(style, row, 2, item.getTicketGroup());
                createCell(style, row, 3, item.getTicketGroupStatus());
                createCell(style, row, 4, item.getTicketGenreCode());
                createCell(style, row, 5, item.getTicketGenre());
                createCell(style, row, 6, item.getTicketGenreStatus());
                createCell(style, row, 7, item.getTicketErrorId1());
                createCell(style, row, 8, item.getTicketErrorName1());
                createCell(style, row, 9, item.getTicketErrorId2());
                createCell(style, row, 10, item.getTicketErrorName2());
                createCell(style, row, 11, item.getTicketErrorId3());
                createCell(style, row, 12, item.getTicketErrorName3());
                createCell(style, row, 13, item.getCreateUser());
                createCell(style, row, 14, FnCommon.convertDateToString(item.getCreateDate(), true));
                createCell(style, row, 15, item.getUpdateUser());
                createCell(style, row, 16, FnCommon.convertDateToString(item.getUpdateDate(), true));
            }
            ByteArrayOutputStream os = new org.apache.commons.io.output.ByteArrayOutputStream();
            wb.write(os);
            FnCommon.responseFile(response, os.toByteArray(), "DANH_SACH_MAPING_NNL" + System.currentTimeMillis() + ".xlsx");
            log.info("MapErrorCauseServiceImpl end export ");
        } catch (Exception e) {
            log.error("Has Error Export export: ", e);
        }
    }

    /**
     * Tai file template Mapping nguyên nhân lỗi
     *
     * @param authentication Ma xac thuc
     * @return duong dan tai file ket qua
     */
    @Override
    public void downloadMapErrorCauseTemplate(Authentication authentication, HttpServletResponse response) {
        log.info("MapErrorCauseServiceImpl start dowload Template");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("template" + File.separator + "export" + File.separator + "FORM_IMPORT_MAPPING_NNL.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(is)) {
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowIndex = 2;
            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCellStyle style = row.getCell(0).getCellStyle();
            ByteArrayOutputStream os = new org.apache.commons.io.output.ByteArrayOutputStream();
            wb.write(os);
            FnCommon.responseFile(response, os.toByteArray(), "FORM_IMPORT_MAPPING_NNL.xlsx");
            log.info("MapErrorCauseServiceImpl end Template ");
        } catch (Exception e) {
            log.error("Has Error Export Template: ", e);
        }
    }

    @Override
    public Object getErrorCauseByParentIdForUpdateMap(Authentication authentication, MapErrorCauseSearchDTO dataParams) {
        return mapErrorCauseRepository.getErrorCauseByParentIdForUpdateMap(authentication, dataParams);
    }

    @Override
    public Boolean updateMapError(List<MapErrorUpdateDTO> dataParams, Authentication authentication) {

        List<MapErrorEntity> dataList = new ArrayList<>();

        try {
            if (!dataParams.isEmpty()) {
                String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                MapErrorEntity oldMapErroreEntity = null;

                for (MapErrorUpdateDTO dto : dataParams) {

                    if (dto.getMapErrorCauseId() != null) {
                        List<Long> lstMapId = dto.getMapErrorCauseId();
                        MapErrorEntity entity = new MapErrorEntity();

                        for (Long mapId : lstMapId) {
                            Optional<MapErrorEntity> optional = mapErrorCauseServiceJPA.findById(mapId);
                            if (optional.isPresent()) {
                                entity = optional.get();
                                oldMapErroreEntity = (MapErrorEntity) entity;
                                actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                                entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                                entity.setUpdateDate(new Date(System.currentTimeMillis()));
                            }
                        }
                        mapErrorCauseServiceJPA.deleteDataByListId(lstMapId);
                    }

                    MapErrorEntity item = new MapErrorEntity();

                    item.setTicketErrorId(dto.getTicketErrorId());
                    item.setTicketErrorLv2Id(dto.getTicketErrorLv2Id());
                    item.setTicketErrorLv3Id(dto.getTicketErrorLv3Id());
                    item.setTicketTypeId(dto.getTicketTypeId());
                    item.setCreateUser(FnCommon.getUserLogin(authentication));
                    item.setCreateDate(new Date(System.currentTimeMillis()));
                    item.setUpdateDate(new Date(System.currentTimeMillis()));
                    item.setUpdateUser(FnCommon.getUserLogin(authentication));

                    dataList.add(item);
                }

                List<MapErrorEntity> lstEntity = mapErrorCauseServiceJPA.saveAll(dataList);
                for (MapErrorEntity entitylog : lstEntity) {
//                    oldMapErroreEntity = (MapErrorEntity) entitylog.clone();
                    TicketTypeEntity ticketType = ticketTypeServiceJPA.findById(entitylog.getTicketTypeId()).get();
                    ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                    actionAuditDTO.setDescription(ticketType.getName());
                    actionAuditDTO.setTicketStatus(0L);
                    actionAuditDTO.setCreateDate(entitylog.getCreateDate());
                    actionAuditDTO.setTicketAssignId(entitylog.getTicketTypeId());

                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.MAPPING_EXPIRE_ERROR.value);
                    ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                    actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldMapErroreEntity, entitylog, entitylog.getTicketTypeId(), actionType);
                }
                return true;
            } else {
                return null;
            }

        } catch (Exception ex) {

            log.error("Has error MapErrorCauseServiceImpl save", ex);
        }
        return null;
    }
}