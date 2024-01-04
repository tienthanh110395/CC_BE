package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Action_audit
 *
 * @author ToolGen
 * @date Tue Jun 08 12:19:39 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "ACTION_AUDIT")
public class ActionAuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "ACTION_AUDIT_SEQ")
    @SequenceGenerator(name = "ACTION_AUDIT_SEQ", sequenceName = "ACTION_AUDIT_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTION_AUDIT_ID")
    Long actionAuditId;

    @Column(name = "ACT_TYPE_ID")
    Long actTypeId;

    @Column(name = "CONTRACT_ID")
    Long contractId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "TICKET_ASSIGN_ID")
    Long ticketAssignId;

    @CreationTimestamp
    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "ACTION_USER_FULL_NAME")
    String actionUserFullName;

    @Column(name = "ACTION_USER_NAME")
    String actionUserName;

    @Column(name = "APP_ID")
    String appId;

    @Column(name = "IP_PC")
    String ipPc;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "TICKET_STATUS")
    Long ticketStatus;

    public enum Status {
        NOT_SUCCESS(0L),
        SUCCESS(1L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }

    public enum TicketStatus {
        NOT_SUCCESS(0L),
        SUCCESS(1L);
        public final Long value;

        TicketStatus(Long value) {
            this.value = value;
        }
    }
}
