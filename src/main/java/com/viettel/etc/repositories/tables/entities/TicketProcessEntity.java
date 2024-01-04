package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_process
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_PROCESS")
public class TicketProcessEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_PROCESS_SEQ")
    @SequenceGenerator(name = "TICKET_PROCESS_SEQ", sequenceName = "TICKET_PROCESS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_PROCESS_ID")
    Long ticketProcessId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "DESTROY_REASON")
    String destroyReason;

    @Column(name = "PROCESS_RESULT")
    String processResult;

    @Column(name = "PROCESS_TIME")
    Date processTime;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "REASON_LEVEL1")
    String reasonLevel1;

    @Column(name = "REASON_LEVEL2")
    String reasonLevel2;

    @Column(name = "PROCESS_CONTENT")
    String processContent;

    @Column(name = "STAFF_CODE")
    String staffCode;

    @Column(name = "STAFF_NAME")
    String staffName;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "TICKET_ERROR_CAUSE_ID_L1")
    Long ticketErrorCauseIdL1;

    @Column(name = "TICKET_ERROR_CAUSE_ID_L2")
    Long ticketErrorCauseIdL2;

    @Column(name = "TICKET_ERROR_CAUSE_ID_L3")
    Long ticketErrorCauseIdL3;

    @Column(name = "REASON_LEVEL3")
    String reasonLevel3;

    @Column(name = "TICKET_EXPIRE_CAUSE_ID_L1")
    Long ticketExpireCauseIdL1;

    @Column(name = "TICKET_EXPIRE_CAUSE_ID_L2")
    Long ticketExpireCauseIdL2;

    @Column(name = "TICKET_EXPIRE_CAUSE_ID_L3")
    Long ticketExpireCauseIdL3;

    @Column(name = "TICKET_EXPIRE_CAUSE_NAME_L1")
    String ticketExpireCauseNameL1;

    @Column(name = "TICKET_EXPIRE_CAUSE_NAME_L2")
    String ticketExpireCauseNameL2;

    @Column(name = "TICKET_EXPIRE_CAUSE_NAME_L3")
    String ticketExpireCauseNameL3;

    @Column(name = "TICKET_EXPIRE_SITE_ID")
    Long ticketExpireSiteId;

    @Column(name = "TICKET_SITE_ID_L1")
    Long ticketSiteIdL1;

    @Column(name = "TICKET_SITE_ID_L2")
    Long ticketSiteIdL2;

    @Column(name = "TICKET_SITE_ID_L3")
    Long ticketSiteIdL3;

    @Column(name = "EMAIL")
    String email;
}
