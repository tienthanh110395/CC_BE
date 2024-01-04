package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_site
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_SITE")
public class TicketSiteEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(generator = "TICKET_SITE_SEQ")
    @SequenceGenerator(name = "TICKET_SITE_SEQ", sequenceName = "TICKET_SITE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITE_ID")
    Long siteId;

    @Column(name = "SITE_CODE")
    String siteCode;

    @Column(name = "SITE_NAME")
    String siteName;

    @Column(name = "PARENT_ID")
    Long parentId;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "PHONE")
    String phone;

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

    @Column(name = "LEVEL_SITE")
    Long levelSite;

    public enum Status {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }

    public enum Level {
        ONE(1L),//Cấp 1
        TWO(2L),//Cấp 2
        THREE(3L);//Cấp 3
        public final Long value;

        Level(Long value) {
            this.value = value;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
