package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketRepository;
import com.viettel.etc.repositories.tables.entities.TicketAssignProcessEntity;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Autogen class Repository Impl: Lop thao tac them moi ticket
 *
 * @author ToolGen
 * @date Tue Mar 02 14:49:46 ICT 2021
 */
@Repository
public class TicketRepositoryImpl extends CommonDataBaseRepository implements TicketRepository {
    /***
     * Lay thon tin nguoi phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketInfo(TicketInfoDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    TK.TICKET_ID                                 as ticketId,                                   \n");
        sql.append("    TK.CONTRACT_NO                               as contractNo,                                 \n");
        sql.append("    TK.CONTRACT_ID                               as contractId,                                 \n");
        sql.append("    TK.PLATE_NUMBER                              as plateNumber,                                \n");
        sql.append("    TK.CUST_ID                                   as custId,                                     \n");
        sql.append("    TK.CUST_NAME                                 as custName,                                   \n");
        sql.append("    TK.CUST_TYPE_ID                              as custTypeId,                                 \n");
        sql.append("    TK.PHONE_NUMBER                              as phoneNumber,                                \n");
        sql.append("    TK.EMAIL                                     as email,                                      \n");
        sql.append("    TK.STATUS                                    as status,                                     \n");
        sql.append("    TK.SOURCE_ID                                 as sourceId,                                   \n");
        sql.append("    TK.STAGE_ID                                  as stageId,                                    \n");
        sql.append("    TK.STATION_ID                                as stationId,                                  \n");
        sql.append("    TK.STAGE_NAME                                as stageName,                                  \n");
        sql.append("    TK.STATION_NAME                              as stationName,                                \n");
        sql.append("    TK.PROVINCE_NAME                             as provinceName,                               \n");
        sql.append("    TK.DISTRICT_NAME                             as districtName,                               \n");
        sql.append("    TK.COMMUNE_NAME                              as communeName,                                \n");
        sql.append("    TK.REQUEST_DATE                              as requestDate,                                \n");
        sql.append("    TK.SLA_DATE                                  as slaDate,                                    \n");
        sql.append("    TK.TICKET_KIND                               as ticketKind,                                 \n");
        sql.append("    TK.PRIORITY_ID                               as priorityId,                                 \n");
        sql.append("    TK.SUPPORT_INFO                              as supportInfo,                                \n");
        sql.append("    TK.NOTE                                      as note,                                       \n");
        sql.append("    TS.NAME                                      as sourceName,                                 \n");
        sql.append("    TK.CUST_ADDRESS                              as custAddress,                                \n");
        sql.append("    DBMS_LOB.SUBSTR(TK.CONTENT_RECEIVE)          as contentReceive                              \n");
        sql.append("FROM                                                                                            \n");
        sql.append("    TICKET TK                                                                                   \n");
        sql.append("    LEFT JOIN TICKET_SOURCE TS ON TS.TICKET_SOURCE_ID = TK.SOURCE_ID                            \n");
        sql.append("                                  AND TS.STATUS = 1                                             \n");
        sql.append(" WHERE 1 = 1                                                                                    \n");
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getTicketId())) {
            sql.append(" and TK.TICKET_ID = :ticketId \n");
            hmapParams.put("ticketId", itemParamsEntity.getTicketId());
        }
        return getListDataAndCount(sql, hmapParams, null, null, TicketInfoDTO.class);
    }

    /***
     * Lay thong tin qua trinh xu ly phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketHistory(TicketHistoryDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                             \n");
        sql.append("    tt.*,                                                                                          \n");
        sql.append("    ts.site_name                         AS siteName                    --Tên nhân viên xử lý      \n");
        sql.append("FROM                                                                                               \n");
        sql.append("    (                                                                                              \n");
        sql.append("        SELECT                                                                                     \n");
        sql.append("            tap.ticket_id                AS ticketId,                   --Mã ticket                \n");
        sql.append("            tap.process_time             AS processTime,                --Thời điểm xử lý          \n");
        sql.append("            tap.site_id                  AS siteId,                                                \n");
        sql.append("            tap.create_user              AS staffName,                  --Tên nhân viên xử lý      \n");
        sql.append("            tap.process_result           AS processContent,             --Nội dung xử lý           \n");
        sql.append("            tap.status                   AS assignStatus,               --Trạng thái xử lý PA      \n");
        sql.append("            NULL                         AS ticketStatus                --Trạng thái ticket        \n");
        sql.append("        FROM                                                                                       \n");
        sql.append("            ticket_assign_process tap                                                              \n");
        sql.append("        WHERE                                                                                      \n");
        sql.append("            tap.ticket_id = :ticketId                                                              \n");
        sql.append("        UNION ALL                                                                                  \n");
        sql.append("        SELECT                                                                                     \n");
        sql.append("            tst.ticket_id                 AS ticketId,                                             \n");
        sql.append("            tst.process_time              AS processTime,                                          \n");
        sql.append("            tst.site_id                   AS siteId,                     --KHONG CO SITE_ID        \n");
        sql.append("            tst.CREATE_USER               AS staffName,                                            \n");
        sql.append("            tst.process_content           AS processContent,                                       \n");
        sql.append("            NULL                          AS assignStatus,               --Trạng thái xử lý PA     \n");
        sql.append("            tst.ticket_status             AS ticketStatus                --Trạng thái ticket       \n");
        sql.append("        FROM                                                                                       \n");
        sql.append("            ticket_status tst                                                                      \n");
        sql.append("        WHERE                                                                                      \n");
        sql.append("            tst.ticket_id = :ticketId                                                              \n");
        sql.append("    ) tt                                                                                           \n");
        sql.append("    LEFT JOIN ticket_site ts ON ts.site_id = tt.siteId                                             \n");
        hmapParams.put("ticketId", itemParamsEntity.getTicketId());
        sql.append(" ORDER BY tt.processTime,tt.ticketStatus,tt.assignStatus \n");
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }

        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketHistoryDTO.class);
    }

    /***
     * Lay sanh sach lich su phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getListTicketHistories(TicketHistoryListDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                                                      \n");
        sql.append("    TK.PLATE_NUMBER                                  as plateNumber,                                                        \n");
        sql.append("    TK.PLATE_TYPE_CODE                               as plateTypeCode,                                                      \n");
        sql.append("    TK.PHONE_NUMBER                                  as phoneNumber,               --Số điện thoại theo hợp đồng            \n");
        sql.append("    TK.PHONE_CONTACT                                 as phoneContact,              --Số điện thoại liên hệ                  \n");
        sql.append("    TK.TICKET_TIMES                                  as ticketTimes,               --Số lần phản ánh                        \n");
        sql.append("    TK.STAGE_ID                                      as stageId,                   --Mã đoạn                                \n");
        sql.append("    TK.STAGE_NAME                                    as stageName,                 --Tên đoạn                               \n");
        sql.append("    TK.STATION_ID                                    as stationId,                 --Mã trạm                                \n");
        sql.append("    TK.STATION_NAME                                  as stationName,               --Tên trạm                               \n");
        sql.append("    TK.CONTRACT_NO                                   as contractNo,                                                         \n");
        sql.append("    TK.TICKET_ID                                     as ticketId,                                                           \n");
        sql.append("    TA.TICKET_ASSIGN_ID                              as ticketAssignId,                                                     \n");
        sql.append("    TKT1.NAME                                        as groupPA,                    --Nhóm phản ánh                         \n");
        sql.append("    TK.L1_TICKET_TYPE_ID                             as l1TicketTypeId,                                                     \n");
        sql.append("    TKT2.NAME                                        as subgroupPA,                 --Thể lại                               \n");
        sql.append("    TK.L2_TICKET_TYPE_ID                             as l2TicketTypeId,                                                     \n");
        sql.append("    TKT3.NAME                                        as detailPA,                   --Loại                                  \n");
        sql.append("    TK.L3_TICKET_TYPE_ID                             as l3TicketTypeId,                                                     \n");
        sql.append("    DBMS_LOB.SUBSTR(TK.CONTENT_RECEIVE)              as contentReceive,             --Nội dung phản ánh                     \n");
        sql.append("    TK.STATUS                                        as ticketStatus,               --Trạng thái phản ánh                   \n");
        sql.append("    TK.CREATE_DATE                                   as receiveDate,                --Ngày tiếp nhân                        \n");
        sql.append("    TP.PROCESS_TIME                                  as concludeDate,               --Ngày đóng                             \n");
        sql.append("    TK.CREATE_USER                                   as receiveUser,                --User tiếp nhận                        \n");
        sql.append("    TP.STAFF_CODE                                    as concludeUserCode,           --Mã nhân viên đóng tiếp nhận           \n");
        sql.append("    TP.STAFF_NAME                                    as concludeUserName            --Tiên nhân viên đóng tiếp nhận         \n");
        sql.append("FROM                                                                                                                        \n");
        sql.append("    TICKET TK                                                                                                               \n");
        sql.append("    LEFT JOIN TICKET_PROCESS TP ON TP.TICKET_ID = TK.TICKET_ID                                                              \n");
        sql.append("    LEFT JOIN TICKET_ASSIGN  TA ON TA.TICKET_ID = TK.TICKET_ID                                                              \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT1  ON TKT1.TICKET_TYPE_ID = TK.L1_TICKET_TYPE_ID                                               \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT2  ON TKT2.TICKET_TYPE_ID = TK.L2_TICKET_TYPE_ID                                               \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT3  ON TKT3.TICKET_TYPE_ID = TK.L3_TICKET_TYPE_ID                                               \n");
        sql.append(" WHERE 1 = 1                                                                                                                \n");
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getPhoneNumber())) {
            sql.append(" and TK.PHONE_NUMBER = :phoneNumber\n");
            hmapParams.put("phoneNumber", itemParamsEntity.getPhoneNumber());
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getContractNo())) {
            sql.append(" and UPPER(TK.CONTRACT_NO) = :contractNo \n");
            hmapParams.put("contractNo", itemParamsEntity.getContractNo().toUpperCase());
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getPlateNumber())) {
            sql.append(" and UPPER(TK.PLATE_NUMBER) = :plateNumber \n");
            hmapParams.put("plateNumber", itemParamsEntity.getPlateNumber().toUpperCase());
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getPlateTypeCode())) {
            sql.append(" and UPPER(TK.PLATE_TYPE_CODE) = :plateTypeCode \n");
            hmapParams.put("plateTypeCode", itemParamsEntity.getPlateTypeCode().toUpperCase());
        }

        if (itemParamsEntity.getStartDate() != null) {
            sql.append("and TK.CREATE_DATE  >= :startDate \n");
            hmapParams.put("startDate", new java.sql.Date(itemParamsEntity.getStartDate().getTime()));
        }

        if (itemParamsEntity.getEndDate() != null) {
            sql.append("and TK.CREATE_DATE  < :endDate \n");
            hmapParams.put("endDate", new java.sql.Date(itemParamsEntity.getEndDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        sql.append(" ORDER BY TK.CREATE_DATE DESC \n");
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }

        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketHistoryListDTO.class);
    }

    /***
     * Tim kiem phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity searchTicket(SearchTicketDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                                                                                      \n");
        sql.append("    TK.TICKET_ID                                                as ticketId,                    --1.Mã phản ánh                                             \n");
        sql.append("    TK.PLATE_NUMBER                                             as plateNumber,                 --2.Biển số xe                                              \n");
        sql.append("    TK.PHONE_CONTACT                                            as phoneContact,                --Số điện thoại liên hệ                                     \n");
        sql.append("    TK.TICKET_TIMES                                             as ticketTimes,                 --Số lần phản ánh                                           \n");
        sql.append("    TK.STAGE_ID                                                 as stageId,                     --Mã đoạn                                                   \n");
        sql.append("    TK.STAGE_NAME                                               as stageName,                   --Tên đoạn                                                  \n");
        sql.append("    TK.STATION_ID                                               as stationId,                   --Mã trạm                                                   \n");
        sql.append("    TK.STATION_NAME                                             as stationName,                 --Tên trạm                                                  \n");
        sql.append("    TK.CONTRACT_NO                                              as contractNo,                  --3.Số hợp đồng                                             \n");
        sql.append("    TK.CONTRACT_ID                                              as contractId,                  --3.Id hợp đồng                                             \n");
        sql.append("    TK.CUST_NAME                                                as custName,                    --4.Người phản ánh                                          \n");
        sql.append("    TK.CUST_ADDRESS                                             as custAddress,                 --5.Địa chỉ                                                 \n");
        sql.append("    TK.CREATE_USER                                              as createUser,                  --7.Người tiếp nhận                                         \n");
        sql.append("    TA.TO_SITE_EMAIL                                            as toSiteEmail,                                                                             \n");
        sql.append("    TK.CREATE_DATE                                              as createDate,                  --Thời điểm tiếp nhận phản ánh                              \n");
        sql.append("    to_char (TK.CREATE_DATE,'dd/mm/yyyy' )                      as receiveDate,                 --Ngày tiếp nhận                                            \n");
        sql.append("    to_char (TK.CREATE_DATE,'hh24:mi:ss' )                      as receiveTime,                 --Giờ tiếp nhận                                             \n");
        sql.append("    TA.ASSIGN_DATE                                              as assignDate,                  --8.Thời điểm tiếp nhận phối hợp xử lý phản ánh             \n");
        sql.append("    TKT1.NAME                                                   as groupPA,                     --10.Nhóm phản ánh                                          \n");
        sql.append("    TK.L1_TICKET_TYPE_ID                                        as l1TicketTypeId,              --  Id Nhóm phản ánh                                        \n");
        sql.append("    TKT2.NAME                                                   as subgroupPA,                  --11.Thể loại phản ánh                                      \n");
        sql.append("    TK.L2_TICKET_TYPE_ID                                        as l2TicketTypeId,              --   Id Thể lại phản ánh                                    \n");
        sql.append("    TKT3.NAME                                                   as detailPA,                    --12.Loại Phản ánh                                          \n");
        sql.append("    TK.L3_TICKET_TYPE_ID                                        as l3TicketTypeId,              --Id Loại phản ánh                                          \n");
        sql.append("    TSU.SITE_ID                                                 as siteId,                      --Mã đơn vị CSKH                                            \n");
        sql.append("    TK.SOURCE_ID                                                as sourceId,                    --Mã hình thức tiếp nhận                                    \n");
        sql.append("    TS.NAME                                                     as sourceName,                  --13.Hình thức tiếp nhận                                    \n");
        sql.append("    TK.PRIORITY_ID                                              as priorityId,                  --14.Ưu tiên                                                \n");
        sql.append("    DBMS_LOB.SUBSTR(TK.CONTENT_RECEIVE)                         as contentReceive,              --15.Nội dung phản ánh                                      \n");
        sql.append("    TK.REQUEST_DATE                                             as requestDate,                 --16.Ngày hẹn trả lời khách hàng                            \n");
        sql.append("    TA.UPDATE_DATE                                              as concludeProcessTime,         --17.Thời điểm kết thúc xử lý phản ánh                      \n");
        sql.append("    TA.ASSIGN_CONTENT                                           as assignContent,               --18.Nội dung xử lý                                         \n");
        sql.append("    TSI1.SITE_NAME                                              as toSiteName,                  --19.Tên đơn vị phối hợp                                    \n");
        sql.append("    TA.TO_SITE_ID                                               as toSiteId,                    --   Mã đơn vị phối hợp                                     \n");
        sql.append("    TK.STATUS                                                   as status,                      --20.Trạng thái phản ánh                                    \n");
        sql.append("    TA.UPDATE_USER                                              as staffName,                   --21.NGười xử lý                                            \n");
        sql.append("    TK.TICKET_KIND                                              as ticketKind,                  --24.Lỗi phát sinh                                          \n");
        sql.append("    TK.AREA_CODE                                                as areaCode,                    --27.28.29.Tỉnh/huyện/xã                                    \n");
        sql.append("    TP.PROCESS_TIME                                             as processTime,                 --32.Thời điểm đóng phản ánh                                \n");
        sql.append("    TK.UPDATE_USER                                              as updateUser,                                                                              \n");
        sql.append("    TK.LOCATION                                                 as location,                                                                                \n");
        sql.append("    CASE WHEN TK.STATUS = 5 then to_char (TK.UPDATE_DATE,'dd/mm/yyyy') else '' end closeDateTicket,          --Ngày đóng phản ánh                           \n");
        sql.append("    CASE WHEN TK.STATUS = 5 then to_char (TK.UPDATE_DATE,'hh24:mi:ss') else '' end closeHourTicket,          --Giờ đóng phản ánh                            \n");
        sql.append("    CASE TK.STATUS                                                                                                                                          \n");
        sql.append("        WHEN 5   THEN ROUND(( TK.UPDATE_DATE - TK.CREATE_DATE )*24,2)                                                                                       \n");
        sql.append("        ELSE NULL                                                                                                                                           \n");
        sql.append("    END                                                         as totalProcessTime,            --Tổng thời gian xử lý(theo giờ)                            \n");
        sql.append("    ROUND(NVL((NVL(TA.UPDATE_DATE,SYSDATE)-TA.CREATE_DATE),0),2) as totalInnerProcessTime,       --Tổng thời gian phối hợp xử lý(theo ngay)                 \n");
        sql.append("    CASE                                                                                                                                                    \n");
        sql.append("        WHEN ROUND(((SYSDATE - TA.SLA_DATE)*24),2) <= 0 THEN 0                                                                                              \n");
        sql.append("        ELSE ROUND(((SYSDATE - TA.SLA_DATE)*24),2)                                                                                                          \n");
        sql.append("    END AS expireTime,   --Tổng thời gian quá hạn(theo giờ)                                                                                                 \n");
        sql.append("    CASE                                                                                                                                                    \n");
        sql.append("        WHEN TK.SLA_DATE - SYSDATE > 0 AND TK.SLA_DATE is not null THEN '1'                                                                                 \n");
        sql.append("        ELSE '2'                                                                                                                                            \n");
        sql.append("    END AS expireStatus,                                                                                                                                    \n");
        sql.append("    case when TK.STATUS = 5 and TK.update_date > TK.sla_date then 'Quá Hạn'                                                                                                                                    \n");
        sql.append("    when TK.STATUS = 5 and (TK.update_date <= TK.sla_date or TK.sla_date is null )then 'Trong Hạn'                                                                                                                                    \n");
        sql.append("    when TK.STATUS <> 5 and (sysdate <= TK.sla_date or TK.sla_date is null) then 'Trong Hạn'                                                                                                                                    \n");
        sql.append("    when TK.STATUS <> 5 and sysdate > TK.sla_date then 'Quá Hạn'                                                                                                                                     \n");
        sql.append("    END AS processingProgress,                                                                                                                                    \n");
        sql.append("    TA.SLA_DATE                                                 as slaDate,                     --33.Thời điểm hạn cuối phải giải quyết                     \n");
        sql.append("    TK.SLA_DATE                                                 as deadlineProcess,             --Hạn xử lý                                                 \n");
        sql.append("    TK.PLATE_TYPE_CODE                                          as plateTypeCode,               -- Loại biển                                                \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L1                                 as ticketErrorCauseIdL1,        --Nguyên nhân lỗi cấp 1                                     \n");
        sql.append("    TEC1.NAME                                                   as ticketErrorCauseIdL1Name,    --Nguyên nhân lỗi cấp 1                                     \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L2                                 as ticketErrorCauseIdL2,        --Nguyên nhân lỗi cấp 2                                     \n");
        sql.append("    TEC2.NAME                                                   as ticketErrorCauseIdL2Name,    --Nguyên nhân lỗi cấp 2                                     \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L3                                 as ticketErrorCauseIdL3,        --Nguyên nhân lỗi cấp 3                                     \n");
        sql.append("    TEC3.NAME                                                   as ticketErrorCauseIdL3Name,    --Nguyên nhân lỗi cấp 3                                     \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_ID_L1                                as ticketExpireCauseIdL1,        --Mã nguyên nhân quá hạn cấp 1                             \n");
        sql.append("    TEC4.NAME                                                   as ticketExpireCauseIdL1Name,    --Tên nguyên nhân quá hạn cấp 1                            \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_ID_L2                                as ticketExpireCauseIdL2,        --Mã nguyên nhân quá hạn cấp 2                             \n");
        sql.append("    TEC5.NAME                                                   as ticketExpireCauseIdL2Name,    --Tên nguyên nhân quá hạn cấp 2                            \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_ID_L3                                as ticketExpireCauseIdL3,        --Mã nguyên nhân quá hạn cấp 3                             \n");
        sql.append("    TEC5.NAME                                                   as ticketExpireCauseIdL3Name,    --Tên nguyên nhân quá hạn cấp 3                            \n");
        sql.append("    TP.TICKET_EXPIRE_SITE_ID                                    as ticketExpireSiteId,          --Mã đơn vị chịu trách nhiệm quá hạn                        \n");
        sql.append("    TSI6.SITE_NAME                                              as ticketExpireSiteName,        --Tên đơn vị chịu trách nhiệm quá hạn                       \n");
        sql.append("    TP.TICKET_SITE_ID_L1                                        as ticketSiteIdL1,              --Đơn vị chịu trách nhiệm (cấp 1)                           \n");
        sql.append("    TSI3.SITE_NAME                                              as ticketSiteIdL1Name,          --Đơn vị chịu trách nhiệm (cấp 1)                           \n");
        sql.append("    TP.TICKET_SITE_ID_L2                                        as ticketSiteIdL2,              --Đơn vị chịu trách nhiệm (cấp 2)                           \n");
        sql.append("    TSI4.SITE_NAME                                              as ticketSiteIdL2Name,          --Đơn vị chịu trách nhiệm (cấp 2)                           \n");
        sql.append("    TP.PROCESS_CONTENT                                          as processContent,              --Nội dung xử lý PA                                         \n");
        sql.append("    TP.CREATE_USER                                              as processCreateUser,           --Người xử lý PA                                            \n");
        sql.append("    TP.TICKET_SITE_ID_L3                                        as ticketSiteIdL3,              --Đơn vị chịu trách nhiệm (cấp 3)                           \n");
        sql.append("    TSI5.SITE_NAME                                              as ticketSiteIdL3Name,          --Đơn vị chịu trách nhiệm (cấp 3)                           \n");
        sql.append("    TA.TO_SITE_L2_ID                                            as toSiteL2Id,                  --Mã đơn vị phối hợp cap 2                                  \n");
        sql.append("    TSI2.SITE_NAME                                              as toSiteL2Name,                --Tên đơn vị Phối hợp cap 2                                 \n");
        sql.append("    TK.CUST_TYPE_ID                                             as custTypeId,                  --Loại khách hàng                                           \n");
        sql.append("    TK.PHONE_NUMBER                                             as phoneNumber,                 --Số điện thoại liên hệ                                     \n");
        sql.append("    TK.SUPPORT_INFO                                             as supportInfo,                 --Thông tin bổ sung                                         \n");
        sql.append("    TK.PROVINCE_NAME                                            as provinceName,                --Tỉnh                                                      \n");
        sql.append("    TK.DISTRICT_NAME                                            as districtName,                --Huyện                                                     \n");
        sql.append("    TK.COMMUNE_NAME                                             as communeName,                 --Xã                                                        \n");
        sql.append("    TAP.CREATE_USER                                             as userHandle,                  --Người phối hợp xử lý                                      \n");
        sql.append("    TAP.CREATE_DATE                                             as dateHandle,                  --Thời gian phối hợp                                        \n");
        sql.append("    TK.CUST_ID                                                  as custId                                                                                   \n");
        sql.append("FROM                                                                                                                                                        \n");
        sql.append("    TICKET TK                                                                                                                                               \n");
        sql.append("    LEFT JOIN TICKET_ASSIGN TA ON TA.TICKET_ID = TK.TICKET_ID                                                                                               \n");
        sql.append("    LEFT JOIN TICKET_SOURCE TS ON TS.TICKET_SOURCE_ID = TK.SOURCE_ID                                                                                        \n");
        sql.append("    LEFT JOIN TICKET_PROCESS TP ON TP.TICKET_ID = TK.TICKET_ID                                                                                              \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT1  ON TKT1.TICKET_TYPE_ID = TK.L1_TICKET_TYPE_ID                                                                               \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT2  ON TKT2.TICKET_TYPE_ID = TK.L2_TICKET_TYPE_ID                                                                               \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT3  ON TKT3.TICKET_TYPE_ID = TK.L3_TICKET_TYPE_ID                                                                               \n");
        sql.append("    LEFT JOIN TICKET_SITE TSI1  ON TSI1.SITE_ID = TA.TO_SITE_ID                                                                                             \n");
        sql.append("    LEFT JOIN TICKET_SITE TSI2  ON TSI2.SITE_ID = TA.TO_SITE_L2_ID                                                                                          \n");
        sql.append("    LEFT JOIN TICKET_ASSIGN_PROCESS TAP  ON TAP.TICKET_ID = TA.TICKET_ID                                                                                    \n");
        sql.append(" AND TAP.TICKET_ASSIGN_ID = TA.TICKET_ASSIGN_ID AND TAP.STATUS =                                                                                            \n");
        sql.append(TicketAssignProcessEntity.Status.IN_PROGRESS.value + "                                                                                                       \n");
        sql.append("    LEFT JOIN TICKET_SITE TSI3  ON TSI3.SITE_ID = TP.TICKET_SITE_ID_L1                                                                                      \n");
        sql.append("    LEFT JOIN TICKET_SITE TSI4  ON TSI4.SITE_ID = TP.TICKET_SITE_ID_L2                                                                                      \n");
        sql.append("    LEFT JOIN TICKET_SITE TSI5  ON TSI5.SITE_ID = TP.TICKET_SITE_ID_L3                                                                                      \n");
        sql.append("    LEFT JOIN TICKET_SITE TSI6  ON TSI6.SITE_ID = TP.TICKET_EXPIRE_SITE_ID                                                                                  \n");
        sql.append("    LEFT JOIN TICKET_ERROR_CAUSE TEC1  ON TEC1.TICKET_ERROR_CAUSE_ID = TP.TICKET_ERROR_CAUSE_ID_L1                                                          \n");
        sql.append("    LEFT JOIN TICKET_ERROR_CAUSE TEC2  ON TEC2.TICKET_ERROR_CAUSE_ID = TP.TICKET_ERROR_CAUSE_ID_L2                                                          \n");
        sql.append("    LEFT JOIN TICKET_ERROR_CAUSE TEC3  ON TEC3.TICKET_ERROR_CAUSE_ID = TP.TICKET_ERROR_CAUSE_ID_L3                                                          \n");
        sql.append("    LEFT JOIN TICKET_EXPIRE_CAUSE TEC4  ON TEC4.TICKET_EXPIRE_CAUSE_ID = TP.TICKET_EXPIRE_CAUSE_ID_L1                                                       \n");
        sql.append("    LEFT JOIN TICKET_EXPIRE_CAUSE TEC5  ON TEC5.TICKET_EXPIRE_CAUSE_ID = TP.TICKET_EXPIRE_CAUSE_ID_L2                                                       \n");
        sql.append("    LEFT JOIN TICKET_EXPIRE_CAUSE TEC6  ON TEC6.TICKET_EXPIRE_CAUSE_ID = TP.TICKET_EXPIRE_CAUSE_ID_L3                                                       \n");
        sql.append("    LEFT JOIN TICKET_SITE_USER TSU  ON UPPER(TSU.USER_NAME) = UPPER(TK.CREATE_USER)                                                                         \n");
        sql.append(" WHERE 1 = 1                                                                                                                                                \n");
        List<Object> list;
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getPhoneNumber())) {
            list = Arrays.stream(itemParamsEntity.getPhoneNumber().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            FnCommon.appendSQL("TK.PHONE_NUMBER like :phoneNumber", hmapParams, list, sql);
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getContractNo())) {
            list = Arrays.stream(itemParamsEntity.getContractNo().toUpperCase().split(";")).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            FnCommon.appendSQL(" UPPER(TK.CONTRACT_NO) like :contractNo", hmapParams, list, sql);
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getPlateNumber())) {
            list = Arrays.stream(itemParamsEntity.getPlateNumber().toUpperCase().split(";")).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            FnCommon.appendSQL(" UPPER(TK.PLATE_NUMBER) like :plateNumber", hmapParams, list, sql);
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getPlateTypeCode())) {
            list = Arrays.stream(itemParamsEntity.getPlateTypeCode().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            FnCommon.appendSQL(" UPPER(TK.PLATE_TYPE_CODE) like :plateTypeCode", hmapParams, list, sql);
        }

        if (itemParamsEntity.getStartReceiveDate() != null) {
            sql.append("and TK.CREATE_DATE >= :startReceiveDate \n");
            hmapParams.put("startReceiveDate", new java.sql.Date(itemParamsEntity.getStartReceiveDate().getTime()));
        }

        if (itemParamsEntity.getEndReceiveDate() != null) {
            sql.append("and TK.CREATE_DATE < :endReceiveDate \n");
            hmapParams.put("endReceiveDate", new java.sql.Date(itemParamsEntity.getEndReceiveDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        if (itemParamsEntity.getStartProcessDate() != null) {
            sql.append("and TP.PROCESS_TIME >= :startProcessDate \n");
            hmapParams.put("startProcessDate", new java.sql.Date(itemParamsEntity.getStartProcessDate().getTime()));
        }

        if (itemParamsEntity.getEndProcessDate() != null) {
            sql.append("and TP.PROCESS_TIME < :endProcessDate \n");
            hmapParams.put("endProcessDate", new java.sql.Date(itemParamsEntity.getEndProcessDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getTicketId())) {
            list = Arrays.stream(itemParamsEntity.getTicketId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" TK.TICKET_ID like :ticketId", hmapParams, list, sql);
            } else {
                sql.append(" and TK.TICKET_ID like '%'|| :ticketId || '%' \n");
                hmapParams.put("ticketId", itemParamsEntity.getTicketId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getPriorityId())) {
            list = Arrays.stream(itemParamsEntity.getPriorityId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" TK.PRIORITY_ID like :priorityId", hmapParams, list, sql);
            } else {
                sql.append(" and TK.PRIORITY_ID like '%'|| :priorityId || '%' \n");
                hmapParams.put("priorityId", itemParamsEntity.getPriorityId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getSourceId())) {
            list = Arrays.stream(itemParamsEntity.getSourceId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" TK.SOURCE_ID like :sourceId", hmapParams, list, sql);
            } else {
                sql.append(" and TK.SOURCE_ID like '%'|| :sourceId || '%' \n");
                hmapParams.put("sourceId", itemParamsEntity.getSourceId());
            }

        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL1TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL1TicketTypeId().split(";")).collect(Collectors.toList());
            if (list.size() > 1) {
                sql.append(" AND TK.L1_TICKET_TYPE_ID IN (:listL1TicketTypeId)");
                hmapParams.put("listL1TicketTypeId",list);
            } else {
                sql.append(" and TK.L1_TICKET_TYPE_ID =:l1TicketTypeId \n");
                hmapParams.put("l1TicketTypeId", itemParamsEntity.getL1TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL2TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL2TicketTypeId().split(";")).collect(Collectors.toList());
            if (list.size() > 1) {
                sql.append(" AND TK.L2_TICKET_TYPE_ID IN (:listL2TicketTypeId)");
                hmapParams.put("listL2TicketTypeId",list);
            } else {
                sql.append(" and TK.L2_TICKET_TYPE_ID =:l2TicketTypeId \n");
                hmapParams.put("l2TicketTypeId", itemParamsEntity.getL2TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL3TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL3TicketTypeId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 1) {
                sql.append(" AND TK.L3_TICKET_TYPE_ID IN (:l3TicketTypeId)");
                hmapParams.put("l3TicketTypeId",list);
            } else {
                sql.append(" and TK.L3_TICKET_TYPE_ID = :l3TicketTypeId \n");
                hmapParams.put("l3TicketTypeId", itemParamsEntity.getL3TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getStatus())) {
            list = Arrays.stream(itemParamsEntity.getStatus().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" NVL(TA.TICKET_STATUS,TK.STATUS) like  :status", hmapParams, list, sql);
            } else {
                sql.append(" and NVL(TA.TICKET_STATUS,TK.STATUS) like '%'|| :status || '%' \n");
                hmapParams.put("status", itemParamsEntity.getStatus());
            }

        }
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getProvinceName())) {
            sql.append(" and UPPER(TK.PROVINCE_NAME) like '%'|| :provinceName || '%' \n");
            hmapParams.put("provinceName", itemParamsEntity.getProvinceName().toUpperCase());
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getDistrictName())) {
            sql.append(" and UPPER(TK.DISTRICT_NAME) like '%'|| :districtName || '%' \n");
            hmapParams.put("districtName", itemParamsEntity.getDistrictName().toUpperCase());
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getCommuneName())) {
            sql.append(" and UPPER(TK.COMMUNE_NAME) like '%'|| :communeName || '%' \n");
            hmapParams.put("communeName", itemParamsEntity.getCommuneName().toUpperCase());
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getCreateUser())) {
            list = Arrays.stream(itemParamsEntity.getCreateUser().toUpperCase().split(";")).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            FnCommon.appendSQL(" UPPER(TK.CREATE_USER)  like :createUser", hmapParams, list, sql);
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getStaffName())) {
            list = Arrays.stream(itemParamsEntity.getStaffName().toUpperCase().split(";")).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            FnCommon.appendSQL(" UPPER(TP.STAFF_NAME)  like :staffName", hmapParams, list, sql);
        }

        sql.append(" ORDER BY TK.CREATE_DATE DESC \n");

        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        return getListDataAndCount(sql, hmapParams, start, pageSize, SearchTicketDTO.class);
    }


    /**
     * Get thong tin phan anh, gop y
     *
     * @param itemParamsEntity
     * @return
     */
    @Override
    public List<TicketDTO> getTicket(TicketDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("select \n" +
                "    case when tt1.ticket_type_id = 999 then concat(tt1.name, concat(': ', t.feed_back)) else tt1.name end as l1TicketTypeName,\n" +
                "    tt2.name as l2TicketTypeName,\n" +
                "    tt3.name as l3TicketTypeName,\n" +
                "    t.create_date as createDate,\n" +
                "    DBMS_LOB.SUBSTR(t.content_receive) as contentReceive,\n" +
                "    case \n" +
                "        when t.status = 1 then 'Tạo mới'\n" +
                "        when t.status = 2 then 'Đang xử lý'\n" +
                "        when t.status = 3 then 'Xử lý xong'\n" +
                "        when t.status = 5 then 'Đóng phản ánh'\n" +
                "        when t.status = 6 then 'Hủy phản ánh'\n" +
                "        when t.status = 7 then 'Theo dõi'\n" +
                "    else '' end as statusName,\n" +
                "    tp.process_time as processTime,\n" +
                "    tp.staff_name as staffName,\n" +
                "    tp.process_content as processContent,\n" +
                "    ta.file_name as fileName,\n" +
                "    ta.attachment_id as attachmentId\n" +
                "from ticket t \n" +
                "left join ticket_process tp on t.ticket_id = tp.ticket_id\n" +
                "left join ticket_type tt1 on t.l1_ticket_type_id = tt1.ticket_type_id\n" +
                "left join ticket_type tt2 on t.l2_ticket_type_id = tt2.ticket_type_id\n" +
                "left join ticket_type tt3 on t.l3_ticket_type_id = tt3.ticket_type_id\n" +
                "left join (\n" +
                "select \n" +
                "    LISTAGG(file_name, '|') WITHIN GROUP (ORDER BY attachment_id) file_name,\n" +
                "    LISTAGG(attachment_id, '|') WITHIN GROUP (ORDER BY attachment_id) attachment_id,\n" +
                "    ticket_id\n" +
                "from ticket_attachment where type = 1\n" +
                "group by ticket_id\n" +
                ") ta on t.ticket_id = ta.ticket_id where t.contract_no = :contractNo");
        hmapParams.put("contractNo", FnCommon.getUserLogin(authentication));
        if (itemParamsEntity.getTicketChannel() != null) {
            sql.append(" and t.TICKET_CHANNEL = :ticketChannel \n");
            hmapParams.put("ticketChannel", itemParamsEntity.getTicketChannel());
        }
        sql.append(" order by T.CREATE_DATE DESC");
        return (List<TicketDTO>) getListDataAndCount(sql, hmapParams, null, null, TicketDTO.class).getListData();
    }


    /**
     * Get thong tin phan anh, gop y chua assign
     *
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketNotAssign(SearchTicketDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT \n");
        sql.append("    t.TICKET_ID                         as ticketId,        \n");
        sql.append("    DBMS_LOB.SUBSTR(t.CONTENT_RECEIVE)  as contentReceive,  \n");
        sql.append("    t.STATUS                            as status,          \n");
        sql.append("    t.CONTRACT_NO                       as contractNo,      \n");
        sql.append("    t.CONTRACT_ID                       as contractId,      \n");
        sql.append("    t.CUST_ID                           as custId,          \n");
        sql.append("    t.CUST_NAME                         as custName,        \n");
        sql.append("    t.PRIORITY_ID                       as priorityId,      \n");
        sql.append("    t.SOURCE_ID                         as sourceId,        \n");
        sql.append("    t.L1_TICKET_TYPE_ID                 as l1TicketTypeId,  \n");
        sql.append("    t.L2_TICKET_TYPE_ID                 as l2TicketTypeId,  \n");
        sql.append("    t.L3_TICKET_TYPE_ID                 as l3TicketTypeId,  \n");
        sql.append("    t.TICKET_CHANNEL                    as ticketChannel,   \n");
        sql.append("    t.CREATE_DATE                       as createDate,      \n");
        sql.append("    t.CREATE_USER                       as createUser,      \n");
        sql.append("    t.PROCESS_USER                      as processUser,     \n");
        sql.append("    tt1.NAME                            as l1TicketTypeName,\n");
        sql.append("    tt2.NAME                            as l2TicketTypeName,\n");
        sql.append("    tt3.NAME                            as l3TicketTypeName \n");
        sql.append("FROM TICKET t \n");
        sql.append("LEFT JOIN TICKET_TYPE tt1 on t.L1_TICKET_TYPE_ID = tt1.TICKET_TYPE_ID\n");
        sql.append("LEFT JOIN TICKET_TYPE tt2 on t.L2_TICKET_TYPE_ID = tt2.TICKET_TYPE_ID\n");
        sql.append("LEFT JOIN TICKET_TYPE tt3 on t.L3_TICKET_TYPE_ID = tt3.TICKET_TYPE_ID\n");
        sql.append("WHERE t.PROCESS_USER is null and t.STATUS = 1\n");
        List<Object> list;
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getTicketId())) {
            sql.append(" and t.TICKET_ID like :ticketId \n");
            hmapParams.put("ticketId", itemParamsEntity.getTicketId());
        }

        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getContractNos())) {
            List<String> contractNos = Arrays.asList(itemParamsEntity.getContractNos().trim().toUpperCase().split(";"));
            sql.append(" and t.CONTRACT_NO in (:contractNo) \n");
            hmapParams.put("contractNo", contractNos);
        }

        if (itemParamsEntity.getFromDate() != null) {
            sql.append("and t.CREATE_DATE >= :fromDate \n");
            hmapParams.put("fromDate", new java.sql.Date(itemParamsEntity.getFromDate().getTime()));
        }

        if (itemParamsEntity.getToDate() != null) {
            sql.append("and t.CREATE_DATE < :toDate \n");
            hmapParams.put("toDate", new java.sql.Date(itemParamsEntity.getToDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL1TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL1TicketTypeId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.L1_TICKET_TYPE_ID like :l1TicketTypeId", hmapParams, list, sql);
            } else {
                sql.append(" and t.L1_TICKET_TYPE_ID like '%'|| :l1TicketTypeId || '%' \n");
                hmapParams.put("l1TicketTypeId", itemParamsEntity.getL1TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL2TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL2TicketTypeId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.L2_TICKET_TYPE_ID like :l2TicketTypeId", hmapParams, list, sql);
            } else {
                sql.append(" and t.L2_TICKET_TYPE_ID like '%'|| :l2TicketTypeId || '%' \n");
                hmapParams.put("l2TicketTypeId", itemParamsEntity.getL2TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL3TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL3TicketTypeId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.L3_TICKET_TYPE_ID like :l3TicketTypeId", hmapParams, list, sql);
            } else {
                sql.append(" and t.L3_TICKET_TYPE_ID like '%'|| :l3TicketTypeId || '%' \n");
                hmapParams.put("l3TicketTypeId", itemParamsEntity.getL3TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getStatus())) {
            list = Arrays.stream(itemParamsEntity.getStatus().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL("t.STATUS like :status", hmapParams, list, sql);
            } else {
                sql.append(" and t.STATUS like '%'|| :status || '%' \n");
                hmapParams.put("status", itemParamsEntity.getStatus());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getPriorityId())) {
            list = Arrays.stream(itemParamsEntity.getPriorityId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.PRIORITY_ID like :priorityId", hmapParams, list, sql);
            } else {
                sql.append(" and t.PRIORITY_ID like '%'|| :priorityId || '%' \n");
                hmapParams.put("priorityId", itemParamsEntity.getPriorityId());
            }
        }

        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        sql.append(" order by t.CREATE_DATE DESC");
        return getListDataAndCount(sql, hmapParams, start, pageSize, SearchTicketDTO.class);
    }

    /**
     * Get thong tin phan anh, gop y chua assign
     *
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketReportPerformmance(SearchTicketDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hashMap = new HashMap<>();
        sql.append("SELECT \n");
        sql.append("    to_char(updateDateReport, 'dd/mm/yyyy') updateDateReport, l1TicketTypeName, l2TicketTypeName, l3TicketTypeName, updateUser, \n");
        sql.append("    sum(inDueDate) inDueDate, sum(outOfDate) outOfDate, sum(closeQuantity) closeQuantity            \n");
        sql.append("FROM                                                                                                \n");
        sql.append("    (SELECT                                                                                         \n");
        sql.append("    t.CREATE_DATE                       as createDate,                                              \n");
        sql.append("    t.SOURCE_ID                         as sourceId,                                                \n");
        sql.append("    t.L1_TICKET_TYPE_ID                 as l1TicketTypeId,                                          \n");
        sql.append("    t.L2_TICKET_TYPE_ID                 as l2TicketTypeId,                                          \n");
        sql.append("    t.L3_TICKET_TYPE_ID                 as l3TicketTypeId,                                          \n");
        sql.append("    t.UPDATE_DATE                       as updateDateReport,                                        \n");
        sql.append("    t.UPDATE_USER                       as updateUser,                                              \n");
        sql.append("    tt1.NAME                            as l1TicketTypeName,                                        \n");
        sql.append("    tt2.NAME                            as l2TicketTypeName,                                        \n");
        sql.append("    tt3.NAME                            as l3TicketTypeName,                                        \n");
        sql.append("    CASE WHEN (t.STATUS = 5) THEN 1 ELSE 0 END closeQuantity,                                       \n");
        sql.append("    CASE WHEN (t.UPDATE_DATE <= t.SLA_DATE and t.STATUS = 5 ) THEN 1 ELSE 0 END inDueDate,          \n");
        sql.append("    CASE WHEN (t.UPDATE_DATE > t.SLA_DATE and t.STATUS = 5) THEN 1 ELSE 0 END outOfDate             \n");
        sql.append("FROM TICKET t                                                                                       \n");
        sql.append("LEFT JOIN TICKET_TYPE tt1 on t.L1_TICKET_TYPE_ID = tt1.TICKET_TYPE_ID                               \n");
        sql.append("LEFT JOIN TICKET_TYPE tt2 on t.L2_TICKET_TYPE_ID = tt2.TICKET_TYPE_ID                               \n");
        sql.append("LEFT JOIN TICKET_TYPE tt3 on t.L3_TICKET_TYPE_ID = tt3.TICKET_TYPE_ID                               \n");
        sql.append("LEFT JOIN TICKET_SOURCE tks on t.SOURCE_ID = tks.TICKET_SOURCE_ID                                   \n");
        sql.append("WHERE 1 = 1                                                                                         \n");
        List<Object> list;
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getProcessUser())) {
            sql.append(" and UPPER(t.UPDATE_USER) like '%'|| :updateUser || '%' \n");
            hashMap.put("updateUser", itemParamsEntity.getProcessUser().toUpperCase());
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL1TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL1TicketTypeId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.L1_TICKET_TYPE_ID like :l1TicketTypeId", hashMap, list, sql);
            } else {
                sql.append(" and t.L1_TICKET_TYPE_ID like '%'|| :l1TicketTypeId || '%' \n");
                hashMap.put("l1TicketTypeId", itemParamsEntity.getL1TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL2TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL2TicketTypeId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.L2_TICKET_TYPE_ID like :l2TicketTypeId", hashMap, list, sql);
            } else {
                sql.append(" and t.L2_TICKET_TYPE_ID like '%'|| :l2TicketTypeId || '%' \n");
                hashMap.put("l2TicketTypeId", itemParamsEntity.getL2TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getL3TicketTypeId())) {
            list = Arrays.stream(itemParamsEntity.getL3TicketTypeId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.L3_TICKET_TYPE_ID like :l3TicketTypeId", hashMap, list, sql);
            } else {
                sql.append(" and t.L3_TICKET_TYPE_ID like '%'|| :l3TicketTypeId || '%' \n");
                hashMap.put("l3TicketTypeId", itemParamsEntity.getL3TicketTypeId());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getSourceId())) {
            list = Arrays.stream(itemParamsEntity.getSourceId().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" t.SOURCE_ID like :sourceId", hashMap, list, sql);
            } else {
                sql.append(" and t.SOURCE_ID like '%'|| :sourceId || '%' \n");
                hashMap.put("sourceId", itemParamsEntity.getSourceId());
            }

        }

        if (itemParamsEntity.getFromDate() != null) {
            sql.append("and t.CREATE_DATE >= :fromDate \n");
            hashMap.put("fromDate", new java.sql.Date(itemParamsEntity.getFromDate().getTime()));
        }

        if (itemParamsEntity.getToDate() != null) {
            sql.append("and t.CREATE_DATE < :toDate \n");
            hashMap.put("toDate", new java.sql.Date(itemParamsEntity.getToDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }
        sql.append(")WHERE 1 = 1                                                                                        \n");
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        sql.append("group by to_char(updateDateReport, 'dd/mm/yyyy'), l1TicketTypeName, l2TicketTypeName, l3TicketTypeName, updateUser \n");
        sql.append("order by to_date(updateDateReport, 'dd/mm/yyyy') DESC \n");
        return getListDataAndCount(sql, hashMap, start, pageSize, SearchTicketDTO.class);
    }
}
