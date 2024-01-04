package com.viettel.etc.repositories.impl;

import com.viettel.etc.dto.TicketSLANewDTO;
import com.viettel.etc.dto.TicketTypeDTO;
import com.viettel.etc.repositories.TicketTypeRepository;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.HashMap;

/**
 * Autogen class Repository Impl:
 *
 * @author ToolGen
 * @date Sun Jan 23 17:12:57 ICT 2022
 */
@Repository
public class TicketTypeRepositoryImpl extends CommonDataBaseRepository implements TicketTypeRepository {

    @Autowired
    EntityManager entityManager;

    /**
     * @param itemParamsEntity: params client truyen len
     * @return
     */
    @Override
    public ResultSelectEntity getTicketType(TicketTypeDTO itemParamsEntity, Authentication authentication) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                               \n");
        sql.append("    TT.TICKET_TYPE_ID              as ticketTypeId,  \n");
        sql.append("    TT.NAME                        as name,          \n");
        sql.append("    TT.CODE                        as code,          \n");
        sql.append("    TT.PARENT_ID                   as parentId,      \n");
        sql.append("    TT.STATUS                      as status,        \n");
        sql.append("    TT.TICKET_TEMPLATE             as ticketTemplate,\n");
        sql.append("    TT.DESCRIPTION                 as description,   \n");
        sql.append("    TT.CREATE_USER                 as createUser,    \n");
        sql.append("    TT.CREATE_DATE                 as createDate,    \n");
        sql.append("    TT.UPDATE_USER                 as updateUser,    \n");
        sql.append("    TT.UPDATE_DATE                 as updateDate,    \n");
        sql.append("    TT.NAME                        as groupPA,       \n");
        sql.append("    TT1.NAME                       as subgroupPA,    \n");
        sql.append("    TT2.NAME                       as detailPA       \n");
        sql.append("FROM TICKET_TYPE TT                                  \n");
        sql.append("LEFT JOIN TICKET_TYPE TT1 ON TT.TICKET_TYPE_ID = TT1.PARENT_ID \n");
        sql.append("LEFT JOIN TICKET_TYPE TT2 ON TT1.TICKET_TYPE_ID = TT2.PARENT_ID \n");
        sql.append("WHERE 1 = 1                                          \n");

        if (itemParamsEntity != null && itemParamsEntity.getName() != null) {
            sql.append(" and UPPER(TT.NAME) = :name \n");
            hmapParams.put("name", itemParamsEntity.getName().toUpperCase());
        }

        if (itemParamsEntity != null && itemParamsEntity.getTicketTypeId() != null) {
            sql.append(" and TT.TICKET_TYPE_ID = :ticketTypeId \n");
            hmapParams.put("ticketTypeId", itemParamsEntity.getTicketTypeId());
        }

        if (itemParamsEntity != null && itemParamsEntity.getStatusReac() != null) {
            sql.append(" and TT.STATUS in (:status) \n");
            hmapParams.put("status", itemParamsEntity.getStatusReac());
        }

        Integer start = null;
        if (itemParamsEntity != null && itemParamsEntity.getStartrecord() != null) {
            start = itemParamsEntity.getStartrecord();
        }

        Integer pageSize = null;
        if (itemParamsEntity != null && itemParamsEntity.getPagesize() != null) {
            pageSize = itemParamsEntity.getPagesize();
        }

        sql.append(" order by TT.CREATE_DATE DESC \n");
        ResultSelectEntity resultData = getListDataAndCount(sql, hmapParams, start, pageSize, TicketTypeDTO.class);
        return resultData;
    }

    @Override
    public List<String> getListTicketGroupImport(Long levelTT) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("CODE FROM TICKET_TYPE WHERE LEVEL_TT = :levelTT");
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("levelTT", levelTT);
        return query.getResultList();
    }
}