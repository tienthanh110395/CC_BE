package com.viettel.etc.repositories.tables.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.*;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_extent
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:32:10 ICT 2022
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_EXTENT")
public class TicketExtentEntity implements Serializable{

    @Id
    @GeneratedValue(generator = "TICKET_EXTENT_SEQ")
    @SequenceGenerator(name = "TICKET_EXTENT_SEQ", sequenceName = "TICKET_EXTENT_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_EXTENT_ID")
    Long ticketExtentId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "EXTENT_DATE")
    Date extentDate;

    @Column(name = "EXTENT_REASON_ID")
    Long extentReasonId;

    @Column(name = "EXTENT_REASON_CODE")
    String extentReasonCode;

    @Column(name = "EXTENT_REASON_NAME")
    String extentReasonName;

    @Column(name = "REQUEST_EXTENT_DATE")
    Date requestExtentDate;

    @Column(name = "MANAGER_USER_NAME")
    String managerUserName;

    @Column(name = "MANAGER_PHONE")
    String managerPhone;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "APPROVE_USER_NAME")
    String approveUserName;

    @Column(name = "APPROVE_DATE")
    Date approveDate;

    @Column(name = "APPROVE_REASON")
    String approveReason;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    public enum Status {
        APPROVE(2L),
        NOT_APPROVE(1L),
        REFUSE(0L);

        public final Long value;
        Status(Long s) {
            value = s;
        }

    }
}