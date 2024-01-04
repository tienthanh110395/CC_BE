/*
package com.viettel.etc.services.impl;

import com.viettel.etc.repositories.tables.entities.TicketTypeLogDetailEntity;
import com.viettel.etc.services.TicketTypeLogDetailService;
import com.viettel.etc.services.tables.TicketTypeLogDetailServiceJPA;
import com.viettel.etc.utils.FnCommon;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TicketTypeLogDetailServiceImpl implements TicketTypeLogDetailService {
    private static final Logger log = Logger.getLogger(TicketTypeLogServiceImpl.class);

    @Autowired
    TicketTypeLogDetailServiceJPA ticketTypeLogDetailServiceJPA;

    @Override
    public List<TicketTypeLogDetailEntity> saveTicketTypeLog(Long tableId, Object oldEntity, Object newEntity, Long pkId, Long actionType) {
        log.info("TicketTypeNewLogServiceImpl start saveTicketTypeLog");
        if (newEntity == null && oldEntity == null) return null;
        List<TicketTypeLogDetailEntity> result = new ArrayList<>();
        try {
            String tableName;
            Field[] fields;
            if (Objects.nonNull(oldEntity)) {
                tableName = oldEntity.getClass().getAnnotation(Table.class).name();
                fields = oldEntity.getClass().getDeclaredFields();
            } else {
                tableName = newEntity.getClass().getAnnotation(Table.class).name();
                fields = newEntity.getClass().getDeclaredFields();
            }
            for (Field field : fields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (Objects.nonNull(column)) {
                    TicketTypeLogDetailEntity typeLogEntity = new TicketTypeLogDetailEntity();
                    typeLogEntity.setTableId(pkId);
                    typeLogEntity.setTicketTypeLogId(tableId);
                    typeLogEntity.setColumnName(column.name());
                    typeLogEntity.setTableName(tableName);
                    typeLogEntity.setCreateUser(typeLogEntity.getCreateUser());
                    if (Objects.nonNull(oldEntity)) {
                        typeLogEntity.setOldValue(getField(field, oldEntity));
                    }
                    typeLogEntity.setNewValue(getField(field, newEntity));
                    result.add(typeLogEntity);
                }
            }
            if (!FnCommon.isNullOrEmpty(result)) {
                result = ticketTypeLogDetailServiceJPA.saveAll(result);
            }
        } catch (Exception ex) {
            log.info("Has Error TicketTypeNewLogServiceImpl saveTicketTypeLog", ex);
        }
        return result;
    }

    private String getField(Field field, Object entity) {
        String result = "";
        try {
            if (field != null) {
                Class<?> dateType = Date.class;
                if (field.getType().isAssignableFrom(dateType)) {
                    result = FnCommon.convertDateToString((Date) field.get(entity), true, "/");
                } else {
                    result = Objects.toString(field.get(entity), null);
                }
            }
        } catch (Exception e) {
            log.error("Error getField: ", e);
        }
        return result;
    }
}
*/
