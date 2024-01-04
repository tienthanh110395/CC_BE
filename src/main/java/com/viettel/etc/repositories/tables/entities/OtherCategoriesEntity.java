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
@Table(name = "TICKET_PRIORITIES")
public class OtherCategoriesEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue(generator = "TICKET_PRIORITIES_SEQ")
    @SequenceGenerator(name = "TICKET_PRIORITIES_SEQ", sequenceName = "TICKET_PRIORITIES_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "CODE")
    String code;

    @Column(name = "TYPE")
    Long type;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "IS_ACTIVE")
    Long isActive;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "TYPE_NAME")
    String typeName;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "PRIORITY_VALUE")
    Long priorityValue;

    public enum IsActive {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        IsActive(Long value) {
            this.value = value;
        }
    }
    public enum Type{
        TYPE1(1L),
        TYPE2(2L);
        public final Long value;
        Type(Long value){this.value=value;}
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}


