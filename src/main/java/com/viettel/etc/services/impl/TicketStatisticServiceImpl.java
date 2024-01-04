package com.viettel.etc.services.impl;

import com.viettel.etc.dto.CustTypeDTO;
import com.viettel.etc.dto.SearchTicketDTO;
import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.repositories.TicketStatisticRepository;
import com.viettel.etc.dto.TicketStatisticDTO;
import com.viettel.etc.repositories.tables.entities.StatisticEntity;
import com.viettel.etc.services.TicketStatisticService;
import com.viettel.etc.services.tables.StatisticServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.ExcellDataEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellHeaderEntity;
import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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
 * @date Thu Dec 02 09:01:14 ICT 2021
 */
@Service
public class TicketStatisticServiceImpl implements TicketStatisticService{

    private static final Logger LOGGER = Logger.getLogger(TicketServiceImpl.class);

    @Autowired 
    private TicketStatisticRepository ticketStatisticRepository;

    @Autowired
    private StatisticServiceJPA statisticServiceJPA;

    /**
     * Lay danh sach loai thong ke
     *
     * @param itemParamsEntity params client
     * @return
     */
    @Override
    public Object getTicketStatistic(TicketStatisticDTO itemParamsEntity, Authentication authentication) {
        return ticketStatisticRepository.getTicketStatistic(itemParamsEntity, authentication);
    }

    /***
     * Xuat exel danh sach tim kiem bao cao thong ke
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    public Object exportTicketStatistic(TicketStatisticDTO itemParamsEntity, Authentication authentication) {
        try {
            String fileName = "Tra_cuu_ticket_statistic";

            fileName += DateTimeFormatter.ofPattern("ddMMyyy hhmmss").format(LocalDateTime.now()) + ".xlsx";

            SimpleDateFormat formatter = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT);
            String date = formatter.format(new java.util.Date());
            String title = "Danh sách thống kê \nNgày xuất ";
            title += date;
            ExcellSheet sheetExport = new ExcellSheet();

            //set header
            List<ExcellHeaderEntity> listHeader = new ArrayList<>();
            listHeader.add(new ExcellHeaderEntity("STT"));
            listHeader.add(new ExcellHeaderEntity("Ngày tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Số HĐ/User CTV"));
            listHeader.add(new ExcellHeaderEntity("Biển số xe"));
            listHeader.add(new ExcellHeaderEntity("Số điện thoại trên hệ thống"));
            listHeader.add(new ExcellHeaderEntity("Số điện thoại gọi lên"));
            listHeader.add(new ExcellHeaderEntity("Kênh tiếp nhận"));
            listHeader.add(new ExcellHeaderEntity("Cấp 1"));
            listHeader.add(new ExcellHeaderEntity("Cấp 2"));
            listHeader.add(new ExcellHeaderEntity("Cấp 3"));
            listHeader.add(new ExcellHeaderEntity("Cấp 4"));
            listHeader.add(new ExcellHeaderEntity("Cấp 5"));
            listHeader.add(new ExcellHeaderEntity("Nội dung thống kê"));
            listHeader.add(new ExcellHeaderEntity("Phản ứng khách hàng"));
            sheetExport.setListHeader(listHeader);

            ExcellDataEntity excellDataEntity = new ExcellDataEntity();
            List<List<Object>> listData = new ArrayList<>();
            List<TicketStatisticDTO> searchTicketStatisticList = new ArrayList<>();
            ResultSelectEntity result = ticketStatisticRepository.getTicketStatistic(itemParamsEntity, authentication);
            if (result != null) {
                searchTicketStatisticList = (List<TicketStatisticDTO>) result.getListData();
            }
            int no = 1;

            //set data
            for (TicketStatisticDTO getTicketStatistic : searchTicketStatisticList) {
                List<Object> objectList = new ArrayList<>();
                objectList.add(no);//STT
                objectList.add(getTicketStatistic.getCreateDate() != null ? formatter.format(getTicketStatistic.getCreateDate()) : "");//Ngày tiếp nhận
                objectList.add(getTicketStatistic.getContractNoUserName() != null ? getTicketStatistic.getContractNoUserName() : "");//Số HĐ/User CTV
                objectList.add(getTicketStatistic.getPlateNumber() != null ? getTicketStatistic.getPlateNumber() : "");//Biển số xe
                objectList.add(getTicketStatistic.getSystemPhoneNumber() != null ? getTicketStatistic.getSystemPhoneNumber() : "");//Số điện thoại trên hệ thống
                objectList.add(getTicketStatistic.getCallPhoneNumber() != null ? getTicketStatistic.getCallPhoneNumber() : "");//Số điện thoại gọi lên
                objectList.add(getTicketStatistic.getSourceName() != null ? getTicketStatistic.getSourceName() : "");//Kênh tiếp nhận
                objectList.add(getTicketStatistic.getL1StatisticTypeName() != null ? getTicketStatistic.getL1StatisticTypeName() : "");//Cấp 1
                objectList.add(getTicketStatistic.getL2StatisticTypeName() != null ? getTicketStatistic.getL2StatisticTypeName() : "");//Cấp 2
                objectList.add(getTicketStatistic.getL3StatisticTypeName() != null ? getTicketStatistic.getL3StatisticTypeName() : "");//Cấp 3
                objectList.add(getTicketStatistic.getL4StatisticTypeName() != null ? getTicketStatistic.getL4StatisticTypeName() : "");//Cấp 4
                objectList.add(getTicketStatistic.getL5StatisticTypeName() != null ? getTicketStatistic.getL5StatisticTypeName() : "");//Cấp 5
                objectList.add(getTicketStatistic.getStatisticContent() != null ? getTicketStatistic.getStatisticContent() : "");//Nội dung thống kê
                String custReac = "";
                if (getTicketStatistic.getCustReaction() != null) {
                    if ("1".equals(getTicketStatistic.getCustReaction())) {
                        custReac = "OK";
                    } else if ("2".equals(getTicketStatistic.getCustReaction())) {
                        custReac = "NOK";
                    } else {
                        custReac = "Gay gắt";
                    }
                }
                objectList.add(custReac);//Phản ứng khách hàng
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

    /**
     * Them moi thong ke
     * 
     * @param itemParamsEntity params client
     * @return 
     */

    @Override
    public Object insertTicketStatistic(TicketStatisticDTO itemParamsEntity, Authentication authentication) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setContractNoUserName(StringUtils.trim(itemParamsEntity.getContractNoUserName()));
        statisticEntity.setPlateNumber(StringUtils.trim(itemParamsEntity.getPlateNumber()));
        statisticEntity.setSystemPhoneNumber(StringUtils.trim(itemParamsEntity.getSystemPhoneNumber()));
        statisticEntity.setCallPhoneNumber(StringUtils.trim(itemParamsEntity.getCallPhoneNumber()));
        statisticEntity.setSourceId(itemParamsEntity.getSourceId());
        statisticEntity.setL1StatisticTypeId(itemParamsEntity.getL1StatisticTypeId());
        statisticEntity.setL2StatisticTypeId(itemParamsEntity.getL2StatisticTypeId());
        statisticEntity.setL3StatisticTypeId(itemParamsEntity.getL3StatisticTypeId());
        statisticEntity.setL4StatisticTypeId(itemParamsEntity.getL4StatisticTypeId());
        statisticEntity.setL5StatisticTypeId(itemParamsEntity.getL5StatisticTypeId());
        statisticEntity.setStatisticContent(StringUtils.trim(itemParamsEntity.getStatisticContent()));
        statisticEntity.setCustReaction(itemParamsEntity.getCustReaction());
        statisticEntity.setCreateUser(itemParamsEntity.getCreateUser());
        statisticEntity.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        statisticServiceJPA.save(statisticEntity);
        return  statisticEntity;
    }
}