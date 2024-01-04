package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketAssignProcessIdDTO;
import com.viettel.etc.repositories.TicketAssignRepository;
import com.viettel.etc.repositories.tables.TicketAssignProcessRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketAssignRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketAssignService;
import com.viettel.etc.services.TicketAttachmentService;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.ExcellDataEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellHeaderEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.viettel.etc.utils.Constants.COMMON_DATE_TIME_FORMAT;

/**
 * Autogen class: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:44 ICT 2021
 */

@Service
public class TicketAssignServiceImpl implements TicketAssignService {

    private static final Logger LOGGER = Logger.getLogger(TicketAssignServiceImpl.class);

    @Autowired
    TicketAssignRepository ticketAssignRepository;

    @Autowired
    TicketAssignRepositoryJPA ticketAssignRepositoryJPA;

    @Autowired
    TicketAttachmentService ticketAttachmentService;

    @Autowired
    TicketAssignProcessRepositoryJPA ticketAssignProcessRepositoryJPA;

    @Autowired
    TicketRepositoryJPA ticketRepositoryJPA;

    @Autowired
    TicketAttachmentServiceJPA ticketAttachmentServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;


    /**
     * Lay du lieu yeu cau phoi hop xu ly
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketAssigns(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        return ticketAssignRepository.getTicketAssigns(itemParamsEntity, authentication);
    }

    /***
     * Xuat excel danh sach yeu cau ho tro
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object exportTicketAssigns(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        try {
            String fileName = "ticket-Assigns";

            fileName += System.currentTimeMillis() + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT);
            String date = formatter.format(new Date());
            String title = "Danh sách phản ánh \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
            listHeader.add(new ExcellHeaderEntity("Mã phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Biển số xe"));
            listHeader.add(new ExcellHeaderEntity("Số hợp đồng"));
            listHeader.add(new ExcellHeaderEntity("Nội dung phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 1"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 2"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 3"));
            listHeader.add(new ExcellHeaderEntity("Trạng thái tiếp nhận, xử lý"));

            listHeader.add(new ExcellHeaderEntity("Người tạo yêu cầu"));
            listHeader.add(new ExcellHeaderEntity("Ngày xử lý yêu cầu hỗ trợ"));
            listHeader.add(new ExcellHeaderEntity("Người xử lý yêu cầu phối hợp"));
            listHeader.add(new ExcellHeaderEntity("Tiến độ xử lý"));
            listHeader.add(new ExcellHeaderEntity("Thời gian xử lý yêu cầu (Giờ)"));

            listHeader.add(new ExcellHeaderEntity("Nội dung xử lý yêu cầu"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị xử lý yêu cầu phối hợp"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị phối hợp"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị chịu trách nhiệm"));
            sheetExport.setListHeader(listHeader);

            ExcellDataEntity excellDataEntity = new ExcellDataEntity();
            List<List<Object>> listData = new ArrayList<>();
            List<TicketAssignDTO> listTicketAssigns = new ArrayList<>();
            ResultSelectEntity dataResult = ticketAssignRepository.getTicketAssigns(itemParamsEntity, authentication);
            if (dataResult != null) {
                listTicketAssigns = (List<TicketAssignDTO>) dataResult.getListData();
            }
            int no = 1;

            //set data
            for (TicketAssignDTO ticketAssignDTO : listTicketAssigns) {
                List<Object> objectList = new ArrayList<>();
                objectList.add(no);
                objectList.add(ticketAssignDTO.getTicketId() != null ? ticketAssignDTO.getTicketId() : "");//Mã phản ánh
                objectList.add(ticketAssignDTO.getPlateNumber());//Biển số xe
                objectList.add(ticketAssignDTO.getContractNo());//Số hợp đồng
                objectList.add(ticketAssignDTO.getContentReceive());//Nội dung phản ánh
                objectList.add(ticketAssignDTO.getTicketErrorCauseIdL1Name());//Nguyên nhân lỗi cấp 1
                objectList.add(ticketAssignDTO.getTicketErrorCauseIdL2Name());//Nguyên nhân lỗi cấp 2
                objectList.add(ticketAssignDTO.getTicketErrorCauseIdL2Name());//Nguyên nhân lỗi cấp 3
                String status = "";
                switch (ticketAssignDTO.getTicketStatus()) {
                    case "1":
                        status = "Tạo mới";
                        break;
                    case "2":
                        status = "Đang xử lý";
                        break;
                    case "3":
                        status = "Kết luật xử lý";
                        break;
                    case "4":
                        status = "Hoàn thành xử lý";
                        break;
                    case "5":
                        status = "Đóng phản ánh";
                        break;
                    case "6":
                        status = "Từ chối";
                        break;
                    default:
                        status = "Hủy";
                        break;
                }
                objectList.add(status);//Trạng thái tiếp nhận, xử lý

                objectList.add(ticketAssignDTO.getFromUsername());//Người tạo yêu cầu
                if (ticketAssignDTO.getProcessTime() != null) {
                    objectList.add(FnCommon.convertDateToString(new java.sql.Date(ticketAssignDTO.getProcessTime().getTime()), true, "/"));//Ngày xử lý yêu cầu hỗ trợ
                } else {
                    objectList.add("");
                }
                objectList.add(ticketAssignDTO.getUserProcess());//Người xử lý yêu cầu phối hợp
                objectList.add(ticketAssignDTO.getTxtProcessTicket()); // Tiến độ xử lý
                objectList.add(ticketAssignDTO.getHourProcessTicket()); // Thời gian xử lý yêu cầu (Giờ)

                objectList.add(ticketAssignDTO.getProcessContent());//Nội dung xử lý yêu cầu
                objectList.add(ticketAssignDTO.getFromSiteName());//Đơn vị xử lý yêu cầu phối hợp
                objectList.add(ticketAssignDTO.getToSiteL2Name());//Đơn vị phối hợp
                objectList.add(ticketAssignDTO.getToSiteName());//Đơn vị chịu trách nhiệm
                listData.add(objectList);
                no++;
            }
            excellDataEntity.setListData(listData);
            sheetExport.setExcellDataEntity(excellDataEntity);
            //export excel
            if (!FunctionCommon.exportFileExcell(sheetExport, System.getProperty("user.dir") + File.separator + fileName, title)) {
                throw new IOException();
            }
            return fileName;
        } catch (Exception ex) {
            LOGGER.error("Export Fail", ex);
        }
        return null;
    }

    /***
     * Tiep nhan yeu cau ho tro
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object receiveTicketToHandle(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        TicketAssignEntity ticketAssignEntity = ticketAssignRepositoryJPA.getOne(itemParamsEntity.getTicketAssignId());
        ticketAssignEntity.setTicketStatus(TicketAssignEntity.TicketStatus.IN_PROGRESS.value);
        ticketAssignEntity.setUpdateUser(itemParamsEntity.getUserProcess());
        ticketAssignEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        return ticketAssignRepositoryJPA.save(ticketAssignEntity);
    }

    /***
     * Lay thong tin yeu cau ho tro cua 1 phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object getTicketAssignByTicketId(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        long ticketId = Long.parseLong(itemParamsEntity.getTicketId());
        Optional<TicketAssignEntity> ticketAssignEntity = ticketAssignRepositoryJPA.findByTicketId(ticketId);
        if (!ticketAssignEntity.isPresent()) {
            return null;
        }
        ModelMapper mapper = new ModelMapper();
        TicketAssignDTO ticketAssignDTO = mapper.map(ticketAssignEntity.get(), TicketAssignDTO.class);

        List<TicketAssignProcessEntity> ticketAssignProcessEntityList = ticketAssignProcessRepositoryJPA.findAllByTicketId(ticketId);
        ticketAssignDTO.setTicketAssignProcessEntitys(ticketAssignProcessEntityList);

        List<TicketAttachmentEntity> ticketAttachmentEntities = ticketAttachmentServiceJPA.getByTicketIdAndTypeAssign(ticketId);
        ticketAssignDTO.setTicketAttachmentEntityList(ticketAttachmentEntities);

        return ticketAssignDTO;
    }

    /***
     * Lay thong tin yeu cau ho tro
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object getTicketAssignById(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        long ticketId = Long.parseLong(itemParamsEntity.getTicketId());
        ResultSelectEntity dataResult = ticketAssignRepository.getTicketAssignById(itemParamsEntity, authentication);
        List<TicketAssignProcessIdDTO> ticketAssignProcessIdDTOS = (List<TicketAssignProcessIdDTO>) dataResult.getListData();
        if (ticketAssignProcessIdDTOS == null || ticketAssignProcessIdDTOS.isEmpty()) {
            return null;
        }
        List<TicketAttachmentEntity> ticketAttachmentEntities = ticketAttachmentServiceJPA.getTicketAttachment(ticketId,
                TicketAttachmentEntity.Type.TICKET_ASSIGN.value);
        ticketAssignProcessIdDTOS.get(0).setListAttachFiles(ticketAttachmentEntities);
        dataResult.setListData(ticketAssignProcessIdDTOS);
        return dataResult;
    }

    /***
     * Tạo moi yeu cau ho tro
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object saveTicketAssign(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        long ticketId = Long.parseLong(itemParamsEntity.getTicketId());
        TicketEntity ticketEntity = ticketRepositoryJPA.getOne(ticketId);
        if (ticketEntity.getStatus().equals(TicketStatusEntity.TicketStatus.NEW.value)) {
            throw new EtcException("Không thể tạo yêu cầu phối hợp khi trạng thái ticket là new !");
        }
        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.validateFileAttach(itemParamsEntity.getAttachmentFiles());
        }
        String userLogin = FnCommon.getUserLogin(authentication);
        ModelMapper mapper = new ModelMapper();
        TicketAssignEntity ticketAssignEntity = mapper.map(itemParamsEntity, TicketAssignEntity.class);
        ticketAssignEntity.setTicketStatus(TicketAssignEntity.TicketStatus.NEW.value);
        ticketAssignEntity.setAssignDate(new java.sql.Date(System.currentTimeMillis()));
        ticketAssignEntity.setUserProcess(userLogin);
        ticketAssignEntity.setCreateUser(userLogin);
        ticketAssignEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketAssignEntity.setToUsername("");
        TicketAssignEntity oldTicketAssignEntity = null;
        String actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketAssignId())) {
            oldTicketAssignEntity = ticketAssignRepositoryJPA.findById(ticketAssignEntity.getTicketAssignId()).get();
            actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
            ticketAssignEntity.setUpdateUser(userLogin);
            ticketAssignEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        }
        ticketAssignRepositoryJPA.save(ticketAssignEntity);
        /* Luu log */
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketId(ticketEntity.getTicketId());
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        actionAuditDTO.setTicketAssignId(ticketAssignEntity.getTicketAssignId());
        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketAssignEntity, ticketAssignEntity, ticketAssignEntity.getTicketAssignId(), actionName);


        TicketAssignProcessEntity ticketAssignProcessEntity = new TicketAssignProcessEntity();
        Optional<TicketAssignProcessEntity> ticketAssignProcessEntityOptional = ticketAssignProcessRepositoryJPA.findByTicketIdAndStatus(ticketAssignEntity.getTicketId(), TicketAssignProcessEntity.Status.NEW.value);
        ticketAssignProcessEntityOptional.ifPresent(assignProcessEntity -> ticketAssignProcessEntity.setTicketAssignProcessId(assignProcessEntity.getTicketAssignProcessId()));
        ticketAssignProcessEntity.setTicketAssignId(ticketAssignEntity.getTicketAssignId());
        ticketAssignProcessEntity.setTicketId(ticketAssignEntity.getTicketId());
        ticketAssignProcessEntity.setProcessContent(ticketAssignEntity.getAssignContent());
        ticketAssignProcessEntity.setProcessTime(new java.sql.Date(System.currentTimeMillis()));
        ticketAssignProcessEntity.setProcessResult("Tạo mới yêu cầu phối hợp");
        ticketAssignProcessEntity.setSiteId(ticketAssignEntity.getToSiteId());
        ticketAssignProcessEntity.setStaffCode(itemParamsEntity.getStaffCode() != null ? itemParamsEntity.getStaffCode() : "staffCode not exist");
        ticketAssignProcessEntity.setStaffName(itemParamsEntity.getStaffName());
        ticketAssignProcessEntity.setCreateUser(userLogin);
        ticketAssignProcessEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketAssignProcessEntity.setStatus(ticketAssignEntity.getTicketStatus());
        ticketAssignProcessRepositoryJPA.save(ticketAssignProcessEntity);

        /* Luu log */
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), null, ticketAssignProcessEntity, ticketAssignProcessEntity.getTicketAssignProcessId(), actionName);


        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.saveTicketAttachment(itemParamsEntity.getAttachmentFiles(),
                    ticketAssignEntity.getTicketAssignId(), authentication,
                    ticketAssignEntity.getTicketId(), TicketAttachmentEntity.Type.TICKET_ASSIGN.value, actionAuditEntity.getActionAuditId(), actionName);
        }
        return ticketAssignEntity;
    }

    /***
     * Xoa yeu cau ho tro moi tao
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object removeTicketAssignById(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        Optional<TicketAssignEntity> ticketAssignEntity = Optional.empty();
        if (!FnCommon.isNullObject(itemParamsEntity.getCreateUser()) && !FnCommon.isNullObject(itemParamsEntity.getTicketAssignId())) {
            ticketAssignEntity = ticketAssignRepositoryJPA.findByTicketAssignIdAndCreateUser(itemParamsEntity.getTicketAssignId(), itemParamsEntity.getCreateUser());
        }
        if (ticketAssignEntity.isPresent() && ticketAssignEntity.get().getTicketStatus().equals(TicketAssignEntity.TicketStatus.NEW.value)) {
            ticketAssignRepositoryJPA.deleteById(itemParamsEntity.getTicketAssignId());
            return "Delete ticket assign successfully !";
        } else {
            return "Only delete with status is 1";
        }
    }
}
