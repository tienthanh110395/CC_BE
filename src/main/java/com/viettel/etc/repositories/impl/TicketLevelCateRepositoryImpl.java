package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.dto.TicketLevelCateDTO;
import com.viettel.etc.dto.TicketLevelCateSearchDTO;
import com.viettel.etc.repositories.TicketLevelCateRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class TicketLevelCateRepositoryImpl extends CommonDataBaseRepository implements TicketLevelCateRepository {

    @Override
    public Object getDataDetail(Long ticketLevelCateId, TicketLevelCateDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" oc.ID AS ticketLevelCateId,");
        sql.append(" oc.NAME AS ticketLevelCateName,");
        sql.append(" oc.CODE AS ticketLevelCateCode,");
        sql.append(" oc.Type AS type,");
        sql.append(" oc.DESCRIPTION AS description,");
        sql.append(" oc.IS_ACTIVE AS status,");
        sql.append(" oc.CREATE_DATE AS createDate,");
        sql.append(" oc.CREATE_USER AS createUser,");
        sql.append(" oc.TYPE_NAME AS typeName,");
        sql.append(" oc.UPDATE_DATE AS updateDate,");
        sql.append(" oc.UPDATE_USER AS updateUser");
        sql.append(" FROM TICKET_PRIORITIES oc  WHERE 1=1");
        if (ticketLevelCateId != null) {
            sql.append(" AND oc.ID = :ticketLevelCateId\n");
            hmapParams.put("ticketLevelCateId", ticketLevelCateId);
        }
        return getFirstData(sql, hmapParams, TicketLevelCateDTO.class);
    }

    @Override
    public Object searchTicketLevelCate(Authentication authentication, TicketLevelCateSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" oc.ID AS ticketLevelCateId,oc.NAME AS ticketLevelCateName,oc.CODE AS ticketLevelCateCode,oc.Type AS type,oc.DESCRIPTION AS description,");
        sql.append(" oc.IS_ACTIVE AS status,lower(oc.CREATE_USER) AS createUser,oc.CREATE_DATE AS createDate,lower(oc.UPDATE_USER) AS updateUser,");
        sql.append(" oc.TYPE_NAME AS typeName,oc.UPDATE_DATE AS updateDate");

        sql.append(" FROM TICKET_PRIORITIES oc WHERE 1=1");
        sql.append(" AND type=1");
        if (params.getLstLevelCateId() != null && params.getLstLevelCateId().size() > 0) {
            sql.append(" and oc.ID in (:ticketLevelCateId)");
            hmapParams.put("ticketLevelCateId", params.getLstLevelCateId());
        }
        if (!FnCommon.isNullOrEmpty(params.getTicketLevelCateCode())) {
            sql.append(" AND lower(oc.code) like (:ticketLevelCateCode)");
            hmapParams.put("ticketLevelCateCode", sqlStringSearch(params.getTicketLevelCateCode(), true));
            if (params.getTicketLevelCateCode().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }
//        if (!FnCommon.isNullOrEmpty(params.getTicketLevelCateName())) {
//            sql.append(" AND lower(oc.name) like (:ticketLevelCateName)");
//            hmapParams.put("ticketLevelCateName", sqlStringSearch(params.getTicketLevelCateName(),true));
//            if (params.getTicketLevelCateName().contains("_")) {
//                sql.append(" ESCAPE '/'");
//            }
//        }
        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(oc.create_user) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(),true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }
        if (params.getStatus() != null && params.getStatus().size() > 0) {
            sql.append(" and oc.IS_ACTIVE in (:isActive)");
            hmapParams.put("isActive", params.getStatus());
        }

        if (params.getFromDate() != null) {
            sql.append(" and trunc(oc.create_date) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFromDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(oc.create_date) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        sql.append(" ORDER BY oc.ID DESC");
        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), TicketLevelCateDTO.class);
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }

    @Override
    public ResultSelectEntity getTicketProcessTime(Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" oc.ID AS ticketLevelCateId,oc.NAME AS ticketLevelCateName,oc.CODE AS ticketLevelCateCode");
        sql.append(" FROM TICKET_PRIORITIES oc WHERE 1=1 AND oc.IS_ACTIVE = 1 AND oc.TYPE = 1");
        sql.append(" ORDER BY oc.ID DESC");
        return getListDataAndCount(sql, hmapParams, null, null, TicketLevelCateDTO.class);
    }

    @Override
    public ResultSelectEntity getTicketLevelCateNameById(TicketLevelCateDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" oc.ID AS ticketLevelCateId,oc.NAME AS ticketLevelCateName,oc.CODE AS ticketLevelCateCode,oc.Type AS type,oc.DESCRIPTION AS description,");
        sql.append(" oc.IS_ACTIVE AS status,lower(oc.CREATE_USER) AS createUser,oc.CREATE_DATE AS createDate,lower(oc.UPDATE_USER) AS updateUser,");
        sql.append(" oc.TYPE_NAME AS typeName,oc.UPDATE_DATE AS updateDate");

        sql.append(" FROM TICKET_PRIORITIES oc WHERE 1=1");
        sql.append(" AND type=1");
//        if (params.getLstLevelCateId() != null && params.getLstLevelCateId().size() > 0 && params.getSearch() != null && params.getSearch().equals(0L) ) {
//            sql.append(" AND oc.ID in (:lstId)");
//            hmapParams.put("lstId", params.getLstLevelCateId());
//        }
        sql.append(" ORDER BY nlssort(oc.NAME, 'nls_sort = Vietnamese') \n");

        return getListDataAndCount(sql, hmapParams, null, null, TicketLevelCateDTO.class);
    }

}
