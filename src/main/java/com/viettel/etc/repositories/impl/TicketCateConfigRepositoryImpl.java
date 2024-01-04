package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketConfigSearchDTO;
import com.viettel.etc.dto.TicketTypeLogDTO;
import com.viettel.etc.dto.TicketTypeLogDetailDTO;
import com.viettel.etc.repositories.TicketCateConfigRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class TicketCateConfigRepositoryImpl extends CommonDataBaseRepository implements TicketCateConfigRepository {

    @Override
    public Object searchTicketType(Authentication authentication, TicketConfigSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" tt.TICKET_TYPE_ID ticketTypeId,tt.NAME ticketTypeName,tt.CODE ticketTypeCode,tt.DESCRIPTION AS description,");
        sql.append(" tt.PARENT_ID parentId,tt.STATUS status, lower(tt.CREATE_USER) createUser,tt.CREATE_DATE createDate, lower(tt.UPDATE_USER) updateUser,");
        sql.append(" tt.UPDATE_DATE updateDate, tt.TICKET_TEMPLATE ticketTemplate");
        if (params != null && params.getTicketTypeLevel() != null && params.getTicketTypeLevel().equals(3L)) {
            sql.append(" ,tp.name ticketgenre, tpp.name ticketgroup");
        }
        if (params != null && params.getTicketTypeLevel() != null && params.getTicketTypeLevel().equals(2L)) {
            sql.append(" ,tp.name ticketgroup");
        }
        sql.append(" FROM TICKET_TYPE tt");

        if (params != null && params.getTicketTypeLevel() != null && params.getTicketTypeLevel().equals(3L)) {
            sql.append(" inner join ticket_type tp on tt.parent_id = tp.ticket_type_id");
            sql.append(" inner join ticket_type tpp on tp.parent_id = tpp.ticket_type_id");
        }

        if (params != null && params.getTicketTypeLevel() != null && params.getTicketTypeLevel().equals(2L)) {
            sql.append(" inner join ticket_type tp on tt.parent_id = tp.ticket_type_id");
        }

        sql.append(" WHERE tt.LEVEL_TT =:ticketTypeLevel");
        hmapParams.put("ticketTypeLevel", params.getTicketTypeLevel());

        if (params.getLstTicketTypeGroupId() != null && params.getLstTicketTypeGroupId().size() > 0 && params.getTicketTypeLevel().equals(3L)) {
            sql.append(" and tpp.ticket_type_id in (:ticketTypeGroupId)");
            hmapParams.put("ticketTypeGroupId", params.getLstTicketTypeGroupId());
        }

        if (params.getLstTicketTypeGroupId() != null && params.getLstTicketTypeGroupId().size() > 0 && params.getTicketTypeLevel().equals(2L)) {
            sql.append(" and tp.ticket_type_id in (:ticketTypeId)");
            hmapParams.put("ticketTypeId", params.getLstTicketTypeGroupId());
        }

        if (params.getLstTicketTypeGenreId() != null && params.getLstTicketTypeGenreId().size() > 0 && params.getTicketTypeLevel().equals(2L)) {
            sql.append(" and tt.ticket_type_id in (:ticketGenreId)");
            hmapParams.put("ticketGenreId", params.getLstTicketTypeGenreId());
        }

        if (params.getLstTicketTypeGroupId() != null && params.getLstTicketTypeGroupId().size() > 0 && params.getTicketTypeLevel().equals(1L)) {
            sql.append(" and tt.ticket_type_id in (:ticketTypeId)");
            hmapParams.put("ticketTypeId", params.getLstTicketTypeGroupId());
        }

        if (params.getLstTicketCategoryId() != null && params.getLstTicketCategoryId().size() > 0 && params.getTicketTypeLevel().equals(3L)) {
            sql.append(" and tt.parent_id in (:ticketCategoryId)");
            hmapParams.put("ticketCategoryId", params.getLstTicketCategoryId());
        }

        if (params.getLstTicketType() != null && params.getLstTicketType().size() > 0 && params.getTicketTypeLevel().equals(3L)) {
            sql.append(" and tt.ticket_type_id in (:ticketType)");
            hmapParams.put("ticketType", params.getLstTicketType());
        }

        if (!FnCommon.isNullOrEmpty(params.getTicketTypeCode())) {
            sql.append(" and lower(tt.code) like (:ticketCode)");
            hmapParams.put("ticketCode", sqlStringSearch(params.getTicketTypeCode(), true));
            if (params.getTicketTypeCode().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }

        if (!FnCommon.isNullOrEmpty(params.getTicketTypeName())) {
            sql.append(" and lower(tt.name) like (:ticketName)");
            hmapParams.put("ticketName", sqlStringSearch(params.getTicketTypeName(), true));
            if (params.getTicketTypeName().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(tt.create_user) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }

        if (params.getStatus() != null && params.getStatus().size() > 0) {
            sql.append(" and tt.status in (:status)");
            hmapParams.put("status", params.getStatus());
        }

        if (params.getFormDate() != null) {
            sql.append(" and trunc(tt.create_date) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFormDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(tt.create_date) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        sql.append(" ORDER BY tt.TICKET_TYPE_ID DESC");
        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), TicketCateConfigDTO.class);
    }

    @Override
    public Object getDataDetail(Long ticketTypeId, TicketCateConfigDTO params) {

        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" tt.TICKET_TYPE_ID AS ticketTypeId,");
        sql.append(" tt.NAME AS ticketTypeName,");
        sql.append(" tt.CODE AS ticketTypeCode,");
        sql.append(" tt.DESCRIPTION AS description,");
        sql.append(" tt.STATUS AS status,");
        sql.append(" tt.TICKET_TEMPLATE AS ticketTemplate,");
        sql.append(" tt.PARENT_ID AS parentId,");
        sql.append(" (SELECT tt2.TICKET_TYPE_ID FROM TICKET_TYPE tt2 WHERE tt2.TICKET_TYPE_ID =");
        sql.append(" (SELECT tt3.PARENT_ID FROM TICKET_TYPE tt3 WHERE tt3.TICKET_TYPE_ID = tt.PARENT_ID)) AS ticketTypeGroupId");
        sql.append(" FROM TICKET_TYPE tt  WHERE 1=1");

        if (ticketTypeId != null) {
            sql.append(" AND tt.TICKET_TYPE_ID = :ticketTypeId\n");
            hmapParams.put("ticketTypeId", ticketTypeId);
        }

        return getFirstData(sql, hmapParams, TicketCateConfigDTO.class);
    }

    @Override
    public Object getDataDetailImpact(TicketTypeLogDetailDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" ttld.TICKET_TYPE_LOG_DETAIL_ID ticketTypeLogDetailId,ttld.TICKET_TYPE_LOG_ID ticketTypeLogId,");
        sql.append(" ttld.COLUMN_NAME columnName,ttld.OLD_VALUE oldValue,ttld.NEW_VALUE newValue");
        sql.append(" FROM TICKET_TYPE_LOG_DETAIL ttld where 1=1");
        sql.append(" AND ttld.NEW_VALUE is not null");

        if (params.getTicketTypeLogId() != null) {
            sql.append(" AND ttld.TICKET_TYPE_LOG_ID = :ticketTypeLogId\n");
            hmapParams.put("ticketTypeLogId", params.getTicketTypeLogId());
        }

        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), TicketTypeLogDetailDTO.class);
    }

    @Override
    public ResultSelectEntity getTicketTypeByParentId(TicketCateConfigDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    TK.TICKET_TYPE_ID       as ticketTypeId,");
        sql.append("    TK.NAME                 as ticketTypeName,");
        sql.append("    TK.CODE                 as ticketTypeCode,");
        sql.append("    TK.DESCRIPTION          as description,");
        sql.append("    TK.PARENT_ID            as parentId,");
        sql.append("    TK.STATUS               as status,");
        sql.append("    TK.CREATE_USER          as createUser,");
        sql.append("    TK.CREATE_DATE          as createDate,");
        sql.append("    TK.UPDATE_USER          as updateUser,");
        sql.append("    TK.UPDATE_DATE          as updateDate,");
        sql.append("    TK.TICKET_TEMPLATE      as ticketTemplate,");
        sql.append("    TK.DEAD_TIME_TYPE       as deadTimeType,");
        sql.append("    TK.HOT_SLA              as hotSla,");
        sql.append("    TK.OTHERS_SLA           as othersSla");
        sql.append(" FROM TICKET_TYPE TK WHERE  1=1");
        if (params.getLstParentId() != null && params.getLstParentId().size() > 0 && params.getSearch() != null && params.getSearch().equals(0L)) {
            sql.append(" AND TK.parent_id in (:parentId)");
            hmapParams.put("parentId", params.getLstParentId());
        }

        if (params.getLstParentId() != null && params.getLstParentId().size() > 0 && params.getSearch() != null && params.getSearch().equals(1L)) {
            sql.append(" AND TK.parent_id in (:parentId)");
            sql.append(" AND TK.status =1");
            hmapParams.put("parentId", params.getLstParentId());
        }

        if (params.getLstParentId() == null && params.getSearch().equals(0L)) {
            sql.append(" AND TK.parent_id is null");
        }

        if (params.getLstParentId() == null && params.getSearch().equals(1L)) {
            sql.append(" AND TK.status =1");
            sql.append(" AND TK.parent_id is null");
        }

        sql.append(" ORDER BY nlssort(TK.NAME, 'nls_sort = Vietnamese') \n");

        return getListDataAndCount(sql, hmapParams, null, null, TicketCateConfigDTO.class);
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }

    @Override
    public ResultSelectEntity exportImpactLog(TicketTypeLogDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" ttl.TICKET_TYPE_LOG_ID ticketTypeLogId, ttl.ACT_TYPE actType,");
        sql.append(" LOWER(ttl.CREATE_USER) createUser, ttl.LEVEL_TT levelTt, ttl.CREATE_DATE createDate");
        sql.append(" FROM TICKET_TYPE_LOG ttl WHERE 1=1");

        sql.append(" ORDER BY ttl.TICKET_TYPE_LOG_ID DESC");
        return getListDataAndCount(sql, hmapParams, null, null, TicketTypeLogDTO.class);
    }

    /**
     * Lấy danh sách thể loại phản ánh ở màn Tạo mới Map nguyên nhân lỗi
     *
     * @param parentId
     * @return
     */
    @Override
    public List<TicketCateConfigDTO> getTicketTypeByParentIdForMapping(Long parentId) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("select tt.ticket_type_id ticketTypeId,tt.name name,tt.parent_id parentId ");
        sql.append("from ticket_type tt ");
        sql.append("where tt.status = 1 ");
        sql.append("and tt.parent_id =:parentId ");
        sql.append("and tt.ticket_type_id not in ( ");
        sql.append("select map.TICKET_TYPE_ID ");
        sql.append("from mapping_error_cause map) ");
        sql.append("ORDER BY nlssort(tt.NAME, 'nls_sort = Vietnamese')");

        hmapParams.put("parentId", parentId);

        return (List<TicketCateConfigDTO>) getListData(sql, hmapParams, null, null, TicketCateConfigDTO.class);
    }

    @Override
    public List<TicketCateConfigDTO> getTicketTypeByParentIdForConfigTime(Long parentId) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT tt.ticket_type_id ticketTypeId,tt.name ticketTypeName,tt.parent_id parentId ");
        sql.append("FROM ticket_type tt ");
        sql.append("WHERE tt.status = 1 ");
        sql.append("AND tt.parent_id =:parentId ");
        sql.append("AND tt.ticket_type_id not in ( ");
        sql.append("SELECT sla.TICKET_TYPE_ID ");
        sql.append("FROM TICKET_SLA sla) ");
        sql.append("ORDER BY nlssort(tt.NAME, 'nls_sort = Vietnamese')");

        hmapParams.put("parentId", parentId);

        return (List<TicketCateConfigDTO>) getListData(sql, hmapParams, null, null, TicketCateConfigDTO.class);
    }

    @Override
    public List<TicketCateConfigDTO> getTicketTypeByTicketTypeIdForConfigTime(Long ticketTypeId) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT tt.ticket_type_id ticketTypeId,tt.name ticketTypeName,tt.parent_id parentId ");
        sql.append("FROM ticket_type tt ");
//        sql.append("WHERE tt.status = 1 ");
        sql.append("WHERE 1 = 1 ");
        sql.append("AND tt.ticket_type_id =:ticketTypeId ");
        sql.append("ORDER BY nlssort(tt.NAME, 'nls_sort = Vietnamese')");

        hmapParams.put("ticketTypeId", ticketTypeId);

        return (List<TicketCateConfigDTO>) getListData(sql, hmapParams, null, null, TicketCateConfigDTO.class);
    }

}
