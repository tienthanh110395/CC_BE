package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.ActionAuditDetailDTO;
import com.viettel.etc.repositories.ActionAuditDetailRepository;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ActionAuditDetailRepositoryImpl extends CommonDataBaseRepository implements ActionAuditDetailRepository {
    @Override
    public Object getActionAuditDetails(ActionAuditDetailDTO actionAuditDetailDTO) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    aad.ACTION_AUDIT_DETAIL_ID       AS actionAuditDetailId,\n");
        sql.append("    aad.ACTION_AUDIT_ID              AS actionAuditId,      \n");
        sql.append("    aad.CREATE_DATE                  AS createDate,         \n");
        sql.append("    aad.TABLE_NAME                   AS tableName,          \n");
        sql.append("    aad.PK_ID                        AS pkId,               \n");
        sql.append("    aad.COLUMN_NAME                  AS columnName,         \n");
        sql.append("    aad.OLD_VALUE                    AS oldValue,           \n");
        sql.append("    aad.NEW_VALUE                    AS newValue,           \n");
        sql.append("    aad.ACTION_NAME                  AS actionName,          \n");
        sql.append(" adm.DESCRIPTION decription ");
        sql.append(" FROM ACTION_AUDIT_DETAIL aad                                \n");
        sql.append(" left join ACTION_AUDIT_DETAIL_MAPPING adm on aad.TABLE_NAME = adm.TABLE_NAME and aad.COLUMN_NAME = adm.COLUMN_NAME");
        sql.append(" WHERE                                                       \n");
        sql.append("    1 = 1                                                   \n");
        sql.append("AND ((aad.OLD_VALUE IS NOT NULL) OR (aad.NEW_VALUE IS NOT NULL)) \n");

        if (actionAuditDetailDTO.getActionAuditId() != null) {
            sql.append("    AND aad.ACTION_AUDIT_ID = :actionAuditId\n");
            hmapParams.put("actionAuditId", actionAuditDetailDTO.getActionAuditId());
        }
        sql.append("    ORDER BY aad.ACTION_AUDIT_DETAIL_ID ASC \n");
        Integer start = null;
        if (actionAuditDetailDTO.getStartrecord() != null) {
            start = actionAuditDetailDTO.getStartrecord();
        }
        Integer pageSize = null;
        if (actionAuditDetailDTO.getPagesize() != null) {
            pageSize = actionAuditDetailDTO.getPagesize();
        }
        return getListDataAndCount(sql, hmapParams, start, pageSize, ActionAuditDetailDTO.class);
    }
}
