package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.ActionAuditRepository;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ActionAuditRepositoryImpl extends CommonDataBaseRepository implements ActionAuditRepository {

    @Override
    public Object getActionAudit(ActionAuditDTO actionAuditDTO) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  ");
        sql.append("    ad.ACTION_AUDIT_ID         AS actionAuditId,          ");
        sql.append("    ad.ACT_TYPE_ID             AS actTypeId,              ");
        sql.append("    ad.CONTRACT_ID             AS contractId,             ");
        sql.append("    ad.TICKET_ID               AS ticketId,               ");
        sql.append("    ad.TICKET_ASSIGN_ID        AS ticketAssignId,         ");
        sql.append("    ad.CREATE_DATE             AS createDate,             ");
        sql.append("    ad.ACTION_USER_FULL_NAME   AS actionUserFullName,     ");
        sql.append("    ad.ACTION_USER_NAME        AS actionUserName,         ");
        sql.append("    ad.APP_ID                  AS appId,                  ");
        sql.append("    ad.IP_PC                   AS ipPc,                   ");
        sql.append("    ad.DESCRIPTION             AS description,            ");
        sql.append("    ad.STATUS                  AS status,                 ");
        sql.append("    ad.TICKET_STATUS           AS ticketStatus,           ");
        sql.append("    at.NAME                    AS actTypeName,            ");
        sql.append("    tsu.STAFF_NAME             AS staffName,              ");
        sql.append("    ts.SITE_NAME               AS siteName                ");
        sql.append("FROM ACTION_AUDIT ad                                      ");
        sql.append("LEFT JOIN ACT_TYPE at on ad.ACT_TYPE_ID = at.ACT_TYPE_ID  ");
        sql.append("LEFT JOIN TICKET_SITE_USER tsu on ad.ACTION_USER_NAME = tsu.USER_NAME");
        sql.append("LEFT JOIN TICKET_SITE ts on tsu.SITE_ID = ts.SITE_ID");
        sql.append("WHERE                                                     ");
        sql.append("    1 = 1                                                 ");

        if (actionAuditDTO.getContractId() != null) {
            sql.append("    AND ad.CONTRACT_ID = :contractId\n");
            hmapParams.put("contractId", actionAuditDTO.getContractId());
        }

        if (actionAuditDTO.getTicketId() != null) {
            sql.append("    AND ad.TICKET_ID = :ticketId\n");
            hmapParams.put("ticketId", actionAuditDTO.getTicketId());
        }

        if (actionAuditDTO.getTicketAssignId() != null) {
            sql.append("    AND ad.TICKET_ASSIGN_ID = :ticketAssignId\n");
            hmapParams.put("ticketAssignId", actionAuditDTO.getTicketAssignId());
        }

        if (actionAuditDTO.getTicketStatus() != null) {
            sql.append("    AND ad.TICKET_STATUS = :ticketStatus\n");
            hmapParams.put("ticketStatus", actionAuditDTO.getTicketStatus());
        }
        sql.append("    ORDER BY ACTION_AUDIT_ID DESC \n");
        Integer start = null;
        if (actionAuditDTO.getStartrecord() != null) {
            start = actionAuditDTO.getStartrecord();
        }
        Integer pageSize = null;
        if (actionAuditDTO.getPagesize() != null) {
            pageSize = actionAuditDTO.getPagesize();
        }
        return getListDataAndCount(sql, hmapParams, start, pageSize, ActionAuditDTO.class);
    }

    @Override
    public Object searchImpactLog(Authentication authentication, ActionAuditSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  distinct ");
        sql.append(" ad.ACTION_AUDIT_ID as actionAuditId,at.NAME AS actTypeName,ad.CREATE_DATE AS createDate,");
        sql.append(" ad.ACTION_USER_FULL_NAME AS actionUserFullName, ad.ACTION_USER_NAME AS actionUserName, ");
        sql.append(" aad.ACTION_NAME actionName, ad.ACT_TYPE_ID as actTypeId, ad.DESCRIPTION as description");
        sql.append(" FROM ACTION_AUDIT ad");
        sql.append(" INNER JOIN ACT_TYPE at on ad.ACT_TYPE_ID = at.ACT_TYPE_ID");
        sql.append(" inner join ACTION_AUDIT_DETAIL aad on ad.ACTION_AUDIT_ID = aad.ACTION_AUDIT_ID");
        sql.append(" WHERE at.ACT_OBJECT = 9");
        if (params.getFromDate() != null) {
            sql.append(" and trunc(ad.CREATE_DATE) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFromDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(ad.CREATE_DATE) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        if (params.getActType() != null) {
            sql.append(" and ad.ACT_TYPE_ID in (:actType)");
            hmapParams.put("actType", params.getActType());
        }

        if (params.getImpactType() != null) {
            sql.append(" and aad.action_name LIKE (:impactType)");
            if (params.getImpactType().equals("INSERT")) {
                hmapParams.put("impactType", ActionAuditDetailEntity.ActionName.INSERT.value);
            } else {
                hmapParams.put("impactType", ActionAuditDetailEntity.ActionName.UPDATE.value);
            }
        }
        if (!FnCommon.isNullOrEmpty(params.getDescription())) {
            sql.append(" and lower(ad.DESCRIPTION) like (:description)");
            hmapParams.put("description", sqlStringSearch(params.getDescription(), true));
            if (params.getDescription().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }
        sql.append(" ORDER BY ad.ACTION_AUDIT_ID  DESC");
        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), ActionAuditNewDTO.class);
    }

    @Override
    public Object getDataDetailImpact(ActionAuditDetailDTO params) {
        return null;
    }

    @Override
    public ResultSelectEntity exportImpactLog(ActionAuditSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  distinct ");
        sql.append(" ad.ACTION_AUDIT_ID as actionAuditId,at.NAME AS actTypeName,ad.CREATE_DATE AS createDate,");
        sql.append(" ad.ACTION_USER_FULL_NAME AS actionUserFullName, ad.ACTION_USER_NAME AS actionUserName, ");
        sql.append(" aad.ACTION_NAME actionName, ad.ACT_TYPE_ID as actTypeId");
        sql.append(" FROM ACTION_AUDIT ad");
        sql.append(" INNER JOIN ACT_TYPE at on ad.ACT_TYPE_ID = at.ACT_TYPE_ID");
        sql.append(" inner join ACTION_AUDIT_DETAIL aad on ad.ACTION_AUDIT_ID = aad.ACTION_AUDIT_ID");
        sql.append(" WHERE at.ACT_OBJECT = 9");
        if (params.getFromDate() != null) {
            sql.append(" and trunc(ad.CREATE_DATE) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFromDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(ad.CREATE_DATE) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        if (params.getActType() != null) {
            sql.append(" and ad.ACT_TYPE_ID in (:actType)");
            hmapParams.put("actType", params.getActType());
        }

        if (params.getImpactType() != null) {
            sql.append(" and aad.action_name LIKE (:impactType)");
            if (params.getImpactType().equals("INSERT")) {
                hmapParams.put("impactType", ActionAuditDetailEntity.ActionName.INSERT.value);
            } else {
                hmapParams.put("impactType", ActionAuditDetailEntity.ActionName.UPDATE.value);
            }
        }
        sql.append(" ORDER BY ad.ACTION_AUDIT_ID  DESC");
        return getListDataAndCount(sql, hmapParams, null, null, ActionAuditDTO.class);
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }
}
