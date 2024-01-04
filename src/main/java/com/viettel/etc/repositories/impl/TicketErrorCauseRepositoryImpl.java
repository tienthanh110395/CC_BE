package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketErrorCauseDTO;
import com.viettel.etc.dto.TicketErrorCauseNewDTO;
import com.viettel.etc.dto.TicketErrorCauseSearchDTO;
import com.viettel.etc.repositories.TicketErrorCauseRepository;
import com.viettel.etc.repositories.tables.entities.TicketErrorCauseEntity;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

/**
 * Autogen class Repository Impl: Lop thao danh muc nguyen nhan loi
 *
 * @author ToolGen
 * @date Thu Jun 03 13:45:57 ICT 2021
 */
@Repository
public class TicketErrorCauseRepositoryImpl extends CommonDataBaseRepository implements TicketErrorCauseRepository {

    @Autowired
    EntityManager entityManager;

    /**
     * Tìm kiếm danh muc nguyen nhan loi
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity searchTicketErrorCause(TicketErrorCauseDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("select                                                                  \n");
        sql.append("TEC.TICKET_ERROR_CAUSE_ID                   as ticketErrorCauseId,      \n");
        sql.append("TEC.PARENT_ID                               as parentId,                \n");
        sql.append("TEC.CODE                                    as code,                    \n");
        sql.append("TEC.NAME                                    as name,                    \n");
        sql.append("TEC.STATUS                                  as status,                  \n");
        sql.append("TEC.DESCRIPTION                             as description,             \n");
        sql.append("TEC2.TICKET_ERROR_CAUSE_ID                  as ticketErrorCauseId2,     \n");
        sql.append("TEC2.PARENT_ID                              as parentId2,               \n");
        sql.append("TEC2.CODE                                   as code2,                   \n");
        sql.append("TEC2.NAME                                   as name2,                   \n");
        sql.append("TEC3.TICKET_ERROR_CAUSE_ID                  as ticketErrorCauseId3,     \n");
        sql.append("TEC3.PARENT_ID                              as parentId3,               \n");
        sql.append("TEC3.CODE                                   as code3,                   \n");
        sql.append("TEC3.NAME                                   as name3                    \n");
        sql.append("FROM TICKET_ERROR_CAUSE TEC                                             \n");
        sql.append("LEFT JOIN TICKET_ERROR_CAUSE TEC2 on TEC.TICKET_ERROR_CAUSE_ID = TEC2.PARENT_ID \n");
        sql.append("LEFT JOIN TICKET_ERROR_CAUSE TEC3 on TEC2.TICKET_ERROR_CAUSE_ID = TEC3.PARENT_ID \n");

        //Tìm kiếm theo tên nguyên nhân lỗi
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getName())) {
            sql.append(" and UPPER(TEC3.NAME) = :name3 \n");
            hmapParams.put("name3", itemParamsEntity.getName().toUpperCase());
        }

        //Tìm kiếm theo mã nguyên nhân lỗi
        if (!FnCommon.isNullOrEmpty(itemParamsEntity.getCode())) {
            sql.append(" and TEC3.CODE = :code3 \n");
            hmapParams.put("code3", itemParamsEntity.getCode());
        }

        //Tìm kiếm theo trạng thái sử dụng
        if (!FnCommon.isNullObject(itemParamsEntity.getStatusType())) {
            sql.append(" and TEC.STATUS in (:statusType) \n");
            hmapParams.put("statusType", itemParamsEntity.getStatusType());
        }

        Integer start = null;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketErrorCauseDTO.class);
    }

    /**
     * Tìm kiếm theo tree danh muc nguyen nhan loi
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity searchTreeTicketErrorCause(TicketErrorCauseDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT TEC.TICKET_ERROR_CAUSE_ID as ticketErrorCauseId, TEC.PARENT_ID as parentId, TEC.CODE as code, TEC.NAME as name, " +
                "TEC.STATUS as status, TEC.DESCRIPTION as description FROM TICKET_ERROR_CAUSE TEC WHERE 1 = 1 START WITH TEC.PARENT_ID is null " +
                "CONNECT BY PRIOR TEC.TICKET_ERROR_CAUSE_ID = TEC.PARENT_ID");
        return getListDataAndCount(sql, hmapParams, null, null, TicketErrorCauseDTO.class);
    }

    @Override
    public ResultSelectEntity getTicketErrorCauseByParent(TicketErrorCauseNewDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    TEC.TICKET_ERROR_CAUSE_ID       as ticketErrorCauseId,");
        sql.append("    TEC.NAME       as name,");
        sql.append("    TEC.CODE       as code,");
        sql.append("    TEC.DESCRIPTION          as description,");
        sql.append("    TEC.PARENT_ID            as parentId,");
        sql.append("    TEC.STATUS               as status,");
        sql.append("    TEC.CREATE_USER          as createUser,");
        sql.append("    TEC.CREATE_DATE          as createDate,");
        sql.append("    TEC.UPDATE_USER          as updateUser,");
        sql.append("    TEC.UPDATE_DATE          as updateDate");
        sql.append(" FROM TICKET_ERROR_CAUSE TEC WHERE  1 = 1");

        if (params.getLstParentId() != null && params.getLstParentId().size() > 0 && params.getSearch() != null && params.getSearch().equals(0L)) {
            sql.append(" AND TEC.parent_id in (:parentId)");
            hmapParams.put("parentId", params.getLstParentId());
        }
        if(params.getLstParentId() != null && params.getLstParentId().size() > 0 && params.getSearch() != null && params.getSearch().equals(1L)){
            sql.append(" AND TEC.parent_id in (:parentId)");
            sql.append(" AND TEC.status =1");
            hmapParams.put("parentId", params.getLstParentId());
        }
        if (params.getLstParentId() == null && params.getSearch().equals(0L)) {
            sql.append(" AND TEC.parent_id is null");
        }

        if (params.getLstParentId() == null && params.getSearch().equals(1L)) {
            sql.append(" AND TEC.status =1");
            sql.append(" AND TEC.parent_id is null");
        }
        sql.append(" ORDER BY nlssort(TEC.NAME, 'nls_sort = Vietnamese') \n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketErrorCauseNewDTO.class);

    }

    @Override
    public Object searchTicketErrorCauseNew(Authentication authentication, TicketErrorCauseSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    TEC.TICKET_ERROR_CAUSE_ID       as ticketErrorCauseId,");
        sql.append("    TEC2.TICKET_ERROR_CAUSE_ID       as ticketErrorCauseId2,");
        sql.append("    TEC3.TICKET_ERROR_CAUSE_ID       as ticketErrorCauseId3,");
        sql.append("CASE                                                                                \n");
        sql.append("    WHEN TEC.LEVEL_ERROR = 3 THEN concat(TEC3.NAME,concat('/', TEC2.NAME))         \n");
        sql.append("    WHEN TEC.LEVEL_ERROR = 2 THEN TEC2.NAME                                        \n");
        sql.append("    ELSE null                                                                       \n");
        sql.append("    END                                     as errorCauseNameParent,               \n");
        sql.append("    TEC.LEVEL_ERROR                            as levelError,                         \n");
        sql.append("    TEC.CODE       as ticketErrorCauseCode,");
        sql.append("    TEC.NAME       as ticketErrorCauseName,");
        sql.append("    TEC.DESCRIPTION          as description,");
        sql.append("    TEC.PARENT_ID            as parentId,");
        sql.append("    TEC.STATUS               as status,");
        sql.append("    lower(TEC.CREATE_USER)          as createUser,");
        sql.append("    TEC.CREATE_DATE          as createDate,");
        sql.append("    lower(TEC.UPDATE_USER)          as updateUser,");
        sql.append("    TEC.UPDATE_DATE          as updateDate \n");
        sql.append("FROM TICKET_ERROR_CAUSE TEC              \n");
        sql.append("LEFT JOIN TICKET_ERROR_CAUSE TEC2 ON TEC.PARENT_ID = TEC2.TICKET_ERROR_CAUSE_ID \n");
        sql.append("LEFT JOIN TICKET_ERROR_CAUSE TEC3 ON TEC2.PARENT_ID = TEC3.TICKET_ERROR_CAUSE_ID \n");
        sql.append(" WHERE 1 = 1");

        if (params.getLstErrorCauseLv1() != null && params.getLstErrorCauseLv1().size() > 0 && params.getLstErrorCauseLv2().size()==0) {
            sql.append(" AND (TEC.PARENT_ID in (:ticketErrorCauseId)");
            sql.append(" OR (TEC.TICKET_ERROR_CAUSE_ID in (:ticketErrorCauseId)");
            sql.append(" OR TEC2.PARENT_ID in (:ticketErrorCauseId)))");
            hmapParams.put("ticketErrorCauseId", params.getLstErrorCauseLv1());
        }
        if (params.getLstErrorCauseLv2() != null && params.getLstErrorCauseLv2().size() > 0 && params.getLstErrorCauseLv3().size()==0) {
            sql.append(" and (TEC.PARENT_ID in (:ticketErrorCauseId2)");
            sql.append(" OR TEC.TICKET_ERROR_CAUSE_ID in (:ticketErrorCauseId2))");
            hmapParams.put("ticketErrorCauseId2", params.getLstErrorCauseLv2());
        }
        if (params.getLstErrorCauseLv3() != null && params.getLstErrorCauseLv3().size() > 0) {
            sql.append(" and TEC.TICKET_ERROR_CAUSE_ID in (:ticketErrorCauseId3)");
            hmapParams.put("ticketErrorCauseId3", params.getLstErrorCauseLv3());
        }

        if (params.getLstLevelError() != null && params.getLstLevelError().size() > 0) {
            sql.append(" and TEC.LEVEL_ERROR in (:levelError)");
            hmapParams.put("levelError", params.getLstLevelError());
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(TEC.CREATE_USER) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }

        if (params.getStatus() != null && params.getStatus().size() > 0) {
            sql.append(" and TEC.STATUS in (:status)");
            hmapParams.put("status", params.getStatus());
        }

        if (params.getFormDate() != null) {
            sql.append(" and trunc(TEC.CREATE_DATE) >= trunc(:formDate)");
            hmapParams.put("formDate", params.getFormDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(TEC.CREATE_DATE) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        sql.append(" ORDER BY TEC.CREATE_DATE  DESC");
        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), TicketErrorCauseNewDTO.class);

    }

    @Override
    public Object getDataDetailTicketErrorCause(Long ticketErrorCauseId, TicketErrorCauseNewDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" tec.TICKET_ERROR_CAUSE_ID AS ticketErrorCauseId,");
        sql.append(" tec.NAME AS ticketErrorCauseName,");
        sql.append(" tec.CODE AS ticketErrorCauseCode,");
        sql.append(" tec.DESCRIPTION AS description,");
        sql.append(" tec.STATUS AS status,");
        sql.append(" tec.PARENT_ID AS parentId,");
        sql.append(" tec.LEVEL_ERROR as levelError,");
        sql.append(" (SELECT tec2.TICKET_ERROR_CAUSE_ID FROM TICKET_ERROR_CAUSE tec2 WHERE tec2.TICKET_ERROR_CAUSE_ID =");
        sql.append(" (SELECT tec3.PARENT_ID FROM TICKET_ERROR_CAUSE tec3 WHERE tec3.TICKET_ERROR_CAUSE_ID = tec.PARENT_ID)) AS ticketErrorCauseId3");
        sql.append(" FROM TICKET_ERROR_CAUSE tec  WHERE 1=1");

        if (ticketErrorCauseId != null) {
            sql.append(" AND tec.TICKET_ERROR_CAUSE_ID = :ticketErrorCauseId            \n");
            hmapParams.put("ticketErrorCauseId", ticketErrorCauseId);
        }
        return getFirstData(sql, hmapParams, TicketErrorCauseNewDTO.class);
    }

    @Override
    public List<TicketErrorCauseEntity> getErrorCauseByParentId(List<Long> lstParentId, Long levelId) {
        StringBuilder sql = new StringBuilder("select tec");
        sql.append(" from TicketErrorCauseEntity tec");
        sql.append(" where tec.status = 1");
        sql.append(" and tec.levelError =:levelId");
        if (lstParentId != null && lstParentId.size() > 0) {
            sql.append(" and tec.parentId in (:parentId)");
        }

        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("levelId", levelId);
        if (lstParentId != null && lstParentId.size() > 0) {
            query.setParameter("parentId", lstParentId);
        }

        return query.getResultList();
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }
}
