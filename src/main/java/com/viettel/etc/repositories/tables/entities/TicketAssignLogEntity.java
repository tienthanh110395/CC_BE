package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_assign_log
 *
 * @author ToolGen
 * @date Thu Mar 25 13:34:13 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_ASSIGN_LOG")
public class TicketAssignLogEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_ASSIGN_LOG_SEQ")
    @SequenceGenerator(name = "TICKET_ASSIGN_LOG_SEQ", sequenceName = "TICKET_ASSIGN_LOG_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_ASSIGN_LOG_ID")
    Long ticketAssignLogId;

    @Column(name = "TICKET_ASSIGN_ID")
    Long ticketAssignId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "LOG_CONTENT")
    String logContent;

    @Column(name = "SITE_ID")
    Long siteId;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "LOG_TYPE")
    Long logType;

}
