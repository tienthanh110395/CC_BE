package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.*;
import com.viettel.etc.repositories.TicketCateStatisticRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class TicketCateStatisticRepositoryImpl extends CommonDataBaseRepository implements TicketCateStatisticRepository {

    @Override
    public Object searchTicketCateStatistic(Authentication authentication, TicketCateStatisticSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    st.STATISTIC_TYPE_ID       as statisticTypeId,");
        sql.append("CASE                                                                                                          ");
        sql.append("    WHEN st.LEVEL_STATISTIC = 5 THEN st5.NAME || '/' || st4.NAME || '/' || st3.NAME || '/' || st2.NAME        ");
        sql.append("    WHEN st.LEVEL_STATISTIC = 4 THEN st4.NAME || '/' || st3.NAME || '/' || st2.NAME                           ");
        sql.append("    WHEN st.LEVEL_STATISTIC = 3 THEN st3.NAME || '/' || st2.NAME                                              ");
        sql.append("    WHEN st.LEVEL_STATISTIC = 2 THEN st2.NAME                                                                 ");
        sql.append("    ELSE null                                                                                                 ");
        sql.append("    END                                     as cateStatisticNameParent,                                       ");
        sql.append("    st.LEVEL_STATISTIC                            as levelStatistic,                                          ");
        sql.append("    st.CODE       as ticketCateStatisticsCode,");
        sql.append("    st.NAME       as ticketCateStatisticsName,");
        sql.append("    st.DESCRIPTION          as description,");
        sql.append("    st.PARENT_ID            as parentId,");
        sql.append("    st.STATUS               as status,");
        sql.append("    lower(st.CREATE_USER)          as createUser,");
        sql.append("    st.CREATE_DATE          as createDate,");
        sql.append("    lower(st.UPDATE_USER)          as updateUser,");
        sql.append("    st.UPDATE_DATE          as updateDate, ");
        sql.append("    st.TEMPLATE          as template ");
        sql.append(" FROM STATISTIC_TYPE st              ");
        sql.append(" LEFT JOIN STATISTIC_TYPE st2 ON st.PARENT_ID = st2.STATISTIC_TYPE_ID ");
        sql.append(" LEFT JOIN STATISTIC_TYPE st3 ON st2.PARENT_ID = st3.STATISTIC_TYPE_ID ");
        sql.append(" LEFT JOIN STATISTIC_TYPE st4 ON st3.PARENT_ID = st4.STATISTIC_TYPE_ID ");
        sql.append(" LEFT JOIN STATISTIC_TYPE st5 ON st4.PARENT_ID = st5.STATISTIC_TYPE_ID ");
        sql.append(" WHERE 1 = 1");

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(st.CREATE_USER) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }
        if (params.getLstCateStatisticsLv1() != null && params.getLstCateStatisticsLv1().size() > 0 && params.getLstCateStatisticsLv2().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId)");
            sql.append(" or (st.parent_id in (:statisticTypeId)");
            sql.append(" or (st2.parent_id in (:statisticTypeId)");
            sql.append(" or (st3.parent_id in (:statisticTypeId)");
            sql.append(" or st4.parent_id in (:statisticTypeId)))))");
            hmapParams.put("statisticTypeId", params.getLstCateStatisticsLv1());
        }
        if (params.getLstCateStatisticsLv2() != null && params.getLstCateStatisticsLv2().size() > 0 && params.getLstCateStatisticsLv3().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId2)");
            sql.append(" or (st.parent_id in (:statisticTypeId2)");
            sql.append(" or (st2.parent_id in (:statisticTypeId2)");
            sql.append(" or st3.parent_id in (:statisticTypeId2))))");
            hmapParams.put("statisticTypeId2", params.getLstCateStatisticsLv2());
        }
        if (params.getLstCateStatisticsLv3() != null && params.getLstCateStatisticsLv3().size() > 0 && params.getLstCateStatisticsLv4().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId3)");
            sql.append(" or (st.parent_id in (:statisticTypeId3)");
            sql.append(" or st2.parent_id in (:statisticTypeId3)))");
            hmapParams.put("statisticTypeId3", params.getLstCateStatisticsLv3());
        }

        if (params.getLstCateStatisticsLv4() != null && params.getLstCateStatisticsLv4().size() > 0 && params.getLstCateStatisticsLv5().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId4)");
            sql.append(" or st.parent_id in (:statisticTypeId4))");
            hmapParams.put("statisticTypeId4", params.getLstCateStatisticsLv4());
        }

        if (params.getLstCateStatisticsLv5() != null && params.getLstCateStatisticsLv5().size() > 0) {
            sql.append(" and st.STATISTIC_TYPE_ID in (:statisticTypeId5)");
            hmapParams.put("statisticTypeId5", params.getLstCateStatisticsLv5());
        }

        if (params.getLstLevelStatistics() != null && params.getLstLevelStatistics().size() > 0) {
            sql.append(" and st.LEVEL_STATISTIC in (:levelStatistic)");
            hmapParams.put("levelStatistic", params.getLstLevelStatistics());
        }



        if (params.getStatus() != null && params.getStatus().size() > 0) {
            sql.append(" and st.STATUS in (:status)");
            hmapParams.put("status", params.getStatus());
        }

        if (params.getFromDate() != null) {
            sql.append(" and trunc(st.CREATE_DATE) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFromDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(st.CREATE_DATE) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

//        sql.append(" ORDER BY st.STATISTIC_TYPE_ID, st2.STATISTIC_TYPE_ID, st3.STATISTIC_TYPE_ID, st4.STATISTIC_TYPE_ID,st5.STATISTIC_TYPE_ID ASC");
        sql.append(" ORDER BY st.CREATE_DATE DESC");

        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), TicketCateStatisticDTO.class);

    }

    @Override
    public ResultSelectEntity getTicketCateStatisticByParent(TicketCateStatisticDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("    st.STATISTIC_TYPE_ID       as statisticTypeId,");
        sql.append("    st.NAME       as name,");
        sql.append("    st.CODE       as code,");
        sql.append("    st.DESCRIPTION          as description,");
        sql.append("    st.TEMPLATE          as template,");
        sql.append("    st.PARENT_ID            as parentId,");
        sql.append("    st.STATUS               as status,");
        sql.append("    st.CREATE_USER          as createUser,");
        sql.append("    st.CREATE_DATE          as createDate,");
        sql.append("    st.UPDATE_USER          as updateUser,");
        sql.append("    st.UPDATE_DATE          as updateDate");
        sql.append(" FROM STATISTIC_TYPE st WHERE  1 = 1");

        if (params.getLstParentId() != null && params.getLstParentId().size() > 0 && params.getSearch() != null && params.getSearch().equals(0L)) {
            sql.append(" AND st.parent_id in (:parentId)");
            hmapParams.put("parentId", params.getLstParentId());
        }
        else if(params.getLstParentId() != null && params.getLstParentId().size() > 0 && params.getSearch() != null && params.getSearch().equals(1L)){
            sql.append(" AND st.parent_id in (:parentId)");
            sql.append(" AND st.status =1");
            hmapParams.put("parentId", params.getLstParentId());
        }
        if (params.getLstParentId() == null && params.getSearch().equals(0L)) {
            sql.append(" AND st.parent_id is null");
        }

        if (params.getLstParentId() == null && params.getSearch().equals(1L)) {
            sql.append(" AND st.status =1");
            sql.append(" AND st.parent_id is null");
        }
        sql.append(" ORDER BY nlssort(st.NAME, 'nls_sort = Vietnamese') \n");
        return getListDataAndCount(sql, hmapParams, null, null, TicketCateStatisticDTO.class);

    }

    @Override
    public Object getDataDetailTicketCateStatistic(Long statisticTypeId, TicketCateStatisticDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT");
        sql.append(" st.STATISTIC_TYPE_ID AS statisticTypeId,");
        sql.append(" st.NAME AS ticketCateStatisticsName,");
        sql.append(" st.CODE AS ticketCateStatisticsCode,");
        sql.append(" st.DESCRIPTION AS description,");
        sql.append(" st.TEMPLATE AS template,");
        sql.append(" st.STATUS AS status,");
        sql.append(" st.LEVEL_STATISTIC as levelStatistic,   ");
        sql.append(" st.PARENT_ID AS parentId1,");
        sql.append(" stpr1.NAME AS cateStatisticNameParent1,");
        sql.append("stpr1.PARENT_ID  as parentId2, ");
        sql.append("stpr2.NAME  as cateStatisticNameParent2, ");
        sql.append("stpr2.PARENT_ID  as parentId3, ");
        sql.append("stpr3.NAME  as cateStatisticNameParent3, ");
        sql.append("stpr3.PARENT_ID  as parentId4,");
        sql.append("stpr4.NAME  as cateStatisticNameParent4\n ");
        sql.append("FROM STATISTIC_TYPE st ");

        // join lay cap parent
        sql.append("LEFT JOIN statistic_type stpr1 on st.parent_id = stpr1.statistic_type_id ");
        sql.append("LEFT JOIN statistic_type stpr2 on stpr1.parent_id = stpr2.statistic_type_id ");
        sql.append("LEFT JOIN statistic_type stpr3 on stpr2.parent_id = stpr3.statistic_type_id ");
        sql.append("LEFT JOIN statistic_type stpr4 on stpr3.parent_id = stpr4.statistic_type_id ");
        sql.append(" WHERE 1=1");

        if (statisticTypeId != null) {
            sql.append(" AND st.STATISTIC_TYPE_ID = :statisticTypeId            \n");
            hmapParams.put("statisticTypeId", statisticTypeId);
        }
        return getFirstData(sql, hmapParams, TicketCateStatisticDTO.class);
    }

    @Override
    public ResultSelectEntity exportFile(TicketCateStatisticSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT  \n");
        sql.append("   CASE \n" +
                "     WHEN st.level_statistic =  1 THEN st.code\n" +
                "     WHEN st.level_statistic = 2 THEN st2.code\n" +
                "     WHEN st.level_statistic = 3 THEN st3.code\n" +
                "     WHEN st.level_statistic = 4 THEN st4.code\n" +
                "     WHEN st.level_statistic = 5 THEN st5.code\n" +
                "  END as ticketCateStatisticsCode1,");
        sql.append("   CASE \n" +
                "     WHEN st.level_statistic =  1 THEN st.name\n" +
                "     WHEN st.level_statistic = 2 THEN st2.name\n" +
                "     WHEN st.level_statistic = 3 THEN st3.name\n" +
                "     WHEN st.level_statistic = 4 THEN st4.name\n" +
                "     WHEN st.level_statistic = 5 THEN st5.name\n" +
                "  END as cateStatisticNameParent1,");
        sql.append("   CASE \n" +
                "     WHEN st.level_statistic =  1 THEN st.status\n" +
                "     WHEN st.level_statistic = 2 THEN st2.status\n" +
                "     WHEN st.level_statistic = 3 THEN st3.status\n" +
                "     WHEN st.level_statistic = 4 THEN st4.status\n" +
                "     WHEN st.level_statistic = 5 THEN st5.status\n" +
                "  END as status1,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN st.code\n" +
                "     WHEN st.level_statistic = 3 THEN st2.code\n" +
                "     WHEN st.level_statistic = 4 THEN st3.code\n" +
                "     WHEN st.level_statistic = 5 THEN st4.code\n" +
                "  END as ticketCateStatisticsCode2,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN st.name\n" +
                "     WHEN st.level_statistic = 3 THEN st2.name\n" +
                "     WHEN st.level_statistic = 4 THEN st3.name\n" +
                "     WHEN st.level_statistic = 5 THEN st4.name\n" +
                "  END as cateStatisticNameParent2,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN st.status\n" +
                "     WHEN st.level_statistic = 3 THEN st2.status\n" +
                "     WHEN st.level_statistic = 4 THEN st3.status\n" +
                "     WHEN st.level_statistic = 5 THEN st4.status\n" +
                "  END as status2,");

        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN st.code\n" +
                "     WHEN st.level_statistic = 4 THEN st2.code\n" +
                "     WHEN st.level_statistic = 5 THEN st3.code\n" +
                "  END as ticketCateStatisticsCode3,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN st.name\n" +
                "     WHEN st.level_statistic = 4 THEN st2.name\n" +
                "     WHEN st.level_statistic = 5 THEN st3.name\n" +
                "  END as cateStatisticNameParent3,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN st.status\n" +
                "     WHEN st.level_statistic = 4 THEN st2.status\n" +
                "     WHEN st.level_statistic = 5 THEN st3.status\n" +
                "  END as status3,");
        sql.append("   CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN null\n" +
                "     WHEN st.level_statistic = 4 THEN st.code\n" +
                "     WHEN st.level_statistic = 5 THEN st2.code\n" +
                "  END as ticketCateStatisticsCode4,");
        sql.append("   CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN null\n" +
                "     WHEN st.level_statistic = 4 THEN st.name\n" +
                "     WHEN st.level_statistic = 5 THEN st2.name\n" +
                "  END as cateStatisticNameParent4,");
        sql.append("   CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN null\n" +
                "     WHEN st.level_statistic = 4 THEN st.status\n" +
                "     WHEN st.level_statistic = 5 THEN st2.status\n" +
                "  END as status4,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN null\n" +
                "     WHEN st.level_statistic = 4 THEN null\n" +
                "     WHEN st.level_statistic = 5 THEN st.code\n" +
                "  END as ticketCateStatisticsCode5,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN null\n" +
                "     WHEN st.level_statistic = 4 THEN null\n" +
                "     WHEN st.level_statistic = 5 THEN st.name\n" +
                "  END as cateStatisticNameParent5,");
        sql.append("    CASE \n" +
                "     WHEN st.level_statistic =  1 THEN null\n" +
                "     WHEN st.level_statistic = 2 THEN null\n" +
                "     WHEN st.level_statistic = 3 THEN null\n" +
                "     WHEN st.level_statistic = 4 THEN null\n" +
                "     WHEN st.level_statistic = 5 THEN st.status\n" +
                "  END as status5,");
        sql.append("    st.TEMPLATE    AS template,");
        sql.append("    lower(st.CREATE_USER)          AS createUser,");
        sql.append("    st.CREATE_DATE          AS createDate,");
        sql.append("    lower(st.UPDATE_USER)          AS updateUser,");
        sql.append("    st.UPDATE_DATE          AS updateDate \n");
        sql.append("FROM STATISTIC_TYPE st              \n");
        sql.append("LEFT JOIN STATISTIC_TYPE st2 ON st.PARENT_ID = st2.STATISTIC_TYPE_ID \n");
        sql.append("LEFT JOIN STATISTIC_TYPE st3 ON st2.PARENT_ID = st3.STATISTIC_TYPE_ID \n");
        sql.append("LEFT JOIN STATISTIC_TYPE st4 ON st3.PARENT_ID = st4.STATISTIC_TYPE_ID \n");
        sql.append("LEFT JOIN STATISTIC_TYPE st5 ON st4.PARENT_ID = st5.STATISTIC_TYPE_ID \n");
        sql.append(" WHERE 1 = 1");
        if (params.getLstCateStatisticsLv1() != null && params.getLstCateStatisticsLv1().size() > 0 && params.getLstCateStatisticsLv2().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId)");
            sql.append(" or (st.parent_id in (:statisticTypeId)");
            sql.append(" or (st2.parent_id in (:statisticTypeId)");
            sql.append(" or (st3.parent_id in (:statisticTypeId)");
            sql.append(" or st4.parent_id in (:statisticTypeId)))))");
            hmapParams.put("statisticTypeId", params.getLstCateStatisticsLv1());
        }
        if (params.getLstCateStatisticsLv2() != null && params.getLstCateStatisticsLv2().size() > 0 && params.getLstCateStatisticsLv3().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId2)");
            sql.append(" or (st.parent_id in (:statisticTypeId2)");
            sql.append(" or (st2.parent_id in (:statisticTypeId2)");
            sql.append(" or st3.parent_id in (:statisticTypeId2))))");
            hmapParams.put("statisticTypeId2", params.getLstCateStatisticsLv2());
        }
        if (params.getLstCateStatisticsLv3() != null && params.getLstCateStatisticsLv3().size() > 0 && params.getLstCateStatisticsLv4().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId3)");
            sql.append(" or (st.parent_id in (:statisticTypeId3)");
            sql.append(" or st2.parent_id in (:statisticTypeId3)))");
            hmapParams.put("statisticTypeId3", params.getLstCateStatisticsLv3());
        }

        if (params.getLstCateStatisticsLv4() != null && params.getLstCateStatisticsLv4().size() > 0 && params.getLstCateStatisticsLv5().size() == 0) {
            sql.append(" and (st.STATISTIC_TYPE_ID in (:statisticTypeId4)");
            sql.append(" or st.parent_id in (:statisticTypeId4))");
            hmapParams.put("statisticTypeId4", params.getLstCateStatisticsLv4());
        }

        if (params.getLstCateStatisticsLv5() != null && params.getLstCateStatisticsLv5().size() > 0) {
            sql.append(" and st.STATISTIC_TYPE_ID in (:statisticTypeId5)");
            hmapParams.put("statisticTypeId5", params.getLstCateStatisticsLv5());
        }

        if (params.getLstLevelStatistics() != null && params.getLstLevelStatistics().size() > 0) {
            sql.append(" and st.LEVEL_STATISTIC in (:levelStatistic)");
            hmapParams.put("levelStatistic", params.getLstLevelStatistics());
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(st.CREATE_USER) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }

        if (params.getStatus() != null && params.getStatus().size() > 0) {
            sql.append(" and st.STATUS in (:status)");
            hmapParams.put("status", params.getStatus());
        }

        if (params.getFromDate() != null) {
            sql.append(" and trunc(st.CREATE_DATE) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFromDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(st.CREATE_DATE) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }
        sql.append(" ORDER BY st.CREATE_DATE DESC");
        return getListDataAndCount(sql, hmapParams, null, null, TicketCateStatisticDTO.class);
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }
}
