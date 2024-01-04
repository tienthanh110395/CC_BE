package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketAssignLogDTO;
import com.viettel.etc.repositories.TicketAssignLogRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Autogen class Repository Impl: Lop thao tac tim assign log
 *
 * @author ToolGen
 * @date Thu Mar 25 13:34:13 ICT 2021
 */
@Repository
public class TicketAssignLogRepositoryImpl extends CommonDataBaseRepository implements TicketAssignLogRepository {

    /**
     * Lay thong tin ghi nhan qua trinh ho tro
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getTicketAssignLog(TicketAssignLogDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                               \n");
        sql.append("    TS.SITE_NAME                         AS siteName,                                                \n");
        sql.append("    TAL.TICKET_ASSIGN_LOG_ID             AS ticketAssignLogId,                                       \n");
        sql.append("    TAL.TICKET_ASSIGN_ID                 AS ticketAssignId,                                          \n");
        sql.append("    TAL.TICKET_ID                        AS ticketId,                                                \n");
        sql.append("    TAL.LOG_CONTENT                      AS logContent,                                              \n");
        sql.append("    TAL.SITE_ID                          AS siteId,                                                  \n");
        sql.append("    TAL.CREATE_USER                      AS createUser,                                              \n");
        sql.append("    TAL.CREATE_DATE                      AS createDate,                                              \n");
        sql.append("    TAL.UPDATE_USER                      AS updateUser,                                              \n");
        sql.append("    TAL.UPDATE_DATE                      AS updateDate,                                              \n");
        sql.append("    TAL.LOG_TYPE                         AS logType                                                  \n");
        sql.append("FROM                                                                                                 \n");
        sql.append("    TICKET_ASSIGN_LOG TAL                                                                            \n");
        sql.append("    LEFT JOIN TICKET_SITE TS ON TS.SITE_ID = TAL.SITE_ID  AND TS.STATUS = 1                          \n");
        sql.append("WHERE                                                                                                \n");
        sql.append("    1 = 1                                                                                            \n");
        if (itemParamsEntity.getTicketId() != null) {
            sql.append("    AND TAL.TICKET_ID = :ticketId     \n");
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
        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketAssignLogDTO.class);
    }
}
