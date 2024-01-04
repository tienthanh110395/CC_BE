package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_assign
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_ASSIGN")
public class TicketAssignEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_ASSIGN_SEQ")
    @SequenceGenerator(name = "TICKET_ASSIGN_SEQ", sequenceName = "TICKET_ASSIGN_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_ASSIGN_ID")
    Long ticketAssignId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "SITE_ID")
    Long siteId;

    @Column(name = "FROM_USERNAME")
    String fromUsername;

    @Column(name = "TO_USERNAME")
    String toUsername;

    @Column(name = "ASSIGN_DATE")
    Date assignDate;

    @Column(name = "RESOLVE_DATE")
    Date resolveDate;

    @Column(name = "FROM_SITE_ID")
    Long fromSiteId;

    @Column(name = "SLA_DATE")
    Date slaDate;

    @Column(name = "ASSIGN_TYPE")
    Long assignType;

    @Column(name = "TICKET_STATUS")
    Long ticketStatus;

    @Column(name = "USER_PROCESS")
    String userProcess;

    @Column(name = "TO_SITE_ID")
    Long toSiteId;

    @Column(name = "ASSIGN_LEVEL")
    Long assignLevel;

    @Column(name = "ASSIGN_CONTENT")
    String assignContent;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "TO_SITE_L2_ID")
    Long toSiteL2Id;

    @Column(name = "TO_SITE_EMAIL")
    String toSiteEmail;

    public enum TicketStatus {
        NEW(1L),
        IN_PROGRESS(2L),
        CONCLUDE(3L),
        FINISH(4L),
        CLOSE(5L),
        REJECT(6L),
        CANCEL(7L);
        public final Long value;

        TicketStatus(Long value) {
            this.value = value;
        }
    }
}
