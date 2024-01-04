package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Action_audit_detail
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:39 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "ACTION_AUDIT_DETAIL")
public class ActionAuditDetailEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "ACTION_AUDIT_DETAIL_SEQ")
    @SequenceGenerator(name = "ACTION_AUDIT_DETAIL_SEQ", sequenceName = "ACTION_AUDIT_DETAIL_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTION_AUDIT_DETAIL_ID")
    Long actionAuditDetailId;

    @Column(name = "ACTION_AUDIT_ID")
    Long actionAuditId;

    @CreationTimestamp
    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "TABLE_NAME")
    String tableName;

    @Column(name = "PK_ID")
    Long pkId;

    @Column(name = "COLUMN_NAME")
    String columnName;

    @Column(name = "OLD_VALUE")
    String oldValue;

    @Column(name = "NEW_VALUE")
    String newValue;

    @Column(name = "ACTION_NAME")
    String actionName;

    public enum ActionName {
        INSERT("INSERT"),
        UPDATE("UPDATE"),
        DELETE("DELETE"),
        STATUS("STATUS");
        public final String value;

        ActionName(String value) {
            this.value = value;
        }
    }
}
