package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_expire_cause
 *
 * @author ToolGen
 * @date Thu Jun 03 11:31:40 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_EXPIRE_CAUSE")
public class TicketExpireCauseEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(generator = "TICKET_EXPIRE_CAUSE_SEQ")
    @SequenceGenerator(name = "TICKET_EXPIRE_CAUSE_SEQ", sequenceName = "TICKET_EXPIRE_CAUSE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_EXPIRE_CAUSE_ID")
    Long ticketExpireCauseId;

    @Column(name = "NAME")
    String name;

    @Column(name = "CODE")
    String code;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "PARENT_ID")
    Long parentId;

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

    @Column(name = "LEVEL_EXPIRE")
    Long levelExpire;

//    @Column(name = "LEVEL_TT")
//    Long levelTt;

    public enum STATUS {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        STATUS(Long value) {
            this.value = value;
        }
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public enum ExpireCauseLevel {
        Level_1(1L),
        Level_2(2L),
        Level_3(3L);
        public final Long value;

        ExpireCauseLevel(Long value) {
            this.value = value;
        }
    }
}
