/*
package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_TYPE_LOG_DETAIL")
public class TicketTypeLogDetailEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_TYPE_LOG_DETAIL_SEQ")
    @SequenceGenerator(name = "TICKET_TYPE_LOG_DETAIL_SEQ", sequenceName = "TICKET_TYPE_LOG_DETAIL_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_TYPE_LOG_DETAIL_ID")
    Long ticketTypeLogDetailId;

    @Column(name = "TICKET_TYPE_LOG_ID")
    Long ticketTypeLogId;

    @Column(name = "TABLE_ID")
    Long tableId;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "COLUMN_NAME")
    String columnName;

    @Column(name = "OLD_VALUE")
    String oldValue;

    @Column(name = "NEW_VALUE")
    String newValue;

    @Column(name = "TABLE_NAME")
    String tableName;

    public enum ActionType {
        INSERT(1L),
        UPDATE(2L),
        DELETE(3L),
        STATUS(4L);

        public final Long value;

        ActionType(Long value) {
            this.value = value;
        }
    }
}
*/
