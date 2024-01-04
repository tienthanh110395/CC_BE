package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketAssignDTO;
import com.viettel.etc.dto.TicketAssignProcessIdDTO;
import com.viettel.etc.repositories.TicketAssignRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Autogen class Repository Impl: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * Mon Mar 01 09:31:44 ICT 2021
 */
@Repository
public class TicketAssignRepositoryImpl extends CommonDataBaseRepository implements TicketAssignRepository {

    /**
     * Lay danh sach  yeu cau phoi hop xu ly
     *
     * @param itemParamsEntity: params client truyen len
     */
    @Override
    public ResultSelectEntity getTicketAssigns(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                                                            \n");
        sql.append("    TK.TICKET_ID                                as ticketId,                       --1.Mã phản ánh                                \n");
        sql.append("    TK.PLATE_NUMBER                             as plateNumber,                    --2.Biển số xe                                 \n");
        sql.append("    TK.CONTRACT_NO                              as contractNo,                     --3.Số hợp đồng                                \n");
        sql.append("    DBMS_LOB.SUBSTR(TK.CONTENT_RECEIVE)         as contentReceive,                 --4.Nội dung phản ánh                          \n");
        sql.append("    NVL(TAP.PROCESS_RESULT,TP.PROCESS_CONTENT)  as processContent,                 --5.Nội dung xử lý                             \n");
        sql.append("    TA.TICKET_STATUS                            as ticketStatus,                   --7.Trạng thái tiếp nhận                       \n");
        sql.append("    TA.CREATE_DATE                              as createDate,                     --Mã nhân viên xử lý                           \n");
        sql.append("    TA.CREATE_USER                              as createUser,                     --Mã nhân viên xử lý                           \n");
        sql.append("    TA.UPDATE_USER                              as staffName,                      --8.Tên nhân viên xử lý                        \n");
        sql.append("    TA.FROM_USERNAME                            as fromUserName,                                       \n");
        sql.append("    case when TA.TICKET_STATUS = 1 then null else TAP.STAFF_NAME  end                            as userProcess,                    -- Nguoi xu ly y/c phoi hop                    \n");
        sql.append("    case when TA.TICKET_STATUS = 1 then null else TAP.PROCESS_TIME end                           as processTime,                    -- Ngay xu ly y/c phoi hop                    \n");
        sql.append("    TP.REASON_LEVEL1                            as reasonLevel1,                   --9.Nguyên nhân lỗi cấp 1                      \n");
        sql.append("    TP.REASON_LEVEL2                            as reasonLevel2,                   --10.Nguyên nhân lỗi cấp2                      \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L1                 as ticketErrorCauseIdL1,           --9.Nguyên nhân lỗi cấp 1                      \n");
        sql.append("    TEC1.NAME                                   as ticketErrorCauseIdL1Name,       --9.Nguyên nhân lỗi cấp 1                      \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L3                 as ticketErrorCauseIdL2,           --9.Nguyên nhân lỗi cấp 2                      \n");
        sql.append("    TEC2.NAME                                   as ticketErrorCauseIdL2Name,       --9.Nguyên nhân lỗi cấp 2                      \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L2                 as ticketErrorCauseIdL3,           --9.Nguyên nhân lỗi cấp 3                      \n");
        sql.append("    TEC3.NAME                                   as ticketErrorCauseIdL3Name,       --9.Nguyên nhân lỗi cấp 3                      \n");
        sql.append("    TA.ASSIGN_DATE                              as assignDate,                                                                    \n");
        sql.append("    TA.RESOLVE_DATE                             as resolveDate,                                                                   \n");
        sql.append("    TA.TICKET_ASSIGN_ID                         as ticketAssignId,                                                                \n");
        sql.append("    TA.ASSIGN_TYPE                              as assignType,                                                                    \n");
        sql.append("    TA.SLA_DATE                                 as slaDate,                                                                       \n");
        sql.append("    TA.TO_SITE_EMAIL                            as toSiteEmail,                                                                   \n");
        sql.append("    TS1.SITE_CODE                               as fromSiteCode,                                                                  \n");
        sql.append("    TA.FROM_SITE_ID                             as fromSiteId,                                                                    \n");
        sql.append("    TS1.SITE_NAME                               as fromSiteName,                    --6.Đơn vị xử lý                              \n");
        sql.append("    TS2.SITE_CODE                               as toSiteCode,                                                                    \n");
        sql.append("    TA.TO_SITE_ID                               as toSiteId,                                                                      \n");
        sql.append("    TS2.SITE_NAME                               as toSiteName,                      --11.Đơn vị phối hợp                          \n");
        sql.append("    TS3.SITE_CODE                               as toSiteL2Code,                                                                  \n");
        sql.append("    TA.TO_SITE_L2_ID                            as toSiteL2Id,                                                                    \n");
        sql.append("    TS3.SITE_NAME                               as toSiteL2Name,                     --12.Đơn vị chịu trách nhiệm                 \n");
        sql.append("    case when TK.SLA_DATE > sysdate then 'Trong hạn' else 'Quá hạn'  end  as txtProcessTicket,  -- Tien do xu ly                      \n");
        sql.append("    case when ts_close.process_time is null then null else round(ts_close.PROCESS_TIME - ts_new.PROCESS_TIME, 2) * 24 end as hourProcessTicket  -- Tien do xu ly                      \n");
        sql.append("FROM                                                                                                                              \n");
        sql.append("    TICKET TK                                                                                                                     \n");
        sql.append("    LEFT JOIN TICKET_ASSIGN TA ON TA.TICKET_ID = TK.TICKET_ID                                                                    \n");
        sql.append("    LEFT JOIN TICKET_ASSIGN_PROCESS TAP ON TAP.TICKET_ID = TK.TICKET_ID AND TAP.STATUS = TA.TICKET_STATUS                                                        \n");
        sql.append("    LEFT JOIN TICKET_PROCESS TP ON TP.TICKET_ID = TK.TICKET_ID                                                                   \n");
        sql.append("    LEFT JOIN TICKET_SITE TS1 ON TS1.SITE_ID = TA.FROM_SITE_ID                                                                  \n");
        sql.append("    LEFT JOIN TICKET_SITE TS2 ON TS2.SITE_ID = TA.TO_SITE_ID                                                                    \n");
        sql.append("    LEFT JOIN TICKET_SITE TS3 ON TS3.SITE_ID = TA.TO_SITE_L2_ID                                                                 \n");
        sql.append("    LEFT JOIN TICKET_ERROR_CAUSE TEC1 ON TEC1.TICKET_ERROR_CAUSE_ID = TP.TICKET_ERROR_CAUSE_ID_L1                               \n");
        sql.append("    LEFT JOIN TICKET_ERROR_CAUSE TEC2 ON TEC2.TICKET_ERROR_CAUSE_ID = TP.TICKET_ERROR_CAUSE_ID_L2                               \n");
        sql.append("    LEFT JOIN TICKET_ERROR_CAUSE TEC3 ON TEC3.TICKET_ERROR_CAUSE_ID = TP.TICKET_ERROR_CAUSE_ID_L3                               \n");
        sql.append("    LEFT JOIN TICKET_STATUS ts_new on TK.TICKET_ID = ts_new.TICKET_ID and ts_new.TICKET_STATUS = 1");
        sql.append("    LEFT JOIN TICKET_STATUS ts_close on TK.TICKET_ID = ts_close.TICKET_ID and ts_close.TICKET_STATUS = 5");
        sql.append("    WHERE (TA.TO_SITE_ID = :toSiteId OR UPPER(TA.CREATE_USER) = :createUser )         \n");
        hmapParams.put("toSiteId", itemParamsEntity.getToSiteId() != null ? itemParamsEntity.getToSiteId().toUpperCase() : "");
        hmapParams.put("createUser", FnCommon.getUserLogin(authentication));
        if (itemParamsEntity.getStartReceiveDate() != null) {
            sql.append("and TA.ASSIGN_DATE >= :startReceiveDate \n");
            hmapParams.put("startReceiveDate", new java.sql.Date(itemParamsEntity.getStartReceiveDate().getTime()));
        }

        if (itemParamsEntity.getEndReceiveDate() != null) {
            sql.append("and TA.ASSIGN_DATE < :endReceiveDate \n");
            hmapParams.put("endReceiveDate", new java.sql.Date(itemParamsEntity.getEndReceiveDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        if (itemParamsEntity.getStartProcessDate() != null) {
            sql.append("and TA.RESOLVE_DATE >= :startProcessDate \n");
            hmapParams.put("startProcessDate", new java.sql.Date(itemParamsEntity.getStartProcessDate().getTime()));
        }

        if (itemParamsEntity.getEndProcessDate() != null) {
            sql.append("and TA.RESOLVE_DATE < :endProcessDate \n");
            hmapParams.put("endProcessDate", new java.sql.Date(itemParamsEntity.getEndProcessDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getTicketId())) {
            List<String> ticketIds = Arrays.asList(itemParamsEntity.getTicketId().split(";"));
            sql.append(" and TK.TICKET_ID in (:ticketId) \n");
            hmapParams.put("ticketId", ticketIds);
        }
        
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getTicketStatus())) {
            List<String> listStatus = Arrays.asList(itemParamsEntity.getTicketStatus().split(";"));
            sql.append(" and TA.TICKET_STATUS in (:status) \n");
            hmapParams.put("status", listStatus);
        }

        sql.append(" ORDER BY TA.CREATE_DATE DESC \n");
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketAssignDTO.class);
    }

    /***
     * Lay thong tin xu ly cua 1 phan anh
     * @param itemParamsEntity tham so nguoi dung truyen vao
     * @return tra lai 1 ban ghi
     */
    @Override
    public ResultSelectEntity getTicketAssignById(TicketAssignDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  DISTINCT                                                                                                    \n");
        sql.append("    TA.TICKET_STATUS                                              as status,                    --Trạng thái phản ánh\n");
        sql.append("    TA.UPDATE_USER                                                as updateUser,                         \n");
        sql.append("    TA.CREATE_USER                                                as createUser,                         \n");
        sql.append("    TA.UPDATE_DATE                                                as processTime,                         \n");
        sql.append("    TA.CREATE_DATE                                                as createDate,                            \n");
        sql.append("    TA.SLA_DATE                                                   as slaDate,                            \n");
        sql.append("    TA.USER_PROCESS                                               as userProcess,                        \n");
        sql.append("    TA.TICKET_ID                                                  as ticketId,                  --Mã phản ánh\n");
        sql.append("    TK.CUST_ID                                                    as custId,                    --Mã khách hàng\n");
        sql.append("    TK.CUST_TYPE_ID                                               as custTypeId,                --Mã loại khách hàng\n");
        sql.append("    TK.CUST_NAME                                                  as custName,                  --Tên khách hàng\n");
        sql.append("    TK.PLATE_NUMBER                                               as plateNumber,               --Biển số\n");
        sql.append("    TK.PLATE_TYPE_CODE                                            as plateTypeCode,             --Loại biển\n");
        sql.append("    TK.PHONE_NUMBER                                               as phoneNumber,               --Số điện thoại\n");
        sql.append("    TK.EMAIL                                                      as email,                     --Email khách hàng \n");
        sql.append("    TK.CONTRACT_NO                                                as contractNo,                --Số hợp đồng\n");
        sql.append("    TK.CUST_ADDRESS                                               as custAddress,               --Địa chỉ khách hàng\n");
        sql.append("    TK.PRIORITY_ID                                                as priorityId,                --Mức độ ưu tiên\n");
        sql.append("    TK.TICKET_KIND                                                as ticketKind,                --Loại lỗi\n");
        sql.append("    TK.LOCATION                                                   as location,                  --Địa điểm phản ánh\n");
        sql.append("    TK.AREA_CODE                                                  as areaCode,                  --Mã tỉnh/huyện/xã\n");
        sql.append("    DBMS_LOB.SUBSTR(TK.CONTENT_RECEIVE)                           as contentReceive,            --Nội dung phản ánh\n");
        sql.append("    TK.SUPPORT_INFO                                               as supportInfo,               --Thông tin bổ sung\n");
        sql.append("    TK.REQUEST_DATE                                               as requestDate,               --Ngày yêu cầu trả kết quả\n");
        sql.append("    TK.NOTE                                                       as note,                      --Ghi chú\n");
        sql.append("    TK.SOURCE_ID                                                  as sourceId,                  --Mã nguồn tiếp nhận\n");
        sql.append("    TS.SOURCE_CODE                                                as sourceCode,                --Mã nguồn tiếp nhận\n");
        sql.append("    TS.NAME                                                       as sourceName,                --Tên nguồn tiếp nhận\n");
        sql.append("    TKT1.NAME                                                     as groupPA,                   --Nhóm phản ánh\n");
        sql.append("    TK.L1_TICKET_TYPE_ID                                          as l1TicketTypeId,            --Id nhóm phản ánh\n");
        sql.append("    TKT2.NAME                                                     as subgroupPA,                --Thể loại\n");
        sql.append("    TK.L2_TICKET_TYPE_ID                                          as l2TicketTypeId,            --id thẻ loại\n");
        sql.append("    TKT3.NAME                                                     as detailPA,                  --Loại phản ánh\n");
        sql.append("    TK.L3_TICKET_TYPE_ID                                          as l3TicketTypeId,            --Id loại phản ánh\n");
        sql.append("    TA.ASSIGN_CONTENT                                             as assignContent,             --Nội dung giao việc phối hợp\n");
        sql.append("    TA.TICKET_ASSIGN_ID                                           as ticketAssignId,            --Mã giao việc phối hợp\n");
        sql.append("    TA.SITE_ID                                                    as siteId,                    \n");
        sql.append("    TA.TO_SITE_ID                                                 as toSiteId,                   --Mã đơn vị xử lý\n");
        sql.append("    TSI.SITE_NAME                                                 as siteName                   --Tên đơn vị xử lý\n");
        sql.append("FROM                                                                                                        \n");
        sql.append("    TICKET_ASSIGN TA                                                                                                        \n");
        sql.append("    JOIN TICKET TK ON TK.TICKET_ID = TA.TICKET_ID                                                                           \n");
        sql.append("    LEFT JOIN TICKET_SOURCE TS ON TS.TICKET_SOURCE_ID = TK.SOURCE_ID                                                        \n");
        sql.append("    LEFT JOIN TICKET_SITE TSI  ON TSI.SITE_ID = TA.SITE_ID                                                                  \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT1  ON TKT1.TICKET_TYPE_ID = TK.L1_TICKET_TYPE_ID                                               \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT2  ON TKT2.TICKET_TYPE_ID = TK.L2_TICKET_TYPE_ID                                               \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT3  ON TKT3.TICKET_TYPE_ID = TK.L3_TICKET_TYPE_ID                                               \n");
        sql.append(" WHERE 1 = 1                                                                                                                \n");
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketId())) {
            sql.append(" AND TA.TICKET_ID = :ticketID\n");
            hmapParams.put("ticketID", itemParamsEntity.getTicketId());
        }
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketAssignId())) {
            sql.append(" AND TA.TICKET_ASSIGN_ID = :ticketAssignId \n");
            hmapParams.put("ticketAssignId", itemParamsEntity.getTicketAssignId());
        }
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketAssignProcessIdDTO.class);

    }

}
