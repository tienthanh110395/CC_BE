package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketStatusDTO;
import com.viettel.etc.repositories.TicketAssignProcessRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Autogen class Repository Impl: Lop thao tac  danh sach cac yeu cau phoi hop xu ly
 *
 * @author ToolGen
 * @date Tue Mar 02 11:15:25 ICT 2021
 */
@Repository
public class TicketAssignProcessRepositoryImpl extends CommonDataBaseRepository implements TicketAssignProcessRepository {
    /***
     * Lay thong tin thay doi trang thai trong qua trinh ho tro
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketAssignProcess(TicketStatusDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                                                                          \n");
        sql.append("    TAP.TICKET_ID                                   as ticketId,             -- MÃ PHẢN ÁNH                     \n");
        sql.append("    TAP.CREATE_USER                                 as createUser,           --NGƯỜI THAY ĐỔI                   \n");
        sql.append("    TAP.SITE_ID                                     as siteId,                                                  \n");
        sql.append("    TAP.CREATE_DATE                                 as createDate,           --THỜI GIAN TẠO                    \n");
        sql.append("    TAP.STATUS                                      as ticketStatus,         --TRẠNG THÁI                       \n");
        sql.append("    TAP.PROCESS_RESULT                              as processContent,       --NỘI DUNG (LÝ DO, KẾT QUẢ...)     \n");
        sql.append("    TS.SITE_NAME                                    as siteName              --ĐƠN VỊ                           \n");
        sql.append("FROM                                                                                                            \n");
        sql.append("    TICKET_ASSIGN_PROCESS TAP                                                                                   \n");
        sql.append("    LEFT JOIN TICKET_SITE TS ON TS.SITE_ID = TAP.SITE_ID AND TS.STATUS = 1                                      \n");
        sql.append(" WHERE 1 = 1                                                                                                    \n");
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketId())) {
            sql.append(" AND TAP.TICKET_ID = :ticketId\n");
            hmapParams.put("ticketId", itemParamsEntity.getTicketId());
        }
        sql.append(" ORDER BY TAP.CREATE_DATE \n");
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
