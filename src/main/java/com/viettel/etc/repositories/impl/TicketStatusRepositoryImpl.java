package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.repositories.TicketStatusRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Autogen class Repository Impl: Lop thao tac lay lich su trang thai
 *
 * @author ToolGen
 * @date Thu Mar 25 09:03:53 ICT 2021
 */
@Repository
public class TicketStatusRepositoryImpl extends CommonDataBaseRepository implements TicketStatusRepository {

    /**
     * Lay thong tin lich su trang thai
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getTicketStatus(TicketStatusDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                                          \n");
        sql.append("    TST.TICKET_ID                                   as ticketId,             -- MÃ PHẢN ÁNH                     \n");
        sql.append("    TST.CREATE_USER                                 as createUser,           --NGƯỜI THAY ĐỔI                   \n");
        sql.append("    TST.SITE_ID                                     as siteId,                                                  \n");
        sql.append("    TST.CREATE_DATE                                 as createDate,           --THỜI GIAN TẠO                    \n");
        sql.append("    TST.TICKET_STATUS                               as ticketStatus,         --TRẠNG THÁI                       \n");
        sql.append("    TST.PROCESS_CONTENT                             as processContent,       --NỘI DUNG (LÝ DO, KẾT QUẢ...)     \n");
        sql.append("    TS.SITE_NAME                                    as siteName              --ĐƠN VỊ                           \n");
        sql.append("FROM                                                                                                            \n");
        sql.append("    TICKET_STATUS TST                                                                                           \n");
        sql.append("    LEFT JOIN TICKET_SITE TS ON TS.SITE_ID = TST.SITE_ID AND TS.STATUS = 1                                      \n");
        sql.append(" WHERE 1 = 1                                                                                                    \n");
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketId())) {
            sql.append(" AND TST.TICKET_ID = :ticketId\n");
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
        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketStatusDTO.class);
    }
}
