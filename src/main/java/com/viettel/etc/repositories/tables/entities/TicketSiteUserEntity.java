package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_site_user
 *
 * @author ToolGen
 * @date Mon Apr 05 09:30:08 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_SITE_USER")
public class TicketSiteUserEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_SITE_USER_SEQ")
    @SequenceGenerator(name = "TICKET_SITE_USER_SEQ", sequenceName = "TICKET_SITE_USER_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_SITE_USER_ID")
    Long ticketSiteUserId;

    @Column(name = "SITE_ID")
    Long siteId;

    @Column(name = "USER_ID")
    String userId;

    @Column(name = "USER_NAME")
    String userName;

    @Column(name = "STAFF_CODE")
    String staffCode;

    @Column(name = "STAFF_NAME")
    String staffName;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "PHONE")
    String phone;

    public enum Status {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }

}
