package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketExtentDTO;
import com.viettel.etc.repositories.TicketAssignRepository;
import com.viettel.etc.repositories.TicketExtentRepository;
import com.viettel.etc.repositories.tables.TicketRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketSmsMailPushRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketExtentService;
import com.viettel.etc.services.tables.TicketExtentServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.ExcellDataEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellHeaderEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.viettel.etc.utils.Constants.COMMON_DATE_TIME_FORMAT;

/**
 * Autogen class: 
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:32:08 ICT 2022
 */
@Service
public class TicketExtentServiceImpl implements TicketExtentService{

    private static final Logger LOGGER = Logger.getLogger(TicketExtentServiceImpl.class);

    @Autowired
    private TicketExtentRepository ticketExtentRepository;

    @Autowired
    TicketSiteUserRepositoryJPA ticketSiteUserRepositoryJPA;

    @Value("${sms.user.receive.ticket-extent}")
    String smsReceiveTicket;

    @Autowired
    TicketServiceJPA ticketServiceJPA;

    @Autowired
    TicketRepositoryJPA ticketRepositoryJPA;

    @Autowired
    TicketExtentServiceJPA ticketExtentServiceJPA;

    @Autowired
    SMSServiceImpl smsService;

    @Autowired
    TicketSmsMailPushRepositoryJPA ticketSmsMailPushRepositoryJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    private TicketAssignRepository ticketAssignRepository;


    /**
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketExtent(TicketExtentDTO itemParamsEntity, Authentication authentication) {
        ResultSelectEntity dataResult = ticketExtentRepository.getTicketExtent(itemParamsEntity, authentication);
        return dataResult;
    }

    /**
     * Xin gia han xu ly
     *
     * @param authentication
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object insertTicketExtent(TicketExtentDTO itemParamsEntity, Authentication authentication) {
        TicketExtentEntity ticketExtentOld = ticketExtentServiceJPA.getByTicketIdAndStatus(itemParamsEntity.getTicketId(), TicketExtentEntity.Status.NOT_APPROVE.value);
        if(ticketExtentOld != null) {
            throw new EtcException("Ticket đang ở trạng thái chờ phê duyệt");
        }
        TicketExtentEntity ticketExtent = new TicketExtentEntity();
        TicketEntity ticketEntity = ticketServiceJPA.getOne(itemParamsEntity.getTicketId());
        Date slaDate = ticketEntity.getSlaDate() != null ? new java.sql.Date(ticketEntity.getSlaDate().getTime()) : null;
        Date requestExtentDate = itemParamsEntity.getRequestExtentDate() != null ? new java.sql.Date(itemParamsEntity.getRequestExtentDate().getTime()) : null;
        if(slaDate != null && slaDate.compareTo(requestExtentDate) > 0) {
            throw new EtcException("Ngày xin gia hạn không được nhỏ hơn hạn xử lý");
        }

        List<TicketSmsMailPushEntity> ticketSmsMailPushEntities = new ArrayList<>();
        String processUser = itemParamsEntity.getManagerUserName();
        if (itemParamsEntity.getIsSms() != null && itemParamsEntity.getIsSms()) {
            TicketSiteUserEntity ticketSiteUserEntity = ticketSiteUserRepositoryJPA.findByUserNameIgnoreCase(processUser);
            if (ticketSiteUserEntity != null && !FnCommon.isNullOrEmpty(ticketSiteUserEntity.getPhone())) {
                String msg = String.format(smsReceiveTicket, itemParamsEntity.getManagerUserName(), itemParamsEntity.getTicketId());
                long status = smsService.sendSMS(ticketSiteUserEntity.getPhone(), msg, authentication);
                TicketSmsMailPushEntity ticketSmsMailPushEntity = itemParamsEntity.toTicketSmsMailPushEntity(itemParamsEntity.getTicketId(), msg, ticketSiteUserEntity.getPhone());
                ticketSmsMailPushEntity.setTicketSmsMailPushId(ticketSmsMailPushRepositoryJPA.getNextValSequenceSerial());
                ticketSmsMailPushEntity.setStatus(status);
                ticketSmsMailPushEntity.setErrorMessage(status == 1L ? "" : "Send sms failed!");
                ticketSmsMailPushEntities.add(ticketSmsMailPushEntity);
            }
        }
        if (!ticketSmsMailPushEntities.isEmpty()) {
            ticketSmsMailPushRepositoryJPA.saveAll(ticketSmsMailPushEntities);
        }

        ticketExtent.setTicketId(itemParamsEntity.getTicketId());
        ticketExtent.setExtentReasonId(itemParamsEntity.getExtentReasonId());
        ticketExtent.setRequestExtentDate(new java.sql.Date(itemParamsEntity.getRequestExtentDate().getTime()));
        ticketExtent.setManagerUserName(itemParamsEntity.getManagerUserName());
        ticketExtent.setManagerPhone(StringUtils.trim(itemParamsEntity.getManagerPhone()));
        ticketExtent.setStatus(TicketExtentEntity.Status.NOT_APPROVE.value);
        ticketExtent.setExtentDate(new java.sql.Date(System.currentTimeMillis()));
        ticketExtent.setCreateUser(FnCommon.getUserLogin(authentication));
        ticketExtent.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        TicketExtentEntity saveTicketExtent = ticketExtentServiceJPA.save(ticketExtent);

        //Lưu log
        String actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setTicketId(itemParamsEntity.getTicketId());
        actionAuditDTO.setStatus(itemParamsEntity.getStatus());
        actionAuditService.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, ticketExtent, saveTicketExtent, ticketExtent.getTicketId(), actionName);

        return saveTicketExtent;
    }

    /**
     * Lấy chi tiết 1 phản ánh cần gia hạn
     *
     * @param ticketId
     * @return
     */
    @Override
    public Object getDetailTicketExtent(Long ticketId) {
        TicketExtentEntity ticketExtentEntity = ticketExtentServiceJPA.getByTicketIdAndStatus(ticketId, TicketExtentEntity.Status.NOT_APPROVE.value);
        return new TicketExtentDTO(ticketExtentEntity);
    }

    /**
     * Sua phản ánh cần gia hạn
     *
     * @param authentication: thong tin nguoi dung
     * @param dataParams      params client
     * @param ticketId        params client
     * @return
     */
    @Override
    public Object updateTicketExtent(TicketExtentDTO dataParams, Long ticketId, Authentication authentication) throws EtcException {
        TicketExtentEntity ticketExtentEntity = ticketExtentServiceJPA.findByTicketId(ticketId);
        if (ticketExtentEntity == null) {
            throw new EtcException("Không có dữ liệu thỏa mãn");
        }
        //Lưu log
        String actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(dataParams.getActTypeId());
        actionAuditDTO.setTicketId(dataParams.getTicketId());
        actionAuditDTO.setStatus(dataParams.getStatus());
        actionAuditService.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, null, ticketExtentEntity, ticketExtentEntity.getTicketId(), actionName);

        ticketExtentEntity.setExtentReasonId(dataParams.getExtentReasonId());
        ticketExtentEntity.setRequestExtentDate(new java.sql.Date(dataParams.getRequestExtentDate().getTime()));
        ticketExtentEntity.setManagerUserName(dataParams.getManagerUserName());
        ticketExtentEntity.setManagerPhone(StringUtils.trim(dataParams.getManagerPhone()));
        ticketExtentEntity.setExtentDate(new java.sql.Date(System.currentTimeMillis()));
        TicketExtentEntity savedTicketExtents = ticketExtentServiceJPA.save(ticketExtentEntity);
        return savedTicketExtents;
    }

    /**
     * Phê duyệt phản ánh cần gia hạn
     *
     * @param authentication: thong tin nguoi dung
     * @return
     */
    @Override
    public List<TicketExtentEntity> approveTicketStatus(TicketExtentDTO dataParams, Authentication authentication) throws EtcException {
        List<Long> ticketIdList = dataParams.getTicketIds();
        List<TicketExtentEntity> ticketExtentEntityListUpdate = new ArrayList<>();
        for (Long ticketId : ticketIdList) {
            TicketExtentEntity ticketExtentEntity = ticketExtentServiceJPA.getByTicketIdAndStatus(ticketId, TicketExtentEntity.Status.NOT_APPROVE.value);

            //Phê duyệt phản ánh
            ticketExtentEntity.setStatus(TicketExtentEntity.Status.APPROVE.value);
            ticketExtentEntity.setApproveUserName(FnCommon.getIdUserLogin(authentication));
            ticketExtentEntity.setUpdateUser(FnCommon.getIdUserLogin(authentication));
            ticketExtentEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
            ticketExtentEntityListUpdate.add(ticketExtentEntity);

            TicketEntity ticketEntity = ticketServiceJPA.getOne(ticketId);
            ticketEntity.setSlaDate(ticketExtentEntity.getRequestExtentDate() != null ? new java.sql.Date(ticketExtentEntity.getRequestExtentDate().getTime()) : null);
            ticketRepositoryJPA.save(ticketEntity);

            //Lưu log
            String actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setActTypeId(dataParams.getActTypeId());
            actionAuditDTO.setTicketId(dataParams.getTicketId());
            actionAuditDTO.setStatus(dataParams.getStatus());
            actionAuditService.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, null, ticketExtentEntity, ticketExtentEntity.getTicketId(), actionName);
        }
        if(ticketExtentEntityListUpdate.size() > 0) {
            ticketExtentServiceJPA.saveAll(ticketExtentEntityListUpdate);
        }
        return ticketExtentEntityListUpdate;
    }

    /**
     * Từ chối gia hạn phản ánh
     *
     * @param authentication: thong tin nguoi dung
     * @param ticketId    params client
     * @return
     */
    @Override
    public Object rejectTicketStatus(TicketExtentDTO dataParams, Long ticketId, Authentication authentication) throws EtcException {
        TicketExtentEntity ticketExtentEntity = ticketExtentServiceJPA.getByTicketIdAndStatus(dataParams.getTicketId(), TicketExtentEntity.Status.NOT_APPROVE.value);
        if (ticketExtentEntity == null) {
            throw new EtcException("Không có dữ liệu thỏa mãn");
        }

        ticketExtentEntity.setTicketId(dataParams.getTicketId());
        ticketExtentEntity.setStatus(TicketExtentEntity.Status.REFUSE.value);
        ticketExtentEntity.setApproveReason(StringUtils.trim(dataParams.getApproveReason()));
        ticketExtentEntity.setUpdateUser(FnCommon.getIdUserLogin(authentication));
        ticketExtentServiceJPA.save(ticketExtentEntity);

        //Lưu log
        String actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(dataParams.getActTypeId());
        actionAuditDTO.setTicketId(dataParams.getTicketId());
        actionAuditDTO.setStatus(dataParams.getStatus());
        actionAuditService.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, null, ticketExtentEntity, ticketExtentEntity.getTicketId(), actionName);

        ModelMapper modelMapper = new ModelMapper();
        TicketExtentDTO obj = modelMapper.map(ticketExtentEntity, TicketExtentDTO.class);
        return obj;
    }

    /***
     * Xuat file excel yeu cau gia hạn phản ánh
     * @param authentication
     * @param dataParams
     * @throws IOException
     */
    @Override
    public Object exportTicketExtent(TicketExtentDTO dataParams, Authentication authentication) {
        try {
            String fileName = "Tra_cuu_ticket_extent";

            fileName += DateTimeFormatter.ofPattern("ddMMyyy hhmmss").format(LocalDateTime.now()) + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT);
            String date = formatter.format(new java.util.Date());
            String title = "Danh sách phản ánh cần gia hạn \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
            listHeader.add(new ExcellHeaderEntity("Mã phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Số hợp đồng"));
            listHeader.add(new ExcellHeaderEntity("Nhóm phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Thể loại"));
            listHeader.add(new ExcellHeaderEntity("Loại phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Ngày hết hạn"));
            listHeader.add(new ExcellHeaderEntity("Nhân viên xin gia hạn"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị phối hợp"));
            listHeader.add(new ExcellHeaderEntity("Ngày xin gia hạn"));
            listHeader.add(new ExcellHeaderEntity("Gia hạn đến ngày giờ"));
            listHeader.add(new ExcellHeaderEntity("Nội dung phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Trạng thái phê duyệt gia hạn"));
            listHeader.add(new ExcellHeaderEntity("Lý do từ chối"));
            sheetExport.setListHeader(listHeader);

            ExcellDataEntity excellDataEntity = new ExcellDataEntity();
            List<List<Object>> listData = new ArrayList<>();
            List<TicketExtentDTO> searchTicketExtentList = new ArrayList<>();
            ResultSelectEntity result = ticketExtentRepository.getTicketExtent(dataParams, authentication);
            if (result != null) {
                searchTicketExtentList = (List<TicketExtentDTO>) result.getListData();
            }
            int no = 1;

            //set data
            for (TicketExtentDTO getTicketExtent : searchTicketExtentList) {
                List<Object> objectList = new ArrayList<>();
                objectList.add(no);//STT
                objectList.add(getTicketExtent.getTicketId() != null ? getTicketExtent.getTicketId() : "");//Mã phản ánh
                objectList.add(getTicketExtent.getContractNo() != null ? getTicketExtent.getContractNo() : "");//Số hợp đồng
                objectList.add(getTicketExtent.getGroupPA() != null ? getTicketExtent.getGroupPA() : "");//Nhóm phản ánh
                objectList.add(getTicketExtent.getSubgroupPA() != null ? getTicketExtent.getSubgroupPA() : "");//Thể loại
                objectList.add(getTicketExtent.getDetailPA() != null ? getTicketExtent.getDetailPA() : "");//Loại phản ánh
                objectList.add(getTicketExtent.getDeadlineProcess() != null ? formatter.format(getTicketExtent.getDeadlineProcess()) : "");//Ngày hết hạn
                objectList.add(getTicketExtent.getManagerUserName() != null ? getTicketExtent.getManagerUserName() : "");//Nhân viên xin gia hạn
                objectList.add(getTicketExtent.getSiteName() != null ? getTicketExtent.getSiteName() : "");//Đơn vị phối hợp
                objectList.add(getTicketExtent.getExtentDate() != null ? formatter.format(getTicketExtent.getExtentDate()) : "");//Ngày xin gia hạn
                objectList.add(getTicketExtent.getRequestExtentDate() != null ? formatter.format(getTicketExtent.getRequestExtentDate()) : "");//Gia hạn đến ngày giờ
                objectList.add(getTicketExtent.getContentReceive() != null ? getTicketExtent.getContentReceive() : "");//Nội dung phản ánh
                String status = "";
                if (getTicketExtent.getStatus() != null) {
                    if ("0".equals(getTicketExtent.getStatus())) {
                        status = "Từ chối";
                    } else if ("1".equals(getTicketExtent.getStatus())) {
                        status = "Chưa duyệt";
                    } else {
                        status = "Đã duyệt";
                    }
                }
                objectList.add(status);//Trạng thái phản ánh phê duyệt
                objectList.add(getTicketExtent.getApproveReason() != null ? getTicketExtent.getApproveReason() : "");//Lý do từ chối
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
     * Xuat exel danh sach gia han xu ly phan anh
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    public Object exportTicketProcess(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        try {
            String fileName = "Tra_cuu_ticket_process";

            fileName += DateTimeFormatter.ofPattern("ddMMyyy hhmmss").format(LocalDateTime.now()) + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT);
            String date = formatter.format(new java.util.Date());
            String title = "Danh sách gia hạn xử lý phản ánh \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
            listHeader.add(new ExcellHeaderEntity("Mã phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Số hợp đồng"));
            listHeader.add(new ExcellHeaderEntity("Ngày tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Biển số xe"));
            listHeader.add(new ExcellHeaderEntity("Nội dung phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Trạng thái phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Hạn xử lý"));
            listHeader.add(new ExcellHeaderEntity("Người xử lý PA"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 1"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 2"));
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
                objectList.add(no);//STT
                objectList.add(ticketAssignDTO.getTicketId() != null ? ticketAssignDTO.getTicketId() : "");//Mã phản ánh
                objectList.add(ticketAssignDTO.getContractNo() != null ? ticketAssignDTO.getContractNo() : "");//Số hợp đồng
                objectList.add(ticketAssignDTO.getCreateDate() != null ? formatter.format(ticketAssignDTO.getCreateDate()) : "");//Ngày tiếp nhận
                objectList.add(ticketAssignDTO.getPlateNumber() != null ? ticketAssignDTO.getPlateNumber() : "");//Biển số xe
                objectList.add(ticketAssignDTO.getContentReceive() != null ? ticketAssignDTO.getContentReceive() : "");//Nội dung phản ánh
                String status = "";
                if (ticketAssignDTO.getTicketStatus() != null) {
                    if ("1".equals(ticketAssignDTO.getTicketStatus())) {
                        status = "Tạo mới";
                    }
                    else if ("2".equals(ticketAssignDTO.getTicketStatus())) {
                        status = "Tiếp nhận";
                    }
                    else if ("3".equals(ticketAssignDTO.getTicketStatus())) {
                        status = "Kết luận";
                    }
                    else if ("4".equals(ticketAssignDTO.getTicketStatus())) {
                        status = "Hoàn thành";
                    }
                    else if ("5".equals(ticketAssignDTO.getTicketStatus())) {
                        status = "Đóng";
                    }
                    else if ("6".equals(ticketAssignDTO.getTicketStatus())) {
                        status = "Từ chối";
                    }
                    else {
                        status = "Hủy";
                    }
                }
                objectList.add(status);//Trạng thái phản ánh
                objectList.add(ticketAssignDTO.getSlaDate() != null ? formatter.format(ticketAssignDTO.getSlaDate()) : "");//Hạn xử lý
                objectList.add(ticketAssignDTO.getStaffName() != null ? ticketAssignDTO.getStaffName() : "");//Người xử lý PA
                objectList.add(ticketAssignDTO.getTicketErrorCauseIdL1Name() != null ? ticketAssignDTO.getTicketErrorCauseIdL1Name() : "");//Nguyên nhân lỗi cấp 1
                objectList.add(ticketAssignDTO.getTicketErrorCauseIdL2Name() != null ? ticketAssignDTO.getTicketErrorCauseIdL2Name() : "");//Nguyên nhân lỗi cấp 2
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
}
