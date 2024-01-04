package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketStatisticTypeDTO;
import com.viettel.etc.repositories.TicketStatisticTypeRepository;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Wed Dec 01 13:45:05 ICT 2021
 */
@Repository
public class TicketStatisticTypeRepositoryImpl extends CommonDataBaseRepository implements TicketStatisticTypeRepository {

    /**
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getTicketStatisticType(TicketStatisticTypeDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hashMap = new HashMap<>();
        sql.append("SELECT \n");
        sql.append(" TST.STATISTIC_TYPE_ID      as statisticTypeId,             -- KHÓA CHÍNH MÃ LOẠI PHẢN ÁNH                     \n");
        sql.append(" TST.NAME                   as name,                        -- TÊN                                             \n");
        sql.append(" TST.CODE                   as code,                        -- MÃ                                              \n");
        sql.append(" TST.DESCRIPTION            as description,                 -- MÔ TẢ                                           \n");
        sql.append(" TST.PARENT_ID              as parentId,                    -- ID CHA                                          \n");
        sql.append(" TST.STATUS                 as status,                      -- TRẠNG THÁI SỬ DUNG                              \n");
        sql.append(" TST.CREATE_USER            as CREATE_USER,                 -- TÀI KHOẢN TẠO                                   \n");
        sql.append(" TST.CREATE_DATE            as createDate,                  -- THỜI GIAN TẠO                                   \n");
        sql.append(" TST.UPDATE_USER            as updateUser,                  -- TÀI KHOẢN CẬP NHẬP DỮ LIỆU                      \n");
        sql.append(" TST.UPDATE_DATE            as updateDate,                  -- THỜI GIAN CẬP NHẬP DỮ LIỆU                      \n");
        sql.append(" TST.MYCC_ID                as myccId                       -- MÃ DM TƯƠNG ỨNG TRÊN HỆ THỐNG MYCC              \n");
        sql.append(" FROM STATISTIC_TYPE TST WHERE TST.STATUS = 1                                                                  \n");
        if (itemParamsEntity.getParentId() == null) {
            sql.append("AND TST.PARENT_ID is null\n");
        } else {
            sql.append("AND TST.PARENT_ID = :parentId\n");
            hashMap.put("parentId", itemParamsEntity.getParentId());
        }
        sql.append(" order by nlssort(TST.STATISTIC_TYPE_ID, 'nls_sort = Vietnamese') \n");
        Integer start = null;
        if (itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }
        Integer pageSize = null;
        if (itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }
        return getListDataAndCount(sql, hashMap, start, pageSize, TicketStatisticTypeDTO.class);
    }
}