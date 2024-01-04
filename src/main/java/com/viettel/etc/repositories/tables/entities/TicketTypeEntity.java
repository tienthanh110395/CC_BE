package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_type
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:46 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_TYPE")
public class TicketTypeEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(generator = "TICKET_TYPE_SEQ")
    @SequenceGenerator(name = "TICKET_TYPE_SEQ", sequenceName = "TICKET_TYPE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_TYPE_ID")
    Long ticketTypeId;

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

    @Column(name = "IS_CPT")
    Long isCPT;

    @Column(name = "TYPE")
    Long type;

    @Column(name = "TICKET_TEMPLATE")
    String ticketTemplate;

    @Column(name = "DEAD_TIME_TYPE")
    Long deadTimeType;

    @Column(name = "HOT_SLA")
    Long hotSla;

    @Column(name = "OTHERS_SLA")
    Long othersSla;

    @Column(name = "LEVEL_TT")
    Long levelTt;

    public enum Status {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }

    @Column(name = "MYCC_ID")
    String mycc_id;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public enum TicketTypeLevel {
        GROUP(1L),
        GENRE(2L),
        TYPE(3L);
        public final Long value;

        TicketTypeLevel(Long value) {
            this.value = value;
        }
    }

}