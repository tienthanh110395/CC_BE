package com.viettel.etc.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketCategoryRepository;
import com.viettel.etc.repositories.TicketRepository;
import com.viettel.etc.repositories.tables.TicketRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketStatusRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.*;
import com.viettel.etc.services.*;
import com.viettel.etc.services.tables.TicketAttachmentServiceJPA;
import com.viettel.etc.services.tables.TicketServiceJPA;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.exceptions.EtcException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static com.viettel.etc.utils.Constants.COMMON_DATE_TIME_FORMAT;

/**
 * Autogen class: Lop thao tac them moi ticket
 *
 * @author ToolGen
 * @date Tue Mar 02 14:49:46 ICT 2021
 */
@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = Logger.getLogger(TicketServiceImpl.class);
    private static final String COMMON_DATE_FORMAT = "dd/MM/yyyy";

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketAttachmentService ticketAttachmentService;

    @Autowired
    TicketRepositoryJPA ticketRepositoryJPA;

    @Autowired
    TicketStatusRepositoryJPA ticketStatusRepositoryJPA;

    @Autowired
    TicketAttachmentServiceJPA ticketAttachmentServiceJPA;

    @Value("${ws.crm.cust.type}")
    String custTypeURL;

    @Autowired
    TicketCategoryRepository ticketCategoryRepository;

    @Autowired
    OtpService otpService;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    TicketCategoryService ticketCategoryService;

    @Autowired
    TicketServiceJPA ticketServiceJPA;

    @Autowired
    CRMService crmService;

    /**
     * Them moi ticket
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    @Transactional
    public Object saveTicket(TicketDTO itemParamsEntity, Authentication authentication) {
        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.validateFileAttach(itemParamsEntity.getAttachmentFiles());
        }
        String actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        ModelMapper modelMapper = new ModelMapper();
        ActionAuditDTO actionAuditDTO = itemParamsEntity.toActionAuditDTO();
        TicketEntity oldTicketEntity = null;
        TicketEntity ticketEntity = modelMapper.map(itemParamsEntity, TicketEntity.class);
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketId())) {
            oldTicketEntity = ticketRepositoryJPA.findById(itemParamsEntity.getTicketId()).get();
            ticketEntity.setUpdateDate(new Date(System.currentTimeMillis()));
            ticketEntity.setUpdateUser(FnCommon.getUserLogin(authentication));
            actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
        } else {
            ticketEntity.setCreateDate(new Date(System.currentTimeMillis()));
            ticketEntity.setCreateUser(FnCommon.getUserLogin(authentication));
            ticketEntity.setProcessUser(FnCommon.getUserLogin(authentication));
            ticketEntity.setStatus(TicketStatusEntity.TicketStatus.NEW.value);
        }
        java.util.Date slaDate = ticketCategoryService.getDateTicketSlaNew(itemParamsEntity.getPriorityId(), itemParamsEntity.getL3TicketTypeId(), authentication, ticketEntity.getSla());
        if (slaDate != null) ticketEntity.setSlaDate(new Date(slaDate.getTime()));
        ticketRepositoryJPA.save(ticketEntity);

        /* Luu log */
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        actionAuditDTO.setTicketId(ticketEntity.getTicketId());
        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketEntity, ticketEntity, ticketEntity.getTicketId(), actionName);

        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.saveTicketAttachment(itemParamsEntity.getAttachmentFiles(),
                    ticketEntity.getTicketId(), authentication, ticketEntity.getTicketId(), TicketAttachmentEntity.Type.TICKET.value, actionAuditEntity.getActionAuditId(), actionName);
        }

        TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketId(ticketEntity.getTicketId());
        ticketStatusEntity.setSiteId(itemParamsEntity.getSiteId());
        ticketStatusEntity.setTicketStatus(ticketEntity.getStatus());
        ticketStatusEntity.setProcessTime(ticketEntity.getCreateDate());
        ticketStatusEntity.setNote(ticketEntity.getNote());
        ticketStatusEntity.setCreateUser(FnCommon.getUserLogin(authentication));
        ticketStatusEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketStatusEntity.setProcessContent(ticketEntity.getContentReceive());
        ticketStatusRepositoryJPA.save(ticketStatusEntity);
        /* Luu log */
        actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketEntity, ticketStatusEntity, ticketStatusEntity.getTicketStatusId(), actionName);

        return ticketEntity;

    }

    /***
     * Lay thong tin nguoi phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object getTicketDetails(TicketInfoDTO itemParamsEntity, Authentication authentication) {
        long ticketId = Long.parseLong(itemParamsEntity.getTicketId());
        ResultSelectEntity dataResult = ticketRepository.getTicketInfo(itemParamsEntity);
        List<TicketAttachmentEntity> ticketAttachmentEntities = ticketAttachmentServiceJPA.getTicketAttachment(ticketId,
                TicketAttachmentEntity.Type.TICKET.value);
        TicketInfoDTO ticketInfoDTO = (TicketInfoDTO) dataResult.getListData().get(0);
        ticketInfoDTO.setTicketAttachmentEntityList(ticketAttachmentEntities);
        return ticketInfoDTO;
    }

    /***
     * Lay lich su 1 phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object getTicketHistory(TicketHistoryDTO itemParamsEntity) {
        return ticketRepository.getTicketHistory(itemParamsEntity);
    }

    /***
     * Lay danh sach lich su phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object getListTicketHistories(TicketHistoryListDTO itemParamsEntity) {
        return ticketRepository.getListTicketHistories(itemParamsEntity);
    }

    /***
     * Xuat excel lich su phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object exportHistoryTicket(TicketHistoryListDTO itemParamsEntity) {
        try {
            String fileName = "tickets";

            fileName += System.currentTimeMillis() + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT);
            String date = formatter.format(new java.util.Date());
            String title = "Danh sách tickets \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
            listHeader.add(new ExcellHeaderEntity("Mã phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Nhóm phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Loại phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Nội dung phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Tình trạng xử lý"));
            listHeader.add(new ExcellHeaderEntity("Số lần phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Ngày tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Ngày đóng phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Người tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Người đóng phản ánh"));
            sheetExport.setListHeader(listHeader);

            ExcellDataEntity excellDataEntity = new ExcellDataEntity();
            List<List<Object>> listData = new ArrayList<>();
            List<TicketHistoryListDTO> historyTicketList = new ArrayList<>();
            ResultSelectEntity result = ticketRepository.getListTicketHistories(itemParamsEntity);
            if (result != null) {
                historyTicketList = (List<TicketHistoryListDTO>) result.getListData();
            }
            int no = 1;

            //set data
            for (TicketHistoryListDTO ticketDTO : historyTicketList) {
                List<Object> objectList = new ArrayList<>();
                objectList.add(no);
                objectList.add(Long.parseLong(ticketDTO.getTicketId()));//Mã phản ánh
                objectList.add(ticketDTO.getGroupPA());//Nhóm phản ánh
                objectList.add(ticketDTO.getDetailPA());//Loại phản ánh
                objectList.add(ticketDTO.getContentReceive());//Nội dung phản ánh
                String status = "";
                switch (ticketDTO.getTicketStatus()) {
                    case "1":
                        status = "Tạo mới";
                        break;
                    case "2":
                        status = "Đang xử lý";
                        break;
                    case "3":
                        status = "Xu ly xong";
                        break;
                    case "4":
                        status = "";
                        break;
                    case "5":
                        status = "Đóng phản ánh";
                        break;
                    case "6":
                        status = "Hủy";
                        break;
                    default:
                        status = "Theo dõi";
                        break;
                }
                objectList.add(status);//Tình trạng xử lý
                objectList.add(ticketDTO.getTicketTimes());//Số lần phản ánh
                objectList.add(ticketDTO.getReceiveDate() != null ? formatter.format(ticketDTO.getReceiveDate()) : "");//Ngày tiếp nhận
                objectList.add(ticketDTO.getConcludeDate() != null ? formatter.format(ticketDTO.getConcludeDate()) : "");//Ngày đóng phản ánh
                objectList.add(ticketDTO.getReceiveUser() != null ? ticketDTO.getReceiveUser() : "");//Người tiếp nhận
                objectList.add(ticketDTO.getConcludeUserName() != null ? ticketDTO.getConcludeUserName() : "");//Người đóng phản ánh
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
     * Tim kiem phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object searchTicket(SearchTicketDTO itemParamsEntity) {
        return ticketRepository.searchTicket(itemParamsEntity);
    }

    /***
     * Xuat exel danh sach tim kiem phan anh
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    public Object exportTicket(SearchTicketDTO itemParamsEntity, Authentication authentication) {
        try {
            String fileName = "Tra_cuu_tickets";

            fileName += DateTimeFormatter.ofPattern("ddMMyyy hhmmss").format(LocalDateTime.now()) + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT);
            SimpleDateFormat formatterDate = new SimpleDateFormat(COMMON_DATE_FORMAT);
            String date = formatter.format(new java.util.Date());
            String title = "Danh sách tickets \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
//            listHeader.add(new ExcellHeaderEntity("Tác động"));
            listHeader.add(new ExcellHeaderEntity("Mã PA"));
            listHeader.add(new ExcellHeaderEntity("Biển số xe"));
            listHeader.add(new ExcellHeaderEntity("Số HĐ"));
            listHeader.add(new ExcellHeaderEntity("Số ĐT theo HĐ"));
            listHeader.add(new ExcellHeaderEntity("Tên khách hàng"));
            listHeader.add(new ExcellHeaderEntity("Địa chỉ"));
            listHeader.add(new ExcellHeaderEntity("Địa chỉ chi tiết"));
            listHeader.add(new ExcellHeaderEntity("Người tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Ngày tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Giờ tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Nhóm PA"));
            listHeader.add(new ExcellHeaderEntity("Thể loại"));
            listHeader.add(new ExcellHeaderEntity("Loại PA"));
            listHeader.add(new ExcellHeaderEntity("Hình thức tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Ưu tiên"));
            listHeader.add(new ExcellHeaderEntity("Số lần PA"));
            listHeader.add(new ExcellHeaderEntity("ND PA"));
            listHeader.add(new ExcellHeaderEntity("Ngày hẹn KH"));
            listHeader.add(new ExcellHeaderEntity("Ngày hết hạn"));
            listHeader.add(new ExcellHeaderEntity("Trạng thái PA"));
            listHeader.add(new ExcellHeaderEntity("Ngày đóng PA"));
            listHeader.add(new ExcellHeaderEntity("Giờ đóng PA"));
            listHeader.add(new ExcellHeaderEntity("Nội dung xử lý"));
            listHeader.add(new ExcellHeaderEntity("Người xử lý"));
            listHeader.add(new ExcellHeaderEntity("Tiến độ xử lý"));
            listHeader.add(new ExcellHeaderEntity("Tổng TG xử lý"));
            listHeader.add(new ExcellHeaderEntity("Thời gian quá hạn"));
            listHeader.add(new ExcellHeaderEntity("Loại lỗi "));
            listHeader.add(new ExcellHeaderEntity("Trạm lỗi"));
            listHeader.add(new ExcellHeaderEntity("Loại KH"));
            listHeader.add(new ExcellHeaderEntity("Số ĐT liên hệ"));
            listHeader.add(new ExcellHeaderEntity("Thông tin bổ sung"));
            listHeader.add(new ExcellHeaderEntity("Mức độ hài lòng"));
            listHeader.add(new ExcellHeaderEntity("Tỉnh PA"));
            listHeader.add(new ExcellHeaderEntity("Quận/ Huyện"));
            listHeader.add(new ExcellHeaderEntity("Phường/ Xã"));
            listHeader.add(new ExcellHeaderEntity("Kỳ Hóa đơn"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 1"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 2"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân lỗi cấp 3"));
            listHeader.add(new ExcellHeaderEntity("ND tin nhắn phản hồi"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị phối hợp"));
            listHeader.add(new ExcellHeaderEntity("Người phối hợp"));
            listHeader.add(new ExcellHeaderEntity("TG phối hợp"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị chịu trách nhiệm (cấp 1)"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị chịu trách nhiệm (cấp 2)"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị chịu trách nhiệm (cấp 3)"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân quá hạn cấp 1"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân quá hạn cấp 2"));
            listHeader.add(new ExcellHeaderEntity("Nguyên nhân quá hạn cấp 3"));
            listHeader.add(new ExcellHeaderEntity("Đơn vị chịu trách nhiệm quá hạn "));
            sheetExport.setListHeader(listHeader);

            ExcellDataEntity excellDataEntity = new ExcellDataEntity();
            List<List<Object>> listData = new ArrayList<>();
            List<SearchTicketDTO> searchTicketList = new ArrayList<>();
            ResultSelectEntity result = ticketRepository.searchTicket(itemParamsEntity);
            if (result != null) {
                searchTicketList = (List<SearchTicketDTO>) result.getListData();
            }
            int no = 1;

            //set data
            for (SearchTicketDTO searchTicket : searchTicketList) {
                List<Object> objectList = new ArrayList<>();
                objectList.add(no);//STT
//                objectList.add("");//Tác động
                objectList.add(searchTicket.getTicketId() != null ? searchTicket.getTicketId() : "");//Mã PA
                objectList.add(searchTicket.getPlateNumber() != null ? searchTicket.getPlateNumber() : "");//Biển số xe
                objectList.add(searchTicket.getContractNo() != null ? searchTicket.getContractNo() : "");//Số HĐ
                objectList.add(searchTicket.getPhoneNumber() != null ? searchTicket.getPhoneNumber() : "");//Số điện thoại theo hợp đồng
                objectList.add(searchTicket.getCustName() != null ? searchTicket.getCustName() : "");//Tên khách hàng
                objectList.add(searchTicket.getCustAddress() != null ? searchTicket.getCustAddress() : "");//Địa chỉ
                objectList.add(searchTicket.getLocation() != null ? searchTicket.getLocation() : "");//Địa chỉ chi tiết
                objectList.add(searchTicket.getCreateUser() != null ? searchTicket.getCreateUser() : "");//Người tiếp nhận
                objectList.add(searchTicket.getCreateDate() != null ? formatterDate.format(searchTicket.getCreateDate()) : "");//Ngày tiếp nhận
                objectList.add(searchTicket.getCreateDate() != null ? new Time(searchTicket.getCreateDate().getTime()).toString() : "");//Giờ tiếp nhận
                objectList.add(searchTicket.getGroupPA() != null ? searchTicket.getGroupPA() : "");//Nhóm PA
                objectList.add(searchTicket.getSubgroupPA() != null ? searchTicket.getSubgroupPA() : "");//Thể loại
                objectList.add(searchTicket.getDetailPA() != null ? searchTicket.getDetailPA() : "");//Loại PA
                objectList.add(searchTicket.getSourceName() != null ? searchTicket.getSourceName() : "");//Hình thức tiếp nhận
                String pri = "";
                if (searchTicket.getPriorityId() != null) {
                    if ("1".equals(searchTicket.getPriorityId())) {
                        pri = "Normal";
                    } else if ("2".equals(searchTicket.getPriorityId())) {
                        pri = "HOT";
                    } else {
                        pri = "VIP";
                    }
                }
                objectList.add(pri);//Ưu tiên
                objectList.add(searchTicket.getTicketTimes() != null ? searchTicket.getTicketTimes() : "");//Số lần phản ánh
                objectList.add(searchTicket.getContentReceive() != null ? searchTicket.getContentReceive() : "");//ND PA
                objectList.add(searchTicket.getRequestDate() != null ? formatter.format(searchTicket.getRequestDate()) : "");//Ngày hẹn KH
                objectList.add(searchTicket.getDeadlineProcess() != null ? formatter.format(searchTicket.getDeadlineProcess()) : "");//Ngày hết hạn xử lý
                String status = "";
                switch (searchTicket.getStatus() != null ? searchTicket.getStatus() : "") {
                    case "1":
                        status = "Tạo mới";
                        break;
                    case "2":
                        status = "Đang xử lý";
                        break;
                    case "3":
                        status = "Xử lý xong";
                        break;
                    case "5":
                        status = "Đóng phản ánh";
                        break;
                    case "6":
                        status = "Hủy phản ánh";
                        break;
                    case "7":
                        status = "Theo dõi";
                        break;
                }
                objectList.add(status);//Trạng thái PA
                objectList.add(searchTicket.getCloseDateTicket() != null ? formatterDate.format(searchTicket.getCloseDateTicket()) : "");//Ngày đóng PA
                objectList.add(searchTicket.getCloseHourTicket() != null ? searchTicket.getCloseHourTicket() : "");//Giờ đóng PA
                objectList.add(searchTicket.getProcessContent() != null ? searchTicket.getProcessContent() : "");   //Nội dung xử lý PA
                objectList.add(searchTicket.getUpdateUser() != null ? searchTicket.getUpdateUser() : "");    //Người xử lý PA
                objectList.add(searchTicket.getProcessingProgress());//Tiến độ xử lý
                objectList.add(searchTicket.getTotalProcessTime() != null && searchTicket.getTotalProcessTime() > 0 ? searchTicket.getTotalProcessTime() : 0);//Tổng TG xử lý
                objectList.add(searchTicket.getExpireTime() != null && searchTicket.getExpireTime() > 0 ? searchTicket.getExpireTime() : 0);//Thời gian quá hạn
                String ticketKind = "";
                if (searchTicket.getTicketKind() != null) {
                    if ("1".equals(searchTicket.getTicketKind())) {
                        ticketKind = "Phát sinh";
                    } else {
                        ticketKind = "Đơn lẻ";
                    }
                }
                objectList.add(ticketKind);//Loại lỗi
                objectList.add(searchTicket.getStationName() != null ? searchTicket.getStationName() : "");//Trạm lỗi
                String custType = "";
                switch (searchTicket.getCustTypeId() != null ? searchTicket.getCustTypeId() : "") {
                    case "1":
                        custType = "Cá nhân trong nước";
                        break;
                    case "2":
                        custType = "Công ty cổ phần";
                        break;
                    case "3":
                        custType = "Công ty trách nhiệm hữu hạn";
                        break;
                    case "4":
                        custType = "Doanh nghiệp nhà nước";
                        break;
                    case "5":
                        custType = "Doanh nghiệp tư nhân";
                        break;
                    case "6":
                        custType = "Cơ quan nhà nước";
                        break;
                    case "7":
                        custType = "Cá nhân nước ngoài";
                        break;
                }
                objectList.add(custType);//Loại khách hàng
                objectList.add(searchTicket.getPhoneContact() != null ? searchTicket.getPhoneContact() : "");//Số ĐT liên hệ
                objectList.add(searchTicket.getSupportInfo() != null ? searchTicket.getSupportInfo() : "");//Thông tin bổ sung
                objectList.add("");//Mức độ hài lòng
                objectList.add(searchTicket.getProvinceName() != null ? searchTicket.getProvinceName() : "");//Tỉnh PA
                objectList.add(searchTicket.getDistrictName() != null ? searchTicket.getDistrictName() : "");//Quận/ Huyện
                objectList.add(searchTicket.getCommuneName() != null ? searchTicket.getCommuneName() : "");//Phường/ Xã
                objectList.add("");//Kỳ Hóa đơn
                objectList.add(searchTicket.getTicketErrorCauseIdL1Name() != null ? searchTicket.getTicketErrorCauseIdL1Name() : "");//Nguyên nhân lỗi cấp 1
                objectList.add(searchTicket.getTicketErrorCauseIdL2Name() != null ? searchTicket.getTicketErrorCauseIdL2Name() : "");//Nguyên nhân lỗi cấp 2
                objectList.add(searchTicket.getTicketErrorCauseIdL3Name() != null ? searchTicket.getTicketErrorCauseIdL3Name() : "");//Nguyên nhân lỗi cấp 3
                objectList.add("");//ND tin nhắn phản hồi
                objectList.add(searchTicket.getToSiteName() != null ? searchTicket.getToSiteName() : "");//Đơn vị phối hợp
                objectList.add(searchTicket.getToSiteEmail() != null ? searchTicket.getToSiteEmail() : "");//Người phối hợp
                objectList.add(searchTicket.getTotalInnerProcessTime() != null ? searchTicket.getTotalInnerProcessTime() : "" + " Ngày");//TG phối hợp
                objectList.add(searchTicket.getTicketSiteIdL1Name() != null ? searchTicket.getTicketSiteIdL1Name() : "");//Đơn vị chịu trách nhiệm cap 1
                objectList.add(searchTicket.getTicketSiteIdL2Name() != null ? searchTicket.getTicketSiteIdL2Name() : "");//Đơn vị chịu trách nhiệm cap 2
                objectList.add(searchTicket.getTicketSiteIdL3Name() != null ? searchTicket.getTicketSiteIdL3Name() : "");//Đơn vị chịu trách nhiệm cap 3
                objectList.add(searchTicket.getTicketExpireCauseIdL1Name() != null ? searchTicket.getTicketExpireCauseIdL1Name() : "");//Nguyên nhân quá hạn cấp 1
                objectList.add(searchTicket.getTicketExpireCauseIdL2Name() != null ? searchTicket.getTicketExpireCauseIdL2Name() : "");//Nguyên nhân quá hạn cấp 2
                objectList.add(searchTicket.getTicketExpireCauseIdL3Name() != null ? searchTicket.getTicketExpireCauseIdL3Name() : "");//Nguyên nhân quá hạn cấp 3
                objectList.add(searchTicket.getTicketExpireSiteName() != null ? searchTicket.getTicketExpireSiteName() : "");//Đơn vị chịu trách nhiệm quá hạn
                listData.add(objectList);
                no++;
            }
            excellDataEntity.setListData(listData);
            sheetExport.setExcellDataEntity(excellDataEntity);
            //export excel
            if (!FnCommon.exportFileExcell(sheetExport, System.getProperty("user.dir") + File.separator + fileName, title, "Times New Roman")) {
                throw new IOException();
            }
            return fileName;
        } catch (Exception ex) {
            LOGGER.error("Export Fail", ex);
        }
        return null;
    }

    /****
     * cap nhat phan anh
     * @param itemParamsEntity
     * @return
     */

    @Override
    public Object updateTicket(TicketDTO itemParamsEntity, Authentication authentication) {
        Optional<TicketEntity> optionalTicketEntity = ticketRepositoryJPA.findById(itemParamsEntity.getTicketId());
        TicketEntity ticketEntity = null;
        if (optionalTicketEntity.isPresent()) {
            ticketEntity = optionalTicketEntity.get();
        } else {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        TicketEntity oldTicketEntity = new TicketEntity();
        modelMapper.map(ticketEntity, oldTicketEntity);
        if (!FnCommon.isNullObject(itemParamsEntity.getPriorityId())) {
            ticketEntity.setPriorityId(itemParamsEntity.getPriorityId());
        }
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketTimes()) && itemParamsEntity.getTicketTimes() < ticketEntity.getTicketTimes()) {
            throw new EtcException("Không thể giảm số lần phản ánh");
        } else if (!FnCommon.isNullObject(itemParamsEntity.getTicketTimes()) && itemParamsEntity.getTicketTimes() > ticketEntity.getTicketTimes()) {
            ticketEntity.setTicketTimes(itemParamsEntity.getTicketTimes());
        }
        /* Luu log */
        String actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketId(itemParamsEntity.getTicketId());
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        actionAuditService.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, oldTicketEntity, ticketEntity, ticketEntity.getTicketId(), actionName);
        return ticketRepositoryJPA.save(ticketEntity);
    }


    /**
     * Luu ticket phan anh tu CPT
     *
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    public Object saveTicketForCPT(TicketDTO itemParamsEntity, Authentication authentication) {
        String userLogin = FnCommon.getUserLogin(authentication);
        if (userLogin != null && !userLogin.equalsIgnoreCase(itemParamsEntity.getContractNo())) {
            throw new EtcException("access.denied");
        }
        ContractDetailDTO contractDetailDTO = crmService.getContractDetails(authentication);
        if (contractDetailDTO == null) {
            throw new EtcException("access.denied");
        }

        if (itemParamsEntity.getTicketChannel() != null && itemParamsEntity.getTicketChannel() == 1) {
            otpService.checkOtpImportantService(itemParamsEntity.getOtp(), Constants.OTP_CATEGORY.CODE_REPORT_ERROR, OtpIdentify.REPORT_ERROR, authentication);
        }

        if (itemParamsEntity.getTicketChannel() != null && itemParamsEntity.getTicketChannel() == 2) {
            otpService.checkOtpImportantService(itemParamsEntity.getOtp(), Constants.OTP_CATEGORY.CODE_FEEDBACK, OtpIdentify.FEEDBACK, authentication);
        }

        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.validateFileAttach(itemParamsEntity.getAttachmentFiles());
        }

        java.util.Date createDate = new Date(System.currentTimeMillis());
        String actionName = ActionAuditDetailEntity.ActionName.INSERT.value;

        ModelMapper modelMapper = new ModelMapper();
        TicketEntity ticketEntity = modelMapper.map(itemParamsEntity, TicketEntity.class);
        ticketEntity.setCreateDate(new Date(createDate.getTime()));
        ticketEntity.setCreateUser(userLogin);
        ticketEntity.setStatus(TicketStatusEntity.TicketStatus.NEW.value);
        ticketEntity.setCustAddress(itemParamsEntity.getLocation());
        if (Constants.APP_CLIENT_ID.APP_CHU_PT.equals(FnCommon.getClientId(authentication)))
            ticketEntity.setSourceId(8L);
        if (Constants.APP_CLIENT_ID.PORTAL_CHU_PT.equals(FnCommon.getClientId(authentication)))
            ticketEntity.setSourceId(9L);


        ticketEntity.setCustName(contractDetailDTO.getUserName());
        ticketEntity.setCustTypeId(contractDetailDTO.getCusTypeId());
        ticketEntity.setPhoneNumber(contractDetailDTO.getNoticePhoneNumber());
        if (!FnCommon.isNullOrEmpty(contractDetailDTO.getCustomerId()))
            ticketEntity.setCustId(Long.parseLong(contractDetailDTO.getCustomerId()));
        if (!FnCommon.isNullOrEmpty(contractDetailDTO.getContractId()))
            ticketEntity.setContractId(Long.parseLong(contractDetailDTO.getContractId()));
        if (itemParamsEntity.getTicketChannel() != null && itemParamsEntity.getTicketChannel() == 2) {
            ticketEntity.setPhoneContact(contractDetailDTO.getNoticePhoneNumber());
        }
        Long ticketTypeId = itemParamsEntity.getL3TicketTypeId();
        if (ticketTypeId != null && itemParamsEntity.getPriorityId() != null) {
//            TicketSLADTO ticketSLADTO = TicketSLADTO.builder().ticketTypeId(ticketTypeId).priorityId(itemParamsEntity.getPriorityId()).build();
//            ResultSelectEntity result = ticketCategoryRepository.getTicketSla(ticketSLADTO);
//            if (result != null && result.getListData() != null && !result.getListData().isEmpty()) {
//                TicketSLADTO sla = (TicketSLADTO) result.getListData().get(0);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(createDate);
//                calendar.add(Calendar.HOUR_OF_DAY, sla.getSla().intValue());
//                ticketEntity.setSlaDate(new java.sql.Date(calendar.getTime().getTime()));
//                ticketEntity.setSla(sla.getSla());
//            }
            java.util.Date slaDate = ticketCategoryService.getDateTicketSlaNew(itemParamsEntity.getPriorityId(), itemParamsEntity.getL3TicketTypeId(), authentication, ticketEntity.getSla());
            if (slaDate != null) ticketEntity.setSlaDate(new Date(slaDate.getTime()));
        }

        ticketRepositoryJPA.save(ticketEntity);
        /* Luu log */
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setTicketId(ticketEntity.getTicketId());
        actionAuditDTO.setActTypeId(3001L);
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        actionAuditDTO.setDescription(ticketEntity.getNote());
        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), null, ticketEntity, ticketEntity.getTicketId(), actionName);

        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.saveTicketAttachment(itemParamsEntity.getAttachmentFiles(),
                    ticketEntity.getTicketId(), authentication, ticketEntity.getTicketId(), TicketAttachmentEntity.Type.TICKET.value, actionAuditEntity.getActionAuditId(), actionName);
        }

        TicketStatusEntity ticketStatusEntity = new TicketStatusEntity();
        ticketStatusEntity.setTicketId(ticketEntity.getTicketId());
        ticketStatusEntity.setSiteId(itemParamsEntity.getSiteId());
        ticketStatusEntity.setTicketStatus(ticketEntity.getStatus());
        ticketStatusEntity.setProcessTime(ticketEntity.getCreateDate());
        ticketStatusEntity.setNote(ticketEntity.getNote());
        ticketStatusEntity.setCreateUser(userLogin);
        ticketStatusEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketStatusEntity.setProcessContent(ticketEntity.getContentReceive());
        ticketStatusRepositoryJPA.save(ticketStatusEntity);

        /* Luu log */
        actionName = ActionAuditDetailEntity.ActionName.INSERT.value;
        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), null, ticketStatusEntity, ticketStatusEntity.getTicketStatusId(), actionName);

        return ticketEntity;
    }


    /**
     * Get thong tin phan anh, gop y tu CPT
     *
     * @param ticketDTO
     * @param authentication
     * @return
     */
    @Override
    public Object getTicketForCPT(TicketDTO ticketDTO, Authentication authentication) {
        List<TicketDTO> ticketDTOS = ticketRepository.getTicket(ticketDTO, authentication);
        if (ticketDTOS != null && !ticketDTOS.isEmpty()) {
            for (TicketDTO ticket : ticketDTOS) {
                List<FileDTO> attachmentFiles = new ArrayList<>();
                if (ticket.getFileName() != null && ticket.getAttachmentId() != null) {
                    String[] fileNames = ticket.getFileName().split("\\|");
                    String[] attachmentIds = ticket.getAttachmentId().split("\\|");
                    for (int i = 0; i < fileNames.length; i++) {
                        FileDTO fileDTO = new FileDTO();
                        fileDTO.setFileName(fileNames[i]);
                        fileDTO.setAttachmentId(Long.parseLong(attachmentIds[i]));
                        attachmentFiles.add(fileDTO);
                    }
                }
                ticket.setAttachmentFiles(attachmentFiles);
            }
        }
        return ticketDTOS;
    }

    /**
     * Get thong tin PA chua assign
     *
     * @param searchTicketDTO
     * @param authentication
     * @return
     */
    @Override
    public Object getTicketNotAssign(SearchTicketDTO searchTicketDTO, Authentication authentication) {
        return ticketRepository.getTicketNotAssign(searchTicketDTO, authentication);
    }

    /***
     * Xuat exel danh sach tim kiem phan anh chua assign
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    public Object exportTicketNotAssign(SearchTicketDTO itemParamsEntity, Authentication authentication) {
        try {
            String fileName = "Tra_cuu_ticket_not_assign";

            fileName += DateTimeFormatter.ofPattern("ddMMyyy hhmmss").format(LocalDateTime.now()) + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT);
            String date = formatter.format(new java.util.Date());
            String title = "Danh sách phản ánh chưa assign \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
            listHeader.add(new ExcellHeaderEntity("Mã phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Nội dung phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Trạng thái phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Số hợp đồng"));
            listHeader.add(new ExcellHeaderEntity("Tên khách hàng"));
            listHeader.add(new ExcellHeaderEntity("Người tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Thời gian tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Nhóm phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Thể loại"));
            listHeader.add(new ExcellHeaderEntity("Loại phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Mức độ ưu tiên"));
            sheetExport.setListHeader(listHeader);

            ExcellDataEntity excellDataEntity = new ExcellDataEntity();
            List<List<Object>> listData = new ArrayList<>();
            List<SearchTicketDTO> searchTicketNotAssign = new ArrayList<>();
            ResultSelectEntity result = ticketRepository.getTicketNotAssign(itemParamsEntity, authentication);
            if (result != null) {
                searchTicketNotAssign = (List<SearchTicketDTO>) result.getListData();
            }
            int no = 1;

            //set data
            for (SearchTicketDTO getTicketNotAssign : searchTicketNotAssign) {
                List<Object> objectList = new ArrayList<>();
                objectList.add(no);//STT
                objectList.add(getTicketNotAssign.getTicketId() != null ? getTicketNotAssign.getTicketId() : "");//Mã phản ánh
                objectList.add(getTicketNotAssign.getContentReceive() != null ? getTicketNotAssign.getContentReceive() : "");//Nội dung phản ánh
                String status = "";
                switch (getTicketNotAssign.getStatus() != null ? getTicketNotAssign.getStatus() : "") {
                    case "1":
                        status = "Tạo mới";
                        break;
                    case "2":
                        status = "Đang xử lý";
                        break;
                    case "3":
                        status = "Xử lý xong";
                        break;
                    case "5":
                        status = "Đóng phản ánh";
                        break;
                    case "6":
                        status = "Hủy phản ánh";
                        break;
                    case "7":
                        status = "Theo dõi";
                        break;
                }
                objectList.add(status);//Trạng thái phản ánh
                objectList.add(getTicketNotAssign.getContractNo() != null ? getTicketNotAssign.getContractNo() : "");//Số hợp đồng
                objectList.add(getTicketNotAssign.getCustName() != null ? getTicketNotAssign.getCustName() : "");//Tên khách hàng
                objectList.add(getTicketNotAssign.getCreateUser() != null ? getTicketNotAssign.getCreateUser() : "");//Người tiếp nhận
                objectList.add(getTicketNotAssign.getCreateDate() != null ? formatter.format(getTicketNotAssign.getCreateDate()) : "");//Thời gian tiếp nhận
                objectList.add(getTicketNotAssign.getL1TicketTypeName() != null ? getTicketNotAssign.getL1TicketTypeName() : "");//Nhóm phản ánh
                objectList.add(getTicketNotAssign.getL2TicketTypeName() != null ? getTicketNotAssign.getL2TicketTypeName() : "");//Thể loại
                objectList.add(getTicketNotAssign.getL3TicketTypeName() != null ? getTicketNotAssign.getL3TicketTypeName() : "");//Loại phản ánh
                String pri = "";
                switch (getTicketNotAssign.getPriorityId() != null ? getTicketNotAssign.getPriorityId() : "") {
                    case "1":
                        pri = "NORMAL";
                        break;
                    case "2":
                        pri = "HOT";
                        break;
                    case "3":
                        pri = "VIP";
                        break;
                }
                objectList.add(pri);//Mức độ ưu tiên
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
     * Lay danh muc loai khac hang
     * @param authentication
     * @return
     */
    private List<CustTypeDTO> getListCustType(Authentication authentication) {
        List<LinkedHashMap<String, Object>> listCustType = new ArrayList<>();
        String strResp = FnCommon.doGetRequest(custTypeURL, null, FnCommon.getStringToken(authentication));
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ObjectMapper().readValue(strResp, ResponseEntity.class);
        } catch (Exception e) {
            LOGGER.error("Convert data fail !", e);
        }
        if (responseEntity != null) {
            listCustType = (List<LinkedHashMap<String, Object>>) responseEntity.getData();
        }
        List<CustTypeDTO> list = new ArrayList<>();
        listCustType.forEach(obj -> {
            CustTypeDTO custTypeDTO = new CustTypeDTO();
            custTypeDTO.setCustTypeId(obj.get("cust_type_id").toString());
            custTypeDTO.setName(obj.get("name").toString());
            list.add(custTypeDTO);
        });
        return list;
    }

    /**
     * Lay danh sach bao cao nang suat xu ly phan anh
     *
     * @param searchTicketDTO
     * @param authentication
     * @return
     */
    public Object getTicketReportPerformmance(SearchTicketDTO searchTicketDTO, Authentication authentication) {
        return ticketRepository.getTicketReportPerformmance(searchTicketDTO, authentication);
    }

    /****
     * Chinh sua phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public Object editTicket(TicketInfoDTO itemParamsEntity, Long ticketId, Authentication authentication) {
        TicketEntity ticketEntity = ticketServiceJPA.getOne(ticketId);
        if (ticketEntity == null) {
            throw new EtcException("Không có dữ liệu thỏa mãn");
        }

        ticketEntity.setContractNo(StringUtils.trim(itemParamsEntity.getContractNo()));
        ticketEntity.setCustTypeId(Long.valueOf(itemParamsEntity.getCustTypeId()));
        ticketEntity.setPlateNumber(StringUtils.trim(itemParamsEntity.getPlateNumber()));
        ticketEntity.setPhoneNumber(StringUtils.trim(itemParamsEntity.getPhoneNumber()));
        ticketEntity.setCustName(StringUtils.trim(itemParamsEntity.getCustName()));
        ticketEntity.setEmail(StringUtils.trim(itemParamsEntity.getEmail()));
        ticketEntity.setCustAddress(StringUtils.trim(itemParamsEntity.getCustAddress()));
        ticketEntity.setPriorityId(itemParamsEntity.getPriorityId());
        ticketEntity.setSourceId(Long.valueOf(itemParamsEntity.getSourceId()));
        ticketEntity.setL1TicketTypeId(itemParamsEntity.getL1TicketTypeId());
        ticketEntity.setL2TicketTypeId(itemParamsEntity.getL2TicketTypeId());
        ticketEntity.setL3TicketTypeId(itemParamsEntity.getL3TicketTypeId());
        ticketEntity.setProvinceName(itemParamsEntity.getProvinceName());
        ticketEntity.setDistrictName(itemParamsEntity.getDistrictName());
        ticketEntity.setCommuneName(itemParamsEntity.getCommuneName());
        ticketEntity.setStageId(itemParamsEntity.getStageId());
        ticketEntity.setStationId(itemParamsEntity.getStationId());
        ticketEntity.setTicketKind(itemParamsEntity.getTicketKind());
        ticketEntity.setRequestDate(itemParamsEntity.getRequestDate() != null ? new java.sql.Date(itemParamsEntity.getRequestDate().getTime()) : null);
        ticketEntity.setStatus(Long.valueOf(itemParamsEntity.getStatus()));
        ticketEntity.setSlaDate(itemParamsEntity.getSlaDate() != null ? new java.sql.Date(itemParamsEntity.getSlaDate().getTime()) : null);
        ticketEntity.setPhoneContact(StringUtils.trim(itemParamsEntity.getPhoneContact()));
        ticketEntity.setSupportInfo(StringUtils.trim(itemParamsEntity.getSupportInfo()));
        ticketEntity.setNote(StringUtils.trim(itemParamsEntity.getNote()));
        ticketEntity.setContentReceive(StringUtils.trim(itemParamsEntity.getContentReceive()));
        ticketEntity.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
        ticketEntity.setUpdateUser(FnCommon.getUserLogin(authentication));
        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.validateFileAttach(itemParamsEntity.getAttachmentFiles());
        }
        TicketEntity savedTicket = ticketRepositoryJPA.save(ticketEntity);
        //Lưu log
        String actionName = ActionAuditDetailEntity.ActionName.UPDATE.value;
        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
        actionAuditDTO.setTicketId(ticketEntity.getTicketId());
        actionAuditDTO.setActTypeId(itemParamsEntity.getActTypeId());
        actionAuditDTO.setContractId(ticketEntity.getContractId());
        actionAuditDTO.setTicketStatus(ticketEntity.getStatus());
        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
        actionAuditService.saveActAuditAndActAuditDetail(authentication, actionAuditDTO, ticketEntity, savedTicket, ticketEntity.getTicketId(), actionName);
        if (itemParamsEntity.getAttachmentFiles() != null && !itemParamsEntity.getAttachmentFiles().isEmpty()) {
            ticketAttachmentService.saveTicketAttachment(itemParamsEntity.getAttachmentFiles(), ticketEntity.getTicketId(), authentication,
                    ticketEntity.getTicketId(), TicketAttachmentEntity.Type.TICKET.value, actionAuditEntity.getActionAuditId(), actionName);
        }
        return savedTicket;
    }

    /***
     * Xuat exel danh sach bao cao nang suat xu ly
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    public Object exportTicketReportPerformance(SearchTicketDTO itemParamsEntity, Authentication authentication) {
        try {
            String fileName = "Tra_cuu_ticket_report_performance";

            fileName += DateTimeFormatter.ofPattern("ddMMyyy hhmmss").format(LocalDateTime.now()) + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_FORMAT);
            String date = formatter.format(new java.util.Date());
            String title = "Danh sách báo cáo năng suất xử lý phản ánh \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
            listHeader.add(new ExcellHeaderEntity("Ngày xử lý"));
            listHeader.add(new ExcellHeaderEntity("Nhân viên xử lý"));
            listHeader.add(new ExcellHeaderEntity("Nhóm phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Thể loại"));
            listHeader.add(new ExcellHeaderEntity("Loại phản ánh"));
            listHeader.add(new ExcellHeaderEntity("Số lượng đã đóng"));
            listHeader.add(new ExcellHeaderEntity("Số lượng PA trong hạn"));
            listHeader.add(new ExcellHeaderEntity("Số lượng PA quá hạn"));
            sheetExport.setListHeader(listHeader);

            ExcellDataEntity excellDataEntity = new ExcellDataEntity();
            List<List<Object>> listData = new ArrayList<>();
            List<SearchTicketDTO> searchTicketReportPerformance = new ArrayList<>();
            ResultSelectEntity result = ticketRepository.getTicketReportPerformmance(itemParamsEntity, authentication);
            if (result != null) {
                searchTicketReportPerformance = (List<SearchTicketDTO>) result.getListData();
            }
            int no = 1;

            //set data
            for (SearchTicketDTO getTicketReportPerformmance : searchTicketReportPerformance) {
                List<Object> objectList = new ArrayList<>();
                objectList.add(no);//STT
                objectList.add(getTicketReportPerformmance.getUpdateDateReport() != null ? formatter.format(getTicketReportPerformmance.getUpdateDateReport()) : "");//Ngày xử lý
                objectList.add(getTicketReportPerformmance.getUpdateUser() != null ? getTicketReportPerformmance.getUpdateUser() : "");//Nhân viên xử lý
                objectList.add(getTicketReportPerformmance.getL1TicketTypeName() != null ? getTicketReportPerformmance.getL1TicketTypeName() : "");//Nhóm phản ánh
                objectList.add(getTicketReportPerformmance.getL2TicketTypeName() != null ? getTicketReportPerformmance.getL2TicketTypeName() : "");//Thể loại
                objectList.add(getTicketReportPerformmance.getL3TicketTypeName() != null ? getTicketReportPerformmance.getL3TicketTypeName() : "");//Loại phản ánh
                objectList.add(getTicketReportPerformmance.getCloseQuantity() != null ? getTicketReportPerformmance.getCloseQuantity() : "");//Số lượng đã đóng
                objectList.add(getTicketReportPerformmance.getInDueDate() != null ? getTicketReportPerformmance.getInDueDate() : "");//Số lượng PA trong hạn
                objectList.add(getTicketReportPerformmance.getOutOfDate() != null ? getTicketReportPerformmance.getOutOfDate() : "");//Số lượng PA quá hạn
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
