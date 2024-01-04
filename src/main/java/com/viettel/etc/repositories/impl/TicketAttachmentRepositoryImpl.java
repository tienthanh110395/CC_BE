package com.viettel.etc.repositories.impl;

import com.viettel.etc.repositories.TicketAttachmentRepository;
import com.viettel.etc.repositories.tables.entities.TicketAttachmentEntity;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class TicketAttachmentRepositoryImpl extends CommonDataBaseRepository implements TicketAttachmentRepository {
    /***
     * Lay thong tin file attach cua 1 loai ho tro
     * @param ticketAttachmentEntity
     * @return
     */
    @Override
    public ResultSelectEntity getTicketAttachment(TicketAttachmentEntity ticketAttachmentEntity) {
        StringBuilder sql = new StringBuilder();
        HashMap<String, Object> hmapParams = new HashMap<>();
        sql.append("SELECT                                                        \n");
        sql.append(" TAT.ATTACHMENT_ID                        as   attachmentId,  \n");
        sql.append(" TAT.TICKET_ID                            as   ticketId,      \n");
        sql.append(" TAT.FILE_NAME                            as   fileName,      \n");
        sql.append(" TAT.FILE_PATH                            as   filePath,      \n");
        sql.append(" TAT.DESCRIPTION                          as   description,   \n");
        sql.append(" TAT.TYPE                                 as   type,          \n");
        sql.append(" TAT.OBJECTS_ID                           as   objectsId,     \n");
        sql.append(" TAT.CREATE_USER                          as   createUser,    \n");
        sql.append(" TAT.CREATE_DATE                          as   createDate,    \n");
        sql.append(" TAT.UPDATE_USER                          as   updateUser,    \n");
        sql.append(" TAT.UPDATE_DATE                          as   updateDate     \n");
        sql.append("FROM                                                          \n");
        sql.append("    TICKET_ATTACHMENT TAT                                     \n");
        sql.append("WHERE  1 = 1                                                  \n");
        if (!FnCommon.isNullObject(ticketAttachmentEntity.getTicketId())) {
            sql.append(" AND TAT.TICKET_ID = :ticketId \n");
            hmapParams.put("ticketId", ticketAttachmentEntity.getTicketId());
        }
        if (!FnCommon.isNullObject(ticketAttachmentEntity.getType())) {
            sql.append(" AND TAT.TYPE = :type \n");
            hmapParams.put("type", ticketAttachmentEntity.getType());
        }
        return getListDataAndCount(sql, hmapParams, null, null, TicketAttachmentEntity.class);
    }
}
