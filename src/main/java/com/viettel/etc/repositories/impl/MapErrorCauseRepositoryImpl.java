package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.MapErrorCauseDTO;
import com.viettel.etc.dto.MapErrorCauseSearchDTO;
import com.viettel.etc.dto.MapErrorCauseUpdateDTO;
import com.viettel.etc.repositories.MapErrorCauseRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author ThaiBQ
 * @date 07/06/2023
 */
@Repository
public class MapErrorCauseRepositoryImpl extends CommonDataBaseRepository implements MapErrorCauseRepository {

    @Override
    public Object searchMapErrorCause(Authentication authentication, MapErrorCauseSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT ");
        sql.append("MAP_ID mapErrorCauseId,tt.name ticketGenre,ttn.name ticketGroup,tec1.name ticketErrorName1,tec2.name ticketErrorName2,");
        sql.append("tec3.name ticketErrorName3,mec.create_date createDate,lower(mec.create_user) createUser,mec.update_date updateDate, ");
        sql.append("lower(mec.update_user) updateUser,tt.TICKET_TYPE_ID ticketGenreId,ttn.TICKET_TYPE_ID ticketGroupId ");
        sql.append("from MAPPING_ERROR_CAUSE mec ");
        sql.append("left join TICKET_ERROR_CAUSE tec3 on mec.TICKET_ERROR_CAUSE_LV3_ID = tec3.TICKET_ERROR_CAUSE_ID ");
        sql.append("left join TICKET_ERROR_CAUSE tec2 on mec.TICKET_ERROR_CAUSE_LV2_ID = tec2.TICKET_ERROR_CAUSE_ID ");
        sql.append("left join TICKET_ERROR_CAUSE tec1 on mec.TICKET_ERROR_CAUSE_ID = tec1.TICKET_ERROR_CAUSE_ID ");
        sql.append("left join ticket_type tt on mec.TICKET_TYPE_ID = tt.TICKET_TYPE_ID ");
        sql.append("left join ticket_type ttn on tt.parent_id = ttn.TICKET_TYPE_ID ");
        sql.append("WHERE 1 = 1");

        if (!params.getLstTicketCategoryId().isEmpty()) {
            sql.append(" and mec.TICKET_TYPE_ID in (:ticketTypeId)");
            hmapParams.put("ticketTypeId", params.getLstTicketCategoryId());
        }

        if (!params.getLstTicketTypeGroupId().isEmpty() && params.getLstTicketTypeGroupId().size() > 0) {
            sql.append(" and ttn.TICKET_TYPE_ID in (:ticketCategoryId)");
            hmapParams.put("ticketCategoryId", params.getLstTicketTypeGroupId());
        }

        if (!params.getLstErrorCauseLv1().isEmpty()) {
            sql.append(" and mec.TICKET_ERROR_CAUSE_ID in (:lstErrorCauseLv1)");
            hmapParams.put("lstErrorCauseLv1", params.getLstErrorCauseLv1());
        }

        if (!params.getLstErrorCauseLv2().isEmpty()) {
            sql.append(" and mec.TICKET_ERROR_CAUSE_LV2_ID in (:lstErrorCauseLv2)");
            hmapParams.put("lstErrorCauseLv2", params.getLstErrorCauseLv2());
        }
        if (!params.getLstErrorCauseLv3().isEmpty()) {
            sql.append(" and mec.TICKET_ERROR_CAUSE_LV3_ID in (:lstErrorCauseLv3)");
            hmapParams.put("lstErrorCauseLv3", params.getLstErrorCauseLv3());
        }

        if (params.getFormDate() != null) {
            sql.append(" and trunc(MEC.CREATE_DATE) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFormDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(MEC.CREATE_DATE) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(MEC.CREATE_USER) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }

        sql.append(" ORDER BY MEC.CREATE_DATE DESC");

        return getListDataAndCount(sql, hmapParams, params.getStartRecord() * params.getPageSize(), params.getPageSize(), MapErrorCauseDTO.class);
    }

    /**
     * Lấy danh sách data ở màn Cập nhât nguyên nhân lỗi
     *
     * @param ticketGenreId
     * @param authentication
     * @return
     */
    @Override
    public Object searchDataMapForUpdate(Long ticketGenreId, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("select ");
        sql.append("mec.map_id mapErrorCauseId,tt1.name ticketGroup,tt.name ticketGenre,tt.ticket_type_id ticketGenreId,");
        sql.append("tec.ticket_error_cause_id ticketErrorId1,tec.name ticketErrorName1,tec.level_error levelTec,");
        sql.append("tec2.ticket_error_cause_id ticketErrorId2,tec2.name ticketErrorName2,tec2.parent_id parentIdLv2,tec2.level_error levelTec2, ");
        sql.append("tec3.ticket_error_cause_id ticketErrorId3,tec3.name ticketErrorName3,tec3.parent_id parentIdLv3,tec3.level_error levelTec3 ");
        sql.append(" from ticket_type tt ");
        sql.append(" join mapping_error_cause mec on mec.ticket_type_id = tt.ticket_type_id ");
        sql.append(" join ticket_error_cause tec on tec.ticket_error_cause_id = mec.ticket_error_cause_id ");
        sql.append(" join ticket_error_cause tec2 on tec2.ticket_error_cause_id = mec.ticket_error_cause_lv2_id ");
        sql.append(" join ticket_error_cause tec3 on tec3.ticket_error_cause_id = mec.ticket_error_cause_lv3_id ");
        sql.append(" left join ticket_type tt1 on tt.parent_id = tt1.ticket_type_id ");
        sql.append(" where 1 = 1");

        if (ticketGenreId != null) {
            sql.append(" and mec.ticket_type_id in (:ticketTypeId)");
            hmapParams.put("ticketTypeId", ticketGenreId);
        }
        sql.append(" ORDER BY tec3.ticket_error_cause_id ASC");

        return getListData(sql, hmapParams, null, null, MapErrorCauseUpdateDTO.class);
    }

    /**
     * @param : params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity exportImpact(MapErrorCauseSearchDTO params) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT ");
        sql.append("tt.code ticketGroupCode,tt.name ticketGroup,case when tt.status = 1 then 'Đang hiệu lực' else 'Không hiệu lực' end as ticketGroupStatus, ");
        sql.append("tt2.code ticketGenreCode,tt2.name ticketGenre,case when tt2.status = 1 then 'Đang hiệu lực' else 'Không hiệu lực' end as ticketGenreStatus, ");
        sql.append("tec.name ticketErrorName1,tec2.name ticketErrorName2,tec3.name ticketErrorName3, ");
        sql.append("tec.ticket_error_cause_id ticketErrorId1,tec2.ticket_error_cause_id ticketErrorId2,tec3.ticket_error_cause_id ticketErrorId3, ");
        sql.append("lower(mec.create_user) createUser,mec.create_date createDate,lower(mec.update_user) updateUser,");
        sql.append("mec.update_date updateDate ");

        sql.append("from ticket_type tt ");
        sql.append("left join ticket_type tt2 on tt2.parent_id = tt.TICKET_TYPE_ID ");
        sql.append("left join mapping_error_cause mec on tt2.ticket_type_id = mec.ticket_type_id ");
        sql.append("left join ticket_error_cause tec on mec.ticket_error_cause_id = tec.ticket_error_cause_id ");
        sql.append("left join ticket_error_cause tec2 on mec.ticket_error_cause_lv2_id = tec2.ticket_error_cause_id ");
        sql.append("left join ticket_error_cause tec3 on mec.ticket_error_cause_lv3_id = tec3.ticket_error_cause_id ");
        sql.append("WHERE 1 = 1 and tt.parent_id is null");

        if (params.getLstTicketTypeGroupId() != null && params.getLstTicketTypeGroupId().size() > 0) {
            sql.append(" and tt.TICKET_TYPE_ID in (:ticketCategoryId)");
            hmapParams.put("ticketCategoryId", params.getLstTicketTypeGroupId());
        }

        if (params.getLstTicketCategoryId() != null && params.getLstTicketCategoryId().size() > 0) {
            sql.append(" and tt2.TICKET_TYPE_ID in (:ticketGenreId)");
            hmapParams.put("ticketGenreId", params.getLstTicketCategoryId());
        }

        if (params.getLstErrorCauseLv1() != null && params.getLstErrorCauseLv1().size() > 0) {
            sql.append(" and mec.TICKET_ERROR_CAUSE_ID in (:lstErrorCauseLv1)");
            hmapParams.put("lstErrorCauseLv1", params.getLstErrorCauseLv1());
        }

        if (params.getLstErrorCauseLv2() != null && params.getLstErrorCauseLv2().size() > 0) {
            sql.append(" and mec.TICKET_ERROR_CAUSE_LV2_ID in (:lstErrorCauseLv2)");
            hmapParams.put("lstErrorCauseLv2", params.getLstErrorCauseLv2());
        }
        if (params.getLstErrorCauseLv3() != null && params.getLstErrorCauseLv3().size() > 0) {
            sql.append(" and mec.TICKET_ERROR_CAUSE_LV3_ID in (:lstErrorCauseLv3)");
            hmapParams.put("lstErrorCauseLv3", params.getLstErrorCauseLv3());
        }

        if (params.getFormDate() != null) {
            sql.append(" and trunc(MEC.CREATE_DATE) >= trunc(:fromDate)");
            hmapParams.put("fromDate", params.getFormDate());
        }

        if (params.getToDate() != null) {
            sql.append(" and trunc(MEC.CREATE_DATE) <= trunc(:toDate)");
            hmapParams.put("toDate", params.getToDate());
        }

        if (!FnCommon.isNullOrEmpty(params.getCreateUser())) {
            sql.append(" and lower(MEC.CREATE_USER) like (:createUser)");
            hmapParams.put("createUser", sqlStringSearch(params.getCreateUser(), true));
            if (params.getCreateUser().contains("_")) {
                sql.append(" ESCAPE '/'");
            }
        }
        return getListDataAndCount(sql, hmapParams, null, null, MapErrorCauseDTO.class);
    }

    @Override
    public Object getErrorCauseByParentIdForUpdateMap(Authentication authentication,
                                                      MapErrorCauseSearchDTO dataParams) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("select ");
        sql.append("tec.name AS ticketErrorName1,tec.ticket_error_cause_id AS ticketErrorId1, ");
        sql.append("tec2.name AS ticketErrorName2,tec2.ticket_error_cause_id AS ticketErrorId2, ");
        sql.append("tec3.name AS ticketErrorName3,tec3.ticket_error_cause_id AS ticketErrorId3, ");
        sql.append("tec2.parent_id AS parentIdLv2,tec3.parent_id AS parentIdLv3 ");
        sql.append("from ticket_error_cause  tec ");

        if (dataParams.getLstParentLvId2() != null) {
            sql.append("left join ( ( select * from ticket_error_cause tec where tec.status = 1 and tec.parent_id ");
            sql.append("in (:lstParentId2) ) tec2 ) on tec2.parent_id = tec.ticket_error_cause_id ");
            hmapParams.put("lstParentId2", dataParams.getLstParentLvId2());
        }

        if (dataParams.getLstParentLvId3() != null) {
            sql.append("left join ( ( select * from ticket_error_cause tec where tec.status = 1 and tec.parent_id ");
            sql.append("in (:lstParentId3) ) tec3 ) on tec3.parent_id = tec2.ticket_error_cause_id ");
            hmapParams.put("lstParentId3", dataParams.getLstParentLvId3());
        }

        sql.append("where tec.level_error in ( 1 ) ");
        sql.append("and tec.status = 1 ");
        sql.append(" ORDER BY tec3.ticket_error_cause_id ASC");
        return getListDataAndCount(sql, hmapParams, null, null, MapErrorCauseDTO.class);
    }

    private String escapeSql(String input) {
        return input.trim().replace("/", "//").replace("_", "/_")
                .replace("%", "/%");
    }

    private String sqlStringSearch(String str, boolean isLike) {
        return isLike ? "%" + escapeSql(str.toLowerCase().trim()) + "%" : escapeSql(str.toLowerCase().trim());
    }
}
