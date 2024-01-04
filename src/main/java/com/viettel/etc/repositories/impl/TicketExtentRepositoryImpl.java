package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketExtentDTO;
import com.viettel.etc.repositories.TicketExtentRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Fri Jan 07 16:32:08 ICT 2022
 */
@Repository
public class TicketExtentRepositoryImpl extends CommonDataBaseRepository implements TicketExtentRepository {

    /**
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getTicketExtent(TicketExtentDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                                                \n");
        sql.append(" TK.TICKET_ID                                as ticketId,                    --1.Mã phản ánh                          \n");
        sql.append(" TK.CONTRACT_NO                              as contractNo,                  --3.Số hợp đồng                          \n");
        sql.append(" TKT1.NAME                                   as groupPA,                     --Nhóm phản ánh                          \n");
        sql.append(" TK.L1_TICKET_TYPE_ID                        as l1TicketTypeId,              --Nhóm phản ánh                          \n");
        sql.append(" TKT2.NAME                                   as subgroupPA,                  --Thể loại phản ánh                      \n");
        sql.append(" TK.L2_TICKET_TYPE_ID                        as l2TicketTypeId,              --Id Thể lại phản ánh                    \n");
        sql.append(" TKT3.NAME                                   as detailPA,                    --Loại Phản ánh                          \n");
        sql.append(" TK.L3_TICKET_TYPE_ID                        as l3TicketTypeId,              --Id Loại phản ánh                       \n");
        sql.append(" TK.SLA_DATE                                 as deadlineProcess,             --Hạn xử lý                              \n");
        sql.append(" TE.MANAGER_USER_NAME                        as managerUserName,             --Nhân viên xin gia hạn                  \n");
        sql.append(" TS.SITE_NAME                                as siteName,                    --Đơn vị xử lý                           \n");
        sql.append(" TE.EXTENT_DATE                              as extentDate,                  --Ngày xin gia hạn                       \n");
        sql.append(" TE.REQUEST_EXTENT_DATE                      as requestExtentDate,           --Gia hạn đến ngày giờ                   \n");
        sql.append(" DBMS_LOB.SUBSTR(TK.CONTENT_RECEIVE)         as contentReceive,              --Nội dung phản ánh                      \n");
        sql.append(" TE.STATUS                                   as status,                      --Gia hạn đến ngày giờ                   \n");
        sql.append(" TE.APPROVE_REASON                           as approveReason,               --Lý do từ chối                          \n");
        sql.append(" TE.APPROVE_USER_NAME                        as approveUserName,             --Nhân viên phê duyệt                    \n");
        sql.append(" TE.CREATE_DATE                              as createDate,                  --Ngày phê duyệt                         \n");
        sql.append(" TA.TO_SITE_ID                               as toSiteId                                                              \n");
        sql.append("FROM                                                                                                                  \n");
        sql.append("    TICKET TK                                                                                                         \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT1  ON TKT1.TICKET_TYPE_ID = TK.L1_TICKET_TYPE_ID                                         \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT2  ON TKT2.TICKET_TYPE_ID = TK.L2_TICKET_TYPE_ID                                         \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT3  ON TKT3.TICKET_TYPE_ID = TK.L3_TICKET_TYPE_ID                                         \n");
        sql.append("    LEFT JOIN TICKET_EXTENT TE  ON TE.TICKET_ID = TK.TICKET_ID                                                        \n");
        sql.append("    LEFT JOIN TICKET_ASSIGN TA ON TA.TICKET_ID = TK.TICKET_ID                                                         \n");
        sql.append("    LEFT JOIN TICKET_SITE TS ON TS.SITE_ID = TA.FROM_SITE_ID                                                          \n");
        sql.append("    WHERE 1 = 1                                                                                                       \n");

        if (itemParamsEntity.getStartExtentDate() != null) {
            sql.append("and TE.EXTENT_DATE >= :startExtentDate \n");
            hmapParams.put("startExtentDate", new java.sql.Date(itemParamsEntity.getStartExtentDate().getTime()));
        }
        if (itemParamsEntity.getEndExtentDate() != null) {
            sql.append("and TE.EXTENT_DATE < :endExtentDate \n");
            hmapParams.put("endExtentDate", new java.sql.Date(itemParamsEntity.getEndExtentDate().getTime() + Constants.ONE_DAY_MILLI_SECONDS));
        }

        List<Object> list;
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketSearchList())) {
            list = Arrays.stream(itemParamsEntity.getTicketSearchList().split(";")).filter(FnCommon::isNumeric).map(obj -> "%" + obj + "%").collect(Collectors.toList());
            if (list.size() > 0) {
                FnCommon.appendSQL(" TK.TICKET_ID like :ticketSearchList", hmapParams, list, sql);
            } else {
                sql.append(" and TK.TICKET_ID like '%'|| :ticketSearchList || '%' \n");
                hmapParams.put("ticketSearchList", itemParamsEntity.getTicketSearchList());
            }
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getStatus())) {
            sql.append(" and TE.STATUS like '%'|| :status || '%' \n");
            hmapParams.put("status", itemParamsEntity.getStatus());
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getApproveUserName())) {
            sql.append(" and TE.APPROVE_USER_NAME like :approveUserName \n");
            hmapParams.put("approveUserName", itemParamsEntity.getApproveUserName());
        }

        if (!FnCommon.isNullObject(itemParamsEntity.getManagerUserName())) {
            sql.append(" and TE.MANAGER_USER_NAME like :managerUserName \n");
            hmapParams.put("managerUserName", itemParamsEntity.getManagerUserName());
        }

        sql.append(" ORDER BY TE.CREATE_DATE DESC \n");
        Integer start = null;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        ResultSelectEntity resultData = getListDataAndCount(sql, hmapParams, start, pageSize, TicketExtentDTO.class);
        return resultData;
    }
}