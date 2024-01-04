package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketProcessDTO;
import com.viettel.etc.repositories.TicketProcessRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Autogen class Repository Impl: Lop thao tac tien xu ly cua CSKH
 *
 * @author ToolGen
 * @date Tue Mar 02 16:00:43 ICT 2021
 */
@Repository
public class TicketProcessRepositoryImpl extends CommonDataBaseRepository implements TicketProcessRepository {
    /***
     * Lay thong tin xu ly phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketProcessInfo(TicketProcessDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                                       \n");
        sql.append("    TK.PHONE_NUMBER                                      as phoneNumber,                                     \n");
        sql.append("    TK.PRIORITY_ID                                       as priorityId,                                      \n");
        sql.append("    TK.TICKET_ID                                         as ticketId,                                        \n");
        sql.append("    TK.SOURCE_ID                                         as sourceId,                                        \n");
        sql.append("    TP.DESTROY_REASON                                    as destroyReason,                                   \n");
        sql.append("    TP.PROCESS_RESULT                                    as processResult,                                   \n");
        sql.append("    TP.PROCESS_TIME                                      as processTime,                                     \n");
        sql.append("    TP.STATUS                                            as status,                                          \n");
        sql.append("    TP.REASON_LEVEL1                                     as reasonLevel1,                                    \n");
        sql.append("    TP.REASON_LEVEL2                                     as reasonLevel2,                                    \n");
        sql.append("    TP.PROCESS_CONTENT                                   as processContent,                                  \n");
        sql.append("    TP.STAFF_CODE                                        as staffCode,                                       \n");
        sql.append("    TP.STAFF_NAME                                        as staffName,                                       \n");
        sql.append("    TP.CREATE_USER                                       as createUser,                                      \n");
        sql.append("    TP.CREATE_DATE                                       as createDate,                                      \n");
        sql.append("    TP.UPDATE_USER                                       as updateUser,                                      \n");
        sql.append("    TP.UPDATE_DATE                                       as updateDate,                                      \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L1                          as ticketErrorCauseIdL1,                            \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L2                          as ticketErrorCauseIdL2,                            \n");
        sql.append("    TP.TICKET_ERROR_CAUSE_ID_L3                          as ticketErrorCauseIdL3,                            \n");
        sql.append("    TP.REASON_LEVEL3                                     as reasonLevel3,                                    \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_ID_L1                         as ticketExpireCauseIdL1,                           \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_ID_L2                         as ticketExpireCauseIdL2,                           \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_ID_L3                         as ticketExpireCauseIdL3,                           \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_NAME_L1                       as ticketExpireCauseNameL1,                         \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_NAME_L2                       as ticketExpireCauseNameL2,                         \n");
        sql.append("    TP.TICKET_EXPIRE_CAUSE_NAME_L3                       as ticketExpireCauseNameL3,                         \n");
        sql.append("    TP.TICKET_EXPIRE_SITE_ID                             as ticketExpireSiteId,                              \n");
        sql.append("    TP.EMAIL                                             as email,                                           \n");
        sql.append("    TP.TICKET_SITE_ID_L1                                 as ticketSiteIdL1,                                  \n");
        sql.append("    TP.TICKET_SITE_ID_L2                                 as ticketSiteIdL2,                                  \n");
        sql.append("    TP.TICKET_SITE_ID_L3                                 as ticketSiteIdL3,                                  \n");
        sql.append("    TP.TICKET_PROCESS_ID                                 as ticketProcessId,                                 \n");
        sql.append("    TKT1.NAME                                            as groupPA,               --Nhóm phản ánh           \n");
        sql.append("    TK.L1_TICKET_TYPE_ID                                 as l1TicketTypeId,                                  \n");
        sql.append("    TKT2.NAME                                            as subgroupPA,            --Thể lại                 \n");
        sql.append("    TK.L2_TICKET_TYPE_ID                                 as l2TicketTypeId,                                  \n");
        sql.append("    TKT3.NAME                                            as detailPA,              --Loại                    \n");
        sql.append("    TK.L3_TICKET_TYPE_ID                                 as l3TicketTypeId,                                  \n");
        sql.append("    TK.SLA_DATE                                          as slaDate                                          \n");
        sql.append("FROM                                                                                                         \n");
        sql.append("    TICKET TK                                                                                                \n");
        sql.append("    LEFT JOIN TICKET_PROCESS TP ON TK.TICKET_ID = TP.TICKET_ID                                               \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT1  ON TKT1.TICKET_TYPE_ID = TK.L1_TICKET_TYPE_ID                                \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT2  ON TKT2.TICKET_TYPE_ID = TK.L2_TICKET_TYPE_ID                                \n");
        sql.append("    LEFT JOIN TICKET_TYPE TKT3  ON TKT3.TICKET_TYPE_ID = TK.L3_TICKET_TYPE_ID                                \n");
        sql.append(" WHERE 1 = 1                                                                                                 \n");
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketId())) {
            sql.append(" and TK.TICKET_ID = :ticketId \n");
            hmapParams.put("ticketId", itemParamsEntity.getTicketId());
        }
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }

        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketProcessDTO.class);
    }
}
