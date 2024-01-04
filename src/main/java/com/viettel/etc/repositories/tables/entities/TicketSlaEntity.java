package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Ticket_sla
 *
 * @author ToolGen
 * @date Mon Mar 01 09:31:46 ICT 2021
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TICKET_SLA")
public class TicketSlaEntity implements Serializable,Cloneable {

    @Id
    @GeneratedValue(generator = "TICKET_SLA_SEQ")
    @SequenceGenerator(name = "TICKET_SLA_SEQ", sequenceName = "TICKET_SLA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_SLA_ID")
    Long ticketSlaId;

    @Column(name = "SLA_NAME")
    String slaName;

    @Column(name = "SLA")
    Long sla;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "SITE_ID")
    Long siteId;

    @Column(name = "SOURCE_ID")
    Long sourceId;

    @Column(name = "TICKET_TYPE_ID")
    Long ticketTypeId;

    @Column(name = "CREATE_USER")
    String createUser;

    @Column(name = "CREATE_DATE")
    Date createDate;

    @Column(name = "UPDATE_USER")
    String updateUser;

    @Column(name = "UPDATE_DATE")
    Date updateDate;

    @Column(name = "PROCESS_TIME")
    Long processTime;

    @Column(name = "PROCESS_TIME_TYPE")
    Long processTimeType;

    @Column(name = "COMBINE_TIME_L1")
    Long combineTimeL1;

    @Column(name = "COMBINE_TIME_L1_TYPE")
    Long combineTimeL1Type;

    @Column(name = "COMBINE_TIME_L2")
    Long combineTimeL2;

    @Column(name = "COMBINE_TIME_L2_TYPE")
    Long combineTimeL2Type;

    @Column(name = "MAN_TIME_SLA")
    Long manTimeSla;

    @Column(name = "IS_ADD_COMBINE")
    Long isAddCombine;

    @Column(name = "PROCESS_TYPE")
    Long processType;

    @Column(name = "PRIORITY_ID")
    Long priorityId;

    @Column(name = "RECEPTION_TIME_FROM")
    String receptionTimeFrom;

    @Column(name = "RECEPTION_TIME_TO")
    String receptionTimeTo;

    @Column(name = "RE_TICKET_TIME")
    Long reTicketTime;

    public enum Status {
        VALID(1L),
        INVALID(0L);
        public final Long value;

        Status(Long value) {
            this.value = value;
        }
    }

    public enum Unit {
        DAY(1L),
        HOUR(2L);
        public final Long value;

        Unit(Long value) {
            this.value = value;
        }
    }

    public enum ProcessType {
        ALL_HOUR(0L),
        ROUND_LAST_DAY(1L),
        WORKING_HOUR(2L);
        public final Long value;

        ProcessType(Long value) {
            this.value = value;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}