package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketStatisticDTO;
import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.repositories.TicketStatisticRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

/**
 * Autogen class Repository Impl: 
 * 
 * @author ToolGen
 * @date Thu Dec 02 09:01:14 ICT 2021
 */
@Repository
public class TicketStatisticRepositoryImpl extends CommonDataBaseRepository implements TicketStatisticRepository{

    /**
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getTicketStatistic(TicketStatisticDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hashMap = new HashMap<>();
        sql.append("SELECT \n");
        sql.append(" TS.STATISTIC_ID                    as statisticId,             -- KHÓA CHÍNH MÃ THỐNG KÊ           \n");
        sql.append(" TS.CONTRACT_NO_USER_NAME           as contractNoUserName,      -- SỐ HĐ/TÀI KHOẢN ĐẠI LÝ           \n");
        sql.append(" TS.PLATE_NUMBER                    as plateNumber,             -- BIẾN SỐ XE                       \n");
        sql.append(" TS.SYSTEM_PHONE_NUMBER             as systemPhoneNumber,       -- SỐ ĐIỆN THOẠI HỆ THỐNG           \n");
        sql.append(" TS.CALL_PHONE_NUMBER               as callPhoneNumber,         -- SỐ ĐIỆN THOẠI GỌI LÊN            \n");
        sql.append(" TS.SOURCE_ID                       as sourceId,                -- HÌNH THỨC TIẾP NHẬN              \n");
        sql.append(" TS.L1_STATISTIC_TYPE_ID            as l1StatisticTypeId,       -- CẤP 1                            \n");
        sql.append(" TS.L2_STATISTIC_TYPE_ID            as l2StatisticTypeId,       -- CẤP 2                            \n");
        sql.append(" TS.L3_STATISTIC_TYPE_ID            as l3StatisticTypeId,       -- CẤP 3                            \n");
        sql.append(" TS.L4_STATISTIC_TYPE_ID            as l4StatisticTypeId,       -- CẤP 4                            \n");
        sql.append(" TS.L5_STATISTIC_TYPE_ID            as l5StatisticTypeId,       -- CẤP 5                            \n");
        sql.append(" TS.STATISTIC_CONTENT               as statisticContent,        -- NỘI DUNG THỐNG KÊ                \n");
        sql.append(" TS.CUST_REACTION                   as custReaction,            -- PHẢN ÁNH KHÁCH HÀNG              \n");
        sql.append(" TS.CREATE_USER                     as createUser,              -- TÀI KHOẢN CẬP NHẬP DỮ LIỆU       \n");
        sql.append(" TS.CREATE_DATE                     as createDate,              -- THỜI GIAN TẠO                    \n");
        sql.append(" TS.UPDATE_USER                     as updateUser,              -- TÀI KHOẢN CẬP NHẬP DỮ LIỆU       \n");
        sql.append(" TS.UPDATE_DATE                     as updateDate,              -- NGÀY CẬP NHẬP DỮ LIỆU            \n");
        sql.append(" TST1.NAME                          as l1StatisticTypeName,     -- TÊN CẤP 1                        \n");
        sql.append(" TST2.NAME                          as l2StatisticTypeName,     -- TÊN CẤP 2                        \n");
        sql.append(" TST3.NAME                          as l3StatisticTypeName,     -- TÊN CẤP 3                        \n");
        sql.append(" TST4.NAME                          as l4StatisticTypeName,     -- TÊN CẤP 4                        \n");
        sql.append(" TST5.NAME                          as l5StatisticTypeName,     -- TÊN CẤP 5                        \n");
        sql.append(" TKS.NAME                           as sourceName               -- TÊN HÌNH THỨC TIẾP NHẬN          \n");
        sql.append(" FROM STATISTIC TS                                                                                  \n");
        sql.append(" LEFT JOIN STATISTIC_TYPE TST1 on TS.L1_STATISTIC_TYPE_ID = TST1.STATISTIC_TYPE_ID                  \n");
        sql.append(" LEFT JOIN STATISTIC_TYPE TST2 on TS.L2_STATISTIC_TYPE_ID = TST2.STATISTIC_TYPE_ID                  \n");
        sql.append(" LEFT JOIN STATISTIC_TYPE TST3 on TS.L3_STATISTIC_TYPE_ID = TST3.STATISTIC_TYPE_ID                  \n");
        sql.append(" LEFT JOIN STATISTIC_TYPE TST4 on TS.L4_STATISTIC_TYPE_ID = TST4.STATISTIC_TYPE_ID                  \n");
        sql.append(" LEFT JOIN STATISTIC_TYPE TST5 on TS.L5_STATISTIC_TYPE_ID = TST5.STATISTIC_TYPE_ID                  \n");
        sql.append(" LEFT JOIN TICKET_SOURCE  TKS on TS.SOURCE_ID = TKS.TICKET_SOURCE_ID                                \n");
        sql.append(" WHERE 1 = 1                                                                                        \n");

        //Tìm kiếm theo số HĐ/User CTV
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getContractNoUserNames())) {
            List<String> contractNoUserNames = Arrays.asList(itemParamsEntity.getContractNoUserNames().trim().toUpperCase().split(";"));
            sql.append(" and TS.CONTRACT_NO_USER_NAME in (:contractNoUserName) \n");
            hashMap.put("contractNoUserName", contractNoUserNames);
        }

        //Tìm kiếm theo biển số
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getPlateNumbers())) {
            List<String> plateNumbers = Arrays.asList(itemParamsEntity.getPlateNumbers().trim().toUpperCase().split(";"));
            sql.append(" and TS.PLATE_NUMBER in (:plateNumber) \n");
            hashMap.put("plateNumber", plateNumbers);
        }

        //Tìm kiếm theo sđt trên hệ thống
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getSystemPhoneNumbers())) {
            List<String> systemPhoneNumbers = Arrays.asList(itemParamsEntity.getSystemPhoneNumbers().trim().split(";"));
            sql.append(" and TS.SYSTEM_PHONE_NUMBER in (:systemPhoneNumber) \n");
            hashMap.put("systemPhoneNumber", systemPhoneNumbers);
        }

        //Tìm kiếm sđt gọi lên
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getCallPhoneNumbers())) {
            List<String> callPhoneNumbers = Arrays.asList(itemParamsEntity.getCallPhoneNumbers().trim().split(";"));
            sql.append(" and TS.CALL_PHONE_NUMBER in (:callPhoneNumber) \n");
            hashMap.put("callPhoneNumber", callPhoneNumbers);
        }

        //Tìm kiếm theo kênh tiếp nhận
        if (itemParamsEntity.getSourceId() != null) {
            sql.append("and TS.SOURCE_ID = :sourceId \n");
            hashMap.put("sourceId", itemParamsEntity.getSourceId());
        }

        //Tìm kiếm theo cấp 1
        if (itemParamsEntity.getL1StatisticTypeId() != null) {
            sql.append("and TS.L1_STATISTIC_TYPE_ID = :l1StatisticTypeId \n");
            hashMap.put("l1StatisticTypeId", itemParamsEntity.getL1StatisticTypeId());
        }

        //Tìm kiếm theo cấp 2
        if (itemParamsEntity.getL2StatisticTypeId() != null) {
            sql.append("and TS.L2_STATISTIC_TYPE_ID = :l2StatisticTypeId \n");
            hashMap.put("l2StatisticTypeId", itemParamsEntity.getL2StatisticTypeId());
        }

        //Tìm kiếm theo cấp 3
        if (itemParamsEntity.getL3StatisticTypeId() != null) {
            sql.append("and TS.L3_STATISTIC_TYPE_ID = :l3StatisticTypeId \n");
            hashMap.put("l3StatisticTypeId", itemParamsEntity.getL3StatisticTypeId());
        }

        //Tìm kiếm theo cấp 4
        if (itemParamsEntity.getL4StatisticTypeId() != null) {
            sql.append("and TS.L4_STATISTIC_TYPE_ID = :l4StatisticTypeId \n");
            hashMap.put("l4StatisticTypeId", itemParamsEntity.getL4StatisticTypeId());
        }

        //Tìm kiếm theo cấp 5
        if (itemParamsEntity.getL5StatisticTypeId() != null) {
            sql.append("and TS.L5_STATISTIC_TYPE_ID = :l5StatisticTypeId \n");
            hashMap.put("l5StatisticTypeId", itemParamsEntity.getL5StatisticTypeId());
        }

        //Tìm kiếm theo phản ứng khách hàng
        if (!FnCommon.isNullObject(itemParamsEntity.getReactionCustomerType())) {
            sql.append(" and TS.CUST_REACTION in (:custReactionType) \n");
            hashMap.put("custReactionType", itemParamsEntity.getReactionCustomerType());
        }

        //Tìm kiếm từ ngày - đến ngày
        if (itemParamsEntity.getFromDate() != null) {
            sql.append("and TS.CREATE_DATE >= :fromDate \n");
            hashMap.put("fromDate", new java.sql.Date(itemParamsEntity.getFromDate().getTime()));
        }
        if (itemParamsEntity.getToDate() != null) {
            sql.append("and TS.CREATE_DATE < :toDate \n");
            hashMap.put("toDate", new java.sql.Date(itemParamsEntity.getToDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        sql.append(" order by TS.STATISTIC_ID DESC \n");
        return getListDataAndCount(sql, hashMap, start, pageSize, TicketStatisticDTO.class);
    }
}