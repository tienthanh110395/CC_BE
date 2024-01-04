package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_source
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:46 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_SOURCE")
public class TicketSourceEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_SOURCE_SEQ")
    @SequenceGenerator(name = "TICKET_SOURCE_SEQ", sequenceName = "TICKET_SOURCE_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_SOURCE_ID")
    Long ticketSourceId;

    @Column(name = "NAME")
    String name;

    @Column(name = "SOURCE_CODE")
    String sourceCode;

    @Column(name = "DESCRIPTION")
    String description;

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

    public enum Status {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }

}
