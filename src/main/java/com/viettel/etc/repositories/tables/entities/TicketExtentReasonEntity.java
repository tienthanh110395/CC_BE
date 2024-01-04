package com.viettel.etc.repositories.tables.entities;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.*;
/**
 * Autogen class Entity: Create Entity For Table Name Ticket_extent_reason
 * 
 * @author ToolGen
 * @date Fri Jan 07 16:57:18 ICT 2022
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_EXTENT_REASON")
public class TicketExtentReasonEntity implements Serializable{

    @Id
    @GeneratedValue(generator = "TICKET_EXTENT_REASON_SEQ")
    @SequenceGenerator(name = "TICKET_EXTENT_REASON_SEQ", sequenceName = "TICKET_EXTENT_REASON_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_EXTENT_REASON_ID")
    Long ticketExtentReasonId;

    @Column(name = "NAME")
    String name;

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

}