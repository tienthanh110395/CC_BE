package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.repositories.TicketSiteUserRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Autogen class Repository Impl: Lop thao tac ticket site user
 *
 * @author ToolGen
 * @date Mon Apr 05 09:30:08 ICT 2021
 */
@Repository
public class TicketSiteUserRepositoryImpl extends CommonDataBaseRepository implements TicketSiteUserRepository {

    /**
     *
     * @param itemParamsEntity
     * @param authentication
     * @return
     */
    @Override
    public ResultSelectEntity getTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT \n");
        sql.append("    tsu.TICKET_SITE_USER_ID   as ticketSiteUserId,  \n");
        sql.append("    tsu.SITE_ID               as siteId,            \n");
        sql.append("    ts.SITE_NAME              as siteName,          \n");
        sql.append("    tsu.EMAIL                 as email,             \n");
        sql.append("    tsu.PHONE                 as phone,             \n");
        sql.append("    tsu.USER_ID               as userId,            \n");
        sql.append("    tsu.USER_NAME             as userName,          \n");
        sql.append("    tsu.STAFF_CODE            as staffCode,         \n");
        sql.append("    tsu.STAFF_NAME            as staffName,         \n");
        sql.append("    tsu.STATUS                as status,            \n");
        sql.append("    tsu.CREATE_USER           as createUser,        \n");
        sql.append("    tsu.CREATE_DATE           as createDate,        \n");
        sql.append("    tsu.UPDATE_USER           as updateUser,        \n");
        sql.append("    tsu.UPDATE_DATE           as updateDate        \n");
        sql.append("FROM TICKET_SITE_USER tsu \n");
        sql.append("LEFT JOIN TICKET_SITE ts ON tsu.SITE_ID = ts.SITE_ID\n");
        sql.append("WHERE tsu.STATUS = 1 \n");
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getKeySearch())) {
            sql.append(" AND (UPPER(TSU.USER_NAME) like '%' || :userName ||'%' OR UPPER(TSU.EMAIL) like '%' || :userName ||'%' OR UPPER(TSU.PHONE) like '%' || :userName ||'%' )\n");
            hmapParams.put("userName", itemParamsEntity.getKeySearch().trim().toUpperCase());
        }
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketSiteUserDTO.class);
    }
}
