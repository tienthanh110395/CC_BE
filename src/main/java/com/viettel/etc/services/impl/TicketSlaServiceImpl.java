package com.viettel.etc.services.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketSlaRepository;
import com.viettel.etc.repositories.TicketTypeRepository;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import com.viettel.etc.repositories.tables.entities.TicketSlaEntity;
import com.viettel.etc.repositories.tables.entities.TicketTypeEntity;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketCateConfigService;
import com.viettel.etc.services.TicketSlaService;
import com.viettel.etc.services.tables.TicketCateConfigServiceJPA;
import com.viettel.etc.services.tables.TicketSlaServiceJPA;
import com.viettel.etc.services.tables.TicketTypeServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Autogen class:
 *
 * @author ToolGen
 * @date Wed Jan 26 09:59:24 ICT 2022
 */
@Service
public class TicketSlaServiceImpl implements TicketSlaService {

    private final int ID = 0;
    private final int NAME = 1;
    private final int STT = 0;
    private final int GROUP_CODE = 1;
    private final int GROUP_NAME = 2;
    private final int GENNER_CODE = 3;
    private final int GENNER_NAME = 4;
    private final int TYPE_CODE = 5;
    private final int TYPE_NAME = 6;
    private final int TICKET_TEMPLATE = 7;
    private final int LEVEL_CATE_NAME = 8;
    private final int PROCESS_TIME = 9;
    private final int COMBINE_TIME_L1 = 10;
    private final int COMBINE_TIME_L2 = 11;
    private final int RE_TICKET_TIME = 12;
    private final int RESULT = 13;
    private final int DESC = 14;

    private static final Logger log = Logger.getLogger(TicketExpireCauseServiceImpl.class);

    @Autowired
    private TicketSlaRepository ticketSlaRepository;

    @Autowired
    TicketSlaServiceJPA ticketSlaServiceJPA;

    @Autowired
    TicketCateConfigService ticketCateConfigService;

    @Autowired
    TicketCateConfigServiceJPA ticketCateConfigServiceJPA;

    @Autowired
    TicketTypeServiceJPA ticketTypeServiceJPA;

    @Autowired
    TicketTypeRepository ticketTypeRepository;

    @Autowired
    ActionAuditService actionAuditService;


    /**
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketSla(TicketSLADTO itemParamsEntity) {
        ResultSelectEntity dataResult = ticketSlaRepository.getTicketSla(itemParamsEntity);
        return dataResult;
    }

    /**
     * Lấy chi tiết sla
     *
     * @param ticketTypeId
     * @return
     */
    @Override
    public Object getTicketSlaDetail(Long ticketTypeId, Authentication authentication) {
        return ticketSlaRepository.getTicketSlaDetail(ticketTypeId);
    }

    @Override
    public Object getListDataTicketSLA(Authentication authentication, TicketSlaSearchDTO params) {

        log.info("TicketSlaServiceImpl start search ticket sla ");
        return ticketSlaRepository.getListTicketSLA(authentication, params);
    }

    @Override
    public Object createOrUpdate(TicketSLANewDTO params, Authentication authentication) {
        log.info("TicketSlaServiceImpl start createOrUpdate");

        try {
            String userNameLogin = FnCommon.getUserLogin(authentication);

            List<TicketSlaEntity> lstDataSave = new ArrayList<>();

            List<TicketSlaEntity> lstDataOld = new ArrayList<>();

            TicketTypeEntity ticketType;

            ActionAuditDTO logsUpdate = new ActionAuditDTO();

            ActionAuditEntity actionAuditEntity = new ActionAuditEntity();

            // Nếu là cập nhật
            if (params.getTicketTypeId() != null) {
                ticketType = ticketTypeServiceJPA.findById(params.getTicketTypeId()).get();
                TicketTypeEntity ticketGroupName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                logsUpdate.setDescription(ticketGroupName.getName() + "/" + ticketType.getName());
                List<TicketSlaEntity> lstDataTemp = ticketSlaServiceJPA.findByTicketTypeId(params.getTicketTypeId());
                lstDataOld = FnCommon.clone(lstDataTemp);
                // update
                List<TicketLevelCateDTO> lstTimeConfig = params.getLstDataTicketTimeConfig();
                if (!lstTimeConfig.isEmpty()) {
                    for (TicketLevelCateDTO sla : params.getLstDataTicketTimeConfig()) {
                        if (sla.getTicketSlaId() != null) {
                            //check su thay doi
                            TicketSlaEntity oldTemp = ticketSlaServiceJPA.getOne(sla.getTicketSlaId());
                            TicketSlaEntity slaOld = (TicketSlaEntity) oldTemp.clone();
                            boolean valueChange = false;
                            if (slaOld.getProcessTime() != sla.getTicketTimeFull()) {
                                slaOld.setProcessTime(sla.getTicketTimeFull());
                                valueChange = true;
                            }
                            if (slaOld.getCombineTimeL1() != sla.getTicketTimeLv1()) {
                                slaOld.setCombineTimeL1(sla.getTicketTimeLv1());
                                valueChange = true;
                            }
                            if (slaOld.getCombineTimeL2() != sla.getTicketTimeLv2()) {
                                slaOld.setCombineTimeL2(sla.getTicketTimeLv2());
                                valueChange = true;
                            }
                            if (slaOld.getReTicketTime() != sla.getTicketRetime()) {
                                slaOld.setReTicketTime(sla.getTicketRetime());
                                valueChange = true;
                            }
                            if (valueChange) {
                                slaOld.setUpdateDate(new Date(System.currentTimeMillis()));
                                slaOld.setUpdateUser(userNameLogin);
                                lstDataSave.add(slaOld);
                            } else {
                                slaOld.setReceptionTimeFrom(params.getReceptionTimeFrom());
                                slaOld.setReceptionTimeTo(params.getReceptionTimeTo());
                                slaOld.setUpdateDate(new Date(System.currentTimeMillis()));
                                slaOld.setUpdateUser(userNameLogin);
                                lstDataSave.add(slaOld);
                            }
                        } else {
                            lstDataSave.add(initTicketSlaData(userNameLogin, params.getTicketTypeId(), params.getReceptionTimeFrom(), params.getReceptionTimeTo(), sla));
                        }
                    }
                    logsUpdate.setCreateDate(new Date(System.currentTimeMillis()));
                    logsUpdate.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_PROCESSING_TIME_CONFIG.value);
                    logsUpdate.setStatus(1L);
                    //save logs
                    actionAuditEntity = actionAuditService.saveActAudit(authentication, logsUpdate);
                }
            } else {
                if (!params.getLstTicketType().isEmpty() && !params.getLstDataTicketTimeConfig().isEmpty()) {
                    for (Long ticketTypeId : params.getLstTicketType()) {
                        logsUpdate = new ActionAuditDTO();
                        for (TicketLevelCateDTO item : params.getLstDataTicketTimeConfig()) {
                            lstDataSave.add(initTicketSlaData(userNameLogin, ticketTypeId, params.getReceptionTimeFrom(), params.getReceptionTimeTo(), item));
                        }
                        ticketType = ticketTypeServiceJPA.findById(ticketTypeId).get();
                        TicketTypeEntity ticketGroupName = ticketTypeServiceJPA.findById(ticketType.getParentId()).get();
                        logsUpdate.setDescription(ticketGroupName.getName() + "/" + ticketType.getName());
                        logsUpdate.setCreateDate(new Date(System.currentTimeMillis()));
                        logsUpdate.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_PROCESSING_TIME_CONFIG.value);
                        logsUpdate.setStatus(1L);
                        //save logs
                        actionAuditEntity = actionAuditService.saveActAudit(authentication, logsUpdate);
                    }
                }
            }

            //save data
            ticketSlaServiceJPA.saveAll(lstDataSave);

            //save log detail
            for (TicketSlaEntity slaNew : lstDataSave) {
                if (lstDataOld.isEmpty()) {
                    actionAuditService.saveAllActAuditDetail(actionAuditEntity.getActionAuditId(), new TicketSlaEntity(), slaNew, slaNew.getTicketSlaId(), ActionAuditDetailEntity.ActionName.INSERT.value);
                } else {
                    TicketSlaEntity slaOld = lstDataOld.stream()
                            .filter(x -> slaNew.getTicketSlaId().equals(x.getTicketSlaId()))
                            .findAny()
                            .orElse(null);
                    if (slaOld != null) {
                        actionAuditService.saveAllActAuditDetail(actionAuditEntity.getActionAuditId(), slaOld, slaNew, slaNew.getTicketSlaId(), ActionAuditDetailEntity.ActionName.UPDATE.value);
                    } else {
                        actionAuditService.saveAllActAuditDetail(actionAuditEntity.getActionAuditId(), new TicketSlaEntity(), slaNew, slaNew.getTicketSlaId(), ActionAuditDetailEntity.ActionName.INSERT.value);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketSlaServiceImpl createUpdate", ex);
        }
        return false;
    }

    private TicketSlaEntity initTicketSlaData(String userName, Long ticketTypeId, String receptionTimeFrom, String receptionTimeTo, TicketLevelCateDTO dto) {
        TicketSlaEntity entity = new TicketSlaEntity();
        entity.setSla(1L);
        entity.setStatus(1L);
        entity.setCreateUser(userName);
        entity.setCreateDate(new Date(System.currentTimeMillis()));
        entity.setUpdateUser(userName);
        entity.setUpdateDate(new Date(System.currentTimeMillis()));
        entity.setTicketTypeId(ticketTypeId);
        entity.setProcessTime(dto.getTicketTimeFull());
        entity.setCombineTimeL1(dto.getTicketTimeLv1());
        entity.setCombineTimeL2(dto.getTicketTimeLv2());
        entity.setReTicketTime(dto.getTicketRetime());
        entity.setPriorityId(dto.getTicketLevelCateId());
        entity.setReceptionTimeFrom(receptionTimeFrom);
        entity.setReceptionTimeTo(receptionTimeTo);

        return entity;
    }

    @Override
    @Transactional
    public Boolean doDeleteData(Long ticketTypeId, Authentication authentication) {
        log.info("TicketSlaServiceImpl start onDelete ");
        try {
            List<TicketSlaEntity> ticketSlaEntity = ticketSlaServiceJPA.findByTicketTypeId(ticketTypeId);
            if (!ticketSlaEntity.isEmpty()) {
                ticketSlaServiceJPA.onDelete(ticketTypeId);
                /* Luu log */
                for (TicketSlaEntity entitylog : ticketSlaEntity) {
                    String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
                    ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                    actionAuditDTO.setTicketStatus(entitylog.getStatus());
                    actionAuditDTO.setCreateDate(entitylog.getCreateDate());
                    actionAuditDTO.setTicketAssignId(entitylog.getTicketSlaId());
                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_PROCESSING_TIME_CONFIG.value);
                    ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                    actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), entitylog, null, ticketTypeId, actionType);
                }
                return true;
            }
        } catch (Exception ex) {
            log.error("Has error TicketSlaServiceImpl onDelete", ex);
        }
        return false;
    }

    @Override
    public Object getDataDetail(Long ticketTypeId, TicketSLANewDTO params) {
        log.info("TicketSlaServiceImpl start getDataDetail ");
        return ticketSlaRepository.getDataDetailById(ticketTypeId, params);
    }

    @Override
    public void exportTicketSla(TicketSlaSearchDTO params, HttpServletResponse response) {
        log.info("TicketSlaServiceImpl start exportImpactLog ");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("template" + File.separator
                + "export" + File.separator + "DANH_SACH_CAU_HINH_TGXL.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(is)) {
            List<TicketSLANewDTO> resultList = (List<TicketSLANewDTO>) ticketSlaRepository.exportImpact(params).getListData();
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowIndex = 3, no = 1;
            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCellStyle style = row.getCell(0).getCellStyle();
            for (TicketSLANewDTO item : resultList) {
                row = sheet.createRow(rowIndex++);
                createCell(style, row, 0, no++);
                createCell(style, row, 1, item.getTicketGroupCode());
                createCell(style, row, 2, item.getTicketGroupName());
                createCell(style, row, 3, item.getTicketGroupStatus());
                createCell(style, row, 4, item.getTicketGenreCode());
                createCell(style, row, 5, item.getTicketGenreName());
                createCell(style, row, 6, item.getTicketGenreStatus());
                createCell(style, row, 7, item.getTicketTypeCode());
                createCell(style, row, 8, item.getTicketTypeName());
                createCell(style, row, 9, item.getTicketTypeStatus());
                createCell(style, row, 10, item.getTicketTemplate());
                createCell(style, row, 11, item.getPriorityCode());
                createCell(style, row, 12, item.getPriorityName());
                createCell(style, row, 13, item.getReceptionTimeFrom() + " - " + item.getReceptionTimeTo());
                createCell(style, row, 14, item.getProcessTime());
                createCell(style, row, 15, item.getCombineTimeL1());
                createCell(style, row, 16, item.getCombineTimeL2());
                createCell(style, row, 17, item.getReTicketTime());
                createCell(style, row, 18, item.getCreateUser());
                createCell(style, row, 19, FnCommon.convertDateToString(item.getCreateDate(), true));
                createCell(style, row, 20, item.getUpdateUser());
                createCell(style, row, 21, FnCommon.convertDateToString(item.getUpdateDate(), true));
            }
            ByteArrayOutputStream os = new org.apache.commons.io.output.ByteArrayOutputStream();
            wb.write(os);
            FnCommon.responseFile(response, os.toByteArray(), "DANH_SACH_CAU_HINH_TGXL" + System.currentTimeMillis() + ".xlsx");
            log.info("TicketSlaServiceImpl end export ");
        } catch (Exception e) {
            log.error("Has Error Export export: ", e);
        }
    }

    /**
     * Tai file template Cau hinh thoi gian xu ly
     *
     * @param authentication Ma xac thuc
     * @return duong dan tai file ket qua
     */
    @Override
    public void downloadServicePlanTemplate(Authentication authentication, HttpServletResponse response) {
        log.info("TicketSlaServiceImpl start exportImpactLog ");
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("template" + File.separator + "export" + File.separator + "FORM_IMPORT_CAU_HINH.xlsx");
             XSSFWorkbook wb = new XSSFWorkbook(is)) {
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowIndex = 2;
            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCellStyle style = row.getCell(0).getCellStyle();
            ByteArrayOutputStream os = new org.apache.commons.io.output.ByteArrayOutputStream();
            wb.write(os);
            FnCommon.responseFile(response, os.toByteArray(), "FORM_IMPORT_CAU_HINH.xlsx");
            log.info("TicketSlaServiceImpl end Template ");
        } catch (Exception e) {
            log.error("Has Error Export Template: ", e);
        }
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
        if (!FnCommon.isNullOrBlank(row.getCell(GROUP_CODE)) && row.getCell(GROUP_CODE).getCellType() != CellType.STRING) {
            return "Mã nhóm phản ánh";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(GROUP_NAME)) && row.getCell(GROUP_NAME).getCellType() != CellType.STRING) {
            return "Tên nhóm phản ánh";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(GENNER_CODE)) && row.getCell(GENNER_CODE).getCellType() != CellType.STRING) {
            return "Mã thể loại";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(GENNER_NAME)) && row.getCell(GENNER_NAME).getCellType() != CellType.STRING) {
            return "Tên thể loại";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(TYPE_CODE)) && row.getCell(TYPE_CODE).getCellType() != CellType.STRING) {
            return "Mã loại phản ánh";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(TYPE_NAME)) && row.getCell(TYPE_NAME).getCellType() != CellType.STRING) {
            return "Tên loại phản ánh";
        }
//        if (!FnCommon.isNullOrBlank(row.getCell(TICKET_TEMPLATE)) && row.getCell(TICKET_TEMPLATE).getCellType() != CellType.STRING) {
//            return "Form nhập";
//        }
        if (!FnCommon.isNullOrBlank(row.getCell(LEVEL_CATE_NAME)) && row.getCell(LEVEL_CATE_NAME).getCellType() != CellType.STRING) {
            return "Mức độ ưu tiên";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(PROCESS_TIME)) && row.getCell(PROCESS_TIME).getCellType() != CellType.STRING) {
            return "Thời gian xử lý toàn trình";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(COMBINE_TIME_L1)) && row.getCell(COMBINE_TIME_L1).getCellType() != CellType.STRING) {
            return "Thời gian phối hợp cấp 1";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(COMBINE_TIME_L2)) && row.getCell(COMBINE_TIME_L2).getCellType() != CellType.STRING) {
            return "Thời gian phối hợp cấp 2";
        }
        if (!FnCommon.isNullOrBlank(row.getCell(RE_TICKET_TIME)) && row.getCell(RE_TICKET_TIME).getCellType() != CellType.STRING) {
            return "Thời gian xuất lại";
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

    @Override
    public ResponseEntity<?> importTicketProcessTime(Authentication authentication, MultipartFile fileImport) throws IOException {
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
            if (sheet.getRow(3) != null && sheet.getRow(3).getLastCellNum() < 13) {
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
                    if (FnCommon.isNullOrBlank(row.getCell(GROUP_CODE)) || FnCommon.isNullOrBlank(row.getCell(GROUP_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã nhóm phản ánh không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(GROUP_NAME)) || FnCommon.isNullOrBlank(row.getCell(GROUP_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên nhóm phản ánh không được để trống");
                        failedLines++;
                        continue;
                    }

                    if (FnCommon.isNullOrBlank(row.getCell(GENNER_CODE)) || FnCommon.isNullOrBlank(row.getCell(GENNER_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã thể loại phản ánh không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(GENNER_NAME)) || FnCommon.isNullOrBlank(row.getCell(GENNER_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên thể loại phản ánh không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(TYPE_CODE)) || FnCommon.isNullOrBlank(row.getCell(TYPE_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã loại phản ánh không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(TYPE_NAME)) || FnCommon.isNullOrBlank(row.getCell(TYPE_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Tên loại phản ánh không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(TYPE_CODE)) || FnCommon.isNullOrBlank(row.getCell(TYPE_CODE).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mã loại phản ánh không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(LEVEL_CATE_NAME)) || FnCommon.isNullOrBlank(row.getCell(LEVEL_CATE_NAME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Mức độ ưu tiên không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(PROCESS_TIME)) || FnCommon.isNullOrBlank(row.getCell(PROCESS_TIME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Thời gian xử lý toàn trình không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(COMBINE_TIME_L1)) || FnCommon.isNullOrBlank(row.getCell(COMBINE_TIME_L1).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Thời gian phối hợp cấp 1 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(COMBINE_TIME_L2)) || FnCommon.isNullOrBlank(row.getCell(COMBINE_TIME_L2).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Thời gian phối hợp cấp 2 không được để trống");
                        failedLines++;
                        continue;
                    }
                    if (FnCommon.isNullOrBlank(row.getCell(RE_TICKET_TIME)) || FnCommon.isNullOrBlank(row.getCell(RE_TICKET_TIME).getStringCellValue())) {
                        resultImport(row.createCell(RESULT), row.createCell(DESC), "Failed", "Thời gian xuất lại toàn trình không được để trống");
                        failedLines++;
                        continue;
                    }

                    List<String> lstTicketTypeGroupEntity = ticketTypeRepository.getListTicketGroupImport(1L);
                    List<String> lstTicketTypeGennerEntity = ticketTypeRepository.getListTicketGroupImport(2L);
                    List<String> lstTicketTypeEntity = ticketTypeRepository.getListTicketGroupImport(3L);
                    for (String item : lstTicketTypeGroupEntity) {
                        if (!row.getCell(PROCESS_TIME).equals(item)) {

                        } else {

                        }
                    }
                }
            }
            boolean validateHeaderCell = FnCommon.validateHeaderCell(sheet, 3, 12, headerContent());
//            if (validateHeaderCell) {
            org.apache.commons.io.output.ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            bytes = os.toByteArray();
//            } else {
//                return new ResponseEntity<>(FnCommon.responseToClient(new EtcException("common.validate.err.data.excel")), HttpStatus.NOT_FOUND);
//            }
        } catch (Exception e) {
            log.error("Import noi dung khong thanh cong ", e);
        }
//        return returnFileExcel(bytes, fileName, hasError);
        return null;
    }

    public ResponseEntity<?> returnFileExcel(byte[] data, String fileName, StringBuilder hasError) throws UnsupportedEncodingException {
        if (fileName.endsWith(".xlsx")) {
            fileName = fileName.substring(0, fileName.length() - 5);
            fileName = FnCommon.replaceFileName(fileName) + "-result.xlsx";
        }
        if (fileName.endsWith(".xls")) {
            fileName = fileName.substring(0, fileName.length() - 4);
            fileName = FnCommon.replaceFileName(fileName) + "-result.xls";
        }
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Access-Control-Expose-Headers", "Content-Disposition");
        if (!FnCommon.isNullOrEmpty(hasError.toString())) {
            return ResponseEntity.badRequest().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        }
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    /**
     * Check header
     *
     * @param sheet             sheet lua chon
     * @param indexHeaderRow    chi muc hang
     * @param indexCheckInvalid chi muc cot kiem tra du lieu hop le
     * @param indexReason       chi muc cot ghi ly do
     * @param headerStyle       set style
     */
    private void addHeaderContent(Sheet sheet, int indexHeaderRow, int indexCheckInvalid, int indexReason, CellStyle headerStyle) {
        if (sheet.getLastRowNum() >= 4) {
            Cell cell16 = sheet.getRow(indexHeaderRow).createCell(indexCheckInvalid);
            cell16.setCellStyle(headerStyle);
            cell16.setCellValue("Kiểm tra hợp lệ");
            Cell cell17 = sheet.getRow(indexHeaderRow).createCell(indexReason);
            cell17.setCellStyle(headerStyle);
            cell17.setCellValue("Lý do");
        }
    }

    /**
     * Import du lieu tu file excel
     * <p>
     * Xu ly du lieu doanh thu chia se trong file excel
     *
     * @param workbook File excel
     * @return
     */
    public Map<String, List<Map<String, String>>> handleRevenueShareExcel(Workbook workbook) {
        List<Map<String, String>> result = new ArrayList();
        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        for (int i = 4; i < lastRow; i++) {
            Map<String, String> element = new HashMap<>();
            Row row = sheet.getRow(i);
            Cell cellServicePlan = row.getCell(13);
            Cell cellBot = row.getCell(14);
            Cell cellRevenueShare = row.getCell(15);
            if (!FnCommon.isNullOrEmpty(FnCommon.getStringValue(cellServicePlan)) &&
                    !FnCommon.isNullOrEmpty(FnCommon.getStringValue(cellBot))
            ) {
                element.put("servicePlanCode", FnCommon.getStringValue(cellServicePlan).trim());
                element.put("botName", FnCommon.getStringValue(cellBot));
                String botId = FnCommon.getIdFromString(FnCommon.getStringValue(cellBot).trim());
                element.put("botId", botId);
                if (!FnCommon.isNullOrEmpty(FnCommon.getStringValue(cellRevenueShare))) {
                    element.put("revenueShare", FnCommon.getStringValue(cellRevenueShare).trim());
                } else {
                    element.put("revenueShare", "");
                }
                result.add(element);
            }
        }
        return result.stream().collect(Collectors.groupingBy(value -> value.get("servicePlanCode")));
    }

    /**
     * check content row has empty or null
     *
     * @param row      Du lieu ma row can kiem tra
     * @param fromCell Cot bat dau thuc hien
     * @param toCell   Cot Ket thuc thuc hien
     * @return Chuoi kiem tra
     */
    private boolean hasEmptyOrNullRow(Row row, int fromCell, int toCell) {
        if (row != null) {
            for (int i = fromCell; i <= toCell; i++) {
                if (!FnCommon.isNullOrEmpty(FnCommon.getStringValue(row.getCell(i)))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Noi dung phan header
     *
     * @return Chuoi string header cua sheet
     */
    private String headerContent() {
        StringBuilder headerContent = new StringBuilder();
        headerContent.append("STT").append("servicePlanCode").append("servicePlanTypeID").append("stationId")
                .append("stageId").append("vehicleGroupId").append("fee").append("ocsCode")
                .append("startDate").append("endDate").append("autoRenew").append("useDay");
        return headerContent.toString();

    }

    @Override
    public Object updateReception(TicketSLANewDTO params, Authentication authentication) {
        log.info("TicketSlaServiceImpl start createOrUpdateUpdate");
        try {
            if (params.getReceptionTimeFrom() != null && params.getReceptionTimeTo() != null) {
                ticketSlaRepository.updateReptionTime(params.getReceptionTimeFrom(), params.getReceptionTimeTo());
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketSlaServiceImpl createUpdate", ex);
        }
        return false;
    }

    public ByteArrayResource importTemplateExceptionList(Authentication authentication) {
        String pathTemplate = "template" + File.separator + "FORM_IMPORT_CAU_HINH.xlsx";
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(pathTemplate);
             Workbook workbook = WorkbookFactory.create(in)) {
            // get data categories
            /*Map<String, String> params = new HashMap<>();
            params.put("booCode", "BOO2");
            List<LinkedHashMap<?, ?>> stages = stageService.findAllStagesBOO2(FnCommon.getStringToken(authentication), params);
            List<LinkedHashMap<?, ?>> stations = stationService.findAllStationsBOO2(FnCommon.getStringToken(authentication), params);
            PromotionDTO promotionParams = new PromotionDTO();
            promotionParams.setIsActive(true);
            promotionParams.setPromotionLevel(Constants.PROMOTION_NL);
            ResultSelectEntity dataResult = (ResultSelectEntity) promotionService.getPromotions(promotionParams);
            List<PromotionDTO> promotions = (List<PromotionDTO>) dataResult.getListData();

            //danh muc chuong trinh khuyen mai
            Sheet sheetPromotion = workbook.getSheetAt(1);
            for (int i = 0; i < promotions.size(); i++) {
                Row row = sheetPromotion.createRow(i + 1);
                Cell cellId = row.createCell(ID, CellType.NUMERIC);
                cellId.setCellValue(promotions.get(i).getId());
                Cell cellName = row.createCell(NAME, CellType.STRING);
                cellName.setCellValue(promotions.get(i).getPromotionName());
            }

            //danh muc tram
            sheetPromotion = workbook.getSheetAt(2);
            for (int i = 0; i < stations.size(); i++) {
                LinkedHashMap<?, ?> station = stations.get(i);
                Row row = sheetPromotion.createRow(i + 1);
                Cell cellId = row.createCell(ID, CellType.NUMERIC);
                cellId.setCellValue(Long.parseLong(station.get("id").toString()));
                Cell cellName = row.createCell(NAME, CellType.STRING);
                cellName.setCellValue(station.get("name").toString());
            }

            //danh muc doan
            sheetPromotion = workbook.getSheetAt(3);
            for (int i = 0; i < stages.size(); i++) {
                LinkedHashMap<?, ?> stage = stages.get(i);
                Row row = sheetPromotion.createRow(i + 1);
                Cell cellId = row.createCell(ID, CellType.NUMERIC);
                cellId.setCellValue(Long.parseLong(stage.get("id").toString()));
                Cell cellName = row.createCell(NAME, CellType.STRING);
                cellName.setCellValue(stage.get("name").toString());
            }*/

            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayResource(out.toByteArray());
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }
}