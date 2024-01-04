package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketCateConfigDTO;
import com.viettel.etc.dto.TicketExpireCauseDTO;
import com.viettel.etc.dto.TicketExpireCauseNewDTO;
import com.viettel.etc.dto.TicketExpireCauseSearchDTO;
import com.viettel.etc.repositories.TicketExpireCauseRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Autogen class Repository Impl: Lop thao danh muc nguyen nhan qua han
 *
 * @author ToolGen
 * @date Thu Jun 03 11:31:38 ICT 2021
 */
@Repository
public class TicketExpireCauseRepositoryImpl extends CommonDataBaseRepository implements TicketExpireCauseRepository {

    /**
     * Tìm kiếm danh muc nguyen nhan qua han
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity searchTicketExpireCause(TicketExpireCauseDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("select                                                                  \n");
        sql.append("TEC.TICKET_EXPIRE_CAUSE_ID                  as ticketExpireCauseId,     \n");
        sql.append("TEC.PARENT_ID                               as parentId,                \n");
        sql.append("TEC.CODE                                    as code,                    \n");
        sql.append("TEC.NAME                                    as name,                    \n");
        sql.append("TEC.STATUS                                  as status,                  \n");
        sql.append("TEC.DESCRIPTION                             as description,             \n");
        sql.append("TEC2.TICKET_EXPIRE_CAUSE_ID                 as ticketExpireCauseId2,    \n");
        sql.append("TEC2.PARENT_ID                              as parentId2,               \n");
        sql.append("TEC2.CODE                                   as code2,                   \n");
        sql.append("TEC2.NAME                                   as name2,                   \n");
        sql.append("TEC3.TICKET_EXPIRE_CAUSE_ID                 as ticketExpireCauseId3,    \n");
        sql.append("TEC3.PARENT_ID                              as parentId3,               \n");
        sql.append("TEC3.CODE                                   as code3,                   \n");
        sql.append("TEC3.NAME                                   as name3                    \n");
        sql.append("FROM TICKET_EXPIRE_CAUSE TEC                                            \n");
        sql.append("LEFT JOIN TICKET_EXPIRE_CAUSE TEC2 on TEC.TICKET_EXPIRE_CAUSE_ID = TEC2.PARENT_ID \n");
        sql.append("LEFT JOIN TICKET_EXPIRE_CAUSE TEC3 on TEC2.TICKET_EXPIRE_CAUSE_ID = TEC3.PARENT_ID \n");

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
        return getListDataAndCount(sql, hmapParams, start, pageSize, TicketExpireCauseDTO.class);
    }

    /**
     * Tìm kiếm theo tree danh muc nguyen nhan qua han
     *
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity searchTreeTicketExpireCause(TicketExpireCauseDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT TEC.TICKET_EXPIRE_CAUSE_ID as ticketExpireCauseId, TEC.PARENT_ID as parentId, TEC.CODE as code, TEC.NAME as name, " +
                "TEC.STATUS as status, TEC.DESCRIPTION as description FROM TICKET_EXPIRE_CAUSE TEC WHERE 1 = 1 START WITH TEC.PARENT_ID is null " +
                "CONNECT BY PRIOR TEC.TICKET_EXPIRE_CAUSE_ID = TEC.PARENT_ID");
        return getListDataAndCount(sql, hmapParams, null, null, TicketExpireCauseDTO.class);
    }

    @Override
    public Object getListExpireCause(Authentication authentication, TicketExpireCauseSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();

        sql.append("SELECT                                                                              \n");
        sql.append("TEC.TICKET_EXPIRE_CAUSE_ID                   as ticketExpireCauseId,                \n");
        sql.append("TEC2.TICKET_EXPIRE_CAUSE_ID                  as ticketExpireCauseTwoId,             \n");
        sql.append("TEC3.TICKET_EXPIRE_CAUSE_ID                  as ticketExpireCauseThreeId,           \n");
        sql.append("CASE                                                                                \n");
        sql.append("    WHEN TEC.LEVEL_EXPIRE = 3 THEN concat(TEC3.NAME,concat('/', TEC2.NAME))         \n");
        sql.append("    WHEN TEC.LEVEL_EXPIRE = 2 THEN TEC2.NAME                                        \n");
        sql.append("    ELSE null                                                                       \n");
        sql.append("    END                                     as expireCauseNameParent,               \n");
        sql.append("TEC.LEVEL_EXPIRE                            as levelExpire,                         \n");
        sql.append("TEC.CODE                                    as expireCauseCode,                     \n");
        sql.append("TEC.NAME                                    as expireCauseName,                     \n");
        sql.append("TEC.PARENT_ID                               as parentId,                            \n");
        sql.append("TEC.DESCRIPTION                             as description,                         \n");
        sql.append("TEC.STATUS                                  as status,                              \n");
        sql.append("lower(TEC.CREATE_USER)                             as createUser,                          \n");
        sql.append("TEC.CREATE_DATE                             as createDate,                          \n");
        sql.append("lower(TEC.UPDATE_USER)                             as updateUser,                          \n");
        sql.append("TEC.UPDATE_DATE                             as updateDate                           \n");
        sql.append("FROM TICKET_EXPIRE_CAUSE TEC                                                        \n");
        sql.append("LEFT JOIN TICKET_EXPIRE_CAUSE TEC2 on TEC.PARENT_ID = TEC2.TICKET_EXPIRE_CAUSE_ID   \n");
        sql.append("LEFT JOIN TICKET_EXPIRE_CAUSE TEC3 on TEC2.PARENT_ID = TEC3.TICKET_EXPIRE_CAUSE_ID  \n");
        sql.append(" WHERE 1 = 1");
        if (params.getLstExpireCauseOne() != null && params.getLstExpireCauseOne().size() > 0 && params.getLstExpireCauseTwo().size() == 0) {
            sql.append(" AND (TEC.PARENT_ID in (:ticketExpireCauseId)");
            sql.append(" OR (TEC.TICKET_EXPIRE_CAUSE_ID in (:ticketExpireCauseId)");
            sql.append(" OR TEC2.PARENT_ID in (:ticketExpireCauseId)))");
            hmapParams.put("ticketExpireCauseId", params.getLstExpireCauseOne());
        }
        if (params.getLstExpireCauseTwo() != null && params.getLstExpireCauseTwo().size() > 0 && params.getLstExpireCauseThree().size() == 0) {
            sql.append(" and (TEC.PARENT_ID in (:ticketExpireCauseTwoId)");
            sql.append(" OR TEC.TICKET_EXPIRE_CAUSE_ID in (:ticketExpireCauseTwoId))");
            hmapParams.put("ticketExpireCauseTwoId", params.getLstExpireCauseTwo());
        }
        if (params.getLstExpireCauseThree() != null && params.getLstExpireCauseThree().size() > 0) {
            sql.append(" and TEC.TICKET_EXPIRE_CAUSE_ID in (:ticketExpireCauseThreeId)");
            hmapParams.put("ticketExpireCauseThreeId", params.getLstExpireCauseThree());
        }
        if (params.getLstLevelExpire() != null && params.getLstLevelExpire().size() > 0) {
            sql.append(" and TEC.LEVEL_EXPIRE in (:levelExpire)");
            hmapParams.put("levelExpire", params.getLstLevelExpire());
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(TEC.CREATE_USER) like :createUser \n");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }

        if (params.getStatus() != null && params.getStatus().size() > 0) {
            sql.append(" and TEC.status in (:status)");
            hmapParams.put("status", params.getStatus());
        }

        if (params.getFormDate() != null) {
            sql.append(" and trunc(TEC.create_date) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFormDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(TEC.create_date) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        sql.append(" ORDER BY TEC.CREATE_DATE  DESC");
        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), TicketExpireCauseNewDTO.class);
    }

    @Override
    public ResultSelectEntity getExpireLevelByParent(TicketExpireCauseDTO dataParams) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();

        sql.append("SELECT  \n");
        sql.append("    TEC.TICKET_EXPIRE_CAUSE_ID          as ticketExpireCauseId,");
        sql.append("    TEC.NAME                            as name,");
        sql.append("    TEC.CODE                            as code,");
        sql.append("    TEC.PARENT_ID                       as parentId,");
        sql.append("    TEC.STATUS                          as status,");
        sql.append("    TEC.CREATE_USER                     as createUser,");
        sql.append("    TEC.CREATE_DATE                     as createDate,");
        sql.append("    TEC.UPDATE_USER                     as updateUser,");
        sql.append("    TEC.UPDATE_DATE                     as updateDate");
        sql.append(" FROM TICKET_EXPIRE_CAUSE TEC WHERE  1 = 1");
        if (dataParams.getLstParentId() != null && dataParams.getLstParentId().size() > 0 && dataParams.getSearch() != null && dataParams.getSearch().equals(0L)) {
            sql.append(" AND TEC.parent_id in (:parentId)");
            hmapParams.put("parentId", dataParams.getLstParentId());
        }
        if (dataParams.getLstParentId() != null && dataParams.getLstParentId().size() > 0 && dataParams.getSearch() != null && dataParams.getSearch().equals(1L)) {
            sql.append(" AND TEC.parent_id in (:parentId)");
            sql.append(" AND TEC.status = 1 ");
            hmapParams.put("parentId", dataParams.getLstParentId());
        }
        if (dataParams.getLstParentId() == null && dataParams.getSearch().equals(0L)) {
            sql.append(" AND TEC.parent_id is null");
        }

        if (dataParams.getLstParentId() == null && dataParams.getSearch().equals(1L)) {
            sql.append(" AND TEC.status =1");
            sql.append(" AND TEC.parent_id is null");
        }

        sql.append(" ORDER BY nlssort(TEC.NAME, 'nls_sort = Vietnamese') \n");


        return getListDataAndCount(sql, hmapParams, null, null, TicketExpireCauseDTO.class);
    }

    @Override
    public Object getDataDetailById(Long ticketExpireCauseId, TicketExpireCauseNewDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" tec.TICKET_EXPIRE_CAUSE_ID AS ticketExpireCauseId,");
        sql.append(" tec.NAME AS expireCauseName,");
        sql.append(" tec.CODE AS expireCauseCode,");
        sql.append(" tec.DESCRIPTION AS description,");
        sql.append(" tec.STATUS AS status,");
        sql.append(" tec.PARENT_ID AS parentId,");
        sql.append(" tec.LEVEL_EXPIRE as levelExpire,");
        sql.append(" (SELECT tec2.TICKET_EXPIRE_CAUSE_ID FROM TICKET_EXPIRE_CAUSE tec2 WHERE tec2.TICKET_EXPIRE_CAUSE_ID =");
        sql.append(" (SELECT tec3.PARENT_ID FROM TICKET_EXPIRE_CAUSE tec3 WHERE tec3.TICKET_EXPIRE_CAUSE_ID = tec.PARENT_ID)) AS ticketExpireCauseThreeId");
        sql.append(" FROM TICKET_EXPIRE_CAUSE tec  WHERE 1=1");

        if (ticketExpireCauseId != null) {
            sql.append(" AND tec.TICKET_EXPIRE_CAUSE_ID = :ticketExpireCauseId            \n");
            hmapParams.put("ticketExpireCauseId", ticketExpireCauseId);
        }
        return getFirstData(sql, hmapParams, TicketExpireCauseNewDTO.class);
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }
}
