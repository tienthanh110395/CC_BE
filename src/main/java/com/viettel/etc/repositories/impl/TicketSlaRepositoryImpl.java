package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketSlaRepository;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Wed Jan 26 09:59:24 ICT 2022
 */
@Repository
public class TicketSlaRepositoryImpl extends CommonDataBaseRepository implements TicketSlaRepository {

    @Autowired
    EntityManager entityManager;

    /**
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getTicketSla(TicketSLADTO itemParamsEntity) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from TICKET_SLA");
        List<Object> arrParams = new ArrayList<>();
        Integer start = null;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        ResultSelectEntity resultData = getListDataAndCount(sql, arrParams, start, pageSize, TicketSLADTO.class);
        return resultData;
    }

    @Override
    public ResultSelectEntity getTicketSlaDetail(Long ticketTypeId) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hashMap = new HashMap<>();
        sql.append("SELECT \n");
        sql.append("ts.TICKET_SLA_ID                      as    ticketSlaId,        \n");
        sql.append("ts.SLA_NAME                           as    slaName,            \n");
        sql.append("ts.SLA                                as    sla,                \n");
        sql.append("ts.DESCRIPTION                        as    description,        \n");
        sql.append("ts.STATUS                             as    status,             \n");
        sql.append("ts.SITE_ID                            as    siteId,             \n");
        sql.append("ts.SOURCE_ID                          as    sourceId,           \n");
        sql.append("ts.TICKET_TYPE_ID                     as    ticketTypeId,       \n");
        sql.append("ts.PROCESS_TIME                       as    processTime,        \n");
        sql.append("ts.PROCESS_TIME_TYPE                  as    processTimeType,    \n");
        sql.append("ts.COMBINE_TIME_L1                    as    combineTimeL1,      \n");
        sql.append("ts.COMBINE_TIME_L1_TYPE               as    combineTimeL1Type,  \n");
        sql.append("ts.COMBINE_TIME_L2                    as    combineTimeL2,      \n");
        sql.append("ts.COMBINE_TIME_L2_TYPE               as    combineTimeL2Type,  \n");
        sql.append("ts.MAN_TIME_SLA                       as    manTimeSla,         \n");
        sql.append("ts.IS_ADD_COMBINE                     as    isAddCombine,       \n");
        sql.append("ts.PROCESS_TYPE                       as    processType,        \n");
        sql.append("ts.PRIORITY_ID                        as    priorityId          \n");
        sql.append("FROM TICKET_SLA ts WHERE 1 = 1                                  \n");
        if (!FnCommon.isNullObject(ticketTypeId)) {
            sql.append("AND ts.TICKET_TYPE_ID = :ticketTypeId \n");
            hashMap.put("ticketTypeId", ticketTypeId);
        }
        return getListDataAndCount(sql, hashMap, null, null, TicketSLADTO.class);
    }

    @Override
    public Object getListTicketSLA(Authentication authentication, TicketSlaSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();

        sql.append("SELECT                                                                      \n");
        sql.append("sla.ticket_sla_id                           as ticketSlaId,                 \n");
        sql.append("sla.ticket_type_id                          as ticketTypeId,                \n");
        sql.append("tp.parent_id                                as parentId,                    \n");
        sql.append("tpgr.name                                   as ticketGroupName,             \n");
        sql.append("tpgr.ticket_type_id                         as ticketGroupId,               \n");
        sql.append("tpgen.ticket_type_id                        as ticketGenreId,               \n");
        sql.append("tpgen.name                                  as ticketGenreName,             \n");
        sql.append("tp.name                                     as ticketTypeName,              \n");
        sql.append("sla.PRIORITY_ID                             as priorityId,                  \n");
        sql.append("oc.NAME                                     as priorityName,                \n");
        sql.append("sla.PROCESS_TIME                            as processTime,                 \n");
        sql.append("sla.COMBINE_TIME_L1                         as combineTimeL1,               \n");
        sql.append("sla.COMBINE_TIME_L2                         as combineTimeL2,                \n");
        sql.append("sla.RE_TICKET_TIME                          as reTicketTime,                \n");
        sql.append("lower(sla.CREATE_USER)                             as createUser,                  \n");
        sql.append("sla.CREATE_DATE                             as createDate,                  \n");
        sql.append("lower(sla.UPDATE_USER)                             as updateUser,                  \n");
        sql.append("sla.UPDATE_DATE                             as updateDate                   \n");
        sql.append("FROM TICKET_SLA sla                                                         \n");
        sql.append("LEFT JOIN ticket_type tp ON sla.ticket_type_id = tp.TICKET_TYPE_ID          \n");
        sql.append("LEFT JOIN ticket_type tpgen ON tp.parent_id = tpgen.TICKET_TYPE_ID          \n");
        sql.append("LEFT JOIN ticket_type tpgr ON tpgen.parent_id = tpgr.TICKET_TYPE_ID         \n");
        sql.append("LEFT JOIN TICKET_PRIORITIES oc ON sla.PRIORITY_ID = oc.ID                    \n");
        sql.append("WHERE 1 = 1                                                                 \n");
        if (params.getLstTicketGroup() != null && params.getLstTicketGroup().size() > 0) {
            sql.append(" and tpgr.ticket_type_id in (:ticketGroupId)");
            hmapParams.put("ticketGroupId", params.getLstTicketGroup());
        }
        if (params.getLstTicketGenre() != null && params.getLstTicketGenre().size() > 0) {
            sql.append(" and tpgen.ticket_type_id in (:ticketGenreId)");
            hmapParams.put("ticketGenreId", params.getLstTicketGenre());
        }
        if (params.getLstTicketType() != null && params.getLstTicketType().size() > 0) {
            sql.append(" and sla.ticket_type_id in (:ticketTypeId)");
            hmapParams.put("ticketTypeId", params.getLstTicketType());
        }
        if (params.getLstPriority() != null && params.getLstPriority().size() > 0) {
            sql.append(" and sla.PRIORITY_ID in (:priorityId)");
            hmapParams.put("priorityId", params.getLstPriority());
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(sla.CREATE_USER) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }
        if (params.getFormDate() != null) {
            sql.append(" and trunc(sla.create_date) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFormDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(sla.create_date) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        sql.append(" ORDER BY sla.ticket_type_id DESC");
        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), TicketSLANewDTO.class);
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }

    @Override
    public Object getDataDetailById(Long ticketTypeId, TicketSLANewDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" tt.ticket_type_id AS ticketTypeId,");
        sql.append(" sla1.TICKET_SLA_ID AS ticketSlaId,");
        sql.append(" sla1.PRIORITY_ID AS priorityId,");
        sql.append(" tt.name AS ticketTypeName, tp.name AS priorityName, sla1.process_time AS processTime,");
        sql.append(" sla1.combine_time_l1 as combineTimeL1, sla1.combine_time_l2 AS combineTimeL2,");
        sql.append(" sla1.re_ticket_time AS reTicketTime, sla1.reception_time_from AS receptionTimeFrom,");
        sql.append(" sla1.reception_time_to   AS receptionTimeTo, tt.parent_id as ticketGenreId,");
        sql.append(" (SELECT tg.parent_id FROM ticket_type tg WHERE tt.parent_id = tg.ticket_type_id) AS ticketGroupId");
        sql.append(" FROM ticket_priorities tp");
        sql.append(" LEFT JOIN ( ");
        sql.append(" ( SELECT * FROM ticket_sla sla");
        sql.append(" WHERE sla.ticket_type_id = :ticketTypeId) sla1 ");
        sql.append(" ) ON tp.id = sla1.priority_id");
        sql.append(" LEFT JOIN ticket_type tt ON sla1.ticket_type_id = tt.ticket_type_id");
        sql.append(" WHERE 1 = 1");
        sql.append(" AND tp.is_active = 1");
        sql.append(" AND tp.type = 1");

        if (ticketTypeId != null) {
            hmapParams.put("ticketTypeId", ticketTypeId);
        }
        return getListDataAndCount(sql, hmapParams, null, null, TicketSLADetailDTO.class);
    }

    /**
     * @param : params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity exportImpact(TicketSlaSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" tt.code ticketGroupCode,tt.name ticketGroupName,tt2.code ticketGenreCode,tt2.name ticketGenreName, ");
        sql.append(" tt3.code ticketTypeCode,tt3.name ticketTypeName,tt3.ticket_template ticketTemplate, ");
        sql.append(" CASE WHEN tt.status = 1 THEN 'Đang hiệu lực' ELSE 'Không hiệu lực'END AS ticketGroupStatus, ");
        sql.append(" CASE WHEN tt2.status = 1 THEN 'Đang hiệu lực' ELSE 'Không hiệu lực' END AS ticketGenreStatus, ");
        sql.append(" CASE WHEN tt3.status = 1 THEN 'Đang hiệu lực' ELSE 'Không hiệu lực' END AS ticketTypeStatus, ");
        sql.append(" oc.CODE                                     as priorityCode,");
        sql.append(" oc.NAME                                     as priorityName,");
        sql.append(" sla.PROCESS_TIME                            as processTime,");
        sql.append(" sla.COMBINE_TIME_L1                         as combineTimeL1,");
        sql.append(" sla.COMBINE_TIME_L2                         as combineTimeL2,");
        sql.append(" sla.RE_TICKET_TIME                          as reTicketTime,");
        sql.append(" LOWER(sla.CREATE_USER)                      as createUser,");
        sql.append(" sla.CREATE_DATE                             as createDate,");
        sql.append(" LOWER(sla.UPDATE_USER )                     as updateUser,");
        sql.append(" sla.UPDATE_DATE                             as updateDate,");
        sql.append(" sla.RECEPTION_TIME_FROM                     as receptionTimeFrom,");
        sql.append(" sla.RECEPTION_TIME_TO                       as receptionTimeTo");
        sql.append(" FROM ticket_type tt");
        sql.append(" left join ticket_type tt2 on tt2.parent_id = tt.TICKET_TYPE_ID ");
        sql.append(" left join ticket_type tt3 on tt3.parent_id = tt2.TICKET_TYPE_ID ");
        sql.append(" left join ticket_sla  sla ON sla.ticket_type_id = tt3.ticket_type_id ");
        sql.append(" left join ticket_priorities  oc ON sla.priority_id = oc.id");
        sql.append(" WHERE 1 = 1 and tt.parent_id is null ");
        if (params.getLstTicketGroup() != null && params.getLstTicketGroup().size() > 0) {
            sql.append(" and tt.ticket_type_id in (:ticketGroupId)");
            hmapParams.put("ticketGroupId", params.getLstTicketGroup());
        }
        if (params.getLstTicketGenre() != null && params.getLstTicketGenre().size() > 0) {
            sql.append(" and tt2.ticket_type_id in (:ticketGenreId)");
            hmapParams.put("ticketGenreId", params.getLstTicketGenre());
        }
        if (params.getLstTicketType() != null && params.getLstTicketType().size() > 0) {
            sql.append(" and sla.ticket_type_id in (:ticketTypeId)");
            hmapParams.put("ticketTypeId", params.getLstTicketType());
        }
        if (params.getLstPriority() != null && params.getLstPriority().size() > 0) {
            sql.append(" and sla.PRIORITY_ID in (:priorityId)");
            hmapParams.put("priorityId", params.getLstPriority());
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(sla.CREATE_USER) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }
        if (params.getFormDate() != null) {
            sql.append(" and trunc(sla.create_date) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFormDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(sla.create_date) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }
        sql.append(" ORDER BY sla.ticket_sla_id ASC");

        return getListDataAndCount(sql, hmapParams, null, null, TicketSLANewDTO.class);
    }

    @Override
    @Transactional
    public int updateReptionTime(String receptionTimeFrom, String receptionTimeTo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append("TICKET_SLA ");
        sql.append("SET RECEPTION_TIME_FROM =:receptionTimeFrom,RECEPTION_TIME_TO =:receptionTimeTo");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("receptionTimeFrom", receptionTimeFrom);
        query.setParameter("receptionTimeTo", receptionTimeTo);

        return query.executeUpdate();
    }
}