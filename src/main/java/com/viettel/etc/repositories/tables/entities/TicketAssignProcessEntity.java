package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_assign_process
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_ASSIGN_PROCESS")
public class TicketAssignProcessEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_ASSIGN_PROCESS_SEQ")
    @SequenceGenerator(name = "TICKET_ASSIGN_PROCESS_SEQ", sequenceName = "TICKET_ASSIGN_PROCESS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_ASSIGN_PROCESS_ID")
    Long ticketAssignProcessId;

    @Column(name = "TICKET_ASSIGN_ID")
    Long ticketAssignId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "PROCESS_CONTENT")
    String processContent;

    @Column(name = "PROCESS_TIME")
    Date processTime;

    @Column(name = "PROCESS_RESULT")
    String processResult;

    @Column(name = "SITE_ID")
    Long siteId;

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

    @Column(name = "ACT_TYPE_ID")
    Long actTypeId;

    @Column(name = "STATUS")
    Long status;

    public enum Status {
        NEW(1L),
        IN_PROGRESS(2L),
        CONCLUDE(3L),
        FINISH(4L),
        CLOSE(5L),
        REJECT(6L),
        CANCEL(7L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }
}
