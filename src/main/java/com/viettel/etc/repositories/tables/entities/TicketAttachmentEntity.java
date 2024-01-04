package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_attachment
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:45 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_ATTACHMENT")
public class TicketAttachmentEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_ATTACHMENT_SEQ")
    @SequenceGenerator(name = "TICKET_ATTACHMENT_SEQ", sequenceName = "TICKET_ATTACHMENT_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ATTACHMENT_ID")
    Long attachmentId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "FILE_NAME")
    String fileName;

    @Column(name = "FILE_PATH")
    String filePath;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "TYPE")
    Long type;

    @Column(name = "OBJECTS_ID")
    Long objectsId;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    public enum Type {
        TICKET(1L),
        TICKET_PROCESS(2L),
        TICKET_ADJUST_CHARGE(3L),
        TICKET_ASSIGN(4L),
        TICKET_ASSIGN_PROCESS(5L);

        public final Long value;

        Type(Long value) {
            this.value = value;
        }

    }

}
