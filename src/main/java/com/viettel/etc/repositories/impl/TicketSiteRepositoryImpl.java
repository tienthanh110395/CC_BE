package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketSiteConfigDTO;
import com.viettel.etc.dto.TicketSiteSearchDTO;
import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.repositories.TicketSiteRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class TicketSiteRepositoryImpl extends CommonDataBaseRepository implements TicketSiteRepository {

    @Override
    public Object searchTicketSite(Authentication authentication, TicketSiteSearchDTO dto) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" ts.SITE_ID ticketSiteId,ts.SITE_CODE ticketSiteCode,ts.SITE_NAME ticketSiteName,ts.PARENT_ID parentId,ts.LEVEL_SITE levelSite,");
        sql.append(" ts.STATUS status,lower(ts.CREATE_USER) createUser,ts.CREATE_DATE createDate,lower(ts.UPDATE_USER) updateUser,ts.UPDATE_DATE updateDate,");
        sql.append(" ts.email mail, ts.phone phoneNumber,");
        sql.append(" ( SELECT LISTAGG(tsu.USER_NAME, ',') WITHIN GROUP (ORDER BY tsu.USER_NAME) from TICKET_SITE_USER tsu WHERE tsu.SITE_ID = ts.SITE_ID  ) siteUser");
        sql.append(" FROM TICKET_SITE ts");
        if (!FnCommon.isNullOrEmpty(dto.getSiteUser())) {
            sql.append(" left join ticket_site_user tus1 on tus1.site_id =ts.site_id");
        }
        sql.append(" WHERE 1=1");

        if (dto.getLstTicketSite() != null && dto.getLstTicketSite().size() > 0) {
            sql.append(" and ts.site_id in (:ticketSiteId)");
            hmapParams.put("ticketSiteId", dto.getLstTicketSite());
        }

        if (dto.getLevelSite() != null) {
            sql.append(" and ts.level_site like (:level_site)");
            hmapParams.put("level_site", "%" + dto.getLevelSite());
        }

        if (!FnCommon.isNullOrEmpty(dto.getTicketSiteName())) {
            sql.append(" and lower(ts.SITE_NAME) like (:ticketSiteName)");
            hmapParams.put("ticketSiteName", "%" + dto.getTicketSiteName().toLowerCase() + "%");
        }

        if (!FnCommon.isNullOrEmpty(dto.getSiteUser())) {
            sql.append(" AND lower(tus1.user_name) LIKE ( :siteUser ) ");
//            hmapParams.put("siteUser", sqlStringSearch(dto.getSiteUser(), true));
            hmapParams.put("siteUser", "%" + dto.getSiteUser().toLowerCase().trim() + "%");
        }

        if (!FnCommon.isNullOrEmpty(dto.getCreateUser())) {
            sql.append(" and lower(ts.create_user) like (:createUser)");
            hmapParams.put("createUser", "%" + dto.getCreateUser().toLowerCase() + "%");
        }

        if (dto.getStatus() != null && dto.getStatus().size() > 0) {
            sql.append(" and ts.status in (:status)");
            hmapParams.put("status", dto.getStatus());
        }

        if (dto.getFormDate() != null) {
            sql.append(" and trunc(ts.create_date) >= trunc(:fromDate)");
            hmapParams.put("fromDate", dto.getFormDate());
        }

        if (dto.getToDate() != null) {
            sql.append(" and trunc(ts.create_date) <= trunc(:toDate)");
            hmapParams.put("toDate", dto.getToDate());
        }

        sql.append(" ORDER BY ts.SITE_ID DESC ");
        return getListDataAndCount(sql, hmapParams, dto.getStartRecord() * dto.getPageSize(), dto.getPageSize(), TicketSiteConfigDTO.class);
    }

    @Override
    public Object getTicketSite(Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" TS.SITE_ID AS ticketSiteId, TS.SITE_NAME AS ticketSiteName");
        sql.append(" from ticket_site ts ");
        sql.append(" ORDER BY TS.SITE_NAME DESC ");

        return getListDataAndCount(sql, hmapParams, null, null, TicketSiteConfigDTO.class);
    }

    @Override
    public Object getDataDetail(Long ticketSiteId, TicketSiteConfigDTO dto) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" ts.SITE_ID ticketSiteId,ts.SITE_CODE ticketSiteCode,ts.SITE_NAME ticketSiteName,ts.PARENT_ID parentId,ts.LEVEL_SITE levelSite,ts.STATUS status,");
        sql.append(" ts.CREATE_USER createUser,ts.CREATE_DATE createDate,ts.UPDATE_USER updateUser,ts.UPDATE_DATE updateDate,ts.email ticketSiteEmail,ts.phone ticketSitePhoneNumber,");
        sql.append(" (SELECT ts2.SITE_ID FROM TICKET_SITE ts2 WHERE ts2.SITE_ID =");
        sql.append(" (SELECT ts3.PARENT_ID FROM TICKET_SITE ts3 WHERE ts3.SITE_ID = ts.PARENT_ID)) AS ticketSiteLv1Id");

        sql.append(" FROM TICKET_SITE ts");
        sql.append(" WHERE 1=1");

        if (ticketSiteId != null) {
            sql.append(" AND ts.SITE_ID = :ticketSiteId\n");
            hmapParams.put("ticketSiteId", ticketSiteId);
        }

        return getFirstData(sql, hmapParams, TicketSiteConfigDTO.class);
    }

    @Override
    public ResultSelectEntity getLstUserHandle(Long ticketSiteId, TicketSiteConfigDTO dto) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" tsu.TICKET_SITE_USER_ID ticketSiteUserId, tsu.SITE_ID siteId, tsu.USER_NAME userName, tsu.STAFF_NAME name,tsu.PHONE phone,tsu.EMAIL mail, ts.USERNAME userAdmin ");
        sql.append(" FROM TICKET_SITE_USER tsu ");
        sql.append(" INNER JOIN TICKET_SITE ts on ts.SITE_ID = tsu.SITE_ID ");
        sql.append(" WHERE 1=1 ");
        if (ticketSiteId != null) {
            sql.append(" AND tsu.SITE_ID = :ticketSiteId\n");
            hmapParams.put("ticketSiteId", ticketSiteId);
        }
        return getListDataAndCount(sql, hmapParams, null, null, TicketSiteConfigDTO.UserHandle.class);
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }
}

