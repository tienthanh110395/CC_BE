package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketCategoryRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Autogen class Repository Impl: Lop thao tac  danh sach cac danh muc
 *
 * @author ToolGen
 * @date Tue Mar 02 14:25:37 ICT 2021
 */
@Repository
public class TicketCategoryRepositoryImpl extends CommonDataBaseRepository implements TicketCategoryRepository {
    /***
     * lay danh sach loai phan anh
     * @param dataParams
     * @return
     */
    @Override
    public ResultSelectEntity getTicketTypes(TicketTypeDTO dataParams, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    TK.TICKET_TYPE_ID       as ticketTypeId,\n");
        sql.append("    TK.NAME                 as name,        \n");
        sql.append("    TK.CODE                 as code,        \n");
        sql.append("    TK.DESCRIPTION          as description, \n");
        sql.append("    TK.PARENT_ID            as parentId,    \n");
        sql.append("    TK.STATUS               as status,      \n");
        sql.append("    TK.CREATE_USER          as createUser,  \n");
        sql.append("    TK.CREATE_DATE          as createDate,  \n");
        sql.append("    TK.UPDATE_USER          as updateUser,  \n");
        sql.append("    TK.UPDATE_DATE          as updateDate,   \n");
        sql.append("    TK.TICKET_TEMPLATE      as ticketTemplate,   \n");
        sql.append("    TK.DEAD_TIME_TYPE       as deadTimeType,   \n");
        sql.append("    TK.HOT_SLA              as hotSla,       \n");
        sql.append("    TK.OTHERS_SLA           as othersSla   \n");
        sql.append(" FROM TICKET_TYPE TK WHERE  TK.STATUS = 1   \n");
        if (dataParams.getTicketTypeId() != null) {
            sql.append(" AND TK.TICKET_TYPE_ID = :ticketTypeId\n");
            hmapParams.put("ticketTypeId", dataParams.getTicketTypeId());
        } else {
            if (dataParams.getParentId() == null || "".equals(dataParams.getParentId())) {
                sql.append(" AND TK.PARENT_ID is null\n");
            } else {
                sql.append(" AND TK.PARENT_ID in (:parentId)\n");
                hmapParams.put("parentId", Arrays.stream(dataParams.getParentId().split(",")).filter(FnCommon::isNumeric).collect(Collectors.toList()));
            }
        }

        String clientId = FnCommon.getClientId(authentication);
        if (Constants.APP_CLIENT_ID.PORTAL_CHU_PT.equals(clientId) || Constants.APP_CLIENT_ID.APP_CHU_PT.equals(clientId)) {
            sql.append(" AND TK.IS_CPT = 1\n");
        }

        if (dataParams.getType() != null) {
            sql.append(" AND TK.TYPE = :type\n");
            hmapParams.put("type", dataParams.getType());
        }

        sql.append(" order by nlssort(TK.NAME, 'nls_sort = Vietnamese') \n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketTypeDTO.class);
    }

    /***
     * Lay danh sach phong ban
     * @param dataParams
     * @return
     */
    @Override
    public ResultSelectEntity getTicketSiteByParentId(TicketSiteDTO dataParams) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("   TS.SITE_ID                                                 as siteId,                    \n");
        sql.append("   TS.SITE_CODE                                               as siteCode,                  \n");
        sql.append("   TS.SITE_NAME                                               as siteName,                  \n");
        sql.append("   TS.PARENT_ID                                               as parentId,                  \n");
        sql.append("   TS.ADDRESS                                                 as address,                   \n");
        sql.append("   TS.USERNAME                                                as username,                  \n");
        sql.append("   TS.PASSWORD                                                as password,                  \n");
        sql.append("   TS.EMAIL                                                   as email,                     \n");
        sql.append("   TS.PHONE                                                   as phone,                     \n");
        sql.append("   TS.STATUS                                                  as status,                    \n");
        sql.append("   TS.CREATE_USER                                             as createUser,                \n");
        sql.append("   TS.CREATE_DATE                                             as createDate,                \n");
        sql.append("   TS.UPDATE_USER                                             as updateUser,                \n");
        sql.append("   TS.UPDATE_DATE                                             as updateDate                \n");
        sql.append(" FROM TICKET_SITE TS      WHERE    TS.STATUS = 1                                            \n");
        if (dataParams.getParentId() == null) {
            sql.append("  AND TS.PARENT_ID is null\n");
        } else {
            sql.append(" AND TS.PARENT_ID = :parentId \n");
            hmapParams.put("parentId", dataParams.getParentId());
        }
        sql.append(" order by nlssort(TS.SITE_NAME, 'nls_sort = Vietnamese')\n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketSiteDTO.class);
    }

    /***
     * Lay thoi han su ly cua 1 loai phan anh
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketSla(TicketSLADTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                  \n");
        sql.append("      TSL.TICKET_SLA_ID         as ticketSlaId,         \n");
        sql.append("      TSL.SLA_NAME              as slaName,             \n");
        sql.append("      TSL.SLA                   as sla,                 \n");
        sql.append("      TSL.DESCRIPTION           as description,         \n");
        sql.append("      TSL.STATUS                as status,              \n");
        sql.append("      TSL.SITE_ID               as siteId,              \n");
        sql.append("      TSL.SOURCE_ID             as sourceId,            \n");
        sql.append("      TSL.TICKET_TYPE_ID        as ticketTypeId,        \n");
        sql.append("      TSL.CREATE_USER           as createUser,          \n");
        sql.append("      TSL.CREATE_DATE           as createDate,          \n");
        sql.append("      TSL.UPDATE_USER           as updateUser,          \n");
        sql.append("      TSL.PROCESS_TIME          as processTime,          \n");
        sql.append("      TSL.PROCESS_TIME_TYPE     as processTimeType,          \n");
        sql.append("      TSL.COMBINE_TIME_L1       as combineTimeL1,          \n");
        sql.append("      TSL.COMBINE_TIME_L1_TYPE  as combineTimeL1Type,          \n");
        sql.append("      TSL.COMBINE_TIME_L2       as combineTimeL2,          \n");
        sql.append("      TSL.COMBINE_TIME_L2_TYPE  as combineTimeL2Type,          \n");
        sql.append("      TSL.MAN_TIME_SLA          as manTimeSla,          \n");
        sql.append("      TSL.IS_ADD_COMBINE        as isAddCombine,          \n");
        sql.append("      TSL.PROCESS_TYPE          as processType,          \n");
        sql.append("      TSL.PRIORITY_ID           as priorityId           \n");
        sql.append("FROM  TICKET_SLA TSL                                    \n");
        sql.append(" WHERE TSL.STATUS = 1                                   \n");
//        if (!FnCommon.isNullObject(itemParamsEntity.getSiteId())) {
//            sql.append(" AND TSL.SITE_ID = :siteId \n");
//            hmapParams.put("siteId", itemParamsEntity.getSiteId());
//        }
//        if (!FnCommon.isNullObject(itemParamsEntity.getSourceId())) {
//            sql.append(" AND TSL.SOURCE_ID = :sourceId \n");
//            hmapParams.put("sourceId", itemParamsEntity.getSourceId());
//        }
        if (!FnCommon.isNullObject(itemParamsEntity.getTicketTypeId())) {
            sql.append(" AND TSL.TICKET_TYPE_ID = :ticketTypeId \n");
            hmapParams.put("ticketTypeId", itemParamsEntity.getTicketTypeId());
        }
        if (!FnCommon.isNullObject(itemParamsEntity.getPriorityId())) {
            sql.append(" AND TSL.PRIORITY_ID = :priorityId \n");
            hmapParams.put("priorityId", itemParamsEntity.getPriorityId());
        }
        sql.append(" order by nlssort(TSL.SLA_NAME, 'nls_sort = Vietnamese')\n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketSLADTO.class);
    }

    /**
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketSource(TicketSourceDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                          \n");
        sql.append("    ts.ticket_source_id   AS ticketSourceId,                    \n");
        sql.append("    ts.name               AS name,                              \n");
        sql.append("    ts.source_code        AS sourceCode,                        \n");
        sql.append("    ts.description        AS description,                       \n");
        sql.append("    ts.status             AS status,                            \n");
        sql.append("    ts.create_user        AS createUser,                        \n");
        sql.append("    ts.create_date        AS createDate,                        \n");
        sql.append("    ts.update_user        AS updateUser,                        \n");
        sql.append("    ts.update_date        AS updateDate                         \n");
        sql.append("FROM                                                            \n");
        sql.append("    ticket_source ts                                            \n");
        sql.append("WHERE                                                           \n");
        sql.append("    ts.status = 1                                               \n");
        sql.append("ORDER BY                                                        \n");
        sql.append("    nlssort(ts.name, 'nls_sort = Vietnamese')                   \n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketSourceDTO.class);
    }

    /**
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketTypesTree(TicketTypeDTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.ticket_type_id as ticketTypeId, a.name, a.code, a.parent_id as parentId, level from ticket_type a where status = 1 START WITH parent_id is null CONNECT BY PRIOR ticket_type_id = parent_id");
        return getListDataAndCount(sql, new HashMap<>(), null, null, TicketTypeDTO.class);
    }

    /***
     * Lay danh sach nhan vien
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketSiteUser(TicketSiteUserDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("   TSU.TICKET_SITE_USER_ID                     as ticketSiteUserId,        \n");
        sql.append("   TSU.SITE_ID                                 as siteId,                  \n");
        sql.append("   TSU.USER_ID                                 as userId,                  \n");
        sql.append("   TSU.USER_NAME                               as userName,                \n");
        sql.append("   TSU.STAFF_CODE                              as staffCode,               \n");
        sql.append("   TSU.STAFF_NAME                              as staffName,               \n");
        sql.append("   TSU.STATUS                                  as status,                  \n");
        sql.append("   TSU.EMAIL                                   as email,                   \n");
        sql.append("   TSU.PHONE                                   as phone                    \n");
        sql.append(" FROM TICKET_SITE_USER TSU WHERE 1 = 1                                     \n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketSiteUserDTO.class);
    }

    /***
     * Lay danh sach ly do gia han
     * @param itemParamsEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketExtentReason(TicketExtentReasonDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("   TEX.TICKET_EXTENT_REASON_ID              as ticketExtentReasonId,  \n");
        sql.append("   TEX.NAME                                 as name,                  \n");
        sql.append("   TEX.DESCRIPTION                          as description,           \n");
        sql.append("   TEX.STATUS                               as status                 \n");
        sql.append(" FROM TICKET_EXTENT_REASON TEX WHERE 1 = 1                            \n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketExtentReasonDTO.class);
    }
}
