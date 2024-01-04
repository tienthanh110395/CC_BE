package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_status
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:46 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_STATUS")
public class TicketStatusEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "TICKET_STATUS_SEQ")
    @SequenceGenerator(name = "TICKET_STATUS_SEQ", sequenceName = "TICKET_STATUS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_STATUS_ID")
    Long ticketStatusId;

    @Column(name = "TICKET_ID")
    Long ticketId;

    @Column(name = "SITE_ID")
    Long siteId;

    @Column(name = "TICKET_STATUS")
    Long ticketStatus;

    @Column(name = "PROCESS_TIME")
    Date processTime;

    @Column(name = "NOTE")
    String note;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;


    @Column(name = "PROCESS_CONTENT")
    String processContent;

    public enum TicketStatus {
        NEW(1L),//Trạng thái tạo mới
        IN_PROGRESS(2L),//Trạng thái đang xử lý
        CONCLUDE(3L),//Trang thái xu ly xong
        FINISH(4L),
        CLOSE(5L),// Trạng thái đóng phản ánh
        CANCEL(6L),//Trạng thái hủy
        TRACKING(7L);//Trạng thái theo dõi
        public final Long value;

        TicketStatus(Long value) {
            this.value = value;
        }
    }
}
